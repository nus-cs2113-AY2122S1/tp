### Mudkip8's Product Portfolio Page

### Project: SITUS (Smart Inventory Tracking and Updating System)

The Smart Inventory Tracking and Updating System (SITUS) is a desktop app 
for tracking ingredient inventory designed for restaurant/ F&B inventory managers. 
Users interact with in through a CLI, and is written in Java.

Given below are my contributions to the code:

* **Code Contributed:** You can view the code [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=mudkip8&tabRepo=AY2122S1-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements Implemented:** 
  1. `Alerts` Feature 
     * What it does: Displays a set of alerts to the user. The alerts consist of ingredients expiring, ingredients low in stock, or both. It is displayed on startup, and when the user calls for it.
     * Rationale: With limited time and manpower, keeping track of all ingredients is tedious especially in small restaurants. This will help reduce food wastage by reducing food spoilage and overbuying.
  2. `Expire` Feature
     * What it does: Displays all ingredients expiring before a user specified date.
     * Rationale: User may not want to change their alerts command but want to see expiring ingredients. Can help in forward planning of menu creation.
  3. `Set` Feature
     * What it does: Allows users to set the thresholds for the alerts command.
     * Rationale: Changing consumer and supply patterns will affect the operation of a business, and having versatility in setting thresholds help them remain competent in various situations
  4. `Storage` System - Threshold Storage
     * What it does: Stores the threshold values in a file.
     * Rationale: Allows users to save their thresholds, and import it to another device if necessary.
     * Difficulties: The threshold is stored on the first line of the data file, followed by ingredients in the remaining lines. 
     Errors in the threshold line results in system crashing and ingredient data being corrupted. Java FileWriter cannot append to the start of the document so in the end I decided to just save it as a separate file.
  5. `Find` Feature - Duplicate Detection
     * What is it: Implemented a Set to handle duplicate user inputs within the find feature to prevent identical search terms from being searched for and displayed repeatedly.
  6. Others
     * Added special character detection to user inputs to ensure data entered is valid
     * Added exceptions to catch different input errors instead of only throwing a single type
* **User Guide:** You can find the UG [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/UserGuide.html)
  * Contributions:
    * Expire Command
    * Alerts Command
    * Set Command
* **Developer Guide:** You can find the DG [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/DeveloperGuide.html)
  * Contributions:
    * Created the `style.puml` file for the diagrams.
    * Added the system architecture diagram and details.
    * Added the description of the ingredientList component in Design
    * Described the implementation of the `Alerts` feature
* **Team Tasks:**
  * Contributions:
    * Participated in setting up of team repository
    * Maintained issue tracker
    * Created milestones
    * Ensured tasks on the tp-progress dashboard were all completed.
    * Handled releases
    * Wrote the skeletal product for the application to act as a base to develop
    * Wrote documentation for methods in code
* **Community:** Reported Bugs during PE (Dry-Run and Actual)
