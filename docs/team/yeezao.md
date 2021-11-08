# Liang Yuzhao - Project Portfolio Page

## Overview of Project

_PayMeBack_ is an expense tracker targeted at groups travelling overseas. It aims to help groups simplify the process of
repayment for overseas expenses, by consolidating all expenses and issuing a summary report at the end, so users can easily
identify who they need to pay.

_PayMeBack_ is a greenfield project. My main responsibilities in this project included:
- As the team member most proficient in Java, to provide guidance and mentorship to other team members on Java.
- To manage the team organisation and repository.
- To implement and test new features in the program.
- To maintain a high quality of code and workflows in collaboration with other members of the team.

### Summary of Contributions

#### Code Contributions

I contributed 1585 lines, of which 895 lines are functional code, 373 lines are test code, and 315 lines are documentation-related.
Detailed code contribution information can be viewed via RepoSense [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=yeezao&tabRepo=AY2122S1-CS2113T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false).

- Provided basic skeleton for `Ui`, `Parser`, `Trip`, `Expense`, `Person` classes.
- Implemented and tested `FileStorage` class, and some of `Trip` and `Storage` classes.
- Wrote a large part, or the entirety, of `FileTest`, `TripTest`, `ValidityCheckerTest`

#### Enhancements Implemented:

- **New Feature**: Using `last` to get the trip which the user last interacted with, saving time from having to repeatedly figure
  out what the trip index is. This is especially useful for users who do `open` immediately after `create`, and saves them a step
  of having to run `list` to figure out the trip index.
- **New Feature**: Reading from and writing to a save file using the JSON format (through serialisation/deserialisation with Google Gson).
- **Enhancement to Feature**: Adding custom serialiser/deserialiser to Gson for LocalDate objects.
- **Enhancement to Feature**: Ability to assist user in overwriting a corrupted save file.
- **New Feature**: Allowing users to abort a process when asked to correct an erroneous input (completed in collaboration with @joshualeeky)

#### Contributions to User Guide:

The following sections in the User Guide were largely or wholly written by me:

- Saving your data, Loading your saved data
- Create Trip, Open Trip, Close Trip, Delete Trip, Edit Trip
- FAQ #2 and #3

In addition, I made contributions to the following sections:

- Introduction
- Using this guide
- Quick Start
- FAQ #1

#### Contributions to Developer Guide:

The following sections in the Developer Guide were largely or wholly written by me:

- `Storage` component, and its related diagrams

In addition, I made contributions to the following sections:

- Some text in `Trip` class

#### Contributions to Team Tasks:

- Setting up and administration of organisation and team repository
- Gradle modifications (for enabling assertions and adding Gson dependency)
- Managed `v2.0` release
- Cleanup of PE-D issues (marking duplicate, rejecting non-issues)
- General maintenance of issue tracker (milestone and label assignment)

#### Review and Mentoring contributions

- Comments on PRs ([#31](https://github.com/AY2122S1-CS2113T-T12-2/tp/pull/31#discussion_r723066635),
  [#42](https://github.com/AY2122S1-CS2113T-T12-2/tp/pull/42#discussion_r725532182), [#55](https://github.com/AY2122S1-CS2113T-T12-2/tp/pull/55#discussion_r726785554))
- Mentoring on JUnit tests - simulating user input through `Scanner`, and testing `System.out.println()` statements.

#### Contributions beyond the team

- Replying to module forum posts ([#74](https://github.com/nus-cs2113-AY2122S1/forum/issues/74#issuecomment-922768286))
- Reporting of bugs on module website ([#93](https://github.com/nus-cs2113-AY2122S1/forum/issues/93))