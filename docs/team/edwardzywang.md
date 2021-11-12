# Wang Zhao Yu, Edward - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator
which can give you an idea of your current fitness level.

### Summary of Contributions
#### Code contributed
[Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=EdwardZYWang&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements implemented
* New feature implemented: Command Manager Class:
  * What it does: A class that makes sense of a user's inputs. It first takes in the
   user's input as string, then splits it in a string array. it takes the sub string to
   the left of " /c " as the command and the remaining string is further separated
   into substrings based on other separators. The command substring allows the
   command manager to decide where to send the user's input to, while the
   other substrings are the parameters of the user's input commands.

  * Why is it needed: It is needed so that the numerous commands that CLI.ckFit
    allows the user to utilise are all managed and split at a common starting point.
    This standardises the logic flow and also how we want the user's inputs to be
    formatted in. Through standardisation, it reduces the load on all members
    when it comes to debugging as the same basic logic flows can be used and
    compared to quickly isolate bugs and resolve them. faster and also
    standardise the logic pathway of making sense of the user's inputs.

  * What is noteworthy about it: Due to the numerous commands that CLI.ckFit supports, command manager is further split into different components,
  namely foodbank parser, command manager, list parser, add parser, delete parser, to manage different types of commands. This makes for a neater outlook 
  on our command manager class when it comes to reviewing, and also when users add new commands or functions of the same type, we can easily refer to 
  past or other similar commands to know what format of inputs or parameters should be accepted.

* New feature implemented: Miscellaneous classes like userHelp and contributions to Ui and Storage classes 
  * What it does: userHelp class handles the "help" command and has a unique output when invoked. memoryStartup method in Ui class takes in the user's input and when called, it loads all the information in the text 
    files as an array list by calling my methods in Storage class. My methods in Storage class then organises them in a reader-friendly 
    format, before printing the finished summary for the user to read.
  * Why is it needed: userHelp class is needed as the help commands calls for a unique output and can be called outside the main program memoryStartup method is needed as it is called outside the main program and accounts for the event when the user does
    not want to enter the main program to key in "list" command to see his past progress. It helps greatly in usability and convenience.
  * What is special about it:memoryStartup also offers the user the ability to clear the contents in the text files if the user wishes to start a
    new session, or if the user passes the program on to another user, and wishes to protect his privacy.

* New feature: Implemented BMI calculator in calculator class
  * What it does: A method which allows the user to calculate his or her BMI, so that the user
  can know whether he or she should concentrate on gaining or losing weight.
  * Why is it needed: It is needed to let the user know his or her starting point before using CLI.ckFit. It can also be accessed 
  without entering the main program, so it is useful for users who want to quickly check their BMI.
  * What is special about it: It works well with recommended daily caloric intake calculator to give the user an idea of where he 
  or she stands health-wise. Knowing their starting point helps a lot when it comes to how they use CLIck.Fit for their fitness and health 
  goals.

#### Contributions to UG
Wrote the following sections:
* Introduction
* Quick start
* Common terminologies and definitions
* Important FAQs
* Table of contents
* Help command
* Command Summary

Handled standardisation and formatting for the user guide

#### Contributions to DG
Wrote the following sections:
* User Interface (Ui) and its subsections
* UML diagrams for Getting BMI and daily caloric intake and Memory startup method
* Explanations for the above diagrams
* Added Manual Testing for BMI and recommended daily caloric intake calculators

Contributed to:
* Appendix B User Stories


#### Contributions to team-based tasks
* Reformatting of the Design & implementation section in the UG.
* Added hyperlink support to table of contents in UG
* Added summary table of commands in UG and its hyperlinks
* Maintaining the issue tracker for PED and others
* Release management for v2.0
* Wrote meeting agendas and took down notes during meetings for team member's reference
* Took down notes during meetings with professors and tutors for team member's reference


#### Review/mentoring contributions
* Group mate PR reviews:
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/21](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/21)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/166](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/166)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/123](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/123)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/122](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/122)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/102](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/102)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/83](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/83)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/143](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/143)
  
* Peer PR reviews:
  * [PED](https://github.com/EdwardZYWang/alpha/issues/1)
  * [Review of peer's PR](https://github.com/nus-cs2113-AY2122S1/ip/pull/170)