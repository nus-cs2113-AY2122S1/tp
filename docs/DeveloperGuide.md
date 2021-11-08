# Developer Guide

## Table of Contents

- [Getting Started](#getting-started)
    - [Recommended software (for optimal compatability)](#recommended-software-for-optimal-compatibility)
    - [Setting up this project in your computer](#setting-up-this-project-in-your-computer)


- [Design & Implementation](#design--implementation)
    - [Architecture](#architecture)
    - [Parser](#parser-component)
    - [Ui component](#ui-component)
    - [Command component](#command-component)
        - [Add feature](#add-feature)
        - [Cut feature](#cut-feature)
        - [List feature](#list-feature)
        - [Find feature](#find-feature)
        - [Sort feature](#sort-feature)
    - [Storage component](#storage-component)


- [Appendix A: Product Scope](#appendix-a-product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
- [Appendix B: User Stories](#appendix-b-user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
- [Appendix D: Glossary](#appendix-d-glossary)
- [Appendix E: Instructions for manual testing](#appendix-e-instructions-for-manual-testing)

<br>

## Getting Started

<hr>

### Recommended software (for optimal compatibility)

* GitHub
* Sourcetree (for version control)
* IntelliJ IDEA (IDE)
* Amazon Coretto

### Setting up this project in your computer

1. On Github, fork this repo by clicking on the fork button


2. In Sourcetree, clone the fork into your computer
    1. Open a new tab
    2. Select the clone button at the top of the menu
    3. Key in the details of this repository


3. Configure the JDK: Follow the guide [Project Configuration/ SDKs](https://www.jetbrains.com/help/idea/sdk.html)
   to ensure that Intellij is configured to use JDK 11.


4. Import the project as a Gradle project: Follow the
   guide [Intellij IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)


5. Verify the setup:
    1. Run the seedu.duke.TourPlanner and try a few commands.
    2. Run the tests to ensure they all pass.

<br>

## Design & Implementation

<hr>

## Architecture

<hr>

The diagram below shows the high-level design of TourPlanner:

![Architecture Class Diagram](https://user-images.githubusercontent.com/62021897/140712865-752a92c6-da93-42a2-9d12-66f4182df56c.png)

Below is an overview of the main components, and how they interact with each other.

**Main components**

```TourPlanner``` acts as the main class. It is responsible for:

* At app launch: Initialises the components in the correct sequence, and connects them up with each other.
* While app is running: Executes commands from the user.
* At shut down: Shuts down the components and invokes clean-up methods where necessary.

The rest of the app consists of the following components:

* ```Ui```: The UI of the application.
* ```Parser```: Deciphers user input and returns the appropriate command to `TourPlanner`.
* ```Command```: The different types of commands that can possibly be executed.
* ```ObjectList```: Holds data in different arrays, based on their type. Namely there are four types of ObjectLists:
    * `ClientList`
    * `TourList`
    * `FlightList`
    * `ClientPackageList`
* ```Storage```: Reads data from, and writes data to, the hard disk.

<br>

**Interaction of components**

The diagram below shows how the components interact with each other if the user inputs the command ```list -c```:

![ListClientCommand](https://user-images.githubusercontent.com/70316271/140695121-080a0611-4c22-4399-b460-eb5b0454e445.png)

<br>

## Parser component

<hr>

**API: `Parser.java`**

The Sequence Diagram below illustrates the flow of parsing a command:

Here is a class diagram of Parser component:

![Parser](https://user-images.githubusercontent.com/70316271/140693617-31b988e6-90ff-46b5-8af1-8921ca1ef3fa.png)

The flow of **parse** in `Parser` is as follows:

**Step 1.** `Parser` splits the user input String to identify user's **Command** (add, list, cut etc.).

**Step 2.** Depending on the Command, `Parser` calls `parseXYZ()` method respective to the `XYZCommand`
(`XYZ` is a placeholder for the specific command e.g. `AddClientCommand`) given.

**Step 3.** `Parser` creates an `XYZCommand` object which Parser returns as a `Command` object.

**Step 4.** `XYZCommand` is returned to `TourPlanner` to be executed.

<br>

### Example of parse mechanism - `ParseAdd`

Given below is an example scenario, outlining how parseAdd behaves at each step.

**Step 1.** `Parser` first identifies the identifier for add, namely `-c` (add client), `-t` (add tour), `-f` (add
flight), `-p` (add package), by splitting the identifier and the rest of the string by spaces.

```
-t JPN /n Japan Tour /p 1500 --> [-t, JPN, /n, Japan Tour, /p 1500]
```

**Step 2.** Next, `Parser` checks if all prefixes for data fields e.g. name, price, id are present.

**Step 3.** To sort the values for addition, the prefixes and their corresponding indexes are stored as key-value pairs
into a TreeMap. A TreeMap helps to sort the pairs by the natural ordering of the keys.
:information_source: See [TreeMap](#appendix-d-glossary).

```
^JPN ^/n Japan Tour ^/p 1500 --> [(0, null), (4, /n), (18, /p)] (sorted)
```

**Step 4.** The prefix and its index will facilitate splitting the string into substrings, containing both the prefix
and the value corresponding to the prefix.

```
-t JPN /n Japan Tour /p 1500 --> [JPN, /n Japan Tour, /p 1500] 
```

**Step 5.** Given the prefix, the array index that the value will be inserted into is predetermined.

```
[JPN, /n Japan Tour, /p 1500] --> [JPN, Japan Tour, 1500] 
```

**Step 6.** The value is extracted from the substring by removing the prefix, and inserted into an array. The array is
used as an argument for the *Object* constructor.

<br>

## UI Component

<hr>

**API: `Ui.java`**

The Ui component is the means by which Command(s) can receive inputs from the user, as well as display information to
them, all through the console terminal.

The diagram below shows the class diagram of the Ui component, in relation with other major components:

![Ui Class Diagram](https://user-images.githubusercontent.com/62021897/140712493-2054f2c9-6f58-4353-88ad-1570707ff3ef.png)

<br>

After the user typed in an input into the console terminal and presses 'Enter':

* the ```Ui``` reads the input typed in by the user on the console terminal.
* the ```Parser``` class parses the read input and calls the appropriate ```Command```. (see the ```Parser```
  and ```Command``` sections for more information)
* the called ```Command``` calls a function in the ```Ui``` to print the appropriate information onto the console
  terminal.

<br>

## Command component

<hr>

**API: `Command.java`**

Here's a (partial) class diagram of Command component:

![Command class (2)](https://user-images.githubusercontent.com/70316271/140637570-e6a9f453-ea88-46a8-8f3c-e1913e7e938d.png)

:information_source: `XYZ` in this diagram is a placeholder for the specific data type (`Client`, `Flight`, `Tour`
, `ClientPackage`). A similar workflow applies for these classes depending on the availability of the command for the
specific data type.

How the `Command` works:

**Step 1.** Based on the user input, `Parser` returns one of the subclasses of `Command` to `TourPlanner`.

**Step 2.** `TourPlanner` calls `Command.execute()`.

**Step 3.** Corresponding command uses associate classes, `XYZList` (`XYZ` is a placeholder for the specific data type
e.g. `Client`) and `Ui` to carry out its function.

<br>

### Add feature

The add feature is facilitated mainly by `Parser`, and returns an `AddCommand` object. When `AddCommand` is executed,
the values corresponding to their fields are added.

After receiving the command from the user by `readCommand` of `Ui`, the command is parsed by `Parser`. The `Parser`
first determines the command type by separating the command from the subsequent arguments. After determining the
command, `Parser` executes the specific methods to sense-make the arguments, in response to the specific command.

Here are sample add command inputs that can be parsed.

Example add commands:

* `add -c c001 /n Bo Tuan /cn 91234567 /m bobotea@gmail.com`
* `add -f SQ-JPN1 /d Japan /r Singapore /dd 29/10/21 13:00 /rd 5/11/21 02:00`
* `add -t t001 /n AustralianRomance /p 1500`
* `add -p p001 /c c001 /f SQ-JPN1 /t t001`

In general, there is a sequence of steps when any of the 4 `add` commands are called.

Here is an example usage of `add -c` to add client "c001":

**Step 1.** User inputs `add -c c001 [ARGS...]`. This command is passed to `parse()` method in the `Parser` class.

**Step 2.** Based on the user input, `parse()` identifies that it is of type `add` command and calls `ParseAdd()`.

**Step 3** `ParseAdd()` executes three instructions:

* Extract values from user's input and creates a `values` Array
* Handle exceptions to the `values` Array, determined by the data type identifier (e.g. `-c`, `-t`)
* Instantiates a `Client`/`Tour`/`Flight`/`ClientPackage` *Object*, determined by the same data type identifier.
* Returns an `AddXYZCommand` based on the same data type identifier, passing in the *Object* as an argument for adding.

Depending on the type of add command being called, these command types will be returned:

* `add -c`: `AddClientCommand`
* `add -f`: `AddFlightCommand`
* `add -t`: `AddTourCommand`
* `add -p`: `AddClientPackageCommand`

The following 2 sections focuses on add for the specific classes.

<br>

#### <u>Adding a client package</u>

Given below is an example usage of `add -p p001 ARGS...` to add client package "p001".

Here is a (partial) sequence diagram of above user input:

![AddClientPackage Sequence Diagram](https://user-images.githubusercontent.com/62021897/140713338-a743133a-516b-47e3-88b6-e9b5408b226c.png)

**Step 1.** Parser creates a `values` array, upon extracting values from user's input.
Creates `AddClientPackageCommand(values)`, determined by the datatype identifier `-p`. Returns the created `Command`
subclass to `TourPlanner`.

**Step 2.** Then, `execute()` method in `AddClientCommand` is called. `getClientPackageById("p001")` is called, which
finds the `ClientPackage` based on the `CLIENT_PACKAGE_ID` "p001".

**Step 3.** If the `CLIENT_PACKAGE_ID` "p001" already exists, an error message is returned.

**Step 4.** Else, the `CLIENT_PACKAGE_ID` "p001" does not exist, `execute()` calls `createClientPackage` which finds the
specific `Client`, `Tour` and `Flight` objects to be added to the `ClientPackage`

**Step 5.** Then, `add` in `ClientPackageList` is called, to add the specific `ClientPackage` into `ClientPackageList`.

**Step 5.** `execute()` calls `showAdd` in `Ui`, which prints out the Object, `ClientPackage` that was added.

<br>

#### <u>Adding a particular client / tour / flight</u>

Given below is an example usage of `add -t t001 ARGS...` to add tour with the respective arguments.

Here is a (partial) sequence diagram for above user input:

![AddCommand](https://user-images.githubusercontent.com/62021897/140713091-70c5b626-ec86-4122-a2c9-6b0f47b0a564.png)

**Step 1.** `Parser` parses the input and instantiates `AddTourCommand`. It then returns it to `TourPlanner`.

**Step 2.** Then, `execute()` method in `AddTourCommand` is called. `getTourById("t001")` is called, which finds
the `Tour` based on the `TOUR_ID` "t001".

**Step 3.** If the `TOUR_ID` "t001" already exists, an error message is returned.

**Step 4.** Else, the `TOUR_ID` "t001" does not exist, `execute()` calls `add` in `TourList`, to add the specific client
package and its arguments into the `TourList`.

**Step 5.** `execute()` calls `showAdd` in `Ui`, which prints out the Object, `Tour` that was added.


<br>

### Cut feature

The `cut` feature is used to remove an entry from the `ObjectList` (for `Client`, `Tour`, `Flight` and `ClientPackage`),
where `Parser` determines the `ObjectList` to remove from.

It implements these following types of `cut` commands:

* `cut -c CLIENT_ID`
* `cut -t TOUR_ID`
* `cut -f FLIGHT_ID`
* `cut -p CLIENTPACKAGE_ID`

In general, there is a sequence of steps when any of the 4 `cut` commands are called.

Here is an example usage of `cut -c` to delete client with `CLIENT_ID` of "c001":

**Step 1.** After adding a few client packages to the database, user inputs `cut -c c001`. This command is passed
to `parse()` method in the `Parser` class.

**Step 2.** Based on the user input, `parse()` identifies that it is of type `cut` command and calls `ParseCut()`.
`ParseCut()` will then create `CutClientCommand("c001")` based on the prefix `-c`. Returns the created `Command`
subclass to `TourPlanner`.

Depending on the type of cut command being called, these command types will be returned:

* `cut -c`: `CutClientCommand`
* `cut -f`: `CutFlightCommand`
* `cut -t`: `CutTourCommand`
* `cut -p`: `CutClientPackageCommand`

The following 2 sections focuses on cut for the specific classes.

<br>

#### <u>Cutting a particular client package</u>

Given below is an example usage of `cut -p p001` to deletes client package with `CLIENTPACKAGE_ID` of "p001".

Here is a (partial) sequence diagram of above user input:

![CutClientPackageCommand](https://user-images.githubusercontent.com/70316271/140637935-a64ba82f-a9d1-4439-ad04-157ad0ecaad3.png)

**Step 1.** `Parser` creates `CutClientPackageCommand("p001")` and returns it to `TourPlanner`.

**Step 2.** Then, `execute()` method in `CutClientPackageCommand` calls `getClientPackageById("p001")`
which finds the `ClientPackage` based on the `CLIENTPACKAGE_ID` "p001".

**Step 3.** The `ClientPackage` is removed from the `ClientPackageList`.

<br>

#### <u>Cutting a particular client / tour / flight</u>

Given below is an example usage of `cut -c c001` to delete client with `CLIENT_ID` of "c001"
and all corresponding client packages that contain deleted client.

Here is a (partial) sequence diagram for above user input:

![CutClientCommand](https://user-images.githubusercontent.com/70316271/140637934-08e0a09e-bf63-4f16-b84f-faa7a852b2d3.png)

**Step 1.** `Parser` creates `CutClientCommand("c001")` and returns it to `TourPlanner`.

**Step 2.** Then, `execute()` method in `CutClientCommand` calls `getClientById("c001")`
which finds the `Client` based on the `CLIENT_ID` "c001".

**Step 3.** The `Client` is removed from the `ClientList`.

**Step 4.** Next, `getClientPackageByClient("c001")` returns an `ArrayList` of `ClientPackages` that contains
the `CLient` "c001". :information_source: See [ArrayList](#appendix-d-glossary).

**Step 5.** Loops through the `ClientPackages` and deletes them from `ClientPackageList`.

*:information_source: `Tour` and `Flight` works in the same way respectively with `TourList` and `FlightList`

<br>

### List feature

The `list` feature is used to display all entries in `ObjectList` (`Client`, `Tour`, `Flight` and `ClientPackage`),
where `Parser` determines the `ObjectList` to list from.

It implements these following types of list commands:

* `list -c`: Lists all existing clients and their contact numbers
* `list -t`: Lists all existing tours
* `list -f`: Lists all existing flights
* `list -p`: Lists all clients and their corresponding tours and flights

Given below is an example usage of command `list -c` that lists all `Client` objects in `ClientList`:

Here is a (partial) sequence diagram for the above user input:
![ListClientCommand](https://user-images.githubusercontent.com/70316271/140637940-066bab37-d9dd-4efa-b0ee-e681fcd5b139.png)

**Step 1**: After adding a few clients to the database, user inputs `list -c`. This command is passed to `parse()`
method in the `Parser` class.

**Step 2**: Based on the user input, `parse()` identifies that it is of type `list` command and calls `ParseList()`.
`ParseList()` will then return `ListClientCommand` based on the prefix `-c`.

**Step 3**: Then, `execute()` method in `ListClientCommand` is called, where it loops through the current `clientList`
and prints out all client names and their details.

Depending on the type of list command being called, these command types will be returned:

* `list -c`: `ListClientCommand`
* `list -f`: `ListFlightCommand`
* `list -t`: `ListTourCommand`
* `list -p`: `ListClientPackageCommand`

<br>

### Find feature

The ```find``` feature is to be used to query for a particular client, tour or flight, providing extensive information
about it. It is facilitated by the ```parse``` function in the ```Parser``` class, and returns a FindCommand object.
When FindCommand is executed, it queries the corresponding `ObjectList` and returns the matching entries.

It implements these following types of find commands:

* `find -c CLIENT_NAME`: returns clients that contain `CLIENT_NAME` in their name and client packages they are
  subscribed to.
* `find -t TOUR_ID`: returns tours that match the `TOUR_ID` and tours subscribed to the tour.
* `find -f FLIGHT_ID`: returns flights that match the `FLIGHT_ID` and clients subscribed to the flight.

<br>

In general, there is a sequence of steps when any of the 3 `find` commands are called.

Firstly, assume that in previous sessions, commands were executed to add clients, tours, flights and packages to
the ```ClientList```, ```TourList```, ```FlightList``` and ```PackageList``` respectively. In particular, these specific
commands were executed.

* ```add -c c001 /n Bo Tuan /cn 93338333 /m borangutuan@mail.com```
* ```add -t JPN /n Japan Tour /p 1500```
* ```add -f SQ-JPN /d JPN /r SG /dd 20/10/2021 18:00 /rd 21/10/2021 03:00```
* ```add -p p001 /c c001 /t JPN /f SQ-JPN```

Here is an example usage of `find -c bo` to find a client with name "Bo Tuan":
**Step 1**: The user executes ```find -c bo``` to query if a client named "Bo Tuan" exists in the ClientList.
The ```parse``` function in the ```Parser``` class takes the command, and calls `parseFind()`
based on (```find```) in the input. ```parseFind()``` determines which type of Object is to be queried for and
```FindClientCommand()``` is created with the parameter ```bo``` based on prefix `-c`.

Depending on the type of find command being called, these command types will be returned:

* ```find -c```: ```FindClientCommand```
* ```find -t```: ```FindTourCommand```
* ```find -f```: ```FindFlightCommand```

The following 3 sections focuses on find for the specific classes.

<br>

#### <u>Finding a particular client</u>

Given below is an example usage of `find -c bo`.

Here is a (partial) sequence diagram for the above user input:

![FindClient Sequence Diagram](https://user-images.githubusercontent.com/62021897/140740252-019e87c0-7793-4203-9cc2-df2834f71b0e.png)

**Step 1** `Parser` creates `FindClientCommand("bo")` and returns it to `TourPlanner`.

**Step 2** The ```FindClientCommand``` iterates through each ```Client``` object in the ```ClientList```. For
every ```Client```, the ```getName()``` function is called to retrieve the name attribute of the Client. The name
attribute is then converted to lower case for comparison with the substring. If the name attribute is contains the
substring```bo```, the ```Client``` object is printed onto the console terminal.

**Step 3** In addition, the ```FindClientCommand``` iterates through each ```ClientPackage``` object in
the ```ClientPackageList```. For every ```ClientPackage```, the ```getClient()``` function is called to retrieve the
client attribute of the ClientPackage. If the client attribute is equals to the same ```Client``` object that was found
in Step 2, the respective client package will be printed onto the console terminal.

<br>

#### <u>Finding a particular tour</u>

Given below is an example usage of `find -t JPN`.

Here is a (partial) sequence diagram for the above user input:

![FindTour Sequence Diagram](https://user-images.githubusercontent.com/62021897/140713906-dd0524f5-53d2-4409-819b-05a467adee53.png)

**Step 1**: `Parser` creates `FindTourCommand("JPN")` and returns it to `TourPlanner`.

**Step 2**: The ```FindTourCommand``` is executed and iterates through each ```Tour``` object in the ```TourList```. For
every ```Tour```, the ```getCode()``` function is called to retrieve the code attribute of the Tour. If the tour
attribute is equals to ```JPN```, the ```Tour``` object is printed onto the console terminal.

**Step 3**: In addition, the ```FindTourCommand``` iterates through each ```ClientPackage``` object in
the ```ClientPackageList```. For every ```ClientPackage```, the ```getTour()``` function is called to retrieve the tour
attribute of the ClientPackage. If the tour attribute is equals to the same ```Tour``` object that was found in Step 2,
the client attribute of that same ClientPackage will be retrieved using the ```getClient()``` function.

**Step 4**: The client's name attribute is then retrieved using the ```getName()``` function in the Client class. The
name is printed onto the console terminal under "Subscribed Clients". Once all  ```ClientPackage``` objects are iterated
through in the ```ClientPackageList```, the total number of subscribed clients will be printed on the console terminal.

<br>

#### <u>Finding a particular flight</u>

Given below is an example usage of `find -f SQ-JPN`.

Here is a (partial) sequence diagram for the above user input:

![FindFlight Sequence Diagram](https://user-images.githubusercontent.com/62021897/140713762-85084399-97e1-47ee-a228-51e6c743d0d1.png)

**Step 1**: `Parser` creates `FindFlightCommand("SQ-JPN")` and returns it to `TourPlanner`.

**Step 2**: The ```FindFlightCommand``` is executed and iterates through each ```Flight``` object in
the ```FlightList```. For every ```Flight```, the ```getCode()``` function is called to retrieve the code attribute of
the Tour. If the tour attribute is equals to ```SQ-JPN```, the ```Flight``` object is printed onto the console terminal.

**Step 3**: In addition, the ```FindFlightCommand``` iterates through each ```ClientPackage``` object in
the ```ClientPackageList```. For every ```ClientPackage```, the ```getFlight()``` function is called to retrieve the
tour attribute of the ClientPackage. If the tour attribute is equals to the same ```Flight``` object that was found in
Step 2, the client attribute of that same ClientPackage will be retrieved using the ```getClient()``` function.

**Step 4**: The client's name attribute is then retrieved using the ```getName()``` function in the Client class. The
name is printed onto the console terminal under "Passengers". Once all  ```ClientPackage``` objects are iterated through
in the ```ClientPackageList```, the total number of passengers will be printed on the console terminal.

<br>

#### <u>Design Considerations</u>

* Alternative: only iterate through the ```ClientPackageList```.
    * Pros: fast querying time.
    * Cons: If the client/tour/flight is not in any package, none of their information can be accessed, including their
      contact number.

<br>

### Sort feature

The `sort` feature is used to sort the `ObjectList` (for `Client`, `Tour`, `Flight` and `ClientPackage`) and list it,
where `Parser` determines the `ObjectList` and criteria to sort for.

It implements these following types of `sort` commands:

* Client
    * `sort -c /n`: Sorts alphabetically by client name
    * `sort -c /id`: Sorts alphabetically by client id
* Flight
    * `sort -f /d`: Sorts in ascending order of date and time for departure flight
    * `sort -f /r`: Sorts in ascending order of date and time for return flight
    * `sort -f /id`: Sorts alphabetically by flight id
* Tour
    * `sort -t /id`: Sorts alphabetically by tour id
    * `sort -t /p`: Sorts in ascending order of tour price

Given below is an example usage of `sort -c /id`:

Here is a (partial) sequence diagram of the above user input:

![SortClientCommand](https://user-images.githubusercontent.com/70316271/140637939-1ff5b961-31fa-4afa-b834-316066362ffd.png)

**Step 1**: After adding a few clients to the database, user inputs `sort -c /id`. This command is passed to `parse()`
method in the `Parser` class.

**Step 2**: Based on the user input, `parse()` identifies that it is of type `sort` command and calls `ParseSort()`.
`ParseSort()` will then return `SortClientCommand` based on the prefix `-c`.

**Step 3**: Then, `execute()` method in `SortClientCommand` is called, where it gets the sorted `ArrayList` of
`clientIds`. :information_source: See [ArrayList](#appendix-d-glossary).

**Step 4** In `UI`, `showSortedClientById()` is called, with `clientIds` passed in. The method iterates through all the
client IDs. For each iteration, finds the corresponding `Client` with `getClientById()` and prints out the `Client`
and their details.

Depending on the type of sort command being called, these command types will be returned:

* `sort -c`: `SortClientCommand`
* `sort -f`: `SortFlightCommand`
* `sort -t`: `SortTourCommand`

<br>

## Storage Component

<hr>

**API: `ClientPackageStorage.java` `ClientStorage.java` `FlightStorage.java` `TourStorage.java`**

Shown below is the class diagram for TourPlanner's Storage Component:

![Storage](https://user-images.githubusercontent.com/62021897/140651207-73bd0de8-cb0b-469f-9cdd-c9bbc26b8efc.png)

<br>

The Storage component consists of:

1. `ClientPackageStorage.java`: Reading and saving files which record all clientpackages.
2. `ClientStorage.java`: Reading and saving files which record all clients.
3. `FlightStorage.java`: Reading and saving files which record all flights.
4. `TourStorage.java`: Reading and saving files which record all tours.

To add on Storage component is designed to access only the following folder:

1. `data/`

<br>

## Appendix A: Product scope

<hr>

### Target user profile

* Tour company employees that need to manage a significant amount of tour data
* Prefer desktop apps over other types
* Can type fast
* Is reasonably comfortable using CLI apps

### Value proposition

* Manage tour information faster than typical mouse /GUI driven apps

<br>

## Appendix B: User Stories

<hr>

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

|Priority| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|`* * *`|new user|see usage instructions|refer to them when I forget how to use the application|
|`* * *`|user|add a new entry of specific data type| add data into the current database|
|`* * *`|user|delete an existing entry of specific data type |remove outdated data from the current database|
|`* *`|user with large amounts of data|find an existing entry of specific data type |locate a specific entry easily|
|`* *`|user with large amounts of data|sort existing entries of specific data type |make smarter recommendations to clients based on their preferences|
|`* *`|user|check number of clients subscribed to a tour / flight|check the popularity, vacancy of certain tours / flights|

:information_source: 'specific data type' refers to either clients, tours, flights or client packages.

<br>

## Appendix C: Non-Functional Requirements

<hr>

* Should work on any mainstream OS as long as it has Java 11 or above installed.
* Should be able to hold up to 1000 entries without a noticeable sluggishness in performance for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.

<br>

## Appendix D: Glossary

<hr>

**ClientPackage:** serves as a link between the client, tour and flight, helps to keep track of the tours (and flights)
they are subscribed to. `ClientPackage` class stores the actual `Client`, `Flight` and `Tour` objects.

**ArrayList:** acts as a dynamic array, where items can be easily added and removed.

**TreeMap:** sorts and stores key-value pairs. The key-value pairs are sorted according to the natural ordering of its
keys, or by a Comparator provided at map creation time.

**Subclass:** To say class `A` is a subclass of class `B` would mean that class `A` inherits from class `B`. A subclass
inherits attributes and methods from the parent class.

**Exceptions:** is any event that interrupts the normal flow of program execution, due to an unwanted event which cannot
be controlled by developers

<br>

## Appendix E: Instructions for manual testing

<hr>

Given below are instructions to test the app manually.

### Launch and shutdown

1. Initial launch
    1. Download the jar file and copy into an empty folder.
    2. Run the command ```java -jar TourPlanner.jar``` in a command window to start the program.

    * Expected: Applications shows welcome messages and allows you to type in commands.

### Adding and viewing data

1. Add `Client` to database
    * Test case: `add -c c001 /n Bo Tuan /cn 93338333 /m borangutuan@mail.com`

    1. Input the test case to the command window.
    2. Input `list -c`.

    * Expected: `Client` with id "c001", name "Bo Tuan", contact "93338333", mail "borangutuan@mail.com"
      is displayed in the list of clients.
    * :information_source: tests can be repeated with `Tour`, `Flight` and `ClientPackage` with the corresponding data
      fields.

### Cutting data

1. Cut `Client` from database
    * Test case: `cut -c c001` after calling `add -c c001 /n Bo Tuan /cn 93338333 /m borangutuan@mail.com`

    1. Input the test case to the command window.
    2. Input `list -c`.

    * Expected: `Client` with id "c001", name "Bo Tuan", contact "93338333", mail "borangutuan@mail.com"
      is no longer displayed in the list of clients.
    * :information_source: tests can be repeated with `Tour`, `Flight` and `ClientPackage` with the corresponding data
      fields.
