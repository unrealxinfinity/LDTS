## Game Description

The Bulldozer is a game in which the player controls a tank named dozer and to win, he needs to push the boulders towards the targets. It consists of 15 levels, which increase in difficulty, with more obstacles and more targets to put the boulders.
This project was developed by Afonso Castro Vaz Osório (up202108700@edu.fe.up.pt), Haochang Fu (up202108730@edu.fe.up.pt) and Inês Martin Soares (up202108852@edu.fe.pt) for LDTS 2022-23.

## Implemented Features

- **Menus** - The Main Menu has three options: Level Select, Level Editor and Quit. The Level Select Menu has the Select option, as well as the Start and Quit. Level Editor goes directly to the editor itself.
- **Buttons** - Functional and interactive buttons.
- **Keyboard control** - The keyboard inputs are received through the respective events and interpreted according to the current game state.
- **Player control** - The player may move with the keyboard control.
- **Collisions detection** - Collisions between different objects are verified. (Ex: Player, Walls, Boulders).

## Planned Features

**Saving Levels** - Allow players to save levels from the editor and then select them from the Level Select
**Editor Level Cleaning** - After a level is finished in the editor, check which walls are important for collision and which are not

## Design

### General Structure
#### Problem in Context:
The structure of our project was one of our first concerns. As we had the idea of implementing the GUI as a graphical interface, some patterns were listed according to our needs. With so many components that needs to interact with each other, we need something that makes the interaction to avoid Dependency hell ( a lot of dependencies where a lot of modules depends on a lot of other modules).We also need the states to control the current situation of the game and future events of the game.
#### The Pattern:
We applied the **_State Pattern_** and **_MVC Architecture Pattern_**. By using these patterns we can avoid what we stated before by integrating states to the game. We can also avoid a chunk of if statements by using polymorphism to switch to the right state.
#### Implementation:
In terms of implementation , we have 6 main packages, model (store data), controller (logic), audio (music), viewer (visual effects), gui (graphical interface) and state. They all are associated in this way:
<p align="center" justify="center">
  <img src="Images/StatePattern.png"/>
</p>
<p align="center">
  <b><i>Fig 1. State pattern screenshot </i></b>
</p>

The different states have implemented the **_MVC Architecture Pattern_**.

#### Consequences:
All the states that symbolize the menus are made more explicit in the code, and thus do not depend on flags. Thus, it also facilitates the addition of new features in the course of development.

### Observers
#### Problem in Context:
During gameplay, there are things that don't need to be checked every frame.
For example, checking every boulder/target combination after every move to determine how many boulders are in targets is inefficient. Since there is no need to check for boulder/target collision on moves where a given boulder does not move, we must find a way to check this only when said boulder moves.
#### The Pattern:
By implementing the **_Observer Pattern_** with target controllers as observers and boulder controllers as subjects, we can avoid unnecessary checks. Only when a boulder moves, its controller will notify the target controllers, which will then determine how many boulders are currently in targets. This result is then used to update an integer variable. When this is equal to the number of targets, the level is completed.
#### Implementation:
The BoulderController notifies the TargetController (which implements the BoulderObserver interface) that it has to do this check.
<p align="center" justify="center">
  <img src="Images/Observer.png"/>
</p>
<p align="center">
  <b><i>Fig 2. Observers screenshot </i></b>
</p>
In target controller, we have a private field that tells the number of boulders on the target and the boulder controller has the notify observer that at each movement or step of the bolder makes the target (observer) check if the bolder is in the target position to all targets. If so, it increments the number of bouldersInTargets.

#### Consequences:

This strategy made de code cleaner and easier to read, also respects the single responsibility principle (the observers only activate on their respective game state, when receiving an input warning).

