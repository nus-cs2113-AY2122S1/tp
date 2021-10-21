# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

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