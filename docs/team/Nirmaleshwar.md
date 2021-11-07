# Nirmaleshwar Sathiya Moorthi
## Project Portfolio

---
## Overview
Stonks XD is an expense managing software that aims to simplify the process of keeping track of one's finances.
The target users for this app are computing students that travels frequently and prefer logging their finances.
The app is able to track your daily expenses, set and adjust your spending limits and give advice based on daily expenses.
It is also able to give visual representations of financial data through bar graphs with currency conversion capabilities.
---

### Summary of Contributions

* **Classes:** `Currency Conversion` & `Currency Manager`
<br></br>
    * **Purpose:** Handles all currency related functions that support the execution of commands such as `list_curr`, `set_curr` and `check_curr`.
<br></br>
    * **Justifications:** Allows users to use Stonks XD in their native currency thus, giving them a better grasp of their finances and ensuring a more personalised experience.
<br></br>
    * **Significance:** This enhancement requires proper understanding of what needs to be shown, the indexing, different enums, format specifiers like 2 decimal place for money related entries.

  <br>

* **Feature:** `check_curr`
<br></br>
  * **Purpose:** Allow users to check the current currency mode of Stonks XD.
<br></br>
  * **Justifications:** Sometimes user can forget what currency mode the entries are in e.g. when user re-opens Stonks XD. This commands allows the user to check the currency type at any point during execution. 
<br></br>
  * **Significance:** Requires tracking the currency state at all times, even when switching to other modes or after saving the program.

  <br>

* **Feature:** `list_curr`
<br></br>
  * **Purpose:** Allow users to list available currency conversion modes.
<br></br>
  * **Justifications:** When using Stonks XD for the first time, users can check the available types at their convenience without referring to the UG. 
<br></br>
  * **Significance:** Used an array store and loop through enum class of currency types and print them out. If newer types are added, changes have to be made only to the enum class.

  <br>

* **Feature:** `set_curr`
<br></br>
  * **Purpose:** Converts all entries in Stonks XD to the given currency type.
<br></br>
  * **Justifications:** Users can visualize their expenses or income in their native currency. Since the purpose of Stonks XD is to not only track but also to save money for users, converting to a currency they are familiar with will enable them to achieve the aforementioned goals.
<br></br>
  * **Significance:** The currency types are stored in an enum class and when a user requests a change, it obtains all the entries from `income`, `expense`, `budget`, `balance` and multiplies them all with the correct exchange rate. If returning to default (SGD), then the original entries are returned.

  <br>

* **Feature:** `add_ex` & `add_in` commands.
<br></br>
    * **Purpose** Adds user expense and income entries to their appropriate lists.
<br></br>
    * **Justifications:** Users can manage two separate lists and interact with them by adding items.
<br></br>
    * **Significance:** It is the most instrumental function to the tracker, so it involves managing the different attributes that the user can parse.

  <br>

* **Feature:** `del_ex`& `del_in` commands.
<br></br>
    * **Purpose:** Allows users to delete entries from their appropriate lists.
<br></br>
    * **Justifications:** Users might have unwanted or erroneous entries that they might wish to delete.
<br></br>
    * **Significance:** Similar to the add function, it is instrumental to the tracker. Ensuring the deletion of the correct entry is a bit more crucial. For this purpose, `parser` is made a lot more strict in what is acceptable as a proper index for deletion.

  <br>

* **Link to code contribution:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Nirmaleshwar&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Nirmaleshwar&tabRepo=AY2122S1-CS2113T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
---
###Enhancement to existing features:

###Contributions to the UG:

* 