# Battleship
### **Active: Sprint 3**

This repository contains the code written by team 22 for Sprints 1, 2, and 3 of the Battleship group project as assigned in CS 361 (Software Engineering I) at Oregon State University.

&nbsp;

The following instructions were given for this assignment:

# *Sprint 1*

## Game Rules

### Game Objective

The object of Battleship is to try and sink all of the other player's before they sink all of your ships. All of the other player's ships are somewhere on his/her board. You try and hit them by calling out the coordinates of one of the squares on the board. The other player also tries to hit your ships by calling out coordinates. Neither you nor the other player can see the other's board so you must try to guess where they are. Each board in the physical game has two grids: the lower (horizontal) section for the player's ships and the upper part (vertical during play) for recording the player's guesses.

### Starting a New Game

Each player places the 3 ships somewhere on their board. The ships can only be placed vertically or horizontally. Diagonal placement is not allowed. No part of a ship may hang off the edge of the board. Ships may not overlap each other. No ships may be placed on another ship. Once the guessing begins, the players may not move the ships. The 3 ships are: Battleship (4), Cruiser (3) and Destroyer (2).

### Playing the Game

Player's take turns guessing by calling out the coordinates. The opponent responds with "hit" or "miss" as appropriate. Both players should mark their board with pegs: red for hit, white for miss. For example, if you call out F6 and your opponent does not have any ship located at F6, your opponent would respond with "miss". You record the miss F6 by placing a white peg on the lower part of your board at F6. Your opponent records the miss by placing. When all of the squares that one your ships occupies have been hit, the ship will be sunk. You should announce "hit and sunk". In the physical game, a red peg is placed on the top edge of the vertical board to indicate a sunk ship. As soon as all of one player's ships have been sunk, the game ends.

Your boss wants to know if it is possible to implement this game using the ninja framework. You will have two weeks to implement this game. Since this is just a proof of concept, we will not be developing an extensive UI at this time.

### Prerequisites

