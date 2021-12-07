# Shoib Loya's Project Portfolio Page

# Project: Click

## Overview
Click is an all-in-one command line task manager tool. What makes click standout as compared to other similar applications is the ability to keep track of calories count. This feature ensures that click not only takes care of your work life but also ensures that you stay healthy. Click is developed in Java (SDK: 11) and is compatible with any computer operating on Java version 11 and above. As mentioned, Click is a command line software that helps you to manage your day-to-day task. The reason Click is implemented in a command-line interface as opposed to the popular choice of graphical user interface is because Click also serves as a platform to help CS students transition from GUI platform to a CLI platform by acting as a safe and platform to commit mistakes and learn from them. 

## Contributions to code

My contribution to this project is limited to the zoom feature. Following are the commands implemented by me.

1. Add Zoom Links
This command allows the user to add zoom links for the registered the module. The implementation for this feature is fairly simple. When the user enters the command, parser extracts the module name and the zoom link. Module name is then matched against the modules stored in module.txt. If the module exists, Click writes the zoom module code and the zoom link in zoom.txt.

2. Edit Zoom Links
Similar to the previous feature. When the user enters the command, Click will try to find the module name in zoom.txt. If the module name exists in zoom.txt, Click then updates the zoom link.

3. Open Zoom Link
When the user enters the command. Parser extracts the zoom link and launches it using java.net.URL

4. Zoom List
Click extracts the information from zoom.txt and displays it in the command-line.

## Code Contributions

Link: [Contribution](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=shoibloya&tabRepo=AY2122S1-CS2113T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

## Contributions to User Guide
I wrote the zoom sections of the user guide which consists of the zoom add, zoom open and zoom list commands. 

## Contributions to Developers Guide
I wrote the zoom sections of the developer guide where I explained the architecture and flow of execution of the zoom feature using UML diagrams. 

