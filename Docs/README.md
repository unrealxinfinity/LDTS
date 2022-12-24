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
  <img src="Images/image.png"/>
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
<img src="Images/UmlArenaBuilder.png"/>
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
Since ImportantWall extends Wall, every method that works on Walls will work on ImportantWalls, and due to polymorphism, a Wall container can hold ImportantWalls. If we ever need a container that has both Walls and ImportantWalls, we can differentiate between them with down-casting.

### Various objects to instantiate
#### Problem in Context:
As we have many objects that we need to instantiate, it becomes difficult to maintain Rigidity of the game , having to make changes to all the components and functionalities everytime we add new type of objects.
#### The Pattern:
**_Factory Pattern_** was the chosen one. By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass, so we can add new features like new types of objects(ex: enemies or obstacles) without the need to worry about rigidity.
#### Implementation:
Products were created (abstract and concrete) that execute the decision made in the factory. At runtime we don't know who will be called, instead of having if's and else's in the client, we have all the decision logic in the factory.
<p align="center" justify="center">
<img src="Images/FactoryPatternEx.png"/>
</p>
<p align="center">
  <b><i>Fig 5. Factory pattern : Viewer model</i></b>
</p>
<p align="center" justify="center">
<img src="Images/FactoryPatternEx1.png"/>
</p>
<p align="center">
  <b><i>Fig 6. Factory pattern : Controller model </i></b>
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
  <b><i>Fig 7. Decorator Pattern : EditorViewer </i></b>
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
<img src="Images/Captura de ecrã de 2022-12-19 22-50-00.png"/>
</p>
<p align="center">
  <b><i>Fig 6. GUI implementation</i></b>
</p>

#### Consequences:
The Facade design pattern provides a unified interface to a set of interfaces in a subsystem. The Facade Pattern allows us to disconnect the client implementation from any subsystem. Thus, if we wanted to add new functionalities in the subsystem, it would only be necessary to change the Facade instead of changing several points of the system.
#### Find the related classes in:
- [Click here for GUI and LanternaGUI](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer/gui)


### All the related files:
- [Heres a link to the directory of all existing classes](https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/README/src/main/java/pt/up/fe/edu/dozer)

