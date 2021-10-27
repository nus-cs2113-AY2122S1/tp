# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

### Notes about the command format:

* Words in UPPER_CASE are parameters to be supplied by the user.  
e.g. in `history -g GAME_MODE`, `GAME_MODE` is a parameter which can be used as `history -g time`.
* Items in square brackets are optional.  
e.g. `-g GAME_MODE [-n NUMBER_OF_RECORDS]` can be used as `-g time -n 9` or as `-g time`
* Parameters can be in any order.  
e.g. if command specifies `history -g GAME_MODE [-n NUMBER_OF_RECORDS]`, `history [-n NUMBER_OF_RECORDS] -g GAME_MODE`  
is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, 
only the first occurrence of the parameter will be taken  
e.g. if you specify `history -g time -g time -n 10`, only `history -g time -n 10` will be taken. 
* For commands which have a help parameter, if `-h` is among the parameters provided, 
all other parameters will be ignored and the guide for that command will be displayed.
e.g. `history -g time -h -n 10` will be interpreted as `history -h`.

### Usage
### Open Game

### {Feature name}: {`Keyword`}
Description of feature  
Format: {format of command}
* Explanation 1 of format
* Explanation 2 of format
<!-- -->
Examples
* command example 1
* command example 2
<!-- -->

### View past records: `history`
View past game records.  
Format: `history -g GAME_MODE [-n NUMBER_OF_RECORDS] [-h]`
* NUMBER_OF_RECORDS defaults to all records if not provided
* Possible arguments for GAME_MODE are - `word` or `time`
<!-- -->
Examples
* `history -g time -n 10`
* `history -g word`
<!-- -->

### Clear past records: `clear` 
Clear all past game records
Format: `clear [-g GAME_MODE] [-h]`
* GAME_MODE defaults to `all` if not provided
* Possible arguments for GAME_MODE are - `word`, `time` or `all`
<!-- -->
Examples
* `clear -g time`
* `clear`
<!-- -->

### Saving the data
Typists automatically stores the updated game records into text files when gamer enters the 
`bye` command to exit Typists.


### !Warning: Editing the data file
Typists is a game application and only game records will be stored. 
Gamers should not edit the data it will manipulate the integrity of the records.
In the event where the data is editted and the wrong format is inputted, the game will crash when it tries to read the file. 
The only way to make it run smoothly again is to delete the text files, hence losing all the game data.



### `Keyword` - Describe action

List of the keywords are shown below:

`content`-Set the content

`new`-Enter a word-limited game mode

>Exit word-limited game mode command: `Exit`

`time`-Enter a time-limited game mode

`error`-Enter a error detecting game mode

`bye`-Exit the program.

### Showing summary of a game
Statistics of game is shown automatically after a game is finished.

## Example of usage:

>`new`
```
| Enter how many words you want the game to run: 
```
>`12`
```
lorem ipsum is simply dummy
```
>`lorem ipsum is simply dummy`
```
Your progress:5/12
text of the printing and
```
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
