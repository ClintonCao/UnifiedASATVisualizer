|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 5|
|Date|29/05/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 5.

Total: ****

| User Story | Score |
|------------|-------|
| definition |      |
| splitting  |      |
| responsibility |  |

| Learning from History | Score |
|-----------------------|-------|
| estimation            |      |
| prioritisation        |      |
| reflection            |      |

## Notes
* Unclear user stories
	* GitHub issues - would be better to briefly state what the issue is about, thanks for adding the numbers
	* The Project should be good tested - what do you deine as good?
* Refactoring and improving coding style are different
	* Add an explanation of what high level changes were made
	* Here you can add that as part of refactoring you also improved coding style (what did you improve on)

#Code Evolution Quality Feedback

Total: ****

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            |      |
| Architecture Design Document (ADD) |      |

|                     | Score |
|---------------------|-------|
| Code Change Quality |       |

| Code Readability | Score |
|------------------|-------|
| Formatting       |      |
| Naming           |      |
| Comments         |      |

| Continuous Integration | Score |
|------------------------|-------|
| Building               |     |
| Testing                |     |

|         | Score |
|---------|-------|
| Tooling |     |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              |     |
| Code Review            |     |

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
