# BullDozer
### > A game that is all about moving boulders and thinking

This is a game where you control a tank called _Dozer_ and try to move the boulders to the specific locations where _targets_ are located in order to pass the level
Project done by a group of students called Afonso Osório(up202108700@fe.up.pt) ,HaoCHang(up202108730@fe.up.pt) and Ines(up202108674@edu.fe.up.pt).

### _Implemented Features_
Yet to be done

### Planned Features
- **Moving the Dozer** - The game receives input from user and moves the character in controll accordingly;
- **Moving the Boulders** -The dozer moves the Boulder by pushing it;
- **Activate Targets** - The targets receive information about the Boulder being pushed onto it and switches state accordingly;
- **Graphical interface aka GUI** - Allows user to interact before the game starts to select levels, start a game or exit the game;
- **Level selection** - As the name suggests, allows user to select pretended level to play.

### Design Problems

#### **Having multiple objects to instantiate**

- As we have many objects that we need to instantiate, it becomes difficult to maintain **Ridigity** of the game , having to make changes to all the components and functionalities everytime we add new type of objects.

**Solution** : **Factory Pattern**

- By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass so we can add new features like new types of objects(_ex: enemies or powerups_) without the need to worry about rigidity.

#### **The game needs a main "Control Unit" to check the state of itself and evolve throught time accordingly** 

- With so many components that needs to interact with each other, we need something that makes the interaction and to avoid _Dependency hell_ ( a lot of dependencies where a lot of modules depends on a lot of other modules) and we also need the states to control the current situation of the game and future events of the game.

**Solution** : **State Pattern**

- By using this pattern we can avoid what we stated before by integrating states to the game.

[Heres a link to show UML Class Diagram, click to see their interactions](.....)

#### You can find the classes in the following folders:

- [Folder where all the classes are listed] (https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/States/src/main/java/pt/up/fe/edu/hero)


 
