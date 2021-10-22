# TermiNUS User Guide

## Introduction

TermiNUS is a CLI (command line interface) program for NUS Students who wish to consolidate their 
NUS academic materials such as zoom links, questions and notes for the modules that they are taking. 
With TermiNUS, it aims to aid Students and improve their learning experiences while studying in NUS.

## Purpose

This documents aims to provide you with instruction on how to use `TermiNUS` and tips & tricks 
included to improve your experiences while using it. The document will bring you through a 
detailed guide on all existing commands as well as aiding you in installing `TerminNUS`.

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
* [Section: Question](#Section:-Question)
    * [Accessing Question : `note`](#Accessing-Note)
    * [Adding a Question : `add "<question>" "<answer>"`](#Adding-a-Question)
    * [Delete a Question : `delete <index>`](#Delete-a-Question)
    * [View Question : `view {index}`](#View-Question)
    * [Testing Yourself with Active Recall: `test {count}`](#Testing-Yourself-with-Active-Recall)
* [Exiting the Program: `exit`](#Exiting-the-Program)
* [Accessing Help: `help`](#Accessing-Help)
* [FAQ](#faq)
* [Command Summary](#Command-Summary)
* [Advanced Command Summary](#Advanced-Command-Summary)

## Getting Started

1. Ensure that you have Java 11 or above installed. You can 
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


## Section: Question

### Accessing Question
**Format:** `question`  
Accessing the question workspace
After running the question command, you can see the following:

```
[] >>> question

You have 0 question(s) in this workspace.

Type any of the following to get started:
> add
> exit
> help
> view
> test
> back
> delete
 
[question] >>> 
```

### Adding a Question
**Format:** `add "<question>" "<answer>"`  
Adding a question when in the question workspace
```
[question] >>> add "What is 1+1?" "2" 
Your question on 'What is 1+1?' has been added!
[question] >>> 
```

### Delete a Question
**Format:** `delete <index>`  
Deletes the specified question given by its index.
```
[question] >>> delete 1
Your question on 'What is 1+1?' has been deleted!
[question] >>> 
```

### View Question
**Format:** `view` or `view {index}`  
Two ways to use this command simply running view or view [index]
View by itself will list all questions
```
[question] >>> view 
List of Content
---------------
1. What is segmentation?
2. What is paging?
3. What is the pro of Fixed Partitioning in Contiguous Memory allocation?

Rerun the same command with an index behind to view the content.
[question] >>> 
```

The second way to use view is with an index view [index]

```
[question] >>> view 1 
Name: What is segmentation?
Content:
Placing different data regions into different frames

[question] >>> 
```

### Testing Yourself with Active Recall
**Format:** `test` or `test {count}`  
You can start an Active Recall session by running the `test` command.

By default, it will test 10 questions (or less if there are not enough questions). You may specify 
how many questions you wish to run in that session by keying in the number of questions after the 
`test` command.

When you begin, you will be prompted with the following (do note that the question pool may be 
smaller if there are not enough questions in the workspace). Press the <kbd>Enter</kbd> key to
start.

```
[question] >>> test 3
---[Active Recall]---

We will be starting your active recall training session.
This session will consist of 3 questions.

When you are ready, press [Enter] to continue.
```

The first question will be displayed, and once you are ready to reveal the answer, press the 
<kbd>Enter</kbd> key again.

```
---

Question:
What is segmentation?

When you are ready, press [Enter] to continue.
```

After revealing the answer, provide feedback to TermiNUS to allow it to know if the question should 
appear more often in the future. 

- Key in `1` if you felt it was easy.
- Key in `2` if you felt it was normal.
- Key in `3` if you felt it was hard.
- Key in `E` if you wish to exit the session.

```
You took 172 seconds to reveal the answer.

Answer:
Placing different data regions into different frames

How did you find the question? (Compare against past attempts if any)
[1] Easy; [2] Normal / Same; [3] Hard; [E] Exit
[1/2/3/E] >> 
```

After choosing your difficulty, you may proceed to reveal the next question by pressing 
<kbd>Enter</kbd> key again.

```
[1/2/3/E] >> 2

When you are ready, press [Enter] to continue.
```

Once the question pool is empty, or when you decided to stop, TermiNUS will drop you back into the 
command prompt.

```
This training session has ended.
Returning you back to main program.
[question] >>> 
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
