#### You can find the related classes in the following links:
- [Click here for the TargetController](https://github.com/FEUP-LDTS-2022/project-l07gr08/blob/README/src/main/java/pt/up/fe/edu/dozer/controller/game/TargetController.java)

- [Click here for the BoulderController](https://github.com/FEUP-LDTS-2022/project-l07gr08/blob/README/src/main/java/pt/up/fe/edu/dozer/controller/game/BoulderController.java)

- [Click here for the BoulderObserver](https://github.com/FEUP-LDTS-2022/project-l07gr08/blob/README/src/main/java/pt/up/fe/edu/dozer/controller/game/BoulderObserver.java)

### Field builder
#### Problem in Context:
In this game, the areas that the player can't reach are filled with walls. However, it is impossible to touch most of these walls.
Checking collision with every wall is inefficient, since only a handful of them will ever be actually important for a collision check.
#### The Solution:
We can differentiate two separate types of wall.
#### Implementation:
By creating an "ImportantWall" class that is a subclass of Wall and is functionally identical, we can differentiate between walls that are just decoration (notably, walls behind walls) and walls that matter for collision. Thus, the level saves two lists of walls.

<p align="center" justify="center">
<img src="Images/Captura de ecrã de 2022-12-23 16-01-50.png"/>
</p>
<p align="center">
  <b><i>Fig 3. Field Builder and loader </i></b>
</p>

#### Consequences:
Since ImportantWall extends Wall, every method that works on Walls will work on ImportantWalls, and due to polymorphism, a Wall container can hold ImportantWalls. If we ever need a container that has both Walls and ImportantWalls, we can differentiate between them with down-casting.

### Various objects to instantiate
#### Problem in Context:
As we have many objects that we need to instantiate, it becomes difficult to maintain Rigidity of the game , having to make changes to all the components and functionalities everytime we add new type of objects.
#### The Pattern:
**_Factory Pattern_** was the chosen one. By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass, so we can add new features like new types of objects(ex: enemies or obstacles) without the need to worry about rigidity.
#### Implementation:
Products were created (abstract and concrete) that execute the decision made in the factory. At runtime we don't know who will be called, instead of having if's and else's in the client, we have all the decision logic in the factory.
<p align="center" justify="center">
<img src="Images/Factory1.png"/>
</p>
<p align="center">
  <b><i>Fig 4. Factory pattern : Viewer model</i></b>
</p>
<p align="center" justify="center">
<img src="Images/Factory.png"/>
</p>
<p align="center">
  <b><i>Fig 5. Factory pattern : Controller model </i></b>
</p>

#### Consequences:
The pattern gives us a way to turn off the implementation of a Product. Adding or changing Products will not affect the Creator as they are not tightly linked. It encapsulates the code that creates objects and avoids duplication, plus we have a single place to maintain it.

#### Related classes in:
- [Click here for the viewer classes](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer/viewer)

- [Click here for the controller classes](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer/controller)

### Editor View
#### Problem in Context:
When drawing the screen during level editing, we're drawing everything as if it were a regular level, but with the added placer icon.
Therefore, we needed a viewer that could do everything that the GameViewer could do with a small addition.
#### The Pattern:
With the **_Decorator Pattern_**, we can take an existing class and extend its funcionality without over complicating the inheritance tree by wrapping an object of the initial class around a decorator that adds functionality.
#### Implementation:
The EditorViewer class has a field of type GameViewer. When calling draw() on an EditorViewer, it calls the draw() method of its own GameViewer and then draws the placer symbol on top.
<p align="center" justify="center">
<img src="Images/EditorViewer.png"/>
</p>
<p align="center">
  <b><i>Fig 6. Decorator Pattern : EditorViewer </i></b>
</p>

#### Consequences:
The EditorViewer holds all the functionality of the GameViewer without subclassing it, and if at some point we change how the GameViewer draws the game, those changes will be reflected in the EditorViewer.

#### The related classes in :
- [Click here for the Editor Viewer](https://github.com/FEUP-LDTS-2022/project-l07gr08/blob/README/src/main/java/pt/up/fe/edu/dozer/viewer/game/EditorViewer.java)

### GUI
#### Problem in Context:
The Lanterna library has a vast list of unnecessary functions for our project, which many violate the Interface Segregation Principle.
Another point to bear in mind is, when using the raw library, the game, which is a high-level module, started to depend on a low-level module, which violates basic OOP principles.
#### The Pattern:
**_Facade Pattern_** was the chosen method. The intent of this pattern is to encapsulate complicated logic in a high-level interface that makes accessing a subsystem very simple and easy to use.
#### Implementation:
<p align="center" justify="center">
<img src="Images/GuiUml.png"/>
</p>
<p align="center">
  <b><i>Fig 7. GUI implementation</i></b>
</p>

#### Consequences:
The Facade design pattern provides a unified interface to a set of interfaces in a subsystem. The Facade Pattern allows us to disconnect the client implementation from any subsystem. Thus, if we wanted to add new functionalities in the subsystem, it would only be necessary to change the Facade instead of changing several points of the system.
#### Find the related classes in:
- [Click here for GUI and LanternaGUI](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer/gui)


### All the related files:
- [Heres a link to the directory of all existing classes](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer)

- [Heres a link to the whole UML diagram](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Diagrama_sem_nome21111162.drawio#R7V1pU9vKtv01VHFelSm1Zn%2FEQAg3JOGEIcn5QglbgBPZcjwAzq9%2FmlpD95Ysyd0tJ%2B7UrXOxMUJor97D2tOBdjJ5O587s%2BeP%2Fsj1DlRl9HagnR6oqoo0O%2Fi%2F8J118k5f68fvPM3Ho%2Fg9lL1xPf7tJm8qybur8chdxO8lby1931uOZ4vCTw%2F96dQdLgvvOfO5%2F1r82UffK%2F7WmfPkUm9cDx2PfvfreLR8jt%2B1VSt7%2F707fnrGvxmZyd83cfCHk1%2B9eHZG%2FmvuLe3sQDuZ%2B%2F4y%2FmryduJ64dPDz%2BXrxfqrd%2FnTPP%2Ffv4tfzu3gw82nu158sXdNfiT9E%2BbudNn60v6ljm5uzqfnJ9bw%2FG5lDsbarx5KHsOL462SBzbwV97InSd%2F83KNH%2BTidTzxnGnwavDoT5fXyXdQ8Hr4PPZGl87aX4W3t1g6w5%2F41eDZn49%2FB593vOTDwbfnywQnqhlebex5J77nz4M3pn70C7Ifug4vFnxDCd6du4vgx67wY0DEWx%2Bdt8IHL53FMnlj6HueM1uMH9Jbnjjzp%2FF04C%2BX%2FiT5UM2nnEjjxZ0v3bccxpKnfu76E3c5XwcfSb6rGQmAkiNk28nr1wyPyEree85h0Uzec5Ij8JReOhNz8EUi6QZSx4ciJ%2FUrfzFejv1pE7E73vhpGnw9DB5YABhtED6TcXD0jpNvLP3Z1uj4q6Cga0UooFTGeSyYABZUhRsYVAoMveiOAyWnHYc%2FHTwDEhXB37uMpDX3f7rE4QXOM0aK5z4uS3GymDnD8fTpMvrMqZ698yV5DOFbfvCzj16khp%2FHo5E7DWXsL52lEws0lN7MD245ekzGIPhf8OBOlCPjwAhu%2FCR4jbLXwf%2FCj8%2BXJ%2F40%2BFuccSRuN0DLqxsiBgBC9YHajARsR82actd5id0Gxb6WYucpdqPucecmdo0SOyVlbxxJL5YydppQKxFPAmF5bibTm1Dkpz1EyV2j5a4BMvacB9dLbZV2Oo8%2FS8h%2Bk3iL%2BB1Pn935mKvYLb2e2JG5vdiv7t%2FMH1bvdfHBuPo9RCfvV2%2FnPQSJfRC65%2B7y2%2BE%2F8tDzlH6%2F5qHXGJj4S3V6%2FHh98ct611vNn73PP9A16uGQDZL%2Bdyl9vtJHak3x67zEjy9Mid%2F9tXK8xSHWpxkQBkGE7jp0MCDBsC0Y6vr7LMAA3rNGg%2BEmCIBc%2BuTLgL9plIeFrGiAlHVAyobKS8q0d%2F%2FVCX9MyphNJJ8SNpUytznJHPTxAC1%2Foh4cD67d%2Bct46IYvBsHzML1Qgg%2Fz4KunWNcl7%2BA3Lp2Qzpk6%2BBvBzTxkHyYA9LycYAxwe9SKWXzWPQTGUQh62AzUKPiwdZo1OfPcSfCn3124ryF9Gj%2B85M3S51dyADV5ADeiguDSAi%2B3pm3lBwqjxNEazZ3XQzeGwiLwQ85vLzJn684PLrqvnlYe9CqNleqzt5NsG3jLBs24nzsTF%2BsKaZV5KQUDMMuaCogeKQxkP7QHn06Du528fL9RjbP1ze%2BJ36MtRagOEsNwGKiC4LgkuiFQCDfBK2xJxnlLcoNtiNQcJTFa9cnbRlmAiFEZkHUgYMwqwCyKiAl%2B9%2FHleLGkUCJh1DGMsJ%2FfGYzo1D4Ao5vxxP1HCp%2B18C1bnPDBWzZph%2BPU%2F%2B3OsTKQHgcbj8Miijt6kOiRIZIHMGmHIxeFRCiQIUhzJWCycCRAJPBTAnQ8GrKAUgew1QF9nAeo1AG8%2BF9Y8rQPmdMBMRUsVUBjFWDUhkaFCoCAwE8FlBZ4Si3Amnvok64AVOgJEpLc1ACdBsqpgbTSV2qCxprAqg2PphQkN01AF4HEiV%2BpCBgrAtWqExMI9QfwHcGK4Nx3pD%2FQRgv0a2NjV%2FwBi%2BYFKCm709Fx2HkTvHrw%2FPA4DoK3kiOMzPjlu3H4i6MHn8sBz%2F3VdOSOkm8ET2%2B%2B%2Fha%2BONJtG7%2FxPfzskWpY%2BI3Tt%2FznT9f5V1fufBz85WF7QYtDu4wrW8ofB87YuaNCFxEtP0htz13PWY5f8j8ISyy53JUfFdilegIpdlFPaGSR%2F8JfzYdu8nOZ5OlLGWSK3CCbR%2BJHQV0qELSzzn0sOUQkzNInsQXyIFqiHHnYMpRhqxkQmsgXJGvYiNxWFVYSV0gjQ12qROLM5FmjkvxvlyfSdHRkWZZtm6qtWyp%2BBH%2BDeHUpXuq4phFdU3luPvi8xQmVpogSZ%2FyUqhypFmaYo9z75OFDZDluXbn3SU5AuNwhIrC93N238TJ26Izk1ffcdzJHLnyx5oQVa6ewghDperVWEogsKhWPFogv%2BrPR0t8ttGg6iRarLVqoS4lGi017gB%2Fd6SoIqpdz3%2FMkp7QJvfU5pR4d4OFy501TBLhxSn24prG5%2BGWx80bxU26EyA4TWPo0nRxKP3jYS1eee2azQ6jWB4UWvApVFmFTzr6VGN9BwUkITEHYTRoXNB%2F%2BE3PIFSXOkkmGT9M2TDKIA25MMlIgKjnBQWYEMBbyZiGuRD0OnolT2hgjAbIlQID6U9EAgf1DaSLYViDWMhEKZCJ4dbukvRSgiRinJqJYmR5iQ6qDeuogO1tbGQwIFTjk54AKiINsYDAkQLgCRKeLFGCA8DMYdLEaJWcuyWlLPygkpxVbcHL6dfy2%2FE9d%2B3ff727uj%2F%2Bnvju9mwdB3kFj7opVcjqlF1KrQk6nqctTUVdKvRBBNFWaZt%2FnVJZOpTTaytPciAzu8oTGGW3BUmeKwMirAWWDCmh%2F2nXt5v2699%2BpPvvxcHX59On63uilw2R2BDCbxVybqFYpD7UmYsqLU8qy5iRPhn9TBsH4mmwBiZoVVQlOrvZ3KwWi24SM%2Bi1xtVml8VZESJWGxSYb4FDbmhf6SsLl2axIgpn%2FqSgW4X%2FqfbH%2Bp4o%2B3Pmjl%2FX69dLQXz%2Fd%2B%2F8%2BrlpZJFb%2BZ48smNHbl1KhI8vop%2F9wRUQ2NkY76ltigaayVRxt8uxlXg9i6vVsNk4AGBczf23oX04vZ1en%2Fpdz86bvrjMjuyN6r0c6Na1r%2FXqkp5VGTKLgCAyzeucMl370OK%2BcZTgOjMInz8lf6aEHxiX2AZlyG50J7Ee4mIT0jDNdyoF6TGSNv0u4heCWBF4ZblD2wJaEpHleypyNzA1912QOTHBL0pT1ZS7XYjTFgUqcfYuGgQqWNinccECXzYbbEZJh%2BMdyUHYeBNUnaasEFSR1FvkH%2BJZpXjEUOl4PJaXOQ%2BpQmQIvqYOBhU3nqkOpe%2B6L631aTQ7yw%2FHDebpK9E7wMSUNciQcmMEB6JQWrARq5CDlghTWYk8rRjbJnUFVI6wFIAo%2FbJBfuMvYABzK3ni%2BRz%2Bl07szBRAtl4AgOuYSA7wxoAnU%2FzAGyjYlBRi4THwCCQPeMDBrmgN%2BbkDZ5KQABsncJIkC3igABvqLRYEB1bEmKAjE540XgZcV8sCLw2HhZYqM%2FGzmmDDG05ejzRsHqoYyZkWChx140l163YEHiiSwNxFhRkJlN6CC81%2FdQaXC2sRD2haHyf%2BDcMEbvCRgBDGVXbMU4BivEDDjBfZO5FY%2FcXjod%2B2ugsO1YjyEpoQEg0QCJySkQ%2F67Q0JZ%2FDpexFZCKgaBcNC7dkLBMV7J6t%2FYCc1gUOaASlwwxwXW193homxV3VPmccLIKPqaEhvMsdHv3LksWx7wlHJfZeDA35bw4AUPXe3a1%2BxXqA6CFJO2pQOAALtxBQOkQn9EFZQ5XMAVlRIE24LA6trxhJri8GbpaPUcIXJZUBk9BkYzQXAvQjbAVAe0AlhKz6%2BWHmiXCyutFsO5Gxw0rBGu45dJqZVUDFWKITtkW1VZQjjgqBhqDA6XFVbMBQ8VWkKCZ1BhVXLLcKFluIHk5NmZO8NljrUOzkr6Zvh18KBWc5lw56wcgHYrbsrB%2F3rjfX%2F%2F9lt7nny7enfi2d9HZ5Dr2Au8xuvlPDy544l7uAz%2BE2DA86dPGRzi7%2B8tIPIOk8IPHXb3pqMs9Ax8Bmfpxq7D4Y07n4ynjifVBWd1AVZs8kIEOCIJGkUa4sHzndH1r1XwN%2BdizeOvNxga74ITEzyvx%2FHTau5E9kb6m5wwAlV0ghhh4HiAGClLlcc6AyPiMPYy1ZOk3Sd4hFkJRRVyknF1%2BPsSQuwhZNY0PCxCVxBCdOCaElqfAvEeD0Mg4MGFxyc3F58%2FSRywD10VgQ4IrErKdEkYxBQKLcJHQOXZpRvCBxda124IOAgK4yKmvCUwOgCGUdf34AaMsmqcEBg3gYDr4EJWcnKEiA2kU8VSHmCFToiPK88Z1tMcEiGCKBAN2LojGC4gBRKpk5Aek2DZIbBonfurZdV%2FQ891ZAMbd%2BuimZ17pqWMqecvXIkA7giwO3dBy6px5u7j3F3InnbuGNCRQB8Tvmlglt2Z506CPzXezzVYjcO6TUr4crRd23GG9NBzqAIH2q9BjT1mCIMKIjOkr4rr26J3xnJ9Ww2loDYPPOuu4%2BGnE8q4CVymmaIhsg%2B5tyQcmMMBqs4RDIeKTqG45yPd3BXhIXlPAoIPIKBBaIIBUVHnn%2FR5FBFxEHYS5t6XqGCOCnBOmlhYaPRYC6wEkgax0t4OWePN0MO0yPUnOjAuB9wInpZkcAAHPcgirPGc%2BCPXS5XEjVQMGxRDesa2cSdB2XPUCzW2csmybuaCBxxHUPDcjrwO1URoYUIiYRgWh1G%2Fz0mxcndPNUDe7qn8UAEUcgtWB5jtgLzHj6E5yHmNH2PzsMl92BeM8IMFUMEtGBZmGRsdKoxQUUg2un2KsjkgoApu0Yqi2XZWZkvX%2Bn3tIL%2Ft8whZ6Rtilq5V29ONe64SAQc3nuYksPY3aKEy2svWJ7c36pgmaL6Xjdz3Z9Tc95ddCn%2FQf3xcuNvuxioRB9tls83QsiO7ziiha6bSTujUlQy7nsyZydOA%2FBIpz5byRIq26VLcBQrFH%2B0FmlmIonUwNtiGvwMIOnkc2wNBt%2FpH%2Fdw%2FWzAsVHnON2vb%2BuLVWyru5ju8yc2v%2BDex2uENV8nQXmjgmgdRhufVL4vQJJHdplSC8gOhIlsEHBMmRDbc8AXPKpA8dhlDUX2otqqKgCTPrVpOreEeShabtdih6gdI7NyOe0WqG5OVkqkUqAegQSS89ICKPtz5o5f1%2BvXS0F8%2F3fv%2FPq6g5b8pIq5DzzD4Mj9aYDUa%2Bx%2BdqfMkyx7YowEaPCLWKpShYbF0Z4cfg0dx7kzc4FHFneBZsusgHEUR5rv2WElsSHkxwgg4i0QoSDTadQi886Urowee0YNOhovQ%2FFMEVdsjPISbPRJodSGjh8Z2Ij1PWxXBCO21wYMIZPTQPnpoLnaoBIbTZMOr%2Bzfzh9V7XXwwrn4P0cn71ds53JKrHSQDsTGJFIUQeU5JBhGc1QFUBAPhgkW%2FDQwMqKQ%2BAUaxuQIXTEtMcMYEVCMrFBNILd31RsQSuao57DMUp17KApnyAhlGcEHAPifBLkVFl5aso%2BtGiSBgrZNgVECWhRAzs6KpZqFbvsIJtooNC5zwhdNGWrVPP31G5U09MpqkZgrWzZj2VO3I1JX0n6ZaxJUt%2Fci2sw8Q97qx9il9Okbhuka%2FeB0mpVEgBoHCi4%2FudFXS%2ByU7ydumR6nUuwnkR8GiTRwYMVc%2FYHEFruIFyv73mv%2BsaYaM%2BsjooOcHvmVow3XsfTxg7%2BOdM1z60V1fOctw%2B0v6iXnmoMTvBPfwUOq0FE3S6%2FN46V4Hog2%2F%2Bzp3ZkX7xessWog4igYw1AFBrDOL0bSW%2Fe%2FPj7PHW%2F%2F%2B%2B%2B23%2FtmHM%2BtyAU7AJkSQsNCkAHb%2FcRt1KkNSF53141Zuf370LVc3Tf9tsvwx%2BHLy%2BVeP9rtCm1fX2kmKn0Wnqw2AwAb1HgPGD0QBTfiF%2FH5YEDp24Q3teENFctzktPkiUCrP2lYGEQYGJ1zQvQIhLoarefiXnk3jm43BETvx0YcUiQTWSIBSAkKRALW5ETKWiSDGQgdGpoBC52UVIG84DIqmwWGODr8ctcf31EPtrUJPPYK88RACs7n7MvZXiwwGEgAcAAC2s4pFQEXdYCx8l3QD%2FpE7zHhCAtg%2FJBgSZWTZeHHteu5w6Y5KQTHwfc91phIVrFFh0nO0xKKiNA0c7hRaTR7c%2BVkcTuacBpwLkEhgiASoIkAsEqAO8wQJJ7nQ8VDuu%2BTNH6CuHUigcjRrO0iMRaAdpE7giwO9azcS0wV5HISc4WAcksaPIREfvByEyIiybPXoZzlosRX9TE7y7kMV5iYAD81ggI%2Fp3f3wh21%2BPkE31vDdUnv89eMBWEjoRJ0nGfnsTlcTqRiqFUN6yrZimEHJc1MMNQp%2FJLHIWuzAGhdQ7NyGLCp0FSC5e1Qyiwcbe80YwcGqqf%2F5DU1T6AxkuoJUQkEgFPo1DQJHKNCppmgbXFQ%2FKMEgEgzpfpUO0UAnoUI0JHP5JRyEwkEHqGfBcKD5BLn2qwsoWADfLBgKdOSQbaeWYBAIhrTgp0Mw0ASCXAgpHgh16QSOQKArFsDdsHHBIv1fCRCuAIHq2HgBBFw3bdOKIl1dL3WFSChA1W1ioUBzUOnacQkFgVDQgCVggqEA%2B5JXnjOUzqRgMGgCnUk4Zw11qBMy57HAwVALCxzQEUKCFzhUpvBrtzeHqyj05EClKcYKx3DLFmfbLP6udIBW0w5nm5j4rfWJC4ld3wALA%2BjXCk5qk54t2aG8sU2P6M%2BCJrDBm66xIWOvk8oGumY1l5fui%2BvFLwoLTGXBZQ07pbHozeK2uBS%2B5bLNUwQkzkbjpT%2BXkOAPCZE7r%2BFbLuvYySDx72os1YMALIhcdw3fMl2PnbMQ0llgVk3Xr%2BEswMseePX0g5trIzUwHc6jWSZJGfbpxVOkDRLqUwa59RXCTm%2BCgKuGy3r5Ri6MCokHnniouyKCHx6g0vxwmOei2KZx4x9mVbhxo4bEBl9sQM6DWGzQWRKqbSPyJqTsWcseAWS4YOE34z%2F%2Fyk1zJtEj0ZpU7CsbLsRubyAszGaDNTduk3wbL2O2Wtf7yet4n6SC9OR1xlWHL9a5F9sx1fEjrzps%2Bm5hiBiuprcdvbkRjLwxBJGPe6YQNp7j1grB4CbMxYWmerdv6%2BXx%2Ffrd%2B2%2Bzn4uX%2Bx7t9R0HgnAGq3FYUEvJVXbeMeQKsum7WPhAszZs7LFHto21B%2FFQw9jL%2FqsUB5VHijkXwKD9CrxhyCcIvfvh3HWWbqQPDqP%2FJlM7oq8pWOyvq88GBZpAR38x89eG%2FuX0cnZ16n85N2%2F67rpnVux5%2BXoR3BTeErnPPfkbql7YQEHkekgYClAyMYHC%2B%2BgGJBYEYUHkckgYC1AWUUvNA26wiNAQvZB44IkHoYsgYUBAo50yQIRNugsMiPyM4PAbe7%2B0RQxGgKFwgjEC5RwzjATS9MaLwEeXYOkeLMCsOLFgwfV1JWBJmntBmCTfk0gRgpTa9AQ3pJQlKWOkxEMBQKDE35I4EYETaM4cP5xAt0xHspe%2BE%2BgJyW0K4zYNIkthAI0cMCj6DKqmQVTQQW24vsALE9dZScM%2BB7Q1FYJRGwwdFDvB7ihNcUaiH0%2Fr7TORgGALCJHVTmCHn0xzbJ3maCz0umVMDNIcoMxlmqOtp6jwgoRIchPEhEx57AgShNKaIBTA5SaShdg9pIgkN2GkSBbiT8CJSF4TxInkwP8YrIhkNuGRI5VYkRDpHCJCSU0QIjIPv1N4AJZoiMWDrNHZGSyYIvPtYI5G6JCipOsDHSmKdVDo%2BlDQQdOuj9zIIyW5i2TkkXKkauoB95FH%2BUaSSvY4PxupsmyuSZMCq7lHPQ23IOAiQoyJpi0JFnkdsR0JKmTnyqH8V7aXpDvg05XwLdtLNl6ItzAhv3bfhKkzEibZQSZamDiDW1OY%2Fiwy7plRQUWLo3YjaVYKV9cYiZW6kC1YrBCdtRNizRtccL6z2qH8N57GuvInKyWsmoMGmcm%2Fme%2B4lfxzzl64TaTg7CnZwEsx8y39Sx3d3JxPz0%2Bs4fndyhyMtaxFvwtIpRaHNaT6NVuEAxk769zHkkCs9IZRyQ2X3Rf5ebxqsPTz%2FcrPB1%2FEd8z2PDTrkd8uluoKvdl0VkXFIybS%2BcsHZDDLDOAkLsmhV60BXrNtmtNwVhhHzfrkO7KrMIyU7pRgmU4RhpGmStCq1IGcdJSMq7JRCVi%2BpN9cO67SNlyItwMGJQqlMNsJk7oQOWaFszDBuXp7JkzqQLU1812fTHAoHi9XMBumZORI9WpCvRk8NjLd%2Ff7u2H2kth2QRHqZSK0Jm23Dn%2FSW69%2BZLsBZAMf3tVdJ%2BR0V%2BQgebYjem2GVS6DOUechUrTUQN%2B68KWvxCl6p9CIb5kvGrucJ8gl8vkjUKURmSCk1MzrbRkOCQKVnEmn6qSEWxtQ6koK3oglyu%2BCals4USf5%2BgOFoKT7%2Bg4w0vhv78Ip0wgHHGmqdWSalq73DcW0EWoHMNKvRxqp1ngDjC4yQBTCkhqpHHKalkuV9gunHWGvz%2BOlez1zIrf8de7MttRF9Tt0NVJRp15yDkfQpjUWiwpgodCk1r4JxSTGgdrA%2BgixMqG5qf%2FbM5mQQZ4C7LcWKhN8Q3ssE0R5KJ0LhaaK9k0o6RDkXVFeuM1csBupWwU3sldwLJs4krnx6vElMSd4hHDdrYjp6lf3b%2BYPq%2Fe6%2BGBc%2FR6ik%2Fert%2FMeUpKQa%2BPG0BZj2Fm5r5ZhFBBptQyI%2BkQJHxLMQxsiS3RS0KmoWN3d76vdo67Dii%2BNcM7MlmjSFbsSlbzBRNe37JubbRPnuXNDJSMfpBH1lD3UtVTAgdh1tG5ez46cxXP08FDdnIpZaIIJbb3gJhiw1BbhbaIFvgpsZe3S4BMqukcRS7XXfxP0SDbQTJCaBmdwc3dei%2FynsQl4WScYsgu%2BQqBiOfsKG4vDkd6hr2Ai7UhH%2FfSfWQSTjswjE5ktoamRIBe8NAacAM0bmqgZNNuDSUUf7vzRy3r9emnor5%2Fu%2FX8fV512GpBxdYAe409FDk0n7pujQ0YRPb1r6gqYv7xvQrFI5xPa2yxWKCK5q6zyTNEKPJNi4Fo0ESE%2FWJOGOmzIMUhYmK0bFkoBJkjzWjSFtG%2BHXCddemjfGq9Dfnvz49v9xe3pCTqeLqyBPRm8GD1a8cYTwk78wJfxPU9OqeZMOpCIUAFbjCBIpFPomGMCHOURzimdribJaDk51oUGROX52mZSNSh%2BFoNcYOmXCP8hGT95MZUY4IwBYDi1WAzU8PzkcGrGQgeGU4NC5zWcGoEdfeHJX81GztLFs7xe%2FOAy8sSzFT4whprbiS%2BRPtQCGEo%2FUPUDUvXLuW78oADNoRar%2FQE9cKIeHA8uQqf%2BMQybgpcDPHP688PCnb%2FIOIF3cpKKE2iU2ABKyJwROz%2BRDh2lk9DQSbBrw6AiOoCkzi00LIsOUh8B77LZazdhw5IzNlgA1nWDWOAXKTYbU%2FNXtjz1VJJWbd3z1COTbNSV2BG0sDyb5ftxr3mhvqTYad6c%2FtfsfBYfHSEkkv6vdo074f9J%2Br8luMI5tqD%2FIApbbGeqlnWAZ1l41jn5%2Fv3tzf9e%2F1Pc16%2FXH18%2Ff54df9Cxc%2F836iLqUvxGfcKAoaOQJOCQqQlBCzT7ZRDY6HHgeQ%2FsiUkZdGwddKBdjjrgNTa0LgiXZ04Crz7RCbnA48H3PdeZUrDYl9iDGwpExhuwIwac%2Ft7UX44f15iEiojJu30OPbmJ36yr%2FLkts6IZ6lALPI6nI6wFsGrNtEG6GE%2FCgS0coIyFWG2gQL7AoGgXbmc5yyD1Ag8ggPkKwUgoYyVzSDj1X6cSC7yxAGzGFI0FdTMWQmFILPDGArD9UjQWygrZclj4chHvKZNg4AoGoM5VNBggBnIQydmdHX4MHsa5M3GDh3U8jKJz9WQ5Dl8nuPD86ZNECV%2BUQBsuuaGkitDNYSRaZJlQjs0Yx%2B2oRAAwfxe7SHUhgWsfuNGLJameMk9yNJ67kV5Iyl7d6WoiFUCKicrDxJxn5FgFR3uPvbz0Y%2BHnwCAhwBYCUOmzYJKxxi4EmWJgLHao%2BJlThuF1%2FLb8T137d9%2Fvbu6P%2F6e%2BO72bwyOhNzqH0h%2FkqgtEUowgKkprosP4MXIMJZPAHwZCCUZYO1RxShEOIHYxrJlWJBpYo0EkxQijoYptjtAgsw7cUSCSXIRRUJV9ilAgmUURQBBJLMJOQhmvmAIBSEtLLPCgD0TSh7p2837d%2B%2B9Un%2F14uLp8%2BnR9b%2FTA3mlCyKzG9eG5Zwf5mWfVtdL0c69bFw0CP%2Fnb8kWy4MTUFkWyzDZ6KSTJSM48qb0SCREFsTVr88t312xRMAtijy7ePw4E7chqWUF8tkFMdO2BpqkPqKM0wOFAZtEDREM%2BMzfIA1ukfW7lzaNfoQFSedy2IrchNHAzTjVGJkpek7HQITobEjq3oQ4qbRZ6m2jNQCHIbLdY5QAV1%2FJSDiVAgcIYLXLBIsMdORMRZJ4cyXyLxAZEgws1HHU6%2Fbn1%2BebDkKrnuSs9eJQXSC653KIFb%2BuYo%2ByedQP%2BTaw2ZsKwahstbzFftEm4XFjLaB1o%2BYn4imIfVHeaMp6IX%2B3eb5yInxY2dBGFZzOes8UKxEXqHgpyWiK1jLbzMBzHcns85RSSN%2B3f8BpzCksFyuSaXiiE0fgl%2BPIpjgLitx7wG9lsIyX93pz8dHBDD8B7wHWrftWVswwEOq26KPirC9ckYJbDFy9Zk6sJoRo%2BVGFumUtapa3K2Wi89OfXgRfoUo9IcmAMwdC3y5yfHBxUFYADk2G2MB5ofRzHNTMvih7OPHeSBjPXy3nwVpm6%2FuvDGRoX1UdsG%2B4LhAG3EEatsb5Okl%2BsxQ6wX6DYuZ19aEBVePYDF%2FFu7L4W5gbEb8izzxoEALUl%2BOxDS9QSFGTZsRwSKlJm%2B4KGDQPsGEEDYLa4QQMMi4G9MzE0FkEMmfkHsohHJCqgek%2FBGoNOmkSFPDKQEDXtNpU3xgQw7jalQ%2FOYsHhl0sBtSKCqkHqirmpgkUMHUcDPYEBxBORLUI5ETNjEGiTKpZWyNhIlW6IECjt4oQS8ZY12K5JpA9KCiLIgul3DgmhCLQg0XldakG10Q3rQtrIgEAr4WZCyOgtpQXYHJZAF4YUS%2BJZp5iou2JQGRNhiPq2GAYFSW9wMiF2brZAGpK5q0GvjosKAQCjgZkBsqGxGGpDdQglkQHihBL5lutr7q%2BN50nyISoVbHYYfICLAtmVpPbbSCywmqQsOP6T12H2UdB5%2B6M02O%2BFNQLlqXPOA3AUkau9T8oTDol3NLOhgg3qq7PpMCXWPXfXGbabkhWrWt2YXwh%2F0Hx8DxZ7%2FjMBWVNr5CC2MO7%2B7CGssBqsxOJNdzlJsG6sq5OqwPrCqBeTAsaphrkJMcHbSU%2BxrBEggy2%2Fyb0uLsqGFiInfwSsloqIPd%2F7oZb1%2BvTT010%2F3%2Fr%2BPK6gM83g1GvsfnanzJHvTBS%2BPhdQD3FPGYtQqDAg6kxo1poauwfVy7jphVW4EkYvpbLVM3pIztYr4qD5rO9mbvpj5a0P%2Fcno5uzr1v5ybN313DcxdDsEw9Maz1ECcRC%2Bk5NlKvm6DOjfJ0%2FFoKHlciK8EQnRnTnBrUvKMJQ%2BNXOUlefiWawSZsiaftdiRAkzO4zSSAr5lqBw7nJc285y1nKEp5OyDI1WFqn06HIhBgKdNhI6fLLvmDQNolqpQGJSN3fZ8f3YdhgISA7wxAE1SFYoByA2I5meulq4UP2%2Fx1x1Sx038Ze7A0PMXUv7cI39oZqpQ%2BdNVsdlcMkkK8iMFdYoUhGY3QFM61L7OCQt0bWw8rC4qV0l79KHilf1VBJWHaisGEJQ9Nx4IJoLG0yC%2BdrybeCzhQbyHT4aFXDAAcYFiMUBHhSEGBucfY9kXMkYSAzwwALGCYjEA54UeniYfg3hgFAPhwfc915lK6TOWPkgOChW%2FHFLchBNmJPW6jh8DShi847I1Wwt3GXl7h9mY8ui1DAr5agGIFhRrBMo4oSCEcpehMziXzABnEEC8oFgQlDFDITEY%2BIQSAZwRAFGDYhEAdUyHCBgvBok%2FmAOBdAo5UQMQRSgWB2Urtp7cZVERVNYUSiBsCQRcDNgdEKDwIF7T68zlrj3uHGHXfmGa39jf4doauRXABmbs8pqtDQsF0s6ETLith9iRtQ%2FkvLKeYRGPu24HkE6OuNfJUdmMtj5QvwjfMqulD3Cyn47sLt0X17t2PXe4PCmfvCmbg1ovpjMJQWs64Nhb0OFQOakMg3bsIxR8cR3ZGMZS9iax%2F1IHjIWmCBU9nOOdB5K%2FHE%2FdxeHDPHbhBqvHR3fujmJMZK7d5Th4tkk3Mi4Tl43IdRw%2Bg0VWGEQLv%2BIQaORNuW%2FBYqNuthboe%2B47G1cE6bZxUFgRhJT0DTErghYXmurdvq2Xx%2Ffrd%2B%2B%2FzX4uXu57wIKgymNZv706XT5ErtblsUFIJ7fvmi3dq8CdKt62TjpqGzus8ZWIv4JuuGblM5nNFmXtiYPddq%2FaZle9BAHMxEkHsXsnTo30SVvHS9SVLHL2Am9x1lgtwnqNnXIgYut7Va62iz1yOnlyLctoCRrqSoJBY0G5rXLQbDdgJfVSeuFQlIKbohzZqrXJTQF3JyK0YXti8IL0bVg7POCTTQCa93gu1enx4%2FXFL%2BtdbzV%2F9j7%2FQNeop9s1kZx5PKZpEVqLn8tD7BFN3e7GE2XsDRfaPX8HGJRcGDtVnx2QVeEHTRkDRKhGFSCL4BVuuGCAfVW4LArbuijMYhH781reVanIoaRvvIw8l%2FYFFYOkf7aEAJ57KmIRD1wNCtE%2FCQbigVHUBCmJAtYoELnA7fzp%2B%2FXZ3der%2BerizLE93Z%2Bc3pYNh5m7ztJN9mosDnMeQgaJF1kGwAEQIte2gYCgg6cMEPGYfIkHgXgQurANBAQdMGSACMdeSziIhANQPi7YbYDqRtszrLksj6HYxSyPYgjI8mzkMvrJH7wj7K6OijyJ3Za8IMldSzBVb0OlTXHq%2BQFnnU%2FdoT93AuUSCXQZyHiKPxP8zofS5HQObs3Q0GSNokExpgBjBS0mIIuO2PX60GFd2NMzHobD591EjLL4g4H0yQVoCJI9r5nAcPmuIksFs3gq3Qyht9SO9Io78kqc1SNSII6mXJ6M0hhJxivNYWwwwO1tbaUCq583KBYcpIQ8jzn0Bnnmyarf2llyRGKrZhlqnUn0zPAHDI2IHf12haQyXdDUxgQqoCyRv6lJQOOVLkBg4%2BBfrpXSo1BXLfVIvcSzfIsq0jDbVu%2FY5IXUemqpaXV8j6wQwHcMVsdnP44%2FLixZilRaB8ZMuCym56DvqIjKhvKjUESFbG5uNdZvBP818V%2FcJEuC04%2ByQ64J95WdrZ1cD1lyz2UzVTM4hLKQzZK8oSByB2TJPZeOVk2h8OUiuA%2BJBd5YAMZqid0aW5ZIz5BwO5Mg4AsCaLqWWBRAjQRFFJz6r1OJA844AOZticUB7S%2FiMQru7DAbv3tyPIxq1tST5Xjiyrwpb1wYAp1HWD9AwChnTv5Kfp6qgO3pbdOXFIlAXYkdQQ%2FLk44FcoUQkiQVRBroJP1lQxYAaqxNaynYH%2FUaJKksqk6RUH28tqIHILlzkzo4UW2D2Ze%2BIDc8QBXWvBrsYU8QDAmGnuvM8emS7ABnEEAF1mJBAJVlBH%2BGMlwHSIhH8GYgiD2I%2FV7DkHeKVG7AgAqthQJDg5yEEBizkCxIKrYOZWAoEBNgsbVQUIDTOMuDRDYN7%2B2GsnDMyOcbkStFuzl1r7YIgPml5u1%2B%2B2h306VYJefpm7aV6ntDulH5E1tPu4Mx0Kw%2Brkkheh7ZiCmy84CtRPauEDbsqkuCSyHiUuSgIc6MDWaMOCAmfICF1gXN3oAbxq0LVVT0rmAJ6cywRBZj9syaJZTMsESzubHvjiO7kzDMk%2Bwfb%2FaPrFlDKcO8aQCnzS3ig7csOXEffSHQk731Nfz49KhttXAPggC%2FoB8qDpEEcCMCuLnYoR174MnnJXVwqcpJge%2FLigW1Y8n48UABwPgJPvzNOmTZzFVV2w1WRemAsmSwqsbfbW0V3VceuLp1%2BUT8gYyKnQtbT1Ul89N2%2B7Gq1FqC1nNV8ZaJ9BGQPUxMiulBYel09JVzgpJZI7KunoOTbJHcUR%2FqR4EWk1u8TKUOpcZqZUXi1bT0f6UdrbSj6enbyonmtbwevmU6xA4wUpIdkb4UDwxAHrVYDNCBVM5onPq%2FpckQYzLszk0GsNyiyq1uXj9ZCcAd4VDTR45rXPrtfEqyBZ28Dmf2VC%2FLfUvzz0n1Yz36B5l%2FKIyW5l8kBro3%2F7TOz5n%2FeByhtP8i7L8F7TUTGzJCk6qlzeCoL6zasNkZmwHVhEibIRIDndsMo5JnDCeWSoshwmKYnVsMQ5KMYrWF8eeRjFhdSYvRGQa6txjNtoyxmml2kK%2BfPto06riZ4t6cOm040kw50hAxeRiSG6vsqUGmPPut%2B4R1jQxlamZPG1dO6%2F2Smy6%2FN%2FInrITl3DAJjX%2B21mg25%2B%2Fv7FanUGi0rt%2BnJV2z%2BHl7FOKb5lqND2wd3wYv8F48XssYK%2FmGnQEj5WC3XesJgFEh7ocz%2F2%2FQXA6i4JI4VjmMNPWxSgup04LJ1%2Bfx0r0OfKvwd77OndmWmqvJdFeqzwaY7goOd1XKsbOVH9Tvxg9q1kmWlbzptp7TDuhI0a1K%2FQBtzWyvM17Hb8v%2F1LV%2F9%2F3u5v74f%2Bq707t5OgIg734ptz8%2F%2Bparm6b%2FNln%2BGHw5%2BfwrLWtsV7kGDTli5XrZZJNG37LaqZlsQHaqsWr2rNGXKv75xYvwc4KQBs94cUdyxotQbom0V%2BCcdyhm5NbkgTS6MiXs8nhwFu6x7PRoxx1k5%2B3PIZAQ2MdPyFn2ejAXfF3WiNu0Hw2sT4gMcKSO4z2qePKPJBE5KwKo7UOwImhWoMbMfVaypo04TO5baTdyky6ONOY%2BUhTsSccXNLGjLcKxrja3tVcwkL0eOLjhwVaaZBdq280wPUOzjgy1n%2F6ziL9Cs48s1Uq%2FTWC5%2Frp5lZjYQFIJTHzqoT34dBoc4MnL9xvVOFvf%2FJ74vRr7xv92XrFPwsVoy%2BQghYzWrJpTMJvSitQ9W0Vue2tWEQRLRyuwrEyB4rY4s41GbYbOPGlwdf9m%2FrB6r4sPxtXvITp5v3o77yGlLo5ze2gsgzjqHLkDkwj428LaIqixmrMdmmKavF2tOltjVH5cSKoGPCQ4SUGRFKHfCY%2BSk2UuLakIg1yaWtPrZLH2ERa%2BCqjIQaEs9jDsNc9zVfEC1YSRKNmgur8BSfUZ20liAr5laAZFxX7dq93ar2tCDUe8tunCD1CmwNUg7qJCm7a%2BqkF2xKcL5xjbdfo3aSKcVWD%2Bc7z3427svspaU3aqAenk9C8VygfwWr8MCx8ifwajufN6GIMgeB7ntxdy7EtNe2vWBkWFvYUQwM3eAtMF49RP8%2BMvU4SNVQJNhACuObwOgBsg4AxhWFscnMGwujhQBtfLefBCqoNqdaCycL%2B5TXKGbxkKzAgpy7Qga7FDeyE57QCB7xhcEBq6AUk3weIw8AKCA3IzngTfP%2Fb86dM%2F8vSzhoHIrQ%2FwLTdLc3Q5x13vp6OKE7Jb39SLwHgIXKXQ86y4rt28X%2Ff%2BO9VnPx6uLp8%2BXd8bvZ6ZJAm6mPBOh8dU%2FqV%2BeEy1XPNbaAY%2F8WbZlu0wi%2FGHjhSkHxTS14phbcJfi%2Bpwxgka%2BAl2iESVRqLREok22fagkXO6WRE1Jvmb8E2X3hv1E4aiEYeBA7Wj0bGdgKNRPBYblbLQlgnwMQHVz5fq9Pjx%2BuKX9a63mj97n3%2Bga5TtUe%2FkpNAQUtvqbBOZRwYxUpNTd1nwu4hiEN3ceFaIn7AKP8DpqNBN1%2FvW2kHzk2niQkBrBywVOhrdO6lopFQMoBRCrFToaHHvpEJtBLaBuRFipcKnlTGItOIqosQ298LKTbXaZwUKK9v2TbU39WDUhXfnbgzPahduCkpokkQxmYOs7yVr1RdiZfcVC%2Fw9FfdlVHyek9HnPZvzT0iVG3a%2F8OBRa7eSvFJNaDGTpqzRpb2F9tIkr8RPmher4bNye%2Bl8u1%2B9DE5%2Fnnw86%2FV6dOh67k4DozL86E5XsoVRUAujStZP61DJAuTnIFvd3tEBkQHvqQoXlp%2F6r9PDf%2BTeWQAPlWdsq2oFSPgsEhTgHdMqPslNr69D5S2Fz1H4VYOThAgfLkwID%2F7tTEqeo%2BStioVDQiRfNnc9lH0oASl9jtIPYs%2BOxV%2FWshyKP3rgUv485V%2FVeiVE%2FlCYjuV%2F7XrucJlrWJdA4AYEo2v3D0GjcctDfEadlSHlqZLZfl3AysE8S1nlEXWRj6R3GOutZ12qZA1J3cw9K7IBbEfbgjvPlSmZBgEc1BcLnMXMXxv6l9PL2dWp%2F%2BXcvOm7613bD8QUTOSAOo1MufAGU7N5dH8lD8lSoPSlas4gbSHQ86fv12d3X6%2Fmq4szx%2FZ0f3J6C5AOSVWspCHF0JAWeaKhJksNQDOLJgkQEnTNYW%2Fij1wv9j7l1LQiCipP1VbkIyR0Ft4neMfAxAJKyLI3grHUIdYRPOq8hA6FHMFfqwQW52N45DH3IA89D%2FFD89HEHvpmocF2ISc%2FX49d6wDZw9w%2BwUxeSbBb13BQQlYdna%2BHHjmL50h8qDIgTIupQn2Wn%2F6MFO1A3JA6sG4iYdc2znHCi627gB01x6v1kCaEyI4D6lK8cdesUI8N7pBSxJ2i9xvjLkdrZCRGnSI%2Bxo1X8KhFFcDx4kJTvdu39fL4fv3u%2FbfZz8XLfW3tmQAm7OzhuTOG3I2M2pb29ciaVF79Lz2DmGyuqdXFfRTBR%2FyAkCFk8FlsVTNW8%2FAJX5n0R1B95JQbra0D0SMn2wUWnRPg7ZJ7Lr018gd6WjLXilU9K4hmlY5VEqaKAnUJP6VJfqpNNwBBUCG1D4QtUKaMG0EFdvRLrqIRV6HWB0LD%2BjheZAUwuyUlK%2FDTzC2GTB%2BwZC0YAwG3j4jIlMO3DAW3IRIWOSTgLzJEyLoJHmiASCyxaGDbG9bMju6I40mmERFqm5Ekr9Tn1xoBS5Ouh0qG9dX08uSsxo2ND4hEiwJVwfGa1QiLvVmsKpKJzrMzlZDNR6uVyrWbkiZSQ%2BhtQ1NEDGvIJu6wDk3p35Q0AvONNAUO3WkPRZAPsYBxH5WYFQpFXHFPMhn1kUe07fV5cSIE7mxzAyVCApX4AT44BSbg7NuoAooWDvwe2pTxGlXwctZ717eH7rP99Msav7t5mS3Pm1bIbaU9Ir8n%2FmA0x%2BDHajLDflAixeit%2BCN40gGe8VWcSqRo9jZJE0VJ17HE10NI8Mg6OHOiAMb59ubHt%2FuL29MTdDxdWAN7Mngx0lqIThLP9MytdhqSqibjl%2F6DwQ9xNO1jseb4bgafHYneSOm3LjvYfCXe8me7n2Ff5d96YGXX4mdLxeyJ%2BNnJv3sANBs605HzoxyZKnZ2kooRq%2FmeejEp8I377TXAwal2hTqhH6iZYm1dHHqmai2ENw8B7eKkxqrVcsyOj8AR1dscH93u549P46PT%2FghUl0f92cjWyAvVrBltDG1yFma%2Fmt1Qta0%2BvoE7If9qxswJfM4ghi%2FesRYei8KBM3%2BtfPyN3iI6DWEyEamztwjJ%2BPt4MRvKbWuLL1a9zi93QOszLlFCoTRRGOYCndXSXyR6oAHpQ2qFZie1CXEDpByg2nfyDDDjaYDdQBIDQjGAyM5oYMooI0gEL%2Bd%2BKKJMi8yd2fPHuK3t7P8B)



## Known Code Smells
- #### **Large Class**
Some classes are significantly larger than others, mainly due to holding a lot of data, like Arena.
- #### **Parallel Hierarchy**
When we create a class to extend a functionality, we find the need to create more classes to complement the application.
For example , such problem is found when we want to create a new object that moves but we need to create another controller specific to the object if it is necessary (walls don't need controllers, for example), the viewer to draw it, the corresponding editor state, and the corresponding editor controller.
On the other hand, this allows us to better respect the Single Responsibility Principle by not having a class that stores, controls and draws an element, and it is also necessary to uphold the intent of the MVC architectural pattern.
- #### **Switch Statements**
At multiple points, we use switch statements, mainly when it comes to input processing.
- #### **Data Class**
A lot of classes in the model package, notably the elements themselves, are simple data classes. Normally, this should be avoided, but the use of the MVC pattern makes it somewhat of a necessary evil to be able to keep the model, the view and the controller separate.
- #### **Alternative classes with different interfaces and Lazy Classes**
There were classes that did similar jobs but had different interfaces and
during the development we created many classes that were developed towards the thought of needing them in the future, but in the end these classes were useless. 
- #### **Refused bequest**
There are cases where an inherited method is never used. An example that we don't consider to be too offensive is for the main menu controller (MenuController), which has empty stepLeft and stepRight methods.
We say we find this smell less offensive because we saw putting the different abstract step methods on the GenericMenuController as telling each menu "these are the keys you can use" instead of "these are the keys you need to use",
since naturally different menus will have different functionalities.
- #### **Feature Envy**
Since most of the data is stored in the model, lots of classes access the data and methods of the model much more frequently than their own.
- #### **Message chains**
Due to the nature of the MVC, message passing is common between classes and there are times that a subclass only propagates the messages between its subclass and its superclasss, making it a *middle man*.
## Refactoring examples
- #### **Extract Superclass**
During most of the project's lifetime, both menu controllers inherited directly from Controller, which meant there was quite a lot of repeated code between the two.
- #### **Extract Variable**
Some one-liners that we had in our code weren't very readable, so we had to split up the calls/calculations between lines.
- #### **Move method**
At times, there were methods that didn't really belong in their class, so we had to move them somewhere else.
For example, the game's screen was initialized in MainGame, and then the LanternaGUI was initialized with that screen. This defeats the entire purpose of LanternaGUI's existence, which is to hide all Lanterna calls from the rest of the code.
Therefore, that method was moved to LanternaGUI.
- #### **Extract Method**
Some of our methods were much longer than others, so, to improve readability, we extracted some of their code to new methods, making the initial method shorter and easier to understand.
For example, in the EditorArena, the getArena() was even bigger, before we extracted some of its parts to other, smaller methods.
- #### **Replace Conditional with Polymorphism**
Early on, we had some complicated conditional expressions that were later replaced with easier to read polymorphism.
Notable examples are the menu controllers, which used to have down-casting, and the early ideas for the level editor, which had a lot of if statements for type checking.


## Testing
We often find the need to use dependency injection in order to test certain functionalities within a method.
By using mocks with Mockito and Assertions, we tested both the behaviour and the values of methods that we thought it was the best to.
By using Pitest mutation testing, we were able to find holes in our tests that we needed to fix by covering more content of the methods, improving its coverage.

### During the tests
We followed many testing techniques, these include:
- **Black box testing** : Some small amount functions used a testing method close to this type of testing because we evaluated input output relation,but we had to guarantee their initial state. In those tests we used techniques such as **boundary value analysis** ;
- **White box testing** : This type of testing was used in order to test the behaviour and the internal structure of the methods we wanted to test, in order to assure that within the methods we want to test everything went accoring to the expectations. We used techniques such as **path coverage**, to assure every method inside a method was invoked at least once, and **statement coverage** to test whether all the statements were covered.
All of this was possile thanks to Mockito and Junit!
But there´s a catch, it was not possible to cover every class and method, due to the nature of the MVC model because it makes testing more difficult. And with other factors accumulated like snowball effect makes the full coverage almost impossible!

### Screenshot of coverage report
<p align="center" justify="center">
<img src="Images/Coverage/coverage1.png"/>
<img src="Images/Coverage/coverage2.png"/>
<img src="Images/Coverage/coverage3.png"/>
</p>

<p align="center" justify="center">
<img src="Images/pitestReport.png"/>
</p>

### Link to mutation testing report
[Click here to access the folder with index.html file that contains the report and open it with a browser](PitestReport/202212231510)
## Group Evaluation