## Game Description

The Bulldozer is a game in which the player controls a tank named dozer and to win, he needs to push the boulders towards the targets. It consists of 15 levels, which increase in difficulty, with more obstacles and more targets to put the boulders.
This project was developed by Afonso Castro Vaz Osório (up202108700@edu.fe.up.pt), Haochang Fu (up202108730@edu.fe.up.pt) and Inês Martin Soares (up202108852@edu.fe.pt) for LDTS 2022-23.

## Implemented Features

**Menus** - The Main Menu has three options: Level Select, Level Editor and Quit. The Level Select Menu has the Select option, as well as the Start and Quit. Level Editor goes directly to the editor itself.
**Buttons** - Functional and interactive buttons.
**Keyboard control** - The keyboard inputs are received through the respective events and interpreted according to the current game state.
**Player control** - The player may move with the keyboard control.
**Collisions detection** - Collisions between different objects are verified. (Ex: Player, Walls, Boulders).

## Planned Features

All the planned features were successfully implemented.

## Design

### General Structure
#### Problem in Context:
The structure of our project was one of our first concerns. As we had the idea of implementing the GUI as a graphical interface, some patterns were listed according to our needs. With so many components that needs to interact with each other, we need something that makes the interaction to avoid Dependency hell ( a lot of dependencies where a lot of modules depends on a lot of other modules).We also need the states to control the current situation of the game and future events of the game.
#### The Pattern:
We applied the **_State Pattern_** and **_MVC Architecture Pattern_**. By using these patterns we can avoid what we stated before by integrating states to the game. We can also avoid a chunk of if statements by using polymorphism to switch to the right state.
#### Implementation:
In terms of implementation , we have 6 main classes, model (store data), controller (logic), audio (music), viewer (visual effects), gui (graphical interface) and state. They all are associated in this way:
(...)
The different states have implemented the **_MVC Architecture Pattern_**.
#### Consequences:
All the states that symbolize the menus are made more explicit in the code, and thus do not depend on flags. Thus, it also facilitates the addition of new features in the course of development.

### Observers
#### Problem in Context:
Our project is controlled by the keyboard. The required input is mainly to select the option from the menus and after that it is used to move the dozer. In order for the game not to request input when necessary, thus avoiding unnecessary calls, we have implemented observers that are responsible for receiving input and redirecting it so as not to overload the game.
For example, checking every cobblestone/target combination after every move is inefficient. Since there is no need to check for boulder/target collision on moves where a given boulder does not move, we must find a way to check this only when said boulder moves.
#### The Pattern:
By implementing the **_Observer Pattern_** with target controllers as observers and boulder controllers as subjects, we can avoid unnecessary checks. Only when a boulder moves, its controller will notify the target controllers, which will then determine if the boulder has stepped into a target, out of a target, both, or neither. This result could then be used to increment/decrement an integer variable that determines how many boulders are in targets. When this is equal to the number of targets, the level is completed.
#### Implementation:
The BoulderController notifies the TargetController (which implements the BoulderObserver interface) that it has to do this check.
<p align="center" justify="center">
  <img src="Images/image.png"/>
</p>
<p align="center">
  <b><i>Fig 2. Observers screenshot </i></b>
</p>
In target controller, we have a private field that tells the number of boulders on the target and the boulder controller has the notify observer that at each movement or step of the bolder makes the target (observer) check if the bolder is in the target position to all targets. If so, it increments the number of bouldersInTargets.
#### Consequences:
This strategy made de code cleaner and easier to read, also respects the single responsibility principle (the observers only activate on their respective game state, when receiving an input warning).

### Field builder
#### Problem in Context:
The field has various elements in its composition which are the Walls (two types), the boulders, the targets and the dozer (player). Since we have different arenas depending on the level we are playing, instead having a builder to each level, we have a loader
Checking collision with every wall is inefficient. In this game's levels, the areas of the screen that aren't relevant to the level are filled with walls, and since nothing will ever touch them, checking for collision with them is not necessary.
#### The Pattern:
The design pattern used is **_Decorator Pattern_**. It allows adding a behavior to an existing object at runtime, that is, it dynamically adds additional responsibilities to an object.
#### Implementation:
By creating an "ImportantWall" class that is a subclass of Wall and is functionally identical, we can differentiate between walls that are just decoration (notably, walls behind walls) and walls that matter for collision. Thus, the level saves two lists of walls.
<p align="center" justify="center">
<img src="Images/Captura de ecrã de 2022-12-18 19-06-19.png"/>
</p>
<p align="center">
  <b><i>Fig 3. Field Builder and loader </i></b>
</p>
<p align="center" justify="center">
<img src="Images/Captura de ecrã de 2022-12-18 19-06-26.png"/>
</p>
<p align="center">
  <b><i>Fig 4. Field Builder and loader </i></b>
</p>

#### Consequences:
The biggest advantage of the used pattern is that it can enhance the extensibility of the object, because changes are made by coding new classes. It simplifies the coding by allowing you to develop a series of functionality from targeted classes instead of coding all of the behavior into the object.

### Various objects to instantiate
#### Problem in Context:
As we have many objects that we need to instantiate, it becomes difficult to maintain Rigidity of the game , having to make changes to all the components and functionalities everytime we add new type of objects.
#### The Pattern:
**_Factory Pattern_** was the chosen one. By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass, so we can add new features like new types of objects(ex: enemies or powerups) without the need to worry about rigidity.
#### Implementation:
Products were created (abstract and concrete) that execute the decision made in the factory. At runtime we don't know who will be called, instead of having if's and else's in the client, we have all the decision logic in the factory.
<p align="center" justify="center">
<img src="Images/Captura de ecrã de 2022-12-18 19-06-19.png"/>
</p>
<p align="center">
  <b><i>Fig 5. Factory pattern</i></b>
</p>
<p align="center" justify="center">
<img src="Images/Captura de ecrã de 2022-12-18 19-06-19.png"/>
</p>
<p align="center">
  <b><i>Fig 6. Factory pattern </i></b>
</p>

#### Consequences:
The pattern gives us a way to turn off the implementation of a Product. Adding or changing Products will not affect the Creator as they are not tightly linked. It encapsulates the code that creates objects and avoids duplication, plus we have a single place to maintain it.

### GUI
#### Problem in Context:
(The lanterna library contains various functions that aren't useful to our program, Interface Segregation Principle violation, and lacks some other functions that our interface needs. Also, if using the raw library, our game (high level module) would be directly depending on a low level module. This is a violation of the Dependency Inversion Principle (DIP). A need to implement an interface that solves these problems was born.)
#### The Pattern:
**_Facade Pattern_** was the chosen method. The intent of this pattern is to encapsulate complicated logic in a high-level interface that makes accessing a subsystem very simple and easy to use.
#### Implementation:
(...)
#### Consequences:
(...)

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