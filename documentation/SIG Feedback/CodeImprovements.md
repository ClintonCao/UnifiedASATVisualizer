# Improving the code based on the feedback received from SIG's tool.
This document provides information on what the team did to improve the code. The improvements are done based on the feedback that the team has received from SIG's tool.

## Recap
On the first upload (of our code) to SIG, we passed 7 out of the 10 guidelines. Please click [here](https://github.com/ClintonCao/Contextproject-TSE/blob/master/documentation/SIG%20Feedback/SIGFeedback.JPG) for an image of the feedback that was given. The three guidelines that we have failed to pass was the following: 
  * "Write Short Units of Code"
  * "Write Code Once"
  * "Automate Tests"

	
## What we have done to improve the code

#### Improving the code for "Write Short Units of Code": 
The reason why we did not pass this guideline is because of the following functions:
 * `createGraph` from `graphBuilder` 
 * `display` from `treeMapBuilder`
 * `setTheVariables` from `treeMapBuilder`
 * `initialize` from `GUIController`

These functions had more than 60 lines of code. The severity of the violation for these functions was the highest. We first removed the `graphBuilder` from our repository. This is because the client didn't really see the use of the graph. They wanted to keep it to one visualization, the treemap. We then refactored the `initialize` method from `GUIController`. We removed the anonymous inner classes and created classes for the EventHandlers (please see [#67](https://github.com/ClintonCao/Contextproject-TSE/pull/67) for the solution). For `display` and `setTheVariables` we have splitted the function into smaller functions. It was quite hard to make the `display` function into a smaller function, because this is used to create the whole treemap. Splitting it further might cause the treemap to not work anymore (please see [#89](https://github.com/ClintonCao/Contextproject-TSE/pull/89), [#93](https://github.com/ClintonCao/Contextproject-TSE/pull/93) and [#94](https://github.com/ClintonCao/Contextproject-TSE/pull/94) for more information on the refactoring that we have done for the visualizer) .   

#### Improving the code for "Write Code Once":
The reason why we did not pass this guideline is because our output files of the analyser are JavaScript files, and there was some code duplication in the parsers. The code duplication in the parsers was fixed by adding a function in the super class (please see  [#67](https://github.com/ClintonCao/Contextproject-TSE/pull/67) for the solution). SIG's tool thinks that the output files are also code of the system, which they are not. Different (duplicated) output files were in the repository for testing purposes: Instead of running our tool every time to get output files for the visualizer, we have made some example files. Team members that are working on the visualizer can use them to develop the visualizer. The duplicated output files are now removed from the repository. Curious why the output files of the analyser are JavaScript files? Take a look at our [Architecture Design](https://github.com/ClintonCao/Contextproject-TSE/blob/master/documentation/Product%20related%20documents/ArchitectureDesign(BlueTurtle).pdf) document, and specifically section 2.2 for more information.

#### Improving the code for "Automate tests":
The reason why we did pass this guideline is because our tests coverage was only 72% (for the analyser). We have developed more tests to improve the coverage. The coverage is now at 97.68%. For more information about testing, please take a look at [TestingDocumentation.md](https://github.com/ClintonCao/Contextproject-TSE/blob/master/documentation/Testing%20related%20documents/TestingDocumentation.md). 

## Second upload and results
After all the refactoring has been done for both the analyser and the visualizer, we ran SIG's tool again and we got the following results:
We passed 9 out of the 10 guidelines. The previous 3 guidelines that we did not pass in the first upload are fixed. The guideline that we have failed to pass in the second upload is "Keep Unit Interfaces Small". The reason is because of the following classes: 
 * `FindBugsWarning`
 * `PMDWarning`

The constructor of both classes had more than 7 parameters. We decided to not fix this problem, because each parameter contains information that is needed in the visualizer. One might say that we could have grouped some of the parameters together into an object. This is true and we have discussed about this, but we couldn't figure out how we can group the parameters logically. We can simply create classes that groups two or more parameters together, but this means that we will be creating classes where their attributes does not have a relation with each other (the class does not really represent one entity). Therefore we have decided to not fix this problem.
