# Decodex User Guide <!-- omit in toc -->

## Table of Contents <!-- omit in toc -->
- [Introduction](#introduction)
- [Terminologies](#terminologies)
- [Symbols](#symbols)
- [Quick Start](#quick-start)
- [List of Available Modules](#list-of-available-modules)
- [Features](#features)
    - [Basic Commands](#basic-commands)
        - [Help: `help`](#help-help)
        - [Input of Data: `input`](#input-of-data-input)
        - [List Available Modules or Recipes: `list`](#list-available-modules-or-recipes-list)
        - [Select a Module or Recipe: `select`](#select-a-module-or-recipe-select)
        - [Show Current Data: `show`](#show-current-data-show)
        - [Reset Data: `reset`](#reset-data-reset)
        - [Exit the Program: `exit`](#exit-the-program-exit)
    - [Recipe Commands: `recipe`](#recipe-commands-recipe)
        - [Create a New Recipe: `recipe new`](#create-a-new-recipe-recipe-new)
        - [Select a Recipe for Editing: `recipe select`](#select-a-recipe-for-editing-recipe-select)
        - [Deselect the Current Editing Recipe: `recipe deselect`](#deselect-the-current-editing-recipe-recipe-deselect)
        - [List Modules in a Recipe: `recipe list`](#list-modules-in-a-recipe-recipe-list)
        - [Add a Module to a Recipe: `recipe push`](#add-a-module-into-a-recipe-recipe-push)
        - [Remove a Module from a Recipe: `recipe pop`](#remove-a-module-from-a-recipe-recipe-pop)
        - [Clear All Modules in a Recipe: `recipe reset`](#clear-all-modules-in-a-recipe-recipe-reset)
        - [Delete a Recipe: `recipe delete`](#delete-a-recipe-recipe-delete)
    - [Storage of Recipe Files](#storage-of-recipe-files)
        - [Load Recipe Files on Startup](#load-recipe-files-on-startup)
        - [Save Recipe to File](#save-recipe-to-file)
        - [Delete Recipe File](#delete-recipe-file)
    - [Reading/Writing Data from/to File `coming soon in the future`](#readingwriting-data-fromto-file-coming-soon-in-the-future)
- [Command Summary](#command-summary)
- [FAQ](#faq)

<div style="page-break-after: always;"></div>

## Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to perform [encoding](#terminologies), [decoding](#terminologies), [encryption](#terminologies) and [decryption](#terminologies) of data**, which come in the form of [modules](#terminologies) that can be **executed with ease** and **without any programming** needed. Decodex also provides [recipes](#terminologies) which can also be used to link several of these [modules](#terminologies) together so that they could be executed in one go to speed up repetitive tasks. The intuitive interaction can thus help to speed up a player’s performance during CTFs and save time without having to manually code the tedious [data transformations](#terminologies).

This guide serves to help you understand the usage of the program, which includes performing data transformations using [basic commands](#basic-commands) as well as developing and managing recipes using [recipe commands](#recipe-commands-recipe).

> :information_source: This user guide is tailor-made for CTF players who have some technical understanding of encoding schemes and cryptography.

![carbon(6).png](images/carbon(6).png)

<div style="page-break-after: always;"></div>

## Terminologies

This section serves to help you better understand the terminologies used in this user guide.

| Terminology                 | Definition                                                                                                                                                                                |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Data transformation         | The conversion of one data format to another.                                                                                                                                             |
| Application, Program        | Refers to the Decodex program. These two terms are used interchangeably in this User Guide.                                                                                               |
| Encoding                    | Converting a message into a coded form.                                                                                                                                                   |
| Decoding                    | Converting a coded message into an intelligible form.                                                                                                                                     |
| Encryption                  | Converting a message into a form where only authorized parties are able to decipher it.                                                                                                   |
| Decryption                  | Converting an encrypted message back to its original form by deciphering it.                                                                                                              |
| Base64, Binary, Hexadecimal | Common types of data encoding standards.                                                                                                                                                  |
| Console                     | This refers to your command prompt window.                                                                                                                                                |
| Argument                    | Additional information that you provide to the program's command.                                                                                                                         |
| Module                      | A self-contained set of instructions to process your data into another form. A full list of supported modules can be found [here](#list-of-available-modules).                            |
| Recipe                      | Acts as a container for you to link several modules together that will be executed back-to-back on your data.<br>By default, the application does not have any default recipes installed. |

<div style="page-break-after: always;"></div>

## Symbols

| Symbol               | Definition                                                                                                |
| -------------------- | --------------------------------------------------------------------------------------------------------- |
| :bulb:               | Represents a good tip for you.                                                                            |
| :exclamation:        | Represents something important that you should take note of.                                              |
| :information_source: | Represents additional information regarding commands/features for you to better understand how to use it. |
| :video_game:         | Represents something optional that you can try out, mostly for exploring or fun.                          |

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have Java version `11` or above installed on your computer.
   1. If you haven't, you may download it [here](https://www.oracle.com/java/technologies/downloads/#java11-linux).
    > :exclamation: You should download the installation relative to your Operating System.
2. Next, download the latest `decodex.jar` [here](https://github.com/AY2122S1-CS2113T-T10-3/tp/releases/latest).
   1. Simply click on `decodex.jar` under "Assets" and the download should start.
3. After downloading, you can open up `command prompt`.
    > :bulb: To open command prompt, press `win + r` at the same time, then type and enter `cmd`.
4. Afterwards, you can run `decodex.jar` by typing in `java -jar decodex.jar` and Decodex's prompt should appear. Please also ensure that you are in the same directory as where you have downloaded `decodex.jar`.
   1. In the screenshot below, you can see that `decodex.jar` is run in the `Downloads` folder. To add on, it also shows you how to better understand the startup prompt of Decodex.
   ![quickStartPrompt](images/quickStartPromptEdited.png)
5. Now, you are ready to try out some of the basic commands below:
   1. `input I am groot`:  Inputs the text data `I am groot` into the program.
   2. `list`: Lists all available modules and recipes that you can use.
   3. `select module base64encode`: Selects and runs the base64-encoding module on the data.
   4. `reset`: Resets the changes made to data - resetting to its original data.
   5. `exit`: Exits the application.
6. For more information on Decodex's features, please refer to the Features Section.

<div style="page-break-after: always;"></div>

> :video_game: To make things more interesting while learning how to use Decodex, you may try to find out what `NTEgMzEgNTIgNDcgNjUgMzAgNGUgNTQgNGQgNmEgNDUgNzggNGQgMzEgNTIgMzk=` using our application! If you are new here, you can continue reading this guide to understand how to unravel this mysterious text!

## List of Available [Modules](#terminologies)
Below, you can see the list of available modules and what they do. <a name="selectModuleExamples"></a>

> :information_source: These are some of the more common encoding/decoding/cipher methods that can be found in CTF competitions.


| Module         | Description                                                                                              | `moduleArgument`                                                                                       |
| -------------- | -------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| `base64encode` | Encodes the data using base64 format.                                                                    | N.A                                                                                                    |
| `base64decode` | Decodes the data using base64 format.                                                                  | N.A                                                                                                    |
| `hexencode`    | Converts the input string to hexadecimal bytes.                                                          | N.A                                                                                                    |
| `hexdecode`    | Converts a hexadecimal string back into its raw value.<br><br> Note that this module does not accept any form of separators (space, commas, etc.) in the data.                                                   | N.A                                                                                                    |
| `binencode`    | Encodes the data using binary format.                                                                    | N.A                                                                                                    |
| `bindecode`    | Decodes the data using binary format.<br><br> Note that this module does not accept any form of separators (space, commas, etc.) in the data.                                                                    | N.A                                                                                                    |
| `rotencode`    | Rotates alphabetical characters by a specified integer offset.<br><br>E.g., `select module rotencode 13`, `recipe push rotencode 13` | The rotation offset.<br><br>Must be an integer between `-2,147,483,648` (`MIN_INT`) and `2,147,483,647` (`MAX_INT`). |

<div style="page-break-after: always;"></div>

## Features

> :exclamation: Notes about the commands and arguments:
> * Arguments enclosed in `<>` are **mandatory** arguments while arguments enclosed in `{}` are **optional** arguments. For example, `select <moduleName> {moduleArgument}` would mean that `moduleName` is mandatory while `moduleArgument` is optional.
> * Arguments must be entered in the **exact order and position** as specified in the commands' respective formats.
> * All commands and arguments are **case-sensitive**.
> * After the execution of a command, you may see a `[+]` , which means that it ran successfully or a `[x]`, which denotes that an error has occurred.

### Basic Commands

#### Help: `help`

Lists the syntax and descriptions of all available commands.

Format: `help`

Example:

![help.png](images/ug/help.png)

#### Input of Data: `input`

Stores the data to be processed by modules.

Format: `input <data>`

> :information_source: `data` will be treated as text.

Examples:

- `input HelloWorld!` Enters the plain-text `HelloWorld!` as data.
- `input SGVsbG9Xb3JsZA==` Enters the base64-encoded text `SGVsbG9Xb3JsZA==` as data.

![carbon(1).png](images/ug/inputCommandExample.png)

> :exclamation: Behaviour of the `input` command:
> * Note that the program will consider **all characters as data following the first space character** in the input command. You may observe leading and trailing whitespaces in the output.
> * **Non-printable characters** (e.g. null characters) may not be correctly displayed and could potentially scramble the user interface.


#### List Available [Modules](#terminologies) or [Recipes](#terminologies): `list`

Shows a list of all available modules or recipes.

Format: `list {category}`

> :information_source: Notes about the `list` command:
> * The acceptable values of `category` are `modules` and `recipes` .
> * When `category` is not specified, both lists of modules and recipes are printed.

Examples:

- `list` Lists all available modules and recipes.
- `list modules` Lists available modules only.
- `list recipes` Lists available recipes only.

![carbon(8).png](images/ug/ListCommandExample.png)

#### Select a [Module](#terminologies) or [Recipe](#terminologies): `select`

Selects a module or recipe and processes the data accordingly. Subsequent selection of modules or recipes will process the transformed data output from the previous module or recipe.

Format: `select module <moduleName> {moduleArgument}`, `select recipe <recipeName>`

> :information_source: Notes about the `select` command:
> * `moduleName` is the name of an available module supported by Decodex. `moduleArgument` is an argument that certain modules accept as input.
> * `recipeName` is the name of an available recipe that is loaded by the program.
> * You may also refer [here](#selectModuleExamples) for more details on our modules and its usage.

Examples:

- `select module base64decode` Runs the `base64decode` module on the current data.
- `select module rotencode 13` Runs the `rotencode` module with argument `13` on the current data.
- `select recipe testRecipe1` Runs the `testRecipe1` recipe on the current data.

![carbon(9).png](images/ug/SelectCommandExample.png)

#### Show Current Data: `show`

Shows you the current data.

Format: `show`

#### Reset Data: `reset`

Resets the transformed data back to the original input.

Format: `reset`

#### Exit the Program: `exit`

Exits the program.

Format: `exit`

> :video_game: If you are trying out the side-quest from earlier and can't seem to find the solution to it, you may refer to the solution in the picture at the start of the user guide!

### [Recipe](#terminologies) Commands: `recipe`

> :information_source: During the execution of the application, it would display the name of the recipe that is "currently being edited" (if any) in `[]` of the application's prompt. This currently edited recipe will be the target for some `recipe` commands such as [`recipe push`](#add-a-module-into-a-recipe-recipe-push), [`recipe pop`](#remove-a-module-from-a-recipe-recipe-pop) and [`recipe reset`](#clear-all-modules-in-a-recipe-recipe-reset).
> 
> ![carbon(17).png](images/carbon(17).png)

<div style="page-break-after: always;"></div>

#### Create a New [Recipe](#terminologies): `recipe new`

Creates a new recipe and sets it as currently being edited.

Format: `recipe new <recipeName>`

> :information_source: `recipeName` can only contain alphabets, numbers and underscores.

Example:

- `recipe new testRecipe` Creates a new recipe with the name `testRecipe`.

![recipeNew.png](images/recipeNew.png)

#### Select a [Recipe](#terminologies) for Editing: `recipe select`

Sets a recipe as currently being edited.

Format: `recipe select <recipeName>`

Example:

- `recipe select testRecipe` Selects `testRecipe` as the recipe to be edited.

![recipeSelect.png](images/recipeSelect.png)

<div style="page-break-after: always;"></div>

#### Deselect the Current Editing [Recipe](#terminologies): `recipe deselect`

Deselects the recipe currently being edited.

Format: `recipe deselect`

Example:

![recipeDeselect.png](images/recipeDeselect.png)

#### List [Modules](#terminologies) in a [Recipe](#terminologies): `recipe list`

Show all the modules in a recipe, in the order they were added.

Format: `recipe list {recipeName}`

> :information_source: When `recipeName` is not specified, modules from the recipe that is currently being edited will be listed instead.

Example:

- `recipe list testRecipe` Lists the modules in the recipe named `testRecipe`.

![recipeList.png](images/recipeList.png)

<div style="page-break-after: always;"></div>

#### Add a [Module](#terminologies) into a [Recipe](#terminologies): `recipe push`

Adds a module into the recipe that is currently being edited.

Format: `recipe push <moduleName> {moduleArgument}`

Example:

- `recipe push base64encode` Adds the `base64encode` module into the current editing recipe.
- `recipe push rotencode 13` Adds the `rotencode` module with a single argument `13` into the current editing recipe.

![recipePush.png](images/recipePush.png)

#### Remove a [Module](#terminologies) from a [Recipe](#terminologies): `recipe pop`

Removes the most recently added module from the recipe that is currently being edited.

Format: `recipe pop`

![recipePop.png](images/recipePop.png)

#### Clear All [Modules](#terminologies) in a [Recipe](#terminologies): `recipe reset`

Removes all modules from the recipe that is currently being edited.

Format: `recipe reset`

![recipeReset.png](images/recipeReset.png)

#### Delete a [Recipe](#terminologies): `recipe delete`

Deletes a recipe from the program.

Format: `recipe delete <recipeName>`

Example:

- `recipe delete testRecipe` Deletes the recipe with the name `testRecipe`.

![recipeDelete.png](images/recipeDelete.png)

<div style="page-break-after: always;"></div>

### Storage of Recipe Files

> :exclamation: Notes about the recipe files
> * A recipe will correspond to a recipe file which is a plaintext file with the name `<recipeName>.txt` where `recipeName` is the name of the recipe.
> * It is highly recommended that you only modify recipe files through the [`recipe`](#recipe-commands-recipe) commands in Decodex and not manually edit the recipe files.
> * If you choose to manually edit the recipe files, the correct format would be that each line in the respective recipe file should contain only one module, and they should be formatted in the following syntax of `<moduleName> {moduleArgument}`. You should also not be editing while Decodex is running as Decodex could accidentally overwrite your changes.
> * Incorrectly edited files will be considered as invalid and will not be loaded on program start up.
> * The recipe files would also not be loaded successfully if Decodex does not have access rights to the files, or it is not in the valid filetype.

#### Load Recipe Files on Startup

Loads the recipes from the recipe files found in the `recipe/` directory. This is done automatically for you on startup so that you do not have to manually add multiple recipe files into Decodex each time.

#### Save Recipe to File

Saves the recipe to a recipe file in the `recipe/` directory. This is done automatically for you whenever a recipe is created or modified so that it is more convenient and less punishing for you (especially if you often forget to save your documents before exiting).

#### Delete Recipe File

Deletes the recipe file in the `recipe/` directory. This is also done automatically for you (via the `recipe delete` command), so that you do not have to manually find the corresponding recipe file to delete it.

### Reading/Writing Data from/to File `[coming soon in the future]`

Coming soon to a cinema near you in the future…

<div style="page-break-after: always;"></div>

## Command Summary

| Action                                      | Command Format                                                             | Example Usage                                                                             |
| ------------------------------------------- | -------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| View the syntax and description of commands | `help`                                                                     | `help`                                                                                    |
| Enter input data                            | `input <data>`                                                             | `input HelloWorld!`                                                                       |
| List available modules/recipe               | `list {category}`                                                          | `list` <br> `list modules` <br>`list recipes`                                                    |
| Select processing module/recipe             | `select module <moduleName> {moduleArgument}` <br>&ensp;OR<br> `select recipe <recipeName>` | `select module base64decode`<br> `select module rotencode 13` `select recipe testRecipe1` |
| View current data                           | `show`                                                                     | `show`                                                                                    |
| Reset to original data                      | `reset`                                                                    | `reset`                                                                                   |
| Create new recipe                           | `recipe new <recipeName>`                                                  | `recipe new testRecipe`                                                                   |
| Select recipe for editing                   | `recipe select <recipeName>`                                               | `recipe select testRecipe`                                                                |
| Deselects the recipe currently being edited | `recipe deselect`                                                          | `recipe deselect`                                                                         |
| List modules in recipe                      | `recipe list {recipeName}`                                                 | `recipe list testRecipe`                                                                  |
| Add module to recipe                        | `recipe push <moduleName> {moduleArgument}`                                | `recipe push base64encode`<br> `recipe push rotencode 13`                                 |
| Remove module from recipe                   | `recipe pop`                                                               | `recipe pop`                                                                              |
| Clear all modules in recipe                 | `recipe reset`                                                             | `recipe reset`                                                                            |
| Delete recipe                               | `recipe delete <recipeName>`                                               | `recipe delete testRecipe`                                                                |
| Exit program                                | `exit`                                                                     | `exit`                                                                                    |

## FAQ

**Q:** How do I transfer my recipes to another computer?  
**A:** Install the app on the other computer and copy the recipe files from the computer that you are transferring from, into the recipe folder of the computer that you are transferring to.
