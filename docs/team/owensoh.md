# Soh Jun Han, Owen - Project Portfolio Page

## Overview
Elderly In Your Hood is a lightweight command-line (CLI) desktop application for retirement home nurses/volunteers to keep track of the elderly in their home, along with their specific medical needs, vitals and appointments with the various hospitals.

## Summary of Contributions

### Code contributed
[Link to code](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=owensoh&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=owensoh&tabRepo=AY2122S1-CS2113-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements implemented
- Adding of elderly (`addelderly`)
- Polymorphism of `Elderly` class into `LowRiskElderly`, `MediumRiskElderly` and `HighRiskElderly` 
- Adding of medicine to elderly (`addmed`)
- Adding of medical appointments to elderly (`addappt`)
- Viewing of medicine assigned to elderly (`viewmed`)
- Viewing of medical appointments assigned to elderly (`viewappt`)
- Initialisation of the preset list of all hospitals and doctors into `Hospital` and `Doctor` classes respectively
- Based on risk level of elderly, the retrieval of additional information such as hospital, condition, notes on care and doctor
  - `Hospital` and `Doctor` information are preset, allowing the user to choose from a fixed set of values
- Skeleton for the regex code used to check input. This was then reused by other team members. Then added regex functions to check input for own functions
- Relevant exceptions and error messages when invalid input is detected. 

### Contributions to the UG
Instructions for the features that he has implemented:
- `addelderly`
  - Explanation that there are three different risk levels to be had by any elderly
  - Additional information input varies with risk level
- `addappt`
- `addmed`
- `viewappt`
- `viewmed`

Writing up some Q&A.

Writing up the command summary for the features he has implemented.

### Contributions to the DG
Writing up the implementation of `addElderly()`. Drew the UML sequence diagram for this part. 
Elaborated on how classes interact with each other.

Writing up the implementation of the **polymophism** of `Elderly` class into `LowRiskElderly`, `MediumRiskElderly` and `HighRiskElderly`.
Drew the UML class diagram for this part. Elaborated on how classes interact with each other. Added design considerations.

Added non-functional requirements, added user stories. 

Miscellaneous parts: 
- Table of contents
- Product scope
- Target user profile
- Value proposition, 
- Non-functional requirements
- Instructions for manual testing

### Contributions to team-based tasks
- Adding of all the issue labels in GitHub
- Adding of all the milestones in GitHub
- Maintaining the issue tracker
- GitHub release management
- Miscellaneous parts for DG (For specific parts refer above)
- Initiated the completion of JUnit tests and assertions

### Review/mentoring contributions
- Reviewing the DG of group CS2113T-T10-2: [Click here!](https://github.com/nus-cs2113-AY2122S1/tp/pulls?q=is%3Aopen+is%3Apr+CS2113T-T10-2+)
- Reviewing the JAR and UG of group CS2113T-F12-2: [Click here!](https://github.com/owensoh/ped)
- Helped teammate to fix the bug for `setbloodpressure` function, since it appeared not to handle exceptions for invalid inputs,
as mentioned by fellow course mates during PE-D
- Creation of Demo Video

### Contributions beyond the project team
- Did heavy testing on our team's code after PE-D to check for any more remaining bugs