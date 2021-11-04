# Jonathan Mui's Project Portfolio Page

## Project: Get Jack'd

Get Jack'd is a Command-Line application that helps busy people manage their workouts and fitness routines quickly by 
typing in commands. It is written in Java and has about 6 kLoC. (6000 lines of code)

Given below are my contributions to the project.

- **New Feature**: Added storage of data into JSON file in v1.0
    - **What it does**: automatically saves user's workouts in a JSON file whenever the user makes any changes to their
  workout list. (i.e. adding/editing/deleting exercises or workouts)
    - **Justification**: This feature improves the product as the user does not have to remember any of the workouts they 
  have created. In addition, a JSON file does not use text separators to store data which makes the data file more 
  readable and less prone to bugs. 
    - **Difficulties faced**: This implementation was challenging as I had to learn how to use a third party library and
  implement it. 
    - Credits: [Jackson](https://github.com/FasterXML/jackson) library was used for this feature. In addition, I 
  referred to a Youtube [Video Series](https://www.youtube.com/playlist?list=PLAuGQNR28pW4dOc5uytMdzcQ4-TCJFUN4) to 
  learn how to use the Jackson library. 

- **Enhancement to existing feature**: Allowed users to set a deadline for their newly created workouts in v2.0.

- **New Feature**: Added automatic sorting of workouts according to their deadlines in v2.0
    - **What it does**: automatically sorts user's workouts whenever the user makes any changes to their
      workout list. (i.e. adding/editing/deleting exercises or workouts). The workouts that are due first will appear
  first in the list. The workouts without a deadline will be at the bottom of the list and arranged based on the order
  they are added.
    - **Justification**: This feature improves the product as users can easily see the fitness goals they set for
  themselves and complete their workouts on time.
    - **Difficulties faced**: It was challenging to find an optimal algorithm to sort the workouts, especially since 
  the workout list array list stores both normal workouts and workouts with deadlines (DeadlineWorkout is a child class
  of Workout and WorkoutList is an arraylist of Workouts).

- **Code Contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=jonathanmui4&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Documentation**: 
    - User Guide:
      - Added documentation for `Editable data file` feature.
      - Added documentation for `Automatic sorting of Workouts` feature.
      - Added installation instructions.
      - Added Table of Contents with links to relevant headers. 
    - Developer's Guide:
      - Added implementation details of `storage` feature. 
      - Added Target user profile and value proposition.
      - Added Table of Contents with links to relevant headers.
      - Added `Launch and Shutdown` and `Saving data` components in Instructions for Manual Testing.

- **Project Management**:
    - Managed releases `v1.0`-`v2.0` (2 releases) on GitHub.

- **Community**:
    - PRs reviewed (with non-trivial review comments): [#59](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/59#discussion_r725626678), 
    - Reported bugs and suggestions for other teams in the class: [#16](https://github.com/nus-cs2113-AY2122S1/tp/pull/16#pullrequestreview-792610333)

- **Tools**:
    - Integrated a third party library (Jackson) to the project. ([#20](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/20))