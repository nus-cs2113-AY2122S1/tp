# Developer Guide

PUT LINKS TO ALL PARTS HERE

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
* [AB3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html) - Reference
* [HighWater Design Specification Document](http://www.cci.drexel.edu/seniordesign/2016_2017/HighWater/HighWaterDesignDocument.pdf) - Reference
* [FDsys System Design Document](https://www.govinfo.gov/media/FDsys_Architecture.pdf) - Reference

## Introduction

### Purpose
This document specified the architectural and software design decisions in the implementation of the Smart Inventory
Tracking and Updating System (SITUS).

### Audience
The intended audience for this document are developers looking to introduce new functionalities based on their needs.


## Design

### Architecture

The **_Architecture Diagram_** above explains the high-level design of the application.

The App consists of 6 major components:
* `UI`: Class that deals with the interaction with the user.
* `Main`: The main body of the application.
* `Parser`: Class that processes inputs and executes commands.
* `Commands`: A set of classes covering the functionalities of the App.
* `IngredientList`: Class that holds the information of ingredients.
* `Storage`: Reads data from, and writes data

**Interaction between architecture components**

The _sequence diagram_ below shows how the components interact with each other given a scenario where the user 
enters the input `add n/carrot a/1 e/2021-11-12`





{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Alerts

On startup, the `Alerts` class is instantiated and its `getAlerts()` method is called. There are 2 types of alerts:

1. `getExpiryAlerts()` - gets a list of expiring products 
2. `getLowStockAlerts()` - gets a list of running low products

These 2 alert functions are called in order, with the list obtained from the ingredient list loaded from storage. The process is as follows:

Step 1: The User launches the application and the `Alerts` class is instantiated.

Step 2: The application calls the `getAlerts()` method, which executes `getExpiryAlerts()` and `getLowStockAlerts()`.

Step 3: The `getExpiryAlerts()` method creates an instance of the `IngredientList` class when trying to get an instance of it.

Step 4: The `IngredientList` class calls on the `storage` class to `load()` the saved data, if it exists.

Step 5: The `IngredientList` class loops through the loaded list to obtain a list of expiring ingredients and returns it to `getExpiryAlerts()`. If there is no data stored, then it returns nothing.

Step 6: The `getLowStockAlerts()` method is called, the `IngredientList` instance returns a list of low stock ingredients. If there is no data, it returns nothing.

Step 7: The `getAlerts()` method sends the data to the UI to display to the user.





## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
