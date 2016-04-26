/*
Here we will show how the JSON structure should look like.
If you want to go one level deeper, then you need to use "values" with an s on the end.
Otherwise you can use the normal value parameter.
*/

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