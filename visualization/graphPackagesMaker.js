/*
* Defines the height and width of the graph
*/
var width = window.innerWidth - 30,
    height = window.innerHeight - 100

/*
* Uses a range of 100 values between green and red
* The closer the value is to 0, the more green it will use
* The closer the value is to 100, the more red it will use
*/
var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);

var force = d3.layout.force()
    .charge(-120)
    .linkDistance(linkDistance)
    .size([width, height]);

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

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

function main(graph) {
  force
      .nodes(graph.nodes)
      .links(graph.links)
      .start();

  var link = svg.selectAll(".link")
      .data(graph.links)
      .enter().append("line")
      .attr("class", "link")
      .style("stroke-width", strokeWidth);

  var tooltip = d3.select("body")
    .append("div")
    .style("position", "absolute")
    .style("z-index", "10")
    .style("visibility", "hidden");

  var node = svg.selectAll(".node")
      .data(graph.nodes)
      .enter().append("circle")
      .attr("class", "node")
      .attr("r", nodeRadius)
      .style("fill", function(d) {  var ratio = 200 * d.warnings / d.loc;
                                    // if statement for when there are more warnings then lines
                                    return (ratio > 100) ? color(100) : color(ratio);})
      .on('dblclick', function(d, i) { sessionStorage.setItem('relevantPackageJSON', d.name); sessionStorage.setItem('packageNumber', (d.num + 1)); window.location.href = "graphClassesView.html"; })
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

// Calling the main function to show all packages in a graph
main(graphJSON);
