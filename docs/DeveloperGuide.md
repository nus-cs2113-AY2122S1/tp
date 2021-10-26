# Developer Guide

* [Acknoledgements](#acknowledgements)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
* https://www.baeldung.com/java-testing-system-out-println
* https://github.com/fastily/jwiki

##Architecture

(Some architecture diagram)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Choose the game content

Once the game starts, the main class instantiates a Content object containing a string, which is set to a default 
value (lorem ipsum paragraph). The string can only be changed through the setContent() method.

Users can input the 'content' command to start the text selection.

There are 3 types:

    1. Opening paragraphs from famous books
    2. Custom Wikipedia article
    3. Random words

The following UML diagram illustrates the way content selection works in the program.

![](uml/Content.png)

There only exists one private content string for all sessions. Each time a set method is called, the string is changed 
depending on the choices that the user made throughout the process. Whenever the user starts a game, the getContent() 
method is called and the text is set accordingly.

### Word Limit Game

Once the CommandFactory reads a 'word' command, a word limit game will begin.

Gonna to change this .puml diagram later - zhansen

![](uml/WordLimitMode.png)

### Time Limit Game

### Storage

## Product scope

Our product is a typing game, intent to provide enjoyment for people who are familiar with the CML.


### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|customize the time limit to finish a game|train myself to type faster|
|v1.0|user|customize the word limit, in multiples of 100|have an optimal gaming experience by being able to choose my preferred length of text for the game|
|v1.0|user|choose text from famous books, randomly generated text or customized text for my game|make my gaming experience more fun and fulfilling|
|v2.0|user|view my game statistics after the game finishes|know how I fared|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}
### Animation


## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
