# **TourPlanner User Guide**

TourPlanner is a desktop application meant for employees of travel agencies. Its main purpose is to manage clients,
flights, accommodations and client packages data, optimized for use via a Command Line Interface (CLI). If you can type
fast, this application can allow one to access relevant travel information faster than traditional GUI applications.

<br>

## **Quick Start**

<hr>

1. Ensure you have Java 11 installed in your Computer

2. Download the latest database db.jar from here (link tbd)

3. Copy the file to the folder you want to use as the home folder for your database.

4. Double-click the file to start the app.

5. Type the command in the command box and press Enter to execute.

<br>

## Introduction to Data Types

<hr>

Throughout the user guide, you may observe that many commands have a
```[DATA_TYPE]``` parameter. This parameter is to be specified right after declaring the command to use.

There are 4 data types that are stored in TourPlanner:

* ```-c``` Clients
* ```-t``` Tours
* ```-f``` Flights
* ```-p``` Client Package

<br>

Examples:

* ```add -t JPN /n Japan Basic Tour /p 1500 ``` calls for a <u>tour</u> to be added.
* ```list -p ``` calls for all available <u>client packages</u> to be listed out.

## Introduction to Data Fields

<hr>

Throughout the user guide, you may observe that many commands have a ```[DATA_FIELDS]``` parameter.
The ```[DATA_FIELDS]``` parameter represents the compulsory information fields that serves to describe the command in
more detail.

One data field is represented in the following format: ```/PREFIX INFO```

Data fields can be viewed as supporting documents to the main command to be executed.

These fields are mandatory. Let's say a client is added without his **name**, or **contact details**. Having these empty
fields reduces the utility of the program, since certain meaningful operations cannot be performed. Case in point, one
will not be able to query the client by name.

These fields paint a complete picture of the different data types. Intuitively, clients have *key information fields*
such as **name**, **contact number** and **email**.

Examples of data fields in commands:

* Add client:
    * ```/n NAME```
    * ```/cn CONTACT_NUMBER```
    * ```/m EMAIL```
* Sort tours:
    * ```/p``` - sorts tours by budget
    * ```/d``` - sorts tours by departure date and time

## Features

:information_source: Do refer to <u>Introduction to Data Fields</u> on the purpose and syntax of ```DATA_FIELDS``` for
more clarity!

<hr>

## Adding data types:  ```add```

You are able to add information of all data types into the database, specified by mandatory fields to enter for each
entry.

Format: ```add [DATA_TYPE] [DATA_FIELDS]```

### Adding clients into the database: ```add -c```

Format: ```add [DATA_TYPE] CLIENT_ID [DATA_FIELDS]```

These are your existing or potential customers. In this industry of tour planning, your livelihoods depend on them.

Mandatory data fields:

* Client's name - ```/n NAME```
* Client's contact number - ```/cn CONTACT_NUMBER```
* Client's email address - ```/m EMAIL```

:exclamation: Note that the given contact number must contain only numbers from 0 to 9, or TourPlanner will reject the
entry.

### Adding flights into the database: ```add -f```

Format: ```add [DATA_TYPE] FLIGHT_ID [DATA_FIELDS]```

Mandatory data fields:

* Flight's departure destination - ```/d DEPARTURE_DESTINATION```
* Flight's return destination - ```/r RETURN_DESTINATION```
* Flight's departure date and time - ```/dd DEPARTURE_TIME```
* Flight's return date and time - ```/rd RETURN_TIME```

:exclamation: Note that the given date and times should be of the format: ```d/M/yy HH:mm```

(Please refer to <u>Introduction to Data Fields</u> on the purpose and syntax of ```DATA_FIELDS```)

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

### Find client

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

### Find tour / flight / package

Finds a specific entry based on a particular code. In addition for tours and flights, it will show the names of the
passenger(s) who are assigned to them.

(Please refer to <u>Introduction to Data Types</u> on the syntax of ```DATA_TYPE```)

<br>

Format: ```find [DATA_TYPE] [CODE]```

Examples:

