/*
 * Keep track of which level you are
 */
var packagesLevel = true;

/*
 * Returns the length of a link
 */
function linkDistance(d) {
	if(packagesLevel) {
		return 25 * d.value;
	} else {
		return 25 * d.value;
	}
}
/*
 * Returns the width of a stroke
 */
function linkStrokeWidth(d) {
    if(packagesLevel) {
		return 2;
	} else {
		return 2;
	}
}

/*
 * Returns the radius of a node
 */
function nodeRadius(d) {
	if(packagesLevel) {
		return Math.sqrt(d.classes) * 4;
	} else {
		return Math.sqrt(d.loc) * 1.25;
	}
}
/*
 * Returns the colour of a node
 * Uses a range of 100 values between green and red
 * The closer the value is to 0, the more green it will use
 * The closer the value is to 100, the more red it will use
 */
function nodeColour(d) {
	if(packagesLevel) {
		var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
  		var ratio = 200 * (getWarningTools(d)) / d.loc;
  		return (ratio > 100) ? color(100) : color(ratio);
	} else {
		var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
  		var ratio = 200 * (getWarningTools(d)) / d.loc;
  		return (ratio > 100) ? color(100) : color(ratio);
	}
}
/*
 * Returns what should happen on a double click
 */
function nodeDoubleClick(d, i) {
	if(packagesLevel) { 
		console.log(d.package_Name);
  		sessionStorage.setItem('packageName', d.name);
  		sessionStorage.setItem('packageVariable', d.var);
  		packagesLevel = false
  		removeChart();
		runGraph(window[d.var]); 
  		//window.location.href = "graphClassesView.html";
	} else {
		//TODO: Open right class with source code editor
		window.open("http://9gag.com/","_self")
		// window.location.href = d.path;
	}
}

/*
 * Set the title of the graph
 */
function setTitle(){
	var element = document.getElementById("main-title");
	if(packagesLevel) {
    	element.innerHTML = "Graph view of all packages";
	} else {
		console.log(sessionStorage.getItem('packageName'));
	    element.innerHTML = "Graph view of package '" + sessionStorage.getItem('packageName') + "'";
	    //element = document.getElementById('sub-title');
	    //element.innerHTML = element.innerHTML + '<button class="back-button" id="back-button" onclick="goBack()">Go back to package view</button>';
	}
}

/*
 * Go one view back if possible
 */
function goBack() {
    removeChart();
	packagesLevel = true;
	var element = document.getElementById("back-button");
	element.parentNode.removeChild(element);
	runGraph(graphJSON);
 }

/*
 * Returns only the warnings of the selected tools
 */
function getWarningTools(d) {
	var totalWarnings = 0;
	if(packagesLevel) {
		if($.inArray("CheckStyle", acceptedTypes) > -1 ) {
			totalWarnings += d.warnings.CheckStyle;
		}
		if($.inArray("PMD", acceptedTypes) > -1 ) {
			totalWarnings += d.warnings.PMD;
		}
		if($.inArray("FindBugs", acceptedTypes) > -1 ) {
			totalWarnings += d.warnings.FindBugs;
		}
	} else {
		for(var i = 0; i < d.warningList.length; i++) {
			if($.inArray(d.warningList[i].type, acceptedTypes) > -1 ) {
				totalWarnings += 1;
			}
		}
	}
	return totalWarnings;
}

/*
 * Main function for drawing the graph with its components
 */
function runGraph(graph) {

	setTitle();

	/*
	 * Defines the height and width of the graph
	 */
	var width = window.innerWidth - 225,
		height = window.innerHeight - 175
	
	
	var force = d3.layout.force()
		.charge(-120)
		.linkDistance(linkDistance)
		.size([width, height]);
	
	var svg = d3.select("#chart").append("svg")
		.attr("width", width)
		.attr("height", height);
	

	force
	  .nodes(graph.nodes)
	  .links(graph.links)
	  .start();
	
	 /*
	  * Creates link elements with
	  * Specific length and stroke width based on the corresponding functions
	  */   
	var link = svg.selectAll(".link")
	  .data(graph.links)
	  .enter().append("line")
	  .attr("class", "link")
	  .style("stroke-width", linkStrokeWidth);

	/*
	 * Creates a tooltip that will be shown on hover over a node
	 */
	var tooltip = d3.select("#chart")
	.append("div")
	.style("position", "absolute")
	.style("z-index", "10")
	.style("visibility", "hidden");

	if(packagesLevel) {
	 /*
	  * Creates node elements with
	  * Specific radius, colours and double click actions based on the corresponding functions
	  * On hover over a node it will show statistics of the package (name, classes, loc and warnings)
	  */
	var node = svg.selectAll(".node")
	  .data(graph.nodes)
	  .enter().append("circle")
	  .attr("class", "node")
	  .attr("r", nodeRadius)
	  .style("fill", nodeColour)
	  .on('dblclick', nodeDoubleClick)
	  .on("mouseover", function(d) { 
	  								var totalWarnings = getWarningTools(d);
	  								tooltip.text(d.name + ": " + + d.classes + " classes || " + d.loc + " lines" + " || " + totalWarnings + " warnings");
	  								return tooltip.style("visibility", "visible");
	  							   }
	  	)
	  .on("mousemove", function(d) {
	   								return tooltip.style("top",(d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");
	   							   }
	   	)
	  .on("mouseout", function(d) {
	    							return tooltip.style("visibility", "hidden");
	    						  }
	    )
	  .call(force.drag);
	} else {
	  /*
	   * Creates node elements with
	   * Specific radius, colours and double click actions based on the corresponding functions
	   * On hover over a node it will show statistics of the class (name, loc and warnings)
	   */
	  var node = svg.selectAll(".node")
	      .data(graph.nodes)
	      .enter().append("circle")
	      .attr("class", "node")
	      .attr("r", nodeRadius)
	      .style("fill", nodeColour)
	      .on('dblclick', nodeDoubleClick)
	      .on("mouseover", function(d) { 
	      								var totalWarnings = getWarningTools(d)
	      								tooltip.text(d.fileName + ": " + d.loc + " lines" + " || " + totalWarnings + " warnings"); 
	                                    return tooltip.style("visibility", "visible");
	                                   }
	        )
		    .on("mousemove", function(d){return tooltip.style("top",(d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");})
		    .on("mouseout", function(d){return tooltip.style("visibility", "hidden");})
	      .call(force.drag);
	}
	 
	force.on("tick", function() {
	link.attr("x1", function(d) { return d.source.x; })
	    .attr("y1", function(d) { return d.source.y; })
	    .attr("x2", function(d) { return d.target.x; })
	    .attr("y2", function(d) { return d.target.y; });

	node.attr("cx", function(d) { return d.x; })
	    .attr("cy", function(d) { return d.y; });
	});
};