var treeMapBuilder = (function() {


    // initialize all variables
    var treemap, root, formatNumber, rname, margin, theight, width, height, transitioning, x, y, svg, grandparent, maxDepth, defaults
    var refreshing = false;
    var currentNodePath = []
	
    // initialize the entire treemap up till displaying
    function initializeTheTree(root) {
        initialize(root, width, height);
        accumulateValue(root);
        accumulateWarnings(root);
        layout(root, treemap);
    }

    // initialize the root of the treemap
    function initialize(root, width, height) {
        root.x = root.y = 0;
        root.dx = width;
        root.dy = height;
        root.depth = 0;
    }

    // Aggregate the values for internal nodes. This is normally done by the
    // treemap layout, but not here because of our custom implementation.
    // We also take a snapshot of the original children (_children) to avoid
    // the children being overwritten when when layout is computed.
    function accumulateValue(d) {
        return (d._children = d.values) ?
            d.value = d.values.reduce(function(p, v) {
                return p + accumulateValue(v);
            }, 0) :
            d.value;
    }

    function accumulateWarnings(d) {
        return (d._children = d.values) ?
            d.warnings = d.values.reduce(function(p, v) {
                return p + accumulateWarnings(v);
            }, 0) :
            d.warnings;
    }
    // Compute the treemap layout recursively such that each group of siblings
    // uses the same size (1×1) rather than the dimensions of the parent cell.
    // This optimizes the layout for the current zoom state. Note that a wrapper
    // object is created for the parent node for each group of siblings so that
    // the parent’s dimensions are not discarded as we recurse. Since each group
    // of sibling was laid out in 1×1, we must rescale to fit using absolute
    // coordinates. This lets us use a viewport to zoom.
    function layout(d, treemap) {
        appendInfoToSAT(sumNodeForASAT(d, getTotalASATWarning("CheckStyle")), sumNodeForASAT(d, getTotalASATWarning("PMD")), sumNodeForASAT(d, getTotalASATWarning("FindBugs")));
        if (d._children) {
            treemap.nodes({
                _children: d._children
            });
            d._children.forEach(function(c) {
                c.x = d.x + c.x * d.dx;
                c.y = d.y + c.y * d.dy;
                c.dx *= d.dx;
                c.dy *= d.dy;
                c.parent = d;
                layout(c, treemap);
            });
        }
    }
    function sumNodeForASAT(d, root) {
        var nodeAndSummation = [];
        var sum = 0;
        if (d.fileName == "Project" || d.fileName == "Test Project") {
            for (var i = 0; i < root.length; i++) {
                for (var j = 0; j < root[i].length; j++) {
                    sum += root[i][j].amountOfWarnings;
                }
            }
            return sum;
        } else {
            for (var i = 0; i < root.length; i++) {
                if (root[i].packageName == d.fileName) {
                    for (var j = 0; j < root[i].length; j++) {
                        sum += root[i][j].amountOfWarnings;
                    }
                    return sum;
                }
            }
        }
        return -1;
    }
    //render the chart with given depth and children
	function display(d) {
		// id for all squares
		var id = 0;
        // On click top bar to go back

        /*
         * Creates a tooltip that will be shown on hover over a node
         */
        var tooltip = d3.select("#chart")
            .append("div")
			.attr("id","d3-tip")
            .style("position", "absolute")
            .style("z-index", "10")
            .style("visibility", "hidden");

        grandparent
            .datum(d.parent)
            .on("click", transition)
            .select("text")
            .text(name(d))

        var g1 = svg.insert("g", ".grandparent")
            .datum(d)
            .attr("class", "depth");

        var g = g1.selectAll("g")
            .data(d._children)
            .enter().append("g");

        //on click square to go more in depth
        g.filter(function(d) {
                return d._children;
            })
            .classed("children", true)
            .on("click", navigationDown)
            .on("mouseover", function(d) {
                tooltip.html(d.fileName + "<br>" + getSatWarningsPrint(d));
                tooltip.style("visibility", "visible");
            })
            .on("mousemove", function(d) {
                tooltip.style("top", (d3.event.pageY - 10) + "px").style("left", (d3.event.pageX + 10) + "px");
            })
            .on("mouseout", function(d) {
                tooltip.style("visibility", "hidden");
            });

        var childrenArray = g.filter(function(d) {
                return d._children;
            })
			// bottom layer now we add a click to go to the code editor
			if ( childrenArray[0].length == 0 ){
				g.on("click", toSourceCode).on("mouseover", function(d) {
                    tooltip.html(d.fileName + "<br>" + getSatWarningsPrint(d));
                    tooltip.style("visibility", "visible");
            	})
            	.on("mousemove", function(d) {
                    tooltip.style("top", (d3.event.pageY - 10) + "px").style("left", (d3.event.pageX + 10) + "px");
            	})
            	.on("mouseout", function(d) {
                    tooltip.style("visibility", "hidden");
            	});
			}
            
        // all the updateContent class will trigger this refresh of data
        // so that the input of the user (checkboxes/radiobuttons) will update the content of 
        /*
        $(".updateContent").off("click").on('click', function(view) {
			//document.getElementById('treemapButton').checked ||
           if ( true) {
				if (view.target.name == "sat") {
					handleClickTreeMapTypeSat(view.target.value, view.target.checked);
				}else if (view.target.name == "category"){
					handleClickCategorySat(view.target.value, view.target.checked);
				}
                fastReload();
				
          }	
        });*/

        $('.updateContent').change(function() {
            $('.toggle').prop('disabled', true);
            if (true && !refreshing) {
                refreshing = true;
                $(this).disable = true
                if ($(this).prop('name') == "sat") {
                    handleClickTreeMapTypeSat($(this).prop('value'), $(this).prop('checked'));
                } else if ($(this).prop('name') == "category") {
                    handleClickCategorySat($(this).prop('value'), $(this).prop('checked'));
                }
                fastReload();
                // animation time of the toggle button
                var millisecondsToWait = 100;
                setTimeout(function() {
                    refreshing = false;
                    $(this).disable = false
                }, millisecondsToWait);
            }
            $('.toggle').prop('disabled', false);
        })

        function fastReload() {
            reloadContent();
            var newNode = findNode(currentNodePath, root);
            g.filter(function(newNode) {
                return newNode;
            });
            transition(newNode);
        }
        // code to find a certain node in the treemap


        function findNode(path, root) {
            var node = root;
            for (var i = 0; i < path.length; i++) {
                node = node._children[path[i]]
            }
            return node;
        }

        appendInfoToSAT(sumNodeForASAT(d, getTotalASATWarning("CheckStyle")), sumNodeForASAT(d, getTotalASATWarning("PMD")), sumNodeForASAT(d, getTotalASATWarning("FindBugs")));

        function reloadContent() {
            var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
            var finalJson = createJsonTreeMap(packages);
            root.values = finalJson;
            initialize(root, width, height);
            accumulateValue(root);
            accumulateWarnings(root);
            layout(root, treemap);
            display(root);
        }

        var children = g.selectAll(".child")
            .data(function(d) {
                return d._children || [d];
            })
            .enter().append("g");

        children.append("rect")
            .attr("class", "child")
            .call(rect)
			.style("fill", function(d) {
				console.log(d);
                var ratio = Math.round(100 * d.warnings / d.value);	
				if ( ratio > 100 ) { ratio = 100; }
				id +=1;
				var gradientBackground = backgroundGradient.getBackground(svg, ratio, id);
                return "url(#gradient"+ id + ")";
            })
            .append("title");
			
        children.append("text")
            .attr("class", "ctext")
            .text(function(d) {
                return d.fileName;
            })
            .call(textBottomRight);

        g.append("rect")
            .attr("class", "parent")
            .call(rect);

        var t = g.append("text")
            .attr("class", "ptext")
            .attr("dy", ".75em");

        t.append("tspan")
            .text(function(d) {
                return d.fileName;
            });

        //title of the squares
        t.append("tspan")
            .attr("dy", "1.0em")
            .text(function(d) {
                return d.warnings;
            });
        t.call(text);

        // Method for counting the different warnings
        function getSatWarningsPrint(d) {
            output = ""
            for (var i = 0; i < acceptedTypes.length; i++) {
                switch (acceptedTypes[i]) {
                    case "CheckStyle":
                        output += acceptedTypes[i] + ": " + formatNumber(d.warningsCheckStyle) + " <br> ";
                        break;
                    case "PMD":
                        output += acceptedTypes[i] + ": " + formatNumber(d.warningsPMD) + " <br> ";
                        break;
                    case "FindBugs":
                        output += acceptedTypes[i] + ": " + formatNumber(d.warningsFindBugs) + " <br> ";
                        break;
                    default:
                        output += "";
                }
            }
            return output.slice(0, -3);
        }
		/*
		// code for normal color based on amount of warnings relative to lines
        g.selectAll("rect")
            .style("fill", function(d) {
                var ratio = 100 * d.warnings / d.value;
				var gradientBackground = backgroundGradient.getBackground(svg);
                return "url(#gradient)";
                //return backgroundGradient.getBackground();
				
				
            });*/


        function navigationDown(d) {
            currentNodePath.push(findChildNumber(d, d.parent));
            transition(d)
        }

        function toSourceCode(d) {
        	sessionStorage.setItem('fileName', d.fileName);
			window.open('codeEditor.html','_self',false)
        }

        function findChildNumber(d, parent) {
            for (var i = 0; i < parent._children.length; i++) {
                if (parent._children[i].fileName == d.fileName) {
                    return i;
                }
            }
            return null;
        }

        function navigationUp(d) {
            currentNodePath.pop();
            transition(d)
        }

        function transition(d) {

            if (transitioning || !d) return;
            transitioning = true;
            appendInfoToSAT(sumNodeForASAT(d, getTotalASATWarning("CheckStyle")), sumNodeForASAT(d, getTotalASATWarning("PMD")), sumNodeForASAT(d, getTotalASATWarning("FindBugs")));

            tooltip.style("visibility", "hidden");

            var g2 = display(d),
                t1 = g1.transition().duration(100),
                t2 = g2.transition().duration(100);

            // Update the domain only after entering new elements.
            x.domain([d.x, d.x + d.dx]);
            y.domain([d.y, d.y + d.dy]);

            // Enable anti-aliasing during the transition.
            svg.style("shape-rendering", null);

            // Draw child nodes on top of parent nodes.
            svg.selectAll(".depth").sort(function(a, b) {
                return a.depth - b.depth;
            });

            // Fade-in entering text.
            g2.selectAll("text").style("fill-opacity", 0);

            // Transition to the new view.
            t1.selectAll(".ptext").call(text).style("fill-opacity", 0);
            t1.selectAll(".ctext").call(textBottomRight).style("fill-opacity", 0);
            t2.selectAll(".ptext").call(text).style("fill-opacity", 1);
            t2.selectAll(".ctext").call(textBottomRight).style("fill-opacity", 1);
            t1.selectAll("rect").call(rect);
            t2.selectAll("rect").call(rect);

            // Remove the old node when the transition is finished.
            t1.remove().each("end", function() {
                svg.style("shape-rendering", "crispEdges");
                transitioning = false;
            });
        }

        return g;
    }

	function text(text) {
        text.selectAll("tspan")
            .attr("x", function(d) {
                return x(d.x) + 6;
            })
        text.attr("x", function(d) {
                return x(d.x) + 6;
            })
            .attr("y", function(d) {
                return y(d.y) + 6;
            })
            .style("opacity", function(d) {
                return this.getComputedTextLength() < x(d.x + d.dx) - x(d.x) ? 1 : 0;
            });
    }

    function textBottomRight(text) {
        text.attr("x", function(d) {
                return x(d.x + d.dx) - this.getComputedTextLength() - 6;
            })
            .attr("y", function(d) {
                return y(d.y + d.dy) - 6;
            })
            .style("opacity", function(d) {
                return this.getComputedTextLength() < x(d.x + d.dx) - x(d.x) ? 1 : 0;
            });
    }

    function rect(rect) {
        rect.attr("x", function(d) {
                return x(d.x);
            })
            .attr("y", function(d) {
                return y(d.y);
            })
            .attr("width", function(d) {
                return x(d.x + d.dx) - x(d.x);
            })
            .attr("height", function(d) {
                return y(d.y + d.dy) - y(d.y);
            });
    }
    //title above the chart
    function name(d) {
        return d.parent ?
            name(d.parent) + " / " + d.fileName : //+ " (" + formatNumber(d.warnings) + ")" :
            d.fileName; // + " (" + formatNumber(d.warnings) + ")";
    }
	
    function setTheVariables(o, data) {
        // hard coded the depth where the click should go to source code (no zoom)
        maxDepth = 2
        defaults = {
            margin: {
                top: 30,
                right: 0,
                bottom: 0,
                left: 0
            },
            rootname: "TOP",
            format: ",d",
            title: "",
            width: window.innerWidth - 750,
            height: window.innerHeight - 175
        };
        // Remove the chart if there is already one.
        removeChart();
        // Setting up some values about number format(rounding) and marigns
        opts = $.extend(true, {}, defaults, o);
        formatNumber = d3.format(opts.format);
        rname = opts.rootname;
        margin = opts.margin;
        theight = 36 + 16;

        // size of the chart 
        $('#chart').width(opts.width).height(opts.height);
        width = opts.width - margin.left - margin.right;
        height = opts.height - margin.top - margin.bottom;
        // Uses a range of 100 values between green and red
        // The closer the value is to 0, the more green it will use
        // The closer the value is to 100, the more red it will use

        x = d3.scale.linear()
            .domain([0, width])
            .range([0, width]);

        y = d3.scale.linear()
            .domain([0, height])
            .range([0, height]);

        // Create the d3 treemap from the library
        treemap = d3.layout.treemap()
            .children(function(d, depth) {
                return depth ? null : d._children;
            })
            .sort(function(a, b) {
                return a.value - b.value;
            })
            .ratio(height / width * 0.5 * (1 + Math.sqrt(5)))
            .round(false);

        // creating the chart and appending it to views
        svg = d3.select("#chart").append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.bottom + margin.top)
            .style("margin-left", -margin.left + "px")
            .style("margin.right", -margin.right + "px")
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
            .style("shape-rendering", "crispEdges");

        grandparent = svg.append("g")
            .attr("class", "grandparent");

        grandparent.append("rect")
            .attr("y", -margin.top)
            .attr("width", width)
            .attr("height", margin.top);


        grandparent.append("text")
            .attr("x", 6)
            .attr("y", 6 - margin.top)
            .attr("dy", ".75em")

        if (data instanceof Array) {
            root = {
                fileName: rname,
                values: data
            };
        } else {
            root = data;
        }
    }
	
    return {
        // The main method which is called to create the treeMap.
        // This calls all the methods needed like initialize.
        createTreeMap: function(o, data) {
            // First we create all variables that are needed for this treemap.
            setTheVariables(o, data);
            // After cresating the variables we can start initializing and displaying the tree.
            initializeTheTree(root);
            display(root);
        }

    };

}());