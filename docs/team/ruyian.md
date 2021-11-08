## An Ruyi

### Table of Contents
- [Overview of Product](#overview-of-product)
- [Summary of Contribution](#summary-of-contributions)
    - [Code Contribution Page](#code-contribution-page)
    - [Enhancements Implemented](#enhancements-implemented)
    - [Contributions to UG](#contributions-to-ug)
    - [Contributions to DG](#contributions-to-dg)
    - [Contributions to Team Based Tasks](#contributions-to-team-based-tasks)
    - [Contributions beyond the project team](#contributions-beyond-the-project-team)
- [Other Relevant Links](#other-relevant-links)


### Overview of Project
This product, Elderly in Your Hood, is a lightweight Command Line application for the workers at the
old folk's home to record the information of the elderly who they are servicing such as their medical 
conditions and history, their Next-of-Kin information as well as their dietary preferences.


### Summary of Contribution

#### Code Contribution Page
[click me](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ruyian&tabRepo=AY2122S1-CS2113-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


#### Enhancements implemented
1. `setdiet` : Setting of the dietary preference of the elderly
2. `viewdiet` : Viewing of the dietary preference of the elderly
3. `setbloodpressure` : Setting\Updating of the blood pressure of the elderly
4. `viewbloodpressure` : Viewing of blood pressure of the elderly
5. `addmedicalhistory` : Adding additional medical history to the elderly
6. `viewmedicalhistory` : Viewing the whole medical history of the elderly
7. `deletemedicalhistory` : Clearing the whole medical history of the elderly
8. `viewvaccination` : Viewing of the vaccination status of the elderly
9. `setvaccinated` : Setting the vaccination statuts of the elderly to be vaccinated
10. `setbirthday` : Setting of the birthday of the elderly
11. `viewbirthday` : Viewing of the birthday of the elderly
12. Used regex format to check for the inputs
13. Apply a better way to return Objects - Optional class, and amend the code accordingly
14. Define useful exception classes fit to the program

#### Contributions to UG

1. Creation of Table of Contents
2. Add hyperlinks at Table of Contents
3. Add some expected outputs to commands that are not so easy to be understood
4. Write up the UG details for all functions that I have implemented
   (vaccination, blood pressure, medical history, birthday and diet)


#### Contributions to the DG
1. Provide skeleton structure of DG
2. Amend the UML diagram
3. Explain the program architecture (interaction among classes and inheritance) in detail
4. Come with non-functional requirements

#### Contribution to the Project
1. Craft the architecture of the program as well layers of abstractions (interfaces (Hostpitalisable),
    inheritances of Elderly classes and Exception classes)
2. Think of the idea of creating the elderly catering to their risk levels
3. Improve the program structure by decoupling the content coupling of Duke execution
4. Make the code more defensive and more OOP-like
5. Amend the naming conventions of classes, methods and fields to abide by the coding styles and provide consistency 
in the whole project, as well as
to distinguish the methods that have similar names in different classes 
(e.g. deleteElderlyMedicalHistory in Elderly class and deleteMedicalHistory in ElderlyList class)
6. Add javadocs to the public methods

### Other Relevant Links

- [List of issues resolved](https://github.com/AY2122S1-CS2113-T16-2/tp/issues?q=is%3Aissue+assignee%3Aruyian)
