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

### Command

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

### Prescription Commands

#### AddPrescriptionCommand

#### DeletePrescriptionCommand

#### UpdatePrescriptionCommand

### Order Commands

#### AddOrderCommand

#### DeleteOrderCommand

#### UpdateOrderCommand

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