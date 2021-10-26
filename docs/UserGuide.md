# User Guide

## Introduction

**ConTech** is a **Command Line Interface (CLI) application for managing computing-related contacts**. 
If you are a computing professional that often uses the command line for work, **ConTech** can get your contact 
management tasks done faster than any traditional GUI app. 

With our various [features](#features) such as `add`, `edit`, `view`, and other commands, you can easily perform such 
actions on your **ConTech** Book in a quick and efficient manner. **ConTech** is optimised for computing-related contacts 
such as saving a colleague's GitHub username or even mass-loading from a list of contacts. **ConTech** aims to improve your 
efficiency at work by cutting down the time required to look up for various usernames when working with colleagues. 

* **[Quick Start](#start)**
* **[Features](#features)**
    * **[Contact flags : `-flag`](#flag)**
    * **[Viewing help : `help`](#help)**
    * **[Adding a contact : `add`](#add)**
    * **[Listing all contacts : `ls`](#list)**
    * **[Viewing a contact : `view`](#view)**
    * **[Viewing personal contact : `me`](#me)**
    * **[Deleting a contact : `rm`](#delete)**
    * **[Editing a contact : `edit`](#edit)**
    * **[Searching for a contact : `search`](#search)**
    * **[Importing a contact : `import`](#import)**
    * **[Exiting the program : `exit`](#exit)**
    
* **[FAQ](#faq)**
* **[Command Summary](#summary)**

<a name="start"></a>
## Quick Start

1. Ensure that you have Java `11` or above installed in your Computer.
1. Download the latest version of `contech.jar` from [here](https://github.com/AY2122S1-CS2113T-T09-1/tp/releases).
1. Copy the `contech.jar` file to the folder you want to use as the _home folder_ for **ConTech**.
1. Open your desired _Command Line Interface_ from the folder with `contech.jar` and enter the following code: 
`java -jar contech.jar`.
1. Type a command in the command box and press _Enter_ to execute it. e.g., typing `help` and pressing _Enter_ will 
   display the help message. <br>
   Some example commands you can try:
   * `add -n Alex Lee -g alexlee -e alex.lee@contech.sg -te alexl33`: Adds a contact named `Alex Lee` 
     to the ConTech Book.
   * `ls`: Lists all contacts.
   * `view 1`: Displays all the details of the contact at index `1`.   
   * `rm 1`: Deletes the contact at index `1` in the list.
   * `exit`: Exits the program.

1. Refer to the [Features](#features) below for details of each command.

<a name="features"></a>
## Features 

<a name="flag"></a>
### Contact flags: `-flag`
When using commands such as `add`,`edit` and `search`, the user can specify details with the use of flags in the form `-FLAG`.

ConTech currently supports six `FLAGS` and has in built checkers to check if the format of the `DETAILS` fulfill
the fields. These `flags` include:
- `-n`: for your contact's name
- `-g`: for your contact's GitHub account username
- `-l`: for your contact's LinkedIn handle
- `-te`: for your contact's Telegram handle
- `-tw`: for your contact's Twitter handle
- `-e`: for your contact's email address


<a name="add"></a>
### Adding a contact: `add`

Adds a specified contact to the ConTech Book.

Usage: `add -n NAME -g GITHUB -l LINKEDIN -te TELEGRAM -tw TWITTER -e EMAIL`

- ConTech is robust and allows you to specify your fields in any order.
- The `-n` name field is the only compulsory field required to add a contact, the rest are optional.
- Up to five other `FLAGS` and `DETAILS` can be specified
- ConTech also has built-in duplicate checkers, in case you accidentally add the same contact twice.

Expected outcome of usage:

```
add -n Le Zong -g lezongmun -e lezongmun@gmail.com -te lezongg
____________________________________________________________
ConTech has added the specified contact:
Name:     Le Zong
Github:   github.com/lezongmun
Email:    lezongmun@gmail.com
Telegram: t.me/lezongg

You now have 1 contact(s).
____________________________________________________________
```

<a name="ls"></a>
### Listing all contacts: `ls`

Lists all the saved contacts with the index and contact name.

Usage: `ls`
* The indexes start from 0, this is done as computing professionals 
 are more familiar with index start from 0.
 
Expected outcome of usage:

```
ls
____________________________________________________________
ConTech has 7 contacts stored.
Here's the list :
____________________________________________________________

0. Andre
1. Marcus
2. Le Zong
3. Ashraf
4. Mayank
5. Jim
6. Akshay
____________________________________________________________
```

<a name="view"></a>
### Viewing a contact: `view`
Displays all the details saved to a contact in the ConTech Book.

Usage: `view INDEX`
* Displays all the details of the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.

Expected outcome of usage:
```
view 0
____________________________________________________________
0. Alex Lee
   Github:   github.com/alexlee
   Email:    alex.lee@contech.sg
   Telegram: t.me/alexl33
____________________________________________________________
```
<a name="me"></a>
### Viewing personal contact: `me`
Displays all the details saved to the personal contact in the ConTech Book.

Usage: `me`
* Displays all the details of the personal contact.

Expected outcome of usage:
```
me
____________________________________________________________
Name:     Marcus Bo
Github:   github.com/marcusbohre
Email:    marcus.bo@contech.sg
Telegram: t.me/m4rcusb0
____________________________________________________________
```

<a name="delete"></a>
### Deleting a contact: `rm`
Removes the contact with a specified index from the ConTech Book. Before any deletion, ConTech Book will
display the details of the specified contact to be deleted, and prompt user to confirm deletion.

Usages:

`rm INDEX`
* Deletes the contact with the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.
  
`rm all`
* Deletes all contacts in the ConTech Book

Expected outcome of usages:
* Remove selected contact:
```
rm 0
____________________________________________________________
Delete this contact?  (y/n)
0. Alex Lee
Github:   github.com/alexlee
Email:    alex.lee@contech.sg
Telegram: t.me/alex133
____________________________________________________________
```
```
y
____________________________________________________________
ConTech has removed the specified contact: Alex Lee
You now have 2 contact(s).
____________________________________________________________
```

* Remove all contacts:

```
rm all
____________________________________________________________
Delete all of your contacts?  (y/n)
____________________________________________________________
```
```
y
____________________________________________________________
ConTech has removed all 2 of your contact(s).
____________________________________________________________
```

<a name="edit"></a>
### Editing a contact : `edit`

Edits the details of a specified contact in the ConTech Book.

Usage: `edit INDEX -FLAG DETAIL`

- Up to six `FLAGS` and `DETAILS` can be specified in any order
- The index refers to the index number shown in the displayed contact list.
- The index must be within the range of indexes displayed in the contact list (zero-based).
- ConTech also has built-in duplicate checkers and will alert you if there is already a contact with the same details when editing.


Expected outcome of usage: 
```
edit 0 -n John Lee -g johnlee -te johnlee123 -e john.lee@contech.sg
____________________________________________________________
0. John Lee
   Github:   github.com/johnlee
   Email:    john.lee@contech.sg
   Telegram: t.me/johnlee123
____________________________________________________________
```

<a name="search"></a>
### Searching for a contact: `search`

Search the ConTech Book for a contact whose details contain the specified query.

Usage: `search [-FLAG] QUERY`

- Only one flag can be specified. 
- If no flag is specified, the default search field is contact name.
- The search query is not case-sensitive.
- All contacts with the specified field containing the search query will be displayed in view format.

Expected outcome of usage:
```
search -g lee
____________________________________________________________
0. Alex Lee
   Github:   github.com/alexlee
   Email:    alex.lee@contech.sg
   Telegram: t.me/alexl33
____________________________________________________________

____________________________________________________________
1. John Lee
   Github:   github.com/johnlee
   Email:    john.lee@contech.sg
   Telegram: t.me/johnlee123
____________________________________________________________
```

<a name="import"></a>
### Importing contacts: `import`
Import contacts from a CSV text file into your contact list. 

Usage: `import`

- Data to be imported should be stored in the file `data/import.txt`.
- Invalid or corrupt data will not be imported into the contact list and will be discarded.
- Duplicate contacts will trigger a confirmation message, allowing the user to choose if the duplicate contact should be added.

Expected outcome of usage:

{to be added}

<a name="help"></a>
### Viewing help: `help`
Displays a help message explaining what commands are available and each input format.

Usage: `help`

Expected outcome of usage:

```
help
____________________________________________________________
add: ConTech adds the specified contact with provided parameters.
 Parameters: -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER
 Note: Parameters need not be in order and are optional except for NAME.
 Example: add -n John Doe -g johndoecoder -e john@email.com -te johndoe

ls: Displays the name of all saved contacts.
 Example: ls

edit: Edit any parameter in an existing contact.
 Parameters: INDEX -n NAME -g GITHUB -e EMAIL -te TELEGRAM -l LINKEDIN -tw TWITTER
 Note: Parameters need not be in order and are optional except for INDEX.
 Example: edit 1 -e john.doe@email.com

view: Displays all details for index specified contact.
 Parameter: INDEX
 Note: Index starts from 0.
 Example: view 2

rm: Deletes the index specified contact.
 Parameter: INDEX
 Note: Index starts from 0.
 Example: rm 0

search: Search for a contact containing a specified query.
 Note: Flags may be used to specify a detail type to search.
       If no flag is specified, contact name is searched by default.
 Example: search -g QUERY

help: Displays application usage instructions.
 Example: help

import: Imports contacts from a CSV Text File.
 Note: Please ensure that data is saved in data/import.txt
 Example: import
____________________________________________________________
```


<a name="exit"></a>
### Exiting the program: `exit`
Exits the application.

Usage: `exit`

Excepted outcome of usage: `exit`

```
exit
____________________________________________________________
ConTech will now shutdown.
We hope you have enjoyed using it.
____________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: 



## <a name="summary"></a>Command Summary

Below is a concise summary of frequently used commands in ConTech.
The command format and examples are provided for each command for
a quick view when using the application.

Action | Command Format | Example
--- | --- | --- | 
List all valid commands and usage | `help` | `help`
Add a new contact| `add <INDEX> -n <NAME> -g <GITHUB> -e <EMAIL> -te <TELEGRAM> -l <LINKEDIN> -tw <TWITTER>` | `add -n Marcus` <br>`add -n John Doe -g johndoecoder -e john@email.com -te johndoe`<br/>
List all contacts | `ls` | `ls`
View a contact| `view <INDEX>` | `view 2`
Edit a contact| `edit <INDEX> -n <NAME> -g <GITHUB> -e <EMAIL> -te <TELEGRAM> -l <LINKEDIN> -tw <TWITTER>` | `edit 1 -e john.doe@email.com` <br>`edit 0 -n Tan -g tanned -te tantan`<br/>
Delete contact fields| `<COMMAND>` | `<EXAMPLE>`
Delete a contact | `rm <INDEX>` | `rm 1`
Search for a contact| `search <OPTIONAL_FLAG> <QUERY>` | `search Ashraf` <br>`search -g revflash`<br/>
Import contacts from .txt file|`import` | `import`
Exit ConTech | `exit` | `exit`
