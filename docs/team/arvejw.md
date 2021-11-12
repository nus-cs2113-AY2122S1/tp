# Teh Jiewen - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator 
which can give you an idea of your current fitness level.

### Summary of Contributions
#### Code contributed
[RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=arvejw&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements implemented
* Weekly recurring workouts and automatic deletion/rescheduling of scheduled workouts
  * What it does: Recurring workouts are rescheduled once its date is passed. Similarly non-recurring
    workouts whose dates have passed are deleted. 
  * Justification: This feature ensures the workout schedule remains neat and clean. Furthermore, it does not
    make sense for overdue workouts to remain in the list as the `WorkoutTracker` class already helps with the recording 
    of past workouts.
  * Highlights: Workouts are rescheduled by incrementing the date of the workout by a multiple of 7 that makes the 
    workout's new date be within 7 days from the current date. The deleting and rescheduling of workouts is also done 
    upon loading of user data. While I had some idea of how I wanted to achieve this feature, it proved harder than
    expected. "Rescheduling" by itself was fairly achievable after looking more in `java.time.*` and using the `plusDays()` method
    from `java.time.LocalDateTime`. However, integrating this rescheduling in addition to deleting of overdue workouts
    took quite a bit of time and effort as various other factors also like conditional checks and exception handling had
    to be considered as well.

* Optional activity breakdown for scheduled workouts
  * What it does: Gives an option for users to include a comprehensive activity breakdown for their schedule workout.
  * Justification: When scheduling and planning workouts, some users may want a more detailed breakdown of their workout
    instead of just a single workout description. For instance if I have an upper body workout scheduled, I want to have a
    break down of my activities during the workout, such as how many sets and reps of bench press, etc.
  * Highlights: When parsing activities, an activity description is paired with its respective activity quantifiers 
    using HashMaps where the activity description is the key, and the activity quantifier(s) is/are the value. 
    For 3 special activity descriptions, namely running/cycling/swimming, the quantifier will take in one integer 
    to represent the distance in metres. Otherwise, the activity will be assumed to be quantified with [sets]x[reps], 
    taking in two integers instead. Having some knowledge of HashMaps and how they work, but not having any experience 
    actually using them in code proved for an interesting learning experience. I had quite a lot of instances of 
    addresses being printed instead of the values I was looking for. This was when I started out using an Array as my value 
    in the HashMap in the earlier stages of implementation (or rather attempt at implementation). However, after 
    some trial and error and learning more about how HashMaps work in Java, I was able to discover the flaws in 
    my implementation and correct them. 

#### Contributions to UG

* Wrote add/delete/list workout command sections.
* Wrote add/delete/list schedule commands sections.
* Wrote some parts of known limitations section.

#### Contributions to DG

* ScheduleTracker Design & Implementation section.
* UML Class and sequence diagrams associated with ScheduleTracker.
* Some reformatting of the Design & Implementation section to be neater.
* Added appendix sections in the DG as well as some content to them.
* Added manual testing for workouts, scheduling of workouts and saving of data.

#### Contributions to team-based tasks
* Some reformatting of the Design & implementation section in the DG.
* Added hyperlink support to table of contents in DG.
* Contributed in debugging code.
* Changed some parsing implementation to split user input by any length of whitespace instead of a fixed single whitespace character.

#### Review/mentoring contributions
* Group mate PR reviews:
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/88](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/88)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/77](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/77)  
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/104](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/104)
    
* Peer PR reviews:
  * [https://github.com/nus-cs2113-AY2122S1/tp/pull/8/files/dcf009f3710b916496b8a710a3af954637d46056](https://github.com/nus-cs2113-AY2122S1/tp/pull/8/files/dcf009f3710b916496b8a710a3af954637d46056)
  * [https://github.com/nus-cs2113-AY2122S1/ip/pull/198/files/f0e08de91b407cf0c41f9055e8eaca2e9004e0ae](https://github.com/nus-cs2113-AY2122S1/ip/pull/198/files/f0e08de91b407cf0c41f9055e8eaca2e9004e0ae)
  * [https://github.com/nus-cs2113-AY2122S1/ip/pull/131/files/696012c5533fdfff551896a024445a1a56eb669c](https://github.com/nus-cs2113-AY2122S1/ip/pull/131/files/696012c5533fdfff551896a024445a1a56eb669c)  
    
