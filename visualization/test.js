
/*
Here we will show how the JSON structure should look like.
If you want to go one level deeper, then you need to use "values" with an s on the end.
Otherwise you can use the normal value parameter.
*/
var inputdata = [
  {
    "key": "Character",
    "values": [
    {
      "key": "Boat",
      "value": 3,
	  "bugs" : 5,
	  "lines": 20,
	  "methods": 5
    },
    {
      "key": "Cast",
      "value": 2,
	  "bugs" : 5,
	  "lines": 14,
	  "methods": 5
    },
    {
      "key": "Character",
      "value": 2,
	  "bugs" : 3,
	  "lines": 3,
	  "methods": 9
    },
    {
      "key": "EnemyBoat",
      "value": 2,
	  "bugs" : 4,
	  "lines": 40,
	  "methods": 8
    },
    {
      "key": "Goal",
      "value": 2,
	  "bugs" : 3,
	  "lines": 40,
	  "methods": 6
    },
    {
      "key": "Cast",
      "value": 2,
	  "bugs" : 8,
	  "lines": 40,
	  "methods": 12
    },
    {
      "key": "Character",
      "value": 2,
	  "bugs" : 3,
	  "lines": 40,
	  "methods": 5
    },
    {
      "key": "EnemyBoat",
      "value": 2,
	  "bugs" : 9,
	  "lines": 40,
	  "methods": 14
    },
    {
      "key": "Goal",
      "value": 2,
	  "bugs" : 0,
	  "lines": 30,
	  "methods": 15
    },
    {
      "key": "Cast",
      "value": 2,
	  "bugs" : 1,
	  "lines": 40,
	  "methods": 12
    },
    {
      "key": "Character",
      "value": 2,
	  "bugs" : 10,
	  "lines": 40,
	  "methods": 8
    },
    {
      "key": "EnemyBoat",
      "value": 2,
	  "bugs" : 2,
	  "lines": 40,
	  "methods": 1
    },
    {
      "key": "Goal",
      "value": 2,
	  "bugs" : 3,
	  "lines": 40,
	  "methods": 5
    }
	]
  },
  {
    "key": "Screens",
    "values": [
    {
      "key": "Boat",
      "value": 2,
	  "bugs" : 10,
	  "lines": 250,
	  "methods": 2
    },
    {
      "key": "Night",
      "value": 2,
	  "bugs" : 6,
	  "lines": 40,
	  "methods": 15
    }
	]
  },
  {
    "key": "Movement",
    "values": [
    {
      "key": "Movement",
      "value": 2,
	  "bugs" : 3,
	  "lines": 30,
	  "methods": 7
    },
    {
      "key": "Random",
      "value": 2,
	  "bugs" : 3,
	  "lines": 40,
	  "methods": 3
    },
    {
      "key": "Location",
      "value": 2,
	  "bugs" : 3,
	  "lines": 40,
	  "methods": 1
    }
	]
  }
];

/*
var inputdata = [
  {
    "key": "One-deep",
    "value": 1
  },

  {
    "key": "Two-deep",
    "values": [
      {
        "key": "Second-level-one",
        "value": 0.5
      },
      {
        "key": "Second-level-two",
        "value": 0.5
      }]
  },

  {
    "key": "Three-deep",
    "values": [
      {
        "key": "Second-level",
        "values": [
          {
            "key": "Third-level-one",
            "value": 0.5
          },
          {
            "key": "Third-level-two",
            "value": 0.5
          }]
      }]
  },

  {
    "key": "Package 1",
    "values": [
      {
        "key": "Class 1",
        "values": [
          {
            "key": "Method 1",
            "values": [
              {
                "key": "Fourth-level-one",
                "value": 0.2
              },
              {
                "key": "Fourth-level-two",
                "value": 0.2
              }]
          },
          {
            "key": "Method 2",
            "values": [
              {
                "key": "Fourth-level-one",
                "value": 0.3
              },
              {
                "key": "Fourth-level-two",
                "value": 0.2
              }]
          }]
      },
      {
        "key": "Class 2",
        "values": [
          {
            "key": "Method 1",
            "values": [
              {
                "key": "Fourth-level-one",
                "value": 0.1
              },
              {
                "key": "Fourth-level-two",
                "value": 0
              }]
          },
          {
            "key": "Method 2",
            "values": [
              {
                "key": "Fourth-level-one",
                "value": 0.1
              },
              {
                "key": "Fourth-level-two",
                "value": 0.25
              }]
          }]
      }]
  }

];
*/