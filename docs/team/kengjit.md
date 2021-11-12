# Chan Keng Jit (kengjit) - Project Portfolio Page

## Product Overview
FitNUS Tracker is a desktop app for tracking daily food intake to help users keep a healthy lifestyle. 
FitNUS is specially made for Computing Students living in University Town.


## Summary of Contributions


- **Feature: Adding Entries**
  - What it does: Allows the user to add and track their food intake. They can do so either by choosing from our
  extensive database of foods, or create their own custom food.
  - Highlights: This enhancement was challenging as it was affected by many other changes made in FitNUS, causing it
  to be constantly updated. Some examples include adding meal types and food types, which resulted in a massive overhaul
  of this feature.

- **Feature: Editing Entries**
  - What it does: Allows the user to edit their past food entries. They can either change it to a food from the 
  database, or to a custom food.
  - Highlights: This enhancement required a deep understanding of both the `EntryDatabase` and `FoodDatabase` classes,
  The implementation was challenging as well since it has to not only edit the entries, but also add a new food if it 
  does not exist.

- **Feature: Listing Entries**
  - What it does: Allows the user to list their past food entries. They can either list out all entries in the past day,
  week, or since inception.
  - Highlights: This enhancement required a good understanding of how entries are being logged in. The challenge comes in 
  differentiating how many entries to list out. This required the use of `EntryDatabase#getPastDaysEntryDatabase(int)`
  which returns a subset of the current `EntryDatabase`. 

<div style="page-break-after: always;"></div>
    
- **Feature: Deleting Entries**
  - What it does: Allows the user to delete a specified food entry.
  - Highlights: This enhancement required a good understanding of how entries are being logged in. The challenge when 
  implementing this feature is to ensure that the specified entry exists, and throws the proper exception otherwise.

- **Feature: Showing food intake summary**
  - What it does: Allows the user to view a summary of their past food intake. They can either view a summary of the
    past week or month. Not only does it provide useful information such as the user's average daily calorie intake, it also
  provides information about the user's most/least eaten food and a visual diagram of the user's calorie intake.
  - Highlights: This enhancement required a deep understanding of how entries are being logged in. The challenge comes in
    differentiating how many entries to include in the summary. This required the use of 
  `EntryDatabase#getPastDaysEntryDatabase(int)` and `EntryDatabase#getPastMonthEntryDatabase()` which returns a subset of 
  the current `EntryDatabase`.
  - Credits: Due to the complexity of this feature, it was implemented with the help of @siyuancheng178, which assisted
  with the visualisation of the summary diagrams.

- **Feature: Show help message**
  - What it does: Allows the user to list out all the available commands FitNUS accepts.
  - Highlights: This enhancement required a good understanding of how all the features in FitNUS operates. 


- **Code Contributed**

  Click
  [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=kengjit&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=kengjit&tabRepo=AY2122S1-CS2113T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  for the RepoSense to my code contribution.


- **Contributions to the UG:**
  - **Features**: Adding food entry, Editing existing food entry, Deleting food entry, Listing tracker entries, Searching for tracker entries with keyword
    Viewing help, User Stories.
  
- **Contributions to the DG:**

    Designed and implemented the following segments:
    - **Entry Database**
    - **Implementation**: Add Food Entry, Edit Food Entry, List Food Entry, Delete Food Entry
    - **User Stories**
    - **Instructions for manual testing**: Add Food Entry, Edit Food Entry, List Food Entry, Delete Food Entry
