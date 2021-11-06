# Tan Juen Woo - Project Portfolio Page

## Overview

**TermiNUS** is a CLI (command line interface) program for NUS students who wish to consolidate their NUS academic needs such as schedules, questions and notes for the modules that they are taking. With TermiNUS, it aims to aid students and improve their learning experiences while studying in NUS.

## Summary of Contributions

- Enhancement Implemented
  - **New Feature:** Added the ability to add, delete, view and reload for Note related content.
    - What it does: Allow user the user to add, delete, view and reload note.
    - Justification: This is one of the main feature proposed by Terminus where user can manage their academic notes. Allowing users to manage their notes by using Terminus while studying. 
    - Highlights: The challenge in this implementation is to ensure that other content type could also use the same generic code. Due to note being the first content type in Terminus, there is a need to design the entire structure of how a content is being managed within Terminus.
  - **New Feature:** Added a file storage system in Terminus.
    - What it does: Ensure data from Terminus are saved into their respective files / folders and ensure that they are loaded upon the next execution of Terminus.
    - Justification: User data in Terminus should be handled properly when saving, loading or deleting. 
    - Highlights: The challenge is to anticipate any corner case in which a filesystem could fail when performing any file I/O operations. Such examples could be due to permission issues or missing file / folder. Further challenges are os dependencies issues where the file system of windows differs from the file system in Linux.
  - **New Feature** Added the ability to filter data loaded from json.
    - What it does: Ensure that the data loaded from json follow the criteria of its content type.
    - Justification: Ensure Terminus can function as per normal and prevent any errors arose from loading that may crash Terminus.
- Code Contributed: [RepoSense Link.](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=t10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=Woolicious98&tabRepo=AY2122S1-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
- Documentation:
  - User Guide:
    - Improved documentation for Installing Terminus. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Added documentation for using this guide. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Added documentation for each workspace and their overview. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Added documentation for quick guide of TermiNUS. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Added workspace command summary. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Improved command summary. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
    - Contributed to overall structure. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114)
  - Developer Guide:
    - Added documentation for sections, Setting Up, Purpose and Product Scope. [#190](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/190)
    - Added implementation details for sections, Storage, Adding Content and Deleting Content. [#190](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/190)
    - Added component details for Storage Components. [#190](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/190)
- Team-based Tasks
  - Increased code coverage. [#143](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/143), [#79](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/79), [#82](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/82), [#123](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/123)
  - Synchronised languages in JavaDocs. [#89](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/89)
  - Overall documentation responsibilities. [#114](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/114), [#190](https://github.com/AY2122S1-CS2113T-T10-2/tp/pull/190)
- Beyond Project Team Tasks
  - Reported bugs for another team in PE-D. [PE-D Issues](https://github.com/Woolicious98/ped/issues)
  - Reviewed other teams’ User Guide and Developer Guide.


