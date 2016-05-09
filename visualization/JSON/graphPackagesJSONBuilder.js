/*
 *
 * Dependencies of packages within a Java project
 * These are Javascript variables with JSON in them
 * 
 * Array of nodes: Each node represents a package with some statistics in them
 * Array of links: Each link represents the dependency between two packages (numbers for source and target are indices of 'nodes')
 * Num stands for the index of the package in the array of nodes
 * Var stands for the corresponding variable names defined in graphClassesJSON.js
 *
 */
var graphJSON = {
  "nodes":[
    {"name":"Writers", "num":0, "var":"package1", "classes":6, "loc":290, "warnings": {"CheckStyle":10, "PMD":15, "FindBugs":120}},
    {"name":"Drivers", "num":1,"var":"package2", "classes":2, "loc":65, "warnings": {"CheckStyle":8, "PMD":8, "FindBugs":69}},
    {"name":"GUI", "num":2, "var":"package3", "classes":4, "loc":1895, "warnings": {"CheckStyle": 20, "PMD": 15, "FindBugs": 450}},
    {"name":"Tests", "num":3, "var":"package4", "classes":4, "loc":95, "warnings": {"CheckStyle": 4, "PMD": 15, "FindBugs": 8}}
  ],
  "links":[
    {"source":0, "target":1, "value":3},
    {"source":0, "target":2, "value":8},
    {"source":1, "target":3, "value":16}
  ]
};