The complete requirements document can be found [here](https://cs361fall2018.github.io/docs/battleship-requirements.pdf).

To complete this assignment, each group needs to do the following:

1. Start the assignment from the GH classroom, at this [link](https://classroom.github.com/g/y6LTfIcq). This will initialize the repo with the starter code.

2. Setup the project in IntelliJ IDEA. You can find resources on how to do that [here](https://cs361fall2018.github.io/resources).

3. Turn the assignment into User Stories. From the assignment, each team should turn the assignment into a series of user stories. We will talk about them in class, but a good introduction to User Stories can be found [here](http://www.subcide.com/articles/how-to-write-meaningful-user-stories/). Once you create your user stories, create a page on your Github Project wiki called “User Stories”, where you will record all the user stories. You must have a minimum of 4 user stories.

4. Decide on your definition of done. Record it on a wiki page called "Definition of Done." You need to follow this definition everytime you finish a user story.

5. Create Tasks from User Stories. From the user stories, each team will create a series of features that need to be implemented in the code. These will be added to the Github issue tracking system. Each of these must be assigned to a specific team member, as well as have a time estimate given in the body of the issue. The issues will be tagged with feature.

6. Create a new milestone, called Sprint 1, with the deadline being the assignment due date. As a team, decide on what you are going to implement this sprint, by adding the milestone to each relevant issue.

7. Each team member will then create a branch for each feature they are going to implement. When the feature is completed, create a pull request (PR) to the master branch. Another team member has to review the changes. If they require any changes, implement the feedback and repeat the review cycle. Once the changes have been approved, merge the PR and close the issue. Follow the same process when you fix a bug. First, create an issue in the tracking system. Then, create a separate branch, and create a Pull Request (PR) when done, review and merge.

8. Tag the final version with sprint-1. Don’t forget to push the tags to GitHub, with git push --tags. This is the version we will grade!

9. (Optional) Complete this survey to give feedback regarding your team member's contribution to the sprint. Complete one survey for each team member you would like to give feedback for.

&nbsp;

# *Sprint 2*

## Introduction

For Sprint 2, you and your team are charged with creating a more usable version of the Battleship game.

After the first version (Sprint 1) was released, we conducted focus group testing and found that users were complaining about the game interface being unusable. As a team, you have been tasked with creating a more usable interface for the next version.

### Learning Goals for this Sprint
This assignment will help you learn the following:

• Work with a team to design a user interface

• Communiciate your design of a user interface

• Divide tasks among team members

• Use Github Issue Tracking, Pull Requests, and Branches correctly

### Tasks for this Sprint
For this sprint, each group needs to do the following:

1. **Prepare your team repo for Sprint 2.** You may use your code from Sprint 1 if it runs and is feature complete, or you may copy the code form the [cs361fall2018/sprint2](https://github.com/cs361fall2018/sprint2) repo into your team repo. We will be using the same cs361fall2018/project-X repository during Sprint 2, so if you choose to copy code from the [cs361fall2018/sprint2](https://github.com/cs361fall2018/sprint2) repository you will need to research how to overwrite your previous code.

2. **Decide on a target platform and layout for your application, as well as a description of the user you are targeting.** This will inform your design. Designing for a vertical iPad layout is very different than a 16:9 laptop screen. The input method also will inform design decisions. The type of user you are targeting will also influence things. You should record these design details in a Wiki page on your team repository.

3. **Create 3 different designs per team member on paper.** Each team member should come up with 3 different designs for the application. These should be hand drawn on paper. One drawing per sheet of paper. Each drawing should be labeled with the authors name. You will turn in these drawings as a part of this assignment, by scanning and adding them to the Wiki pages on your team repository. Remember, **you are each creating** 3 **different** designs.

4. **As a group choose the 3 best designs.** As a group, you should vote the three best designs that you like from all the paper prototypes; if your in a team of 4 that means choosing from 12 designs, a team of 3 can choose from 9 designs. You need to mention why the team chose these 3 designs. Put this information (which was voted in and why) in the wiki page.

5. **Merge the best ideas from top 3 designs into a single paper prototype.** Work together as a group to create a new paper drawing that incorporates the best ideas from the 3 top choices. For this last one, you will need to turn in three different versions of the same design. This one "best" design should be drawn three times, once in each of the following states:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• **Regular:** This is how it will look when the user is in the middle of playing a game.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• **Blank:** This is how it will look when the user starts a new game.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• **Error:** This is how it will look when the user tries to do an illegal ship placement or attack.

6. **As a group analyze the usability of your prototype.** On a separate Wiki page, describe your [Sprint 2](https://cs361fall2018.github.io/assignments/sprint2/) prototype according to Nielsen's 5 quality components of usability [link](https://www.nngroup.com/articles/usability-101-introduction-to-usability/). For each of the quality components, describe how your prototype addresses that component. If a component is not pertitent to your prototype, specify why that component is not applicable to your prototype.

7. **Implement the code for your paper prototype as new interface for your application.** This will include:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Divide work up so that each team member has at least one task:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Input tasks into Github Issue Tracker, and assign each team member at least one task.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Each team member should write implementation code in a feature branch.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Each team member should have at least one pull request.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Each team member should make a constructive comment on another team members' pull request.

8. Tag the final version with sprint-2. Don’t forget to push the tags to GitHub, with git push --tags. **This is the version we will grade!**

9. (Optional) Complete [this survey](https://goo.gl/forms/jzvDOPanWHOMLjBq1) to give feedback regarding your team member's contribution to the sprint. Complete one survey for each team member you would like to give feedback for.

&nbsp;

# *Sprint 3*

## Introduction

For Sprint 3, your group will import your group's code from Sprint 2. The goal of this stage is to expand the game and refine the quality of code for the Battleship game previously developed. The focus is on using Object-Oriented Design (specifically inheritance).

Users are enjoying version 2 of your Battleship game, and it's popularity has increased dramatically, but some users are beginning to complain that the game play is boring after playing a few games. Some of these users have requested more variety to the types of ships that can be played. The marketing department has run several user focus groups and come up with the following suggestions:

• A new concept has been introduced, the *"captains quarters."* If the captains quarters is hit, then the ship is immediately sunk. The captains quarters for battleships and destroyers (but not minesweepers!) is armored, so it takes 2 attacks on the same square for it to "hit" (i.e. the result of the first attack always counts as as "miss").

![Image not found: https://cs361fall2018.github.io/images/captains-quarters.png](https://cs361fall2018.github.io/images/captains-quarters.png)

• A new special weapon has been introduced: the *"sonar pulse."* The sonar pulse allows a player to reveal a portion of the map, as shown in the figure below. The sonar pulse merely reveals the status of the cell as being free (shown as gray) or occupied (shown as black). *It does not reveal the location of the ship's captain's quarters.* A player can use a total of two (2) sonar pulses in a game, only **after** successfully sinking the first enemy ship.

![Image not found: https://cs361fall2018.github.io/images/sonar-pulse.png](https://cs361fall2018.github.io/images/sonar-pulse.png)

The user created requirements document can be found [here](https://cs361fall2018.github.io/docs/battleships-phase2.pdf).

## Learning Objectives

The objective of this sprint is to implement the new features of the game.

This assignment will help you learn the following:

&nbsp;&nbsp;&nbsp;&nbsp;• Work with a team to design and implement changes to your model.

&nbsp;&nbsp;&nbsp;&nbsp;• Refactoring existing code to accommodate required code changes.

&nbsp;&nbsp;&nbsp;&nbsp;• Divide tasks among team members.

&nbsp;&nbsp;&nbsp;&nbsp;• Use GitHub Issue Tracking, Pull Requests, and Branches correctly.

&nbsp;&nbsp;&nbsp;&nbsp;• Using Continuous Integration (CI) to detect failed tests and build.

## Tasks

To complete this assignment, each team needs to do the following:

1. Prepare the Team's Repository. Since the UI design completed in Sprint 2 is different for each team, there is no provided course repo for Sprint 3. We will be using the same *cs361fall2018/project-X* repository during Sprint 3.

2. Create user stories for the new features. The Battleships game will be a much more interesting game to play. Create a new wiki page on your team's repo called “Sprint 3 User Story”, and make sure that your user story adheres to the format:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Card: (“As a [user], I want [function], so that [value]”)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Conversation: (Details that clarify the user story)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;• Confirmation: (Tests to show when the User Story is completed)

Your user stories will be evaluated on whether they follow [INVEST](https://xp123.com/articles/invest-in-good-stories-and-smart-tasks/).

3. From the user stories, each team will create a series of features that need to be implemented in the code. These will be added to the Github issue tracking system. Each of these must be assigned to a specific team member, as well as have a time estimate given in the body of the issue. The issues will be tagged with *feature*. Also, make sure that each issue is linked to the appropriate user story.

4. Create a new milestone, called *Sprint 3*, with the deadline being the assignment due date. As a team, decide on what you are going to implement this sprint, by adding the milestone to each relevant issue.

5. Resolve the feature backlog. If your team was not able to implement all of the features in Sprint 2, or you haven't added all of the [Battleship rules](https://cs361fall2018.github.io/docs/battleship-requirements.pdf) into the code, now is the time to bring your game up to speed. Also, if your master branch does not pass the CI tests, now it's time to fix it. This implementation process should still be organized into tasks and documented in the GitHub Issue Tracker, as well as adhering to the GitHub Flow.

6. Refactor to Object-Oriented Design. Using code smells, identify places in your code that require refactoring. Using OO principles such as Inheritance, Polymorphism a-d Encapsulation, refactor the Battlehip codebase (including the new features, if applicable), making sure your design respects the SOLID design principles. Be careful with your design, since the next sprint will include several new features that rely upon having a good OO foundation to build upon.

7. Implement the new features. Using the user stories as a guide, along with the newly refactored Object-Oriented code, implement the new features. Use the GitHub Issue Tracker, the issues you created, Pull Requests, and any other techniques provided in previous sprints, to manage your work and track your progress. Each team member needs to implement at least on issue.

8. All features must be thorughly tested, with the tests included in the same PR as the code they test. At the end of the sprint, the *models* package must have at least 80% test coverage (at the line level). You don't need to test the front-end code.

9. When merging the pull requests it is import to make sure that all the Travis CI tests pass. You cannot merge a PR if the checks fail. Also, if the CI on the master branch fails for any reason, fixing that failure becomes the first priority for your team.

10. Tweek the UI to correctly show the new features. You will need to make changes to the user interface, in order for the user to easily use (and understand) the new features of the game.

11. Tag the final version with *sprint-3.* Don’t forget to push the tags to GitHub, with git push *--tags.* **This is the version we will grade!**

12. (Optional) Complete [this survey](https://goo.gl/forms/jzvDOPanWHOMLjBq1) to give feedback regarding your team member's contribution to the sprint. Complete one survey for each team member you would like to give feedback for.

&nbsp;

## Built With

* [Ninja](http://www.ninjaframework.org/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/) - The Java testing framework used

## Authors

* **Billie Thompson** - *Original creator of "README-Template.md"* - [README Template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* **Caius Brindescu** - *Course Instructor (CS 361)* - [Original Instructions for Sprint 1](https://cs361fall2018.github.io/assignments/sprint1/), [Sprint 2](https://cs361fall2018.github.io/assignments/sprint2/), and [Sprint 3](https://cs361fall2018.github.io/assignments/sprint3/)

## Contributors

* **Cleevesd** - *Team 22* - [GitHub](https://github.com/Cleevesd)
* **mertdedekoy** - *Team 22* - [GitHub](https://github.com/mertdedekoy)
* **AHazy** - *Team 22* - [GitHub](https://github.com/AHazy)
* **AdamTogether** - *Team 22* - [GitHub](https://github.com/AdamTogether)

## Acknowledgments

* Teamwork makes the dream work.

