# Developer Guide

## Introduction

MediVault is a Command Line Interface (CLI) application that will help to manage medication supplies within a pharmacy.
It is an integrated solution that provides real-time tracking of stock, orders and prescribing of medication. The
purpose of this guide is to help developers set up and continue with the development of MediVault past version 2.0.

## Acknowledgements

* Inspiration for App Idea and OOP Structure: https://github.com/se-edu/addressbook-level2
* Inspiration for User Guide: https://se-education.org/addressbook-level3/UserGuide.html
* Inspiration for Developer Guide: https://se-education.org/addressbook-level3/DeveloperGuide.html
* PlantUML Tutorial: https://se-education.org/guides/tutorials/plantUml.html

## Contents

* [Glossary](#glossary)
* [Setting up environment](#setting-up-environment)
  * [Setting up](#setting-up)
  * [Before writing code](#before-writing-code)
* [Design](#design)
  * [Architecture](#architecture)
  * [Command](#command)
  * [Utilities](#utilities)
  * [Inventory](#inventory)
  * [Errors](#errors)
* [Implementation](#implementation)
  * [Main Logic](#main-logic)
  * [List Command](#list-command)
  * [Stock Commands](#stock-commands)
    * [AddStockCommand](#addstockcommand)
    * [DeleteStockCommand](#deletestockcommand)
    * [UpdateStockCommand](#updatestockcommand)
  * [Prescription Commands](#prescription-commands)
    * [AddPrescriptionCommand](#addprescriptioncommand)
    * [DeletePrescriptionCommand](#deleteprescriptioncommand)
    * [UpdatePrescriptionCommand](#updateprescriptioncommand)
  * [Order Commands](#order-commands)
    * [AddOrderCommand](#addordercommand)
    * [DeleteOrderCommand](#deleteordercommand)
    * [UpdateOrderCommand](#updateordercommand)
  * [Archive Commands](#archive-commands)
    * [ArchivePrescriptionCommand](#archiveprescriptioncommand)
    * [ArchiveOrderCommand](#archiveordercommand)
* [Product Scope](#product-scope)
  * [Target user profile](#target-user-profile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Instructions for manual testing](#instructions-for-manual-testing)

## Glossary

## Setting up environment

### Setting up 

### Before writing code

## Design

### Architecture

The **Architecture Diagram** for MediVault is shown below.

![ArchitectureDiagram](diagrams/diagram_images/ArchitectureDiagram.png)

A quick overview of the main components and how they interact with each other is given below.

The main class that runs MediVault is called `MediVault`. It is responsible for,
* At program launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

The rest of the program consist of four components.
* `Command`: Executes command based on the user input that is processed by `Utilities`
  component. The list of commands can be found in our User Guide [here](UserGuide.md).
* `Utilities`: Contains important driver classes for MediVault
  * includes `parser`, `ui`, `storage` and `comparators`.
* `Inventory`: Contains a collection of classes used by MediVault to represent
different medication information.
* `Errors`: Contains collection of classes that handles exceptions during execution of MediVault.
### Command

![CommandClassDiagram](diagrams/diagram_images/CommandClassDiagram.png)

The **Command** class diagram above shows how **Command** interact with other classes in MediVault.

The Command Component consists of **18** subclasses where each subclass represents a command feature.

Let `*` be either of the three class: `Stock`, `Prescription` or `Order`.

* `Add*Command`: Adds a new `*` information into MediVault.
* `Delete*Command`: Removes the visibility of the `*` record in MediVault.
* `Update*Command`: Updates the `*` information.
* `List*Command`: Lists the `*` records.
* `ReceiveOrderCommand`: Marks an order as received and adds the ordered medication into the current stocks.
* `ArchivePresciptionCommand`: Archives all the prescription records before a given date.
* `ArchiveOrderCommand`: Archives all the order records before a given date.
* `PurgeCommand`: Wipes all records in MediVault.
* `HelpCommand`: Shows the help page.
* `ExitCommand`: Exits MediVault.

### Utilities

### Inventory

### Errors

## Implementation

### Main Logic

### List Command

### Stock Commands

#### AddStockCommand

#### DeleteStockCommand

#### UpdateStockCommand

MediVault creates an `UpdateStockCommand` object when CommandParser identifies `updatestock` or
the `update` keyword in `stock` mode.
> :information_source: Note:
> * MediVault checks if `parameters` and `parameterValues` provided by the user are valid.
> * MediVault conducts another validation check on the provided `quantity`,`max_quantity` and `expiry`
against the stored medicine stock information.

The sequence diagram for `UpdateStockCommand` is shown below.

![UpdateStockSequenceDiagram](diagrams/diagram_images/UpdateStockSequenceDiagram.png)

MediVault adds a new stock record when a user updates contains the `n/NAME` parameter. The old stock record still
exists in MediVault, but it will not be visible to user when listed. This approach solves the issue when a user is
unable to delete a prescription record when the medicine stock name gets updated.

### Prescription Commands

#### AddPrescriptionCommand

#### DeletePrescriptionCommand

#### UpdatePrescriptionCommand

MediVault initialises an `UpdatePrescriptionCommand` class when CommandParser identifies
`updateprescription` or the `update` keyword in `prescription` mode.

> :information_source: Note
> * MediVault checks if the `parameters` and `parameterValues` provided by the user are valid.
> * When a user updates prescription information containing either `n/NAME`, `q/QUANTITY` or both, MediVault restores the 
prescribed stocks or prescribes more stocks depending on the user input.

The sequence diagram for `UpdatePrescriptionCommand` is shown below.

![UpdatePrescriptionSequenceDiagram](diagrams/diagram_images/UpdatePrescriptionSequenceDiagram.png)

MediVault adds a new prescription record when a user updates contains either the `n/NAME`, `q/QUANTITY`
parameter or both. The old prescription record is **permanently removed** from MediVault. 

This approach solves the issue when a medication is prescribed to a user with an amount that is 
**more than** the current batch of stock with the same Stock ID but **less than** the total 
stock quantity. 
> :bulb: MediVault automatically adds new prescription records when a medication is prescribed
> from stocks with different Stock IDs.

### Order Commands

#### AddOrderCommand

#### DeleteOrderCommand

#### UpdateOrderCommand

MediVault initialises an `UpdateOrderCommand` class when CommandParser identifies
`updateorder` or the `update` keyword in `order` mode.

> :information_source: Note:
> * MediVault checks if the `parameters` and `parameterValues` provided by the user are valid.
> * MediVault restricts updating of order information that are already **delivered**.

The sequence diagram for UpdateOrderCommand is shown below.

![UpdateOrderSequenceDiagram](diagrams/diagram_images/UpdateOrderSequenceDiagram.png)

### Archive Commands

#### ArchivePrescriptionCommand

#### ArchiveOrderCommand

## Product Scope

### Target user profile

* Pharmacist handling storing, ordering and dispensing of medication
* Has a need to manage large number of stocks in the pharmacy
* May forget how much medicine stock is left in the pharmacy
* Is a fast typist

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}