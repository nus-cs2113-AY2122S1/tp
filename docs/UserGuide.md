# **TourPlanner User Guide**

<br>
TourPlanner is a desktop application meant for employees of travel agencies. Its main purpose is to manage clients,
flights, accommodations and client packages data, optimized for use via a Command Line Interface (CLI). If you can type
fast, this application can allow one to access relevant travel information faster than traditional GUI applications.

<br>

* Table of Contents{:toc}

<br>

## **Quick Start** ##

<hr>

1. Ensure you have Java 11 installed in your Computer

2. Download the latest database db.jar from here (link tbd)

3. Copy the file to the folder you want to use as the home folder for your database.

4. Open a command window in that folder

5. Run the command ```java -jar TourPlanner.jar``` to start the program

6. Type the command in the command box and press Enter to execute

<br>

## Introduction to Data Types ##

<hr>

Throughout the user guide, you may observe that many commands have a
```[DATA_TYPE]``` parameter. This parameter is to be specified right after declaring the command to use.

There are 4 data types that are stored in TourPlanner:

* ```-c``` Clients
* ```-t``` Tours
* ```-f``` Flights
* ```-p``` Client Package: package that contains the client along with the tour and flight they have opted for.


Examples of data types in commands:

* ```add -t JPN /n Japan Basic Tour /p 1500 ``` calls for a <u>tour</u> to be added.
* ```list -p ``` calls for all available <u>client packages</u> to be listed out.

<br>

## Introduction to Data Fields

<hr>

Throughout the user guide, you may observe that many commands have a ```[DATA_FIELDS]``` parameter.
The ```[DATA_FIELDS]``` parameter represents the compulsory information fields that serves to describe the command in
more detail.

One data field is represented in the following format: ```/PREFIX INFO```

Data fields can be viewed as supporting documents to the main command to be executed.

These fields are *mandatory*. Let's say a client is added without his **name**, or **contact details**. Having these
empty fields reduces the utility of the program, since certain meaningful operations cannot be performed. Case in point,
one will not be able to query the client by name.

These fields paint a complete picture of the different data types. Intuitively, clients have *key information fields*
such as **name**, **contact number** and **email**.

Examples of data fields in commands:

* Add client:
    * ```/n NAME```
    * ```/cn CONTACT_NUMBER```
    * ```/m EMAIL```
* Sort tours:
    * ```/p``` - sorts tours by *price*
    * ```/d``` - sorts tours by *departure date and time*

<br>

## Viewing help ``help``
<hr>

Shows a message with the link to this user guide.

<br>

Format: ```help```

The following output will be shown:

(insert output)

<br>

## Adding / Cutting Data Types
<hr>

:information_source:Please refer to <u>Introduction to Data Types</u> and <u>Introduction to Data Fields</u> on
the purpose and syntax of ```DATA_TYPES``` and ```DATA_FIELDS```.

<br>

## :heavy_plus_sign: Adding data types:  ```add```

You are able to add information of all data types into the database, specified by mandatory fields to enter for each
entry.

Format: ```add [DATA_TYPE] [DATA_FIELDS]```

<br>

### Adding clients into database: ```add -c```

Format: ```add [DATA_TYPE] CLIENT_ID [DATA_FIELDS]```

These are your existing or potential customers. In this industry of tour planning, your livelihoods depend on them.

Mandatory data fields:

* Client ID - ```CLIENT_ID```
* Client name - ```/n NAME```
* Client contact number - ```/cn CONTACT_NUMBER```
* Client email address - ```/m EMAIL```

:exclamation: Note that the given contact number must contain only numbers from 0 to 9, or TourPlanner will reject the
entry.

Example:

* `add -c c001 /n Bo Tuan /cn 91234567 /m bobotea@gmail.com`

Adds *Bo Tuan*, contact number *91234567* and email
*bobotea@gmail.com*, as client ID *c001*.

* `add -c c002 /n Wayne /m winnie@gmail.com /cn 92468024`

Adds *Wayne*, contact number *92468024* and email
*winnie@gmail.com*, as client ID *c002*.

<br>

### Adding flights into database: ```add -f```

Format: ```add [DATA_TYPE] FLIGHT_ID [DATA_FIELDS]```

Mandatory data fields:

* Flight ID - ```FLIGHT_ID```
* Flight departure destination - ```/d DEPARTURE_DESTINATION```
* Flight return destination - ```/r RETURN_DESTINATION```
* Flight departure date and time - ```/dd DEPARTURE_TIME```
* Flight return date and time - ```/rd RETURN_TIME```

:exclamation: Note that the given date and times should be of the format: ```d/M/yy HH:mm```

Example:

* `add -f SQ-JPN1 /d Japan /r Singapore /dd 29/10/21 13:00 /rd 5/11/21 02:00`

Add flights from _Singapore_ to _Japan_
and back, departing from Singapore at _1pm, 29 Oct 2021_ and returning to Singapore at _5 Nov 2021, 2am_. Stored in the
database as ID: _SQ-JPN1_.

