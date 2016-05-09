/*
 *
 * Dependencies of classes within different packages
 * These are Javascript variables with JSON in them
 * 
 * Array of nodes: Each node represents a class with some statistics in them
 * Array of links: Each link represents the dependency between two classes (numbers for source and target are indices of 'nodes')
 *
 */
var package1 = 
{
  "nodes":[
    {
      "name":"SpeedBoat",
      "package":1, "loc":50, 
      "warnings":20, 
      "path":"index.html"
    },
    {
      "name":"Factory",
      "package":1, 
      "loc":80, 
      "warnings":2, 
      "path":"path to Factory.java"
    },
    {
      "name":"Water",
      "package":1, 
      "loc":20, 
      "warnings":4, 
      "path":"path to Water.java"
    },
    {
      "name":"Boat",
      "package":1, 
      "loc":25, 
      "warnings":1, 
      "path":"path to Boat.java"
    },
    {
      "name":"Speed",
      "package":1, 
      "loc":110, 
      "warnings":0, 
      "path":"path to Speed.java"
    },
    {
      "name":"Random",
      "package":1, 
      "loc":25, 
      "warnings":10, 
      "path":"path to Random.java"
    }
  ],
  "links":[
    {"source":1,"target":0,"value":1},
    {"source":2,"target":0,"value":8},
    {"source":3,"target":0,"value":16},
    {"source":3,"target":2,"value":6},
    {"source":4,"target":5,"value":6},
    {"source":5,"target":1,"value":10}
  ]
};

var package2 = 
{
  "nodes":[
    {
      "name":"KFC",
      "package":2, 
      "loc":50, 
      "warnings":20, 
      "path":"path to KFC.java"
    },
    {
      "name":"Chicken",
      "package":2, 
      "loc":15, 
      "warnings":2, 
      "path":"path to Chicken.java"
    }
  ],
  "links":[
    {"source":1,"target":0,"value":21}
  ]
};

var package3 = 
{
  "nodes":[
    {
      "name":"KFC",
      "package":3, 
      "loc":505, 
      "warnings":20, 
      "path":"path to KFC.java"
    },
    {
      "name":"Chicken",
      "package":3, 
      "loc":800, 
      "warnings":25, 
      "path":"path to Chicken.java"
    },
    {
      "name":"Restaurant",
      "package":3, 
      "loc":500, 
      "warnings":20, 
      "path":"path to Restaurant.java"
    },
    {
      "name":"Animal",
      "package":3, 
      "loc":90, 
      "warnings":15, 
      "path":"path to Animal.java"
    }
  ],
  "links":[
    {"source":0,"target":2,"value":15},
    {"source":0,"target":1,"value":10},
    {"source":1,"target":3,"value":15}
  ]
};

var package4 = 
{
  "nodes":[
    {
      "name":"SpeedBoat",
      "package":4, 
      "loc":50, 
      "warnings":20, 
      "path":"path to SpeedBoat.java"
    },
    {
      "name":"Factory",
      "package":4, 
      "loc":8, 
      "warnings":2, 
      "path":"path to Factory.java"
    },
    {
      "name":"Water",
      "package":4, 
      "loc":12, 
      "warnings":4, 
      "path":"path to Water.java"
    },
    {
      "name":"Boat",
      "package":4, 
      "loc":25, 
      "warnings":1, 
      "path":"path to Boat.java"
    }
  ],
  "links":[
    {"source":1,"target":0,"value":1},
    {"source":2,"target":0,"value":8},
    {"source":3,"target":0,"value":6}
  ]
};