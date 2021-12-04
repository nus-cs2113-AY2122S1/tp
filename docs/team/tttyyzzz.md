---
layout: page
title: Yi Zhi's Project Portfolio Page
---



## Overview
Fitbot is a desktop app that helps university students who are looking to keep track of their calorie consumption and calorie
output with the speed and convenience of command-line based tools, especially in times of online school.

### Summary of Contributions
Summary of contributions are as listed below.

### **New Feature**
#### **creation of create profile**

- What it does: _Fitbot_ will prompt and guide user for profile particulars before allowing users to type in 
other commands.

- Justification: There are some commands that require profile attributes such as `BMI` and `Overview` commands. A bug might occur if the user decides to use the commands above first instead of setting up profile.
- Highlights: If the user profile is not complete, profile data will not be saved.

### **New Feature**
#### **Overview**
- What it does: create a summary of the daily and weekly overview of the calories gained from food and 
lost from exercise.
- Justification: allows users to have a better user experience and allow users to visualised data.
- Highlights: There is bar graph in the overview
- 
### **New Feature**
#### **creation of `Help` Class**
- What it does: A summary of all the commands and their formats.

### **New Feature**
Fitbot start-up page.

#### **Code Contributed**:
[Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=tttyyzzz&tabRepo=AY2122S1-CS2113T-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
#### **Project Management**:

PRs reviewed: [#33](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/33#discussion_r725440121),
[#36](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/36#discussion_r725726457),
[#39](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/39#discussion_r726717261),
[#217](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/217#discussion_r742581641)
#### **Enhancements To Existing Features**:
#### Create Profile v2.0
Will check if the attributes is valid. IF most of the attributes are invalid, it will ask user to recreate the profile.
Else, the system will pick up those attributes that are invalid and will prompt the user for a valid input upon 
starting up _Fitbot_

Allow users to have the option to quit the application if they wish to quit while setting up the profile attributes.
#### **Documentation**

**User guide** 
- Section 3: Set Up profile
- Section 4.7: Viewing Your Calorie Summary `overview`
- Section 4.8: Viewing Help `help`

[Link to user guide](https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html)

**Developer Guide**
- Explanation for Main Architecture Diagram.
- Explanation of _Ui component_ (includes ui sequence diagram)
- Explanation of _State component_ (includes state class diagram)
- Explanation of _Create Profile If Not Exist On Startup_ (create profile sequence diagram)
- Give test cases for _Setting Up Profile_ in _Instructions for manual testing_

[Link to developer guide](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html)

#### **Community**:
