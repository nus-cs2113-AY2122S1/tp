# Mahadevan Swati's Project Portfolio Page

# Project: Click

## Overview

Click is an all-in-one desktop app for managing modules, tracking your food consumption, time scheduling, journaling, and CAP planning!
We know that you are a Computing student here at NUS 👨‍🎓 and may prefer typing ⌨ to swiping (Hello Vim! 😉)
, so we think that our command line interface for cramming & knowledge (Click) is a great fit for you!

## Summary of Contributions

**Major Enhancement**:
1. Calendar display feature - Displays the calendar to the user when the following command is given: `calendar display [MM-YYYY]`. This feature was the most effort-consuming considering that I had to implement a consistent display (explained in the DG Section 4.3.1 under Design Considerations). 
2. Calendar task add feature - Adds a task to the calendar when the following command is given: `calendar todo n/ [TASK_NAME] d/ [DD-MM-YYYY]`. The code for this portion was taken from the ip and tweaked to accept date of the task as well.
3. Calendar lecture add feature - Adds a lecture to the calendar when the following command is given: `calendar lecture m/ [MODULE_CODE] s/ [DD-MM-YYYY(START_DATE)] e/ [DD-MM-YYYY(END_DATE)]`.
4. Calendar task list feature - Lists all tasks when the following command is given: `calendar list task`.
5. Calendar lecture list feature - Lists all lectures when the following command is given: `calendar list lec`.
6. Calendar task edit feature - Edits a task when the following command is given: `calendar edit [TASK_INDEX]`
7. Calendar task delete feature - Deletes a task when the following command is given: `calendar delete task [TASK_INDEX]`.
8. Calendar lecture delete feature - Deletes a lecture when the following command is given: `calendar delete lec [LECTURE_INDEX]`
9. Calendar related storage

**Code Contribution**: [Functional and Test code](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=swatimahadevan&tabRepo=AY2122S1-CS2113T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

**Contribution to the User Guide**: Wrote the portion of the user guide that corresponds to my features (Section 3.3 - Managing your calendar) and provided constructive feedback and made changes on the layout as a whole. 

**Contribution to the Developer Guide**: Wrote the portion of the developer guide that corresponds to my features (Section 4.3 - Calendar-related Features) and provided constructive feedback and made changes on the layout as a whole. All the UML diagrams under Section 4.3 were done by me.

**Community**:
1. Provided feedback to the team during team meetings and reviewed PRs. These are the PRs I left non-trivial comments on- [#28](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/28), [#36](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/36), [#163](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/163), [#240](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/240), [#245](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/245)
2. Did some common tasks for the team(such as updating logo, last minute team changes prior to submissions, etc).
3. Review Developer Guide from other teams in the tutorial (with non-trivial review comments). [team reviewed](https://github.com/nus-cs2113-AY2122S1/tp/pulls?q=is%3Aopen+is%3Apr+CS2113T-W12-2+)
4. Reported bugs for the other team in the [PE Dry Run](https://github.com/swatimahadevan/ped/issues).

