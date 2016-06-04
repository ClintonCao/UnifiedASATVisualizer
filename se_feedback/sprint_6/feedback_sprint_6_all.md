|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 6|
|Date|04/06/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 6.

Total: **9.5**

| User Story | Score |
|------------|-------|
| definition |  10    |
| splitting  |  10    |
| responsibility |  10 |

| Learning from History | Score |
|-----------------------|-------|
| estimation            |   10   |
| prioritisation        |   10   |
| reflection            |   8  |

## Notes
* Michiel has significantly less hours than the rest but no reason why.
* Good to shortly recap the client feedback in the problem section
* Thanks for adding the notes 

#Code Evolution Quality Feedback

Total: **9.31**

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            |   8   |
| Architecture Design Document (ADD) |   8   |

|                     | Score |
|---------------------|-------|
| Code Change Quality |   8    |

| Code Readability | Score |
|------------------|-------|
| Formatting       |   10   |
| Naming           |   9   |
| Comments         |   10   |

| Continuous Integration | Score |
|------------------------|-------|
| Building               |  10   |
| Testing                |  10   |

|         | Score |
|---------|-------|
| Tooling |  10   |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              |  10   |
| Code Review            |  10   |

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