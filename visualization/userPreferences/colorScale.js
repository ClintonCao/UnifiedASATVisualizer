var colorScale = (function() {

	// default color settings
	var color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);

    return {

        getColor: function() {
            return color;
        },
		getColor: function(ratio) {
            return (ratio > 100) ? color(100) : color(ratio);
        },
		colorsRelative: function() {
			color = d3.scale.linear().domain([0, 50]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
		},
		colorsAbsolute: function() {
			color = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range([d3.rgb("#00C800"), d3.rgb('#C80000')]);
		}

    };

}());
        