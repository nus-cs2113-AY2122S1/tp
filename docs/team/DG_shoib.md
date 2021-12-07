# Zoom link feature

## Showing all available zoom links

This command is implemented by the `ShowZoomLinks` class. The basic functionality of this command is to access the data stored on the local drive to display the relevant zoom links along with the module codes.
The `ShowZoomLinks` class extends `Command`.

Syntax: `zoom list`

## Adding a new zoom link

This command is implemented by the `AddZoomCommand` class. The basic functionality of this command is to write a new zoom link to a local storage file and associate it to the relevant module code. The `AddZoomCommand`
class extends `Command`.

Syntax: `zoom add LINK MODULE_CODE`
Example: `zoom add nus.sg/testlink ABC101`

