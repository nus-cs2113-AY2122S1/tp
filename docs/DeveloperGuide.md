# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

### Implementation of add feature

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


## Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

|Priority| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|`* * *`|new user|see usage instructions|refer to them when I forget how to use the application|
|`* * *`|user|add a new client| |
|`* * *`|user|delete a new client|remove clients that cancelled their travel plans|
|`* * *`|user|add a new tour package|update the tour package database|
|`* * *`|user|delete a tour package|update the tour package database|
|`* * *`|user|add flight timings|keep track of flights|


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## **Find feature**

<hr>

### <u>Proposed Implementation</u>

The ```find``` feature is to be used to query for a particular client or tour, providing extensive information about it. It is facilitated by the ```parse``` function in the ```Parser``` class, which determines which type of Object (```Client``` or ```Tour```) is to be parsed and which command to be executed. It is implemented by following operations:

* ```FindClientCommand(String name)```

* ```FindTourCommand(String code)```

These commands extend from the Command class.

Given below is an example usage scenario and how the find feature behaves at each step, when trying to find a particular client or tour.

<br>


### <u>Finding a particular client</u>

**Step 1**: Assume that in previous sessions, commands were executed to add clients, tours, flights and packages to the ```ClientList```, ```TourList```, ```FlightList``` and ```PackageList``` respectively. In particular, these specific commands were exceuted.

* ```add -c Bo Tuan /cn 12345678```
* ```add -t JPN /n Japan Tour /p 1500 ```
* ```add -f SQ-JPN /t JPN /f SG /dt 20-10-2021 18:00 /df 21-10-2021 02:00```
* ```add -p 0001 /c Bo Tuan /t JPN /f SQ-JPN ```

<br>

**Step 2**: The user executes ```find -c Bo Tuan``` to query if a client named Bo Tuan exists in the ClientList. The ```parse``` function in the ```Parser``` class takes the command, and the first word in it (```find```) means that the ```parseFind()``` is to be called to determine which type of Object is to be queried for. The second word (```-c```) means that a the ```FindClientCommand()``` is executed with the parameter ```Bo Tuan```

<br>

**Step 3**: The ```FindClientCommand``` iterates through each ```Client``` object in the ```ClientList```. For every ```Client```, the ```getName()``` function is called to retrieve the name attribute of the Client. If the name attribute is equals to ```Bo Tuan```, the ```Client``` object is printed onto the console terminal.

</br>

**Step 4**: In addition, the ```FindClientCommand``` iterates through each ```Package``` object in the ```PackageList```. For every ```Package```, the ```getClient()``` function is called to retrieve the client attribute of the ```Package```. If the client attribute is equals to ```Bo Tuan```, the ```Package``` object is printed onto the console terminal.

<br>

The following activity diagram summarizes the following steps.

![image](yuemel.png)

<br>

### <u>Design Considerations</u>

*  Alternative: only iterate through the ```Package``` List.
   * Pros: fast querying time.
   * Cons: If the client has not subscribed to a package, none of their information can be accessed, including their contact number.