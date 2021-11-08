# Kishor Kumar's Project Portfolio Page

## Project: Get Jack'd

Get Jack'd is a Command-Line application that helps busy people manage their workouts and fitness routines quickly by
typing in commands. It is written in Java and has about 6 kLoC. (6000 lines of code)

Given below are my contributions to the project.

- **New Feature**: Added recommend feature that allows users to view a pre-set workouts based on difficulty level
  provided.
    - **What it does**: Displays a set of pre-defined workouts with multiple exercises from the 3 difficulties [beginner/intermediate/pro].
        It also stores these recommended workouts automatically into the workout list.
    - **Justification**: This feature improves the product as users are now able to know how to get started. They are able to modify these recommended
        workouts and take reference.
    - **Difficulties faced**: Storing the recommended workouts into the Json file posed a problem. This was due to the fact that our initial
        code implementation could only allow storing of an exercise one at a time. However, in this case multiple workouts consisting of multiple exercises
        had to be stored at once. Ultimately, usage of hashmaps proved to be helpful.
    -  **Credits** : With the assistance of QianQi, the `UI` and the `storage` for recommended workouts was settled.


- **New Feature**: Added edit feature that allows users to update a current exercise present in a workout.
    - **What it does**: Edits an exercise of given `exerciseIndex` and `workoutIndex` by assigning the previous exercise parameters
      to the newly provided parameters.
    - **Justification**: This feature improves the product as users are now able to quickly change an existing exercise,
      instead of deleting and re-adding that exercise.
    - **Difficulties faced**: Adding the `edit` function with pre-existing parser methods was slightly challenging. This
      was due to the fact that other command's formats were straight forward and required lesser parameter. However, in
      this case the edit user format required `workoutIndex`, `exerciseIndex`, `newDescription`, `sets and reps`. 
      Thus, extracting and making sense of each parameter proved to be tedious. Moreover, adding Junit tests in `RecommendWorkoutCommandTest` proved to be a
      hassle as although the test written showed the same output, the test method of checking if it is showing the
      correct recommended workout kept failing.


- **New Feature**: Added clear feature that allows users to remove all the existing exercises in a particular workout or
  remove all the existing workouts in the application.
    - **What it does**: Deletes all the exercises present in a user specified workout or deletes all the workouts present 
      in the stored file.
    - **Justification**: This feature improves the product as users can easily start a fresh workout plan with a single
      command and free them from having to delete an exercise or a workout one by one.
    - **Difficulties faced**: Adding the `clear` function initially kept throwing exception. This was because I was
      planning to use a single constructor at the start to make it look neater and easier. I tried sending `null` in
      place of `workoutIndex` when it came to `clear workout` command as it did not require a `workoutIndex`. However, I
      realised that it is not possible to assign null with the current implementation. Thus, I created two constructors
      in `ClearComamnd` class to handle clearing of workouts and exercises separately.


- **Extras**:
    - Provided code skeleton for the project with relevant classes to get started.
    - Reformatted the entire code wherever necessary to tidy up and look for violation of coding standard before v1.0
      release.


- **Code
  Contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Kishor&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=KishorKumar11&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


- **Documentation**:
    - User Guide:
        - Added expected outcomes for all the features.
        - Added documentation for `recommend`, `edit` and `clear` feature.
        - Added introduction and purpose.
        - Enhanced display of warnings and notes.
    - Developer's Guide:
        - Added `command` component.
        - Drafted UML Diagram for `Parser`.
        - Added `Recommending workouts`, `Editing an exercise` and `Clearing all workouts or exercises` components in Instructions for Manual Testing.
        - Contributed to the user stories.
        - Added `Setting up and Getting started` component.


- **Project Management**:
    - Helped with assignment of tasks to project members.
    - Planned team meetings and agendas for each week.
    - Enforced appropriate use of labels.
    - Assigned project milestone deadlines.
    - Added author tags for the team to define the workload/contribution for each member.


- **Community**:
    - PRs reviewed (with non-trivial review comments) (Team members): 
      [#16](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/16),
      [#192](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/192)
    - PRs reviewed (with non-trivial review comments) (Other teams): 
      [#31](https://github.com/nus-cs2113-AY2122S1/tp/pull/31#pullrequestreview-792607552)
    - Reported bugs and suggestions for other teams in the class: (AY2122S1-CS2113T-T10-1 issues) 
      [#260](https://github.com/AY2122S1-CS2113T-T10-1/tp/issues/260),
      [#268](https://github.com/AY2122S1-CS2113T-T10-1/tp/issues/268)

