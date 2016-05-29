|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 5|
|Date|29/05/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 5.

Total: **9.1**

| User Story | Score |
|------------|-------|
| definition |  8    |
| splitting  |  10    |
| responsibility | 10  |

| Learning from History | Score |
|-----------------------|-------|
| estimation            |  10    |
| prioritisation        |  10    |
| reflection            |  8   |

## Notes
* Unclear user stories
	* GitHub issues - would be better to briefly state what the issue is about, thanks for adding the numbers
	* The Project should be good tested - what do you deine as good?
* Refactoring and improving coding style are different
	* Add an explanation of what high level changes were made
	* Here you can add that as part of refactoring you also improved coding style (what did you improve on)

#Code Evolution Quality Feedback

Total: **8.95**

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            |  7    |
| Architecture Design Document (ADD) |  5    |

|                     | Score |
|---------------------|-------|
| Code Change Quality |   9    |

| Code Readability | Score |
|------------------|-------|
| Formatting       |  10    |
| Naming           |  10    |
| Comments         |  10    |

| Continuous Integration | Score |
|------------------------|-------|
| Building               |  10   |
| Testing                |  8   |

|         | Score |
|---------|-------|
| Tooling | 10    |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              |  10   |
| Code Review            |  10   |

##Notes
* ADD
	* Good explanations of the design goals
	* Consider adding sequence diagrams / flow diagrams of how the system works
		* User starts the program and what happens in what order
	* 2.2 - Persistant Data Management
		* How is the data that your tool produces being stored?
* `LOCComputer` + `PackageNameFinder`
	* Good names
	* contains a default constructor
		* Classes have one static method so should be singletons but are not
* Parsers
	* consider storing the mapping of errors to categories in a singleton instead of passing it to each method call or pass to super constructor and store as instance attribute
* `Summarizer`
	* making equals and hashcode explicit in an abstract class is overkill since the `Object` class already contains these methods and every class is an `Object`
* Good job on removing setters and implementing hashCode functions
