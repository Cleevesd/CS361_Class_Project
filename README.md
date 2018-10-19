# Battleship

This repository contains the code written by team 22 for Sprints 1 and 2 of the Battleship group project as assigned in CS 361 (Software Engineering I) at Oregon State University.

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

• **Regular:** This is how it will look when the user is in the middle of playing a game.

• **Blank:** This is how it will look when the user starts a new game.

• **Error:** This is how it will look when the user tries to do an illigal ship placement or attack.

6. **As a group analyze the usability of your prototype.** On a separate Wiki page, describe your [Sprint 2](https://cs361fall2018.github.io/assignments/sprint2/) prototype according to Nielsen's 5 quality components of usability [link](https://www.nngroup.com/articles/usability-101-introduction-to-usability/). For each of the quality components, describe how your prototype addresses that component. If a component is not pertitent to your prototype, specify why that component is not applicable to your prototype.

7. **Implement the code for your paper prototype as new interface for your application.** This will include:

Divide work up so that each team member has at least one task:

• Input tasks into Github Issue Tracker, and assign each team member at least one task.

• Each team member should write implementation code in a feature branch

• Each team member should have at least one pull request

• Each team member should make a constructive comment on another team members' pull request

8. Tag the final version with sprint-2. Don’t forget to push the tags to GitHub, with git push --tags. **This is the version we will grade!**

9. (Optional) Complete [this survey](https://goo.gl/forms/jzvDOPanWHOMLjBq1) to give feedback regarding your team member's contribution to the sprint. Complete one survey for each team member you would like to give feedback for.

## Built With

* [Ninja](http://www.ninjaframework.org/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/) - The Java testing framework used

## Authors

* **Billie Thompson** - *Original creator of "README-Template.md"* - [README Template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* **Caius Brindescu** - *Course Instructor (CS 361)* - [Original Instructions for Sprint 1](https://cs361fall2018.github.io/assignments/sprint1/), [Sprint 2](https://cs361fall2018.github.io/assignments/sprint2/)

## Contributors

* **Cleevesd** - *Team 22* - [GitHub](https://github.com/Cleevesd)
* **mertdedekoy** - *Team 22* - [GitHub](https://github.com/mertdedekoy)
* **AHazy** - *Team 22* - [GitHub](https://github.com/AHazy)
* **AdamTogether** - *Team 22* - [GitHub](https://github.com/AdamTogether)

## Acknowledgments

* Teamwork makes the dream work.

