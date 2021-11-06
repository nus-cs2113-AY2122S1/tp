# User Guide

## What is CCA Manager?

CCA Manager is a **simple and easy to use** organisational planner for CCAs (Co-Curricular Activities) in NUS. It is 
designed specifically for students who are key appointment holders of such CCAs. Features include **storing and tracking
of information** on CCA members, training schedules and attendance records which can be accessed quickly via a Command
Line Interface (CLI). CCA Manager aims to **centralise and optimize** all CCA-related information, so that you can focus on
improving other aspects of the CCA.

This user guide will help you get started on using CCA Manager. [Pre-requisites](#pre-requisites---things-to-prepare-before-you-start-using-cca-manager) will show you how to 
set up CCA Manager on your computer. The [Commands](#commands) section will show you the list of commands and how to use
them. Finally, [Command Summary](#command-summary) provides you with an overview of all the commands you can execute.

## Table of contents
* [Acknowledgements](#acknowledgements)
* [Pre-requisites](#pre-requisites---_things-to-prepare-before-you-start-using-cca-manager_)
* [Commands](#commands)
  * [Warning Labels](#warning-labels) ⚠️
  * [Members](#member-commands)
    * [add /m](#add-member)
    * [delete /m](#delete-member)
    * [list /m](#list-member)
    * [edit /m](#edit-member)
    * [find /m](#find-member)
  * [Training](#training-commands)
    * [add /t](#add-training)
    * [delete /t](#delete-training)
    * [list /t](#list-training)
    * [edit /t](#edit-training)
    * [find /t](#find-training)
  * [Attendance](#attendance-commands)
    * [add /att](#add-attendance)
    * [delete /att](#delete-attendance)
    * [list /att](#list-attendance)
* [Command Summary](#command-summary)

## Acknowledgements
* SE-EDU
    1. [AB3 Developer Guide Format](https://se-education.org/addressbook-level3/DeveloperGuide.html)
    2. [AB3 User Guide Format](https://se-education.org/addressbook-level3/UserGuide.html)
    3. [AB3 Appendix: Requirements](https://se-education.org/addressbook-level3/DeveloperGuide.html#appendix-requirements)
    4. [AB2 Code Basics](https://github.com/se-edu/addressbook-level2)
* [PlantUML](https://plantuml.com/)

## Pre Requisites - _Things to prepare before you start using CCA Manager_
1. CCA Manager runs on Java 11. Please ensure you have `Java 11` installed on your workstation.
   1. You can download Java [here](https://java.com/en/download/) 
2. Download the latest .jar of CCA Manager [here](https://github.com/AY2122S1-CS2113T-F12-4/tp/releases)
3. Run CCA Manager with the command `java -jar CCAManager.jar` on your Command Prompt `cmd.exe`
4. You should see the following screen if you have successfully run CCA Manager
   ```
   Welcome to
    ,-----. ,-----.  ,---.      ,--.   ,--.  ,---.  ,--.  ,--.  ,---.   ,----.   ,------.,------.
   '  .--./'  .--./ /  O  \     |   `.'   | /  O  \ |  ,'.|  | /  O  \ '  .-./   |  .---'|  .--. '
   |  |    |  |    |  .-.  |    |  |'.'|  ||  .-.  ||  |' '  ||  .-.  ||  | .---.|  `--, |  '--'.' 
   '  '--'\'  '--'\|  | |  |    |  |   |  ||  | |  ||  | `   ||  | |  |'  '--'  ||  `---.|  |\  \ 
    `-----' `-----'`--' `--'    `--'   `--'`--' `--'`--'  `--'`--' `--' `------' `------'`--' '--' 
   What would you like to do?
   =>
   ```

## Commands

**CCA Manager** helps you to organize three main types of information: [Members], [Training] and [Attendance].
With **CCA Manager**, you can organize your data with the help of useful commands such as `add`, `delete`, `edit`, `list` and `find` function

### Quick Reference: | [Member Commands](#member-commands) | [Training Commands](#trianing-commands) | [Attendance Commands](#attendance-commands)

## Warning Labels
⚠️ *denotes important formatting points you should pay attention to. Failing to follow them will cause your command to be unsuccessful.*


## Member Commands
### Add Member
**CCA Manager** has an add feature which lets you key in necessary information such as your members' information and attendance, as well 
as any training-related information regarding your CCA's venue bookings and timings.

To start, you can populate the member list of **CCA Manager** with the help of the `add` member command. This command lets you key in necessary information such as your members' particulars

* `add /m` This adds entries to the Member List stored in CCA Manager.
    * The `add /m` keyword requires 4 different arguments:
        * use `/n` to input _name_ of your member. 
        * use `/s` to input _student number_ of your member.
          * ⚠️ Student Number of each entry must be _unique_. Different member entries with the same student number are not allowed.
        * use `/g` to input _gender_ of your member. Either _M_ for male or _F_ for female.
        * use `/p` to input _phone number_ of your member.
   
    * **Format:**
      * `add /m [/n MEMBER_NAME] [/s STUDENT_NUMBER] [/g GENDER] [/p PHONE NUMBER]`

    * **Examples:**
      * `add /m /n John Hwee /s A0248192K /g M /p 91128888`
    
    * **Expected Output:**
   ```
   Added a Member: 
   [1] Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
   ```
   
### Delete Member
If one of your CCA members has quit, or your senior has graduated, you can remove their entry from **CCA Manager** to keep your member list up to date.

* `delete /m` This deletes entries from the Member list in **CCA Manager**.
   * The `delete /m` keyword can delete entries using either of two different prompts: _index_ or _name_
     * You can delete member entries by inputting the `index` of the member.
     * You can also delete member entries by inputting the `name` of the member.
       * If there are *two or more* members with the same name, **CCA Manager** will display these members and their indexes and prompt you to delete the member you want using his/her index.
   * **Format:**
     * `delete [/m MEMBER_INDEX_NUMBER]`
     * `delete [/m MEMBER_NAME]`
   * **Examples:**
     * `delete /m 1` Deletes the first member on the member list.
     * `delete /m John` Deletes a member with the name 'John'.
       * If there is more than 1 John in the member list, a prompt will show up, indicating that you may not add a duplicate member. 
       _To avoid this error, you may want to key in the full name of your memebers!_
   * **Expected Output:**
   
   ```
    You have removed member: 
    [1] Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
   ```
   * **Expected Output for duplicate member:**
    ```
    Error when adding member:
        Duplicate name found. Please enter a different name
    ```

### List Member
**CCA Manager** allows you to list out all the recorded members in your member list.

* `list /m` This lists out entries in CCA Manager's Member List.
   * The `list /m` keyword is strict, meaning that it must follow the above format. E.g. `list /m RANDOM` will not be a valid command.
   * `list /m` will also show you the `index` of the members that will be helpful for other commands.
   * **Format:** 
     * `list /m`
   * **Examples:**
     * `list /m`
   * **Expected Output:**
     ```
     [1] Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
     [2] Name: LOREM IPSUM | Student Number: A1231234B | Gender: M | Phone Number: 91118888
     [3] Name: FRANK HAN | Student Number: A1234567Z | Gender: M | Phone Number: 91234567   
     ```
   
### Edit Member
Oh, no! You were careless, and you accidentally keyed in John's name wrongly! Worry not; if you have to perform minor changes to your member entries, **CCA Manager** allows you to edit your member entries by using the edit function.
This means that you will not have to delete the entry and then add a new one later on.

* `edit /m` This edits an existing entry from the member list
    * Entries are referenced using their index
    * `/m <MEMBER_INDEX_NUMBER>` edits the member detail stored at the given index. The index is a **compulsory** field.
      * The below fields are on a fill-per-case basis. Only fill in the fields that need changing.
      * `/n <NEW_MEMBER_NAME>` edits the current member _name_
      * `/s <NEW_PHONE_NUMBER>` edits the current _student number_
      * `/g <NEW_GENDER>` edits the current _gender_
      * `/p <NEW_PHONE_NUMBER>` edits the current _phone number_
    * **Format:**
      * `edit [/m MEMBER_INDEX_NUMBER] [/n NEW_MEMBER_NAME] [/s NEW_STUDENT_NUMBER] [/g NEW_GENDER] [/p NEW_PHONE_NUMBER]`
    * **Examples:**
      * `edit /m 1 /n Juan Hwee` Change only the name of the first entry to JUAN HWEE
      * `edit /m 3 /n 91112222` Change the phone number of the 3rd entry to 91112222
    
    * **Expected Output:**
    
   ```
   Edited member: 
   [1] Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
   To become:  
   [1] Name: JUAN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888

   Edited member: 
   [3] Name: FRANK HAN | Student Number: A1234567Z | Gender: M | Phone Number: 91234567
   To become:  
   [3] Name: FRANK HAN | Student Number: A1234567Z | Gender: M | Phone Number: 91112222
   ```
### Find Member
**CCA Manager** allows you to search for a specific member by typing in a _keyword_ with the find function.

* `find` Searches for members based on the keyword given.
   * The keyword should be related to the name of the member
   * `/m <MEMBER_NAME_KEYWORD>` finds ALL entries with the MEMBER_NAME_KEYWORD in it.
     * For example, if you type `find /m jon`, `JON TAN` and `JON BOVI` will be found, but `JOHN LIM` will not be matched
   * The keyword is not case-sensitive
   * **Format:**
     * `find [/m MEMBER_NAME_KEYWORD]`
   * **Examples:**
     * `find /m juan`
     * `find /m lorem`
   * **Expected Output:**
   
   ```
   The following members matches your search "juan"
   [1] Name: JUAN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
   
   The following members matches your search "lorem"
   [2] Name: LOREM IPSUM | Student Number: A1231234B | Gender: M | Phone Number: 91118888
   ```


## Training Commands
### Add Training
**CCA Manager** can help add trainings to your training list with the help of the `add /t` command.

* `add /t` This adds entries to the Training List stored in CCA Manager.
    * The `add /t` keyword requires 3 different arguments:
        * use `/n` to input _name_ of your training schedule.
          * ⚠️ Training Schedules must have _unique_ names. Different training entries with the same training name is not allowed.
        * use `/a` to input _date and time_ of your training schedule.
          * _Date and time_ is stored as a _String_ to allow the CCA admin to use relative timing. E.g. _After training_, _Recess Week_, etc.
        * use `/v` to input _venue_ of training schedule.
        * ⚠️ In your command input, there should only be ONE of each fields. Additional instances of the field will be treated as a wrong command, thus printing an error.
          * For example, `add /t /n Friday /n Training /a 5 Nov 2021 /v MPSH1` will produce an error, because `/n` is used twice.

    * **Format:**
        * `add /t [/n TRAINING_NAME] [/a TRAINING_TIME] [/v TRAINING_VENUE]`

    * **Examples:**
        * `add /t /n Weekly Friday Training 1 /a 5 Nov 2021 /v MPSH 1`
        * `add /t /n Weekly Friday Meeting /a After Training /v CCA Clubroom`
        * `add /t /n Weekly Friday Training 2 /a 12 Nov 2021 /v MPSH1`

    * **Expected Output:**

   ```
   Added a Training entry:
   [1] Training Name: WEEKLY FRIDAY TRAINING 1 | Venue: MPSH 1 | Time: 5 NOV 2021
   
   Added a Training entry:
   [2] Training Name: WEEKLY FRIDAY MEETING | Venue: CCA CLUBROOM | Time: AFTER TRAINING
   
   Added a Training entry:
   [3] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH 1 | Time: 12 NOV 2021
   ```

### Delete Training
You can delete any existing training schedules with the help of the `delete` command.

* `delete /t` This delete entries from the training list in **CCA Manager**.
    * The `delete /t` keyword can delete entries using either of two different prompts: index or name
        * You can delete training entries by inputting the `index` of the training.
        * You can also delete training entries by inputting the `name` of the training.
            * If there are *two or more* trainings with the same name, **CCA Manager** will display these trainings and their indexes and prompt you to delete the training you want using his/her index.
    * **Format:**
        * `delete [/t TRAINING_INDEX_NUMBER]`
        * `delete [/t TRAINING_NAME]`
    * **Examples:**
        * `delete /t 1` Deletes the first training on the training list.
        * `delete /t Friday` Deletes a training with the name 'John'.
            * If there is more than 1 Friday in the training list, a prompt will show up, and you will need to delete the 'Friday' based on its index number.
    * **Expected Output:**
   ```
   You have removed training entry:
   [1] Training Name: WEEKLY FRIDAY TRAINING 1 | Venue: MPSH 1 | Time: 5 NOV 2021
   
   Below are the possible matches. Please key in the INDEX NUMBER ONLY to delete
   [1] Training Name: WEEKLY FRIDAY MEETING | Venue: CCA CLUBROOM | Time: AFTER TRAINING
   [2] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH 1 | Time: 12 NOV 2021
   => 1
   Trying to delete index 1
   You have removed training entry:
   [1] Training Name: WEEKLY FRIDAY MEETING | Venue: CCA CLUBROOM | Time: AFTER TRAINING
   ```

### List Training
**CCA Manager** allows you to list out all the recorded trainings.

* `list /t` This lists out entries in CCA Manager's Training List.
    * The `list /t` keyword is strict, meaning that it must follow the above format. E.g. `list /t TRAININGABC` will not be a valid command.
    * `list /t` will also show you the `index` of the trainings that will be helpful for other commands.
    * **Format:**
        * `list /t`
    * **Examples:**
        * `list /t`
    * **Expected Output:**
      ```
      [1] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH 1 | Time: 12 NOV 2021
      ```

### Edit Training
Had a sudden change in venue for your trainings? No worries! You can edit your training schedules with the help of the `edit /t` feature.

* `edit /t` This edits an existing entry from the training list
    * Entries are referenced using their index
    * `/t <TRAINING_INDEX_NUMBER>` edits the training detail stored at the given index. The index is a **compulsory** field.
        * The below fields are on a fill-per-case basis. Only fill in the fields that need changing.
        * `/n <NEW_TRAINING_NAME>` edits the current _training name_
        * `/a <NEW_TRAINING_TIME>` edits the current _date and time_
        * `/v <NEW_TRAINING_VENUE>` edits the current _venue_
        * ⚠️ Similar to [Add Member](#add-member), the edit field should only have **one** of each field (`/n`, `/a` or `/v`). Otherwise, the command will be treated as invalid. 

    * **Format:**
        * `edit [/t TRAINING_INDEX_NUMBER] [/n NEW_TRAINING_NAME] [/a NEW_TRAINING_TIME] [/v NEW_TRAINING_VENUE]`
    * **Examples:**
        * `edit /t 1 /v MPSH2` Updates the venue of Index 1 to MPSH2
        * `edit /t 1 /n Weekly Friday Training 2 (Updated)` Updates the name of Index 1
 
    * **Expected Output:**
    
   ```
   Edited Training:
   [1] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH 1 | Time: 12 NOV 2021
   To become:
   [1] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH2 | Time: 12 NOV 2021

   Edited Training:
   [1] Training Name: WEEKLY FRIDAY TRAINING 2 | Venue: MPSH2 | Time: 12 NOV 2021
   To become:
   [1] Training Name: WEEKLY FRIDAY TRAINING 2 (UPDATED) | Venue: MPSH2 | Time: 12 NOV 2021
   ```
### Find Training
Have too many training schedules, and can't find the one you want? Just use the `find /t` feature to list out all candidates, helping you find it easier!
* `find` Searches for training schedules based on the keyword given.
    * The keyword should be related to the name of the training
    * `/t <TRAINING_NAME_KEYWORD>` finds ALL entries with the TRAINING_NAME_KEYWORD in it.
        * For example, if you type `find /t friday`, `FRIDAY TRAINING 1` and `FRIDAY TRAINING 2` will be found, but `FIDAY TRAINING` will not be matched
    * The keyword is not case-sensitive
    * **Format:**
        * `find [/t TRAINING_NAME_KEYWORD]`
    * **Examples:**
        * `find /t friday`
    * **Expected Output:**
   ```
   The following trainings matches your search "friday"
   [1] Training Name: WEEKLY FRIDAY TRAINING 2 (UPDATED) | Venue: MPSH2 | Time: 12 NOV 2021
   [2] Training Name: WEEKLY FRIDAY TRAINING 3 | Venue: MPSH1 | Time: 19 NOV 2021
   ```


## Attendance Commands
### Add Attendance
**CCA Manager** can help keep track of which members went for which training with the help of the `add /att` feature.

* `add /att` This adds entries to the Attendance List stored in CCA Manager.
    * The `add /att` keyword requires 3 different arguments:
        * use `/m` to input _member name_ of the student who attended training.
        * use `/n` to input the _training name_ that the student attended.
        * use `/d` to input _status_ of the student who attended training.
          * _1_ represents 'Present', while _0_ represents 'Absent'.

    * **Format:**
        * `add [/att [/m MEMBER_NAME] [/n TRAINING_NAME] [/d 1_OR_0]`

    * **Examples:**
        * `add /att /m John Hwee /n Weekly Friday Training 1 /d 1`

    * **Expected Output:**
   ```
   Added an Attendance entry:
   Name: JOHN HWEE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
   ```

### Delete Attendance
You can delete existing attendance entries with the help of the `delete /att` command.

* `delete /att` This delete entries from the Attendance List in **CCA Manager**.
    * The `delete /att` keyword can delete attendance entries based on index and training name. It takes in two arguments
        * `/t` represents the _training name_ of the attendance to delete from.
        * `/i` represents the _index_ of the attendee to delete.
   
    * **Format:**
        * `delete [/att [/t TRAINING_NAME] [/i MEMBER_INDEX]`
    * **Examples:**
        * `delete /att /t Weekly Friday Training 1 /i 2` 
          * deletes the second entry on the attendance list for 'Weekly Friday Training 1'.
    * **Expected Output:**
   ```
   => list /att /t WEEKLY FRIDAY TRAINING 1
   [1] Name: ADAM | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
   [2] Name: BEN | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [0]
   [3] Name: CANDICE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
   ===== Would you like to list the full attendance sheet? (y / n) =====
   n
   => delete /att /t WEEKLY FRIDAY TRAINING 1 /i 1
   You have removed attendance entry:
   Name: ADAM | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
   ```

### List Attendance
**CCA Manager** allows you to list out all attendance entries recorded.

* `list /att` This lists out entries in CCA Manager's Attendance List.
    * `list /att` takes in several arguments
      * use `/t <TRAINING_NAME>` to look for all attendances for a particular training
      * use `/d <1 OR 0>` to further filter the list to show attendances of people who were present or absent
        * 1 displays the entries of members who were present for that training
        * 0 displays the entries of members who were absent for that training
    * In addition, there will be a prompt to view the full attendance list that consists of all trainings such as:
      ```
      ===== Would you like to list the full attendance sheet? (y / n) =====
      ```
    * **Format:**
        * `list /att [/t TRAINING_NAME] [/d 1_OR_0]`
    * **Examples:**
        * `list /att /t Weekly Friday Training 1`
        * `list /att /t Weekly Friday Training 1 /d 1`
    * **Expected Output:**
      * Attendance entries for particular training 'Weekly Friday Training 1':
        ```
        [1] Name: JOHN HWEE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
        [2] Name: MARK LEE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [0]
        [3] Name: BILLIE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
        ```
      * Filtered attendance list of 'Weekly Friday Training 1' for those who were present:
        ```
        [1] Name: JOHN HWEE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
        [2] Name: BILLIE | Training Name: WEEKLY FRIDAY TRAINING 1 | Present: [1]
        ```

## Command Summary

A 'cheat sheet' of commands here

Action| Syntax |Remarks|
|-----|----------|----|
|add member| `add /m [/n MEMBER_NAME] [/s STUDENT_NUMBER] [/g GENDER] [/p PHONE NUMBER]`| Only one of each `/n` `/s` `/g` `/p`<br />All fields are compulsory
|add training| `add /t [/n TRAINING_NAME] [/a TRAINING_TIME] [/v TRAINING_VENUE]`| Only one of each `/n` `/a` `/v` All fields are compulsory 
|add attendance| `add [/att [/m MEMBER_NAME] [/n TRAINING_NAME] [/d 1_OR_0]`|
|delete member| `delete [/m MEMBER_INDEX_NUMBER]` OR `delete [/m MEMBER_NAME]` | Get the index or name by calling `list /m`
|delete training| `delete [/t TRAINING_INDEX_NUMBER]` OR `delete [/t TRAINING_NAME]`| Get the name or index by calling `list /t`
|delete attendance|`delete [/att [/t TRAINING_NAME] [/i MEMBER_INDEX]`| Get the index by calling `list /att`
|edit member| `edit [/m MEMBER_INDEX_NUMBER] [/n NEW_MEMBER_NAME] [/s NEW_STUDENT_NUMBER] [/g NEW_GENDER] [/p NEW_PHONE_NUMBER]`| Index is compulsory, the rest are optional fields<br />Only one of each `/n` `/s` `/g` `/p`
|edit training| `edit [/t TRAINING_INDEX_NUMBER] [/n NEW_TRAINING_NAME] [/a NEW_TRAINING_TIME] [/v NEW_TRAINING_VENUE]`| Index is compulsory, the rest are optional fields<br />Only one of each `/n` `/a` `/v`
|find member| `find [/m MEMBER_NAME_KEYWORD]` | Searches for valid entries based on member name
|find training| `find [/t TRAINING_NAME_KEYWORD]` | Searches for valid entries based on training name
|list member| `list /m` |
|list trainings| `list [/t]` |
|list attendance entries | `list /att [/t TRAINING_NAME] [/d 1_OR_0]` |Training name is compulsory, 1_OR_0 is an optional field.
