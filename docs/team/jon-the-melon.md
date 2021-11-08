# Jon Lim's Project Portfolio Page

## Project: Teaching Assistant's Assistant (TAA)
Teaching Assistant's Assistant (TAA) is a Command Line Interface (CLI) application for keeping track of classes and students.
It is written in Java, and has about 6 kLoC. 

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

* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Jon-the-MELON&tabRepo=AY2122S1-CS2113T-F12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub
    * Created issues on issue tracker based on user stories
    * Reviewed and merged teammates pull requests 

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
        * Added command summary as a table ([\#100](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/100/files))
        * Did cosmetic tweaks to features list and command summary 
          ([\#219](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/219/files))
    * Developer Guide:
        * Added implementation details and sequence and object diagram of the `set_mark` feature.
          ([\#134](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/134/files),
           [\#322](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/322/files))
        * Added alternate implementation for `Marks` and the associated class and object diagram. 
          ([\#324](https://github.com/AY2122S1-CS2113T-F12-3/tp/pull/324/files))
      
* **Team-Based Tasks**:
    * Organised Zoom meetings to discuss features and issues
    * Maintained the issue tracker
    * Helped teammates test bugs in their implementation

* **Community**:
    * Reported bugs and suggestions for other teams in the class on [PED](https://github.com/jon-the-melon/ped/issues).
    