## PPP

### v1.0

#### Week 8

* Added in the WorkoutList and Workout of the data component
* Added JUnit tests for WorkoutList and Workout classes.

#### Week 9
* Added assert statements into WorkoutList and Workout.
* Added LoggerUtil class in logger package and setup WorkoutList and Workout to use it.

__Difficulties faced:__ Setting up a logger ended up being much harder than expected as it is recommended to have a different logger for each class.
At first, I made a separate setupLogger() function for each class, but that would have caused a redundant method in each class.
Furthermore, this also caused multiple log files to be created as a new FileHandler was created each time a new logger was set up in each class.
To solve this, I created a LoggerUtil class which could assist in the setup of the logger so that only a single common FileHandler is attached to each logger.
This also ensured that all loggers have the same level.

### v2.0

#### Week 10
* Added enter workout function where users could enter into a workout and not have to enter any workout index for any of the commands (something like `cd` in unix)
* Added a UML diagram of the data component, and an explanation of the structure into the DG.

__Difficulties faced:__ Implementing the EnterWorkout function was tricky as this was a functionality that we had come up with after setting up v1.0 and there were very few ways to implement this functionality without majorly changing all the previously written code.
I ended up adding a static variable called workoutMode in the Command class so that special commands could edit this workoutMode.
The parser class was edited so that incase the user was inside a workout then it would use this workoutMode instead of the workoutIndex.

Plant UML was used for the UML class diagram which was also initially challenging to learn and get used to its syntax.
Since many of the default settings of plant UML were not optimal for class diagrams of the type we learnt in the lecture (this was before prof released tips for using plant UML), it took quite abit of research to figure out a way to  change those settings (such as changing visibility to "+"/"-" and removing the circles).

#### Week 11
* Added basic architecture diagram and sequence diagrams for data component and basic architecture to DG.
* Added information on entering workout into the UG.
* Integrated enter workout function to work with other functionalities added in week 10/11 such as edit and clear.
* Changed the way enter workout works so that when a workoutIndex is entered, the workoutMode gets ignored (Previously the opposite was occuring and workoutIndex was getting ignored when inside a workout).
* Enhanced Recommend workout function so that the workouts recommended are automatically stored into memory.

__Difficulties faced:__ Plant UML syntax for sequence diagram is completely different from the class diagram so learning how to make sequence diagrams was slightly challenging.

Changing the way how enter command worked also required a lot of rewriting of methods.

### v2.1

#### Week 12

* Made this PPP document.
* Added Logger guide into DG and modified DG according to suggestions received during DG review.