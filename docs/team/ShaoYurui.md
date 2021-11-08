# Shao Yurui's Project Portfolio Page

## Project: The Great Detective

_The Great Detective_ is a desktop CLI application that allows players to enjoy the fun of role-playing and 
logical reasoning to find out the truth of a murder case. The player gets the chance to investigate in the 
case by gathering information about the events that lead to the murder and clues about the suspects.

Given below are my contributions to the project.

* **Key Architecture**: Added `Encrypted` class,`FileDecoder` class,`FileEncoder` class, `GameFileManager` class
  * What it does : Serve as tools to encrypt the game data files, using "DES" encryption algorithm. 
  * Justification: The game needs some text files to store crucial information such as the game progress. 
  The user may temper with non-encrypted text file to cheat the game progress, or unintentionally damage the data file
  and cause the game to crush. 
  * Highlights: With "DES" encryption algorithm, the program is able to prevent users from cheating and 
  identify if the user has modified the game data file.
  

* **Key Architecture**: Added `GameDataFileDecoder` class
  * What it does: Serve as an interpreter of the game data file content
  * Justification: The game progress can need to be stored locally so that the user can resume the game in the future.
  `GameDataFileDecoder` can store the game progress into data file and read the progress from it as well.
  * Highlights: `GameDataFileDecoder` can identify if the user has modified the data file, 
  then it will reset the game progress. It can also generate new data file if the previous one is removed or corrupted. 


* **Key Architecture**: Added `GameFileScanner` class
  * What it does: Serve as a scanner of the game data file content
  * Justification: The encrypted file cannot be applied to normal file scanner, 
  `GameFileScanner` is necessary to scan the file line by line, and check if the content has next line.
  

* **New Feature**: Added `SearchedClueTracker` class
    * What it does: allows the user to review the clues he/she has searched before.
    * Justification: This feature improves the product significantly because there are a large number of clues in the game. It serves as a convenient way for him to review.


* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=ShaoYurui&tabRepo=AY2122S1-CS2113-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**: Update file IO   (Pull request [\#45](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/45))
   * Change the file IO from `java.io.File` to `java.io.InputStream` so that the test files that 
  contain narratives, endings and other confidential information are integrated inside the resources of the JAR file. 
  These files will not be accessible or visible to the users. Otherwise, the user would be able to see the endings without even playing the game.
  (Pull request [\#45](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/45))


* **Enhancements to existing features**: Updated `displaySceneNarrative` feature, added `clearConsole()` feature (Pull request [\#113](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/113))
  * What it does: allows the console to display narrative by few lines each time with a fresh clear screen. Previous the console displayed the entire narrative which has hundreds of lines
  * Justification: This feature improves the product significantly because the narrative contains hundreds of lines, the user may be overwhelmed and lose patience.
  * Highlights: The console display a few lines of narrative each time and wait for the user's interaction to continue.
    Moreover, the screen is cleared each time when users press to continue (compatible for Mac, Windows and Linux).
    This provides a more engaging experience.

* **Enhancements to existing features**: Implemented Encryption to `Notes` feature (Pull request [\#238](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/238))
  * What it does: Encrypted the Notes files that users write
  * Justification: This feature improves the product significantly because the users will not be able to mess with the note file and cause crash.
  * Highlights: With "DES" encryption algorithm, the program is able to prevent users from cheating and
    identify if the user has modified the game data file.


* **Project management**:
  * Set up meeting for the team.
  * Publish [Releases](https://github.com/AY2122S1-CS2113-T14-1/tp/releases) with JAR file. 


* **Community**:
  * reviewed PRs and resolved merge conflicts 
  

* **Documentation**:
  * JavaDoc:
    * Added headers comments to improve code readability. (Pull request [\#264]( https://github.com/AY2122S1-CS2113-T14-1/tp/pull/264))
  * User Guide:
    * Added local storage component explaining how the game progress is saved. (Pull request [\#264]( https://github.com/AY2122S1-CS2113-T14-1/tp/pull/264))
  * Developer Guide:
    * Added Storage component explaining how to read and write encrypted files. (Pull request [\#218](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/260))
    * Added Storage class diagram explaining the structure among Added `Encrypted` class, `GameFileManager` class and `GameDataFileDecoer` class. (Pull request [\#218](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/260))
    * Added Sequence class diagram explaining how the `GameDataFileDecoer` class extract the game progress index out of the encrypted file. (Pull request [\#218](https://github.com/AY2122S1-CS2113-T14-1/tp/pull/260))