* ```find -t JPN``` finds a particular tour with code 'JPN'. It also shows the clients who are subscribed to said tour.

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

* ```find -f SQ-JPN``` finds a particular flight with code 'SQ-JPN'. It also shows the clients who are passengers to
  said flight.

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

## Sort clientpackages

Sort the list of clientpackages

Format: ```sort [DATA_TYPE] [DATA_FIELDS]```

### Sorted by returning flight times

Format: ```sort -f /r```
Examples:

Output:

```
Sorted by returning flight times
1. Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00

2. Flight ID: SQ-ZWM
Departure Flight: ZWM, 5/11/21 09:00
Return Flight: SG, 7/11/21 15:00

3. Flight ID: SQ-KOR
Departure Flight: KOR, 23/10/21 08:00
Return Flight: SG, 30/11/21 03:00
```

### Sorted by departing flight times

Format: ```sort -f /d```
Examples:

Output:

```
Sorted by departing flight times
1. Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00

2. Flight ID: SQ-KOR
Departure Flight: KOR, 23/10/21 08:00
Return Flight: SG, 30/11/21 03:00

3. Flight ID: SQ-ZWM
Departure Flight: ZWM, 5/11/21 09:00
Return Flight: SG, 7/11/21 15:00
```

### Sorted by flight id alphabetically

Format: ```sort -f /id```
Examples:

Output:

```
Sorted by departing flight times
Sorted by flight id alphabetically
1. Flight ID: SQ-JPN
Departure Flight: JPN, 20/10/21 18:00
Return Flight: SG, 21/10/21 03:00

2. Flight ID: SQ-KOR
Departure Flight: KOR, 23/10/21 08:00
Return Flight: SG, 30/11/21 03:00

3. Flight ID: SQ-ZWM
Departure Flight: ZWM, 5/11/21 09:00
Return Flight: SG, 7/11/21 15:00
```

### Sorted by tour id alphabetically

Format: ```sort -t /id```
Examples:

Output:

```
Sorted by tour id alphabetically
1. Name: Japan Basic Tour
Code: JPN1
Price per pax: $1500.00

2. Name: Japan Food Tour
Code: JPN2
Price per pax: $4000.00

3. Name: Korea Cultural Tour
Code: KOR
Price per pax: $3000.00

4. Name: Zimbabwe Tour
Code: ZWM
Price per pax: $1700.00
```

### Sorted by price in ascending order

Format: ```sort -t /id```
Examples:

Output:

```
Sorted by tour id alphabetically
1. Name: Japan Basic Tour
Code: JPN1
Price per pax: $1500.00

2. Name: Zimbabwe Tour
Code: ZWM
Price per pax: $1700.00

3. Name: Korea Cultural Tour
Code: KOR
Price per pax: $3000.00

4. Name: Japan Food Tour
Code: JPN2
Price per pax: $4000.00
```

### Sorted by client name alphabetically

Format: ```sort -c /n```
Examples:

Output:

```
Sorted by client name alphabetically
1. Client ID: c001
Name: Bo Tuan
Contact Number: 93338333
Email: borangutuan@mail.com

2. Client ID: c004
Name: ChengXu
Contact Number: 10101010
Email: demonshaha@mail.com

3. Client ID: c002
Name: Sam
Contact Number: 12223444
Email: sam@mail.com

4. Client ID: c003
Name: Wayne
Contact Number: 56667888
Email: wendy@mail.com
```

### Sorted by client id alphabetically

Format: ```sort -c /id```
Examples:

Output:

```
orted by client id alphabetically
1. Client ID: c001
Name: Bo Tuan
Contact Number: 93338333
Email: borangutuan@mail.com

2. Client ID: c002
Name: Sam
Contact Number: 12223444
Email: sam@mail.com

3. Client ID: c003
Name: Wayne
Contact Number: 56667888
Email: wendy@mail.com

4. Client ID: c004
Name: ChengXu
Contact Number: 10101010
```
## Exit application: ```exit```

Exits the application.

<br>

Format: ```bye```

The following output will be shown:

```Thanks for using TourPlanner. Goodbye!```

<br>
<hr>
