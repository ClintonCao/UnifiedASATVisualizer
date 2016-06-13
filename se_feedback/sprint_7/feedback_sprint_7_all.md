|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 7|
|Date|12/06/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 7.

Total: **10**

| User Story | Score |
|------------|-------|
| definition |   10   |
| splitting  |   10   |
| responsibility | 10  |

| Learning from History | Score |
|-----------------------|-------|
| estimation            |  10    |
| prioritisation        |  10    |
| reflection            |  10   |

## Notes
* Good Job!
* Looks like it was a smooth Sprint

#Code Evolution Quality Feedback

Total: **9.41**

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            |   9   |
| Architecture Design Document (ADD) |   8   |

|                     | Score |
|---------------------|-------|
| Code Change Quality |   8    |

| Code Readability | Score |
|------------------|-------|
| Formatting       |  10    |
| Naming           |   9   |
| Comments         |  10    |

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
* Parsers have high cyclomatic complexity (ex. PMDXMLParser)
	* consider splitting into smaller pieces. 
	* Ex:
	 
   ```
   for (condition) {
      if (condition) {
         code -> move to a new function
      }
   }
   ```
	* You've done this in `ProjectInfoFinder.findFiles`
* `JSONFormatter`
	* hardcoded String `"visualization/JSON/outputWarningsJSON.js"`
		* use a config file or an easy to find constant
* Visualisation
	* `mainDriver`
		* `sumXXXX` (3 functions)
			* why are summations part of the MainDriver's responsibilities?
			* consider moving to another class
	* `treeMapBuilder` - good job splitting
	* two folders `css` and `CSS`? 
* Good job taking the feedback into account