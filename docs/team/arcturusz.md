# Eljer's - Project Portfolio Page

## Project: The Great Detective
The Great Detective is an interactive murder-mystery game.

## Summary of Contributions

- **New Feature**: Added the ability to change narrative number of lines
  - What it does: Allows user to change the number of lines to be printed each time during story-telling narrative at the start of each scene.
  - Justification: As the narrative of each scene can be quite long, printing the entire narrative at once could overflow the terminal / command line.
  This feature allows user to easily pace themselves and view the narrative better.

- **New Feature**: Added the ability to restart the game
  - What it does: Allows user to restart the game back to the beginning, either in the middle of the game or after choosing the correct / wrong suspect at the end of the game.
  - Justification: After the game end, the user will receive a prompt to restart the game.
  Also, anytime during the game, the user can discard their progress and restart back to the beginning to start over.

- **New Feature**: Suspect class
  - What it does: Contains the list of clues for the different suspects.
  - Justification: It allows us to manage the different suspects as each suspect has a different list of clues.

- **New Feature**: SuspectList class
  - What it does: Contains the list of suspects for the different scene.
  - Justification: It allows us to manage each scene with different suspect list.

- **New Feature**: Investigation class
  - What it does: Manages the investigation in each investigation scene based on the current state of the game (Suspect or Clue stage)
  - Justification: It allows us to handle the commands from the user and contains the main flow of the game.

- **Code Contribution**: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=arcturusz&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=arcturusz&tabRepo=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

<div style="page-break-after: always;"></div>

- **Enhancements Implemented**:
  - Added assertions to ensure correctness of gameplay data.
  - Wrote JUnit test for several classes.
  - Wrote additional tests for existing features to increase coverage.

- **Documentation**:
  - **User Guide**
    - Added documentation for the features `/narrative-lines NUM`.
  - **Developer Guide**
    - Added documentation and UML diagrams for `Investigation` feature.
    - Added instructions for manual testing.
    - Contributed to part of `Design` component.
  - **Javadocs**
    - Added javadocs for several classes like `Suspect`, `SuspectList`, `Narrative`, `SceneListBuilder`.

- **Project Management**:
  - Setup repo's `Branch Protection Rules` to prevent unauthorized merging, and ensures CI tests pass before able to merge.
  - Maintain the issue tracker and milestones.
  - Review, approve and provide comments for pull requests.

- **Review / Mentoring Contributions**:
  - Helped team members fix branch and version control issues.
  - Helped team members with rebase to fix merge conflicts and revert changes.
  - Some PRs reviewed (with non-trivial comments): 
[#135](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/135),
[#218](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/218),
[#220](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/220),
[#213](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/213)

- **Contributions Beyond the Team Project**:
  - Reviewed other team's project. ([Example](https://github.com/nus-cs2113-AY2122S1/tp/pull/5/files/dc0f334b0895c33494b4ea0685143f176730f8fb))
  - Functional bugs and suggestions for other team during PED. ([Bugs Found](https://github.com/arcturusz/ped/issues))
