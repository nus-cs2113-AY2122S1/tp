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

**[1. Quick Start](#1-quick-start)**

**[2. Features](#2-features)**
  * [2.1 new](#21-creating-a-trip-new)
  * [2.2 viewall](#22-viewing-all-trips-viewall)
  * [2.3 delete](#23-delete-a-trip-delete)
  * [2.4 edit](#24-edit-a-trip-edit)
  * [2.5 exit](#25-exiting-the-program-exit)
  * [2.6 save](#26-saving-your-trips)

**[3. FAQ](#3-faq)**

**[4. Command Summary](#4-command-summary)**


## 1. Quick Start
This sections provides a guide on how to get Traveller up and running on your computer.

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Traveller.zip` from [here](https://github.com/AY2122S1-CS2113T-W13-1/tp/releases).
3. Unzip the downloaded folder.
4. Navigate to the directory containing `Traveller.jar` in your command prompt and run `java -jar Traveller.jar`.
   You should see Traveller's welcome message as shown below.

![Welcome](./documentationPics/welcome.png)

5. Enter a command to execute it.
   Some of our basic commands are:
   * `new FamilyTrip2021 /from SIN /to MLY` : Creates a new trip called `FamilyTrip2021` from Singapore (`SIN`) to Malaysia (`MLY`).
   * `viewall` : Shows all your existing trips and their details.
   * `delete FamilyTrip2021` : Deletes the trip called `FamilyTrip2021`.
   * `exit` : Exits the program.

## 2. Features
This section provides details for all commands that Traveller supports.
For a quick summary of all commands, please click [here](#4.-command-summary) instead.

### 2.1. Creating a trip: `new`
Creates a new trip. 
The CLI then returns the shortest path from one point to another.

#### Format: `new TRIP_NAME /from START /to END`
   * The name of the trip must be one word. 
   * Keywords `/from` and `/to` must be included before the START and END destinations respectively.
   * `START` and `END` destinations must be countries included in `flightData/flights.txt`.

#### Usage Example: 
   * `new FamilyTrip2021 /from SIN /to MLY` creates a new trip called `FamilyTrip2021` from Singapore (`SIN`) to Malaysia (`MLY`).
   * `new Vacation /from SKR /to JPN` creates a new trip called `Vacation` from South Korea (`SKR`) to Japan (`JPN`).


### 2.2. Viewing all trips: `viewall`
Shows you details of all your existing trips.

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

### 2.3. Delete a trip: `delete`
Deletes an existing trip from the trip list.

#### Format: `delete TRIP_NAME`

#### Usage Example:
   * `delete FamilyTrip2021` deletes an existing trip called `FamilyTrip2021`.

### 2.4 Edit a trip: `edit`
Edits an existing trip from the trip list.

#### Format: `edit TRIP_NAME /from START /to END`
   * Format is similar to `new`.

#### Usage Example:
   * `edit FamilyTrip2021 /from SKR /to JPN` edits an existing trip called `FamilyTrip2021` to have new `START` and `END` destinations.

### 2.5 Exiting the program: `exit`
Exits the program.

#### Format: `exit`

### 2.6 Saving your trips
Existing trips are saved automatically whenever you exit the program.

## 3. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Transfer the entire folder to the other computer. 
       The folder should contain 3 items:
       * Traveller.jar
       * flightData folder with flights.txt inside
       * save folder with save.txt inside

**Q**: Why does it keep saying "Either of these nodes doesn't exist!"?

**A**: Traveller can only read specific words as destinations. Please check in `flightData/flights.txt` for all supported destinations and their specific wordings.

## 4. Command Summary

A summary of all commands available in Traveller is detailed here.

Action | Format
--- | ---
**new** | `new TRIP_NAME /from START /to END`
**viewall** | `viewall`
**delete** | `delete TRIP_NAME`
**edit** | `edit TRIP_NAME /from START /to END`
**exit** | `exit`

