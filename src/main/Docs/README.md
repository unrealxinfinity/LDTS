## Game Description

The Bulldozer is a game in which the player controls a tank named dozer and to win, he needs to push the boulders towards the targets. It consists of 15 levels, which increase in difficulty, with more obstacles and more targets to put the boulders.
This project was developed by Afonso Castro Vaz Osório (up202108700@edu.fe.up.pt), Haochang Fu (up202108730@edu.fe.up.pt) and Inês Martin Soares (up202108852@edu.fe.pt) for LDTS 2022-23.

## Implemented Features

**Menus** - 
**Buttons** - Functional and interactive buttons.
**Keyboard control** - The keyboard inputs are received through the respective events and interpreted according to the current game state.
**Player control** - The player may move with the keyboard control.
**Collisions detection** - Collisions between different objects are verified. (Ex: Player, Walls, Boulders).
**Animation** - 

## Planned Features

All the planned features were successfully implemented.

## Design

### General Structure
#### Problem in Context:
The structure of our project was one of our first concerns. As we had the idea of implementing the GUI as a graphical interface, some patterns were listed according to our needs. With so many components that needs to interact with each other, we need something that makes the interaction to avoid Dependency hell ( a lot of dependencies where a lot of modules depends on a lot of other modules).We also need the states to control the current situation of the game and future events of the game.
#### The Pattern:
We applied the **_State Pattern_**. By using this pattern we can avoid what we stated before by integrating states to the game. We can also avoid a chunk of if statements by using polymorphism to switch to the right state.
#### Implementation:

#### Consequences:


### Observers and listeners
#### Problem in Context:
Checking every boulder/target combination after every move is inefficient. Since there is no need to check boulder/target collision in moves where a given boulder does not move, we should find a way to only check for it when said boulder moves.
#### The Pattern:
By implementing the **_Observer Pattern_** with target controllers as observers and boulder controllers as subjects, we can avoid unnecessary checks. Only when a boulder moves, its controller will notify the target controllers, which will then determine if the boulder has stepped into a target, out of a target, both, or neither. This result could then be used to increment/decrement an integer variable that determines how many boulders are in targets. When this is equal to the number of targets, the level is completed.
#### Implementation:

#### Consequences:


### Field builder
#### Problem in Context:
Checking collision with every wall is inefficient. In this game's levels, the areas of the screen that aren't relevant to the level are filled with walls, and since nothing will ever touch them, checking for collision with them is not necessary.
#### The Pattern:

#### Implementation:
By creating an "ImportantWall" class that is a subclass of Wall and is functionally identical, we can differentiate between walls that are just decoration (notably, walls behind walls) and walls that matter for collision. Thus, the level saves two lists of walls.
#### Consequences:


### Various objects to instantiate
#### Problem in Context:
As we have many objects that we need to instantiate, it becomes difficult to maintain Ridigity of the game , having to make changes to all the components and functionalities everytime we add new type of objects.
#### The Pattern:
**_Factory Pattern_** was the chosen one. By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass so we can add new features like new types of objects(ex: enemies or powerups) without the need to worry about rigidity.
#### Implementation:

#### Consequences:


### GUI
#### Problem in Context:

#### The Pattern:

#### Implementation:

#### Consequences:


## Known Code Smells And Refactoring Suggestions
#### **Large Class**
#### **Data Class**
#### **Alternative classes with different interfaces and Lazy Classes**
#### **Refused bequest**
#### **Feature envy and message chains**

## Testing

### Screenshot of coverage report
### Link to mutation testing report
## Self-evaluation