## Vincent's Project Portfolio Page

### Overview
ExpiryEliminator aims to help young adults living by themselves manage their ingredients and recipes they can cook based on the ingredients they have.
The application is CLI based and is suitable for users who can type quickly and accurately.

### Summary of Contributions

#### Code Contributed

- The [Reposense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabAuthor=vincentlauhl&tabRepo=AY2122S1-CS2113-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&tabType=authorship)
  page shows the amount of code I have typed in terms of functionalities, documentation, and testing.

#### Feature: Add a recipe
- *What it does* : Allows a user to add a recipe with its respective ingredients and quantities of ingredients to the Recipe List. Helps the user add 
ingredients in the recipe to the Ingredient Repository if it does not exist in the Ingredient Repository initially.
- *Justification* : This feature is one of the core feature of the product, as it allows user to record the recipe they want to cook
for easy reference in the future.
- *Highlights* : The implementation of the feature required modification to
support another feature and to make it more convenient for the user. It also required some analysis of the design, such as
preventing users from adding a recipe with no ingredients.

#### Feature: Delete a recipe
- *What it does* : Allows the user to delete a recipe from the Recipe List.
- *Justification* : This feature is another core feature of the product, as it allows user to remove a recipe if the user doesn't need to record the recipe anymore.

<div style="page-break-after: always;"></div>

#### Feature: Remove ingredients in the Ingredient Repository based on Recipe cooked
- *What it does* : Helps user automatically remove the amount of ingredients from the Ingredient Repository based on the amount of ingredients
in the cooked recipe.
- *Justification* : This feature helps the user do the manual work of removing
ingredients in the Ingredient Repository after cooking a recipe.
- *Highlights* : The design of the feature required some analysis. We considered whether we should assume users will use ingredients
that are expiring the soonest first.

#### Feature: List recipes user can cook
- *What it does* : Helps user check which recipes the user can cook based on the ingredients they have.
- *Justification* : This feature helps the user do the manual work of checking the quantities of each ingredient to determine which recipe the user 
can cook with the ingredients they currently have.
- *Highlights* : The design of the feature required some analysis. We considered whether the ingredients for a recipe is counted as sufficient if some ingredients have expired.

#### Team Based Tasks
- Managed release of `v2.0` on GitHub.
- Add Launch and Shutdown part for Instructions for manual testing. [#119](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/119).

#### Documentation
- **User Guide**
    - Added documentations for features `add recipe`, `delete recipe`, `cooked` and `list recipes i can cook`. [#86](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/86) [#114](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/114)
- **Developer Guide**
    - Added implementation details and UML diagrams for features `add recipe`, `delete recipe`, `cooked` and `list recipes i can cook`. [#67](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/67) [#119](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/119)
    - Added design details and UML diagrams for `Data` component. [#67](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/67) [#120](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/120)

#### Review Contribution
- Suggestion given to a PR. [#24](https://github.com/AY2122S1-CS2113-T16-3/tp/pull/24)
