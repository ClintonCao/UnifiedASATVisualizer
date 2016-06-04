|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 6|
|Date|04/06/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 6.

Total: ****

| User Story | Score |
|------------|-------|
| definition |      |
| splitting  |      |
| responsibility |   |

| Learning from History | Score |
|-----------------------|-------|
| estimation            |      |
| prioritisation        |      |
| reflection            |     |

## Notes
* Michiel has significantly less hours than the rest but no reason why.
* Good to shortly recap the client feedback in the problem section
* Thanks for adding the notes 

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
	* The diagrams makes your explanation a lot clearer.
	* You should explain how you run the tools in the Concurrency section 
* Good job on the test coverage document!
* Java
	* `JavaController.setASATOutput`
		* Consider using a properties file (or JSON) to store ASAT Output locations
	* `Warning` 
		* making equals and hashcode explicit in an abstract class is overkill since the `Object` class already contains these methods and every class is an `Object`
* Visualisation
	* `mainDriver`
		* `appendXXXX` (3 functions)
			* statements can be combined into function calls with parameters
	* `treeMapBuilder`
		* `updateWarningsCountInUI`
			* large amount of statements consider splitting into smaller functions
		* 599 lines of code, that's a lot - consider breaking into smaller pieces
	* two folders `css` and `CSS`?
* Good job taking feedback into account
* Quoting Elon Musk and Charles Darwin in your code reviews, classy