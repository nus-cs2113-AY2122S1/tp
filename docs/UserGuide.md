# User Guide

## Introduction

Libmgr is a desktop app for managing the inventory of libraries, optimised for use via a Command Line Interface (CLI).
Designed for fast typists, it can help to augment the day-to-day tasks of a librarian and can help them to get tasks
done in an efficient manner.

- [Quick Start](#Quick-Start)
- [Features](#Features)
    - [Adding an item: `add`](#Adding-an-item)
        - [Adding an audio item: `add a`](#Adding-an-audio-item)
        - [Adding a book item: `add b`](#Adding-a-book-item)
        - [Adding a magazine item: `add m`](#Adding-a-magazine-item)
        - [Adding a video item: `add v`](#Adding-a-video-item)
    - [List items: `list`]()
    - [Search items: `search`]()
    - [Reserve items: `res`]()
    - [(NOT IMPLEMENTED YET) Un-reserve items: `unres`]()
    - [Loan items: `loan`]()
    - [Return items: `return`]()
    - [Removing an item: `rm`](#Removing-an-item)
    - [Editing an item: `edit`](#Editing-an-item)
    - [Exiting the program: `exit`](#Exiting-the-program)
- [FAQ](#FAQ)
- [Command Summary](#Command-Summary)

## Quick Start

1. Ensure that you have Java `11` or above installed.
2. Down the latest version of `Libmgr.jar` from [here](https://github.com/AY2122S1-CS2113-T16-1/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Libmgr.
4. Open a terminal/command prompt and run `java -jar Libmgr.jar` to start the application.

## Features

##### Notes about command formats

> ℹ️ Words in `UPPER_CASE` are parameters to be supplied by the user.<br>
e.g. In `add b t/TITLE ...`, `TITLE` is a parameter that can be used like `add b t/Don Quixote ...`

> ℹ️ Parameters can be in any order<br>
e.g. `add a t/TITLE i/ID a/ARTIST d/DURATION` can be used as `add a a/ARTIST i/ID t/TITLE d/DURATION`

> ⚠️ Parameters must be separated with a space<br>
e.g. For `add b t/TITLE i/ID ...`, entering `add a t/The Great Gatsbyi/0125` is invalid

### Adding an item
Add a new item to the catalogue. Items can fall under one of four categories: Audio, Books, Magazines, Videos

#### Adding an audio item
Add a new audio item to the catalogue

Format: `add a t/TITLE i/ID a/ARTIST d/DURATION`

Example: `add a t/Thriller i/5920 a/Michael Jackson d/42:16`

Expected Output:
```
> add a t/Thriller i/5920 a/Michael Jackson d/42:16
  (+) Added new audio item to the catalogue
  [A] 5920 | AVAILABLE | Thriller | Michael Jackson | 42:16
```

#### Adding a book item
Add a new book item to the catalogue

Format: `add b t/TITLE i/ID a/AUTHOR`

Example: `add b t/To Kill a Mockingbird i/2551 a/Harper Lee`

Expected Output:
```
> add b t/To Kill a Mockingbird i/2551 a/Harper Lee
  (+) Added new book item to the catalogue
  [B] 2551 | AVAILABLE | To Kill a Mockingbird | Harper Lee
```

#### Adding a magazine item
Add a new magazine item to the catalogue

Format: `add m t/TITLE i/ID p/PUBLISHER e/EDITION`

Example: `add m t/Time Magazine i/58720a p/Time USA e/oct252021`

Expected Output:
```
> add m t/Time Magazine i/58720a p/Time USA e/oct252021
  (+) Added new magazine item to the catalogue
  [M] 58720a | AVAILABLE | Time Magazine | Time USA | oct252021
```

#### Adding a video item
Add a new video item to the catalogue

Format: `add v t/TITLE i/ID p/PUBLISHER e/DURATION`

Example: `add v t/Casino Royale i/095680 p/Sony Pictures e/144 minutes`

Expected Output:
```
> add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes
  (+) Added new video item to the catalogue
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
```

### List items
List out all items in the catalogue

Format: `list`

Example: `list`

Expected Output:
```
  (+) Listing out all items in library
  ========================================
  [M] 58720a | AVAILABLE | Time Magazine | Time USA | oct252021
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
  ========================================
```

### Search item by ID 
Search items by ID (input keyword must be exactly the same)

Format: `search i/ID` 

Example: `search i/095680`

Expected Output:
```
  (+) Here are the searching results in library
  ========================================
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
  ========================================
```

### Search item by title
Search items by title (input keyword can be part of the actual title)

Format: `search t/TITLE`

Example: `search t/Time`

Expected Output:
```
  (+) Here are the searching results in library
  ========================================
  [M] 58720a | AVAILABLE | Time Magazine | Time USA | oct252021
  ========================================
```

### Search item by status
Search items by title (input keyword must be one of AVAILABLE, LOANED, RESERVED)

Format: `search s/STATUS`

Example: `search t/AVAILABLE`

Expected Output:
```
  (+) Here are the searching results in library
  ========================================
  [M] 58720a | AVAILABLE | Time Magazine | Time USA | oct252021
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
  ========================================
```

### Reserve items
{DESCRIPTION}

Format: ``

Example: ``

Expected Output:
```
```

### Un-reserve items
{DESCRIPTION}

Format: ``

Example: ``

Expected Output:
```
```

### Loan items
{DESCRIPTION}

Format: ``

Example: ``

Expected Output:
```
```

### Return items
{DESCRIPTION}

Format: ``

Example: ``

Expected Output:
```
```

### Editing an item
Edits an attribute of an existing item in the catalogue

Format: `edit ID MARKER/new attribute`

* For Book, valid markers are: t/TITLE, i/ID and a/AUTHOR
* For Audio, valid markers are: t/TITLE, i/ID, a/ARTIST and d/DURATION
* For Magazine, valid markers are: t/TITLE, i/ID, p/PUBLISHER and e/EDITION
* For Video, valid markers are: t/TITLE, i/ID, p/PUBLISHER and d/DURATION

Examples of usage:

`edit 123 t/Harry Potter` Edits the title of an item with ID 123 to Harry Potter

Expected Output:
```
  [B] 123 | AVAILABLE | The Hunger Games | Suzanne Collins
> edit 123 t/Harry Potter
  (+) Edited item details:
  [B] 123 | AVAILABLE | Harry Potter | Suzanne Collins
```


### Removing an item
Removes an existing item from the catalogue.

Format: `rm ID`

* The item specified by `ID` will be deleted.

Example: `rm 095680`

Expected Output:
```
> rm 095680
  (+) Removed the following item:
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
```

### Exiting the program
Exits the program.

Format: `exit`

## FAQ

**Q**: What happens if I mistype a command not recognised by the program?

**A**: A message will be shown saying that the program does not recognise your command.
You can then key in a command again.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
