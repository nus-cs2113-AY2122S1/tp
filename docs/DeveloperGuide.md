# Developer Guide

## Acknowledgements

- Libraries used: [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

## Design & implementation

###Data Component
Location : `seedu.duke.data`

![img.png](umldg/DataClassDiagram.png)

__Note:__
* `WorkoutList` : List of all Workout Routines.
* `Workout` : A specific Workout Routine eg. "Leg day" could be a routine created for exercises that focus on legs.
* `Exercise` : A specific exercise eg. "Squats" could be an exercise added to "Leg day." 

The `data` component defines the format in which data is temporarily stored while the application is running.
As seen in the figure above, the highest level of abstraction is the `WorkoutList` which stores all the workout routines
that the user has currently stored. Next, the `Workout` refers to a Workout Routine and stores a variable number of 
exercises (in the form of an `Exercise` object). Lastly, `Exercise` stores the exercise with attributes:
* `description` corresponding to the name of the exercise.
* `reps` corresponding to the number of times the exercise is done in a given set.
* `sets` corresponding to the number of times the whole exercise is repeated.

Storage.models: `addToWorkoutListModel(WorkoutModel workout)` and `addToWorkoutModel(ExerciseModel exercise)` from the `storage.models` component
are called in the methods of `Workout` and `Exercise` respectively. This causes the dependency on `WorkoutModel` and `WorkoutListModel` as seen in the UML Diagram.

###Storage Component
Location: `seedu.duke.storage`

__Note:__
* `Storage` : Deals with loading tasks from the json file and saving tasks in the json file.
* `JsonUtil` : Handles functions required to convert Java Objects to JSON objects and vice versa using the Jackson Library.

The `Storage` component,
- can save workout list data in json format, and read them back into corresponding Java objects.
- depends on some classes in the `Models` component within Storage (because the Storage component can only save/retrieve objects that belong to the Model).

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
