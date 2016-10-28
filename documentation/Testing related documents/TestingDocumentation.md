# Testing Documentation.

This document provides explanation for the coverage of our tests and the decisions made for the tests e.g. why did we exclude the GUI components from the coverage.

## Test coverage & explanation for excluding GUI and `Main.java` from coverage (3-June-2016)

#### Explanation for the excludes in JaCoCo report.
The coverage for this week has improved from 76% to 97.8%. We improved the coverage by excluding the following files from the JaCoCo report: `Main.java`, `GUI.java`, `GUIController.java` and `SelectButtonEventHandler.java`. The reason why we excluded classes in the `BlueTurtle.gui` package, is because that it might be hard to test the GUI components. It is possible to use the `TestFX` library to test the GUI, but this can lead to problems on Travis-CI; I (Clinton) have used this library for the SEM (Software Engineering Methods) project. It worked fine on local machine, but on Travis-CI there was some problem trying to find the buttons(on the GUI). We had to skip the tests on Travis-CI build, in order to get a passing build. This is not very good practice and therefore we decided to not test the GUI in this project. Not testing the GUI means that it will influence the test coverage. Therefore we have decided to exclude the GUI components from the JaCoCo report. We have also excluded `Main.java` because this class is used for intergrating all the components together. This class starts the GUI and testing this class on Travis-CI might actually lead the build to stall.

#### Test coverage of the system
Almost all packages have 100% coverage. The only package that does not have 100% is `BlueTurtle.summarizers`. The reason for this is because the default case `incrementNumberOfWarnings` is not covered. It is not covered because that case can never be reached. We are using `enums` in order to increment the number of warnings. If `incrementNumberOfWarnings`called with a non-existing `enum`, then the `enum` itself will throw an `IllegalArgumentException`. Therefore the default case can never be reached.

## Test coverage (10-June-2016)

#### Test coverage of the system
The coverage this week has dropped by 0.005%. This is because some extra lines are added in the `handle` method of `VisualizeButtonEventHandler`. This class is not excluded in the report of JaCoCo and therefore the coverage dropped by a little bit.

## Test coverage (15-June-2016)

#### Test coverage of the system
The coverage this week has dropped from 97.72% to 97.55%. This is because we removed a class that is not used. The number lines of code that is not tested is therefore relatively bigger.


 
