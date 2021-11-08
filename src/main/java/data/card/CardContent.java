package data.card;

import java.util.ArrayList;

public class CardContent {
    /**
     * Initial cards for diff games, each with a unique cardID.
     * Initial cardManager for cards and used cards.
     */
    public static ArrayList<Card> initCards() {
        ArrayList<Card> cardsInit = new ArrayList<>();
        // Init cards for diff games
        // For game Treasure
        // for easy level
        cardsInit.add(new Card("'Sequence' is the order that commands are executed by a computer",
                1, 0));
        cardsInit.add(new Card("'Sequence' is a set of logical steps carried out in order.",
                2, 0));
        cardsInit.add(new Card("Binary Search algorithm could only be implemented over a sorted array.",
                3, 0));
        cardsInit.add(new Card("Small unsorted arrays would take considerate time in sorting "
                + "and then searching the desired element. So, binary search is not preferred in such cases.",
                4, 0));
        cardsInit.add(new Card("Binary search has better time complexity and thus takes less compilation time",
                5, 0));
        cardsInit.add(new Card("In Java, everything is an Object. Java can be easily extended "
                + "since it is based on the Object model.", 6, 0));
        cardsInit.add(new Card("'break' terminates the loop or switch statement and transfers execution "
                + "to the statement immediately following the loop or switch.", 7, 0));
        cardsInit.add(new Card("'+' can be used to add values on either side of the operator.",
                8, 0));
        cardsInit.add(new Card("'+=' add AND assignment operator. It adds right operand to the left "
                + "operand and assign the result to left operand.", 9, 0));
        cardsInit.add(new Card("'*=' multiply AND assignment operator. It multiplies right operand with the "
                + "left operand and assign the result to left operand.", 10, 0));
        // for difficult level
        cardsInit.add(new Card("Objects have two characteristics: They have states and behaviors",
                11, 1));
        cardsInit.add(new Card("Abstraction is a process where you show only 'relevant' data "
                + "and 'hide' unnecessary details of an object from the user.", 12, 1));
        cardsInit.add(new Card("In java, Encapsulation simply means binding object state(fields) and "
                + "behaviour(methods) together. If you are creating class, you are doing encapsulation",
                13, 1));
        cardsInit.add(new Card("Constructor looks like a method but it is in fact not a method."
                + " It’s name is same as class name and it does not return any value. ",
                14, 1));
        cardsInit.add(new Card("The process by which one class acquires the properties and functionalities of "
                + "another class is called inheritance.", 15, 1));
        cardsInit.add(new Card("The biggest advantage of Inheritance is that the code in base class need not"
                + " be rewritten in the child class.", 16, 1));
        cardsInit.add(new Card("Single Inheritance: refers to a child and parent class relationship where a class"
                + " extends the another class.", 17, 1));
        cardsInit.add(new Card("Polymorphism is a object oriented programming feature that allows us to perform a"
                + " single action in different ways. ", 18, 1));
        cardsInit.add(new Card("An interface is a blueprint of a class, which can be declared by using"
                + " interface keyword. Interfaces can contain only constants and abstract methods "
                + "(methods with only signatures no body)", 19, 1));
        cardsInit.add(new Card("Java does not support Multiple Inheritance, however a class can implement more "
                + "than one interfaces", 20, 1));


        // For game GuessingNum
        // for easy level
        cardsInit.add(new Card("Binary search is an efficient algorithm"
                + " for finding an item from a sorted list of items.", 21, 0));
        cardsInit.add(new Card("It works by repeatedly dividing in half the portion of the list"
                + " that could contain the item, until you've narrowed down the possible locations to just one.",
                22, 0));
        cardsInit.add(new Card("Binary Search algorithm could only be implemented over a sorted array.",
                23, 0));
        cardsInit.add(new Card("Small unsorted arrays would take considerate time in sorting "
                + "and then searching the desired element. So, binary search is not preferred in such cases.",
                24, 0));
        cardsInit.add(new Card("Binary search has better time complexity and thus takes less compilation time",
                25, 0));
        cardsInit.add(new Card("Binary search is used to search element in a given "
                + "sorted array with more efficiency.", 26, 0));
        cardsInit.add(new Card("'&& (logical and)' is Called Logical AND operator. If both the operands are non-zero,"
                + " then the condition becomes true.", 27, 0));
        cardsInit.add(new Card("'=' is a simple assignment operator. Assigns values from right side operands "
                + "to left side operand.", 28, 0));
        cardsInit.add(new Card("The array elements are accessed through the index. Array indices are 0-based; "
                + "that is, they start from 0 to arrayRefVar.length-1.", 29, 0));
        cardsInit.add(new Card("When processing array elements, we often use either for loop or foreach "
                + "loop because all of the elements in "
                + "an array are of the same type and the size of the array is known.", 30, 0));
        // for difficult level
        cardsInit.add(new Card("Static variables are also known as Class Variables. Unlike non-static variables, "
                + "such variables can be accessed directly in static and non-static methods", 31, 1));
        cardsInit.add(new Card("A package as the name suggests is a pack(group) of classes, interfaces and other"
                + " packages. In java we use packages to organize our classes and interfaces.", 32, 1));
        cardsInit.add(new Card("Method Overloading is a feature that allows a class to have more than one method "
                + "having the same name, if their argument lists are different.", 33, 1));
        cardsInit.add(new Card("Static Polymorphism is also known as compile time binding or early "
                + "binding.", 34, 1));
        cardsInit.add(new Card("Static import allows you to access the static member of a class directly without "
                + "using the fully qualified name.", 35, 1));
        cardsInit.add(new Card("Switch case statement is used when we have number of options (or choices) and we "
                + "may need to perform a different task for each choice.", 36, 1));
        cardsInit.add(new Card("Switch Case statement is mostly used with break statement even though "
                + "it is optional.", 37, 1));
        cardsInit.add(new Card("Java is a high level programming language. A program written in high level "
                + "language cannot be run on any machine directly. First, it needs to be translated into that"
                + " particular machine language. ", 38, 1));
        cardsInit.add(new Card(" Each operating system has different JVM, however the output they produce "
                + "after execution of byte code is same across all operating systems. ", 39, 1));
        cardsInit.add(new Card("Java Virtual Machine (JVM) is a virtual machine that resides in the real machine "
                + "(your computer) and the machine language for JVM is byte code. ", 40, 1));

        // For game QuizGame
        // for easy level
        cardsInit.add(new Card("a variable is an abstract storage location paired "
                + "with an associated symbolic name", 41, 0));
        cardsInit.add(new Card("An if else statement in programming is "
                + "a conditional statement that runs a different set of statements"
                + " depending on whether an expression is true or false", 42, 0));
        cardsInit.add(new Card("Java provides a data structure, the array, which stores a fixed-size sequential "
                + "collection of elements of the same type.", 43, 0));
        cardsInit.add(new Card("A constructor initializes an object when it is created. It has the same name as "
                + "its class and is syntactically similar to a method. However, constructors have "
                + "no explicit return type.", 44, 0));
        cardsInit.add(new Card("Java defines several bitwise operators,"
                + " which can be applied to the integer types, "
                + "long, int, short, char, and byte.", 45, 0));
        cardsInit.add(new Card("'modifier' defines the access type of the method and it is optional to use.",
                46, 0));
        cardsInit.add(new Card("You can use one if or else if statement inside another if or else "
                + "if statement(s)", 47, 0));
        cardsInit.add(new Card("A switch statement allows a variable to be tested for equality against"
                + " a list of values.", 48, 0));
        cardsInit.add(new Card("Short data type is a 16-bit signed two's complement integer", 49, 0));
        cardsInit.add(new Card("Short data type can also be used to save memory as byte data type."
                + " A short is 2 times smaller than an integer", 50, 0));
        // for difficult level
        cardsInit.add(new Card("An inner class is declared inside the curly braces of another enclosing class. ",
                51, 1));
        cardsInit.add(new Card("Inner class acts as a member of the enclosing class and can have any access "
                + "modifiers: abstract, final, public, protected, private, static.", 52, 1));
        cardsInit.add(new Card("Continue statement is mostly used inside loops. Whenever it is encountered "
                + "inside a loop, control directly jumps to the beginning of the loop for next iteration, skipping the "
                + "execution of statements inside loop’s body for the current iteration. ", 53, 1));
        cardsInit.add(new Card("The class that extends the features of another class is known as child class, "
                + "sub class or derived class.", 54, 1));
        cardsInit.add(new Card("The class whose properties and functionalities are used(inherited) by another "
                + "class is known as parent class, super class or Base class.", 55, 1));
        cardsInit.add(new Card("Single Inheritance: refers to a child and parent class relationship where a "
                + "class extends the another class.", 56, 1));
        cardsInit.add(new Card("Multilevel inheritance: refers to a child and parent class relationship where a "
                + "class extends the child class. For example class C extends class B and class B extends class A.",
                57, 1));
        cardsInit.add(new Card("Hierarchical inheritance: refers to a child and parent class relationship where "
                + "more than one classes extends the same class. For example, classes B, C & D extends the"
                + " same class A.", 58, 1));
        cardsInit.add(new Card("Multiple Inheritance: refers to the concept of one class extending more than one"
                + " classes, which means a child class has two parent classes. ", 59, 1));
        cardsInit.add(new Card("Hybrid inheritance: Combination of more than one types of inheritance in a "
                + "single program.", 60, 1));

        // For game HangMan
        // for easy level
        cardsInit.add(new Card("a loop is a sequence of instructions that is continually "
                + "repeated until a certain condition is reached. ", 61, 0));
        cardsInit.add(new Card("A for loop is a control flow statement for specifying iteration, "
                + "which allows code to be executed repeatedly.", 62, 0));
        cardsInit.add(new Card("A \"While\" Loop is used to repeat a specific block of code an unknown"
                + " number of times, until a condition is met. ", 63, 0));
        cardsInit.add(new Card("An array is a data structure, which can store a fixed-size collection of "
                + "elements of the same data type. ", 64, 0));
        cardsInit.add(new Card("An algorithm is simply a set of steps used "
                + "to complete a specific task. ", 65, 0));
        cardsInit.add(new Card("Array is the variable that stores elements of a similar data type. ",
                66, 0));
        cardsInit.add(new Card("Knowledge of how to use a certain condition, "
                + "such that a set of code should execute only if a certain condition is true. "
                + "In case of a false condition,"
                + " the program should exit and should not continue the code further.", 67, 0));
        cardsInit.add(new Card("Class is like a template that contains state and behavior, "
                + "which corresponding to programming is field and method. "
                + "In Object-Oriented languages like Java, everything revolves around Class and Object.",
                68, 0));
        cardsInit.add(new Card("A function can take parameters and process them to get the desired output. ",
                69, 0));
        cardsInit.add(new Card("Abstraction, means ignoring the non-essential details of an object "
                + "and concentrating on its essential details.", 70, 0));
        // for difficult level
        cardsInit.add(new Card("constructor of sub class is invoked when we create the object of subclass, "
                + "it by default invokes the default constructor of super class. ", 71, 1));
        cardsInit.add(new Card("he superclass constructor can be called explicitly using the super keyword, "
                + "but it should be first statement in a constructor.", 72, 1));
        cardsInit.add(new Card("When we declare the same method in child class which is already present in the "
                + "parent class the this is called method overriding. ", 73, 1));
        cardsInit.add(new Card("Association establishes relationship between two separate classes through "
                + "their objects. ", 74, 1));
        cardsInit.add(new Card("Aggregation is a special form of association which is a unidirectional one "
                + "way relationship between classes (or entities)", 75, 1));
        cardsInit.add(new Card("Composition is a restricted form of Aggregation in which two entities "
                + "(or you can say classes) are highly dependent on each other. ", 76, 1));
        cardsInit.add(new Card("When there is an if statement inside another if statement then it is "
                + "called the nested if statement.", 77, 1));
        cardsInit.add(new Card("Java Annotations allow us to add metadata information into our source code,"
                + " although they are not a part of the program itself.", 78, 1));
        cardsInit.add(new Card("Polymorphism is one of the OOPs feature that allows us to perform a single"
                + " action in different ways.", 79, 1));
        cardsInit.add(new Card("An interface which is declared inside another interface or class is called nested "
                + "interface. They are also known as inner interface. ", 80, 1));

        return cardsInit;
    }
}
