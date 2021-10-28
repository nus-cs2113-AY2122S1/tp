# ConTech User Guide

## Introduction

**ConTech** (pronounced as Contact) is a **Command Line Interface (CLI) application for managing computing-related 
contacts**. Specially designed to be optimised for use on the CLI, **ConTech** allows you to seamlessly **add**, **edit**, 
**view**, **list**, **search**, and **import** your contacts.

If you are a computing professional that often uses the command line for work, **ConTech** can get your contact 
management tasks done faster than any traditional GUI app. 

This guide serves to introduce our various [features](#features) such as `add`, `edit`, `view`, and other commands, 
and guide you to easily manage your **ConTech** Book. 

**ConTech** aims to improve your efficiency at work, be it by cutting down the time required to look up for various 
usernames when working with colleagues, or by importing hundreds of contacts with just one simple command.
<br/>

## How to use the User Guide

To make full use of the user guide, we have provided a table of contents with their hyperlinks. This would allow you 
to quickly access portions of the guide you require. 

However, for first time users, you are highly encouraged to go through the user guide in order, from the top to 
bottom. This is because the guide is also written in a specific order to allow you to maximise your understanding 
when learning how to use **ConTech**.

### Table Of Contents

* **[Quick Start](#start)**
* **[Contact flags : `-flag`](#flag)**
* **[Common notations used](#notations)**
* **[Features](#features)**
    * **[Adding a contact : `add`](#add)**
    * **[Listing all contacts : `ls`](#list)**
    * **[Viewing a contact : `view`](#view)**
    * **[Viewing personal contact : `me`](#me)**
    * **[Deleting a contact : `rm`](#delete)**
    * **[Editing a contact : `edit`](#edit)**
    * **[Searching for a contact : `search`](#search)**
    * **[Importing a contact : `import`](#import)**
    * **[Viewing help : `help`](#help)**
    * **[Exiting the program : `exit`](#exit)**
* **[FAQ](#faq)**
* **[Command Summary](#summary)**

<br />

<a name="start"></a>
## Quick Start

This section aims to get you started with using **ConTech**. 

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest version of `contech.jar` from [here](https://github.com/AY2122S1-CS2113T-T09-1/tp/releases).
3. Copy the `contech.jar` file to the folder you want to use as the _home folder_ for **ConTech**.
4. Open your desired _Command Line Interface_ from the folder with `contech.jar` and enter the following code: 
`java -jar contech.jar`.
5. As a first time user, you would be required to type in your personal details.
6. Type a command in the command box and press _Enter_ to execute it. e.g., typing `help` and pressing _Enter_ will 
   display the help message. <br>
   Some example commands you can try:
   * `add -n Alex Lee -g alexlee -e alex.lee@contech.sg -te alexl33`: Adds a contact named `Alex Lee` 
     to the ConTech Book.
   * `ls`: Lists all contacts.
   * `view 1`: Displays all the details of the contact at index `1`.   
   * `rm 1`: Deletes the contact at index `1` in the list.
   * `exit`: Exits the program.
   
If you have reached this step without any issues, congratulations! You have successfully set up **ConTech**.

<div markdown="block" class="alert alert-info">
:information_source: You are advised to go through the following two sections.

Before moving on to the features of **ConTech**, we have prepared some documentation regarding some common notations 
that will be used throughout this guide.

However, if you would like to, you can refer to the [Features](#features) below for details of each feature and 
its respective command.
</div>

<a name="flag"></a>
### Contact flags: `-flag`
When using commands such as `add`,`edit` and `search`, you can specify details with the use of flags in the form: 
`-flag`.

**ConTech** currently supports six `flags` and has in built checkers to check if the format of the `details` are 
fulfilled. These `flags` include:
- `-n`: for your contact's name
- `-g`: for your contact's GitHub account username
- `-l`: for your contact's LinkedIn handle
- `-te`: for your contact's Telegram handle
- `-tw`: for your contact's Twitter handle
- `-e`: for your contact's email address

<a name="notations"></a>
### Common notations used
Throughout the user guide, you will find the following notations:
- Words in `UPPER_CASE` are meant to be parameters that you can supply to your commands.
  - e.g. When adding a contact, in the command `add -n NAME -g GITHUB`, `NAME` and `GITHUB` are parameters which you 
    can specify, such as `add -n Le Zong -g lezongmun`.
  - e.g. When viewing a contact's details, in the command `view INDEX`, `INDEX` is a number (integer) representing 
    your contact's index in the **ConTech** book which you can specify, such as `view 2`.
- Items in curly braces `{}` are optional.
- Items in angle braces `<>` are mandatory.
  - e.g. When specified in the format `<-n> <NAME> {-g <GITHUB>}`, it means that:
    - The `-n` flag and `NAME` detail are mandatory fields, without which the command would not execute.
    - The `-g` flag is optional, however, if used, a `GITHUB` detail would have to be specified.
- Items specified with a pipe `|` denote an either-or field.
  - e.g. For `{-n | -g | -l | -te | -tw | -e}`, only **up to** one `flag` is allowed, but there are **six** choices.

Do familiarise yourself with these notations to better utilise the user guide.
<br/>

<a name="features"></a>
## Features 

<a name="add"></a>
### Adding a contact: `add`

Adds a specified contact to the ConTech Book.

Command format: `add <-n> <NAME> {-g <GITHUB>} {-l <LINKEDIN>} {-te <TELEGRAM>} {-tw <TWITTER>} {-e <EMAIL>}`

- ConTech is robust and allows you to specify your fields in any order.
- The `-n` name field is the only compulsory field required to add a contact. The rest are optional.
- Up to five other `flags` and `details` can be specified.
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

Lists all the contacts in the ConTech Book with their indexes and names.

Command format: `ls`
* Do note that the indexes start from 0, similar to array indexes, which you may be familiar with.
 
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

Command format: `view <INDEX>`
* Displays all the details of the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.

<div markdown="block" class="alert alert-info">

:information_source: You can easily run the [`ls`](#list) command to view your contact indexes before running any 
command 
requiring indexes.

</div>

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
Displays all your details saved to the personal contact in the ConTech Book.

This refers to the details which you filled in while setting up **ConTech**.

Command format: `me`
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
Removes the contact with a specified index from the ConTech Book. The `rm` command can also be used to delete 
**all** your contacts at one go, or to remove specific fields for each of your contacts.

To improve user experience, before any deletion, ConTech Book will display the details of the contact you specified to 
be deleted, and prompt you to confirm deletion.

Command formats:

`rm <INDEX>`
* Deletes the contact with the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be within the range of indexes displayed in the contact list _(zero-based)_.
  
`rm <INDEX> {-g} {-l} {-te} {-tw} {-e}`
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

Command format: `edit <INDEX> {-n <NAME>} {-g <GITHUB>} {-l <LINKEDIN>} {-te <TELEGRAM>} {-tw <TWITTER>} {-e <EMAIL>}`

- Up to six `flags` and `details` can be specified, in any order.
- The index refers to the index number shown in the displayed contact list.
- The index must be within the range of indexes displayed in the contact list (zero-based).
- ConTech also has built-in duplicate checkers and will alert you if there is already a contact with the same details when editing.
- While all six `details` are optional, minimally **one** must be specified for any effect to take place.


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

Command format: `search {-n | -g | -l | -te | -tw | -e} <QUERY>`

- Up to one `flag` can be specified for queries relating to that `flag`.
- If no flag is specified, the default search detail is contact name.
- The search query is **not** case-sensitive.
- All contacts with the specified field containing the search query will be displayed in [`view`](#view) format.

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

Command format: `exit`

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
Add a new contact| `add <INDEX> -n <NAME> {-g <GITHUB>} {-e <EMAIL>} {-te <TELEGRAM>} {-l <LINKEDIN>} {-tw <TWITTER>}` | `add -n Marcus` <br>`add -n John Doe -g johndoecoder -e john@email.com -te johndoe`<br/>
List all contacts | `ls` | `ls`
View a contact| `view <INDEX>` | `view 2`
Edit a contact| `edit <INDEX> {-n <NAME>} {-g <GITHUB>} {-e <EMAIL>} {-te <TELEGRAM>} {-l <LINKEDIN>} {-tw <TWITTER>}` | `edit 1 -e john.doe@email.com` <br>`edit 0 -n Tan -g tanned -te tantan`<br/>
Delete contact fields| `<rm <INDEX> {-g} {-l} {-te} {-tw} {-e}>` | `<rm 3 -g -te -l -e>`
Delete a contact | `rm <INDEX>` | `rm 1`
Search for a contact| <code>search {-n &#124; -g &#124; -l &#124; -te &#124; -tw &#124; -e} \<QUERY\></code> | `search Ashraf` <br>`search -g revflash`<br/>
Import contacts from .txt file|`import` | `import`
Exit ConTech | `exit` | `exit`
