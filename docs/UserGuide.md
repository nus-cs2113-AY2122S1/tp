
# Traveller User Guide

## Introduction
![Traveller](./documentationPics/logo.png)

Thank you for choosing to use Traveller!

Traveller is a travel planner app that is designed to help holidaymakers like you plan your overseas trips with ease.

It is created for individuals who prefer to use a Command Line Interface (CLI) over a Graphical User Interface (GUI), 
while still retaining the ease of use of a GUI.

Use Traveller so that you can plan your trips with ease and focus on what matters most: Fun!

### What is a Command Line Interface (CLI)?
A CLI is a way of interacting with applications via lines of text, instead of the usual way of clicking on the application.
Each computer will have their own CLI, such as the Windows' Powershell and the MacOS's Terminal.

### What is this guide for?
This guide's purpose is to help users like you understand how to use the Traveller application to its fullest potential.

For users who can't wait to start using Traveller, see [here](#1-quick-start) for a quick guide on how to set it up.
The [command summary](#4-command-summary) provides you with a summary of the various functionalities currently 
supported by Traveller too.

For users who are seeking to understand the full functionality of Traveller, details, along with tips and tricks, of 
each functionality are explained in the [features](#2-features) section.

### Legend

|Icon|Explanation|
|:---:|:---:|
|![](documentationPics/info.png)|Shows how this user guide is formatted.|
|![](documentationPics/tip.png)|Shows useful tips when using Traveller.|
|![](documentationPics/warning.png)|Shows potential problems when using Traveller.|

---

## Contents Table

* [1. Quick Start](#1-quick-start)
* [2. Features](#2-features)
  * [2.1. help](#21-getting-help-help)
  * [2.2. new](#22-creating-a-trip-new)
  * [2.3. add-day](#23-adding-a-day-to-trip-add-day)
  * [2.4. add-item](#24-adding-an-item-to-a-day-add-item)
  * [2.5. view](#25-viewing-trips-view)
  * [2.6. delete](#26-delete-a-trip-delete)
  * [2.7. delete-day](#27-deleting-a-day-from-a-trip-delete-day)
  * [2.8. delete-item](#28-deleting-an-item-from-a-day-delete-item)
  * [2.9. edit](#29-edit-a-trip-edit)
  * [2.10. edit-item](#210-edit-an-item-edit-item)
  * [2.11. search-item](#211-searching-for-an-item-search-item)
  * [2.12. shortest-dist](#212-shortest-distance-shortest-dist)
  * [2.13. shortest-cost](#213-least-cost-shortest-cost)
  * [2.14. edit-map](#214-edit-distances-in-map-edit-map)
  * [2.15. exit](#215-exiting-the-program-exit)
* [3. FAQ](#3-faq)
* [4. Command Summary](#4-command-summary)

<br/>

## 1. Quick Start
This section provides a guide on how to get Traveller up and running on your computer.

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Traveller.zip` from [here](https://github.com/AY2122S1-CS2113T-W13-1/tp/releases).
3. Unzip the downloaded folder in an empty directory of your choice.
4. Navigate to the directory containing `Traveller.jar` in your command prompt and run `java -jar Traveller.jar`.
   You should see Traveller's welcome message as shown below.

![Welcome](./documentationPics/welcome.png)

5. Enter a command.

   Some of our basic commands are:
   * `new FamilyTrip2021 /from SIN /to MLY` : Creates a new trip called `FamilyTrip2021` from Singapore (`SIN`) to Malaysia (`MLY`).
   * `view FamilyTrip2021` : Shows your existing trips and their details.
   * `delete FamilyTrip2021` : Deletes the trip called `FamilyTrip2021`.
   * `exit` : Exits the program.

<br/>

## 2. Features
This section provides details for all commands that Traveller supports.

For a quick summary of all commands, please click [here](#4-command-summary) instead.

> ![](documentationPics/info.png) As Traveller is a CLI based app, what and how you type your commands is *very important*.
> Each command has its specific format so that Traveller can understand what you want to do!

<br/>

### 2.1. Getting help: `help`
The help command would return the basic commands that are supported by Traveller.
The purpose of the help command is to ensure that even if there is no internet connection, the user is able to utilise the CLI without having to refer to the user guide.

#### Format: `help`

<br/>

### 2.2. Creating a trip: `new`
Creates a new trip. 
The CLI then returns the shortest path from one point to another.

#### Format: `new TRIP_NAME /from START /to END`
   * The name of the trip must be one word. 
   * Keywords `/from` and `/to` must be included before the START and END destinations respectively.
   * `START` and `END` destinations must be countries included in *flightData/dist.txt* or *flightData/cost.txt*.

#### Usage Example:

```
____________________________________________________________
$ new FamilyTrip2021 /from SIN /to MLY
____________________________________________________________
	You have just created a new trip called FamilyTrip2021.
____________________________________________________________
```

> ![](documentationPics/warning.png) If you encounter an issue where either your Start or End destinations are not recognised by Traveller.
> Take a look in *flightData/dist.txt* or *flightData/cost.txt* to see how Traveller recognises them!

<br/>

### 2.3. Adding a day to Trip: `add-day`
Your overseas trip may span multiple days.

This command allows you to create any number of days in your trip.

#### Format: `add-day TRIP_NAME /day NUMBER_OF_DAYS`

#### Usage Example:

```
____________________________________________________________
$ add-day myTrip /day 3
____________________________________________________________
	Added 3 days to trip trip1.
____________________________________________________________
```

<br/>

### 2.4. Adding an item to a Day: `add-item`
In each day of your trip, you will want to have multiple items planned out, such as visiting a museum or going to the
beach.

Use this command to add an item to a day of your trip.

#### Format: `add-item TRIP_NAME /day DAY_INDEX /time ITEM_TIME /name ITEM_NAME`

#### Usage Example:

```
____________________________________________________________
$ add-item trip1 /day 0 /time 7pm /name Check-in to HolidayInn
____________________________________________________________
	Added a new item to day 0 of trip trip1.
____________________________________________________________
```

> ![](documentationPics/info.png) Days are 0-indexed based. That is, the first day of your trip is day 0.
> 
> ![](documentationPics/tip.png) If your item spans the whole day, just type in `All day` for the time field!

<br/>

### 2.5. Viewing trips: `view`
Shows you details of your existing trips.

#### Format: `view TRIP_NAME`

#### Usage Example:

```
____________________________________________________________
$ view myTrip
____________________________________________________________
	Here are all your trips: 
		TripName: myTrip
			 Origin: SIN
			 Destination: MLY
			 Path: [SIN, MLY]
			 Distances: [1.0]
			 Days: 
				Day 0: 
					0:	7pm		Check-in at HolidayInn
____________________________________________________________
```

> ![](documentationPics/tip.png) To view all trips, simply enter `view all`.

<br/>

### 2.6. Delete a trip: `delete`
Deletes an existing trip from the trip list.

#### Format: `delete TRIP_NAME`

#### Usage Example:
```
____________________________________________________________
$ delete myTrip
____________________________________________________________
	You have just deleted a trip called myTrip.
____________________________________________________________
```

<br/>

### 2.7. Deleting a day from a trip: `delete-day`
Deletes an existing day from a trip.

#### Format: `delete-day TRIP_NAME /day DAY_INDEX`

#### Usage Example:
```
____________________________________________________________
$ delete-day myTrip /day 0
____________________________________________________________
	You have just deleted day 0 of myTrip.
____________________________________________________________
```

<br/>

### 2.8. Deleting an item from a day: `delete-item`
Deletes an existing item from a trip.

#### Format: `delete-item TRIP_NAME /day DAY_INDEX /item ITEM_INDEX`

#### Usage Example:
```
____________________________________________________________
$ delete-item trip1 /day 1 /item 0
____________________________________________________________
	You have just deleted item 0 of trip1 day 1
____________________________________________________________
```

<br/>

### 2.9 Edit a trip: `edit`
Edits an existing trip from the trip list.

#### Format: `edit TRIP_NAME /from START /to END`
   * Format is similar to `new`.

#### Usage Example:
   * `edit FamilyTrip2021 /from SKR /to JPN` edits an existing trip called `FamilyTrip2021` to have new `START` and `END` destinations.

> ![](documentationPics/tip.png) Edit only allows you to change your Start and End destinations! 
> To change your trip itinerary use [edit-item](#210-edit-an-item-edit-item) instead!

<br/>

### 2.10. Edit an item: `edit-item`
Edits and updates existing item from a trip and updates it to a new corresponding item.

#### Format: `edit-item TRIP_NAME /day DAY_NUMBER /time NEW_TIME /name NEW_NAME /index ITEM_INDEX`

#### Usage Example:
```
_________________________________________________________________________________
$ edit-item trip1 /day 1 /time 9am /name later breakfast /index 1
_________________________________________________________________________________
	You have just edited item 1 on day 1 of trip1 to later breakfast at 9am
_________________________________________________________________________________
```

<br/>

### 2.11. Searching for an item: `search-item`
Searches for an item keyword from a trip and returns the resulting matching items.

#### Format: `search-item TRIP_NAME /day DAY_INDEX /key KEYWORD`

#### Usage Example:
```
_________________________________________________________________________________
$ search-item trip1 /day 1 /key n
_________________________________________________________________________________
	You have just search item keyword n on day 1 in trip called trip1
	
	Results: 
	1. 2pm		lunch
	2. 5pm		dinner
_________________________________________________________________________________
```

### 2.12. Shortest distance: `shortest-dist`
Returns the shortest distance from the source to destination country.

#### Format: `shortest-dist /from SOURCE_COUNTRY /to DESTINATION_COUNTRY`

#### Usage Example:
```
____________________________________________________________
$ shortest-dist /from SIN /to JPN
____________________________________________________________
	The shortest distance from SIN to JPN is 6.0.
	Distance breakdown: 
	1.0
	5.0
____________________________________________________________
```

### 2.13. Least cost: `shortest-cost`
Returns the least expensive flight path from the source to destination country.

#### Format: `shortest-cost /from SOURCE_COUNTRY /to DESTINATION_COUNTRY`

#### Usage Example:
```
____________________________________________________________
$ shortest-cost /from SIN /to JPN
____________________________________________________________
	The least cost from SIN to JPN is 550.0.
	Cost breakdown: 
	150.0
	400.0
____________________________________________________________
```

### 2.14. Edit Distances in Map: `edit-map`
Edits and updates the distance from the source to destination country.

#### Format: `edit-map /from SOURCE_COUNTRY /to DESTINATION_COUNTRY /dist NEW_DISTANCE`

#### Usage Example:
```
____________________________________________________________
$ edit-map /from SIN /to MLY /dist 2.0
____________________________________________________________
	The distance from SIN to MLY is updated to 2.0.
____________________________________________________________
```


### 2.15 Exiting the program: `exit`
Exits the program.

#### Format: `exit`

> ![](documentationPics/warning.png) Properly exiting Traveller with the `exit` command is **CRUCIAL** in 
> ensuring that your trips are saved.

<br/>

## 3. FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Transfer the entire folder containing the items listed below to the other computer. 
   * *Traveller.jar*
   * *flightData* folder with *dist.txt* and *cost.txt* inside
   * *save* folder with *save.txt* inside

**Q**: Why does it keep saying "Either of these nodes doesn't exist!"?

**A**: Traveller can only read specific words as destinations. 
Please check in *flightData/dist.txt* or *flightData/cost.txt* for all supported destinations and their specific wordings.

<br/>

## 4. Command Summary

A summary of all commands available in Traveller, and how you can use them, is detailed here.

Action | Format
--- | ---
**help**| `help`
**new** | `new TRIP_NAME /from START /to END`
**add-day**| `add-day TRIP_NAME /day NUMBER_OF_DAYS`
**add-item**|`add-item TRIP_NAME /day DAY_INDEX /time ITEM_TIME /name ITEM_NAME`
**view** | `view TRIP_NAME`
**delete** | `delete TRIP_NAME`
**delete-day** | `delete-day TRIP_NAME /day DAY_INDEX`
**delete-item** | `delete-item TRIP_NAME /day DAY_INDEX /item ITEM_INDEX`
**edit** | `edit TRIP_NAME /from START /to END`
**edit-item** | `edit-item TRIP_NAME /day DAY_NUMBER /time NEW_ITEM_TIME /name NEW_ITEM_NAME /index ITEM_INDEX`
**search-item** |  `search-item TRIP_NAME /day DAY_INDEX /key KEYWORD`
**shortest-dist** | `shortest-dist /from SOURCE_COUNTRY /to DESTINATION_COUNTRY`
**shortest-cost** | `shortest-cost /from SOURCE_COUNTRY /to DESTINATION_COUNTRY`
**edit-map** | `edit-map /from SOURCE_COUNTRY /to DESTINATION_COUNTRY /dist DISTANCE`
**exit** | `exit`

