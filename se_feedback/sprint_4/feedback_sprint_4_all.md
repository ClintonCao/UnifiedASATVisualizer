|      |            |
|------|------------|
|Group | BlueTurtle |
|Assignment|Sprint 4|
|Date|22/05/16|
|TA|Bastiaan Reijm|

#Sprint Feedback
Feedback and Grades for Sprint 4.

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
* Make sure to add a point -> hour conversion in the next retrospective
* Good work and good reflection

#Code Evolution Quality Feedback

Total: **8.59**

| Architecture                       | Score |
|------------------------------------|-------|
| Changes                            | 6     |
| Architecture Design Document (ADD) | 5     |

|                     | Score |
|---------------------|-------|
| Code Change Quality | 8      |

| Code Readability | Score |
|------------------|-------|
| Formatting       | 10     |
| Naming           | 8     |
| Comments         | 10     |

| Continuous Integration | Score |
|------------------------|-------|
| Building               | 10    |
| Testing                | 8    |

|         | Score |
|---------|-------|
| Tooling | 10    |

| Pull-based Development | Score |
|------------------------|-------|
| Branching              |  10   |
| Code Review            |  10   |

##Notes
* Settings are now Singletons
	* Update the ADD!
	* How are `readSettings` and `writeSettings` called since they are private void methods
* `WarningGrouper.Constructor`
	* Is it a style choice to use single use variables?
* `CommandBuilder.buildCommand`
	* Better than last version
	* Still consider just using an array from the start since the arraylist is only accessible inside the method 
* Java General
	* Good use of code injection to minimise getter and setter code
	* Overriding `equals` means you should also override `hashCode`, otherwise this can lead to unexpected behaviour in maps and other collections
		* Maybe look into using the library Guava for this
* Visualisation
	* `graphBuilder` 
		* contains dead code
		* `nodeDoubleClick` should be split into 2 methods
	* `treeMapBuilder`
		* What's the difference between `text` and `text2`?
	* `MainDriver`
		* `handleClickTypeSat` several layers of if and else should be split into separate functions
	* Good job splitting CSS, JS, and HTML
	* Update the ADD with this!
* Good code reviews
* Good job testing!
* Note to get a higher testing grade:
	* provide documentation that this is the highest reasonable amount
	* increase the coverage