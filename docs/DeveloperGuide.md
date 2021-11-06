# Developer Guide

## Getting started

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

## Design & implementation

<hr>

## Architecture

<hr>

The diagram above shows the high-level design of TourPlanner.

<img width="307" alt="component_diagram" src="https://user-images.githubusercontent.com/79963329/140464392-5a1536d2-a5d8-4e57-83f4-f4938ede0cfe.PNG">


Below is an overview of the main components, and how they interact with each other.

**Main components**

```TourPlanner``` acts as the main class. It is responsible for:

* At app launch: Initialises the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes clean-up methods where necessary.

The rest of the app consists of the following components:

* ```Ui```: The UI of the application.
* ```Parser```: Deciphers user input and executes the appropriate command / error message
* ```Command```: The different types of commands that can possibly be executed.
* ```ObjectList```: Holds data in different arrays, based on their type. Namely there are four types of ObjectLists:
    * ClientList
    * TourList
    * FlightList
    * ClientPackageList
* ```Storage```: Reads data from, and writes data to, the hard disk.

<br>

**Interaction of components**

The diagram below shows how the components interact with each other if the user inputs the command ```list -c```:

<img width="538" alt="architecture_example" src="https://user-images.githubusercontent.com/79963329/140464330-b4f9121f-b60f-4203-a814-63b7a6bd97a5.PNG">

<br>

## Parser component

<hr>

**API: `Parser.java`**

The Sequence Diagram below illustrates the flow of parsing a command:

