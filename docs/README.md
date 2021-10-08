# Duke

This is a project for a greenfield Java project. It's named after the Java mascot _Duke_, and it is designed to help CCA's manage their administrative issues. 
Given below are instructions on how to use it.

## Functions of Duke

Prerequisites: JDK 11, update Intellij to the most recent version.

1. `add` This adds entries to the Entry List stored in Duke.
    * The `add` keyword takes in 2 different arguments:
      * `/m` adds member related information.
           use `/n` to input _name_ of your member.
           use `/s` to input _student number_ of your member.
      * `/t` adds training related information.
           use `/at` to input _timing_ of the training entry.
           use `/v` to input _venue_ of the training entry.
    * **Examples:**
      - `add /m /n John Hwee /s A0248192K`
      - `add /t /at 12 Dec 2022 /v MPSH 2`
          
2. `delete` This deletes entries from the Entry List in Duke.
    * The `delete` keyword takes in 2 different arguments:
       * `/m <INDEX_NUMBER>` deletes member by keying in the index number of the member.
       * `/t` deletes training related information.
             use `/at` to input _timing_ of the training entry.
             use `/v` to input _venue_ of the training entry.
    * **Examples:**
      - `delete /m John Hwee 1`
      - `delete /t /at 12 Dec 2022 /v MPSH 2`
            
3. `list` This lists out entries in Duke's Entry List.
    * The `list` keyword takes in 2 different arguments:
       * `/m` lists members and member related information.
       * `/t` lists trainings and training related information.
    * **Examples:**
      - `list /m`
      - `list /t`

