# User Guide


## Introduction


Elderly In Your Hood is a **lightweight command-line (CLI) desktop application** for retirement home
nurses/volunteers to keep track of the elderly in their home, along with their specific medical 
needs, vitals and appointments with the various hospitals.

## Table of Contents

- [User Guide](#user-guide)
    - [Quick Start](#quick-start)
    - [Brief Overview of Functions](#brief-overview-of-functions)
      - [Miscellaneous Commands](#miscellaneous-commands)
      - [Creation Commands](#creation-commands)
      - [Reading Commands](#reading-commands)
      - [Filter Commands](#filter-commands)
      - [Delete Commands](#delete-commands)
      - [File I/O Commands](#file-io-commands)
    - [Features](#features)
        - [Adding an elderly : `addelderly`](#adding-an-elderly-addelderly)
        - [Adding a medicine to an elderly : `addmed`](#adding-medicine-to-an-elderly-addmed)
        - [Adding an appointment to an elderly : `addappt`](#adding-appointment-to-an-elderly-addappt)
        - [Adding next-of-kin information to an elderly: `addnok`](#adding-next-of-kin-information-to-an-elderly-addnok)
        - [Adding record information to an elderly: `addrec`](#adding-record-information-to-an-elderly-addrec)
        - [Adding medical history information to an elderly: `addmedicalhistory`](#adding-medical-history-information-to-an-elderly-addmedicalhistory)
        - [Set birthday `setbirthday`](#set-birthday-setbirthday)
        - [Set vaccination `setvaccination`](#set-vaccination-setvaccination)
        - [Set dietary preference `setdiet`](#set-dietary-preference-setdiet)
        - [Set blood pressure `setbloodpressure`](#set-blood-pressure-setbloodpressure)
        - [Viewing medicine of an elderly: `viewmed`](#viewing-medicine-of-an-elderly-viewmed)
        - [Viewing appointments of an elderly: `viewappt`](#viewing-appointments-of-an-elderly-viewappt)
        - [Viewing Next-Of-Kin information of an elderly: `viewnok`](#viewing-next-of-kin-information-of-an-elderly-viewnok)
        - [Viewing record information of an elderly: `viewrec`](#viewing-record-information-of-an-elderly-viewrec)
        - [Viewing medical history information of an elderly: `viewmedicalhistory`](#viewing-medical-history-information-of-an-elderly-viewmedicalhistory)
        - [View birthday `viewbirthday`](#view-birthday-viewbirthday)
        - [View vaccination status `viewvaccination`](#view-vaccination-status-viewvaccination)
        - [View dietary preference `viewdiet`](#view-dietary-preference-viewdiet)
        - [View blood pressure `viewbloodpressure`](#view-blood-pressure-viewbloodpressure)
        - [List all elderly in the system: `list`](#list-all-elderly-in-the-system-list)
        - [Show help menu: `help`](#show-help-menu-help)
        - [Terminating: `bye`](#terminating-bye)
        - [Find which elderly is taking what medication: `findbymed`](#find-which-elderly-is-taking-what-medication-findbymed)
        - [Find which elderly is on what kind of diet: `findbydiet`](#find-which-elderly-is-on-what-kind-of-diet-findbydiet)
        - [Find elderly details given their name: `findbyname`](#find-elderly-details-given-their-name-findbyname)
        - [Delete elderly from system given username: `deleteelderly`](#delete-elderly-from-system-given-username-deleteelderly)
        - [Delete medicine from elderly: `deletemed`](#delete-medicine-from-elderly-deletemed)
        - [Delete appointment from elderly: `deleteappt`](#delete-appointment-from-elderly-deleteappt)
        - [Delete Next-of-Kin contact from elderly: `deletenok`](#delete-next-of-kin-contact-from-elderly-deletenok)
        - [Delete previous medication history information from elderly: `deletemedicalhistory`](#delete-previous-medication-history-information-from-elderly-deletemedicalhistory)
        - [Store data in system into a file: `store`](#store-data-in-system-into-a-file-store)
        - [Load data from file into the system: `load`](#load-data-from-file-into-the-system-load)
    - [Auto Suggestions Feature](#auto-suggestions-feature)
    - [FAQ](#faq)
    - [Table of commands](#command-summary)
    - [Reference](#reference)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ElderlyInTheHood` 
from [here](https://github.com/AY2122S1-CS2113-T16-2/tp/releases/tag/v2.1).
3. Copy `ElderlyInTheHood.jar` to the target directory as the home folder
4. Open terminal and move current directory to the home folder
5. Run the program using `java -jar ElderlyInTheHood.jar` command.
6. Type in your command and press Enter to execute it.

<div style="page-break-after: always;"></div>

## Brief Overview of Functions
![](https://img.shields.io/static/v1?label=info&message=functions&color=blue&style=flat)

This is a list of sample commands you can try (more details on syntax can be found in 
[Features](#features) or in the `help` menu in the application:

### Miscellaneous Commands
* `list`: List all elderly in their system along with their information.
* `help`: Shows the help menu along with the commands associated to it.
* `bye` : Exits the program.

### Creation Commands
* `addelderly`: Adds an elderly to the system. More information might be needed based on the risk 
level specified.
* `addmed`: Appends a medicine to the list of existing medicine that the elderly must take.
* `addappt`: Tags an appointment listing to an existing elderly.
* `addnok`: Creates a new next-of-kin contact to the elderly.
* `addrec`: Puts in additional essential information like the phone number and home address of an existing elderly.
* `addmedicalhistory`: Creates a note of the medical history of the elderly.
* `setbirthday`: Inserts the birthday of the elderly into the elderly record.
* `setvaccination`: Sets the vaccination status of the elderly to True. (False by default)
* `setdiet`: Specifies the dietary preference of the elderly.
* `setbloodpressure`: Takes in the blood pressure of the elderly. This will overwrite upon each set.

### Reading Commands
* `viewmed`: Gets all the medicine requires to be taken by an elderly.
* `viewappt`: Returns all the appointments associated to the elderly.
* `viewnok`: View all next-of-kin contact information tagged to an elderly.
* `viewrec`: Gets the handphone number and address associated to an elderly.
* `viewmedicalhistory`: Shows the medical history of the elderly.
* `viewbirthday`: Returns the birthday of a given elderly.
* `viewvaccination`: View the vaccination status of the elderly.
* `viewdiet`: Gets the dietary preference of the elderly.
* `viewbloodpressure`: View the blood pressure of the elderly.

### Filter Commands
* `findbymed`: Searches the current system for the medicine specified in this command.
* `findbydiet`: Looks up the system and returns all elderly that has the specified diet.
* `findbyname`: Finds the details of the elderly based on their real name. **Duplicates of real names are allowed** and all details of the elderly with the same name will be returned.  

### Delete Commands
* `deleteelderly`: Remove an elderly record given the elderly username.
* `deletemed`: Deletes the medicine tagged to an elderly. 
* `deleteappt`: Remove an appointment associated to an elderly. 
* `deletenok`: Deletes the next-of-kin information associated to an elderly.
* `deletemedicalhistory`: Remove the medical history details of the elderly.

### File I/O Commands
* `store`: Stores the current data into a specified file path.
* `load`: Loads the current data from a specified file path.

## Features

### Adding an elderly: `addelderly`
Adds an elderly to take care of. Risk level of elderly will be asked for, followed by additional
information if needed (according to risk level)

Format: `addelderly u/USERNAME n/NAME r/RISK_LEVEL`

* The `USERNAME` only can contain characters or numbers.
* No duplicate `USERNAME` is allowed.  
* The `NAME` only can contain characters or whitespaces.
* The `RISK_LEVEL` can only be `l`, `m`, `h` which stands for
low-level risk, medium-level risk and high-level risk respectively.
* `l` risk level does not require additional information.
* `m` risk level requires `hospital`, `conditions` and `notesOnCare` information
* `h` risk level requires `hospital`, `conditions`, `notesOnCare` and `doctor` information.

Example of usage (for `h` risk level elderly):


`addelderly u/johntan123 n/John Tan r/h`

```
> addelderly u/johntan123 n/John Tan r/h
List of hospitals are shown below:
1.changi general hospital
2.khoo teck puat hospital
3.national university hospital
4.ng teng feng general hospital
5.singapore general hospital
6.tan tock seng hospital
Enter hospital number choice. (e.g. 1 for Changi General Hospital etc.)
> 2
Enter elderly condition below. Hit enter when done.
> amnesia, stage 2 liver cancer, lower body paralysis
Enter notes on care below. Hit enter when done.
> at least 3 nurses required to ensure safety when showering. needs daily routine of going on walks at 7am. remember to lock wheelchair when stationary. meals should only be given during mealtimes to prevent overconsumption. reduce salt in food. liver checks to be done every sunday 3pm.
List of khoo teck puat hospital doctors are shown below:
1.adrian vergil
2.joline dean
3.shui lorin
4.britney soh
5.aditi nitya
Enter doctor number choice. (e.g. 1)
> 1
```

### Adding medicine to an elderly: `addmed`
Adds a medicine to an existing elderly.

Format: `addmed u/USERNAME m/MEDICINE_NAME f/FREQUENCY`

* The `USERNAME` only can contain characters or numbers.
* The `MEDICINE_NAME` cannot contain punctuation.
* The `FREQUENCY` can be in a natural language format.

Example of usage:

`addmed u/johntan123 m/paracetamol f/once a day`

```
> addmed u/johntan123 m/paracetamol f/once a day
[*] Medicine has been added!
```

<div style="page-break-after: always;"></div>

### Adding appointment to an elderly: `addappt`
Adds an appointment to an existing elderly.

Format: `addappt u/USERNAME l/LOCATION d/DATE t/TIME [p/PURPOSE]`

* The `USERNAME` only can contain characters or numbers.
* The `LOCATION` can be in a natural language format.
* The `DATE` only can contain 8 numbers, in the format DDMMYYYY.
* The `TIME` only can contain 4 numbers, in the format hhmm. 24h time format.
* The `PURPOSE` can be in a natural language format. Take note that this argument is optional, and
is defaulted to `general checkup` if unassigned.

Example of usage:

`addappt u/johntan123 l/khoo teck puat hospital d/02012021 t/1000`
`addappt u/johntan123 l/khoo teck puat hospital d/05032021 t/1500 p/eye checkup`

```
> addappt u/johntan123 l/khoo teck puat hospital d/02012021 t/1000
[*] Appointment has been added!
> addappt u/johntan123 l/khoo teck puat hospital d/05032021 t/1500 p/eye checkup
[*] Appointment has been added!
```

### Adding next-of-kin information to an elderly: `addnok`
Adds next-of-kin information to an existing elderly.

Format: `addnok u/USERNAME k/NOKNAME p/NOKPHONE e/NOKEMAIL a/NOKADDR r/NOKRSHIP`

* The `USERNAME` only can contain characters or numbers.
* The `NOKNAME` only can contain characters or whitespaces.
* The `NOKPHONE` only can contain 8 numbers.
* The `NOKEMAIL` will contain 
  * Username which allows for alphanumeric characters, hyphens, dashes and dots
  * Domain name which can be made of any characters.
  * A `@` will be between the username and domain name in the format `USERNAME@DOMAIN`
* The `NOKADDRESS` can contain characters or numbers.
* The `NOKRSHIP` can contain characters or numbers.

Example of usage:

`addnok u/johntan123 k/peter tan p/98733842 e/hello-world@gmail.com a/Blk 2 College Drive r/Father`

```
> addnok u/johntan123 k/peter tan p/98733842 e/hello-world@gmail.com a/Blk 2 College Drive r/Father
[*] Next-of-Kin has been added!
```

### Adding record information to an elderly: `addrec`
Adds record information like phone number and home address of elderly to the elderly record.

Format: `addrec u/USERNAME p/PHONENUMBER a/HOMEADDRESS`

* The `USERNAME` only can contain characters or numbers.
* The `PHONENUMBER` only can contain 8 numbers.
* The `HOMEADDRESS` can contain characters or numbers.

Example of usage:

`addrec u/johntan123 p/87778374 a/Blk 2 College Drive`

```
> addrec u/johntan123 p/87778374 a/Blk 2 College Drive
[*] Record of elderly has been added!
```

### Adding medical history information to an elderly: `addmedicalhistory`
Adds medical history information to the elderly record.

Format: `addmedicalhistory u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`addmedicalhistory u/johntan123`

```
[*] Please key in your medical history
> 29/10/20 Fainted due to low blood sugar
[*] Medical history of john tan has been updated
> addmedicalhistory u/johntan123
[*] Please key in your medical history
> 28/04/21 Experienced severe cough
[*] Medical history of john tan has been updated
```
<div style="page-break-after: always;"></div>

### Set birthday `setbirthday`

Sets the birthday of the given elderly

Format:

`setbirthday u/USERNAME b/BIRTHDAY`

* The `USERNAME` only can contain characters or numbers.
* The `BIRTHDAY` is in the format of YYYY-MM-DD

Example of usage:

`setbirthday u/johntan123 b/1959-10-29`

Expected output:

```
[*] Birthday of John Tan has been set to 1959-10-29
```

### Set vaccination `setvaccination`

Sets the vaccination status of the given elderly to be vaccinated

Format:

`setvaccination u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`setvaccination u/johntan123`

Expected output:

```
[*] We have set John Tan to be vaccinated
```

### Set dietary preference `setdiet`

Sets the dietary preference of the given elderly

Format: `setdiet u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`setdiet u/johntan123`

Expected output:
```
Select the possible diet of the elderly
1. Halal
2. Vegetarian
3. Vegan
4. Beef free
5. Diabetes
6. No restriction
```
User input: `2`

Expected output:
```
[*] Dietary preference of John Tan has been set to Vegetarian
```

### Set blood pressure `setbloodpressure`

Sets the most recently measured blood pressure of the elderly

Format:
`setbloodpressure u/USERNAME s/SYSTOLIC_PRESSURE d/DIATOLIC_PRESSURE`

* The `USERNAME` only can contain characters or numbers.
* The `SYSTOLIC_PRESSURE` is an integer.
* The `DIATOLIC_PRESSURE` is an integer.

Example of usage:

`setbloodpressure u/johntan123 s/120 d/80`

Expected output:

```
[*] Blood pressure of John Tan has been updated to 120 80!
```

### Viewing medicine of an elderly: `viewmed`
Views all medicine of an existing elderly.

Format: `viewmed u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewmed u/johntan123`

```
> viewmed u/johntan123
Medicine of johntan123 are shown below:
  1.Medicine Name: paracetamol, Frequency: once a day
```

### Viewing appointments of an elderly: `viewappt`
Views all appointments of an existing elderly.

Format: `viewappt u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewappt u/johntan123`

```
> viewappt u/johntan123
Appointments of johntan123 are shown below:
  1.Location: khoo teck puat hospital, Date: 02012021, Time: 1000, Purpose of Visit: general checkup
  2.Location: khoo teck puat hospital, Date: 05032021, Time: 1500, Purpose of Visit: eye checkup
```

### Viewing Next-Of-Kin information of an elderly: `viewnok`
Views all next-of-kin information of an existing elderly.

Format: `viewnok u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewnok u/johntan123`

```
> viewnok u/johntan123
Details of johntan123 Next-of-Kin are shown below:
[NOK Name: peter tan, NOK HP: 98733842, NOK Email: hello-world@gmail.com, NOK Address: Blk 2 College Drive, Relationship: Father]
```

<div style="page-break-after: always;"></div>

### Viewing record information of an elderly: `viewrec`
Views all record information (phone number, home address) of an existing elderly.

Format: `viewrec u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewrec u/johntan123`

```
> viewrec u/johntan123
Record of johntan123 is shown below:
[Elderly HP: 87778374, Elderly Address: Blk 2 College Drive]
```

### Viewing medical history information of an elderly: `viewmedicalhistory`
Views past medical history of an existing elderly.

Format: `viewmedicalhistory u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewmedicalhistory u/johntan123`

```
john tan's medical history:
29/10/20 Fainted due to low blood sugar
28/04/21 Experienced severe cough
```

### View birthday `viewbirthday`

Views the birthday of the given elderly

Format:

`viewbirthday u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewbirthday u/johntan123`

Expected output:

```
Birthday of John Tan is 1959-10-29
```

### View vaccination status `viewvaccination`

Views the vaccination status of the elderly

Format:

`viewvaccination u/USER_NAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewvaccination u/johntan123`

Expected output:

```
John Tan is currently vaccinated.
```

### View dietary preference `viewdiet`

Views the dietary preference of the given elderly

Format: `viewdiet u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewdiet u/johntan123`

Expected output:
```
John Tan is having a Vegetarian diet
```

### View blood pressure `viewbloodpressure`

Views the most recently measured blood pressure of the given elderly

Format:

`viewbloodpressure u/USERNAME`

* The `USERNAME` only can contain characters or numbers.

Example of usage:

`viewbloodpressure u/johntan123`

Expected output:

```
Blood pressure of John Tan is now (120 80)
```



### List all elderly in the system: `list`
Returns the information of all elderly in the current session.

Format: `list`

Example of usage:

`list`

```
> list
[*] There are currently 2 elderly registered in the system
[*] Risk Level : HIGH
[*] Elderly Username: johntan123
[*] Elderly Name: John Tan
[*] Vaccination status of johntan123: Vaccinated
Blood pressure of John Tan is now (120 80)

[*] Birthday of johntan123: 1959-10-29
[*] Here are johntan123's records:
Elderly HP: 87778374, Elderly Address: Blk 2 College Drive

[*] Here is the list of upcoming appointments:
Location: khoo teck puat hospital, Date: 02012021, Time: 1000, Purpose of Visit: general checkup
Location: khoo teck puat hospital, Date: 05032021, Time: 1500, Purpose of Visit: eye checkup

[*] This is the list of medicine(s):
Medicine Name: paracetamol, Frequency: once a day

[*] These are NOK(s) attached to johntan123:
NOK Name: peter tan, NOK HP: 98733842, NOK Email: hello-world@gmail.com, NOK Address: Blk 2 College Drive, Relationship: Father

[*] Hospital Details :
khoo teck puat hospital (Hospital Name)
65558000 (Hospital Number)

[*] Conditions of elderly johntan123 : amnesia, stage 2 liver cancer, lower body paralysis

[*] Doctor for johntan123 :
adrian vergil (Doctor Name)
88129931 (Doctor Number)

[*] Notes on care for johntan123 : at least 3 nurses required to ensure safety when showering. needs daily routine of going on walks at 7am. remember to lock wheelchair when stationary. meals should only be given during mealtimes to prevent overconsumption. reduce salt in food. liver checks to be done every sunday 3pm.

[*] Risk Level : LOW
[*] Elderly Username: owintoh456
[*] Elderly Name: Owin Toh
[*] Vaccination status of owintoh456: Not vaccinated
[*] Blood pressure of Owin Toh has not yet been set!

[*] Birthday of owintoh456: Not Recorded
[*] Here are owintoh456's records:


[*] Here is the list of upcoming appointments:


[*] This is the list of medicine(s):


[*] These are NOK(s) attached to owintoh456:
```

### Show help menu: `help`
Shows the help menu with the appropriate syntax.

Format: `help`

Example of usage:

`help`

```
> help

[*] The following is a list of commands:
Misc. Commands

list - List all elderly in the program
help - Prints this help menu
bye - Quits Program

Creation Commands

[*] Add Elderly format: addelderly u/USERNAME n/NAME r/RISK_LEVEL
[*] Add Medicine format: addmed u/USERNAME m/MEDICINE_NAME f/FREQUENCY
[*] Add Appointment format: addappt u/USERNAME l/LOCATION d/DATE t/TIME p/[PURPOSE]
[*] Add Next-Of-Kin information format: addnok u/USERNAME k/NOKNAME p/NOKPHONE e/NOKEMAIL a/NOKADDR r/NOKRSHIP
[*] Add Elderly Record Information format: addrec u/USERNAME p/PHONENUMBER a/HOMEADDRESS
[*] Add Medical History format: addmedicalhistory u/USERNAME
[*] Set Birthday format: setbirthday u/USERNAME b/BIRTHDAY
[*] Set Vaccination format: setvaccination u/USERNAME
[*] Set Diet format: setdiet u/USERNAME
[*] Set Blood Pressure format: setbloodpressure u/USERNAME s/SYSTOLIC_PRESSURE d/DIATOLIC_PRESSURE

Reading Commands

[*] View Medicine format: viewmed u/USERNAME
[*] View Appointment format: viewappt u/USERNAME
[*] View Next-Of-Kin format: viewnok u/USERNAME
[*] View Elderly Record Information format: viewrec u/USERNAME
[*] View Medical History format: viewmedicalhistory u/USERNAME
[*] View Birthday format: viewbirthday u/USERNAME
[*] View Vaccination format: viewvaccination u/USERNAME
[*] View Diet format: viewdiet u/USERNAME
[*] View Blood Pressure format: viewbloodpressure u/USERNAME

Filter Commands

[*] Find By Medicine format: findbymed m/MEDICINE_NAME
[*] Find By Diet format: findbydiet d/DIET
[*] Find By Name format: findbyname n/NAME

Delete Commands

[*] Delete format: deleteelderly u/USERNAME
[*] Delete medicine format: deletemed u/USERNAME m/MEDICINE_NAME
[*] Delete appointment format: deleteappt u/USERNAME d/DATE t/TIME
[*] Delete Next-Of-Kin information format: deletenok u/USERNAME n/NOKNAME
[*] Delete medical history format: deletemedicalhistory u/USERNAME

File I/O Commands

[*] Store format: store fp/FILE
[*] Load format: load fp/FILE
```
<div style="page-break-after: always;"></div>

### Terminating: `bye`
Terminates the current session.

Format: `bye`

Example of usage:

`bye`

```
> bye
[*] Bye command has been recognized. Goodbye!
```

### Find which elderly is taking what medication: `findbymed`
Filter elderly that are taking a certain kind of medication.

Format: `findbymed m/MEDICINE_NAME`

Example of usage:

`findbymed m/paracetamol`

```
> findbymed m/paracetamol
[*] This is the results for the following query: paracetamol
johntan123
```

### Find which elderly is on what kind of diet: `findbydiet`
Filter elderly that have certain dietary preferences.

Format: `findbydiet d/DIET`

Example of usage:

`findbydiet d/Vegetarian`

```
> findbydiet d/Vegetarian
[*] This is the results for the following query: Vegetarian
johntan123
```
<div style="page-break-after: always;"></div>

### Find elderly details given their name: `findbyname`
Filter elderly based on real name and get all their information.

Format: `findbyname n/NAME`

Notes:
* If multiple elderly have the same name, all their records will be retrieved as duiplicate real names are allowed.

Example of usage:

`findbyname n/John Tan`

```
> findbyname n/John Tan
[*] This is the results for the following query: John Tan
[*] Risk Level : HIGH
[*] Elderly Username: johntan123
[*] Elderly Name: John Tan
[*] Vaccination status of johntan123: Vaccinated
Blood pressure of John Tan is now (120 80)

[*] Birthday of johntan123: 1959-10-29
[*] Here are johntan123's records:
Elderly HP: 87778374, Elderly Address: Blk 2 College Drive

[*] Here is the list of upcoming appointments:
Location: khoo teck puat hospital, Date: 02012021, Time: 1000, Purpose of Visit: general checkup
Location: khoo teck puat hospital, Date: 05032021, Time: 1500, Purpose of Visit: eye checkup

[*] This is the list of medicine(s):
Medicine Name: paracetamol, Frequency: once a day

[*] These are NOK(s) attached to johntan123:
NOK Name: peter tan, NOK HP: 98733842, NOK Email: hello-world@gmail.com, NOK Address: Blk 2 College Drive, Relationship: Father

[*] Hospital Details :
khoo teck puat hospital (Hospital Name)
65558000 (Hospital Number)

[*] Conditions of elderly johntan123 : amnesia, stage 2 liver cancer, lower body paralysis

[*] Doctor for johntan123 :
adrian vergil (Doctor Name)
88129931 (Doctor Number)

[*] Notes on care for johntan123 : at least 3 nurses required to ensure safety when showering. needs daily routine of going on walks at 7am. remember to lock wheelchair when stationary. meals should only be given during mealtimes to prevent overconsumption. reduce salt in food. liver checks to be done every sunday 3pm.
```

### Delete elderly from system given username: `deleteelderly`
Delete elderly with a specified username.

Format: `deleteelderly u/USERNAME`

Example of usage:

`deleteelderly u/owintoh456`

* Caveat - `owintoh456` exists in the system.

```
> deleteelderly u/owintoh456
[*] owintoh456 has been deleted.
```

### Delete medicine from elderly: `deletemed`
Delete medicine with a specified username.

Format: `deletemed u/USERNAME m/MEDICINE_NAME`

Example of usage:

`deletemed u/johntan123 m/paracetamol`

```
> deletemed u/johntan123 m/paracetamol
These medicine details are now deleted:
Medicine Name: paracetamol, Frequency: once a day
```

### Delete appointment from elderly: `deleteappt`
Delete appointment with date time of an elderly.

Format: `deleteappt u/USERNAME d/DATE t/TIME`

* The `USERNAME` only can contain characters or numbers.
* The `DATE` only can contain 8 numbers, in the format DDMMYYYY.
* The `TIME` only can contain 4 numbers, in the format hhmm.

Example of usage:

`deleteappt u/johntan123 d/02012021 t/1000`

```
> deleteappt u/johntan123 d/02012021 t/1000
These appointment details are now deleted:
Location: khoo teck puat hospital, Date: 02012021, Time: 1000, Purpose of Visit: general checkup
```

### Delete Next-of-Kin contact from elderly: `deletenok`
Delete next-of-kin contact info from an elderly.

Format: `deletenok u/USERNAME n/NOKNAME`

Example of usage:

`deletenok u/johntan123 n/peter tan`

```
> deletenok u/johntan123 n/peter tan
These details are now deleted:
NOK Name: peter tan, NOK HP: 98733842, NOK Email: hello-world@gmail.com, NOK Address: Blk 2 College Drive, Relationship: Father
```

### Delete previous medication history information from elderly: `deletemedicalhistory`
Delete medical history info from an elderly.

Format: `deletemedicalhistory u/USERNAME`

Example of usage:

`deletemedicalhistory u/johntan123`

```
> deletemedicalhistory u/johntan123
[*] Are you sure you want to delete the medical history of John Tan? (Y/N)
> Y
[*] Medical history of John Tan has been deleted
```

### Store data in system into a file: `store`
Store information from system into a json file.

Format: `store fp/FILENAME`

* The `FILENAME` can only consist of characters and must end with a `.json`.
* The `FILENAME` will specify the file saved into. The file will then be saved into the local directory.

Example of usage:

`store fp/data.json`

### Load data from file into the system: `load`
Load information from json file into the system.

Format: `load fp/FILENAME`

* The `FILENAME` can only consist of characters and must end with a `.json`.
* The `FILENAME` will specify the file to be referenced. The file must be in the local directory.

Example of usage:

`load fp/data.json`

```
> load fp/data.json
[*] data.json exists. Loading from file!
```

## Auto-suggestions feature
For all the filter functions like `findbyname` and `deleteelderly`, the autosuggestions feature has been activated. If there is a match close to the query string that exists in the database, it will prompt you. 

Example: 

There exists an elderly called `johntan123`. A `deleteelderly` command is issued to delete an elderly with the username of `johntoh123`. 

```
> deleteelderly u/johntoh123
[*] I am sorry, did you mean: johntan123
```

The system will look in the system and suggest `johntan123` as the possible query term that the user should input. It will however **not execute the command to delete ** `johntan123`.

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: You can use the `load` command to load the json file that you have stored using the `store` command.
It has been designed to **not be auto-save**, so you may specify where to save your file to.

**Q**: `AddMed` does not seem to work? 

**A**: Our program is created for a fast user. Hence, we made the assumption that the user will only
type in small letters instead of capitalising some words. Use `addmed` instead.

**Q**: How do I add new hospitals and doctors?

**A**: Our program is created to keep track of elderly records, not to add hospitals and doctors.
Hence, we have made the hospitals and doctors fixed for this program, with the assumption that only
these few hospitals and doctors are available to be selected. 

Of course, you are welcome to upgrade this to make it more scalable! :D

**Q**: It seems that I can type in numbers and gibberish for inputs like conditions and notes on
care for medium and high risk elderly?

**A**: Yes, but take note that this is **not a bug. It's a feature.** There are many times when the
helper needs to input numbers, medicine name and stuff into these fields. There is no way to check
and 100% guarantee the legitimacy of input. Hence, we decided to accept any input. We trust that the
input by the user will be legitimate, hence we accept anything from the user. 



## Command Summary

|Command|Format|
|---|---|
|Add elderly |`addelderly u/USERNAME n/NAME r/RISKLEVEL`|
|Add medicine |`addmed u/USERNAME m/MEDICINE_NAME f/FREQUENCY`|
|Add appointment |`addappt u/USERNAME l/LOCATION d/DATE t/TIME [p/PURPOSE]`|
|Add nok |`addnok u/USERNAME k/NOKNAME p/NOKPHONE e/NOKEMAIL a/NOKADDR r/NOKRSHIP`|
|Add record |`addrec u/USERNAME p/PHONENUMBER a/HOMEADDRESS`|
|Add medical history |`addmedicalhistory u/USERNAME`|
|Set birthday |`setbirthday u/USERNAME b/BIRTHDAY`|
|Set vaccination status |`setvaccination u/USERNAME`|
|Set dietary preference |`setdiet u/USER_NAME`|
|Set blood pressure |`setbloodpressure u/USERNAME s/SYSTOLIC_PRESSURE d/DIATOLIC_PRESSURE`|
|View medicine |`viewmed u/USERNAME`|
|View appointment |`viewappt u/USERNAME`|
|View nok |`viewnok u/USERNAME`|
|View record |`viewrec u/USERNAME`|
|View medical history |`viewmedicalhistory u/USERNAME`|
|View birthday |`viewbirthday u/USERNAME`|
|View vaccination status |`viewvaccination u/USERNAME`|
|View dietary preference |`viewdiet u/USERNAME`|
|View blood pressure |`viewbloodpressure u/USERNAME`|
|List |`list`|
|Help |`help`|
|Bye |`bye`|
|Find elderly by medication taken |`findbymed m/MEDICINE_NAME`|
|Find elderly by dietary preference |`findbydiet d/DIET`|
|Get elderly records given real name |`findbyname n/NAME`|
|Delete elderly |`deleteelderly u/USERNAME`|
|Delete medicine |`deletemed u/USERNAME m/MEDICINE_NAME`|
|Delete appointment |`deleteappt u/USERNAME d/DATE t/TIME`|
|Delete nok |`deletenok u/USERNAME n/NOKNAME`|
|Delete medical history |`deletemedicalhistory u/USERNAME`|
|Store |`store fp/FILENAME`|
|Load |`load fp/FILENAME`|


## Reference
The template of the User Guide is followed by [AddressBook Level 3 (AB3) User Guide](https://se-education.org/addressbook-level3/UserGuide.html#editing-a-person--edit).

