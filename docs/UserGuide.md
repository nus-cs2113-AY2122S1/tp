# TermiNUS User Guide

## Introduction

TermiNUS is a CLI (command line interface) program for NUS Students who wish to organize their NUS academic materials through a CLI.  
The product aims to aid student in organizing their academic schedule and enhancing their learning experiences.

## Contents
* [Getting Start](#Getting-Started)
* [Section: Note](#Section:-Note)
    * [Accessing Note : `note`](#Accessing-Note)
    * [Adding a note : `add "<name>" "<content>"`](#Adding-a-Note)
    * [Delete a note : `delete <index>`](#Delete-a-Note)
    * [View note : `view {index}`](#View-Note)
* [Section: Schedule](#Section:-Schedule)
    * [Accessing Schedule : `schedule`](#Accessing-Schedule)
    * [Adding a Schedule : `add "<description>" "<day>" "<start_time>" "<zoom_link>"`](#Adding-a-Schedule)
    * [Delete a Schedule : `delete <index>`](#Delete-a-Schedule)
    * [View Schedule : `view`](#View-Schedule)
* [Exiting the Program: `exit`](#Exiting-the-Program)
* [Accessing Help: `help`](#Accessing-Help)
* [FAQ](#faq)
* [Command Summary](#Command-Summary)
* [Advanced Command Summary](#Advanced-Command-Summary)

## Getting Started

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `TermiNUS` from [here](https://github.com/AY2122S1-CS2113T-T10-2/tp/releases/).
3. When you first start the program, you will be greeted with our banner:  

    ```
    Welcome to TermiNUS! 
    
    Type any of the following to get started: 
    > note
    > schedule
    > help 
    > exit 
     
    [] >>> 
    ```
4. To get started, you can run the following commands:

   - note
   - schedule
   - help
   - exit

___  

## Section: Note

### Accessing Note
**Format:** `note`  
Accessing the note workspace
After running the note command, you can see the following:  

```
[] >>> note
You have 0 note(s) inside this workspace 

Type any of  the following to get started: 
> add 
> exit
> help
> view
> back
> delete 
 
[note] >>> 
```

### Adding a Note
**Format:** `add "<name>" "<content>"`  
Adding a note when in the note workspace  
```
[note] >>> add “Remind Cabbin” “Cabbin was here” 
Note has been added! 
[note] >>> 
```

### Delete a Note
**Format:** `delete <index>`  
Deletes the specified note given by its index.
```
[note] >>> delete 1
Note `Remind Cabbin` has been deleted!
[note] >>> 
```

### View Note
**Format:** `view` or `view {index}`  
Two ways to use this command simply running view or view [index]
View by itself will list all notes
```
[note] >>> view 
You have 3 notes inside: 
1. Remind Cabbin 
2. Name1 
3. Name2 

[note] >>> 
```

The second way to use view is with an index view [index]

```
[note] >>> view 1 
Name: Remind Cabbin 
Content: Cabbin was here 

[note] >>> 
```


## Section: Schedule

### Accessing Schedule
**Format:** `schedule`  
After running the schedule command, you can see the following:
```
[] >>> schedule
You have 0 link(s) in this workspace.

Type any of the following to get started: 
> add 
> edit
> help
> view
> back
> delete 

[schedule] >>> 
```

### Adding a Schedule
**Format:** `add "<description>" "<day>" "<start_time>" "<zoom_link>"`  
Adding a new schedule when in the schedule’s workspace
```
[schedule] >>> add “Module1 Tut1” "Thursday" "10:00" "https://zoom.us/test"  
You have added Module1 Tut’s scheduled zoom link! 
[schedule] >>> 
```

### Delete a Schedule
**Format:** `delete <index>`  
Delete schedule when in the schedule’s workspace
```
[schedule] >>> delete 1 
You have deleted your 1st schedule. 
Schedule `Module1 Tut, Thursday, 10:00, https://zoom.us/test` has been deleted! 
[schedule] >>> 
```

### View Schedule
**Format:** `view`  
View all schedules when in the schedule’s workspace
```
[schedule] >>> view 
You have 3 schedules inside: 
1. Module1 Tut, Thursday, 10:00, https://zoom.us/test 
2. Module2 Lecture, Friday, 14:00, https://zoom.us/test 
3. Module1 Tut1, Thursday, 10:00, https://zoom.us/test

[schedule] >>> 
```


## Exiting the Program
**Format:** `exit`  
To exit the program, simply run the following command:
```
[] >>> exit 
Goodbye! 
```

## Accessing Help
**Format:** `help`  
Depending on your current workspace, you may get different help messages.  
The following shows the help message in the main workspace:

```
[] >>> help 

You can run the following commands in the workspace: 
> note 
    - Access all your notes that you have made.  
> schedule 
    - Access all your schedules that you have scheduled. 
> help 
    - Prints this. 
> quit 
    - Quits TermiNUS 

You can also run the following to quickly do certain tasks: 
> note add Water “Drinking more water will make me hydrated” 
> schedule view 

Running `help [command]` will print the help for the specific workspace. 

[] >>> 
```

## Advanced Usage of Commands
User can access workspace command directly without entering its environment. Seen below are some command examples.  

A workspace command is a command that will bring you to its own workspace. Current workspace command includes notes and schedules.  

Command syntax: <workspace> <available workspace command>  


Adding a note without entering the note workspace.
```
[] >>> note add “Remind Cabbin” “Cabbin was here” 
Note has been added! 
[] >>> 
```

Adding a schedule without entering the schedule workspace.  
```dtd
[] >>> schedule add “Module1 Tut” Thursday 10:00 https://zoom.us/test
You have added Module1 Tut’s scheduled zoom link!
[] >>> 
```

___  

## FAQ

___  

## Command Summary  

| **Action** | **Format, Examples** |
| ------------ | -------------  |
|**access note workspace**|`note`|
|**access schedule workspace**|`schedule`|
|**add**|`add "<name>" "<content>"` <br>e.g. `add note1 note_content`|
|**delete**|`delete <index>` <br>e.g. `delete 1`|
|**view**|`view` or `view {index}`<br>e.g. `view` or `view 1`|
|**help**|`help`|
|**exit**|`exit`|

___  

## Advanced Command Summary    

| **Action** | **Format, Examples** |
| ------------ | ------------- |
|**add note**|`note add "<name>" "<content>"` <br>e.g. `note add note1 note_content`|
|**add schedule**|`schedule add "<description>" "<day>" "<start_time>" "<zoom_link>"` <br>e.g. `schedule add “Module1 Tut” "Thursday" "10:00" "https://zoom.us/test"`|
|**delete note**|`note delete <index>` <br>e.g. `note delete 1`|
|**delete schedule**|`schedule delete <index>` <br>e.g. `schedule delete 1`|
|**view note**|`note view` or `note view {index}` <br>e.g. `note view 1`|
|**view schedule**|`schedule view` <br>e.g. `schedule view`|
















