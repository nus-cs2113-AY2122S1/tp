# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Model component
The `model` package consists of three components: `Lesson`, `Task` and `Module`.
#### Lesson component
**API** : [`Lesson.java`](https://github.com/AY2122S1-CS2113T-W11-3/tp/blob/master/src/main/java/seedu/duke/model/lesson/Lesson.java)
![LessonComponentObject](images/LessonComponentObject.png)

The `Lesson` component,
* stores data specific to a lesson as entered by the user 
* stores all `Lesson` objects created by user commands in a `LessonList` object
* does not depend on any of the other three components

### Module component
**API** [`Module.java`](https://github.com/AY2122S1-CS2113T-W11-3/tp/blob/master/src/main/java/seedu/duke/model/module/Module.java)
![ModuleComponentObject](images/ModuleComponentObject.png)

The `Module` component,
* stores information regarding modules added by the user and on the NUSMods API
* does not depend on any of the other three components
* `FullModuleList` stores all `Module` objects corresponding to the modules found on NUSMods
* `ModuleList` stores all user-added `Module` objects

### Task Component
**API** [`Task.java`](https://github.com/AY2122S1-CS2113T-W11-3/tp/blob/master/src/main/java/seedu/duke/model/task/Task.java)
![TaskComponentObject](images/TaskComponentObject.png)

The `Task` component,
* stores data specific to a task as entered by the user
* stores all `Task` objects as createed by user commands in a `TaskList` object
* does not depend on any of the other three components

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2113T-W11-3/tp/blob/master/src/main/java/seedu/duke/storage/Storage.java)  

The storage component makes use of the serializing methods in the `taskList`, `lessonList`, and `moduleList` classes to save data to the `data` folder.
It also reads data from a given path into strings which can be utilized by those 3 list classes to create a list.

The `Storage` component has methods that:
* create the files `task.txt`, `lesson.txt`, `module.txt` in the `data` folder.
* load data from the file in the given path into an array of strings representing each line of data.
* save data of a given list by overwriting the respective file in the `data` folder with serialized data.

The following sequence diagrams below show how task data is [saved](#saving-data) and [loaded](#loading-data) using the Storage class.

#### Saving data:
![StorageSaveSequence](images/StorageSaveSequence.png)

#### Loading data:
![StorageLoadSequence](images/StorageLoadSequence.png)

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
