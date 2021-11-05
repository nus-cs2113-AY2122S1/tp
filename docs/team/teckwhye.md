# Tan Teck Hwee - Project Portfolio Page

## Overview

**CCA Manager** is a simple and easy to use command-line based application aimed to assist you, a CCA manager, to easily add, delete or alter information about your
CCA members. Moreover, **CCA Manager** also allows you to perform its functions on any training-related activities
you may have in order to help you organise your CCA's timetable.

### Summary of Contributions

[Code Contributed](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=teckwhye&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Teckwhye&tabRepo=AY2122S1-CS2113T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false
)

Features Implemented
* Set up member package
  * Implemented `MemberList` and `Member` classes
  * Implemented `AddMember` command; allows users to add a Member to MemberList
  * Implemented `EditMember` command; allows users to edit an existing Member
  * Implemented `DeleteMember` command; allows users to delete an existing Member by name or index
  * Implemented `FindMember` command; allows users to filter and display Member based on Member Name
  * Handled exceptions for the above commands
  * Implemented `InvalidMemberException` custom exception to allow better control of exceptions thrown by Member related functions
  * Set up JUnit test for the above commands


UG Contributions
  * Due to last minute changes to UserGuide.md, most of my team member's code might be attributed to Glenn (poppolette). This leads to inaccuracy of the above link, and you should view my team members' Project Portfolio Pages to get a better overview of everyone's contribution to the User Guide and Developer Guide
  * Add details to `Member` section related functions such as syntax and example input and output


DG Contributions
* Setup `Member` component for DeveloperGuide.md