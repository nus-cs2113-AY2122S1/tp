# User Guide

## Introduction

Libmgr is a desktop app for managing the inventory of libraries, optimised for use via a Command Line Interface (CLI).
Designed for fast typists, it can help to augment the day-to-day tasks of a librarian and can help them to get tasks
done in an efficient manner.

- [Quick Start](#quick-Start)
- [Features](#features)
    - [Adding an item: `add`](#adding-an-item)
        - [Adding an audio item: `add a`](#adding-an-audio-item)
        - [Adding a book item: `add b`](#adding-a-book-item)
        - [Adding a magazine item: `add m`](#adding-a-magazine-item)
        - [Adding a video item: `add v`](#adding-a-video-item)
        - [Adding a miscellaneous item: `add i`](#adding-a-miscellaneous-item)
    - [List items: `list`](#list-items)
    - [Listing items that are due today: `deadline today`](#listing-items-that-are-due-today)
    - [Listing items that are overdue: `deadline overdue`](#listing-items-that-are-overdue)
    - [Search items: `search`](#search-items)
    - [Reserve items: `res`](#reserve-items)
    - [Un-reserve items: `unres`](#unreserve-items)
    - [Loan items: `loan`](#loan-items)
    - [Return items: `return`](#return-items)
    - [Removing an item: `rm`](#removing-an-item)
    - [Editing an item: `edit`](#editing-an-item)
    - [Exiting the program: `exit`](#exiting-the-program)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java `11` or above installed.
2. Down the latest version of `libmgr.jar` from [here](https://github.com/AY2122S1-CS2113-T16-1/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Libmgr.
4. Open a terminal/command prompt and run `java -jar libmgr.jar` to start the application.

## Features

##### Notes about command formats

> ℹ️ Words in `UPPER_CASE` are parameters to be supplied by the user.<br>
e.g. In `add b t/TITLE ...`, `TITLE` is a parameter that can be used like `add b t/Don Quixote ...`

> ℹ️ Parameters can be in any order<br>
e.g. `add a t/TITLE i/ID a/ARTIST d/DURATION` can be used as `add a a/ARTIST i/ID t/TITLE d/DURATION`

> ⚠️ Parameters must be separated with a space<br>
e.g. For `add b t/TITLE i/ID ...`, entering `add a t/The Great Gatsbyi/0125` is invalid

> ⚠️ Command word must be in lower case
e.g. For `add b t/TITLE i/ID ...`, entering `ADD a t/The Great Gatsby i/0125` is invalid

### Adding an item
Add a new item to the catalogue. Items can fall under one of five categories: Audio, Books, Magazines, Videos and Miscellaneous.

#### Adding an audio item
Add a new audio item to the catalogue.

Format: `add a t/TITLE i/ID a/ARTIST d/DURATION`

Example: `add a t/Thriller i/5920 a/Michael Jackson d/42:16`

Expected Output:
```
> add a t/Thriller i/5920 a/Michael Jackson d/42:16
  (+) Added new audio item to the catalogue
  [A] 5920 | AVAILABLE | Thriller | Michael Jackson | 42:16
```

#### Adding a book item
Add a new book item to the catalogue.

Format: `add b t/TITLE i/ID a/AUTHOR`

Example: `add b t/To Kill a Mockingbird i/2551 a/Harper Lee`

Expected Output:
```
> add b t/To Kill a Mockingbird i/2551 a/Harper Lee
  (+) Added new book item to the catalogue
  [B] 2551 | AVAILABLE | To Kill a Mockingbird | Harper Lee
```

#### Adding a magazine item
Add a new magazine item to the catalogue.

Format: `add m t/TITLE i/ID p/PUBLISHER e/EDITION`

Example: `add m t/Time Magazine i/58720a p/Time USA e/oct252021`

Expected Output:
```
> add m t/Time Magazine i/58720a p/Time USA e/oct252021
  (+) Added new magazine item to the catalogue
  [M] 58720a | AVAILABLE | Time Magazine | Time USA | oct252021
```

#### Adding a video item
Add a new video item to the catalogue.

Format: `add v t/TITLE i/ID p/PUBLISHER e/DURATION`

Example: `add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes`

Expected Output:
```
> add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes
  (+) Added new video item to the catalogue
  [V] 095680 | AVAILABLE | Casino Royale | Sony Pictures | 144 minutes
```

#### Adding a miscellaneous item
For any other item that cannot be categorised into the above-mentioned ones, they can be added using this command.

Format: `add i t/TITLE i/ID`

Example: `add i t/Scrabble i/0513895`

Expected Output:
```
> add i t/Scrabble i/0513895
  (+) Added new item to the catalogue
  [-] 0513895 | AVAILABLE | Scrabble
```

### List items
List out all items in the catalogue.

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

### Listing items that are due today
List the loaned items that need to be returned today.

Format: `deadline today`

Example: `deadline today`

Expected Output:
```
  (+) Listing out loaned items that have to be returned today
  ========================================
  [M] 58720a | LOANED (martin TILL 31-10-2021) | Time Magazine | Time USA | oct252021
  ========================================
```

### Listing items that are overdue
List the loaned items that already overdue but haven't been returned yet.

Format: `deadline overdue`

Example: `deadline overdue`

Expected Output:
```
  (+) Listing out loaned items that are overdue
  ========================================
  [B] 2551 | LOANED (johnsmith TILL 01-01-2021) | To Kill a Mockingbird | Harper Lee
  ========================================
```

### Listing items that are due on a specific date
List the loaned items that are due on a specific date given in the input.

> ℹ️ `DUE_DATE` must be in the format of `dd-mm-yyyy` in order to be valid

Format: `deadline d/dd-mm-yyyy`

Example: `deadline d/03-11-2021`

Expected Output:
```
  (+) Listing out loaned items that are due this date: 03-11-2021
  ========================================
  [M] 58720a | LOANED (Sam TILL 03-11-2021) | Time Magazine | Time USA | oct252021
  ========================================
```


### Search items
Search items by ID, Title, Status, and Category.

Status must be one of "AVAILABLE", "LOANED" or "RESERVED".

Category must be one of "Audio", "Video", "Book", "Magazine".

Any items matching more than one keyword will be listed, those items matching the search criteria more closely will be listed first.

Format: Subset of `search i/ID t/TITLE s/STATUS c/CATEGORY`

Example: `search s/LOANED c/Book`

Expected Output:
```
  (+) Here are the searching results in library
  ========================================
  [B] 123 | LOANED (Silin TILL 12-12-2021) | The Hunger Games | Collins
  [B] 2551 | AVAILABLE | To Kill a Mockingbird | Harper Lee
  ========================================
```

### Reserve items
Reserves an item for a specific person.

Format: `res i/ID u/USERNAME`

Example: `res i/2551 u/johnsmith`

Expected Output:
```
> res i/2551 u/johnsmith
  (+) You have successfully reserved an item:
  [B] 2551 | RESERVED (johnsmith) | To Kill a Mockingbird | Harper Lee
```

### Unreserve items
Make a previously reserved item available again.

Format: `unres ID`

Example: `unres 2551`

Expected Output:
```
> unres 2551
  (+) Item unreserved:
  [B] 2551 | AVAILABLE | To Kill a Mockingbird | Harper Lee
```

### Loan items
Loan out an item to an individual until a specific due date.

> ℹ️ Only `AVAILABLE` items can be loaned out

> ℹ️ Items that have been previously reserved by an individual can only be loaned out to the same individual

> ℹ️ `DUE_DATE` must be in the format of `dd-mm-yyyy` in order to be valid

Format: `loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)`

Example: `loan i/2551 d/12-11-2021 u/johnsmith`

Expected Output:
```
> loan i/2551 d/12-11-2021 u/johnsmith
  (+) Item has been loaned out:
  [B] 2551 | LOANED (johnsmith TILL 12-11-2021) | To Kill a Mockingbird | Harper Lee
```

### Return items
Mark a previously loaned item as returned, making it available again.

> ℹ️ Only `LOANED` items can be returned. Unsuccessful message will be displayed when 
> you return items that are not loaned out.

Format: `return ID`

Example: `return 2551`

Expected Output:
```
> return 2551
  (+) Item has been returned:
  [B] 2551 | AVAILABLE | To Kill a Mockingbird | Harper Lee
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

Expected Output:
```
> exit
See you soon!
```

## FAQ

**Q**: What happens if I mistype a command not recognised by the program?

**A**: A message will be shown saying that the program does not recognise your command.
You can then key in a command again.

## Command Summary

|Action|Purpose|Format and Examples|
|---|---|---|
|Add (Audio)|Add an audio item to the catalogue|`add a t/TITLE i/ID a/ARTIST d/DURATION`<br>E.g. `add a t/Thriller i/5920 a/Michael Jackson d/42:16`|
|Add (Book)|Add a book item to the catalogue|`add b t/TITLE i/ID a/AUTHOR`<br>E.g. `add b t/To Kill a Mockingbird i/2551 a/Harper Lee`|
|Add (Magazine)|Add a magazine item to the catalogue|`add m t/TITLE i/ID p/PUBLISHER e/EDITION`<br>E.g. `add m t/Time Magazine i/58720a p/Time USA e/oct252021`|
|Add (Video)|Add a video item to the catalogue|`add v t/TITLE i/ID p/PUBLISHER e/DURATION` <br> E.g. `add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes`|
|Add (Miscellaneous)|Add an item that cannot be categorised under any type|`add i t/TITLE i/ID` <br> E.g. `add i t/Scrabble i/0513895`|
|Deadline|View items due to be returned|`deadline [overdue\|today]` <br> E.g. `deadline today`|
|Edit|Edit existing items within the catalogue|`edit ID MARKER/new attribute` <br> E.g. `edit 123 t/Harry Potter`|
|Exit|Quit the program|`exit`|
|List (all items)|Lists all items within the catalogue|`list`|
|Loan|Loan out an item to someone|`loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)` <br> E.g. `loan i/2551 d/12-11-2021 u/johnsmith`|
|Remove|Remove an item from the catalogue|`rm ID` <br> E.g. `rm 095680`|
|Reserve|Reserve an item for a specific person|`res i/ID u/USERNAME` <br> E.g. `res i/2551 u/johnsmith`|
|Return|Mark an item as returned and available for loan again|`return ID` <br> E.g. `return 2551`|
|Search|Search for items in the catalogue based on their attributes|`search MARKER/attribute` <br> E.g. `search c/Book t/Junglebook`|
|Un-reserve|"Un-reserve" an item and mark as available again|`unres ID`<br> E.g. `unres 2551`|
