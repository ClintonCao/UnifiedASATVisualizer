var treeMapBuilder = (function() {


    // initialize all variables
    var treemap, root, formatNumber, rname, margin, theight, width, height, transitioning, x, y, svg, grandparent, maxDepth, defaults
	var refreshing = false;


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
    //render the chart with given depth and children
    function display(d) {
        // On click top bar to go back
        grandparent
            .datum(d.parent)
            .on("click", transition)
            .select("text")
            .text(name(d));

        var g1 = svg.insert("g", ".grandparent")
            .datum(d)
            .attr("class", "depth");

        var g = g1.selectAll("g")
            .data(d._children)
            .enter().append("g");

        //on click square to go more in depth
        if (d.depth < maxDepth) {
            g.filter(function(d) {
                    return d._children;
                })
                .classed("children", true)
                .on("click", transition);
        }
		
		// all the updateContent class will trigger this refresh of data
		// so that the input of the user (checkboxes/radiobuttons) will update the content of 
        $(".updateContent").off("click").on('click', function(view) {
		 console.log("treemap update");
            if (document.getElementById('treemapButton').checked && !refreshing) {
				refreshing = true;
				
                handleClickTreeMapTypeSat(view.target.value, view.target.checked)
                reloadContent();
                var newNode = findNode(d, root);
                g.filter(function(newNode) {
                    return newNode;
                });
                transition(newNode);
				var millisecondsToWait = 200;
				setTimeout(function() {
    				refreshing = false;
				}, millisecondsToWait);

            }	
        });

        appendInfoToSAT(sumNodeForASAT(d, getTotalASATWarning("CheckStyle")), sumNodeForASAT(d, getTotalASATWarning("PMD")), sumNodeForASAT(d, getTotalASATWarning("FindBugs")));

        function findNode(d, root) {
            if (root.fileName != null && root.values != null) {
                if (root.fileName == d.fileName) {
                    return (root);
                } else {
                    var arr = root.values;
                    for (var i = 0; i < arr.length; i++) {
                        var finalNode = findNode(d, arr[i]);
                        if (finalNode != null) {
                            return finalNode;
                        }
                    }
                }
            } else {
                var err = null
                return err;
            }
        }

        function sumNodeForASAT(d, root) {
            var nodeAndSummation = [];
            var sum = 0;
            if(d.fileName == "Project" || d.fileName == "Test Project") {
                for(var i = 0; i < root.length; i++) {
                    for(var j = 0; j < root[i].length; j++) {
                        sum += root[i][j].amountOfWarnings;
                    }
                }
                return sum;
            } else {
                for(var i = 0; i < root.length; i++) {
                    if(root[i].packageName == d.fileName) {
                        for(var j = 0; j < root[i].length; j++) {
                            sum += root[i][j].amountOfWarnings;
                        }
                        return sum;
                    }
                }
            }
            return -1;
        }

        function reloadContent() {
    		var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    		var finalJson =  createJsonTreeMap(packages);
            root.values = finalJson;
            console.log(getTotalASATWarning("PMD"));
            initialize(root, width, height);
            accumulateValue(root);
            accumulateWarnings(root);
            layout(root, treemap);
            display(root);
        }

        // on click child to go to source code
        if (d.depth == maxDepth) {
            g.filter(function(d) {
                    return d;
                })
                .classed("child", true)
                .on("click", toSourceCode);
        }

        var children = g.selectAll(".child")
            .data(function(d) {
                return d._children || [d];
            })
            .enter().append("g");

        children.append("rect")
            .attr("class", "child")
            .call(rect)
            .append("title")
            .text(function(d) {
                return d.fileName + " (" + formatNumber(d.warnings) + ")";
            });
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
            .attr("dy", ".75em")

        t.append("tspan")
            .text(function(d) {
                return d.fileName;
            });
			
        //title of the squares
        t.append("tspan")
            .attr("dy", "1.0em")
            .text(function(d) {
                return getSatWarningsPrint(d);
            });
        t.call(text);

		// future method for more advance warning count
		function getSatWarningsPrint(d){
			output = ""
			for (var i = 0; i < acceptedTypes.length; i++){
				output += formatNumber(d.warnings) + acceptedTypes[i].substring(0,1) + " ";
			}
			return formatNumber(d.warnings);	
		}
        // set the color of the squares based on warnings / line
        g.selectAll("rect")
            .style("fill", function(d) {
                var ratio = 100 * d.warnings / d.value;
                // if statement for when there are more warnings then lines
                return colorScale.getColor(ratio);
            });

        function toSourceCode(d) {
            //console.log(d.filePath)
        }

        function transition(d) {
            if (transitioning || !d) return;
            transitioning = true;

            appendInfoToSAT(sumNodeForASAT(d, getTotalASATWarning("CheckStyle")), sumNodeForASAT(d, getTotalASATWarning("PMD")), sumNodeForASAT(d, getTotalASATWarning("FindBugs")));

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
            name(d.parent) + " / " + d.fileName + " (" + formatNumber(d.warnings) + ")" :
            d.fileName + " (" + formatNumber(d.warnings) + ")";
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
            width: window.innerWidth - 350,
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