### Developer Guide 
### Table of Content
- [Acknowledgements](#acknowledgements)
- [Common Notations In DG](#notations)
- [Design](#design)
  - [System Architecture](#sys-arch)
  - [TextUi](#text-ui)
  - [MainParser](#main-parser)
  - [Command](#command)
  - [ContactList](#contact-list)
  - [Storage](#storage)
- [Implementation](#implementation)
  - [Supported contact details](#supported-details) 
  - [Adding a contact](#Add)
  - [Viewing a contact](#View)
  - [Editing a contact](#Edit)
  - [Deleting a contact](#Delete)
  - [Searching a contact](#Search)
  - [Listing all contacts](#List)
  - [Importing contacts](#Import)
  - [Contact index parser](#IndexParser)
  - [Design Considerations](#DesignConsiderations)
- [Product Scope](#scope)
  - [Target user profile](#target)
  - [Value proposition](#value)
- [User Stories](#stories)
- [Non-Function Requirements](#nf-req)
- [Glossary](#glossary)
- [Instructions for manual testing](#manual-test)
  - [Launch and Shutdown](#launch-and-shutdown)
  - [Adding a contact](#testing-add)
  - [Viewing a contact](#testing-view)
  - [Editing a contact](#testing-edit)
  - [Deleting a contact](#testing-delete)  
  - [Searching for a contact](#testing-search)
  - [Importing contacts](#testing-import)

<div style="page-break-after: always;"></div>

## <a name="acknowledgements"></a>Acknowledgements

- Inspiration for App Idea and OOP Structure: [AddressBook (Level 2)](https://github.com/se-edu/addressbook-level2) <br />
- Inspiration for User Guide and Developer Guide: AddressBook (Level 3) [[DG]](https://se-education.org/addressbook-level3/DeveloperGuide.html) <br />
  [[UG]](https://se-education.org/addressbook-level3/UserGuide.html)
- Inspiration for Regular expression for [GitHub username](https://github.com/shinnn/github-username-regex) validation
- Regular expression for [email](https://emailregex.com/) validation
- [Converting text for ConTech](https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20) <br />
- [GitHub Markdown Emoji Syntax](https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md) for User Guide: <br />
- [PlantUML Tutorial](https://se-education.org/guides/tutorials/plantUml.html) <br />

## <a name="notations"></a>Common Notations in DG
The common notations listed below will be used throughout the Developer Guide.
- Words in `UPPER_CASE` are meant to be parameters that can be supplied to the commands.
  - e.g. When adding a contact, in the command `add -n NAME -g GITHUB`, `NAME` and `GITHUB` are parameters which
    can be specified, such as `add -n Le Zong -g lezongmun`.
  - e.g. When viewing a contact's details, in the command `view INDEX`, `INDEX` is a number (integer) representing
    the contact's index in the **ConTech** book which can be specified, such as `view 2`.
- Items in curly braces `{}` are optional.
- Items in angle braces `<>` are mandatory.
  - e.g. When specified in the format `<-n> <NAME> {-g <GITHUB>}`, it means that:
    - The `-n` flag and `NAME` detail are mandatory fields, without which the command would not execute.
    - The `-g` flag is optional, however, if used, a `GITHUB` detail would have to be specified.
- Items specified with a pipe `|` denote an either-or field.
  - e.g. For `{-n | -g | -l | -te | -tw | -e}`, only **up to** one `flag` is allowed, but there are **six** choices.

<div style="page-break-after: always;"></div>

## <a name="design"></a>Design

### <a name="sys-arch"></a>System Architecture

![System Architecture](images/SystemArchitectureDiagram.png)

The above **System Architecture** diagram shows the high-level design of ConTech.

On launch, the `Main` class initialises the app components in the correct sequence and links them up
with each other, in the correct sequence.

ConTech comprises five main components, namely:
- `TextUi`: Command Line User Interface of ConTech.
- `MainParser`: Parser to parser user inputs from `TextUi` for `Command`.
- `Command`: Command to be executed upon input parsing.
- `ContactList`: Data structure to store `Contact`s while running ConTech.
- `Storage`: Reads from and writes to [`LocalStorage`](#local-storage).

<div style="page-break-after: always;"></div>

**How the architecture components interact with each other**

The five main components interact with each other, as shown in the sequence diagram below
for the example: `view 2`

![System Architecture Sequence Diagram](images/SystemArchitectureSequence.png)


### <a name="text-ui"></a>TextUi
The `TextUi` component is responsible for displaying all outputs to the Command Line User Interface. After a user's input
has been processed and ConTech has performed its commands depending on the input, in order to output the results of the
commands, a relevant method in the `TextUi` class will be called. 

Methods for printing **error messages** have been separated
from the main feature outputs. These methods have been placed in the `ExceptionTextUi` class within the same `ui` package
as `TextUi`. 

<div style="page-break-after: always;"></div>

### <a name="main-parser"></a>MainParser
The `MainParser` component is responsible for making sense of the user's inputs. It functions as the
not only the identifier for commands, but also calls its relevant sub-parsers to further destructure
the inputs, allowing ConTech to perform its commands.

The diagram below shows a sequence diagram of how `MainParser` works, and a reference diagram is used
to indicate that further parsing is done by sub-parsers for each different command type. This reference diagram
will be referred to later on.

![Main Parser Sequence Diagram](images/MainParserSequenceDiagram.png)

### <a name="command"></a>Command

The `Command` component represents a collection of classes with their names in the form of `XYZCommand`. The `XYZCommand` 
classes are executed in `ConTech.java` by calling on their corresponding `execute()` function. 

### <a name="contact-list"></a>ContactList

The `ContactList` component contains all the contacts stored in an arraylist. It deals with operations that interact with 
the contacts in the `ContactList`, such as adding or editing contacts. The contacts stored in the `ContactList` are sorted
according to their names in alphabetical order.

### <a name="storage"></a>Storage

![Storage Class Diagram](images/StorageClassDiagram.png)

The `Storage` component consists of the `Storage`, `ContactsDecoder`, and `ContactsEncoder`. This component is 
responsible for interacting with the user's local storage files. The user's contacts data and personal contact data 
are stored locally inside the file paths, `data/contacts.txt` and `data/me.txt`.

Firstly, the `Storage` class checks if the user has existing data, or if they are first time users. Next, it will 
make use of the `ContactsDecoder` class to decode the storage file and load the contacts into the `ContactList` as 
`Contacts`. After every command execution, to ensure data integrity, the `Contacts` in the `ContactList` will be 
saved using the `ContactsEncoder` class.

As the `Storage` component is also responsible for loading these data into their corresponding `ContactList` and 
`Contact` objects, it is dependent on the classes, `ContactList` and `Contact`.


<div style="page-break-after: always;"></div>

## <a name="implementation"></a>Implementation

### <a name="supported-details"></a>Supported Contact Details
The currently supported contact details are provided in the table below:

|Flag|Detail of contact|
|----|------|
|`-n`|Name|
|`-g`|Github username|
|`-l`|LinkedIn handle|
|`-te`|Telegram handle|
|`-tw`|Twitter handle|
|`-e`|Email|

<div style="page-break-after: always;"></div>

### <a name="Add"></a>Adding a contact: `add`
This feature is processed using `AddContactCommand`. This feature allows a user to add a contact to their contact list.
The user is able to add a contact by entering a command in the form of `add <-n> <NAME> {-g <GITHUB>} {-l <LINKEDIN>} 
{-te <TELEGRAM>} {-tw <TWITTER>} {-e <EMAIL>}`.

The user's input is parsed in `MainParser` and `AddContactParser`, the latter which inherits `ContactParser`. 
`ContactParser` inherits `RegexParser` (for regex checks regarding each detail) and implements the `ContactDetails`
interface (which uniquely allows the parsers to easily identify each detail based on their indexes). The class 
diagram for this is displayed below.

![Add Contact Parser Class Diagram](images/AddContactParserClassDiagram.png)

As the contacts are identified by their names, the name field is made **compulsory** at the `MainParser` level.
The diagram below shows the process of parsing the user's input.

![Add Contact Parsing](images/AddContactParsingSequenceDiagram.png)

<div style="page-break-after: always;"></div>

Upon parsing the user's input, the details are passed to an `AddContactCommand`, and this command will be 
executed in `ConTech`. The sequence diagram below illustrates the process of executing `AddContactCommand`.

![Add Sequence Diagram](images/AddContactCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### <a name="View"></a>Viewing a contact: `view`
This feature is processed using `ViewContactCommand`. Whenever a user wants to view a specific contact from the
contact list, user can input `view INDEX` with the index of the desired contact displayed from the `ls` feature. 
`ViewContactCommand` is then created in the `MainParser` and executed in `ConTech` after parsing for the index
of the desired contact.

If the `INDEX` is input as `me`, it will be equivalent to the `contactIndex` being `-1` in which case the personal 
details of the user will be printed by calling the `viewPersonalContactMessage` method in `TextUi`. If the `INDEX` is 
missing, it will be equivalent to the `contactIndex` being `-2` in which case the error message to notify the user that 
the index is missing will be printed by calling the `missingIndexMessage` method in `ExceptionTextUi`. Lastly, if the 
`INDEX` is within range and valid, the contact will be retrieved by calling the `getContactAtIndex` method in 
`ContactList` which will return the `Contact` and store it as `viewingContact` in `ViewContactCommand`. To print out 
the contact, the `viewContactMessage` method in `TextUi` will be called. The sequence diagram below illustrates the
process of viewing a contact.

![View Sequence Diagram](images/ViewContactCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### <a name="Edit"></a>Editing a contact: `edit`
This feature is processed using `EditContactCommand`. This feature allows the user to edit a contact in their contact 
list, by entering a command in the form `edit <INDEX> {-n <NAME>} {-g <GITHUB>} {-l <LINKEDIN>} {-te <TELEGRAM>} 
{-tw <TWITTER>} {-e <EMAIL>}`. At least one detail must be specified by the user for the command to be valid. The user 
can also specify `me` as an index to edit the personal contact's details.

The user's input is parsed in `MainParser` and `EditContactParser` and the implementation is similar to that of 
`AddContactParser` .

The user input will be parsed by `EditContactParser` methods `getIndexToStore` and 
`parseContactDetails` to obtain a String array with the details to be edited.
The user input will also be parsed by the [`IndexParser`](#IndexParser) to obtain the contact index, which identifies the contact in the 
contact list to be edited.The sequence diagram below shows how the String array is obtained.

![Edit Sequence Diagram](images/EditContactParserSequenceDiagram.png)

<div style="page-break-after: always;"></div>

Once the user's input is parsed and the index specified is obtained, an `EditContactCommand` with the specified 
parameters will then be created and executed in `ConTech`. The sequence diagram below depicts the execution of 
`EditContactCommand` for the index `me` as well as an invalid index `all`.

![Edit Sequence Diagram](images/EditContactCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

If a valid contact index in the contact book is specified, the details to be edited will first be checked against the 
contact book for duplicates. If there are duplicates, ConTech will prompt the user for confirmation before editing the 
contact. If the user accepts or there are no duplicates, the `EditContactCommand` will be executed. The sequence diagram
below depicts the execution of `EditContactCommand` for a contact in the contact list.

![Edit Sequence Diagram](images/EditContactCommandDetailedSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### <a name="Delete"></a>Deleting contacts: `rm`
This feature is processed using the `DeleteContactCommand`. Users can delete a specified contact, delete all contacts at
once or delete specific details of a selected contact. In order to determine which contact or which 
contact's fields to delete, the user input is parsed using the [`IndexParser`](#IndexParser) to obtain the contact
index for executing the command. The user input is also parsed using `DeleteContactParser` to obtain the specific fields
to be deleted, if any.

A `DeleteContactCommand` with the specified parameters will then be created in the `MainParser`and executed in`ConTech`.

![Delete Sequence Diagram](images/DeleteContactCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

To _delete all contacts_, a user must enter the command `rm all`. 

The sequence diagram below shows how the removal of all contacts works. Before any deletion, the user will be
prompted with a message to confirm deletion. If the user confirms deletion for all contacts,
deletion will be executed, along with a message to show that deletion has been executed.
If user cancels deletion, a message is printed to show that the deletion has been cancelled.

![Delete All Sequence Diagram](images/DeleteAllContacts.png) 

<div style="page-break-after: always;"></div>

To _delete a selected contact_, a user must enter a command in the form `rm <INDEX>`.

The sequence diagram below shows how the removal of the selected contact works. Before any deletion, details of the 
contact with the specified`INDEX` will be displayed to the user, along with a prompt to confirm deletion. If the user 
confirms deletion, deletion of the selected contact will be executed, along with a message to show that deletion has 
been executed. If user cancels deletion, a message is printed to show that the deletion has been cancelled.

![Delete Selected Sequence Diagram](images/DeleteSelectedContact.png)

<div style="page-break-after: always;"></div>

To _delete specific details_ of a selected contact, a user must enter a command in the form `rm <INDEX> {-g} {-l} {-te}
{-tw} {-e}`. 

The sequence diagram below shows how the removal of a contact's fields works. Before any
deletion, details of the contact fields specified will be displayed to the user, along with a prompt to confirm 
deletion. If the user confirms deletion, deletion of the selected contact will be executed, along with a message to show
that deletion has been executed. If user cancels, deletion, a message is printed to show that the deletion has been
cancelled.

![Delete Fields Sequence Diagram](images/DeleteContactFieldsSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### <a name="Search"></a>Searching a contact: `search`
This feature is processed using `SearchContactParser` under `MainParser`. In order to edit a contact in the contact list,
a user must enter a command in the form `search {-n | -g | -l | -te | -tw | -e} <SEARCH QUERY>`. If no flag is specified, 
the search will be done on contact names buy default. 

From the user input, the search query and the search flag are obtained from the `parseSearchQuery` and the `getDetailFlag`
methods respectively. The sequence diagram below shows how the required parameters are obtained. 

![Search Sequence Diagram](images/SearchContactParserSequenceDiagram.png)

<div style="page-break-after: always;"></div>

A `SearchContactCommand` with the specified parameters will then be created and executed in `ConTech`. The sequence 
diagram below shows how the `SearchContactCommand` is executed.

![Search Sequence Diagram](images/SearchContactCommandSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### <a name="List"></a>Listing all contacts: `ls`
This feature is processed under `ListContactsCommand`. The feature allows the user to list all their stored contacts
in an easy to understand manner, by entering the command `ls`. The output is the names of all the contacts stored
with their respective indexes. 

The user’s input is parsed in `MainParser` which invokes the `execute` method in `ListContactsCommand`. The sequence
diagram below shows the series of steps to obtain and print all the contacts.

![List Sequence Diagram](images/ListContactsCommandSequenceDiagram.png)

The `execute` method gets the list size from `ConatactList` class using the `getListSize` method.
If the `contactListSize` is `0` it prints an error message from the `TextUI` class using the method
`contactsEmptyListMessage`. 
If the list is not empty the method `listAllContacts` uses a loop to get the `Contact` object at 
every available index and print it using the `printContactWithIndex`
method in `TextUi` class.

<div style="page-break-after: always;"></div>

### <a name="Import"></a>Importing contacts: `import`
This feature is processed using the `ImportContactCommand`. This feature allows a user to import contacts over from 
a text file containing comma-separated values (CSV).

As inputs may be corrupted (having less fields than required), or may not adhere to the detail formats of each field 
(such as having illegal characters in the Github username), a `ContactsDecoder` is used to read and ensure the data 
integrity of imported contacts. To achieve this, the `ContactsDecoder` class inherits the `RegexParser` abstract 
class, as depicted below.

![ContactsDecoder Class Diagram](images/ContactsDecoderClassDiagram.png)

<div style="page-break-after: always;"></div>

With this implementation in place, the `ImportContactCommand` will firstly check if there exists a file `import.txt` 
stored in the `data/` directory (ie. stored in `data/import.txt`). Should the `import.txt` file exist, it will then 
attempt to import the contacts to a temporary `ContactList`, before adding them to the user's `ContactList`. The 
sequence diagram below illustrates the process of executing `ImportContactCommand`.

![Import Sequence Diagram](images/ImportContactCommandSequenceDiagram.png)

Throughout the process of importing contacts, the alphabetical ordering of contacts is preserved.

<a name="ComingSoon"></a>**Coming soon:** `import` currently does not support duplicate checks. This will be implemented 
in future revisions to the application. Currently, 

Current implementation ideas include:
- import all with/without duplicates (use flag to specify)
- manually check every single duplicate (inefficient, but can be an option with flag for users who want to check every duplicate)
- allow Windows-style duplicate handling (Ignore, Ignore all, Cancel)
- summarise every single duplicate and give user a one time confirmation to add/discard (no granularity)

<div style="page-break-after: always;"></div>

### <a name="IndexParser"></a>Index Parser

The Index Parser is used when the user enters a command that specifies a contact index, such as the [`rm`](#Delete),
[`view`](#View) or [`edit`](#Edit) command. The Index Parser will parse the user's input string, and return the
specified contact index given as an integer, which will then be used in the execution of the corresponding command.

For example, if `edit 2 -n Marcus Bobo` is given as the input, the Index Parser will identify the contact index to be `2`
and pass the contact index to the execution of the `edit` command accordingly.

### <a name="DesignConsiderations"></a>Design Considerations

**Aspect: Implementation of removing fields of a contact**

* **Alternative 1: Implement under `edit` feature**
  <br /> Specify empty flags while using the `edit` command and the program would
  delete those fields from the contact<br />
  Example : `edit 2 -n Jim -e -g` would change the name field for contact and would remove the email
  and github field from the contact.

    * Pros: Editing and removing fields can be done in one step.
    * Cons: Difficult to implement and makes the program more error-prone as flag descriptions can now be empty.

* **Alternative 2 (current choice): Implement under `rm` feature**
  <br /> Specify flags while using the `rm` command and the program would delete those fields from the contact<br />
  Example : `rm 2 -e -g` would remove the email and github field from the contact at index 2.

    * Pros: Easy to implement as rm is a much simpler feature which only takes an index.
      Much easier exception handling also and thus less error-prone.
    * Cons: Less user intuitive and takes two steps when the user wants to edit a contact and also delete fields

<div style="page-break-after: always;"></div>

## <a name="scope"></a>Product scope

### <a name="target"></a>Target user profile
- Has a need to store a significant amount of computing-related contacts
- Prefers and is familiar with Command Line Interface (CLI) applications
- Has many contacts that use common computing platforms, such as: Github, Linkedin, Twitter, Telegram, and Email
- Can type fast and prefers typing to mouse interactions

### <a name="value"></a>Value proposition

As computing professionals are often on their computer, ConTech allows them to have a platform to 
manage their computing-related contacts locally and efficiently, without the need to use 
additional devices or platforms.

## <a name="stories"></a>User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|programmer|add my colleague's account usernames|easily access it|
|v1.0|programmer|edit my colleague's account usernames|update it if there is a change|
|v1.0|programmer|save contact data locally on my hard drive|access it without internet|
|v1.0|programmer|view various contact details of my colleague|contact him easily on different platforms|
|v1.0|programmer|see the names and index of saved contacts|know the contacts I have saved|
|v1.0|programmer|delete a specific contact||
|v1.0|programmer|be able to copy and paste the displayed contact's URLs|visit the contact's accounts|
|v2.0|programmer|save my personal details|the application is aware of the user|
|v2.0|forgetful user|be able to search for my contacts by name|find their contact details|
|v2.0|programmer|import a list of contacts quickly from an input txt file|save time typing each contact

## <a name="nf-req"></a>Non-Functional Requirements

- App should work on any [*mainstream Operating Systems*](#os) as long as Java `11` or higher has been installed on it
- App should be easily usable by a novice who does not have much CLI experience
- Format of details of contacts should follow requirements stated by Github, LinkedIn, Telegram, Twitter, and general Emails
- Importing hundreds of contacts should be instantaneous

## <a name="glossary"></a>Glossary

* <a name="os"></a>**Mainstream Operating Systems** - Windows, macOS, *NIX
* <a name="local-storage"></a>**LocalStorage** - Refers to user's hard disk storage

<div style="page-break-after: always;"></div>

## <a name="manual-test"></a>Instructions for manual testing

Given below are instructions to test the app manually.

**Note:** These instructions only provide a starting point for testers to work on; testers are expected to do more
exploratory testing.

### <a name="launch-and-shutdown"></a>Launch and Shutdown

1. Initial launch
    1. Ensure that you have Java `11` or above installed in your Computer.
    2. Download the latest version of `contech.jar` from **[here](https://github.com/AY2122S1-CS2113T-T09-1/tp/releases)**.
    3. Copy the `contech.jar` file to the folder you want to use as the _home folder_ for **ConTech**.
    4. Open your desired _Command Line Interface_ from the folder with `contech.jar` and enter the following code:
       `java -jar contech.jar`.
       
    
2. First time user
    1. For a first time user, you would be required to type in your personal details.
    2. Entering your name will be **mandatory**. The other 5 fields (GitHub, Telegram, Twitter, Email and LinkedIn) are 
       **optional**. You will be prompted to enter each detail accordingly. For the optional fields, you can press ENTER
       to skip.
       

3. Regular user
    1. For regular users, ConTech will simply greet you and you are ready to input your command.
       Expected:
       ```
       ____________________________________________________________
       Hello, John.
       Welcome back to ConTech, your personal contact tracker.
       ____________________________________________________________
       ```

4. Shutting down ConTech
    1. Once you are done with your tasks and would like to shutdown ConTech, simply input `exit`.
    2. Test case: `exit`<br>
       Expected:
       ```
       ____________________________________________________________
       ConTech will now shutdown.
       We hope you have enjoyed using it.
       ____________________________________________________________
       ```

<div style="page-break-after: always;"></div>

### <a name="testing-add"></a>Adding a contact
1. Adding a contact with all fields
    1. Test case: `add -n Alex Lee -g alexlee -te alexlee -tw alexl33 -e alex.lee@email.com -l alexlee`<br>
       Expected: New contact will have all fields added. Each field will be displayed after it has been added to the 
       contact list.
       ```
       ____________________________________________________________
       ConTech has added the specified contact:
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       LinkedIn: linkedin.com/in/alexlee
       Twitter:  twitter.com/alexl33
       
       You now have 1 contact(s).
       ____________________________________________________________
       ```
       
2. Adding a contact with fewer fields
    1. Test case: `add -n Jake Tan -g tanjakey -e jakeytan@email.com`<br>
       Expected: New contact will only have the fields mentioned added to the contact. Each field that is field will be
       displayed after it has been added to the contact list.
       ```
       ____________________________________________________________
       ConTech has added the specified contact:
       Name:     Jake Tan
       Github:   github.com/tanjakey
       Email:    jakeytan@email.com
       
       You now have 1 contact(s).
       ____________________________________________________________
       ```
       
3. Adding a contact with no field entered
    1. Test case: `add`<br>
       Expected:  An error message will notify user that there are missing parameters.
       ```
       ____________________________________________________________
       There seems to be missing parameters in your request.
       Please enter command in this format:
             add -n <NAME> {-g <GITHUB>} {-e <EMAIL>} {-te <TELEGRAM>} {-l <LINKEDIN>} {-tw <TWITTER>}
             example : add -n John Doe -g johndoecoder -e john@email.com -te johndoe
       NOTE : At least NAME required
              Order of parameters do not matter
       ____________________________________________________________
       ```

4. Adding a contact with duplicates
    1. Prerequisites: A contact with similar either similar name or details must already be in the contact list. For
       simplicity, we will re-use the same command from `1`.
    2. Test case: `add -n Alex Lee -g alexlee -te alexlee -tw alexl33 -e alex.lee@email.com -l alexlee`<br>
       Expected: ConTech will display a list of contacts that are already in your contact list with the same fields as
       the one you are adding. It will then ask for your confirmation whether you would still like to add the contact
       or ignore it.
       ```
       ____________________________________________________________
       One of your saved contacts has a duplicate field:
       
       0.
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       LinkedIn: linkedin.com/in/alexlee
       Twitter:  twitter.com/alexl33
       
       Do you still want to add the contact?  (y/n)
       ____________________________________________________________
       ```
    3. Follow up: You can either input `y` which stands for **yes** allowing you to still add the contact despite having
       a duplicate field or `n` which stands for **no** to disregard adding the contact.
       

5. Adding a contact with a wrong flag
    1. Test case: `add -n Ali -p alixpress`<br>
       Expected:  An error message will notify user that there appears to be a flag that is not recognised.
       ```
       ____________________________________________________________
       There appears to be a flag that is not recognised.
       Please try again with a valid flag.
         -n NAME
         -g GITHUB
         -l LINKEDIN
         -te TELEGRAM
         -tw TWITTER
         -e EMAIL
       ____________________________________________________________
       ```  
  
6. Adding a contact with a wrong command
    1. Test case: `Add -n Ben`<br>
       Expected:  An error message will notify user that ConTech is unable to understand the command and
       that the user can try to input a valid command. The issue with the test case is that the `add` command has a capital
       A.
       ```
       ____________________________________________________________
       ConTech is unable to understand your request.
       Please try again with a valid command.
       ____________________________________________________________
       ```  

7. Adding a contact with an invalid field format
    1. Test case: `add -n George -e george`<br>
       Expected: An error message will notify user on the field with the invalid format. For this test case, the email
       has the wrong format.
       ```
       ____________________________________________________________
       The email id is not correctly formatted,
       Rules for email id :
           * Lowercase letters only
           * Numbers, underscore, hyphen and dot allowed
           * Only one @ character allowed
           * Email cannot start or end with a symbol
       ____________________________________________________________
       ``` 

<div style="page-break-after: always;"></div>

### <a name="testing-view"></a>Viewing a contact
1. Viewing a contact that is in the contact list
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `view 1`<br>
       Expected: All details of the contact with index `1` will be displayed. 
       ```
       ____________________________________________________________
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       LinkedIn: linkedin.com/in/alexlee
       Twitter:  twitter.com/alexl33
       ____________________________________________________________
       ```

2. Viewing user's own personal contact
    1. Test case: `view me`
       Expected: All personal details of the user will be displayed.
    2. Test case: `me`
       Expected: All personal details of the user will be displayed. 
       

3. Viewing a contact with a missing or invalid index
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `view -1`<br>
       Expected: An error message will notify user on what the valid indexes are.
       ```
       ____________________________________________________________
       The index you have input is out of range.
       Please input a index between 0 and 2.
       ____________________________________________________________
       ```
    3. Test case: `view`<br>
       Expected: An error message will notify user that there is an index missing.
       ```
       ____________________________________________________________
       There seems to be missing or invalid index in your request.
       Please enter command in the following way:
             view <INDEX>
       Enter <INDEX> between 0 and 2 or "me" (personal contact)
       ____________________________________________________________
       ```

<div style="page-break-after: always;"></div>

### <a name="testing-edit"></a>Editing a contact
1. Editing a contact with all fields
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `edit 1 -n Ben tan -g bentan -e ben.tan@email.com -te bentn -tw bent4n -l bentan`<br>
       Expected: Contact at index 1 will have all fields edited. Each field will be displayed after it has been edited.
       The index of contacts will be changed after editing as the list will be sorted after the edit.
       ```
       ____________________________________________________________
       ConTech has edited the specified contact:
       Name:     Ben tan
       Github:   github.com/bentan
       Email:    ben.tan@email.com
       Telegram: t.me/bentn
       LinkedIn: linkedin.com/in/bentan
       Twitter:  twitter.com/bent4n
       ____________________________________________________________
       ```

2. Editing a contact with fewer fields
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `edit 2 -n Charles -g chacharles -e charles@email.com`<br>
       Expected: Contact at index 2 will have only the name, GitHub and email edited while the other fields, if filled
       will remain the same. All details will then be displayed.


3. Editing a contact with missing or invalid index
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `edit -n Brandon`<br>
       Expected:  An error message will appear to notify that there are missing parameters.
       ```
       ____________________________________________________________
       There seems to be missing parameters in your request.
       Please enter command in this format:
             edit <INDEX> {-n <NAME>} {-g <GITHUB>} {-e <EMAIL>} {-te <TELEGRAM>} {-l <LINKEDIN>} {-tw <TWITTER>}
             example : edit 0 -n George -g procoder -te george123
       NOTE : At least one flag and description required
              Order of parameters do not matter except for INDEX
              "me" is used as the INDEX for personal contact.
       ____________________________________________________________
       ```
    3. Test case: `edit 4 -n Brandon`<br>
       Expected: An error message will appear to notify that the index is out of range.
       ```
       ____________________________________________________________
       The index you have input is out of range.
       Please input a number between 0 and 3 to edit saved contacts.
       Otherwise, input index "me" if you wish to edit your Personal Contact details.
       ____________________________________________________________
       ```
    

4. Editing a user's personal contact
    1. Test case: `edit me -n Zack -g zackster -e zack@email.com`<br>
    2. Expected: User's personal detail will be edited and the personal details will be displayed including fields that
       were not edited.
       

5. Editing a contact with duplicates
    1. Prerequisites: A contact with similar either similar name or details must already be in the contact list. For
       simplicity, we will re-use the same command from `1`.
    2. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.   
    3. Test case: `edit 0 -n Ben tan -g bentan -e ben.tan@email.com -te bentn -tw bent4n -l bentan`<br>
       Expected: ConTech will display a list of contacts that are already in your contact list with the same fields as
       the ones you are editing. It will then ask for your confirmation whether you would still like to edit the contact
       or ignore it.
       ```
       ____________________________________________________________
       One of your saved contacts has a duplicate field:
       
       1.
       Name:     Ben tan
       Github:   github.com/bentan
       Email:    ben.tan@email.com
       Telegram: t.me/bentn
       LinkedIn: linkedin.com/in/bentan
       Twitter:  twitter.com/bent4n
       
       Do you still want to edit the contact?  (y/n)
       ____________________________________________________________
       ```
    3. Follow up: You can either input `y` which stands for **yes** allowing you to still edit the contact despite having
       a duplicate field or `n` which stands for **no** to disregard editing the contact.


5. Editing a contact with a wrong flag
    1. Test case: `edit 0 -p ali`<br>
       Expected:  An error message will appear to notify the user that there appears to be a flag that is not recognised.
       ```
       ____________________________________________________________
       There appears to be a flag that is not recognised.
       Please try again with a valid flag.
         -n NAME
         -g GITHUB
         -l LINKEDIN
         -te TELEGRAM
         -tw TWITTER
         -e EMAIL
       ____________________________________________________________
       ```  

6. Editing a contact with a wrong command
    1. Test case: `Edit -n Ben`<br>
       Expected:  An error message will appear to notify the user that ConTech is unable to understand the command and
       that the user can try to input a valid command. The issue with the test case is that the `edit` command has a capital
       E.
       ```
       ____________________________________________________________
       ConTech is unable to understand your request.
       Please try again with a valid command.
       ____________________________________________________________
       ```

<div style="page-break-after: always;"></div>

### <a name="testing-delete"></a>Deleting a contact
1. Deleting a contact that is in the contact list
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `rm 1`<br>
       Expected: User will be asked to confirm deleting the contact at the specified index. All details of the contact 
       at index `1` will be displayed to allow user to check before confirming.
       ```
       ____________________________________________________________
       Delete this contact?  (y/n)
       
       1.
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       LinkedIn: linkedin.com/in/alexlee
       Twitter:  twitter.com/alexl33
       ____________________________________________________________
       ```
    3. Follow up: You can either input `y` which stands for **yes** allowing you to delete the contact or `n` which 
       stands for **no** to cancel deleting the contact.   


2. Deleting a contact with a missing or invalid index
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `rm -1`<br>
       Expected: An error message will notify user on what the valid indexes are.
       ```
       ____________________________________________________________
       The index you have input is out of range.
       Please input a index between 0 and 2.
       ____________________________________________________________
       ```
    3. Test case: `rm`<br>
       Expected: An error message will notify user that there is an index missing.
       ```
       ____________________________________________________________
       There seems to be missing or invalid index in your request.
       Please enter command in the following way:
             rm <INDEX> {REMOVE_DETAIL_FLAGS}
       Enter <INDEX> between 0 and 3
       ____________________________________________________________
       ```

3. Deleting all contacts in the contact list
    1. Test case: `rm all`<br>
       Expected: User will be asked to confirm deleting all the contacts in the contact list.
       ```
       ____________________________________________________________
       Delete all of your contacts?  (y/n)
       ____________________________________________________________
       ```
    3. Follow up: You can either input `y` which stands for **yes** allowing you to delete all contacts or `n` which
       stands for **no** to cancel deleting all contacts.


4. Deleting one or few fields from a contact
    1. Prerequisites: List all contacts using the `ls` command to find the index of specific contact.
    2. Test case: `rm 1 -g`<br>
       Expected: User will be asked to confirm deleting a field from the contact at the specified index. The specified 
       field of the contact at index `1` will be displayed to allow user to check before confirming. For this test case,
       only the GitHub for the contact at index `1` will be displayed as only the `-g` flag is mentioned.
       ```
       ____________________________________________________________
       Delete the following fields for Ben tan?  (y/n)
       
       Github:   github.com/bentan
       ____________________________________________________________
       ```
    3. Follow up: You can either input `y` which stands for **yes** allowing you to delete the field from the contact
       or `n` which stands for **no** to cancel deleting the field from the contact.

<div style="page-break-after: always;"></div>

### <a name="testing-search"></a>Searching for a contact
1. Search for a contact that is in the contact list
    1. Test case: `search alex`<br>
       Expected: All contacts with `alex` in their name will be listed
       ```
       ____________________________________________________________      
       1.
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       ____________________________________________________________
       ```

2. Search for a contact with a specified field
    1. Test case: `search -g alexlee`<br>
       Expected: All contacts with the GitHub username containing `alexlee` will be listed.
       ```
       ____________________________________________________________      
        1.
       Name:     Alex Lee
       Github:   github.com/alexlee
       Email:    alex.lee@email.com
       Telegram: t.me/alexlee
       ____________________________________________________________
       ```

3. Search for a contact that is not in the contact list
    1. Test case: `search mebe`<br>
       Expected: Error message will notify users that no contacts are found. 
       ```
       ____________________________________________________________
       No search results found.
       ____________________________________________________________
       ```

4. Search with no parameters specified
    1. Test case: `search`<br>
       Expected: Error message will notify user that the command is invalid.
       ```
       ____________________________________________________________
       There seems to be missing parameters in your request.
       Please enter command in this format:
       search {FLAG} <QUERY>
       example : search Ashraf
       search -g revflash
       NOTE : Flag is optional and only one can be specified
       ____________________________________________________________
       ```

<div style="page-break-after: always;"></div>

### <a name="testing-import"></a>Importing contacts
1. Importing contacts with all valid details
    1. Test case: `import`<br>
       Expected: All contacts will be imported successfully.
       ```
       ____________________________________________________________      
       ConTech has successfully imported 3 lines
       ____________________________________________________________
       ```

2. Importing a contact with invalid fields
    1. Test case: `import`<br>
       Expected: Error message will notify the user that there is an invalid field.

        ```
       data/import.txt:1 - There is an invalid field.
       ____________________________________________________________
       The github username is not correctly formatted,
       Rules for Github username :
       * Only contain alphanumeric characters or hyphens
       * Only lowercase allowed
       * Maximum 39 characters allowed
       * Cannot have multiple consecutive hyphens
       * Cannot begin or end with a hyphen
       ____________________________________________________________
       
       ____________________________________________________________
       ConTech has successfully imported 0 lines
       ____________________________________________________________
       ```

3. Importing a contact with missing fields
    1. Test case: `import`<br>
       Expected: Error message will notify the user that there are missing fields.
       ```
       data/import.txt:1 - "ashraf,ashrafjfr,null,null" is corrupted and not loaded.
       ____________________________________________________________
       ConTech has successfully imported 0 lines
       ____________________________________________________________
       ```