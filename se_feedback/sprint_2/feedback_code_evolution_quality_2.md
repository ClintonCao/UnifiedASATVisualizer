|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Code Evolution Quality 2|
|Date|10/05/16|
|TA|Bastiaan Reijm|

#Feedback

Total: 7.67

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            | 7     |
| Architecture Design Document (ADD) | 7     |

|                     | Score |
|---------------------|-------|
| Code Change Quality | 7     |

| Code Readability | Score |
|------------------|-------|
| Formatting       | 8     |
| Naming           | 8     |
| Comments         | 8     |

| Continuous Integration | Score |
|------------------------|-------|
| Building               | 8     |
| Testing                | 8     |

|         | Score |
|---------|-------|
| Tooling | 8     |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              | 8     |
| Code Review            | 8     |

##Notes
* Good start on the ADD, missing some details related to things such as groupers and the App class.
* Add a release on GitHub when you turn in the code at the end of a Sprint
* Make sure the output of your tools is also available on GitHub, Travis can be configured to write the output of these tools to your repo
* `TSE/App.java`
	* The switch statement is code duplication, especially since all the parsers subclass XMLParser
* `writers/JsonWriter.java`
	* Seems like a good place for a singleton
* `grouper/WarningGrouper.java`
	* You could make each instance immutable so that the results of the `groupby` are computed once and stored
* Don't switch on Strings, use enumerations