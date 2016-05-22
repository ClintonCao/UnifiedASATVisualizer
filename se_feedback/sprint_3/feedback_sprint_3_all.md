|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 3|
|Date|22/05/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 3.

Total: **9.7**

| User Story | Score |
|------------|-------|
| definition |  10    |
| splitting  |  10    |
| responsibility | 10 |

| Learning from History | Score |
|-----------------------|-------|
| estimation            | 8     |
| prioritisation        | 10     |
| reflection            |  10    |

## Notes
* Provide a base unit because how much work does an effort of 10 points mean
	* A base unit is the task you're basing this point system on

#Code Evolution Quality Feedback

Total: **8.19**

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            | 6     |
| Architecture Design Document (ADD) | 5     |

|                     | Score |
|---------------------|-------|
| Code Change Quality | 7      |

| Code Readability | Score |
|------------------|-------|
| Formatting       | 10     |
| Naming           | 8     |
| Comments         | 10     |

| Continuous Integration | Score |
|------------------------|-------|
| Building               | 10    |
| Testing                | 7     |

|         | Score |
|---------|-------|
| Tooling | 10    |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              |  10   |
| Code Review            |  10   |

##Notes
* ADD
	* Last updated 23 days ago, is it still accurate
	* Please put in a proper class diagram
	* Update the decision decisions with respect to the visualisation aspect of this project
	* Good job on the SAT research
		* Good conclusion to work on XML
		* Fix the table of contents
		* Make sure to mention this document in the ADD
	* Explain your package structure
		* Also mention that you bundle interfaces into one package
* `Main`
	* Keep an eye on the switch statement and the enum, if you add support for more languages consider revising this code into a factory or a strategy pattern
* `App`
	* Main method contains a switch on strings
		* Not very modular or robust
* `JavaController`
	* Consider making tool settings into one class
	* Contains quite a bit of dead code
* `CheckStyleCommandBuilder` and the others
	* `buildCommand`
		* First adding to ArrayList and then making an array seems like overkill
		* retCommands commands is a single use variable, maybe just return the value instead
* `LOCFinder`
	* Doesn't search for anything it calculates
	* Name suggests that the LOC is stored somewhere
* `GUIController`
	* Anonymous Inner classes of super type EventHandler add complexity to the code
		* Use if you have no other options
		* Otherwise create classes
* Consider splitting package `interfaces` such that the interfaces are in the same package as the classes that implement them
* `PMDXMLParser` + `GDCParser`
	* `parseFile` is unusually large, split into smaller methods
* General
	* Most classes with setters can be made immutable, please think about changing
		* Easier to test
		* Less error prone because once the state has been set it cannot change
	* Don't use switch statements on Strings
		* Switch on Enum
		* Use a strategy pattern
	* Swallowing errors is not a proper way to handle them
		* Notify the user
	* Split methods with large loops into 2 parts
		* public method with loop
		* private method that works on a single item
	* Use maps when handling collections of attributes of the same type
* Good code reviews
* Nice job with coveralls, now just boost the coverage score