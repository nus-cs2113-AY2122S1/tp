# Richard Willie - Project Portfolio Page

<img src="https://i.imgur.com/9s3AOcl.jpg" width="300" height="300" />

## Table of Contents

* [Overview](#overview)
* [Summary of Contributions](#summary-of-contributions)
    * [Code Contributed](#code-contributed)
    * [Enhancements Implemented](#enhancements-implemented)
        * [Code & Packages Setup](#code--packages-setup)
        * [Unit Testing & Assertions](#unit-testing--assertions)
        * [Logging](#logging)
        * [Code Refactoring](#code-refactoring)
        * [Features Improvement](#features-improvement)
        * [PED Bugs](#ped-bugs)
    * [Contributions to the User Guide](#contributions-to-the-user-guide)
        * [Extract: Command Summary](#extract-command-summary)
    * [Contributions to the Developer Guide](#contributions-to-the-developer-guide)
        * [Extract: Logic Component Diagram](#extract-logic-component-diagram)
    * [Contributions to Team-based Tasks](#contributions-to-team-based-tasks)
    * [Review/Mentoring](#reviewmentoring)
    * [Contributions Beyond The Project Team](#contributions-beyond-the-project-team)
        * [Forum Contributions](#forum-contributions)
        * [Module Contributions](#module-contibutions)
        * [Other Contributions](#other-contributions)

## Overview

The design philosophy of [NUSBuddy](https://ay2122s1-cs2113t-w11-3.github.io/tp/) mostly follows [KISS](https://nus-cs2113-ay2122s1.github.io/website/se-book-adapted/chapters/codeQuality.html#practice-kissing),
in other words, we want the app to be fast and unobtrusive, so that users spend as little time as possible using it and actually focus on doing their actual work.
In addition, our user commands are friendly (i.e., make sense grammatically). For instance, `add task` is better than `task add`.

My responsibilities:

* **Team Lead**: Responsible for project workflow and coordination.
* **Quality Assurance**: Responsible for overall quality of program design and implementation.

## Summary of Contributions

### Code Contributed

My overall code contributions can be found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=richwill28).

### Enhancements Implemented

#### Code & Packages Setup

* **PRs**: [#23](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/23), [#25](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/25).

Set up code skeleton for the program.

#### Unit Testing & Assertions

* **PR**: [#28](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/28), [#114](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/114), [#163](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/163),
[#164](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/164), [#173](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/173).

Added JUnit testing and assertions to make code more defensive and more effective for regression testing.

#### Logging

* **PR**: [#60](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/60).

Added logging to record the states of the program, and is useful for troubleshooting when needed.

#### Code Refactoring

* **PR**: [#28](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/28), [#35](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/35), [#59](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/59),
[#164](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/164), [#184](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/114).

Refactored classes and packages to improve overall code quality (e.g., better OOP, SLAP, etc.).
Most notable refactoring is [#28](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/28),
as major changes to the code architecture were implemented for improved scalability and better separation of concerns with respect to OOP.

#### Features Improvement

* **PR**: [#28](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/28), [#64](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/64), [#84](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/84),
[#87](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/87), [#114](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/114), [#163](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/163),
[#173](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/173), [#192](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/192), [#193](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/193).

Most notable enhancement is the [launch lesson](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/114) feature.
The motivation for developing this comes from the frustration that some terminals do not support URL highlighting,
which prevent users from conveniently clicking and launching the URL stored in `Lesson`.

#### PED Bugs

* **PR**: [#163](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/163), [#173](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/173), [#184](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/184).

Squashed bugs found in PE Dry Run.

### Contributions to the User Guide

* **PRs**: [#95](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/95), [#106](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/106), [#193](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/193).

Refactored most sections of UG to improve its layout. Updated User Guide sequentially as the program output changes.

#### Extract: Command Summary

Command summary was provided for users' convenience. Partial example:

| Command                                                                         | Purpose          |
| :------------------------------------------------------------------------------ | :--------------- |
| `add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`          | To add a task    |
| `add lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}` | To add a lesson  |
| `add module [MODULE_CODE]`                                                      | To add a module  |

### Contributions to the Developer Guide

* **PRs**: [#74](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/74), [#75](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/75), [#106](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/106), [#193](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/193).

Set up the structure of DG, starting with `Architecture` and `Logic` sections.
Incorporated UML Diagrams with the help of [PlantUML](https://plantuml.com/), including class and sequence diagrams.
Standardized UML Diagrams color scheme with `Style.puml`.

#### Extract: Logic Component Diagram

To better illustrate the relationships between the various classes used in the project, UML diagrams were utilized to explains the overall architecture of the program.
The following example is from the **Logic Component Diagram**.

![Logic Component Diagram](../images/LogicComponentDiagram.png)

### Contributions to Team-based Tasks

* Set up GitHub team organization and repository.
* Managed JAR releases.
* Maintained issue tracker.
* Maintained overall code quality and implementation designs.
* Maintained collaborative document ([Notion](https://www.notion.so/)) for project management.

### Review/Mentoring

Almost all PRs were reviewed, with notable ones listed below:

* Gave suggestions accepted by teammates:
[#24](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/24), [#26](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/26), [#30](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/30),
[#31](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/31), [#32](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/32), [#70](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/70),
[#80](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/80), [110](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/110), [#159](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/159),
[#161](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/161).
* Spotted minor fixes:
[#27](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/27), [#57](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/57), [#62](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/62),
[#73](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/73), [#104](https://github.com/AY2122S1-CS2113T-W11-3/tp/pull/104).

All PED bugs were reviewed and assigned with labels. Notable ones are listed below:

* [#112](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/112), [#113](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/113), [#118](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/118),
[#129](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/129), [#133](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/133), [#134](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/134),
[#141](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/141), [#149](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/149), [#155](https://github.com/AY2122S1-CS2113T-W11-3/tp/issues/155).

### Contributions Beyond The Project Team

#### Forum Contributions

I am one of the most active students throughout the module, with a total of [64 posts](https://nus-cs2113-ay2122s1.github.io/dashboards/contents/forum-activities.html#1-rich-llie-richwill28-64-posts) on the forum as of November 4th 2021.

Notable forum posts:

* [Rule of thumb on exceptions handling](https://github.com/nus-cs2113-AY2122S1/forum/issues/63#issuecomment-917387019)
* [Using Markdown when inserting code in forum posts](https://github.com/nus-cs2113-AY2122S1/forum/issues/47#issuecomment-910503125)
* [Helping a peer to check an exercise output](https://github.com/nus-cs2113-AY2122S1/forum/issues/39#issuecomment-908141464)
* [Removing wildcard imports in IntelliJ](https://github.com/nus-cs2113-AY2122S1/forum/issues/68)
* [Disabling automatic line wrap in IntelliJ](https://github.com/nus-cs2113-AY2122S1/forum/issues/122)
* [Hacktoberfest 2021](https://github.com/nus-cs2113-AY2122S1/forum/issues/57)

#### Module Contibutions

I reported website bugs to help improve the module:

* [Inconsistent Exercise tag](https://github.com/nus-cs2113-AY2122S1/forum/issues/1#issuecomment-907837525)
* [Inconsistent whitespaces](https://github.com/nus-cs2113-AY2122S1/forum/issues/1#issuecomment-909482718)
* [Incorrect description](https://github.com/nus-cs2113-AY2122S1/forum/issues/1#issuecomment-940620072)
* [Incorrect link](https://github.com/nus-cs2113-AY2122S1/forum/issues/1#issuecomment-955764610)

#### Other Contributions

I reported a total of [14 program bugs](https://github.com/richwill28/ped/issues) for other teams during PE Dry Run.

Notable reports:

* [Design flaw: duplicate employees](https://github.com/richwill28/ped/issues/5)
* [Implementation flaw: incorrect discount result](https://github.com/richwill28/ped/issues/11)
* [Design flaw: finding expired ingredients](https://github.com/richwill28/ped/issues/14)
