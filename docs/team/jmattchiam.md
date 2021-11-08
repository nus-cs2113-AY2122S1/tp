# Joel Chiam's Project Portfolio Page

## Project: Get Jack'd

Get Jack'd is a Command-Line application that helps busy people manage their workouts and fitness routines quickly by
typing in commands. It is written in Java and has about 6 kLoC. (6000 lines of code)

Given below are my contributions to the project.

- **New Feature**: Implemented the Exercise class for v1.0
  - **What it does:** Exercises are the most basic unit of data for the application. This class had to be defined as early
  as possible so that the other classes that would be referencing it (e.g. parsers, commands) can be written on time.
- **New Feature:** Implemented help command
  - **What it does:** Prints help message for user to describe the various commands and what they do. When the user
  specifies a command (e.g. "help add"), then more detailed information on that command is printed instead.
  - **Justification:** Allows the user to easily get information on how to use Get Jack'd without having to consult the User Guide
  , which may be inconvenient and not fast. Detailed command information is only given when requested because printing all the information
  is both long and will be overwhelming for the user.
  - **Difficulties faced:** Required a minor change to the structure of command classes to include a specific String constant to be printed 
  for each command's specific help message.

- **Enhancement to existing features**: Restructured and splitting of v1.0 parser into various Parser classes
    - **What was done:** Split Parser class into CommandManager class, Parser (parent) class and various child Parsers.
    - **Justification:** In v1.0, there was only one parser class with methods that were shared between the different commands.
    Splitting this class into the separate classes made the code more object-oriented and easier to read and debug.
    made the code more object-oriented and easier to understand and debug. 
    - **Credits:** Some logic was retained from the original methods in parser class written by group member Qianqi. 
    some utility methods were also retained in the new Parser class

- **Enhancement to existing features**: Minimised the use of flags like "/w" for command parameters.
  - **What was done**: Changed command formats to use commas "," instead of different flags like "/w" to indicate command parameters
  - **Justification**: Having many different flags like "/w", "/e", "/d" etc. can get confusing for users as they will have to remember 
  what each flag represents, and what flags to use for the different commands. By eliminating the use of specific flags and using commas instead
  users will no longer need to remember the flags, just the format of the command.
  - **Difficulties faced**: This required significant changes to the logic of many of the Parser classes. After this was done, much testing 
  and bug fixing was required to ensure that the new logic was robust and accounted for different user inputs. This is because using flags like "/w", while
  confusing for the user, is easier to parse and less likely for the user to input wrong formats.

- **Team-Based tasks:** 
  - Wrote JUnit tests for the various Parser classes, CommandManager class
- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=JMattChiam&tabRepo=AY2122S1-CS2113T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
- **Documentation**: 
  - User Guide:
    - Added Command information.
    - Final editing, formatting and standardisation.
  - Developer's Guide:
    - Added Parser component, including UML class diagram.
- **Community**
  - PRs reviewed (with non-trivial comments): [#12](https://github.com/AY2122S1-CS2113T-F12-2/tp/pull/12)
  - Reported bugs and suggestions for other teams: [#26](https://github.com/nus-cs2113-AY2122S1/tp/pull/26/files#r738927325)