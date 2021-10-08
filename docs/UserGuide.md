# User Guide

## Introduction

Duke is an greenfield application aimed to assist CCA's with their administrative issues.

## Features 

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
      - `add /m /n John Hwee /s A0248192K`
      - `add /t /at 12 Dec 2022 /v MPSH 2`
          
2. `delete` This deletes entries from the Entry List in Duke.
    * The `delete` keyword takes in 2 different arguments:
       * `/m <INDEX_NUMBER>` deletes member by keying in the index number of the member.
       * `/t` deletes training related information.
         * use `/at` to input _timing_ of the training entry.
         * use `/v` to input _venue_ of the training entry.
    * Format: delete [/m <MEMBER_NAME> <MEMBER_INDEX_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>]
    * **Examples:**
      - `delete /m John Hwee 1`
      - `delete /t /at 12 Dec 2022 /v MPSH 2`
            
3. `list` This lists out entries in Duke's Entry List.
    * The `list` keyword takes in 2 different arguments:
       * `/m` lists members and member related information.
       * `/t` lists trainings and training related information.
    * Format: list [/m] [/t]
    * **Examples:**
      - `list /m`
      - `list /t`


## Command Summary

A 'cheat sheet' of commands here

* add [/m </n MEMBER_NAME> </s STUDENT_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>]
* delete [/m <MEMBER_NAME> <MEMBER_INDEX_NUMBER>] [/t </at TRAINING_TIME> </v TRAINING_VENUE>]
* list [/m] [/t]
