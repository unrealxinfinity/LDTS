## Game Description

The Bulldozer is a game in which the player controls a tank named dozer and to win, he needs to push the boulders towards the targets. It consists of 15 levels, which increase in difficulty, with more obstacles and more targets on top of which to put the boulders.
This project was developed by Afonso Castro Vaz Osório (up202108700@edu.fe.up.pt), Haochang Fu (up202108730@edu.fe.up.pt) and Inês Martin Soares (up202108852@edu.fe.pt) for LDTS 2022-23.

## Implemented Features

- **Menus** - The Main Menu has three options: Level Select, Level Editor and Quit. The Level Select Menu has the Select option, as well as the Start and Quit. Level Editor goes directly to the editor itself.
- **Buttons** - Functional and interactive buttons.
- **Keyboard control** - The keyboard inputs are received through the respective events and interpreted according to the current game state.
- **Player control** - The player may move with the keyboard control.
- **Collisions detection** - Collisions between different objects are verified. (Ex: Player, Walls, Boulders).

## Planned Features

- **Saving Levels** - Allow players to save levels from the editor and then select them from the Level Select
- **Editor Level Cleaning** - After a level is finished in the editor, check which walls are important for collision and which are not

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

### Unnecessary collision checks
#### Problem in Context:
In this game, the areas that the player can't reach are filled with walls. However, it is impossible to touch most of these walls.
Checking collision with every wall is inefficient, since only a handful of them will ever be actually important for a collision check.
#### The Solution:
We can differentiate two separate types of wall.
#### Implementation:
By creating an "ImportantWall" class that is a subclass of Wall and is functionally identical, we can differentiate between walls that are just decoration (notably, walls behind walls) and walls that matter for collision. Thus, the level saves two lists of walls.

<p align="center" justify="center">
<img src="Images/FieldBuilder.png"/>
</p>
<p align="center">
  <b><i>Fig 3. Model structure </i></b>
</p>

#### Consequences:
Since ImportantWall extends Wall, every method that works on Walls will work on ImportantWalls, and due to polymorphism, a Wall container can hold ImportantWalls. If we ever need a container that has both Walls and ImportantWalls, we can differentiate between them with down-casting.

#### Related classes in:
- [Click here to view the model.game package](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/main/src/main/java/pt/up/fe/edu/dozer/model/game/elements)

### Various objects to instantiate
#### Problem in Context:
As we have many objects that we need to instantiate, it becomes difficult to avoid stuffing too much Rigidity into the game (in other words, making it difficult to make small changes in the code), having to make changes to all the components and functionalities everytime we add new type of objects.
#### The Pattern:
**_Factory Pattern_** was the chosen one. By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass, so we can add new features like new types of objects(ex: enemies or obstacles) without the need to worry too much about rigidity.
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
<img src="Images/GUIUml.png"/>
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

