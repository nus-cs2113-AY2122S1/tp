# User Guide

## Introduction

Duke is an greenfield application aimed to assist CCA's with their administrative issues.

Duke is designed for users who are familiar with Command Line Interface operations

- [Pre-requisites](#Pre-requisites)
- [Commands](#Commands)
- [Command Summary](#Command-Summary)

## Pre-requisites
1. Duke runs on Java 11. Please ensure you have `Java 11` installed on your workstation.
   1. You can download Java [here](https://java.com/en/download/) 
2. Download the latest .jar of Duke [here](https://github.com/AY2122S1-CS2113T-F12-4/tp/releases)
3. Run Duke with the command `java -jar duke.jar` on your Command Prompt `cmd.exe`
4. You should see the following screen if you have successfully run Duke
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|

   What would you like to do?
   >
   ```

## Commands 

1. `add` This adds entries to the Entry List stored in Duke.
    * The `add` keyword takes in 2 different arguments:
      * `/m` adds member related information.
        * use `/n` to input _name_ of your member.
        * use `/s` to input _student number_ of your member.
      * `/t` adds training related information.
        * use `/at` to input _timing_ of the training entry.
        * use `/v` to input _venue_ of the training entry.
    * Format: add [/m </n MEMBER_NAME> </s STUDENT_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>] 
    * **Examples:**
      - `add /m John Hwee /s A0248192K`
      - `add /t /n Weekly December Training 2 /a 12 Dec 2022 /v MPSH 2`
      
    * **Expected Output:**
   ```
   Added a Member: Name: John Hwee | Student Number: A0248192K | Gender:   | Phone Number: 0   
   
   Added a Training entry:
   Training Name: Weekly December Training 2 | Venue: MPSH2 | Time: 12 Dec 2022 
   ```

2. `list` This lists out entries in Duke's Entry List.
   * The `list` keyword takes in 2 different arguments:
      * `/m` lists members and member related information.
      * `/t` lists trainings and training related information.
   * Format: list [/m] [/t]
   * **Examples:**
      - `list /m`
      - `list /t`
   
   * **Expected Output:**
   ```
    [1] Training Name: Weekly December Training 2 | Venue: MPSH 2 | Time: 12 Dec 2022
   ```

3. `delete` This deletes entries from the Entry List in Duke.
    * The `delete` keyword removes entries based on their index:
      * `/m <INDEX>` is used to delete a member from the members list 
      * `/t <INDEX>` is used to delete a training schedule from the trainings list
      * You can get the index of a member or training by calling `list /m` or `list /t` respectively
    * **Examples:**
      - `delete /m 1`
      - `delete /t 1`
    
    * **Expected Output:**
   ```
   You have deleted: 
   Training Name: Weekly December Training 2 | Venue: MPSH 2 | Time: 12 Dec 2022
   ```
4. `edit` This edits an existing entry from either the members list or training list
    * Entries are referenced using their index
    * `/m <INDEX>` edits the member detail stored at the given index
      * `/n <NEW_NAME>` edits the current member _name_
      * `/s ` edits the current _student number_
    * `/t <INDEX>` edits the training detail stored at the given index
      * `/n <NEW_NAME>` edits the current training _name_
      * `/a <NEW_TIME>` edits the current training _date & time_
      * `/v <NEW_VENUE>` edits the current training _venue_
      
    * **Examples:**
      - `edit /m 1 /n Juan Hwee /s A0123456B`
      - `edit /t 1 /n Weekly December Training 3 /a 13 Dec 2022 /v MPSH 3 | list /t`
    
    * **Expected Output:**
   ```
    [1] Training Name: Weekly December Training 3 | Venue: MPSH 3 | Time: 13 Dec 2022
   ```


## Command Summary

A 'cheat sheet' of commands here

* add [/m </n MEMBER_NAME> </s STUDENT_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>]
* delete [/m <MEMBER_NAME> <MEMBER_INDEX_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>]
* list [/m] [/t]
