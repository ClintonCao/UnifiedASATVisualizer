
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
      "value": 90,
	  "warnings" : 5,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 5
    },
    {
      "key": "Cast",
      "value": 65,
	  "warnings" : 55,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 5
    },
    {
      "key": "Character",
      "value": 30,
	  "warnings" : 3,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 9
    },
    {
      "key": "Cast",
      "value": 20,
	  "warnings" : 8,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 12
    },
    {
      "key": "Goal",
      "value": 20,
	  "warnings" : 0,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 15
    },
    {
      "key": "EnemyBoat",
      "value": 20,
	  "warnings" : 2,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 1
    },
    {
      "key": "Goal",
      "value": 20,
	  "warnings" : 3,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 5
    }
	]
  },
  {
    "key": "Screens",
    "values": [
    {
      "key": "Boat",
      "value": 75,
	  "warnings" : 10,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 2
    },
    {
      "key": "Night",
      "value": 100,
	  "warnings" : 6,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 15
    }
	]
  },
  {
    "key": "Movement",
    "values": [
    {
      "key": "Movement",
      "value": 200,
	  "warnings" : 3,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 7
    },
    {
      "key": "Random",
      "value": 20,
	  "warnings" : 3,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
	  "methods": 3
    },
    {
      "key": "Location",
      "value": 15,
	  "warnings" : 6,
	  "path" : "D:\Users\Tim\git\ContextProject\Contextproject-TSE\src\main\java\BlueTurtle\TSE\App.java",
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