# **TourPlanner User Guide**

TourPlanner is a desktop application meant for employees of travel agencies. 
Its main purpose is to manage clients, flights, accommodations and client packages data, 
optimized for use via a Command Line Interface (CLI). 
If you can type fast, this application can allow one to access relevant travel information faster than traditional GUI applications.

<br>

## **Quick Start**

<hr>

1. Ensure you have Java 11 installed in your Computer

2. Download the latest database db.jar from here (link tbd)

3. Copy the file to the folder you want to use as the home folder for your database.

4. Double-click the file to start the app.

5. Type the command in the command box and press Enter to execute.

<br>

## Introduction of Data Types

<hr>

Throughout the user guide, you may see observe many commands have a
```[DATA_TYPE]``` parameter. This parameter is to be specified right after declaring
the command to use.

There are 4 data types that are stored in TourPlanner:

* ```-c``` Clients
* ```-t``` Tours
* ```-f``` Flights
* ```-p``` Client Package

<br>

Examples:

* ```add -t JPN /n Japan Basic Tour /p 1500 ``` calls for a <u>tour</u> to be added.
* ```list -p ``` calls for all available <u>client packages</u> to be listed out.

## Features

<hr>

## Adding data types:  ```add```

<br>

<hr>

## Cutting data types:  ```cut```

<br>

<hr>

## **Querying Data Types**

<hr>

The user is also able to view all entries of a specific data type, as well as find specific entrie(s) based their codes.

<br>

## Listing data types: ```list```

Shows a list of all available entries of a specific data type, along with their respective fields. 


Format: ```list [DATA_TYPE]```

(Please refer to <u>Introduction to Data Types</u> on the syntax of ```DATA_TYPE```)

<br>

Examples:

* ```list -c``` lists out all available client entries.

An output of this format will be shown:

```
Here is a list of all clients:
1. Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

2. Client ID: c002
Name: Betty
Contact Number: 12223444
Email: betty.com

Total Clients: 2
```

<br>

* ```list -f``` lists out all available flight entries.

An output of this format will be shown:

```
Here is a list of all flights:
1. Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/2021 18:00
Return Flight: SG, 21/10/2021 03:00

2. Flight ID: SQ-KOR
Departure Flight: KOR, 23/10/2021 18:00
Return Flight: SG, 30/10/2021 03:00

Total Flights: 2
```

<br>

## Finding data types: ```find```

###Find client

Finds specific client(s) based on a particular substring. It will return client(s) if their name contains 
the substring. Note that the substring is case-insensitive. In addition, it will return the found 
clients' subscribed packages.

<br>

Format: ```find -c [SUBSTRING]```

Examples:

* ```find -c Adam``` finds clients that contain 'Adam' in their name.

An output of this format will be shown:

```
This is the client(s) that matches your search
1. Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

Package ID: p123

Client: 
Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

Tour: 
Name: australiaromance
Code: aus1369
Price per pax: $1300.00

Flight: 
Flight ID: MSIA-KOR1
Departure Flight: MSIA, 29/10/21 12:00
Return Flight: KOR, 24/6/21 02:00
```
In addition, ```find -c ad``` will yield the same results, since "ad" is
contained in "Adam".
<br>

###Find tour / flight / package

Finds a specific entry based on a particular code.
In addition for tours and flights, it will show the names of the 
passenger(s) who are assigned to them.

(Please refer to <u>Introduction to Data Types</u> on the syntax of ```DATA_TYPE```)

<br>

Format: ```find [DATA_TYPE] [CODE]```

Examples:

* ```find -t JPN``` finds a particular tour with code 'JPN'. 
It also shows the clients who are subscribed to said tour.

An output of this format will be shown:

```
This is the tour that matches your search
Name: Japan Basic Tour
Code: JPN
Price per pax: $1500.00


Subscribed Clients:
Adam

Total Subscribed Clients: 1
```

<br>

* ```find -f SQ-JPN``` finds a particular flight with code 'SQ-JPN'.
It also shows the clients who are passengers to said flight.

An output of this format will be shown:

```
This is the flight that matches your search
Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/2021 18:00
Return Flight: SG, 21/10/2021 03:00


Passengers:
Betty

Total Passengers: 1
```
<br>

## Exit application: ```exit```

Exits the application.

<br>

Format: ```bye```

The following output will be shown:

```Thanks for using TourPlanner. Goodbye!```

<br>
<hr>
