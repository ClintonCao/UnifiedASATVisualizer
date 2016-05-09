
	
	
/*
 * Returns the length of a link
 */
function linkDistance(d) {
    return 10 * d.value;
}

/*
 * Returns the width of a stroke
 */
function strokeWidth(d) {
    return 2;
  //return Math.sqrt(d.value);
}
/*
 * Returns the radius of a node
 */
function nodeRadius(d) {
  return Math.sqrt(d.classes) * 4;
}
/*
 * Returns the colour of a node
 */
function nodeColour(d) {
	/*
	 * Uses a range of 100 values between green and red
	 * The closer the value is to 0, the more green it will use
	 * The closer the value is to 100, the more red it will use
	 */
  var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
  var ratio = 200 * d.warnings / d.loc;
  return (ratio > 100) ? color(100) : color(ratio);
}
/*
 * Returns what should happen on a double click
 */
function nodeDoubleClick(d, i) {
  sessionStorage.setItem('relevantPackageJSON', d.name); 
  sessionStorage.setItem('packageNumber', (d.num + 1)); 
  window.location.href = "graphClassesView.html";
}

/*
 * set Title chart
 */
function setTitle(){
	//d3.select("body").append("svg")
}

/*
 * Main function for drawing the graph with its components
 */
function runGraph(graph) {
	/*
	 * Defines the height and width of the graph
	 */
	var width = window.innerWidth - 30,
		height = window.innerHeight - 100
	
	
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
	  .style("stroke-width", strokeWidth);

	/*
	 * Creates a tooltip that will be shown on hover over a node
	 */
	var tooltip = d3.select("#chart")
	.append("div")
	.style("position", "absolute")
	.style("z-index", "10")
	.style("visibility", "hidden");

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
	  .on("mouseover", function(d){tooltip.text(d.name + ": " + + d.classes + " classes || " + d.loc + " lines" + " || " + d.warnings + " warnings"); return tooltip.style("visibility", "visible");})
	    .on("mousemove", function(d){return tooltip.style("top",(d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");})
	    .on("mouseout", function(d){return tooltip.style("visibility", "hidden");})
	  .call(force.drag);

	force.on("tick", function() {
	link.attr("x1", function(d) { return d.source.x; })
	    .attr("y1", function(d) { return d.source.y; })
	    .attr("x2", function(d) { return d.target.x; })
	    .attr("y2", function(d) { return d.target.y; });

	node.attr("cx", function(d) { return d.x; })
	    .attr("cy", function(d) { return d.y; });
	});
};
