# Tan Hui En - Project Portfolio Page

## Overview

Ha(ppy)Bit is a desktop app aimed to empower students to achieve their goals—whether personal, academical, or health—
amidst the hectic and stressful university life, through cultivating good habits. 
Users can only run the app on a Command Line Interface (CLI).

### Summary of Contributions

* Code contribution (LoC): [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=huien77&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=huien77&tabRepo=AY2122S1-CS2113T-F14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)


* Enhancements implemented:
  * **v1.0:**
    * Create a storage file if it does not exist.
    * Allow data to be automatically imported from storage file upon start up of program.
    * Allow data to be automatically saved to storage file before exiting the program.
    * Data stored in storage file includes Goal and Habit objects.
  * **v2.0:**
    * Implemented the live export of data; storage file is updated everytime user changes or adds data.
    * Interval class was implemented so the storage component was expanded to store Interval objects.
  * **v2.1:**
    * Fixed storage related bugs found during PED.
    * Changed the storage file to read-only, therefore preventing users from making changes to the storage file, 
    intentionally or not.
    * Added JUnit testing for storage related functionalities to increase test coverage.
  * **Classes implemented:**
    * Storage.java
    * Import.java
    * ImportParser.java
    * Export.java
    * HaBitStorageException.java

    
* Contributions to the User Guide:
  * Wrote the skeleton for UG, which includes introduction, content page and the headers in content page.
  * _About Loading and Saving Data_ section.
  * _Useful Links_ section.



* Contributions to the Developer Guide:
  * Wrote the skeleton for DG, which includes introduction, content page, acknowledgement and
  headers in content page.
  * _Storage component_ section.