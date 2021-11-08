# Mohamed Irfan - Project Portfolio Page

## Overview
Accouminate helps university students to keep track of their finances to aid them in attaining better financial literacy. It is designed to clearly show the users their budgets, expenses and income to illuminate their paths towards financial literacy.

### Summary of Contributions
* **Code contributed**: [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=mohamedirfansh)

* **New Features implemented**: 
	* User `expense`
		- `What it does` - Allows user to keep track of all their expenses.
		- `Justification` - To attain better financial literacy, the user has to be able to keep track of their expenses and all related functionalities to it.
		- Fully implemented all features related to adding a user expense from start to finish.
		- Fully implemented CRUD functionality for expenses.
		- Implemented other necessary functions for the CRUD functionality to work. e.g. Find function to allow for `expense delete`.
		- Fixed various bugs to ensure feature was robust.
		- Wrote various unit tests for the entities: `Expense`, `ExpenseList`.
		- Wrote various unit tests for the service class `ExpenseManager`.
	* Internal help guide
		- `What it does` - Shows the user the available commands for use.
		- `Justification` - Allows the user to find out the available commands more easily than searching through the User Guide.
		- Used the `picocli` library to implement the `help` command.
		- Stored all `help` messages in separate `static` class.
	* Warning feature when expense is about to exceed remaining budget
		- `What it does` - Warns the user when their latest added expense is within the warning limit they have set, to exceed their remaining budget.
		- `Justification` - If the user is spending carelessly and keeps adding expenses, setting a warning amount to relative to their budget will keep them informed about the spending limits.
		- Cross-checked `budget` functionality implemented by another teammate to implement feature.
		- Used the `picocli` library to implement the necessary commands such as the command to set the warning limit.

* **Enhancement to existing features**: 
	* Gathered all error & warning messages in a central location via the use of `static` classes.
		- Instead of simply keeping the message constants as variables within class that uses it, I gathered all of them in a static class that gathers everything in one place for better organization and usability.
	* Fixed multiple `functionality bugs` found ([#16](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/16), [#55](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/55), [#56](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/56), [#57](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/57), [#58](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/58), [#59](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/59), [#62](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/62), [#65](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/65), [#69](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/69), [#72](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/72), [#86](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/86), [#88](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/88)) via PR [#92](https://github.com/AY2122S1-CS2113-T16-4/tp/pull/92).
	* Fixed multiple `documentation bugs` found ([#53](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/53), [#54](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/54), [#60](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/60), [#61](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/61), [#89](https://github.com/AY2122S1-CS2113-T16-4/tp/issues/89)) via PR [#91](https://github.com/AY2122S1-CS2113-T16-4/tp/pull/91).


* **Contributions to User Guide**
	- Wrote the necessary details for the commands related to 'expense' feature.
	- Wrote the necessary details for the `help` & `exit` commands.
	- Helped with writing details for the other feature commands.
	- Helped with command overview.

* **Contributions to Developer Guide**
	- Formulated the user stories for the group and added them in the DG. [#42](https://github.com/AY2122S1-CS2113-T16-4/tp/pull/42)

* **Contributions to team-based tasks**
	- Setup Organization & Team Repository.
	- Setup GitHub pages for teams docs.
	- Setup & manage issue tracker to have all labels.
	- Helped to manage project milestones.

* **Review/mentoring contributions**
    - Helped group mates with problems related to `style-checks` like [#22](https://github.com/AY2122S1-CS2113-T16-4/tp/pull/22) and helped them in our telegram chat.
    - Helped group mates with problems related to usage of `git` in our telegram chat.
    - Various other bug related, code style related and java related help to all group mates done in our telegram chat.
    - Renamed unit tests done by other group mates to match coding standard as seen here [PR #101 commit 87bb21ca](https://github.com/AY2122S1-CS2113-T16-4/tp/pull/101/commits/87bb21ca398346d0fdd970e499ed5b6948476276).