- [Heres a link to the whole UML diagram](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Diagrama_sem_nome21111(1)(6)(2).drawio#R7V3ZUttKt34aqvhPlSm1Zl9iprADCTsYkuwbStgClMiW4wHjPP3RPHQvyZLc3XJC7zr1n3hACK2v1%2FCt6UA5mbxdzK3Zy7U3tt0DWRq%2FHSinB7Isq6rk%2F7%2FgnU30jiJJKHrnee6Mo%2Fdyb9w6v%2B34zfgHn1fO2F5E78VvLT3PXTqzReGnR950ao%2BWhfes%2BdxbF3%2F2yXOLv3VmPdvEG7cjyyXf%2FeqMly%2FRu6ZsZO9%2FsJ3nl%2BQ3I70ffTKxki%2FHv3rxYo29de4t5exAOZl73jL61%2BTtxHaDp5c8l6%2BXm6%2Fu1U%2F94p9%2FF7%2Bsu8HH4af7XnSx8yY%2Fkv4Jc3u6bH1p70pFw%2BHF9OLEGF3cr%2FSBo%2FzqofgxvFruKn5gA2%2Flju15%2FDcvN8mDXKydiWtN%2FVeDJ2%2B6vI0%2FQf7r0Yvjjq%2BsjbcKbm%2BxtEY%2Fk1eDF2%2Fu%2FPa%2Fb7nxl%2F2P58sYJ7IeXM1x3RPP9eb%2BG1Mv%2FAXZD90GF%2FM%2FkPx35%2FbC%2F7Gb5DEg7K1r663wxStrsYzfGHmua80WzmN6yxNr%2FuxMB95y6U3iL9V8yrE0Xu350n7LYSx%2B6he2N7GX843%2FlfhTRYsBFB8h04xfrzM8IiN%2B7yWHRT1%2Bz4qPwHN66UzM%2Fj9iSTeQenIoclK%2F8RbO0vGmTcRuuc7z1P%2F3yH9gPmCUQfBMHP%2FoHccfLL3Zzuj4q6CgKkUooFTGeSzoABZkiRkYZAIMvfCOfSWnHAc%2F7T8DHBX%2B37sMpTX3ftrY4QXOc4IU135aluJkMbNGzvT5KvzOqZq98yV%2BDMFbnv%2BzT26ohl%2Bc8dieBjL2ltbSigQaSG%2Fm%2BbccPiZt4P%2Bf%2F%2BBOpCPtQPNv%2FMR%2FjbLX%2Fv8FX58vT7yp%2F7dYTihu20fL2g4QAwCh%2BkBtR0IseR%2Fb9eSushK7CYp9I8TOUuxa3ePOTOwKIXZCyq4TSi%2BScuI0oVYinvjCcu1MpsNA5Kc9RMhdIeWuADJ2rUfbTW2VcjqPvovJfpt4i%2Fh1pi%2F23GEqdkOtJ3ak7y72m4c3%2FYfRWy8%2Baje%2FR%2Bjkw%2BrtoocgsQ8C99xefjv8nzj0LKXfr3noFQom%2FkqeHj%2FdXv4yznur%2BYv7%2BQe6RT2lXy7970L6bKWP5JriV1mJP7kwIX7718pyF4eJPs2AMPAjdNsigwEBhl3BUNffpwEG8J4VEgxDPwCyyZMvAv6mUV4iZEkBpKwCUtZkVlImvfuvVvBjQsZ0IvmUsKmUuclI5qCPB2j5E%2FngeHBrz1%2BdkR28GPjPQ3cDCT7O%2FX89R7oufid548oK6JyplXzg38xj9mUMQC%2FLSYIBZo9a0ovPuofAOApBD5uCGgUftkqyJmeuPfH%2F9HvHXgf0afTw4jdLn1%2FJAVTEAdyKCoxL873cmraVHSi0EkdrPLfWh3YEhYXvh1zcXWbO1qvnX%2FS9elp50MskVqrP3l6ybeAtayTjfmFN7ERXCKvMSilogFlWQOpFoiD7kTn4dOrf7eT1%2B1DWzjbD3xOvB%2FPrgUqIjcOhrw78IxPrB18pDP1XoDUZJnZEaI%2BSOK369O2iMEDUyBQIOxA0%2BjbQLIqo8X%2F%2F8ZWzWBJIEVDqGEqJv98ZlMgUf%2BaUYGAaOhNb4IEtHgyTHx7AW9ZJX%2BTU%2B23PhTNC1xkxsLqPHiR6pPGkCHTSF8kFKCEKRHTSXAnoNPwLEAnslAAZqgYEodABdHVAP0kRVOoAVtQwLHnStczpgIglFiqgsQrQakOjQgVAQGCnAkprP4UWoE1L9HFXAKoBBblKZmqAzBDl1EBaBCw0QWNNYNSGR1N2kpkmIOtDopywUASUFYFs1IkJuPoDyR3BiuDCs4Q%2F0EYL9GtjY1%2F8AYPkBQgp29PxcdCU4796dL3gOA78t%2BIjjPTo5bkT%2FOLwwefSw3NvNR3b4%2FgD%2F%2BnNN9%2BCF0eqaSZvfA%2B%2BeyRrRvLG6Vv%2B%2B6eb%2FKsbe%2B74f3nQedDi0C6jopfyx5Ek8%2BxxocGIlB%2Bktue2ay2d1%2FwPwhKLL3fjhbV3qZ5AklnUEwpe%2F7%2FwVvORHf9cJnnyUhqePdfwvpLoURCX8gVtbXJfiw8RDrP0SeyAPIiWKEdeYhnKsNUMCE3kC5I1dERuyhItiUu4kSEuVSJxavKsUWT%2Bt8sTKSo6MgzDNHXZVA05eQR%2Fg3hVIV7iuKYRXVN5bj%2F4rMUJVa3wEmf0lKocqRZmmKHc%2B%2FjhQ3ilbl2593FOgLvcISKwvdztN2cZOXRa%2FOp77pPMkQtebBhhxdgrrCCEu16tlQTC6035owXii%2F5stPT3Cy2KiqPFaIsW4lK80WKSHuC1PV35QfVy7rmu4JS2obc%2Bp9QjA7ykEnrbgAFmnFIfLndsLn5RB71V%2FIQbwbP5BJY%2BSScH0vcf9tIW557aWBGiK0IiBZ8Or8kLPjHl9LuMkzsoOAm%2BKQgaTe8vg1zC4f8iDjmsfr4EswuCSYZP0y5MMogDZkwykiAqOcZBZgQSLOTNQlSceuw%2FE6u0Z0YAZEeAACWpvAEC%2B4fCRNCtQKxlIiTIRLBqhEnbLEAT4aQmolisHmBDqIN66iA7WzsZDAgVScjPABUQB9nAYAiAMAWIShYpwABhZzCgLoZy%2BolectpQDwrJacnknJxeO2%2FL%2F%2BSNd%2F%2F9fvhw%2FI98fno%2F94O8g8bcFa3kdEovpFYFH1xTl6cirpR6IZxoqjTN%2Fp5TWSqR0mgrT30rMpjLE5p0tANLnSkCLa8GpC0qoP1pV5Xhh03vv1N19uPx5ur50%2B2D1kvnzOwJYLaLuTZRLRMeak3ElBenlGXNcZ4s%2BU0ZBKNr0gUkalZUxTm52t%2BvFIhqYjLqt8TVdpXGWhEhWRgWE2%2BAQ21rXsgrcZdnsyIJav6nJBmY%2F6n2%2BfqfMvp4741fN5v1laauPz14%2Fz6tWlkkWv5nDy%2BYUduXUqEjQ%2Bun%2FyUVEdlEGeWob%2FAFmkxXcbTJs5d5PYiq17PdOAFgXMy8jaZ%2BOb2a3Zx6Xy70Yd%2FeZEZ2T%2FReD3dqWtf69XBPK42YeMERmHN1bo2WXvg4b6xlMCmMwCfLoWDpoQcmKfYBmTKbqgmsTricBPSMNV2KWXtUZJ18irmF4AIFVhluUPbAAoW4eV7InI7MNXXfZA4Md4vTlPVlLjZmNMWBjJ19g4SBDJY2ScxwAI9miufkH4sZ2nkQVJ%2BknRJUkNRp5B%2FgWyZ5xUDoyeYoIXUWUofKFFhJHQwsTDJXHUjdtV9t99NqcpCfmx%2BM2pXCd%2FyvSWmQI%2BBADQ5ApzRnJVAjByl2p9AWe1oxsk3uFKoaYS0AUfhBg%2FzCXkYG4FD0xrM9%2Bimd3p0pgGi5GAThMRcYYI0BhaP%2BhzFQtkTJx8BV7BMIGLCGgV7THLBzA8omJ%2FkwiOcmCRSwRgEw658vCjSojjVGgS8%2B11n4XlbAAy8OR4WXKTLy45ojwjgdyOzftPJ2fCBqGRlQCahrLkErG8gcuBIhYGrjROgYlkhJcl%2FdIaXC0kQD2haH8f8H0ZIs9oLxIpQMO%2BjoXXMV4DCvADrOIvFRxNo%2Ffnjod%2B20giO2IjwENgUHg0ACIyQowDZQzkgoi2KdRWQvhGLgCAe1a28UHOYV7waOvNEMBmWeqMAFdVwk%2Bro7XJTtsnvOfE8YGUWvU2CDOjb6nTuXZSsEnlMGrAwcyccCHqzgocpd%2B5r9CtWBUWPCtnQAEGB5LmeAVOiPsI4yhwu4rlKAYFcQGF07nlBrXLJ6OtxGh4lclFWGj4HSZJCkIyEbY6oCWgEsqGdXUQ80zQX1VovR3PYPWqIRbqOXccGVUAxViiE7ZDvVWkI4YKgYaowPF3VW1AUPlVtCgqdQZ1Vyy3C5ZbCH5OTFmlujZY619s9K%2Bmbwb%2F9BreYiJcZYOQBNV8yUg%2Fd16H7%2F8PZbeZl8uzk%2Fcc3v4zPIdexFjuPtch4cXmdiHy79%2F%2FFh4HrT5wwR0efvFhN5n0liBxCze%2BtRFn36boO1tCPv4XBozyfO1HKFxmCsMcDSTVaIAGclQTNJAzy4njW%2B%2FbXy%2F%2BZz%2F2zkQs7jr8MEHsEn%2FjN7cp5Xcys0O8LtZIQTqLwTxAkF%2FwPECRmO9lKtkeDhMHI15ZO488d%2FgFkdRRVu4sl1yecCQPQBpNc0PTTiVxBAZPSaslqffPEejwIgJDMMj0%2BGl58%2FCRzQj18lji4IrEjKam%2BCSKZQbRE8AiLZLhwRNrhQunZEwJlQCS4i3lsAowNgaHU9D2bAKCvJCYAx9AVcBxeinJMhREwgp8qX9wDLdAJ83LjWqJ7mEAjhRIIowAIeznABSZBQnQQEmQDLHoFF6dxfLSsBHLm2JXrZmFsXRe%2FcMy3lTF1vYQsEMEeA2bkLWlaSM7ef5vZCtLczx4CKOPqY8E0DY%2B3OXHvi%2F6nROpbBygmKNwnhiyl3bScbkvPPoTIcaNUGMQGZIgwqiMyAvipucgvfccQmtxpKQW4eeNbdzMNOJ5RxE0mtZoqG0D7k3hJwoA4HqESHMxwq2oWixo90iVeIh%2Fg9AQg2gIBmonEGREWxf9zsUUTEQdBOmHtfoII6KsCRaXxhoZBDLoor%2FYalDR6i0Juih2ngm1BUYHIOuBw8LchgAA5yrkVQajHxxrabKomhUAxbFEN6xnZxJ0HZM9QLNRZ0idpu6oIHHEdQ8MyOvArVRChBQiJmGBaHYdPPSbF2951qgLzdk9mhAqjm5qwOErYD8h6vA3OQ8xqvI%2FOwzX14LxhhBwughpszLPQyNjpQGIGiEGx0%2BxRlc0BANdy8FUWzRa3U9q%2F1%2B8pBfvHnETLSN%2FjsX6u2p1tXXsUC9m88zUkk2l8jhUppRVsfX%2BSoJjRB8xVt%2BOo%2Frebqv%2BxSyRe9p6eFveuarBJx0N072wwte7L2jBC6okvthE5cSTPryZyaPDXILxHybClPJCnbLsVcoFD80V6gmYUoWgdti234O4Cg4sexPRBUo3%2FUz%2F1ncoaFLM75dm1bX7xqS8XdfJ03vgQ2%2BU201nnDVTKkF%2Bq75n6U4br1yyIUQWS3KZUg%2FECoyBYBx4QKkQ03fMEDCwSPXcZQVB%2BqnaoiIMkzq5aTa7iHgsWmLXao%2BgESO7PjXpHqTshKwVRy1APQNBJWekBGH%2B%2B98etms77S1PWnB%2B%2FfpxW0BzhFxG3gGfr%2FzA8WWI0d79qaWs%2Bi7IE%2BGqDRI3ytQhkaFkt7dnjtP4oLa2L7jyrqBBccdp1cFyVwgGNIuKJDIX0G3y1f2iJsYBk2qHicCE0%2FRVCZPUpGcNNHAjxpRIQNjQxEep52qn7h2mSTTCAQYUP7sKG52KHaF0ZzDW8e3vQfRm%2B9%2BKjd%2FB6hkw%2Brtwu4F1c5iMdhJ%2BxRGDvkySQRPTBWB1D1C4QLGo02MDCgWvoYGMWuirqlswITO2ICKo7ligkkl%2B58w4KIXLlc4jMUB17ei6iitDKGElwQsM2Js0tR0Z4lCui6USIIWOrEGRWQZcHETK1aqlnoli9tgq1iw8qm5MJpB63cJ58%2BpbqmHh5NEsME66ZKe7JypKtS%2Bp8iG9iVDfXINLMvYPe6tegpfTpa4bpav3gdKjVRIAaBiotre7oqafoSLeRt86JEzl0HEqOg%2BkkCI%2BrqB6yqSMp3gXp%2FwYQ2MUhafYzsC%2FEBrryO%2FJDHxA85t0ZLL7zrG2sZbIFJvzHPXJXoHf8eHkvdl6JxWr84S%2FvWF23w6XpuzYqWjNWpNBB2KDVgrgOC%2BGca02kN89%2Bf17OnO%2B%2Fh%2B923%2FtnHM%2BNqAY7BxkQQ89G4APb%2FcWt1ikNSZ53245bufl57hq3quvc2Wf4YfDn5%2FKtHemCB9atr9wTZT6PZ1awdnVHg%2FkAUkNRfwPQHNaGODe9sT9ZUxMdNjJsvAqXyrO2lQQTvmGwXCGfNr%2BbBX3o2jW42AkfkzodfkgQSaCOhbnKAGRKgTjdMxiIlRFnowNQUVhkh8IYhbzgIj6b%2BYQ4Pv5i2x%2FbUQx2uXE89grzxAAKzuf3qeKtFBgMBAAYAADta%2BSKgonQwEr6NuwH%2FE4vMWEICWEDEGRJltJmzuLVde7S0x6WgGHiea1tTgQraqNDJUVp8UVGaEA7WCq0mj%2Fb8LAonc05DkhUQSKCIhLq1AeyQADWZx0g4yYWOh2LpJWv%2BAHXtQAI1pFnnQWwsfO0gdAJbHKhdu5EJXZDHQcAZDpyANH4KiHj%2F5SBARphvq0c%2Fi1mLrehnfJh3H6o11wF4KBoFfEzvH0Y%2FTP3zCRoao%2FOl8vTrxyOwk9AKm08y8tmeriZCMVQrhvSU7cQwg5JnphhqlAAJYpG22IFNLqDYmc1ZlMh6QHz9qGAWD7Z2nVGCg1FT%2F7ObmyaRGch0C6mAAkco9GsaBIZQIFNN4UK4sJJQgIEnGNIVKx2igUxCBWiIR%2FMLOHCFgwpQz5zhQPIJYvNXF1AwAL6ZMxTIyCFbUC3AwBEMacFPh2AgCQSxE5I%2FEOrSCQyBQFYsgOtho4JF8n8FQJgCBKpjYwUQcOO0SSqKdHu90BU8oQBVt%2FGFAslBpZvHBRQ4QkEB9oBxhgLsS9641kg4k5zBoHB0JuGcNdSrjsm8o67k2s3GwUYINQZ1muarcM52bDg29eLvSsdZNe03xi%2BkaNiF%2BG5RgPEB9Ez5p6VJ35ToF97aKof1SEHz0OCF04kxoa8XyuaqZnWPV%2Far7UYvCntERdFjDVuh0OiPYrY%2FFL7lsgVQGCTOxs7SmwtIsIcEz9XT8C2Xdc1kkPh35Qj1wAELPLdOw7dM1kTnLIRwFqhVtPVrOAvwzgVWffXgAtlQDUxH83CySFwKfXr5HGqDmH4UgWZ9hbDXCxngyt2yfrqxDaNC4IElHupuamCHB6g8PhituSi2Sgy9w6wSNmqWENhgiw3IeeCLDTJTQbROhN6EkD1t2SOAkOYs%2FGYc5F%2B58E3H%2BhRak4p9acuF6K3vg4XZbMzl1qWOb84y2vqrqv34dbTWUUJq%2FDrb6xi82ORe7LbxN3rkVYdN3S8MYQPO1LaDMLeCkTWGIPLxnSmEree4tUKomWVoIczFpSK7d2%2Bb5fHD5vzDt9nPxetDj%2FT6jn1BWIOVExS1EnIV3W8UuYJsFm4ifKBhGjb2iUe2i7UH8VDD2IseqBQHlUeKOhdAoQUKvGHIJwi8%2B9HctpZ2qA8Ow%2F%2BNJ2eE%2FyZg8X5dfTooUDg6%2BouZt9HUL6dXs5tT78uFPuzbm55esXXl66V%2FU8myxvfcF7%2Bl8oQOFHhuaYShACUTYyh8CG9AYIETFnjuaISxAGURldQ8JE0OIRrCFwIPLPHAdS0jDAhovFIGiKBRdpEAIj%2BnN%2Fjg3a9Q4YMRYDAbZ4xAOccMI740XWfh%2B%2BgCLN2DBZjXxhcsSX1dCVjiBlsQJvFnAilckFKbnmCGlLIkZYSUqDEfBEr0kcAJD5xAs97Y4QS6ZTKSvfIsX08IbpMbt6lhWQoNaKaAQdGnUDUNooIMaoMVAm6QuM5KGt5zQFtTIWi1wdBBsRPsjpIUZyh6Z1pvp4gABF1A8Kx2ArvsRJpj5zRHY6HXLWOikOYAZS7SHG09RYkVJHiSmyAmRMpjT5DAldYEoQAuGBEsxP4hhSe5CSNFsBB%2FAk548pogTgQH%2FsdghSezCY%2F9qMSKgEjnEOFKaoIQEXn4vcIDsMiCLx5Ejc7eYEHnmW8HczQ8BwUlXR%2FoSJKMg0LXh4QOmnZ9hPvZoiYSKb4L%2F43oerIiJ29kFwxfbfKv6DWSVLLHUSl%2B1Rdj8TZpUqA196inJC0ISRFhgommLQkGfh2%2BHQkyZOfKofxXtpeke9jTtewt20u2Xoi1MCG%2F9r0JU6UkTLyDjLcwkwxuTWF6s9C4Z0YFFS2O3I2kaSlcVaEkVuJCJmexQnTWXog1b3DBGctyh%2FLfehrryh%2BvlEhHyPCSfzPfcSf555y9YKNHwdmTZIW9s5eHlHelouHwYnpxYowu7lf6wFGyFv0uIJVaHNqQ6tdsEfZlbG1yX4sDsdIbRiU3XHZf%2BPeTdX%2Bl3%2B9Xft%2F%2FR3THdM9Dsx55nkNX6aE3m84qycmIiXQG8gEezFIDOI5LfOhVa4B3O5wVxlGzPvmO7CoMI6k7JVimU7hhpKkSNCp1ICMdJeKqbFRCIl%2Fcb64dVylbLsTaAYMShUKY7YRJXAgfs8JYmOBcvXcmTOJAtTXzXZ9McCgeK1cwG6ak5Uj1akK9GTy2Mt39%2Fv7YfSS3HZCEe5lIrgmbXcOf9Jbr35nKwVkAx%2Fe1V0m5GF7LR%2FBoS%2FTeDKtMAnWGOg%2FhoiUG%2BtaFL3klRtE7gcbkltmisct5gkwinz8CVQqWCUJSzbzejuEQJ1CJmXSyiku4tQElriQlW6l4%2BV1QbQsj6iRffyBhlHRf3QNGOvnbu3DKFMwBR4psHOm6oap9TdJNhNoBDPfrkYKrNdYAI4sMEIGwuEYqh5ym5VKl%2FcJpR9j6xVnatzMrdMvXc2u2oy6q36Gr4Io69ZJzOIK2ndFYVAALhSS13ptQdGwcqAmsj%2BArE5Kb%2Br93JhM8yJOAHdNcZZLc0DuWCSI8lM6FQlJF700o6RDkfVFeSZs5FzcyG4VuyHn%2B7gglNbI8JqHfPLzpP4zeevFRu%2Fk9QicfVm8XPSTF4VEXLqShaQVUGC2DElMrpqk5U8EazyqZFEsyKhZY9%2Fty92DqsOhKwfwjvW2EK5mVoGQNJrLE5L15uiZWFdu1rQCnH9c53%2FkTPbYWL%2BGTQ%2FTYpfyBBIsgUbLnsRPtjh3IHhHJ19fvRTvR0zjXQYJDj5mTTkXCKU2olPFNWesNMguWwT9QjC3D1mpcpHZoGXSkHKmon%2F6nF8GkIv1IR3pLaCo4yDlv6QBH7rKGJmoGzfZgktHHe2%2F8utmsrzR1%2FenB%2B%2Fdp1WlpNx7I%2BOjR%2FlDkALNV35unYSBcml37GkYncakmKYW4VNI0xhZja70J6rDYXsNhobcuRi4FGK9DTsam7%2B2QqwYuA2CkB6tDfjf88e3h8u70BB1PF8bAnAxetR6peKPpPyeebzY91xUTaBlTxDgiZIAiRhAk0glT1DEBtukHMwinq0k8NkqMbCABUXm%2BdplCC4qfxpAGWPolwn%2BMR8tdTgUGGGMAGDzLFwM1PD8xeJay0IHBs6DQWQ2eRWC3TnDyV7OxtbSTOT1iezoD4QMjZpmd%2BBLpQ%2B098fb0Aa76xcwmdlCAZszy1f6AHjiRD44Hl4FT%2FxSETf7LQTJP9vPjwp6%2FijiBcZyAU3QyECaYAErw3CM9P7FGC5lwEqqVg1kbBhXRASR1ZqFhWXSQ%2BgjJnop37SZsWWBEBwvAKl4QC%2BwixWYjKP7KdoaejNOqrfsZeng%2Bh7gSPYIWlmez1HLSR1ooJyh2kTan%2FxUznzBGRwjxpP%2BrXeNO%2BH%2Bc%2Fm%2FbbarIsP%2FAC1t05yWWdXdmCV%2Fa6d%2F%2Bw93wn%2FV%2Fkr3%2Benu9%2Fvx5dvxRTZz7v1EXEZdiN8YPBgwZhcQBh0hNcFqO1y%2BDwFaPI%2Bnlpk9MiqBj56AD7XPUAa%2BoIHVBsBhv4nv1sU7IBR6Pnufa1pSAxXuJPZihgGe8ATtiwOnvTb2l87RJSCgRgDIGgV7XBDBbV0Py1IEueHKm40QXJAo2w0K6%2BkrAgS4coLwFX50gQR7BoGgd7mZCLzAGApi14IyEMm4yh4RTbz0VWGCNBWD3HW8syNuxEAhDYIE1FoD9dryxUFbOlsPCl8toE5EAA1MwANWuvMEA8ZCDUM727PDafxgX1sT2H9bxqOhCCkQwYRaAfXXMEFFF4ebwEK6li0nGZhzjbuQhAJi%2Fi0%2FE26J74BB3ZoRiSXKnzGscO3M71AFxoas9XU2EAkgxUXmYqDOLDOveSE%2Bxl5d%2BJPwcGAQE6EIAKnbmTCvWmGwukgqUxQ6VOzPKKaydt%2BV%2F8sa7%2F34%2FfDj%2BRz4%2FvZ%2FDA16FI9iREuDJI4JwKC1%2FDoLE0CMUdAF7GHBlEWG1UEUchTiAKMSgPFoSaKCNBp48IoyGKko5RINILTBHAU8GEUZBVYopRIGgD3kAgSd7CDsJZeRhCgQg9yywwII34Mkbqsrww6b336k6%2B%2FF4c%2FX86fZB64Ft0piQKQ1iS6dpHeQnaVWXRZPPvW4JNAj8%2BG%2FL18OCUxdb1MNSW8wj4ewiPt6k9mYThNW%2B1izDL19BsUNtLIg9sk7%2F2Be0JQpjORHZGjYVsgeapj6gjtIAhwGLRY6GDIjM3MyOxCK9567dPPolEiCVx20nVhtCAzPjVGMQnyA0KQsd4rEhoTOb3yCTZqEn%2BMw9UAdQzSwrdVACDShwUUKnKzTVofsQguTZB4nABj9sQMQ3V1NRp42fWRNvPvCoep770mBH%2BH34drod%2But2jjLK7lnV4N9Ea9UdDKu28fEOw0ObBMiFfWrGQa6FFPlvmAfVbaQUFqptbztOHPrC6rXKGoYu4u5sVnB6KDTsInUPBT4Kkdgi2XngnURv73iEKSRv0r9hNcMUlgqUu9XdQAhj59X%2F53Pk90dvPSZvZIOLpPSzOf5t%2F4YegfeA61b9qhtr6Qt0WnVR8FcXronBLIcvVrLGd4pB5XqowtxSl7RMWpWzsbP05re%2BF2gTj0iwXhTB0DfLnJ8cHGQZgAOVSbUwHkh9HMU1MzeMHs5ce5IGM7fLuf9Wmbr%2B68MZEhfVR2wXtguEAbMQRq6x9ErQXbTFDvBdoNiZnX1o%2BlRw9n0X8d6x14Vm8OgNcfZpgwCgtjiffWghVoyCLB%2BWQ0JFkuy9oGHLdDpK0ACYLWbQAMNiYKlMBI2FH0Nm%2FoEo2%2BGJCqjCk7PGINMkYemOCCR4jbJN5Z3udAesiApgwmCVOwNXHYGqQuiJuqqBRtYcRAE7gwHFEZAvQTgSEWETaZAwl1bK2giU7IgSKOxghRLwlhXSrYiHCAgLwsuCqGYNC6JwtSDQ7FxhQXbRDelB28mCQChgZ0HK6iyEBdkflEAWhBVK4FsmmauoRFMYEG5b95QaBgSqJGFmQMzabIUwIHVVg1obFxUGBEIBMwNiQmUzwoDsF0ogA8IKJfAtk%2FXdXy3XFeaDVyrc6DD8ABEBNioL67GTXqAxJp1z%2BCGsx%2F6jpPPwQ222tilZ85OrxtUP8EU%2FvJY6xU84KNpV9IIO1oinSq%2BzFFP3iaveuLEUv1DN%2BtbsQskXvacnX7Hnv8Ox%2BZR0PgILY8%2BjGovBygFHrYuxiW1jVQnfC9YH9rCAHHiiaqirEB2clvQc%2BRoJEnKJ9fzbwqJsaSGi4newSonI6OO9N37dbNZXmrr%2B9OD9%2B7SCyjCPV2PHu7am1rPoRue8GRZSD3BPGY2pqjAgyExq2IoauAa3y7ltZVW5IVAup7PVMv5AzNIqoqT6xO1lT%2Fpi5m009cvp1ezm1PtyoQ%2F79gYYtBxAYuQ6sxQLJ%2BELIXm6kq%2FbmM5M8mRUGkg%2BKcePZe%2BL0p5Z%2Fg0K%2BVOWPzRplZX84VuuEXCK%2BnzaYkcSMDeP0UAK%2BJah0uxgWtrMtTZigiaXsw8OVOWq%2FMnQIAJBMnkicP9ECTZrGECTVLnCoGzatut5s9sgLBAYYI0BaI4qVwxAbkA4PXO1tIX4WYu%2F7og6ZuIvcwdGrrcQ8mce%2F0MTU7nKn6yQzaaSCYKQHUGoEgQh0LiPoIkdVAhCmBKAOQFn6gdZljt0fEyEusD1ps8iNsBgUXm6diIEQRAwo4VgkvjxeXLtOwRjsc2dpfQhUpCr9MWI0iacEB2hQ0wgJHQKjBB4w2U7dhb2MqxaPMxmFIevhU%2FIVAdAsyf5WoCyiNB3oOxl4AXMRVzAFgMgQcgXBGVxYUALDC6uBQIYIwDiBvkiAOqdDBDgLAaxM5gDgfAIGeEA4gf54qBsvc6zvSwqgsrqIgGEHYEAMYV8gQAFB9FuTmsu9myxJgYgppCr%2FFN28%2F2O2VXw%2BeAmQNqxmrILZ29IZ%2F3KfrXdW9u1R8uT8rFqovK79Z4hHQOBogKq2QBQQKPyG07ikL5aiIIvtiWq%2FmnKXsfWmanA%2BVckrqIn3bNeGKtb4ytnai8OH%2BeRVR6snp7suT2OMJFZ6yvHf7Zxq1lS%2FSe6zOrYcI0Guw%2BihV22D5pngEmZ8oLEbOfD99wnW%2Fc%2FqKZ2UNj%2FgKT0DT77HxaXiuzevW2Wxw%2Bb8w%2FfZj8Xrw89YPtD5bGs3zuXbpbANyWyWA%2Bh4ssUdcxBqb0yRdWKt60a2JW2ts8lV8L%2BCrKbrkWvHHwImm1BobtcZ0%2BW5uDTHnta26U55JVqIoCaOMm45N2JU8F9UkIIdcVJXMnAG2tZi7PG3HhaO4rYyZqWpibKRAhx1BYsfk55C9aAUgrlgt2twz31JHpBV3rBlZCOTNnY5kqAy6sQ2rK%2Byn%2BB%2Bx%2B0nRLwyfZJr%2BRKnh4%2F3V7%2BMs57q%2FmL%2B%2FkHukU91Wzslei6wc0twRa5pa5x45Z%2Bc8uF9s8nASZVFuZ%2B1I%2FgRSneQdOoHmGqUQYIHXiHTpKdoR6oGaISZ%2BdKHINGfM5qe0qlIodybdE22Fy2DVQMgqLZEQIKmW9ltgkBLsGDKJoYA9HEDmKEh0ABbRTw3KBz8fz99uz%2B6818dXlmma7qTU7vyvry57a1tOPB5ovDnIcgsq9sAcFzbw4ICDJ4ygARzSkWeOCIB64bc0BAkAFDBohg7qiAA084AOV6nN0GqFyvPQuay8Ro2eLteBO3xiETs5XL6O%2FXFnoVFXkSsy15gROwBmc63YTq%2FbDNyaf2yJtbvnIJBVqxRRkDIIftyIhYWJ4%2BQB4LkuEOCzKsCzopnFEw%2FdeOxSgKNChIH99AgyDZsxrKCFdNStB5aq%2BYmz25PVGOMqbTUMqwNdWO5I4h%2FEqM1SOSII6mXJ6U0hhxFUWaw9higNvb2koFVj9vUCwKSAl5FoOANfzM48WWtTPZCMcWbhF2GAVMDX9Ak3bk6Lcr9hTpgqY2xlcBZcn2bbXZCqt0AQL7tf5yrZQehbpqqYfrJZYlVkQhhd62wsbELyTXU0vl08dLbhmvEEjuOENmdEnsx5Ovc0uWIpnUgRETLgreGeg7GbOwPRPKj0IRFTKZudWJfsP4r4n3asdZkiT9KLivJtxXdrZ2yppCaGDXmQZMto67lVM4BLIQPWqsoQCtUeEMhdJ5dikUvlz69yGwwBoLwFwTZlgAFy%2BVJdIzJNzNBAjYggCad8wXBVCxfxEFp956KnDAGAdKTZeRGQ5IfzHpXrdnh9nMw5PjkXAaeQBC4%2Bg1wooBQkQ5ZfJXEvNE6WtPbZu3JNgD4kr0mHlYnmQQkKuAEOwoJ7ZAxXkvE1L9UNdrWkRB%2F6jXYEdFNXWKhOrjtRMvAMmdmdTBCVbC3jey95SwAJVVs%2Bp8h90%2FMA4YubY1T06WoAQYgwCqquYLAqgWI1jFPNr4SIimnWYgqNrc%2Fl6wkHeIZGbAgKqruQJDgRyEABizgCGIy7SEfuCJCbDCmisowMmH5QHiTp3oO05LYZiGz3cfV4p2e75ebhH8ssvHm%2F32ke62S9HKyJM3bUrV94ZUrfInikl8arF3s6K4JtXneWQjqsjOA7YS2ftC1tArKfEvhbBL4ROAGLM1CVvEADHBAyz0KyjmFtxQ7leo4p%2F3BUtIpYYlvAKzp9esm6SGJZLJjXz3JLI7CcI8wfyxZv7wQjWUssvbJmOazCI%2BeJ2NFTXPFwI90VBfw49Pj9pOG40gCLAL%2BsWi453J3%2BZih1YZgSefldTBBRYnBb5PVAjyUQEA78dZBTRrjqUz9lRuN%2FcUpbPJ4rmnCnvntVWMX3ns6pbkY1EI0irWnuw89BTPUJvtp54Sg%2BBbjz3VTewR4O1LVOroQWGpZAyWc4XiMSOipJ6Bq2zgDFIfakWBFsEarAymCiXIauVGok2g5P8KO1ppR9PTt5MrzWpZMHzLZKDtY0TkSDhiAPKr%2BWKADKdyRuPU%2By1MBh%2BTYXZuMoDdE1VudfMKykoA7gmTmj7ypNKl386nxLvP8esw5lDVsgy4MP%2BMVH%2BiR%2F8g8w%2BF0cL888RA9%2Baf1Pk58x9NIhT2n4f9N6C1Y3xDRmhItbAZDPWFURs2e2MzoMoQYTN4YqBzm6FV8ozBsFJhMXhYDL1zi6EJkpGvttD%2BPJIxUVfCYnSGge4tRrMlYLTGmR3kq6iPtk05bqa4t6dOG04zk44UhA0dhuRGK3uq4SnPfutOYXKpWc3saeP6abVfctPl94b%2FhBGznFuGoLHP1mrNRvz9nf3qBAq11lX8pKRrlkDvjsLkppnW5ANLwXfBC7wSr3ohXjOMba1Cqb2%2BjhcYCQe77dZNAIwSdj%2BM%2BX%2BN5HIQAZfYscphpKmPVVpOnZZNrl%2BcpX3r%2B1bB71zPrdmOmqvJYFei2wYY7ArOdZXKsbOTH9Tvxg9q1k%2BWlbyppprTDuhIUo1K%2FQAtzGyvM9bO2%2FI%2FeePdf78fPhz%2FI5%2Bf3s%2FTIQB590u6%2B3ntGbaq697bZPlj8OXk86%2B0rLFd5Ro034iW62XirRp9w2inZrLZ2KnGqtm5Rl6q%2BOcXL8LOCUIKPOXFHospL1y5JdxegSPeoZiRWasHUsjKlKDX49Fa2Mei36Mdd5Cdtz%2BHQEJgNz8mZ9HxQV3wdVkjZvN%2BFLA%2BITTAoTqOVqgms38EichYEUBtH5wVQbMCNWrus5Q1bURhct9Ie5KbdHGkMfeRJCWedHRBPXG0eTjW1ea29vYFvNcjCW5YsJU63ovadilMT1OMI03up%2F8Z2F%2BhmEeGbKQfY1iuv2lexuY24FQCFZ96ZA4%2BnfoHePL6fShrZ5vh74nXq7Fq%2FG%2FnFfs4XLS2TA6S8GjNqDkHsymtSNyzUeS2d2YVQbB0tP3KyBRo0hant9GozdCZJw1uHt70H0Zvvfio3fweoZMPq7eLHpLq4ji3gsbQsKPOkDvQsYC%2FLawNjBqrOeGhKabx21WqszVa5de5pGrAQ5IkKQiSIvA74YFyosylJRWh4ftSa3qdNDY%2BwsKXARU5KJTFHv7PDzzyXFW0OzVmJEqWp77fgKT6jO0lMQHfMjSJomK17s1%2BrdbVoYYjVot04QcoUuCyH3cRoU1bX1XDO%2BLTXXOU7Tr5mxQeziowATpa%2BXHv2GtRa0pPNSAVnwEmQ%2FkAVpuXYeFD5M9gPLfWhxEI%2FOdxcXcpaMBGVlevDY0KqwvhgJnVBSYNRgmg5kpAJAobKwaSDgEcdHDxC41EIQwIOE8YVBj7ZzCoMfb1we1y7r8Q6qBaHcg0nHBma3%2FgW4bCM0zKIjlIW%2Bx1F0NSyA3CdwxuCA2cgbinYHHo%2BwL%2BARk6E%2F%2FzY9ebPgvPgK0qgNZB8FUFzTIfnQ54l9UDLKPIYahxPglYKfQ8Ua4qww%2Bb3n%2Bn6uzH483V86fbB63X0%2BO8QRej38mImUjJ1I%2BYiS5sdlvO4CfeLAGzG2YT%2FKEjCWH4kzSDPf7ysIKfRoeoItaiG5LWElUm3tWg4MO4afEwOv6bkpsuvTfiJzRJwYDNgLlRyKCNA8yLEN%2FW%2F8W3IwJ8TEBx85U8PX66vfxlnPdW8xf38w90i7IN6Z2cFBJCclv9qyP9SMMmZjJqHvN%2FF1broepbzwr2E0bhBxgdFbKn%2Br11bpD0Y5qX4NC5AUuFDDPfnVQUAz%2F3QKUDX6mQYeC7kwqx%2BcMExkLwlQqbTkXpSI2KhGLb3AsKM%2BXqiItq3SQYCSn71cRIELR4BrC%2BE6sUy5pQshSKtl2WDPCOa98Y5YVYMKRZj8b8EzLVmtkvIqK124dfqWaampo0RYmsb80xH6u9NPErsZPm5Wr0It1dWd8eVq%2BD058n12e9Xo8MLS%2Fsqa%2F0R9f2dCU6CDl1EMp4%2BbIKVQxAfggy5d0dERAZ8LKoYGP4qbeehlV7ByIbgOGh8oztVCYACZ9GMgC8Y1LFx0nhzW2gvIXwGQq%2Fam4RF%2BHDFQHBwb%2BbCckzlLxRse%2BHi%2BTLxp4Hsg8kIKTPUPp%2B8Nmx%2BMs6hgPxhw9cyJ%2Bl%2FKs6n7jIHwrTE%2Fnf2q49Wub6xQUQmAFB69r9Q9Bk2vIQn1JjY0BJ4pUdksph418%2BY1jlEXWRLyQXCautR03KeL1G3cw6LbIB7AbbgdvObYrUNQw4qM8XOIuZt9HUL6dXs5tT78uFPuzbm31bz0MVTPh8OAVPibAGU7NxcH8lD0lToOSlao4AbSHQi%2Bfvt2f3X2%2Fmq8szy3RVb3J6B5AOcTmqoCH50JB4hlqBehwVAM00uhNASJD1fb2JN7bdyPsUQ8uKKKg8VTuRj5DQaXif4B0DAwMIIYumBMpSh1hH8KizEjoUcvh%2FreRbnOvgyCfcgzj0LMQPjSfje%2BibhQa7hZzsfD16Zfp4C3H7BDN%2BJc5uXcM5BVn1cr5eeWwtXkLxocqAMC12kpWDfB1y3zzgV%2BkEVk3E3NrWIUrJVukuQEcM0Wo9IYmsqyMuxRp1zcro6KAOSXIOdsHI735j3OVIjYzCqDV5nG6LEzznUAZwvLhUZPfubbM8fticf%2Fg2%2B7l4faitO2PABD00LBe24IuJUdvKvl5a6ZOM2WLUndLTsLHiilxd2kfQe9gPcJkABp%2FFVhVjNQ8f931FfwTRh4%2BYUdq6Dz18rJxvzxkB3iy559Jbw3%2Bgp8RDpWhVs4JolslIJeapCFCXsFOKYKfa1Opj9BSS%2B0DQAuXJmNFTYCO9YCoaMRVyfSA0rI5jRVUAI1NSqiJ5mrmtjOkDFpwFZSAkQ7d55MnhW4ZC2wAJixwSkn%2BICRps0QBRWHzRQLdzq5kd3RPHE08iItQ2H4lfqc%2BuMQKWJlkNFU%2FKq%2BnliUGJW9seEI4WCaqBYzUoERZ7s1iVJw%2BdZ2cqIZuPViuVazcFTbiGUNuGpggbpZDNtqEdmpK%2FKW7TZRtpchxv0x6KIB9iAMM4KjHLFYpJvT3OZNRHHta012fFiWC4M%2FUtlAgOVOwH2OAUmE%2Fz3gYJELSw7%2FeQpozVIIHXs9553xzZL%2BbzL8M5H77OlhdN6%2BN20h6h3xN9MZwy8GM1mSV%2BUCzF8K3oK8kcgmSaVnFmkKQ0T9bl101J6S6U6HoIbZ1dxCNzIgHG%2BW7449vD5d3pCTqeLoyBORm8amklRCdpZ3IiVjsNSdSSsUv%2FweCHOJr2sVhzfDeDz55Eb7j0WxcdbL8Sa%2FnTXY7wXuXfejRk1%2BKnS8W8E%2FHTk3%2F3AGg2cqYj50c60uXE2YkrRozmS%2BL5pMC3LpdXAAen2hXqhH7AHfXWLg45R7cWwpuHgGZxjmLVXjdqx4fjMOhdjo9q9vPHp%2FHRaX8Eqsuj%2FmxkK%2FiFalaMNoY2PqmyX81uyMpOX9%2FCneB%2FNWXmBD5nEMMXLTgLjkXhwOm%2FVl7yQW8RnoYgmYjk2VuI5OTzZCsayq1Kiy5WvUsvd0DrMy5hQqE0URjkAq3V0lvEeqAB6YNrhWYntQlxA6QcoMp3%2FAxQ42mAlTwCA1wxgPC%2BaGAGKCtITJ8f5IfF3b%2Fa7bdPT7cPtvPj9CswlK3K%2BtZLJGMmqe5z7CwWwW1Q6yXUCt6oTFyJXiwCSrPZMAU6leiKUmiA4BxTtMQalleqKCvfWo9rcuRMi%2FAiuqZrcybGlgtRCyiwXyRT3j4JHgKZOAT7nSIirEd7hZnoITxgVIH2alYZIlAmzThyOmslciqkTlPLjvpk%2By4Ig5uewFekmG3JNQWrmjH7fO0Zya3%2B3zs7ynhkaQK1h5ROsv9y7gU%2BfSZB%2Fy99uY6mIJz9Pw%3D%3D)



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
During most of the project's lifetime, both menu controllers inherited directly from Controller, which meant there was quite a lot of repeated code between the two. By creating a superclass that both controllers inherit from, the amount of repeated code was greatly reduced.
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
But there's a catch, it was not possible to cover every class and method, due to the fact that there are classes that we couldn't refactor to make dependency injection possible such as Arena Controller, which instantiates many other controllers inside of it, due to the design. This, along with other factors that accumulated as time went on, made full perfect coverage hard to achieve.

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
We all work according to our availability, either dividing tasks or working together on the same topic. There were, however, external factors that meant that the work was not equally distributed among the group members, which led to some communication problems. However, despite the circumstances, in the end the development went better. There was still a small difference in performance, but the problems were overcome due to the help that there was among the members. In the end, the result was as expected.

Afonso Osório - 60%; 
Inês Soares - 10%; 
Haochang Fu - 30%
