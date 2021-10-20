#Developer Guide Draft

##Architecture
The Main component has 2 classes, MainParser and MainUI. It is responsible for parsing user commands and displaying messages to interact with the user.

The rest of the app consists of 4 components:  

1.Employee: Modify data related to employee classes  

2.Ingredient: Modify data related to ingredient classes  

3.Menu: Modify data related to menu classes  

4.Storage: Reads and writes data to and from the hard disk  

###Employee Component
The employee component consists of the following four classes: *Employee*, *EmployeeList*, *EmployeeParser* and *EmployeeUI*.

·*Employee* stores the name and phone number of an individual employee working at the restaurant, as well as methods to retrieve employee information.

·*EmployeeList* is an array list of Employees.

·*EmployeeParser* contains operations that decode user inputs into meaningful commands, and modifies the list of employees accordingly.

·*EmployeeUI* contains methods that display messages that interacts with the user.

###Ingredient Component
The ingredient component consists of the *Ingredient*, *IngredientList*, *IngredientParser*, and *IngredientUI* classes.  

·*Ingredient* stores the name and quantity of a particular ingredient used by the restaurant, as well as methods to retrieve ingredient data.  

·*IngredientList* is an array list of Ingredient.  

·*IngredientParser* contains operations that decode user inputs into meaningful commands, and modifies the list of ingredients accordingly.  

·*IngredientUI* contains methods that display messages to interact with the user.  

###Menu Component
The menu component consists of the *Menu*, *MenuList*, *MenuParser*, and *MenuUI* classes.  

·*Menu* stores the name and price of a certain menu item sold by the restaurant and the methods to retrieve menu data.  

·*MenuList* is an arrayList of Menu.  

·*MenuParser* contains operations that decode user inputs into meaningful commands and modify the list of ingredients accordingly.  

·*MenuUI* contains methods that display messages to interact with the user.  

The Menu Component,


###Storage Component
The storage component has a *Storage* class which can load data from the file and save data into the file with the methods to encode and decode data.  

The Storage Component,

##Design and Implementation  

###Add Employee Feature
The mechanism of adding an employee into the list of employees is facilitated by *EmployeeParser*. It first creates a new instance of *Employee*, and adds it to the existing instance of *EmployeeList*. A confirmation message is then displayed to the user.
###List Employees Feature
The mechanism for listing all current employees in the list of employees is facilitated by *EmployeeParser*. It checks if the current instance of *EmployeeList* is empty, and if it is, displays a message to inform the user that the employee list is empty. Otherwise, it displays all employees and their information in the list to the user.
###Remove Employee Feature
The mechanism of removing an employee from the employee list is facilitated by *EmployeeParser*. It identifies the index of the employee to be removed from the current instance of *EmployeeList*, and removes that instance of *Employee* from the list. It then displays a message to the user to inform them of the successful deletion.
###Add Ingredient Feature
The mechanism of adding an ingredient into the ingredient list is facilitated by *IngredientParser*. It creates a new instance of Ingredient, and adds it to the existing instance of IngredientList. It then calls a method from *IngredientUI* to display a confirmation message to the user.
###List Ingredients Feature
The mechanism for listing all existing ingredients in the ingredient list is facilitated by *IngredientParser*. It checks if the existing instance of *IngredientList* is empty or not, and calls a method from *IngredientUI* to display the entire list of ingredients and their quantities to the user.
###Remove Ingredient Feature
The mechanism of removing an ingredient from the ingredient list is facilitated by *IngredientParser*. It identifies the index of the ingredient to be removed from the existing instance of *IngredientList* and removes that instance of Ingredient from the list. It then calls a method from *IngredientUI* which displays to the user that the deletion was successful.
###Add Menu Feature
The mechanism of adding a menu item into the menu list is facilitated by *MenuParser*. It creates a new instance of Menu, and adds it to the existing instance of *MenuList*. It then calls a method from MenuUI to display a confirmation message to the user.
###List Menu Feature
The mechanism for listing all existing menu items in the menu list is facilitated by *MenuParser*. It checks if the existing instance of *MenuList* is empty or not, and calls a method from *MenuUI* to display the entire list of menu items and their prices to the user.
###Remove Menu Feature
The mechanism of removing a menu item from the menu list is facilitated by *MenuParser*. It identifies the index of the menu item to be removed from the existing instance of *MenuList* and removes that instance of the menu from the list. It then calls a method from *MenuUI* which displays to the user that the deletion was successful.
###Save Storage Feature
The mechanism of saving the data into the file is facilitated by *Storage*. After executing the bye command, the program goes into saving date stage automatically. It opens the file with a file writer, and for every list the program has, it gets the item from them. Then, it encodes the string representation of the item, and writes it into the file.   

Besides, the three lists are stored in sequence as below,
###Load Storage Feature
The mechanism of loading the data from the file is facilitated by *Storage*. It opens the file with a file reader and if there are no files to open, it will automatically create a new file of default address. Every line read from the file would be decoded with methods to create a new *Employee*/*Menu*/*Ingredient* item, and add it into the list which is generated.  

Besides, the three types of new item are identified before decoding it, and the decoding methods are worked on at the same time.