* `add -f SQ-KOR1 /d Korea /dd 2/5/22 11:00 /r Singapore /rd 15/5/22 23:00`

Add flights from _Singapore_ to _Korea_
and back, departing from Korea at _11am, 2 May 2022_ and returning to Singapore at _15 May 2022, 11pm_. Stored in the
database as ID: _SQ-KOR1_.

<br>

### Adding tours into database: ```add -t```

Format: ```add [DATA_TYPE] TOUR_ID [DATA_FIELDS]```

Mandatory data fields:

* Tour ID - ```TOUR_ID```
* Tour name - ```/n TOUR_NAME```
* Tour price - ```/p TOUR_PRICE```

:exclamation: Note that the given price should be a numerical value.

Example:

* `add -t t001 /n AustralianRomance /p 1500`

Adds _AustralianRomance_, which costs _$1500_, as tour _t001_.

* `add -t t002 /p 2300 /n KoreanWonderland`

Adds _KoreanWonderland_, which costs _$2300_, as tour _t002_.

<br>

### Adding ClientPackage into database: ```add -p```

ClientPackage acts as a master list of details for each client, to have an overview of the client upon querying. Each
package represents one client, with his or her respective tour and flight details.

Format: ```add [DATA_TYPE] PACKAGE_ID [DATA_FIELDS]```

Mandatory data fields:

* Package ID - ```PACKAGE_ID```
* Client ID - ```/c CLIENT_ID```
* FLIGHT_ID - ```/f FLIGHT_ID```
* Tour ID - ```/t TOUR_ID```

:exclamation: Note that the respective client, flight and tour must **exist** in order for package to be added.

Example:

* `add -p p001 /c c001 /f SQ-JPN1 /t t001`

Adds Client _c001_, Flight _SQ-JPN1_, and Tour _t001_ into an overall package _p001_.

* `add -p p002 /c c002 /t t002 /f SQ-KOR1 `

Adds Client _c002_, Flight _SQ-KOR1_, and Tour _t002_ into an overall package _p002_.

<br>

<hr>

## :scissors: Cutting data types:  ```cut```
Deletes entry of a certain data type.

<br>

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

<br>

## **Querying Data Types**

<hr>

The user is also able to view all entries of a specific data type, as well as find specific entrie(s) based their codes.

<br>
:information_source: Please refer to <u>Introduction to Data Types</u> on the syntax of ```DATA_TYPE```
<br>

## :mag_right: Listing data types: ```list```

Shows a list of all available entries of a specific data type, along with their respective fields.

Format: ```list [DATA_TYPE]```

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
Email: betty@mail.com

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

### Find tour / flight 

Finds a specific entry based on a particular code. In addition, for tours and flights, it will show the names of the
subscriber(s) / passenger(s) who are assigned to them respectively.

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

## :chart_with_upwards_trend: Sorting data types: ```sort```

Sort a specific data type based on a particular ```[FILTER]```. It will return all client(s) in ascending
alphabetical order. the possible values of ```[FILTER]``` varies between data types.

Format: ```sort [DATA_TYPE] [FILTER]```

<br>

### Sort client

The possible values of ```[FILTER]``` are:
* ```\n``` to sort by client name 
* ```\id``` to sort by client id

<br>

Examples:

* ```sort -c \n``` sorts the clients by client name alphabetically.

An output of this format will be shown:

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
<br>

* ```sort -c \id``` sorts the clients by id alphabetically.

An output of this format will be shown:

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

<br>

### Sort tour

Sort tour(s) based on a particular ```[FILTER]```. It will return all tour(s) in ascending
alphabetical order.

The possible values of ```[FILTER]``` are:
* ```\id``` to sort by tour id
* ```\p``` to sort by price

<br>

Examples:

* ```sort -f \id``` sorts the tours by tour id alphabetically.

An output of this format will be shown:

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
<br>

* ```sort -f \id``` sorts the tours by price in ascending order.

An output of this format will be shown:

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

<br>


### Sort flights

Sort flight(s) based on a particular [FILTER]. 
It will return all flight(s) in ascending alphabetical order.

The possible values of ```[FILTER]``` are:
*```\id``` to sort by flight id
* ```\d``` to sort by departing flight times
* ```\r``` to sort by returning flight times

<br>

Examples:

* ```sort -f \id``` sorts the flights by flight id alphabetically.

An output of this format will be shown:

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
<br>

* ```sort -f \d``` sorts the flights by departing returning flight times.

An output of this format will be shown:

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
<br>

* ```sort -f \r``` sorts the flights by ascending returning flight times.

An output of this format will be shown:

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
<br>


## Storage

This program can print all the cilentpackages into a text file will save the clientpackages 
you have added before by load and save functions.

## Exit application: ```bye```

Exits the application.

<br>

Format: ```bye```

The following output will be shown:

```Thanks for using TourPlanner. Goodbye!```

<br>
<hr>
