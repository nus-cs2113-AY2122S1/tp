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
* ```-p``` Client Package: package that contains the client along with the tour and flight they have opted for.

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
Deletes entry of a certain data type.

###Cut Client Package
Deletes the client package from the list of packages.

Format: `cut -p DATA_ID`
* Deletes the client package with specified DATA_ID.

<br>
Examples:

* `cut -p p001` deletes client package with id 'p001', where 'p001' contains client 'c001', tour 'JPN1' and flight 'SQ-JPN'

An output of this format will be shown:
```
Client has been deleted:
Package ID: p001

Client: 
Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

Tour: 
Name: Japan Basic Tour
Code: JPN1
Price per pax: $1500.00

Flight: 
Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00
```

<br>

###Cut Client / Tour / Flight

Format: `cut [DATA_TYPE] DATA_ID`
* Deletes the entry of DATA_TYPE with specified DATA_ID.
* Deletes all client packages that contains the specific entry
* Please refer to <u>Introduction to Data Types</u> on the syntax of ```DATA_TYPE```

<br>
Examples:

* `cut -c c001` deletes client with id 'c001', and all client packages that contains client 'c001'.

An output of this format will be shown:

Deleting client:
```
Client has been deleted:
Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com
```
Deleting all related client packages:
```
Client Package has been deleted:
Package ID: p001

Client: 
Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

Tour: 
Name: Japan Basic Tour
Code: JPN1
Price per pax: $1500.00

Flight: 
Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00
```

<br>

* `cut -t KOR` deletes tour with id 'KOR', and all client packages that contains tour 'KOR'.

An output of this format will be shown:

Deleting tour:
```
Tour has been deleted:
Name: Korea Cultural Tour
Code: KOR
Price per pax: $3000.00
```
Deleting all related client packages:
```
Client Package has been deleted:
Package ID: p001

Client: 
Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com

Tour: 
Name: Korea Cultural Tour
Code: KOR
Price per pax: $3000.00

Flight: 
Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00
```

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

Finds specific client(s) based on a particular name. It will return multiple clients if they have the same name.

<br>

Format: ```find -c [NAME]```

Examples:

* ```find -c Adam``` finds clients with 'Adam' as their name.

An output of this format will be shown:

```
This is the client(s) that matches your search
1. Client ID: c001
Name: Adam
Contact Number: 93338333
Email: adam@mail.com
```

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
