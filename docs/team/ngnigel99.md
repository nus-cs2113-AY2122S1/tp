# Nigel Ng's Project Portfolio Page

# Project: Click

## Overview

Click is an all-in-one desktop app for managing modules, tracking your food consumption, time scheduling, journaling, and CAP planning!
We know that you are a Computing student here at NUS 👨‍🎓 and may prefer typing ⌨ to swiping (Hello Vim! 😉)
, so we think that our command line interface for cramming & knowledge (Click) is a great fit for you!

Given below are my contributions to the project.
## Summary of Contributions

### New Commands

**Food-related operations**
1. Add Food
   `food add n/ [FOOD_NAME] c/ [CALORIE] {d/ DD-MM-YYYY(DATE_RECORDED)}`
   
1. Add Food From Reference 
   `food radd s/ [STORE_INDEX] i/ [ITEM_INDEX]`
   
1. Clear Food 
   `food clear`
   
1. Delete Food 
   `food delete [INDEX]`
   
1. Find Food By Calorie Count
   `food clt [Calories]`
   
1. Find Food With Date `food find [DD-MM-YYYY]`
   
1. List Food `food list`
   
1. View Reference Food `food view, food view [STORE_INDEX], food view all `

      
**Help command**
1. For the user `help`
1. For the developer `help rt`


### Code Contribution: [Functional and Test code](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=ngnigel99&tabRepo=AY2122S1-CS2113T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


### Contribution to the User Guide:
  
Along with my teammates, I worked on writing a user-oriented user guide that has simple and easy to understand instructions, as well as included examples for the user to try.
    

**Wrote user-oriented introduction**
  
Following examples of effective and user-oriented user guides, I added a brief introduction that seeks to inform the user of what our app does, hook them into using the app with you-language and emojis as well as underscore the target audience (NUS computing students).    
    
**Explained Food-related commands**
    
I wrote this section as a narrative, including food items such as the Samurai burger from McDonald's as food consumed by a student after lessons. 
Additionally, I aimed to provide the user with clear instructions. For instance, the optional `d/` parameter was kept optional and highlighted in the user guide, followed with an appropriate note.
   
**Added commands in Command summary**

I used the help command to generate all commands at runtime on my end, to ensure that the syntax and  parameters passed are accurate.

**Compiling Frequently-asked questions**

I added a few FAQs which may be relevant for  computing students using a laptop for day-to-day work as well as to run the app.
  
**Created bug report section**
  
Following [Gimp's user guide](https://docs.gimp.org/2.10/en/gimp-introduction-bugs.html), I decided to include a bug report section that directs the user to our team page. While I acknowledge that there's no form of direct contact on that page, it's important to have a means of contacting the developers nonetheless.

### Contribution to the Developer Guide:

Similar to the user guide, I worked with my teammates on writing a guide that aims to be informative to other developers and for them to understand how our app works.

**Food-related architecture**

Elaborated on the class diagrams; how food records interact with the food list as well as how the food list interacts with the storage component. 

This was supported with a variety of diagrams that aim to demonstrate the functionality of my code from different vantage points. 

For instance, a sequence diagram is used to demonstrate how the food commands are parsed and which commands are constructed. 
Whereas a class diagram is used to show the relationship between a food record, the list as well as storage.

**Help-related architecture**

While a help command for the user is simply a hard-coded string, this string is generated automatically in a neatly sorted order alphabetically.

I explained how the syntax for the commands are extracted, along with the name of the command on runtime from the developer. 

I found this way very efficient and readable, so I shared how I came up with it with appropriate diagrams. 

For instance, a class diagram to show the architecture when parsing the literal `Classes` in, and a sequence diagram to illustrate how the `ClassPackageReader` reads the `classes` on runtime.

**Community**:

In this project, I found the GitHub suggestion feature interesting and have used in a few reviews.
+ PRs reviewed (with non-trivial review comments):
    1. [#28](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/28) 
    1. [#32](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/32)
    1. [#36](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/36)
    1. [#46](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/46)  
    1. [#57](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/57)
    1. [#60](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/60)
    1. [#144](https://github.com/AY2122S1-CS2113T-T09-4/tp/pull/144)
  
+ Gave some food for thought during the ped session, with this project as reference:
    1. [Automatically saving data](https://github.com/ngnigel99/ped/issues/6)
    1. [Replacing the user guide with an interactive tutorial](https://github.com/ngnigel99/ped/issues/5)

**Actions**:
+ Assigned bug issues from PED to teammates as I was browsing through them anyway

**Tools**:
+ Introducing the runtime-based help command organises commands, so teammates just have to add a default constructor for their new commands in order for it to be reflected in help message, which would be converted into a string for the user.