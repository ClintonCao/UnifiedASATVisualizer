	var treeMapBuilder = (function() {

    // initialize all variables
    var treemap, formatNumber, rname, margin, theight, width, height, transitioning, x, y, svg, grandparent, maxDepth, defaults
    var refreshing = false;
    var upperLevel = true;
	var currentNodePath = [];
	var root;

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


    /*
     * Will put the #warnings for each ASAT
     */
    function updateASATWarningsCount(d) {
        var CheckStyleWarnings = sumNodeForASAT(d, getTotalASATWarning("CheckStyle"));
        var PMDWarnings = sumNodeForASAT(d, getTotalASATWarning("PMD"));
        var FindBugsWarnings = sumNodeForASAT(d, getTotalASATWarning("FindBugs"));
        appendInfoToSAT(CheckStyleWarnings, PMDWarnings, FindBugsWarnings);
    }

    /*
     * Will put the #warnings for the function defects category
     */
    function updateFunctionalDefectsCount(d) {
        var CheckWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Check"));
        var ConcWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Concurrency"));
        var ErrorWarnings = sumNodeForASAT(d, getTotalCategoryWarning("ErrorHandling"));
        var InterfaceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Interface"));
        var LogicWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Logic"));
        var MigrationWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Migration"));
        var ResourceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Resource"));
        appendInfoToFunctionalDefects(CheckWarnings, ConcWarnings, ErrorWarnings, InterfaceWarnings, LogicWarnings, MigrationWarnings, ResourceWarnings);
    }

    /*
     * Will put the #warnings for the maintainability defects category
     */
    function updateMaintainabilityDefectsCount(d) {
        var BestPracticeWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Best Practices"));
        var CodeStructureWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Code Structure"));
        var DocConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Documentation Conventions"));
        var MetricWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Metric"));
        var NamingConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Naming Conventions"));
        var OODesignWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Object Oriented Design"));
        var SimplificationsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Simplifications"));
        var ReduncanciesWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Redundancies"));
        var StyleConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Style Conventions"));
        appendInfoToMaintainabilityDefects(BestPracticeWarnings, CodeStructureWarnings, DocConventionsWarnings, MetricWarnings, NamingConventionsWarnings, OODesignWarnings, SimplificationsWarnings, ReduncanciesWarnings, StyleConventionsWarnings);
    }

    /*
     * Will put the #warnings for the other defects category
     */
    function updateOtherDefectsCount(d) {
        var OtherWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Other"));
        var RegularExpressionsWarnings =sumNodeForASAT(d, getTotalCategoryWarning("Regular Expressions"));
        var ToolSpecificWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Tool Specific"));
        appendInfoToOtherDefects(OtherWarnings, RegularExpressionsWarnings, ToolSpecificWarnings);
    }

    /*
     * Will put the #warnings for each specific ASAT and warning type
     */
    function updateWarningsCountInUI(d) {
        updateASATWarningsCount(d);
        updateFunctionalDefectsCount(d);
        updateMaintainabilityDefectsCount(d);
        updateOtherDefectsCount(d);
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
        updateWarningsCountInUI(d);

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
	
	  // Code to find a certain node in the treemap
        function findNode(path, root) {
            var node = root;
            for (var i = 0; i < path.length; i++) {
                node = node._children[path[i]]
            }
            return node;
        }

    /*
     * Sums for each node how many warnings they have.
     * It still checks on Project and Test Project hard coded to find wheter it is on
     * TODO: package level or class level, this should be changed to a dynamic way in the future.
     */
    function sumNodeForASAT(d, root) {
        var nodeAndSummation = [];
        var sum = 0;
        if (d.fileName == projectName) {
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

    //Renders the chart with given depth and children
	function display(d) {
		// id for all squares
		var id = 0;

        setPath(d, name(d));

        /*
         * Creates the navigation balk where you can keep track of
         * which level you are and which you can use to navigate back
         */
        // grandparent
        //     .datum(d.parent)
        //     .on("click", navigationUp)
        //     .select("text")
        //     .style("fill", function() {
        //         return '#333333';
        //     });

        var g1 = svg.insert("g", ".chart-and-code")
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
				$('#extra-info-div').css('display', 'inline-block');
				$('#extra-info-div').html(d.fileName + "<br>" + getSatWarningsPrint(d));
            })
            .on("mouseout", function(d) {
				$('#extra-info-div').html("");
				$('#extra-info-div').css('display', 'none');
            });

        var childrenArray = g.filter(function(d) {
                return d._children;
            })
			// bottom layer now we add a click to go to the code editor
			if ( childrenArray[0].length == 0 ){
				g.on("click", toSourceCode).on("mouseover", function(d) {

            	})
            	.on("mousemove", function(d) {
					$('#extra-info-div') .css('display', 'inline-block')
					$('#extra-info-div').html(d.fileName + "<br>" + getSatWarningsPrint(d));
            	})
            	.on("mouseout", function(d) {
					$('#extra-info-div').html("");
				$('#extra-info-div') .css('display', 'none')
            	});
			}

        /*
         * This function will be triggered when the user clicks on a button
         * It will refresh the data in the treemap according to which button is clicked
         */
        $('.updateContent').change(function() {
            if (true && !refreshing) {
                refreshing = true;
                $(this).disable = true
                if ($(this).prop('name') == "sat") {
                    handleClickTreeMapTypeSat($(this).prop('value'), $(this).prop('checked'));
                } else if ($(this).prop('name') == "category") {
                    handleClickCategorySat($(this).prop('value'), $(this).prop('checked'));
                } else if($(this).prop('name') == "relative") {
                    handleClickRelativeColours($(this));
                }
                fastReload();
                // animation time of the toggle button
                var millisecondsToWait = 0;
                setTimeout(function() {
                    refreshing = false;
                    $(this).disable = false
                }, millisecondsToWait);
            }
        })

        function fastReload() {
            reloadContent();
            var newNode = findNode(currentNodePath, root);
            transition(newNode);
        }



        // Updates all warning counts for all ASATS and categories
        updateWarningsCountInUI(d);

        function reloadContent() {
            var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
            var finalJson = createJsonTreeMap(packages);
            root.values = finalJson;
            initialize(root, width, height);
            accumulateValue(root);
            accumulateWarnings(root);
            layout(root, treemap);
        }

        var children = g.selectAll(".child")
            .data(function(d) {
                return d._children || [d];
            })
            .enter().append("g");

        children.append("rect")
            .attr("class", "child")
            .attr("x", function(d) {
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
            })
			.style("fill", function(d) {
				var ratios =  backgroundObject.getRatios(d);
				var weight = d.warnings / d.value;
				id +=1;
				var gradientBackground = backgroundObject.getBackground(svg, ratios,weight, id,x(d.x + d.dx),y(d.y + d.dy) );
                return "url(#gradient"+ id + ")";
            })
            .append("title");

        /*
         * Sets in the lower right corner of a node the filename
         */
        children.append("text")
            .attr("class", "ctext")
            .text(function(d) {
                return d.fileName;
            })
            .style("fill", function() {
                return '#FFFFFF';
            })
            .call(textBottomRight);

        if(currentNodePath.length == 1) {
            g.append("rect")
            .attr("class", "parent")
            .attr("class", "child")
            .attr("x", function(d) {
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
        } else {
            g.append("rect")
            .attr("class", "parent")
            .attr("class", "child")
            .call(rect);
        }

        var t = g.append("text")
            .attr("class", "ptext")
            .attr("dy", ".75em");

        /*
         * Sets in the upper left corner of a node the filename
         */
        t.append("tspan")
            .style("fill", function(d) {
                return '#FFFFFF';
            })
            .text(function(d) {
                return d.fileName;
            });

        /*
         * Sets in the upper left corner of a node the amount of warnings
         */
        t.append("tspan")
            .attr("dy", "1.2em")
            .text(function(d) {
                return d.warnings;
            })
            .style("fill", function(d) {
                return '#FFFFFF';
            });
        t.call(text);

        // Method for counting the different warnings
        function getSatWarningsPrint(d) {
            output = ""
            output += "Lines of code: " + d.loc + " <br> ";
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
            $('input.updateContent').attr('disabled','disabled');
            sourceCode.show(d, name(d));
            setPath(d, name(d));
        	$('.CodeMirror').width(opts.width).height(opts.height - 30);
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

            if (transitioning || !d) {
                upperLevel = true;
                return;
            }
            upperLevel = false;
            transitioning = true;

            var g2 = display(d),
                t1 = g1.transition().duration(100),
                t2 = g2.transition().duration(100);

            // Update the domain only after entering new elements.
            x.domain([d.x, d.x + d.dx]);
            y.domain([d.y, d.y + d.dy]);

            // Enable anti-aliasing during the transition.
            svg.style("shape-rendering", null);

            // Draw child nodes on top of parent nodes.
            // svg.selectAll(".depth").sort(function(a, b) {
            //     return a.depth - b.depth;
            // });

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

		function goToRelevantLevel(indexString, fromSourceCode) {
			var index = parseInt(indexString.substring(indexString.length-2,indexString.length-1));
			
			while (currentNodePath.length > index){
				console.log("pop");
				currentNodePath.pop();
			}
				var d = findNode(currentNodePath, root);
			if(fromSourceCode) {
				sourceCode.hide();
				transition(d);
			}else{
				transition(d);
				display(d);
			}
		}
		function setPath(d, path) {
			var subTitleDiv = document.getElementById("current-path");
			if(path.indexOf('/') > -1) {
				var pathFirstPart = path.substring(0, path.lastIndexOf("/") + 1);
				var pathSecondPart = path.split(/[/ ]+/).pop();
				var allPreviousLevels = path.split("/");
                var newPath = "";
                var usedIDs = [];
                for(var i = 0; i < allPreviousLevels.length - 1; i++) {
                    var id = "prevLocation" + i;
                    newPath += '<span class="path-span" id="\'' + ("prevLocation" + i) + '\'">' + allPreviousLevels[i] + '</span>/ ';
                    usedIDs.push(id);
                }
                var index = allPreviousLevels.length - 1;
                newPath += '<span id="currentLocation">' + allPreviousLevels[allPreviousLevels.length - 1] + '</span>';
                subTitleDiv.innerHTML = newPath;
				if(pathSecondPart.indexOf("java") > -1) {
					for(var i = 0; i < usedIDs.length; i++) {
						var stringID = "'" + usedIDs[i] + "'";
						document.getElementById(stringID).addEventListener("click", function() {
							goToRelevantLevel($(this).attr('id'), true);
							}, false);
					}
				} else {
                    for(var i = 0; i < usedIDs.length; i++) {
                        var stringID = "'" + usedIDs[i] + "'";
                        document.getElementById(stringID).addEventListener("click", function() {
                            goToRelevantLevel($(this).attr('id'), false);
                            }, false);
                    }
				}
			} else {
			   subTitleDiv.innerHTML = " <span id='currentLocation'>" + path + "</span>";
			}
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

    function setPath(d, path) {
        var subTitleDiv = document.getElementById("current-path");
        if(path.indexOf('/') > -1) {
            var pathFirstPart = path.substring(0, path.lastIndexOf("/") + 1);
            var pathSecondPart = path.split(/[/ ]+/).pop();
            var allPreviousLevels = path.split("/");
            if(pathSecondPart.indexOf("java") > -1) {
                var newPath = "";
                var usedIDs = [];
                for(var i = 0; i < allPreviousLevels.length - 1; i++) {
                    var id = "prevLocation" + i;
                    newPath += '<span class="path-span" id="\'' + ("prevLocation" + i) + '\'">' + allPreviousLevels[i] + '</span>/ ';
                    usedIDs.push(id);
                }
                newPath += '<span id="currentLocation">' + allPreviousLevels[allPreviousLevels.length - 1] + '</span>';
                console.log(newPath);
                subTitleDiv.innerHTML = newPath;
                for(var i = 0; i < usedIDs.length; i++) {
                    var stringID = "'" + usedIDs[i] + "'";
					var node = currentPathNodes[i];
                    console.log(node);
                    document.getElementById(stringID).addEventListener("click", function() {console.log("Node added: "); console.log($(this).id); console.log(currentPathNodes); goToRelevantLevel(node, true, currentNodePath, currentPathNodes);}, false);
                }
            } else {
                subTitleDiv.innerHTML = '<span class="path-span" id="prevLocation"> ' + pathFirstPart + ' </span><span id="currentLocation">' + pathSecondPart + "</span>";
                document.getElementById("prevLocation").addEventListener("click", function() {goToRelevantLevel(d.parent, false, currentNodePath, currentPathNodes);}, false);
            }
        } else {
           subTitleDiv.innerHTML = " <span id='currentLocation'>" + path + "</span>";
        }
    }

    // Sets the current path in a specific div and
    // gives the return button the text
    function name(d) {
        var path = d.parent ? name(d.parent) + " / " + d.fileName : d.fileName;
        return path;
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
        // Setting up some values about number format(rounding) and marigns
        opts = $.extend(true, {}, defaults, o);
        formatNumber = d3.format(opts.format);
        rname = opts.rootname;
        margin = opts.margin;
        theight = 36 + 16;

        // size of the chart
        $('#chart-and-code').width(opts.width + 70).height(opts.height - 30);
        $('#chart').width(opts.width).height(0);
        $('#code-div').width(opts.width).height(opts.height - 30);
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
            .attr("height", height + margin.bottom + margin.top - 30)
            .style("margin-left", -margin.left + "px")
            .style("margin.right", -margin.right + "px")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
            .style("shape-rendering", "crispEdges");

        // grandparent = svg.append("g")
        //     .attr("class", "grandparent")
        //     .attr("id", "grandparent");

        // grandparent.append("rect")
        //     .attr("y", -margin.top)
        //     .attr("width", width)
        //     .attr("height", margin.top);

        // grandparent.append("text")
        //     .attr("x", 6)
        //     .attr("y", 6 - margin.top)
        //     .attr("dy", ".75em")

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
