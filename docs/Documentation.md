### Documentation Guide

* The `/docs` folder is used for documentation.
  * `/docs/umldg` is used to store UML Class/Sequence diagrams.
  * `/docs/team` is used for the PPP for each team member.
  * `/docs/images` is used for pictures for team members used for `Aboutus.md`
* Style guidance:
  * [Style Guidance](https://se-education.org/guides/conventions/markdown.html) can be used for reference.

#### Diagrams:

[Plant UML](https://plantuml.com/) is used for UML diagrams in this project.

Add the three lines below into the `*.puml` files for class diagrams to standardize with other class diagrams. 

```text
skinparam classAttributeIconSize 0
hide circle
hide empty members
```