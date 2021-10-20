# Developer Guide

## Contents 
* __Acknowledgements__
* __Getting Started__
* __Design & Implementation__
* __Product Scope__
  1. Target user profile
  2. Value proposition
* __User Stories__
* __Non-Functional Requirements__
* __Glossary__
* __Instructions for manual testing__ 
## Acknowledgements

* __EduRec:__ For the list of possible module mappings

## Getting started 
Refer to our user guide [here](https://placeholder.com)

## Design & implementation

<img src="images/Placeholder_person.png" width = "280"/>  

The ***Architecture Diagram*** above explains the high-level design of the App. 

### Storage

The storage component can implement the below features:

* Read the list of module mappings offered by each university from the CSV file.
* Read the list of NUS modules which can be mapped from the CSV file.
* Save both user's module mappings for each university and their selected NUS modules in text
  file and read them back into corresponding objects.

## Product scope
### Target user profile

__SEPlanner__ is targeted at Computer Engineering students in NUS planning for their Student Exchange Program (SEP). 

### Value proposition

Student Exchange is one of the most stressful and difficult things to plan for in a NUS students life. 
With multiple sources of information and a frustrating webpage to navigate, SEPlanner aims to organise 
a list of potential exchange Universities based on the users study plan, module requirements and personal preference.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see the list of possible schools for exchange|view my options for SEP|
|v1.0|new user|see the list of available NUS modules|decide on which NUS modules I want to complete during SEP|
|v1.0|beginner user|add a University to my preferred list|view the Universities that I am interested in|
|v1.0|beginner user|add a NUS Module to my preferred list|save my module preferences for the future|
|v1.0|beginner user|list down all Universities in my preferred list|keep track of my target schools for SEP|
|v1.0|beginner user|list down all NUS modules in my preferred list|keep track of the list of NUS modules I want to complete during SEP|
|v1.0|user|interact with the application on the command line in an efficient way|
|v2.0|familiar user|save my university and module information|access my information when I restart the application|
|v2.0|new user|view the program instructions|refer to them when I forget how to use the application|
|v2.0|user|find a University by name|locate a University without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition
* *Master University List* The list of all partner universities.
* *Master Module List* - The list of all NUS modules available for mapping.
* *Selected University List* - The list of partner universities the user selected.
* *Selected Module List* - The list of modules the user selected.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
