# User Guide

## Introduction

SchedUrMods! Your one-stop app for maninging all your daily tasks!

## Quick Start

1. Ensure you have Java 11 installed in your Computer.
1. Download the latest NUSMODS_Scheduler.jar from <embedded text link>.
1. Run <java -jar NUSMODS_Scheduler.jar> command in the terminal to start the program.

## Features 

{Give detailed description of each feature}

### Adding a task: 'add'
Adds a new item to the list of tasks.

Format:
* `add <task desciption> --type todo`
* `add <task desciption> --type deadline --due dd-mm-yyyy hh:mm:ss`
* `add <task desciption> --type event --start dd-mm-yyyy hh:mm:ss --end dd-mm-yyyy hh:mm:ss`

Optional arguments:
* `--recur {daily | weekly | monthly | yearly}`
* `--priority {low | medium | high}`

Example of usage: 

`add read book --type todo --recur DAILY --priority LOW --doOn 14-10-2021 02:30:10`

`add return book --type deadline --recur WEEKLY --priority MEDIUM --due 15-10-2021 03:30:20`
  
`add project meeting --type event --recur MONTHLY --priority HIGH --start 16-10-2021 04:30:30 --end 16-10-2021 05:30:40`
  
### Listing your tasks: 'list'
Lists all the tasks you have in your task manager.

## FAQ

**Q**: How do I transfer my data to another computer? 
**A**: Our team is dilligently working on this feature.

## Command Summary

{Give a 'cheat sheet' of commands here}
