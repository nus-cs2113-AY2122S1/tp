# Bernard Boey Khai Chen - Project Portfolio Page

## Overview

ExpiryEliminator aims to help young adults living by themselves manage their ingredients and recipes they can cook based on the ingredients they have.
The application is CLI based and is suitable for users who can type quickly and accurately.

### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=bernardboey&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)


* **New Feature**: Added the ability to add ingredients.
    * What it does: Allows the user to add an ingredient to the ingredient repository, and optionally define a unit for that ingredient (e.g. `g`, `kg`, `btl`).
    * Justification: This feature is essential for the product as the app aims helps users to manage their ingredients.
    * Highlights: The implementation was challenging as it underwent three versions. At first, for v1.0, it allowed users to add ingredients together with an initial quantity and expiry date. Then, when implementing units for v2.0, we allowed users to optionally add units at the same time. At this point, I also changed the quantity and expiry date to be optional so that users could add an ingredient with 0 quantity. Finally, for v2.1 the quantity and expiry date was removed from this command after feedback from classmates, to focus this command on adding an ingredient and an (optional) associated unit, and quantity and expiry date were delegated to other commands.


* **New Feature**: Added the ability to increment and decrement quantities of ingredients.
    * What it does: Allows the user to increment/decrement quantities of ingredients. For the increment command, it allows the user to specify the expiry date of the ingredients.
    * Justification: This feature is essential for the product, otherwise users would not be able to modify the quantities of ingredients.
    * Highlights: Having different batches of expiry dates for a single ingredient (added one batch at a time) is allowed, which is technically difficult to implement. Additionally, for the decrement command, users don't have to specify an expiry date, and the app automatically decrements from the oldest batch. 


* **New Feature**: Added the ability to delete ingredients.
    * What it does: Allows the user to delete an ingredient entirely from the ingredient repository.
    * Justification: This feature is necessary so that the user can remove ingredients that they no longer have and use.
    * Highlights: I made sure that users cannot delete an ingredient if it is being used by a recipe, as it is necessary for an ingredient to be in the repository if it is used in a recipe (the ingredient stores the associated unit).
    

* **Enhancements**:
    * Set up starter code (`commands`, `data`, `ui`, and `parser` packages) ([#19](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/19)), taking inspiration from my own [ip](https://github.com/bernardboey/ip).
    * Extensively refactored and improved parser by adding more OOP, thereby improving code quality, reducing coupling and increasing cohesion ([#24](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/24)).
        * Organised into the `Parser` class, `ArgsParser` class, `argparser` package which involve the `SingleArgParser` and `MultipleArgParser`, as well as the `prefix` package, making use of abstract classes.
    * Refactored the `data` package to improve OOP and code quality ([#45](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/45)).


* **User Guide**:
    * Added table of contents, introduction, quick start, and notes to the user guide ([#83](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/83), [#90](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/90)).
    * Updated the command summary ([commit](https://github.com/AY2122S1-CS2113-T16-3/tp/commit/cd133ae9db82cfbdec13ea803ffc5e065aed25e8#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)).
    * Added documentation for the features `add`, `increment`, `decrement`, `delete`, and `bye` ([#90](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/90)).


* **Developer Guide**:
    * Added table of contents, acknowledgements, value proposition, user stories, and non-functional requirements to the developer guide ([#84](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/84), [#87](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/87), [#90](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/90)).
    * Added documentation for the parser and added 3 class diagrams and 1 sequence diagram ([#84](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/84), [#87](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/87)).
    * Added instructions for manual testing for `add`, `increment`, `decrement`, `delete`, and `bye` ([#150](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/150)).


* **Team-based tasks**:
  * Set up the GitHub team org and repo.
  * Created and maintained the three [milestones](https://github.com/AY2122S1-CS2113-T16-3/tp/milestones?state=closed) (v1.0, v2.0, v2.1).
  * Maintained the [issue tracker](https://github.com/AY2122S1-CS2113-T16-3/tp/issues?page=1&q=is%3Aissue+is%3Aclosed) by triaging, organising, and closing bugs.
  * Managed the GitHub workflow by imposing branch protection rules (i.e. requiring pull request, approvals, and status checks to pass before merging). Subsequently removed the rules when it was less useful (e.g. cosmetic edits to the UG/DG).
  * Added code enhancements for the team (see more under enhancements)
  * Updated the user guide and developer guide on parts not specific to a feature (see more under user guide and developer guide).


* **Review/mentoring**:
    * PRs reviewed (with non-trivial review comments): [#20](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/20#pullrequestreview-772484416), [#26](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/26#pullrequestreview-775554937).
    * Mentored and provided suggestions to teammates via video call / chat message.


* **Beyond project team**:
    * Reported bugs and suggestions for other teams' UG and TP ([1](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/129), [2](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/133), [3](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/137), [4](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/145), [5](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/147), [6](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/154), [7](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/159), [8](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/162), [9](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/167), [10](https://github.com/AY2122S1-CS2113T-W12-2/tp/issues/170)).
    * Reported bugs and suggestions for other teams' DG ([1](https://github.com/nus-cs2113-AY2122S1/tp/pull/8#pullrequestreview-791538393)).
