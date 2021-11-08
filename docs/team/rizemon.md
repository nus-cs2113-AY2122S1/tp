# Project Portfolio Page - Decodex

## Overview

This is a project in accordance to the fulfilment of the Software Engineering & Object-Oriented Programming (`CS2113T`) module.

I am Tan Jia Le, one of the contributors for `Decodex`.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to perform rapid encoding, decoding, encryption, decryption of data with ease and without any programming needed.

## Summary of Contributions
Below are my contributions to the `Decodex`

### Features/Functionalities

1. Added the `input` command (Formally known as the `data` command):
   1. What it does: Allow the user to enter the data that they wish to execute the modules/recipes on.
   2. Related pull requests: [#52](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/52), [#112](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/112)
   3. Underlying components that were implemented along with it: `Data` class ([#32](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/32)), `DataManager` class ([#34](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/34), [#46](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/46))

2. Added the `reset` command:
   1. What it does: Allow the user to revert any changes done to the data that they first inputted.
   2. Related pull requests: [#65](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/65)

3. Added the `RotEncoder` (Rotational Cipher) module:
   1. What it does: Allow the user to perform the rotational cipher on the current data.
   2. Related pull requests: [#148](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/148)

4. Added the `recipe select`, `recipe push`, `recipe pop`, `recipe reset` commands:
   1. What they do: Allow the user to select a recipe and perform modifications to it.
   2. Related pull requests: [#190](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/190), [#192](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/192), [#194](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/194), [#196](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/196)

5. Added the `help` command:
   1. What it does: Allow the user to view the syntax and purpose of the commands.
   2. Related pull requests: [#226](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/226)

Code contributed: more than 2000 lines of code ([Reposense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=rizemon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=rizemon&tabRepo=AY2122S1-CS2113T-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false))

### Enhancements to Existing Features

1. Wrote tests for `RecipeManager` class: [#171](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/171)
2. Added capabilities for `RecipeManager` to make changes to a particular selected recipe: [#171](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/171)
3. Decoupled `Storage` class from the `RecipeManager` class: [#250](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/250)
4. Added naming restrictions to recipes using Regex: [#206](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/206)

### Review/mentoring contributions
1. Reviewed teammates' PRs: [85 out of 187 PRs](https://github.com/AY2122S1-CS2113T-T10-3/tp/pulls?q=is%3Apr+is%3Aclosed+-author%3A%40me+commenter%3A%40me+reviewed-by%3A%40me) (Also ranked #2 on [tP comments dashboard](https://nus-cs2113-ay2122s1.github.io/dashboards/contents/tp-comments.html))
2. Reported bugs/comments for other teams: ([1](https://github.com/rizemon/ped/issues/5), [2](https://github.com/rizemon/ped/issues/12), [3](https://github.com/rizemon/ped/issues/13), [4](https://github.com/rizemon/ped/issues/7), [5](https://github.com/rizemon/ped/issues/1))

### [User Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/UserGuide.html) 
1. Created all example screenshots using [`carbon`](https://carbon.now.sh/) and maintained them.
2. Added documentation for `input` and `reset` command: [#118](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/118)
3. Added list of supported modules: [#243](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/243)
4. Added section on understanding the application's prompt: [#243](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/243) 

### [Developer Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/DeveloperGuide.html)
1. Updated [`AboutUs.md`](https://ay2122s1-cs2113t-t10-3.github.io/tp/AboutUs.html) to point to respective PPP pages.
2. Created most of the class and sequence diagrams using [`PlantUML`](https://plantuml.com/): [#229](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/229)
3. Added `Acknowledgements` and `Setting Up the Project` sections: [#174](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/174)
4. Added implementation details for all `recipe` commands: [#233](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/233/files), [#322](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/322)