- [Heres a link to the whole UML diagram](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Diagrama_sem_nome21111.drawio#R7V1pU9tIt%2F41VDG3ypR2yR9ZEsIbSJhASDJfKGELcEa2PLYMOL%2F%2Bdmt399Hq7pYT91u35sbGFkLn6bOf5xzop9O384U7f74Kxp5%2FoCnjtwP97EDTNNPW0P%2FD76yTd2xzGL%2FztJiM4%2FfU%2FI2byS8veVNJ3l1Nxt4yfi95KwwCP5zMlxvfHgWzmTcKN95zF4vgdfO7j4G%2F%2BVvn7pNHvXEzcn363W%2BTcfgcv%2Btodv7%2BB2%2Fy9Jz%2BZtVK%2Fr6pm344%2BdXLZ3ccvBbe0t8d6KeLIAjjf03fTj0fP730uXy7WH%2FzL%2F%2B1zv%2F39%2FI%2F9%2BvJx9tPd4P4Yu%2FbfCX7ExbeLOx86eDSUG9vz2fnp%2Fbo%2FG5lnUz0%2FwZq8hheXH%2BVPLCTYOWPvUXyN4fr9EEuXydT352hVyePwSy8SX6iotej54k%2FvnTXwQrf3jJ0R%2F%2Bmr06eg8XkF%2Fq86ycfRj9ehAlONAtfbeL7p4EfLNAbsyD6BfmXbvDF0A8U9O7CW6KvXaePQSXeunLfNj546S7D5I1R4PvufDl5yG556i6eJrOTIAyDafKhhk85kcaLtwi9twLGkqd%2B7gVTL1ys0UeSn%2BpmAqDkCDlO8vo1x6NqJ%2B89F7BoJe%2B5yRF4yi6dixn9I5F0C6mnh6Ig9etgOQknwayN2F1%2F8jRD%2Fx6hB4YAo5%2FgZzJBR%2B84%2BUEYzLdGxx8FBUPfhIKaybiIBQvAgqZwA4NGgWEQ3TFScvox%2FjZ6BiQq0N8bRtJaBP96xOEFznOKFN97DEtxspy7o8ns6TL6zJmRv%2FMleQz4rQB999GP1PDzZDz2ZljGQeiGbixQLL15gG45ekzmCfo%2F9OBOlSPzwEQ3fopeq%2Flr9H%2F444vwNJihv8WdROL2EFpePYwYAAjVB6oeCakdtRrK3eAldgcU%2B1qKnafYzabHnZvYdUrslJT9SSS9WMqp06R2EvEUCcv3cpneYpGfDVRK7jotdx2Qse8%2BeH5mq%2FSzRfxZQvZ14t3E72T27C0mXMVuG83Erlrbi%2F36%2Fs36aQ9elx%2FN618j9fTD6u18oEJiP8HuuRd%2BP%2FxLHnqe0h82PPQ6AxN%2Fqc2OH28u%2FrPfD1aLZ%2F%2FzT%2FVGHejDcun%2FkNLnK31Vayh%2Bg5f40wtT4vf%2BW7n%2B8jDVpzkQTlCE7rl0MCDBsC0Ymvr7LMAA3rNOg%2BEWBUAeffJlwN82ykuFrOiAlA1AyqbGS8q0d%2F%2FNxV%2BTMmYTyWcJm0qZO5xkDvp4gJY%2F1Q6OT268xctk5OEXJ%2Bh5WD6W4MMC%2Fesp1nXJO%2Bkbly5O58zc9AfoZh7yDxMAeg6nKQa4PWrF2nzWAxWMo1ToYTNQo7BDbVBP%2B53vTT3Agyo5Y7o8Y3WCHxJnTIMyp6BedXiJ3aTEjrMm8yx7mjhQpenU%2FfWgqo%2FRVokzCAI6LzVr0PnS5OTfXXivuHASq81MHZRoTqkWOtsDIouO4tuGXjU3c2DQeiEOscYL9%2FXQi6GwROfn%2FOtFHmbdBeii%2B6ohiqDXGqqL7OztZJ4dvGWTrrWdu1Mv1RXSH%2BelFEzAIdc1QPSqwkD2I%2Bfk0xm62%2BnLj1vNfLe%2B%2FTUNBrSlwOogMQyHSBWg45LoBqQQbtGr1JJMipbkNrUhUnO08S3M1oE7oCxAxGgM0vQgYKwqwCw3EYN%2B9%2FHlZBlSKJEw6hlGaYTfG4zoph4ARreTqfeXFD5r4duOOOGDt2zRDsdZ8MtbpMpAehxsPA6baOsaQKJXTZEZQIt2OApRSIQCGYK0VwIWC0cCRAI%2FJUDHozj%2FL3UAWx0wTCuAlTqAV%2BUHljztQxZ0QFwEkiqgtQowG0OjZZ6Snwoobe2WWoB17mFIugJQoQJMSHJTA3QBuKAGsh5%2FqQlaawK7MTzapiC5aQK6%2FStu%2BZCKgLEi0OwmMYFQfyC9I1gRnAeu9Ae6aIFhY2zsij9g03kBSsrebHyMZ%2B7Qqwc%2FwMfxBL2VHGHVil%2B%2Bn%2BBfHD34QvfHIljNxt44%2BQF6eov1d%2FziyHCc9I0f%2BLNHmmmnb5y9FT9%2Fti6%2BuvYWE%2FSX48GiDoc2jHvayh9HWrHzxhvzg7T8ILW98Hw3nLwUvwhLLLncdRC11mZ6QlWcTT2hk%2BM9y2C1GHnJ93LJ05cyyeYYkxwbix8FdSkkaHdd%2BFhyiEiYZU9iC%2BRBaYly5KWWoQxb7YDQRr5gsoaNyB1NYSVxhTQy1KVKJM5Mng1mSP50eaq6oR7Ztu04luYYtpY%2Bgj9BvHRr296JlzquWUTXVp71B5%2B3OKHWFFHijJ9SlSPVwQxzlPuQPHwq2YjfVO5DMicgXO5QIrC73L23SRg7dGby6kfhJ7kjh1%2BsOWHF3imsqCrpenVWEirZTi4eLVC%2B6PdGy3C30KIbJFrsrmihLiUaLQ7tAV55sxUKqsNF4Psyp1SH3uY5pQEd4KWDDnX8IdxySkO4p7G9%2BGWzc634KTdC5GwZLH06nYyljx526Mlzz4w1iBp6UmjBa1Bnkc1t%2BCW9gw0nAZkCPEceNzQf%2FhXnkCtanGUmGT5N22SSQRxwyySrCpRKTnCQG4EUC0WzEHeiHqNn4pYOxkiAbAkQoP9UNEBg%2F1CaCLYdiI1MhAKZCF7TLtksBWgiJpmJ2OxMx9iQ6qCZOsjP1lYGA0JFGvJzQAWUg2xhMCRAuALEoJsUYIDwMxh0sxolZ2bF6Xb6uI8CcRbiZ5qd5IZqmiuirpR5AoJSRVmpe5%2FLSQZVVugqT6sWGdzlCZGJbZEpzjtFzINCn0jeFQL3iLTDQbEdxNBvP6wH%2F5wZ858P15dPn27uzUFG5bQjgKkXc%2BNksUZ5iQ0RU94gUla5JnNV6W%2FKIRhfky0g1XaNTYILnMPdKkMYDiGjYUdc1as03opI1aRhccghNLVr3wl9JeHybNeowKhBcaAcKYpWtDz4HdupMT%2BMWxQ19eNdMH5Zr18vTeP1033w9%2BMqI8fpwwMdkG0rRveGJvXINofZ%2F9K%2BhJy8RT8a2mKhprFVHV2q3WV%2Bj8rU76k3TwAYl%2FNgbRpfzi7n12fBl3PrduitczO7I5pvQLo1nTvuBqSvlcVMouAIUEq9d0dhED3OazfEdHwUPnky72WHHqArHQIy5UZdS7t2V8ELTq9Isj3WqymUzVMAE1qCM2wMkskwbTUl%2FCU%2BhTGzHsBaLAdXNnBReaDYT68xqDrDd0ynBi%2Bm%2BEm4s1By2rJUASoRG4KLini1moCyBxYVJSwWUuZsZG4auyZzgEox6RdoLnO5maotDjTi7Ns0DDRwRxnpK7PDAd2%2Fjql2k300x3JXRa2tN1iQVoBSZ1EIhG%2BZLi5goacbGqXUeUgd6hcSKvU0JUBIPQ67l5l%2Fv8F2mOysSHoCMNG9IpHBGhkAe4FgZNAOIEYGNpWTJQr%2BcAAAAyQODSQ8eMIj6%2Faqwwe3zVYmnSOInIRmsJCAYA0IvSEg%2BCkMOpWKAZEQHsGYyNiQpLbgCg4gmcwNHGANw6GbUzE4fO%2FF8z%2Btphk48DNJQKBEH1OyeorEAzs8OL1HGw26DuUyVOZBZtq4USd3BhllWA1ADUOYEmvphXGkeSiLCpwTDcBSTMG2gHYUMhBEx1xigDcGDIH6H8ZA2VZkhIHLxCmQMOANA7uhOeDnBpRxpSIYJLGBRAFvFAx7TzVBk2sJCk438k2HLdNPf0UPDt23ruYlHAkeduDRtd7TDlAkkXoTEWYkVHYDKsDmWMFQqbA2cVVjeXjbuPIhAcMbMHbfWQqQuBcDZrJMvRO6F%2B4kCHzPlftnmePBUPp2V0E63RgP2JSQYJBI4IUErW%2BXFSTijZEQWwmpGATCwezbCQWJezEcnlInNIeBrIkKwwXQUSkYF2XLqZ9yjxNGBtFlI7HBuvNK6d25LFsX9pTlvsrAkdfUJTw4wUPv29ccVqgOIikmbUsPADH7dkGHFfojGtUo4AIe3ZAg2BYETt%2BOJ0TBceni0YuZGy2bJkQuJzeix8CIBZAY3ByohgF4nODYLr%2B5XYCcA7daLUcLDx20VCPcxC9lw12TyU21OTQqxjkgHHBUDA1WBckOK%2BaChyY6IMFzm9lV4U5LvHPw9NlduKOwkLVGZyV7E%2F8bPajVQhbcOSsHqBuXl3IIvt36Pz68%2FdKfp9%2Bv35%2F6zo%2FxO8h1HCCv8SZc4JM7mXqHIfoPwoAfzJ5yOMQ%2F31tAFB0mhR86oN5cwaajLPREPoMberHrcHjrLaaTmetLdcFZXahQ1y4vRLxO3sJ%2FtHVw9%2BPu9v74f9r7s7vFgB4SjPHgB%2B745r8V%2BpsLsebxt9sUGu%2FRiUHP63HytFq4kb2R%2FiYnjOhAOgLECAPHA8RIWak81hkpIg5jL1M7TeaK0SPMWyiqkJMQVKc%2FlxBiDyGroeFhEbqCEKID1yyh9QmJ93gUsQ8lSDg%2Bvb34%2FEnigH3oClVGhJobkFY2XZy%2B0WiBH4EkoxKEC6gkIhYXZQoC4yJOeUtg9AAMqBQiFhhl3TgYGLdIwE1wITs5OUIEKpSITXmAHToYH9e%2BO2qmOSRCBKVAdGDPpmC4gCmQSJ3g9JgEyw6BRe%2FdXy3r%2Fhv5nisH2LhbF93q3TMtzZj6wdKTCOCOAKd3F7SsG2fhPS68pZxp544BQxXoY8I3DZDmJmzp8Ubek9UkpjsihC85dLvyJtMrlqAOHGijHrVkhSEMKhKZOH21ubA5emciFzY3UApa%2B8Cz6QJOfjqhLDeRtmlmaIjsQ%2BEtCQfmcIC6cwTDoWJSKJ75yHb1RnhI3pOA4AMIiGZVMCAq%2BvyTOY9NRBxknIrSanBCBciuKhYWOk1rkSqBZECsdLZD9ngz9DBtctmiMaQdTBXq%2Bc9aMjiAgyaywD2e02Ds%2BZmSuJWKoUYxZGdsq5U8Yuc99AY7gGVbN3PBA44jKHhuR96AeiJ0XJBIMgzLw2je53Szc3dPNUDR7mn8UAE0cgtWB2m2A%2FIer7A5KHiNV7F5qHMf9gUj%2FGABdHALhoVVlo3GCgMrCpmN7l6ibA8IqINbtKKAuM8IqbNf8awcDYf6QWHHrnKk2tkbYhY8V9vT2p26iYDRjWc1iVT7m7RQGe2AHpK74o00TdB%2BBzS5XdxsuF08v1T6weDxceltu4e3RBwN3No%2FfaM8JXTdUroJnbqS6TSTOTN5AnulpDw7yzPfCFx6Ke4CheKP7gLNLcSmdTBrbMOfAQSDPI7dgWDYQ2Rj8%2F85gmGhyXNer22bi9foqLjRA3bXhY8lPnLpPauKVvKbcoTE1%2ByKF7hLhvZCkWuOogzfb94WoctEdpdWCZKsxID4mFXgmKgWgzXz8MAXzFUg89hlGYrqQ7VVVwQkeRbhqKZ%2BvAvGL%2Bv166VpvH66D%2F5%2BXEEdsxFHDTYGmeCPV%2BNJcOXO3CdZ5GSPAagVghcG4MPfIESQlQzWYocaHiCx8xoTr2p3SBPWMlstUA9AdCNCbQHQNJsh4iYxCEV6CWkVOKIB5BoRaxbK4LAMvfnhFXoW5%2B7UQ88qpgPIK54HmI8EFz33WEvU1D1ZgQQiGxEKEp32HVCIFnoyhOQZQhpkzgAKIVVw5EJjEEIO77%2Fe%2Fu%2F1H8V7%2FXZz9fr58%2Fz4owGNfsdr6vOsQmI4iokG0qtIaInkzGZDnZGdv606p4QOaOm0XZG5BgGChzqneAkeXDWqwc2Sm35kJH9JTsYFAUDIKfjoNyhSyEwDa7FDTfSg3BmkGq7v36yf9uB1%2BdG8%2FjVSTz%2Bs3s5hDgf9INmgkDoDUb6hyjeQ%2BoA1MKBuKAgYLCY0YWRAQ1gJMjbH8dIRGwkK3qAw%2BgaFCjgKcOah0GidOo6bRMmyp7K8p5IVXoDd5YK9iorBXtl63ZMWAXaZC0YFZFoIMTPrs6WfY9OmWNgstuyJTS%2BccS9oQ%2FrpM%2BqIHZC5J4qGtmmTzUDTjyxDyf6nazZxZds4cpz8A8S91rbLZk%2FH3LiuOdy8DpNuWhCDQK%2FelTdblYwLS%2FKRzh01ZLeWBfDWgX3%2BaVKEufoB%2B%2FHSwQ9gUmyvqyUNzZDZHBk9jInCt0xPj6fex0Pqfbx3R2EQ3fW1G%2BKFYdknFrmDEr%2BD7uGh1GnZNEmvz5PQu0GixT99XbjzTfvF6yzaKnEUTYAHSIVqVCzYzG3n73%2Bv5o9fg%2FsfX78P3318Z18uwaUJhAiSmhUpgN1%2F3CY1VAJpPo3T41a%2B%2FnsV2J5hWcHbNPx58uX0838D2u%2FCNq%2BptZMFQRbkCMC%2BZdUB9R6DnB%2BIAjrlh3P9eIZg4i2zcKy4JjVdarRZCZQWMWxw1rYyiDAwOOGCHi%2BLqsSrBf5L383im43BETvx0YcUiQTWSIDKgUKRAE1GEzKWtSDGQocqgJDQeVkFyBvGQdEMHebo8Et2Vr6nHmJEEHrqVcgbxxCYL7yXSbBa5jCQAOAAALDmJxYBFW3GsfA90g34S6695AkJoItUMCTKkmWT5Y3ne6PQG5eC4iQIfM%2BdSVSwRoUFdI0IRUVpGRivoVtNH7zFuzicLDgNaS1AIoEhEiCiRbFIgEhJEiScFkLHQ7kimXf%2BQO3bgQTah%2FMppcRYIO0gdQJfHEBdQmJ1Au0zRNW0ZmlmycHbKc1MLnkYQnMnFgAD3WSAg9nd%2FeinY30%2BVW%2Ft0ftQf%2Fzv5wOwqzaeR8uTzO9mq6lUANUKIDtNW2WSQclzUwANGnxkApG12IENX6DYufHvKnS3H7mWWmYQD2onUBnBwW6o%2F%2FnxaSp0pTHbTi2hIBAKw4YGgSMU6JJStCg06hOUYBAJhmz1Vo9ooItNGA3JyhYJB6FwMIAUs2A40HkDuRGyDyjYQF5ZMBToyAFrhmjjmwSDSDBkjT09goFOIMhdweKB0DSdwBEIdGcCuDY8bkyk%2FysBwhUgUL8aL4AE3279Hx%2FefunP0%2B%2FX709958f43cChFUUUXSAxS10hFApQF5tYKNA5qAgKk6k0G0KhoEPUFmKhAPuS1747ks6kYDDoAp1JuDYNTaITMuex28fUNnb7qEeqKni3T2WpvvEYM95SZCQHKisxVjiGW44yO9bm78po9dpOMjvEMgh9SFxI7GYfWBjAXBY6qW1ms%2BQkcu04HjGHBVL7Q%2FuLs5wEe51UxvOc91Zeei%2BeH7%2FY2G0tGysb2CmdxQwWt53W8C2XLSUkIPFuPAmDhYQEf0hAOwDEQqJsMieHxN%2BriVQPArAALQYQiwW677pgIaSzwKybbtjAWQD3ALGgXoNFX9ZoPZmNFhFnSdJufXbxFGmDJPUpg9zmCmGnlwTB3cFlM3tjD0aFxANPPIhcGATjAWrBx6ydy81xjNvgMO%2FCjQcyJDb4YqPpViF%2B2KCrJNR4RuRNSNmzlj3E8yxY%2BO3yn3%2FkElKLmJHonFQcKjUXYrdSFhZmOwLN2kXDb5MwzlYbxjB5Ha8aVlQjeZ3nqvGLdeHFdpnq%2BJFXHTZjtzBEkKgZXSk2a8HIG0NQ8nHPFELtOe6sEExuwlxe6Jr%2F9W0dHt%2Bv33%2F4Pv93%2BXI%2FoL2%2BYyQI92Q1wQ21lFzl5B3DXEHOspsKHxjKho196pFttc8FwkMDYy%2FnrzIcVB4p5rkABuNX4A1DPgH27kcLzw29SB8cRv9N2Dmif1Ow2F9Xnw0KdIGOPrjKyarY6PLtAt1Uujx2n2fva7pe2EDBamoGuEEBKiYmUPgQ3YDEgiAsiNwgDGMBqiLqmXlIBywiNEQvJB544kHoDmEYEBCFUw4IPKS7TAFR5ALGP9j75SxiMCJyhTCMEajmmGMESdOfLJGPLsHSP1gATjixYEn760rAkgz3gjBJfiaRIgQpjdMT3JBSVqSMkRKTAoBAiX8kcSICJxCfHD%2BcQLdMR7KXgYv0hMxtCsttmkSVwgQGOWBQDBl0TYOooINavKbAx4XrvKVhnwPahgrBbAyGHpqdYHeUTnFGop%2FMmu0tkYBgCwiR3U7ghJ8sc2xd5mgt9KZtTAzKHKDMZZmjq6eo8IKEyOQmiAlZ8tgRJAhNa4JQAJeYyCzE7iFFZHITRorMQvwOOBGZ1wRxInPgvw1WRGY2YcqRSqxIiPQOEaFJTRAisg6%2FU3gAlmWIxYPs0dkZLFgi6%2B1gjUYoSVEy9aEeKYp9sDH1oagHbac%2BCpRHSnIXCeWRcqTp2gF3yqPiIEll9rjIjVTZNtdmSIEV79FAT0cQ0ibCFBNtRxJs8jqCJxIa7BAuQDmYRyqkgGQMmbG7fI7wqlYCuQA9xSSgZ5v8oVdEFEhgmvIq94MoooJnKd0Ale2QSi%2FUcMYFidhdFz6WmIXSGzbg%2B83xGV%2BQKVo1yCsrR%2BsfOQylEfJ1lI5Aqb0QZ9UDLqfcN2EajIRJzjuKFmaqvVrbkcQFUmmr0oOkWSlzQ2ckVupCjmCxQsnXnRBrrTHXepR%2F7WlsKn%2Byr8duSIvJTP7tIp2t5F%2FwD%2FHumw3%2FUMnpWcX4h8Glod7ens%2FOT%2B3R%2Bd3KOpnoOaFEH5DKLA5rSA0bDrS39Q%2FVkhsu9VuJz6eLMUs%2FP6z8PB8HFKCArToP20X%2BfaE35xJWtJQQJWMLj1%2FyADiJS5KirTPAGwZAnKiEYRy1Y3Xoya7CMFL6U4JlOkUYRtoqQbtSB3LSUe2C5L50FLxCpj9sGUQ4PCRd7a7YUhXRTpsMrHNml22laek1F%2BItTKivQQqzmzCpC5GsUJyFCdKA7pkwqQPV1c%2Fr%2B2SCHJ687GzO%2FWYWaoDV9b928KgtzA2HtBG%2Fvn%2BzftqD1%2BVH8%2FrXSD39sHo7H6g9llFID1HVuhK%2Fkak3VWmoLLYNlLNbLk3ckw4G8QU%2BbiVIS9pddxV37xRzPWpNnqcdqLmkdDgqR5UULUVU3rgMSF2JU56HQmN6y3zR2CdPKpcY%2BbdAle6QSrFhv8KWgbMgUPXJtckjNubpk5NI6GxoO2OKmdi79sBsV%2BRQFKLIMTTEtl9VOm%2B1Oi59SL3kakj1oLMKIugr8QYfna1TKfQl%2FaMFVLVtJS3lUsimZV%2BfJ6F3M3cjdLwu3PmW%2Bqw5e4FOSjPrTClAB9pCyWKJCywUOoO2b0KxCKpkB1itI1YmdCLs%2F%2FZMJmSgqCg9yyS9oT2WCdluuQNCofNS%2ByaUjCB%2BV5RXSsEh2MU07A0Xc7DhdLZxMgurJ%2BJLpgnIIzWdSRCxeQL2WJUkbKvdptxjXtI2zQ1E2h391SHRMKoKTnqbIhvCMtBp6ubky3Co9Y%2B6HmvVOuGcdZ0VMBSnEpW8wUR3U%2B2bm%2B2QYxR9GyoZ%2Bag60b07UHuXSrsmgG2mtNo91dpaoNWjlnQ0k5Bj174Bh%2FAnB5rglh6LDrR2AwC91Hh1gzygjtFNsPSlqEPMW7JdJyz%2BSMmSlaotJEtdSrhku8Z8f6Jkh0NWgqWuJFyunUrPfQuSYynQ0SnZdm1aADS7YOG2G%2FBoJdw8ntWL0azASBauK3eZlOPZrWCQ%2Fnfn0rJK0jSIx1PXQY%2Bt%2BBVMa4PaA2fpBFN7wEhLRxM2issgQVefqToiuTLoXFx2iMLmgCJ14I2%2BXjobitArdPeVAS%2Fnt1GdjSyf5hgCdePw%2Fuvt%2F17%2FUbzXbzdXr58%2Fz48%2FGhnTT31zjtGjc2WRWs5Q7Y6IJU25LnhDLrjuihdiE%2BThsoi6gTy8B14c8sBu65Qdo4i818lb%2BI%2B2Du5%2B3N3eH%2F9Pe392t0AWrT%2FgGaS7plvO7wo8OsW2b4lPsqowMPouZQO7qvZNKDbpDBt9J6PtrnmNrUqAplIMmpCzYJp9q%2Bg%2BNa9JwsLqPC5fCjBBmtem8yn7dsjJftYBtJue1yH%2Fevvz%2B%2F3F17NT9Xi2tE%2Bc6cmLOaAVb8ymfhog5z3wfbnRiy8iBlTFCbDFKgSJjLGfOSZA2lO802W2miY0%2FJIClwZE5fnaZqsXKH4WpLew9EuE%2F5Cs6riYSQxwxgCwyEssBhp4fnKRF2OhA4u8QKHzWuSlgnQi%2BOSv5mM39FLe85cAXUaeeLbCB1Z2cTvxJdKHWo%2Bw9JGqPyFVv%2BTA5wcFaGeXWO0P6IFT7eD45AI79Y84bEIvT9L9XJ8flt7iRcYJnOMEKgMLhAkOgBKyEsXOT2zQqiadhGrl4DSGQUV0AEmdW2hYFh1kPkK693ev3YSahfBssJBylNZhgV%2Bk2KmH5s%2FqiRpozJpYnLoLscvPwuLskxWjmISv8qN2ROxUlyNJZdGYl6BvobfrHEnJzTY6lTapzfjT1Vapz15qNGSJpiMWNLIuS6GKNxjYrl0po%2F7Ke4NYU3%2BBvT27pTkYGgzgUvy2gcCAoUPFJCqU9SMxcaFKVhRB6nbYLeSVSRzSamSQHdQCMKI4obbcuC8xA42Q6iPHPGbkV02QmYKtMwXtxQ4VkTilCmAtQNsGrAWm6FglNqKQLXgIAt9zZ%2FLws0aB1dgacIKBCpz%2BwSwIJ4%2FrNHMcVRPu9jlfxE38UF1JqPiHdFkJa4HHyWycaoFUtebaIPmJhANrOIC1JbHqQIGcgZNNw%2FB1XjANUjFwQYIOtBsIRkJZLaGAhLPgdSaxwBsLlsBKQgkWtHosYGFILPDGAtCQLBoLZe2nBSx8uUA3IsHAO3Og9u0%2BqgqUkj6J5OzND6%2FQwzh3px56WMejKDzXTsMJfp3gwg9mTxIlnFGSbgzpL8EkJ0tUipvDphW50MkSrV13AJPxMWK6VxU5OlY5StELeSTZMkZymTVm1yBrDKpN3AznMpPWrkjNb19HpcbckQJjbRtJY7GTde2mtKHbbhYasN5zVVUWLmDqLPiVFS7b1S23K0gCJuePqlEOqIHzHShSZgaTyEw%2B0PXrzaxkChBfepIZTioP2E4WKkswUZaSGk8WXhRgJFNv3mw1lfJnLP%2BmFUuO8qfTUIOi9GPhF8AgIcAWAtAQnOByZQMKZtmswFjsmIepkdwZeAMguRK4ZrI2zSQzS1yVgdBqJQiL0qFInIqOwgVZlBCAA5G1Slg90H4BgQOoUImHJhWJBtZoEFmthNFQVbiO0CAbGLijQGSdEkZBVSNLhAJZpBSRNBBZo4SdhLISZQYEoMVNYoEHFkRWIg399sN68M%2BZMf%2F5cH359Onm3hyA5EnlhYqtWMDBkld1wYt%2B7k2LWyDwk7%2BtdjdQhwEsZotGFDL1TJIeNt8zQgxbNRzULC%2BGbFElA7FHF8mOkaBdOYklqMphUjstIB9lCKgjlazMMkxnlWQ0C6WuYo3jjHx%2FTy1U8TgoNGIqz99W9Q4IHhyTnXTaC6MDmOEEa2ASIZwRAlVEBCOE7uIBZzlTgMhxTpH4gMolgvFBk4ZgfBSYYlNk7DNXnBAwgEUUXmgAb7nBTg9ZOmMtdaiVBpI6t0YaoDtvcFDXoK0fy9ZsweoBqqIINhZQpkyPovwoNozi1QgzT66srgoFB1RUEWo7mtBJCmEfq3qgO9LiS5GBdqYfa8BGxKjJd2AYJvybWHX5wrDqmpFlNoXAKyVbHa%2FxYMVilZQdUOx5XXfaUIMJDVcEi0vKpi0sezyaNNCpZd8a7bPymk2CpdJutfNWWoH5Cbd7PLkGIcqu1RRq7Y0qyuyYSvWNDcE%2FsOnHs7%2BDr1Gjk3J7p1TIMSeR846wUKBeQcvHMhhPXtA%2Fn%2BI0QPzWQ%2FpGTp%2BvZD9bkJ9GN%2FQAvAdct%2BpXXbshkues6qLgr964JoGyArx4iZqeaHNoUWceMORuM5e1RnuV78aTMFjcoDDQox6SrLMyhMOQOvkqDQdNA%2BDAZGMajAfay4sTG3M%2FSh%2B8871pls24CRforTJ9%2FcfnM2hcVB%2BxbcqpIAy4pTCAaiolZZn%2FZi12oEYKip3b2Yd4DvDZR%2B7r3cR73eC5jN%2BQZ581CACaS8FnH1qekKAgr4YXkCBL5HVbUhhBA6DA5AYNMGIHlpvH0Fii%2BDb3D2SjuEhUQLNlgjUGXTaNuu9kICGKOl8jklBQWjIrhxQxYfOqpdtlcQSlKqSeaKoaWLRlgijgZzCgOALyJShHIk7ZxBokKqaX5m0kSrZECRR28EIJeMsAf03SnSstiCgLYjgNLIgu1IJAVGnSgmyjG7KDtpUFgVDAz4KUNVpJC7I7KIEsCC%2BUwLdMZ67ipm1pQIRt79IbGBCotMXNgDiNsxXSgDRVDUZjXFQYEAgF3AyIA7XNSQOyWyiBDAgvlMC3TA98fHN9X5oPUaVwu8fwA0QESI0jrcdWeoHFwkfB4Ye0HruPkt7DD6PdgoB09Xih79Y6IJePM%2BrCre2tTZ6wcqQourWhg03qqbLjMiHUfeqqt6YyIS%2FUsGs%2Bv1D6weDxESn24mfEddan%2BCX5Tm7cF0l1wjlsVYipIVuhHQ8V6r51eCkTk66Z%2BN6L539a5Y13%2BzyB3tCMZIdqKxITSPLczIjZwIzIrjvWYoeYScADz0vqdL%2FVEin%2B2wA5A16hleZFRhgcpA9tlRZ76NttBGIz1omcPecgH%2B1Uj7T2%2B6WQJBbr78UXhSlR%2FDK%2FVPRq%2BznRSnNZHBOFPzhs6BHzcHh1YgNRNn3WfoMV4fHaWjOXl5mzShfk9m1iS9UIKkYbmNsQOrGVYpuMINBbh1N3%2FYDXSM68Vyyk1cPId5foBt7%2FVT4qNUnfeL8KVwv87feeG%2F8rm6Wa9DlJpZKLmCARgLQPLAgKYWIFOoWM84Te4u4Cd8qfrCbgJni5i6tz6EZO3g6B2A3sZErdBPYYALcsPMUZY4QEcoii%2BLb07WqYYJhkj3k1tmnqx7tg%2FLJev16axuun%2B%2BDvxxU0THe8Gk%2BCK3fmPsnUjuA9spB6gJmBDAb6AQYEzFK6xG7LTbjwXJziiSByMZuvwuQtuX1jEx%2FVZ20nSWuX82BtGl%2FOLufXZ8GXc%2Bt26K2BvZ0YDCN%2FMs8MxGn0QkqereRFktGCkqeriljy6Ti1goTozV10a1LyjCUvkmYWvmWZ422T42Uk9saEsgySvPAtQ0O1eLPK3HfXctuWkLMPLuETqvbpcCAGQUoaih0%2FOTzLGwYi2WNBGJRt6PSDYH6DQwGJAd4YEEkSC2IAcgOiTVur0JPi5y3%2BputsuIm%2FzB0Y%2BcFSyp975A9t2hMqf7rom%2FPLy6Qgv6SgQSUFoaUDYAPA0OCEBbqgGi8diIYOMqY1aARhfxVB5aHayY4vOA8EJ4ImMxRfu%2F5tvF4i8gxnTzIs5IKBpu1f%2FDBAR4XR5rLzq1j2GxUjiQEeGICygmIxANeFHp6mVygeGMdAeAgC33NnUvqMpQ8mB4WKX26bapMTZiT1po4fg5QweMdgq8hBNF8YeXuH%2BcK56LUMCvlqASgtKNYIlOWEUAjlhdgZXMjMAGcQQHlBsSAoywzhxCDyCSUCOCMASg2KRQA0EIARMFmeJP5gAQTSKeSUGoBShGJxADFfYRw8eeGmIqjsKZRA2BIIaTNgf0CAwoOTOEW4CKVB4J0j7NsvzOob%2Bztxo5O7HR2BEzewUCDtTMiE25LPHVneSbJOD0ybeNxNp9oMcv2hQS48YrREjfpF6S2z2nIGF%2FvpyO4Sj%2FTfeL43CousMQSC5HBQ16VqJrmpUTcAx96GDofGSWWYdLEnQkHMEXTlzVZS%2FuyGwwj5G8CmNW50lLD4aTOO3LixN1p4U%2FTXn64W%2BCGcTZ4m0qdr59OZzeHRAyslfMtlPYAXM4kH8XgQyT8J3zKU9iOWXp55o2DhIlOBPle5AJNAh4hxbIXkNwD4G7kttoSfKJ1Gi6ztF8%2BVY9gsPS2LmMQ3gNAsm9YX42jBHVULJPnLycxbHj4sYmV6snp89BbeOMZErmQvJ%2BjZJkcrHcqS5I2NVC%2BLHiwQLfxaMekxTErKIAFPkXJn7C6fo2BerYryU%2F6dhCQnYd%2FJ2HZg6p2MZufIcDJunZi1B6ndg2qynegVydzTThUUGXiWF7rmf31bh8f36%2Fcfvs%2F%2FXb7cp73MRQKeymPZnJJS1TY3ng9MwKowWwhP5Jg6U%2FQMDHPztg0yLVLLSpleifgraJJKVhkKC%2BK53fd0FpWEaoqA%2BsQYO5ImWJxQrLln4tTJDFDn7CR1JZvkq%2BUtzgbrmFlzxClFC3VUTQ%2B3hUGp6Izqg6jNIE%2BubZsdQUNdSTBobJGk1Llnoyh6ATmD2Ih35hZUNx0eRRsKdniqkjdFj0f5%2Bu9VYHuGZQVv0%2FDnyZfTz%2F8N0pRzLZJzihpu%2Fo2qDDcBaZHEgY0pCDWz5kpbsG4zQz%2BUUOGGfiFUmLUOttPUAOcOtmXZhJHkh0BjEzVZlNcaf07NhXbPvQZ2GW5shmiejJIjfwdNEwyZlAlLrFVhvOiIZoQR7Ef%2B4AzVPKIDzNJQ18lLmXKq0nr27zf2Z8uBj60HPtqLXSDRO3zHZRMfyFhFdqDY0gnaBXnyt4SASLZ32EWDks0JBmJ1T7HDShSwRoHTt%2F53oBb%2F7um5QonAjHn9CxGzKaBEUIxMLrXZ8ePNxX%2F2%2B8Fq8ex%2F%2FqneqINh8gfvSGrQUDejHqdrKEJmBm3BeV4H6kKtaBuY71bbwIBISahAk5bYroEhrZ9LtLCM0RgCwSSBoEDTuSBhIy8dPYSpOTw%2F6s7KbPS79LU00lVGOjtXvw8781CrN9IySGMt9qbUzNym8ssaM1fzsRt6yXE%2FTP6%2FXP7LEQpQsCZWA7SroLVw0w1LJ930rNIlxk2vBP%2BuuOlEol%2FNSght%2FXSboH1TVU7zRVaa2iNvmet80ZDOLGHKkMmozDuR3a4dHVUSkdkEUVFJ8Vo5BE8HK%2B0qnds1h%2BQqzDI2VNhAOVJQVL0DOqxpzZ1DhdMiWgg75xVslYyHGtY42%2Bor8held8xVXakKnf%2Fat4lmCiqAtyN0oFlV5ERz%2Fsi39jjqfRfOqUFVgcpN5fJk1QFztNGjrtRYBNbKv2nrYNYBs9mpDSYgWTVgmaTvQJ7lxu3FpHWg8qE70IClAty2cc9Lt3l3mVRta2KQCijrgK6rfOm8uK5VkN%2FsD9dK2VFoqpYGpF7iOfdCdbdnrklbveSQF2rYF9rWaR2QrdXpHYNea%2F719OPC2v5UjdaBcTlJcn5w0HdkX%2FLAATg%2FwLlu1eEWnqf6jagjTYMXL2n4SJP0csi%2FTT45P1u%2FD%2BuDCux%2FTdg9MzhgWUi%2BB95Q6J3wQQUXQW5C4csFug%2BJBd5YANj%2FuWEh%2BHbr%2F%2Fjw9kt%2Fnn6%2Ffn%2FqOz%2FG74A9wCQSvs4lCPiCAFoCIBYF0AT2JgrOgteZxAFnHACNR2JxQPuLKdurNz%2FMt4SdHo%2Bizg7tNJxMC8sBXiQuuODCFOg8wvoBAkZ55uSPzM9Ts1wDo2uJjUoiUFdil6CH5UnHAoWZQJkkFZQ0MMj0lwNZAIiRKFsWwf6o07EBzhmMCpjYGBGpAMv%2Bav7qQ7dV0oAXPxV8yw0y5rIPlbXYoQQBJHYGfajwHYNbQGp8QBkYcMMD0J4hVg0ApQQEiJHvuYv0dMlUEWcQAIODgkEA9ejo2DVYIyTEa%2BNyEMTu5H6vDi56yBo3YGRMEb0hQ4e8BIyMiFMiHVuQaQKRoAA2Cgp2Hdu19LHhjevGbcqxP6PI51Up2vpGDq1DOoRfo4Yz7J77qLsUq1YN%2BqYdpfreVMOs%2FMbWPckwBtp1S7YZyS8iu27Kpx2yi4CtRPaupO%2FY9RqhS6nEpUi%2BXs75uzR%2FyAEx%2BAFuTIfpTg1uGE9WVBUmdgVLqsEMS2Rr7sBq2FDLDEsahaXYeU9Du1Mc58lcMO9cMNnBqGb1hrqtUSx4CGBo0GUCnAt2Y4KojUhPkkY18OOzo7YVEwEEAX5Rf4M5ZJkBZi12iIkAPPm8pA5uAj%2FdSPjlraP6sUz58UABREIg9vC34wpjs55E67afJB8ATvaTZCPCYti6G0f3lQeu6ZQGEX%2BoZsWi4K2Xk5DdCk737STULt3O60ksh3gE5EQbk9EKUFgGHX0VnKCTYOWP5ZQFFyfZJnNHQ2g6CSJqYbFaEwYDVBtrVBY51Q6OFeC%2F0o5W2tHs9O0knRd8y3SIjTBSUh2RvhQPDDTl9uKHATqQKhiNs%2BCXNBliTIbTu8kAdkRWudXtu2krAbgjOVSVoN3Uh918SpKQgLwO5%2BypUVb7luafk%2BpP9ehvZP6hMFqaf5EY6N%2F80zq%2FYP5vIyUl7b8I%2B28Dg%2FmCQ0ZoBYu0GRz1hd0YNjtjM6CeEGkzRGKgd5thVuYZv7lYhtJi8LcYVu8Ww5RJRrHawvz9koypupIWozcM9G8x2i3r%2Ft02z1ZOqzXn3dRVYgdT1Xa%2BbaunJlnyHHaeGjd0MpRpWD1t3TltDEtuuvzeyG%2FYSZazhhePf7XWbMf6%2BGdyF1AoNDv379OSbtj8vD0K05vm2o1vtmt6qe2tThpb1INCV8tR9Vr6dhir7UJJ8w07A0bKwSb12BZgVIj74Zz%2FN%2Blczr7RyQ9Uas4G4PrlRSgPSgXcpMTfD2o3SZa3vBmOUdAO6pFi2JX6Ab9g17n2OnkL%2F9HWwd2Pu9v74%2F9p78%2FuFplXVXS%2FlK%2F%2FXgW2Z1hW8DYNf558Of38X9bW2K1zDaK8YuV6OeSQxjBdfd5WzeR06ZnGajizRl9q88%2FfvAg%2FJyijmBM7jqlZ5iawh7pAYJdQWdntrSG7aIAAktPR8JFDlA0XPGztgtUMUJK31fLjjPdVlZwEOh7YN4utEtEA0BEj1FyrOsxH5o0lH5nQzDfpyIHmmdcq3BJowHxkD%2B7SO5ZzaN0ym%2Fl5%2B33S2yrIMkLIWU6iMRe8wKW4JbcMdk9FvlikjqMTnxGTyRIHZ0UgcjNuyT23a59lFtwr%2BUhZnMQb2hlXQpsZsywjeKQoaZwfX9BK0wD9RUepua0vu6STwY0XC5Eza2mShkecZZHT9F33nQ1McvqNvFLjiTVVI8hlyKwnx%2FAfmFL6uqSZ2VgPc7bIee8KH1OqTGoJmVqXHRVnqG0AIGVX5XEAqOSX2rXgY9LEFLzKjvRNJ40vpWeK%2Fkb6BEv%2FHLISRn5DSKVy5Jx8OkM2d%2Fry41Yz361vf02DAdQWWn5A%2F8hC5ZAEm9m1NKQqJDRsTnuMqXu2GS8yBsHS04ZVO%2Fd50jl7q4sT1A6dRWV8ff9m%2FbQHr8uP5vWvkXr6YfV2PlCVpjhONQcyM7ZJGGSOxQiLSPx2hTWZomlIFtUW0%2BTt6jVKtfLj%2FWnUVNdTeUUcKsLktLJvtmP20LRghNcFimmCYZtAERa%2BBqjIk405m0NMXlNML1s%2BFmaSRLSe8lBa5hBqz9hO5hLhW4aKobHoH1Kxn3mjYOGGSJKacu2GyHLN0s%2Bg3%2FlQio6CEeV10ixoghnaB0QaB3YPUPbUaapOOph6185OlUoyUIVhRnad%2Fk26CGcV2CgRb4%2B5m3ivcniFnWpQDZJONENSUVkYQu0wlK89GS%2Fc18MYBOh5nH%2B9kDxyDe2t1RgUFfYWQgA3ewvQFcfV2vbHX1b1W6sEOhECuObwljGdQVUPRgSdj8ZV%2FZcYD%2Foxjs%2FuLkBwSHUAn62d3CoG3zLc0oFH1dAjx8NqCAE34QK9kNJnLf2my8X4SZ%2BOHQYPqwnmTERiL7qFJ8m7EgOMMQBtHReLASg1Q0hZ9vKwFju4Z5zTYkH4lqGxjCgSSCaUl4coEEAn5HYyRT8%2F9oPZ01%2Fy%2BDPHgciNYfA9tyt19rkcyhg6B5sFL6NuwJkxs3Sl1IuVMUO%2F%2FbAe%2FHNmzH8%2BXF8%2Bfbq5NwcDKykU9jHuQKfIqBps8xQZxePEb2c2%2FMTbVVy3w2yKP%2FVIUY0i%2FpQjxbTr8Ndh5FQIZoHeMviDPULWInvgbds%2B0mzN0nRraA%2FRzzsC2CEbT3RyZxCrHK9F%2FiY7mXkpvTfqG6aiE2eIQ1ZYp9NCAk7U5mmq1eVCx7fBxwRMYl5qs%2BPHm4v%2F7PeD1eLZ%2F%2FxTvVEHqtZh4xq7c0NBSOuq6i3VOjIJen9OLWfod21SgQwMq%2FasEN%2BwN77A6ajQ%2BbK9G1qjShtZzVPA3BosFTqM3Tup6KRUTKCLSqxU6Chz76SikmVeB%2BCwEysVPrQqKECLGxAT2zzAcxpatasLjFGI7z4Hg7W03lIb1aVjGrvSC0HWmLrOtatOzYA8K7uv2ODvqbgvs%2BLznIw%2B7z0Bv0OXjekMNx682tmtJK%2FUEFrMpCnb%2B2lvobs0ySsJliawA%2BzSe%2FH8uK%2Fhyput8BWQBlJGqwWWkY%2Bf8mrp%2Bd5yeZR1Gy7SPsIL%2FLe9utEXFp4bIjkjMxCg%2F0zdmYuEh149e5EkJ%2FEPffzr8OfdGX65dF%2BSD03Tb8ZfQC6Mhz%2F2sIp%2BBX7r2Y2%2BgR4ChtAiePC9Kf7IGF8ymEd11%2FTb6F5WC%2Fyv8crbvPDCS%2BwZ%2BlQQF%2BqUBy%2F%2BMvoT8K8IZv4a%2FbHKTRBLRlOi1tobdK%2BLIwr9y2d37kXQD6NiTdKRoZS4UgI6LVWFQJkJtU5AOxnJaasuftTFavSsfL10v9%2BvXk7O%2Fj29ejcYDGjcnXsz5M2MMOYkU4YgpgyNnPkxoDY7yMFWHY0TMuBlzcvQm58Fr7Oo1R3%2FWLbYFfFQeca26rCDhM%2BioAbeMe1bJB016xvsNUjhcxR%2BFXuwEOHD7VT44H%2BdS8lzlDzQRyNW8mXLx7DssQSk9DlKH2qnESv%2BMmYcLP7ogUv585R%2F1biwEPlD%2BaFU%2Fjco0hyFBV4kCQRuQDD7dv9UaD9MeW6JERsAzrVrZHeKkZEkiaEDqPKIeqF4NajQsPPCB5IftXHLSIcsF4wrrRWuaos2hbY6yySAow7FAgck9dmxJblMwUQ2NukNmayYgakdKfsfmQBnKVD6Ug0XcdQLFL1cBEFY%2FPjCnT9fBWMPf%2BL%2FAQ%3D%3D)



## Known Code Smells
- #### **Large Class**
Some classes are significantly larger than others, mainly due to holding a lot of data, like Arena.
- #### **Parallel Hierarchy**
When we create a class to extend a functionality, we find the need to create more classes to complement the application.
For exemple , such problem is found when we want to create a new object that moves but we need to create another controller specific to the object if it is necessary (walls don't need controllers, for example), the viewer to draw it, and the corresponding editor state.
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
- **White box testing** : This type of testing was used in order to test the behaviour and the internal structure of the methods we wanted to test, in order to assure that within the methods we want to test everything went accoring to the expectations. We used techniquegs such as **path coverage**, to assure every method inside a method was invoked at least once, and **statement coverage** to test whether all the statements were covered.
All of this was possile thanks to Mockito and Junit!
But there´s a catch, it was not possible to cover every class and method, due to the nature of the MVC model because it makes testing more difficult. And with other factors accumulated like snowball effect makes the full coverage almost impossible!

### Screenshot of coverage report
### Link to mutation testing report