![Parser](https://user-images.githubusercontent.com/62021897/140608448-dc37fb49-dbab-4279-9067-770af331d19a.PNG)

The flow of **parse** in `Parser` is as follows:

**Step 1.** `Parser` splits the user input String to identify user's **Command** (add, list, cut etc.).

**Step 2.** Depending on the Command, `Parser` executes parsing respective to the Command given.

**Step 3.** `Parser` creates an `XYZCommand` object (`XYZ` is a placeholder for the specific command e.g.
`AddClientCommand`) which Parser returns as a `Command` object.

**Step 4.** `XYZCommand` is returned to `TourPlanner`.

<br>

## Command component

<hr>

**API: `Command.java`**

Here's a (partial) class diagram of Command component:

![Command class](https://user-images.githubusercontent.com/70316271/140176181-47f4dd25-6c60-4c9a-8b4a-dd21f916a52a.png)

*Note: This diagram and explanation includes only commands related to the `Client` class. The same workflow applies for
classes `Flight`, `Tour` and `ClientPackage`, only with a difference in command naming.*

How the `Command` works:

**Step 1.** Based on the user input, `Parser` returns a `Command` to `TourPlanner` *(main class)*.

**Step 2.** `TourPlanner` calls `Command.execute()`.

**Step 3.** Corresponding command uses associate classes, `ClientList` and `Ui` to carry out its function.

<br>

### <u>Add feature</u>

The add feature is facilitated mainly by `Parser`, and returns an `AddCommand` object. When `AddCommand` is executed,
the values corresponding to their fields are added.

After receiving the command from the user by `readCommand` of `Ui`, the command is parsed by `Parser`. The `Parser`
first determines the command type by separating the command from the subsequent arguments. After determining the
command, `Parser` executes the specific methods to sense-make the arguments, in response to the specific command.

Here are sample add command inputs that can be parsed.

Example add commands:

* `add -c NAME /cn 91234567`
* `add -t JPN /n Hokkaido-A /p 1500`
* `add -f SQ-JPN /t JPN /f SG dt 20-10-2021 18:00 /df 21-10-2021 03:00`
* `add -p ID /c NAME /t JPN /f SQ-JPN`

Given below is an example scenario, outlining how the add mechanism behaves at each step.

**Step 1.** `Parser` first identifies the identifier for add, namely `-c` (add client), `-t` (add tour), `-f` (add
flight), `-p` (add package), by splitting the identifier and the rest of the string, separated by white space.

```
-t JPN /n Hokkaido-A /p 1500 --> -t <<split>> JPN /n Hokkaido-A /p 1500 --> [-t, JPN /n Hokkaido-A /p 1500]
```

**Step 2.** Next, `Parser` checks if all prefixes e.g.  `/p, /f` are present.

**Step 3.** To sort the values for addition, the prefixes and their corresponding indexes are stored as key-value pairs
into a TreeMap. A TreeMap helps to sort the pairs by the natural ordering of the keys.

```
extract(JPN /n Hokkaido-A /p 1500) --> ^JPN ^/n Hokkaido-A ^/p 1500 --> [(0, null), (4, /n), (18, /p)] (sorted)
```

**Step 4.** The prefix and its index will facilitate splitting the string into substrings, containing both the prefix
and the value corresponding to the prefix.

```
obtainSubstring(JPN /n Hokkaido-A /p 1500, indexes) --> <<split>>JPN <<split>>/n Hokkaido-A <<split>>/p 1500 
--> [JPN, /n Hokkaido-A, /p 1500] 
```

**Step 5.** Given the prefix, the array index that the value will be inserted into is predetermined.

**Step 6.** The value is extracted from the substring by removing the prefix, and inserted into the array.

```
extractValue(/n Hokkaido-A) --> Hokkaido-A 
```

**Step 7.** Depending on the identifier, a corresponding object is initialised.

* `-c : Client`
* `-t : Tour`
* `-f : Flight`
* `-p : Package`

**Step 8.** `AddCommand(Object o)` constructor is called, passing in the created object.

<br>

### <u>Cut feature</u>

The `cut` feature is used to remove an entry from the `ObjectList` (for Client, Tour, Flight and ClientPackage),
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
`ParseCut()` will then return `CutClientCommand("c001")` based on the prefix `-c`, where "c001" will be stored in
`CutClientCommand`.

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

![CutClientPackageCommand](https://user-images.githubusercontent.com/70316271/140183929-2968d7c1-6a93-4378-8389-a3f7a3413cbe.png)

**Step 1.** `Parser` returns `CutClientPackageCommand("p001)`.

**Step 2.** Then, `execute()` method in `CutClientPackageCommand` calls `getClientPackageById("p001")`
which finds the `ClientPackage` based on the `CLIENTPACKAGE_ID` "p001".

**Step 3.** The `ClientPackage` is removed from the `ClientPackageList`.

<br>

#### <u>Cutting a particular client / tour / flight</u>

Given below is an example usage of `cut -c c001` to delete client with `CLIENT_ID` of "c001"
and all corresponding client packages that contain deleted client.

Here is a (partial) sequence diagram for above user input:

![CutClientCommand](https://user-images.githubusercontent.com/70316271/140184002-db955693-ac5e-44c5-9c13-e18df17a8974.png)

**Step 1.** `Parser` returns `CutClientCommand("c001)`.

**Step 2.** Then, `execute()` method in `CutClientCommand` calls `getClientById("c001")`
which finds the `Client` based on the `CLIENT_ID` "c001".

**Step 3.** The `Client` is removed from the `ClientList`.

**Step 4.** Next, `getClientPackageByClient("c001")` returns an `ArrayList` of `ClientPackages` that contains
the `CLient` "c001".

**Step 5.** Loops through the `ClientPackages` and deletes them from `ClientPackageList`.

*Note: `Tour` and `Flight` works in the same way respectively with `TourList` and `FlightList`*

<br>

### <u>List feature</u>

The `list` feature is used to display all entries in `ObjectList` (for Client, Tour, Flight and ClientPackage),
where `Parser` determines the `ObjectList` to list from.

It implements these following types of list commands:

* `list -c`: Lists all existing clients and their contact numbers
* `list -t`: Lists all existing tours
* `list -f`: Lists all existing flights
* `list -p`: Lists all clients and their corresponding tours and flights

Given below is an example usage of command `list -c`:

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

### <u>Find feature</u>

The ```find``` feature is to be used to query for a particular client or tour, providing extensive information about it.
It is facilitated by the ```parse``` function in the ```Parser``` class, which determines which type of
Object (```Client``` or ```Tour```) is to be parsed and which command to be executed. It is implemented by following
operations:

* ```FindClientCommand(String substring)```
* ```FindTourCommand(String code)```
* ```FindFlightCommand(String code)```

These commands extend from the Command class.

Given below is an example usage scenario and how the find feature behaves at each step, when trying to find a particular
client or tour.

<br>

Firstly, assume that in previous sessions, commands were executed to add clients, tours, flights and packages to
the ```ClientList```, ```TourList```, ```FlightList``` and ```PackageList``` respectively. In particular, these specific
commands were exceuted.

* ```add -c c001 /n Bo Tuan /cn 93338333 /m borangutuan@mail.com```
* ```add -t JPN /n Japan Tour /p 1500 ```
* ```add -f SQ-JPN /d JPN /r SG /dd 20/10/2021 18:00 /rd 21/10/2021 03:00```
* ```add -p p001 /c c001 /t JPN /f SQ-JPN```

<br>

#### <u>Finding a particular client</u>

**Step 1**: The user executes ```find -c bo``` to query if a client named Bo Tuan exists in the ClientList.
The ```parse``` function in the ```Parser```
class takes the command, and the first word in it (```find```) means that the
```parseFind()``` is to be called to determine which type of Object is to be queried for. The second word (```-c```)
means that a the ```FindClientCommand()``` is executed with the parameter ```bo```

**Step 2**: The ```FindClientCommand``` iterates through each ```Client``` object in the ```ClientList```. For
every ```Client```, the ```getName()``` function is called to retrieve the name attribute of the Client. The name
attribute is then converted to lower case for comparison with the substring. If the name attribute is contains the
substring```bo```, the ```Client``` object is printed onto the console terminal.

**Step 3**: In addition, the ```FindClientCommand``` iterates through each ```ClientPackage``` object in
the ```ClientPackageList```. For every ```ClientPackage```, the ```getClient()``` function is called to retrieve the
client attribute of the ClientPackage. If the client attribute is equals to the same ```Client``` object that was found
in Step 2, the respective client package will be printed onto the console terminal.

The following activity diagram summarizes the following steps.

<img width="542" alt="findclient" src="https://user-images.githubusercontent.com/79963329/140464449-0f72431b-8ae5-40e4-add1-aef8fed50031.PNG">

<br>

#### <u>Finding a particular tour</u>

**Step 1**: The user executes ```find -t JPN``` to query if a tour with code JPN exists in the TourList. The ```parse```
function in the ```Parser```
class takes the command, and the first word in it (```find```) means that the
```parseFind()``` is to be called to determine which type of Object is to be queried for. The second word (```-f```)
means that a the ```FindTourCommand()``` is executed with the parameter ```JPN```

**Step 2**: The ```FindTourCommand``` iterates through each ```Tour``` object in the ```TourList```. For
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

The following activity diagram summarizes the following steps.

<img width="456" alt="findtour" src="https://user-images.githubusercontent.com/79963329/140464566-248296e9-0e27-4840-9906-5cfdbe57c309.PNG">


<br>

#### <u>Finding a particular flight</u>

**Step 1**: The user executes ```find -f SQ-JPN``` to query if a tour with code JPN exists in the TourList.
The ```parse``` function in the ```Parser```
class takes the command, and the first word in it (```find```) means that the
```parseFind()``` is to be called to determine which type of Object is to be queried for. The second word (```-f```)
means that a the ```FindFlightCommand()``` is executed with the parameter ```SQ-JPN```

**Step 2**: The ```FindFlightCommand``` iterates through each ```Flight``` object in the ```FlightList```. For
every ```Flight```, the ```getCode()``` function is called to retrieve the code attribute of the Tour. If the tour
attribute is equals to ```SQ-JPN```, the ```Flight``` object is printed onto the console terminal.

**Step 3**: In addition, the ```FindFlightCommand``` iterates through each ```ClientPackage``` object in
the ```ClientPackageList```. For every ```ClientPackage```, the ```getFlight()``` function is called to retrieve the
tour attribute of the ClientPackage. If the tour attribute is equals to the same ```Flight``` object that was found in
Step 2, the client attribute of that same ClientPackage will be retrieved using the ```getClient()``` function.

**Step 4**: The client's name attribute is then retrieved using the ```getName()``` function in the Client class. The
name is printed onto the console terminal under "Passengers". Once all  ```ClientPackage``` objects are iterated through
in the ```ClientPackageList```, the total number of passengers will be printed on the console terminal.

<br>

The following activity diagram summarizes the following steps.

<img width="508" alt="findflight" src="https://user-images.githubusercontent.com/79963329/140464579-91ab5042-885b-4e55-877c-ac733bd12ce9.PNG">

<br>

#### <u>Design Considerations</u>

* Alternative: only iterate through the ```Package``` List.
    * Pros: fast querying time.
    * Cons: If the client/tour/flight is not in any package, none of their information can be accessed, including their
      contact number.

### <u>Sort feature</u>

The `sort` feature is used to sort the `ObjectList` (for Client, Tour, Flight and ClientPackage) and list it,
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
![SortClientCommand](https://user-images.githubusercontent.com/70316271/140540865-4e29204d-d501-4968-b69a-b7fbbdfe399c.png)

**Step 1**: After adding a few clients to the database, user inputs `sort -c /id`. This command is passed to `parse()`
method in the `Parser` class.

**Step 2**: Based on the user input, `parse()` identifies that it is of type `sort` command and calls `ParseSort()`.
`ParseSort()` will then return `SortClientCommand` based on the prefix `-c`.

**Step 3**: Then, `execute()` method in `SortClientCommand` is called, where it gets the sorted `ArrayList<>` of
`clientIds`.

**Step 4** In `UI`, `showSortedClientById()` is called, with `clientIds` passed in. The method iterates through all the
client IDs. For each iteration, finds the corresponding `Client` with `getClientById()` and prints out the `Client`
and their details.

Depending on the type of sort command being called, these command types will be returned:

* `sort -c`: `SortClientCommand`
* `sort -f`: `SortFlightCommand`
* `sort -t`: `SortTourCommand`

<br>

## UI Component

<hr>

**API: `Ui.java`**

The Ui component is the means by which Command(s) can receive inputs from the user, as well as display information to
them, all through the console terminal.

After the user typed in an input into the console terminal and presses 'Enter':

* the ```Ui``` reads the input typed in by the user on the console terminal.
* the ```Parser``` class parses the read input and calls the apprpriate ```Command```. (see the ```Parser```
  and ```Command``` sections for more information)
* the called ```Command``` calls a function in the ```Ui``` to print the appropriate information onto the console
  terminal.

<br>

The diagram below shows the class diagram of the Ui component, in relation with other major components:

<img width="289" alt="ui" src="https://user-images.githubusercontent.com/79963329/140464630-a8a8000c-fb45-44af-9cc2-d146ae5ea5c8.PNG">

<br>

## Product scope

<hr>

### Target user profile

* Tour company employees that need to manage a significant amount of tour data
* Prefer desktop apps over other types
* Can type fast
* Is reasonably comfortable using CLI apps

### Value proposition

* Manage tour information faster than typical mouse /GUI driven apps

<br>

## User Stories

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

Note: 'specific data type' refers to either clients, tours, flights or tour packages.

<br>

## Non-Functional Requirements

<hr>

* Should work on any mainstream OS as long as it has Java 11 or above installed.
* Should be able to hold up to 1000 entries without a noticeable sluggishness in performance for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.

<br>

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

