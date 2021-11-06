#Jon Lim's Project Portfolio Page


### Project: Teaching Assistant's Assistant (TAA)
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of classes and students.
It is written in Java, and has about 9 kLoC.

Given below are my contributions to the project.
* **New Feature**: Added `results` HashMap attribute for each student in `v1.0`
  * **What it does**: Stores the `marks` for a student as a value, using the `assessmentName` as the key in the HashMap.
  * **Justification**: Enable access to the correct `mark` by simply querying for the `assessmentName`.
  
* **New Feature**: Added `command` classes for `marks` across `v1.0` - `v2.1`
  * These include: `SetMarkCommand`, `ListMarksCommand`, `EditMarkCommand`, `DeleteMarkCommand`, `AverageMarkCommand`
  , `MedianMarkCommand`
  * **What they do**: Allows the user to mark students, edit or delete those marks, list all marks in their class, 
  as well as find the average and median mark.
  * **Justification**: These commands form the backbone of our application's features by allowing the user
  to track their students' class performance.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=jon-the-melon)

* **Project management**:
    * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage 
      (Pull request [\#234](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/234/files))

* **Documentation**:
    * User Guide:
        * Added documentation for the `mark` features 
         ([\#100](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/100/files), 
          [\#114](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/114/files), 
          [\#127](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/127/files),
          [\#212](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/212/files))
        * Did cosmetic tweaks to features list and command summary 
          ([\#219](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/219/files))
    * Developer Guide:
        * Added implementation details and sequence diagram of the `set_mark` feature.
      
* **Team-Based Tasks**:
    * Organising Zoom meetings to discuss features and issues.
    * Maintaining the issue tracker

* **Community**:
    * Reported bugs and suggestions for other teams in the class.
      (example: [1](https://github.com/jon-the-melon/ped/tree/main/files))
    