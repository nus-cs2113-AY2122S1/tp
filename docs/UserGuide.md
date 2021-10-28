# User Guide

## What is CCA Manager?

**CCA Manager** is a simple and easy to use command-line based application aimed to assist you, a CCA manager, to easily add, delete or alter information about your 
CCA members. Moreover, **CCA Manager** also allows you to perform its functions on any training-related activities
you may have in order to help you organise your CCA's timetable.

**CCA Manager** is designed for users who are familiar with Command Line Interface (CLI) operations.

- [Pre-requisites](#pre-requisites)
- [Commands](#commands)
- [Command Summary](#command-summary)

## Pre-requisites - _Things to prepare before you start using CCA Manager_
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
### Quick Reference: | [add](#Add) | [list](#list) | [delete](#delete) | [edit](#edit) |

### Add
**CCA Manager** has an add feature which lets you key in necessary information such as your members' information and attendance, as well 
as any training-related information regarding your CCA's venue bookings and timings.  

1. `add` This adds entries to the Entry List stored in CCA Manager.
    * The `add` keyword takes in 3 different arguments:
      * `/m` adds member-related information.
        * use `/n` to input _name_ of your member. 
        * use `/s` to input _student number_ of your member.
        * use `/g` to input _gender_ of your member. Either _M_ for male and _F_ for female.
        * use `/p` to input _phone number_ of your member.
      * `/t` adds training-related information.
        * use `/n` to input _name_ of the training entry.
        * use `/a` to input _timing_ of the training entry.
        * use `/v` to input _venue_ of the training entry.
      * `/att` adds attendance-related information.
        * use `/m` to input _name_ of your member.
        * use `/n` to input _name_ of the training.
        * use `/d` to input _attendance status_ of your member for the particular training, 1 for present and 0 for absent.
    * **Format:**
      * add [/m </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE_NUMBER>]
      * add [/t </a TRAINING_TIME> </v TRAINING_VENUE>]
      * add [/att </m MEMBER_NAME> </n TRAINING_NAME> </d 1_OR_0>]
    * **Examples:**
      - `add /m /n John Hwee /s A0248192K /g M /p 91128888`
      - `add /t /n Weekly December Training 2 /a 12 Dec 2022 /v MPSH 2`
      - `add /att /m Mark /n Monday Training /d 1`
      
    * **Expected Output:**
   ```
   Added a Member: 
   Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888   
   
   Added a Training entry:
   Training Name: Weekly December Training 2 | Venue: MPSH2 | Time: 12 Dec 2022
   
   Added an Attendance entry:
   Name: Mark | Training Name: Monday Training | Present: [1]
   ```

### List
**CCA Manager** allows you to list out all the information you may need after you have input your entries. There are 3 different
kinds of entries you may want to list out, which are your member, training and attendance lists.

2. `list` This lists out entries in CCA Manager's Entry List.
   * The `list` keyword takes in few different arguments:
      * `/m` lists members and member related information.
      * `/t` lists trainings and training related information.
      * `/att [/t <TRAINING_NAME> </d 1_OR_0>]` lists attendance entries, each consisting of a member and whether they attended a particular training.
   * Format: list [/m] [/t] [/att]
   * **Examples:**
      - `list /m`
      - `list /t`
      - `list /att /t Monday Training`
   
   * **Expected Output:**
   ```
   [1] Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888   
    ```
    ```
   [1] Training Name: Weekly December Training 2 | Venue: MPSH 2 | Time: 12 Dec 2022
    ```
    ```
   [1] Name: Mark | Training Name: Monday Training | Present: [1]
    ```
### Delete
**CCA Manager** has a delete function which allows you to remove any members who have left the CCA, trainings which have been
cancelled, or remove any incorrect entries to your attendance list. This means that wrong data can be easily cleared, 
allowing for a simple way to tidy up your file entries.

3. `delete` This deletes entries from the Entry List in CCA Manager.
    * The `delete` keyword removes entries based on their index/name:
      * `/m <INDEX> or <MEMBER_NAME>` is used to delete a member from the members list 
      * `/t <INDEX>` is used to delete a training schedule from the trainings list
      * `/att <INDEX> or <MEMBER_NAME> or <TRAINING_NAME>` is used to delete an attendance entry from the attendance list
      * You can get the index of a member or training by calling `list /m`, `list /t` or `list /att` respectively
    * **Examples:**
      - `delete /m 1` or `delete /m JOHN HWEE`
      - `delete /t 2`
      - `delete /att 3` or `delete /m Mark` or `delete /t Monday Training` 
    
    * **Expected Output:**
    ```
    You have removed member:
    Member Name: JOHN HWEE | Student Number: A0248192K | Gender: M | Phone Number: 91128888
   ```
   ```
    You have removed training entry:
    Training Name: Weekly December Training 2 | Venue: MPSH 2 | Time: 12 Dec 2022
   ```
   ```
    You have removed attendance entry:
    Name: Mark | Training Name: Monday Training | Present: [1]
   ```
   
### Edit
If you have to perform minor changes to your entries, **CCA Manager** allows you to edit your entries by using the edit function.
This means that you will not have to delete the entry and then add a new one later on.

4. `edit` This edits an existing entry from either the members list or training list
    * Entries are referenced using their index
    * `/m <INDEX>` edits the member detail stored at the given index
      * `/n <NEW_NAME>` edits the current member _name_
      * `/s <NEW_NUMBER>` edits the current _student number_
    * `/t <INDEX>` edits the training detail stored at the given index
      * `/n <NEW_NAME>` edits the current training _name_
      * `/a <NEW_TIME>` edits the current training _date & time_
      * `/v <NEW_VENUE>` edits the current training _venue_
      
    * **Examples:**
      - `edit /m 1 /n Juan Hwee /s A0123456B /g F /p 90001111`
      - `edit /t 1 /n Weekly December Training 3 /a 13 Dec 2022 /v MPSH 3 | list /t`
    
    * **Expected Output:**
   ```
    [1] Training Name: Weekly December Training 3 | Venue: MPSH 3 | Time: 13 Dec 2022
   ```


## Command Summary

A 'cheat sheet' of commands here

Action| Syntax |Remarks|
|-----|----------|----|
|add member| add [/m </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE NUMBER>]| 
|add training| add [/t </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>]| 
|add attendance| add [/att </m MEMBER_NAME> </n TRAINING_NAME> </d 1_OR_0>]|
|delete member| delete [/m <MEMBER_INDEX_NUMBER>] OR [/m <MEMBER_NAME>] | Get the index or name by calling `list /m`
|delete training|delete [/t <TRAINING_INDEX_NUMBER>]| Get the index by calling `list /t`
|delete attendance|delete [/att <ATTENDANCE_INDEX_NUMBER>] OR <MEMBER_NAME> OR <TRAINING_NAME>| Get the index by calling `list /att`
|edit member|edit [/m <MEMBER_INDEX_NUMBER> </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE NUMBER>]| Index is compulsory, the rest are optional fields
|edit training|edit [/t <TRAINING_INDEX_NUMBER> </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>]| Index is compulsory, the rest are optional fields
|list| list [/m] [/t] [/att /t <TRAINING_NAME> </d 1_OR_0>]| `/m` for Member, `/t` for Training, `/att` for Attendance where `<1_or_0>` is an optional field
