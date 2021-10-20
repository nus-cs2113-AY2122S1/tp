# Traveller User Guide

## Introduction
![Traveller](./documentationPics/logo.png)

Traveller is a travel planner app that is designed to help holidaymakers like you optimise your trips!
It is created for individuals who prefer to use a Command Line Interface (CLI) over a Graphical User Interface (GUI), 
while still retaining the ease of use of a GUI.
Use Traveller so that you can plan your trips with ease and focus on what matters most: Fun!

### What is a Command Line Interface (CLI)?
A CLI is a way of interacting with applications via lines of text, instead of the usual way of clicking on the application.
Each computer will have their own CLI, such as the window's Powershell and the MacOS's Terminal.

### Legend

|Icon|Explanation|
|:---:|:---:|
|![](documentationPics/info.png)|Shows how this user guide is formatted.|
|![](documentationPics/tip.png)|Shows useful tips when using Traveller.|
|![](documentationPics/warning.png)|Shows potential problems when using Traveller.|

---

## Contents Table

1. [Quick Start](#1.-quick-start)
2. [Features](#2.-features)
3. [FAQ](#3.-FAQ)
4. [Command Summary](#4.-command-summary)

## 1. Quick Start
This sections provides a guide on how to get Traveller up and running on your computer.

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Traveller` from [here](https://github.com/AY2122S1-CS2113T-W13-1/tp).
3. Unzip the downloaded file.
4. Navigate to the directory containing `Traveller.java` and run `java -jar Traveller.java`.

## 2. Features
This section provides details for all commands that Traveller supports.
For a quick summary of all commands, please click [here](#4.-command-summary) instead.

### 2.1. Creating a trip: `new`
insert elaboration here

#### Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

#### Usage Example:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### 2.1. Viewing all trips: `viewall`
Shows you details of all your trips that is saved in Traveller.

#### Format: `viewall`

#### Usage Example:

Input > `viewall`

Expected Output >
```
	Here are all your trips: 
		TripName: myTrip
			 Origin: SIN
			 Destination: MLY
			 Path: [SIN, MLY]
			 Distances: [1.0]
			 Days: 
```


## 3. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You transfer it somehow~~

## 4. Command Summary

A summary of all commands available in Traveller is detailed here.

* Create new trip `new TRIP_NAME /from START_COUNTRY /to END_COUNTRY`
