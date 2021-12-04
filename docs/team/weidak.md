---
layout: page
title: Weida's Project Portfolio Page
---


## Overview

Fitbot is a desktop app that helps university students who are looking to keep track of their calorie consumption and calorie output with the speed and convenience of command-line based tools, especially in times of online school.

### Summary of Contributions
The following items listed below are what I have contributed to the team:

#### **New Feature**: Profile

- What it does: Contains the user's attributes such as Name, Height, Weight, Age, Gender, Calorie Goal, Activity Factor.
- Justification: The main idea behind the profile was to make Fitbot feel more personalized and let it know your characteristics. Most of these attributes are required in the Harris-Benedict Equation we used to calculate one's Basal Metabolic Rate, which is
  important in determining an accurate calorie budget for the day.
- Highlights: This feature incorporates its individual classes of each attribute to ensure data integrity is preserved and profile is a container that
  holds these attributes together.

#### **New Feature**: Saving and loading of data from Storage

- What it does: It allows users to load all the data upon startup of the application and save data depending on the command that is being executed. 
- Justification: Having a storage utility is crucial in our app's functionality of calculating the past week of exercise/food consumed,
as well as remembering one's profile attributes that lead to the calculations.
- Highlights: This feature loads and saves all the different data structures we have come up with, including FoodList, ExerciseList, FutureExerciseList, ItemBank, Profile. It can detect an anomaly within the file and handle it in a way that it can be dealt with later on
in another state whereby users can modify their attributes again in case they have accidentally corrupted a particular attribute.


#### **Code Contributed**:

[Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=weidak&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=weidak&tabRepo=AY2122S1-CS2113T-F14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### **Project Management**:

- Aided in setting up the GitHub repo and initializing the project during the early stages of the project
- Created issues and user stories for `v1.0`, and generally helped to find bugs and assign new issues on a consistent basis such as Documentation or type.Task related.



[comment]: <> (TODO add the PRs reviewed on github prs..)

#### **Enhancements To Existing Features**:

**Add verifiability to various Profile attributes**

- As our program loads the attributes from storage (to see whether it has been modified maliciously), it detects the attribute validity. With this
implementation, we are able to utilize the StartState (implemented by Yi Zhi) to rectify the problem without discarding the entire profile data. ([#88](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/88))

**Creation of StorageManager Class**

- As we grew in our list of features, our storages for the various features grew as well. As such, acting as an agent, it encapsulates all storages required to make our program work with their respective functionality (loading/saving). ([#99](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/99))

**Creation of DataManager Class**

- After deliberating with the other members, a DataManager class was created to encompass all the different types of Data we have (FoodList, Profile, ExerciseBank etc.)
and ensured all the items are localised in this class. This de-clutters Main.java and primarily acts as an agent for the various items. ([#143](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/143))

**Storage OOP**

- Using a `Storage.java` API, it interfaces all other Storage-related interfaces and ensures the methods for each `Storage` superinterface is implemented. As such, they are initialised by each storage's class of `XYZStorageUtils.java` and brought together in the `StorageManager.java` class.

#### **Documentation**:


- User Guide:
  - [Section 4.2 (Customising your profile)](https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html#41-customising-your-profile) ([#126](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/126), [#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
  - [Section 6 (Data Limits)](https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html#6-data-limits) ([#243](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
- Developer Guide:
  - Diagrams: 
    - [Data Component Diagram (Design)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#data-component) ([#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
    - [Profile Class Diagram (Design)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#data-component-profile) ([#108](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/108), [#135](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/135), [#208](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/208)[#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
    - [Storage Class Diagram (Design)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#storage-component) ([#108](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/108), [#135](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/135), [#208](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/208), [#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
    - [All the diagrams under "Loading of Data on Startup" (Implementation)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#loading-of-data-on-startup) ([#208](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/208) ,[#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
  - Explanations: 
    - [Explanation for Profile Component (Design)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#data-component-profile) ([#108](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/108), [#135](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/135), [#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
    - [Explanation for Storage Component (Design)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#storage-component) ([#108](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/108), [#135](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/135), [#208](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/208), [#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
    - [Explanation for "Loading of Data on Startup" (Implementation)](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#loading-of-data-on-startup) ([#208](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/208), [#238](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/238))
  - Manual Testing:
    - [Manipulating and saving data](https://ay2122s1-cs2113t-f14-2.github.io/tp/DeveloperGuide.html#manipulating-and-saving-data) ([#229](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/229))

#### **Community**:

- Helped other team review their Developer Guide ([here](https://github.com/nus-cs2113-AY2122S1/tp/pull/38/files/573949f70d1e1057b046baeb5df957ba63857559))
- PRs reviewed (With non-trivial comments): [#34](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/34), [#35](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/35),
    [#80](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/80), [#119](https://github.com/AY2122S1-CS2113T-F14-2/tp/pull/119)