# Research on how to execute Maven through Java.
This document provides information on the research that we have done on how to execute _Maven_ through Java.

### Why was the reseach needed?
It was pointed by our TA (in pull request [#79](https://github.com/ClintonCao/Contextproject-TSE/pull/79)), that it might be convenient for our system to execute `mvn site` if the output files of the ASATs are not already generated; maybe because the user forgot to run `mvn site` before clicking on the _Visualize_ button. Therefore we needed to do some research on how to execute Maven through Java.

### Ways that we have found on how to execute Maven through Java.
We have found three ways on how to execute maven through Java:
  1. Execute Maven commands using `ProcessBuilder`.
  2. Execute Maven commands using [Maven Embedder](http://maven.apache.org/ref/3.3.1/maven-embedder/).
  3. Execute Maven commands using [Maven-Invoker](http://maven.apache.org/shared/maven-invoker/).

### Experimentation & results.
We've experimented with each of the methods mentioned above, but none of them seem to provide a solution. We will give an explanation for each of the methods(numbered the same way as above):
  1. Using the `ProcessBuilder` to run the maven commands is not extremely hard. The problem is that we have to run the commands where Maven (the standalone app and not a plugin in the IDE) is installed. This means that we have to search on the user's system for the location where Maven is installed, or the user has to provide us the location where Maven is installed, but we do not know if the user have the standalone app installed or he/she is using a plugin in the IDE. Therefore this method does not provide a solution.
  2. This was the closest one that we've gotten to execute the Maven commands through Java. It was possible for us to use an older version (3.1.1) of the Maven Embedder in order to execute Maven commands. With this method we did not need the location where Maven is installed. The only problem is that this method does not work on everyone's machine(with this we mean the machine of the team members). Actually it only works on Clinton's laptop (which we think is very strange). The problem that occurred the most if the following: There was no compiler (java JDK) provided for Maven. This method is therefore also not a solution.
  3. This method have the same problem as 1 and therefore this method is also not a solution.

### Conclusion.
Because of the complications that are mentioned above(in _Experimentation & results_), we have therefore decided to not execute Maven if the output files of the ASATs are not already generated. It is simpler to tell the user to execute `mvn site` and then use our system.
