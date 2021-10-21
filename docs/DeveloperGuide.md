# Developer Guide

## UNIMods

**UNIMods** is a light-weight Command Line Interface (CLI) Application that provides a means for students to plan for
their academic journey in NUS. This guide details the implementation design and architecture that Unimods was built
upon.

## Contents

- [Acknowledgements]()
- [Setting up, getting started]()
- [Design]()
    * [Architecture]()
    * [UI component]()
    * [Logic component]()
    * [Model component]()
    * [Storage component]()
    * [Common classes]()
- [Implementation]()
    * [Add, Delete, Save, Clear, Show Modules, Display timetable]()
        * [Proposed Implementation]()
        * [Design considerations]()
    * [[Proposed] Data archiving]()
- [Documentation, logging, testing, configuration, dev-ops]()
- [Appendix: Requirements]()
    * [Product scope]()
    * [User stories]()
    * [Use cases]()
    * [Non-Functional Requirements]()
    * [Glossary]()
- [Appendix: Instructions for manual testing]()
    * [Launch and shutdown]()
    * [Deleting a person]()
    * [Saving data]()

## Acknowledgements

## Setting up, getting started

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest unimods.jar from [here](https://github.com/AY2122S1-CS2113T-W12-2/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your NUS Mods Lite.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it. Some example commands you can try:
    - `help` : to list all the commands with their description
    - `search <module_code>` : lists module code based on the given partial regex
    - `show <module_code>`: displays the module information
    - `add <module_code>` : to add the module to the timetable
    - `delete <module_code>` : to remove the module from the timetable
    - `timetable` : lists all modules added to the timetable
    - `exit` : Exits the app.
    - Refer to the [User Guide](https://ay2122s1-cs2113t-w12-2.github.io/tp/UserGuide.html) for details of each command.

