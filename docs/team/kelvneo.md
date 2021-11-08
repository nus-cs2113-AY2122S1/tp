# Kelvin Neo - Project Portfolio Page

## Overview

**TermiNUS** is a CLI (command line interface) program for NUS students who wish to consolidate their NUS academic needs such as schedules, questions and notes for the modules that they are taking. With TermiNUS, it aims to aid students and improve their learning experiences while studying in NUS.

### Summary of Contributions

- **New Feature:** Added `question` which is the ability to add, delete, list and test the user 
with questions.
  - What it does: It provides the user a way of storing and managing their questions, as well as 
  running an Active Recall session to test themselves on their own knowledge.
  - Justifications: `question` was built to allow users to test themselves using the techniques of 
  Active Recall, which aims to improve their knowledge and memory of the subject.
  - Highlights: The `test` feature includes a timer that indicates how long the user took to answer 
  each question. The Active Recall portion will also tweak the frequency of the questions appearing 
  based on the feedback the user gives. For example, if the user deems the question as hard, the 
  question would appear more often, and if the user deems the question easy, the question would 
  appear less often.
- **New Feature:** Added `TerminusLogger`
  - What it does: `TerminusLogger` wraps around `java.util.logging.Logger` and provides helper 
  methods that calls the internal `Logger` methods.
  - Justifications: To provide a unified and simpler logging experience for the entire application, 
  `TerminusLogger` was created to prevent duplicate Logger initialization code. It also disables 
  logging to terminal, and logs only to a file by default.
  - Highlights: It provides a wrapper function that can print the stack trace into the logger for 
  `WARNING` and `SEVERE` cases.
- **New Feature:** Added `help` and `exit` command.
  - What it does: `help` and `exit` commands are basic commands to enable the user to view the 
  commands in the current workspace and exit the program respectively.
  - Justifications: `help` must print the commands that exist within the specific workspace to 
  prevent the user from being confused and running the commands in the wrong places. The `exit` 
  command is placed at every workspace to allow the user to quit any time, regardless of which 
  workspace they are in.
- **New Feature:** Added `Terminus` and `Ui`
  - What it does: `Terminus` is the main class that runs the main loop of the entire application.
    `Ui` class handles all input and output from the user.
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=kelvneo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=kelvneo&tabRepo=AY2122S1-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)



- **Documentation:**
  - README.md:
    - Included short writeup on how TermiNUS works: [#107](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/107)
    - Added badges on various DevOps: [#107](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/107)
  - User Guide:
    - Documented section on Questions: [#107](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/107)
    - Reorganized User Guide based on feedback from PE-D and tutor: [#189](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/189)
  - Developer Guide:
    - Documented section on Questions: [#107](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/107)
    - Documented section on Ui: [#133](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/133)
    - Documented sections on Documentation, Logging, Testing, and DevOps: [#184](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/184)
    - Added acknowledgements and necessary headers for Developer Guide: [#184](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/184) 
- **Team-based Tasks**:
  - Tracked the deadlines and schedules of deliverables
  - Maintaining the issues and PR trackers
  - Increased code coverage: [#56](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/56), 
  [#68](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/68),
  [#84](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/84),
  [#102](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/102),
  [#117](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/117),
  [#186](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/186)
  - Added GSON as a library for main data storage: [#70](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/70)
- **Beyond Project Team Tasks:**
  - Reported bugs for another team in PE-D: [PE-D Issues](https://github.com/kelvneo/ped/issues)
  - Reviewed other teams' User Guide and Developer Guide.
- **Tools:**
  - Integrated [Codecov](https://codecov.io) for code coverage tracking: [#110](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/110)
