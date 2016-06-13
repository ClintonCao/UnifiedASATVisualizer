|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 7|
|Date|12/06/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 7.

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
* Good Job!
* Looks like it was a smooth Sprint

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