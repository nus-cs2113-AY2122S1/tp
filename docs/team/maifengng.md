# Mai Feng - Project Portfolio Page

## Project: The Great Detective
The Great Detective is an interactive murder-mystery game.


### Summary of Contributions

- `Feature`: Parser class
  - What it does: Parses the input from the user and returns a command based on the user input.
  - Justification: This allows us to generate specific commands based on the user input.

- `Feature`: Command class
  - What it does: The command class is an abstract class that has an `execute` method that executes the game logic and an `exit` command to determine if the game has ended.
  - Justification: It allows us to extend the abstract Command class to create subclasses that executes different game logic.

- `Feature`: Ui class
  - What it does: The Ui class prints the output to the terminal/command line to communicate with the user.
  - Justification: It allows us to have a centralized system to communicate with the user to reduce code duplication.


- `Code Contribution`: [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Maifeng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=zoom&zA=MaifengNg&zR=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&zACS=103.0909090909091&zS=2021-09-25&zFS=Maifeng&zU=2021-11-05&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
- `Enhancements implemented`: 
  - Refactor code base to follow more principles such as SLAP.
  - Implemented feature to allow users to investigate a suspect in multiple ways. Instead of only being able to choose a suspect using a suspect's index, we can now investigate using the suspect name or even use the /investigate command.
- `Documentation`:
  - `User Guide`
    - Added documentation for the features `/next`, `/exit`, `/back`, `/help`, `/restart`, `Choosing a suspect`, `Investigating clue`.
  - `Developer Guide`
    - Added documentation and UML diagram for `Parser` feature.
  - `Javadocs`
    - Written non-trivial documentation for `Parser`, `Command`, `Investigation` class.
    
- `Contributions to team-based tasks`:
  - Created the skeleton structure for the Developer Guide.
  - Created the skeleton structure for the User Guide.

- `Review/mentoring contributions`:
  - Reviewed and merge some PRs requested from team members [#237](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/237) [#114](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/114) [#93](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/93)