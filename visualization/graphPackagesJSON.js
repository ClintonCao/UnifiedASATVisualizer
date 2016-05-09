/*
*
* Dependencies of packages within a Java project
* These are Javascript variables with JSON in them
* 
* Array of nodes: Each node represents a package with some statistics in them
* Array of links: Each link represents the dependency between two packages (numbers for source and target are indices of 'nodes')
*
*/
var graphJSON = {
  "nodes":[
    {"name":"package1", "classes":6, "loc":290, "warnings":37},
    {"name":"package2", "classes":2, "loc":65, "warnings":22},
    {"name":"package3", "classes":4, "loc":1895, "warnings":80},
    {"name":"package4", "classes":4, "loc":95, "warnings":27}
  ],
  "links":[
    {"source":0, "target":1, "value":3},
    {"source":0, "target":2, "value":8},
    {"source":1, "target":3, "value":16}
  ]
};