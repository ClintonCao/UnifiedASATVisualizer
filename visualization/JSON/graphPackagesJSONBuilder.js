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
    {"name":"BlueTurtle.warnings", "num":0, "var":"package1", "classes":7, "loc":290, "warnings": {"CheckStyle":15, "PMD":0, "FindBugs":0}},
    {"name":"BlueTurtle.errors", "num":1,"var":"package2", "classes":2, "loc":65, "warnings": {"CheckStyle":11, "PMD":0, "FindBugs":0}}
  ],
  "links":[
    {"source":0, "target":1, "value":3}
  ]
};