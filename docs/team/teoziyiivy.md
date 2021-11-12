# Teo Ziyi Ivy - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator
which can give you an idea of your current fitness level.

### Summary of Contributions

#### _New Feature_: 
* Added `WeightTracker` class and weight commands/functions/responses.
  * What it does: Handles weight commands from user allowing user to add, delete and list their weight. Also handling
  the saving of weights to a `Weight.txt` file and loading of weights from the `Weight.txt` file.
  * Justification: This feature improves the product significantly as users are able to track their 
  weights which is important for our target users who need to closely monitor and control their weight.
  * Highlights: This enhancement affected existing commands and commands to be added in the future. It required an 
  in-depth analysis of design alternatives. The implementation was challenging as well as it required changes to 
  existing commands to ensure consistency between features despite taking in entirely different parameters which was 
  `double` as compared to the existing features such as the `Fluid` and `Meal` classes which used `String`.
  * Credits: Groupmates helped flesh out the ideas for `WeightTracker` class, standardising the user inputs and features.

#### _Code Contributed_:
* [**RepoSense Link**](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=teoziyiivy&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

<div style="page-break-after: always;"></div>

#### _Documentation_:
* User Guide: 
  * Added documentation for the weight related features
* Developer Guide: 
  * Added implementation details for the weight related features
  * Added manual testing details for weight related features

#### _Community_:
* Reviewed groupmates PRs
* Reported bugs and suggestions for other teams' UG,DG and applications
