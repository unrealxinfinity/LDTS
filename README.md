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
- **Level editor** - Allows user to create and play their own levels. In the level editor, the player will move around a "cursor" with the arrow keys that shows a character corresponding to one of the elements in the game in purple (here, a wall). There will be a key to cycle between elements and a key to place an element.

Heres a drawing for the level editor

![Heres a mock up for the level editor feature](https://cdn.discordapp.com/attachments/1030861260406935632/1045265120147820544/image.png)

### Design Problems

#### **Having multiple objects to instantiate**

- As we have many objects that we need to instantiate, it becomes difficult to maintain **Ridigity** of the game , having to make changes to all the components and functionalities everytime we add new type of objects.

**Solution** : **Factory Pattern**

- By using this design pattern,we ensure that we can instantiate different objects regardless their types by using their corresponding superclass so we can add new features like new types of objects(_ex: enemies or powerups_) without the need to worry about rigidity.

#### **The game needs a main "Control Unit" to check the state of itself and evolve throught time accordingly** 

- With so many components that needs to interact with each other, we need something that makes the interaction to avoid _Dependency hell_ ( a lot of dependencies where a lot of modules depends on a lot of other modules).We also need the states to control the current situation of the game and future events of the game.

**Solution** : **State Pattern**

- By using this pattern we can avoid what we stated before by integrating states to the game. We can also avoid a chunk of if statements by using polymorphism to switch to the right state.

#### **Checking every boulder/target combination after every move is inefficient**

- Since there is no need to check boulder/target collision in moves where a given boulder does not move, we should find a way to only check for it when said boulder moves.

**Solution** : **Observer Pattern**

- By implementing this pattern with target controllers as observers and boulder controllers as subjects, we can avoid unnecessary checks. Only when a boulder moves, its controller will notify the target controllers, which will then determine if the boulder has stepped into a target, out of a target, both, or neither. This result could then be used to increment/decrement an integer variable that determines how many boulders are in targets. When this is equal to the number of targets, the level is completed.

#### **Checking collision with every wall is inefficient**

- In this game's levels, the areas of the screen that aren't relevant to the level are filled with walls, and since nothing will ever touch them, checking for collision with them is not necessary.

**Solution** : **Have two Wall classes**

- By creating an "ImportantWall" class that is a subclass of Wall and is functionally identical, we can differentiate between walls that are just decoration (notably, walls behind walls) and walls that matter for collision. Thus, the level saves two lists of walls.

[Heres a link to show UML Class Diagram, click to see their interactions](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=LdtsUml.drawio#R7V1bV9s41%2F41rMVcJMvyMb6EcBjeoS0zUDr9brpMYsAdJ6aOKTC%2F%2FpMPcmxpJ1YSSQ4dsWZNiUicxM%2BztY%2Fa%2B8Aaz17P0%2BDp8UMyDeMD05i%2BHlgnB6Zpu6aB%2F8lX3soV0%2FPMcuUhjablGlouXEf%2FhtVi9cKH52gaLsq1ailLkjiLnhatV0%2BS%2BTycZK21IE2Tl%2FZr75O4%2Fa5PwUPILFxPgphd%2FRJNs8dydWR6y%2FXfw%2Bjhkbwzcv3yL7OAPLl668VjME1eGkvW6YE1TpMkK3%2BbvY7DOL975L58uXj7El%2F%2B457%2F78%2FFj%2BDz8R83H28H5cXONnlJ%2FRXScJ5tfenk0kY3N%2Bfz87E3Ob99do8j68cAVbfhZxA%2FVzfsOHmOp2FafefsjdzIxUs0i4M5fnR8n8yz6%2BovCD%2BePEbx9DJ4S57zj7fIgsk%2F5NHxY5JG%2F%2BLnB3H1ZPznNKt4Yrr51aI4HidxkuKFeVK8wfJF1%2FnF8B8MvJqGC%2FyyK3IbELX0IXhtPfEyWGTVwiSJ4%2BBpEd3VH3kWpA%2FR%2FDjJsmRWPYnzLldo%2FAzTLHxtcKy66%2BdhMguz9A0%2FpfqrY1QEqkQImV618LIkJCJrjw0yutVaUMnAQ33tJc74lwrqDWAnUtGA%2FSpZRFmUzDfBPYijhzn%2BfYLvGGaMdZzflAjL3lH1hyx52pkevxYX3DYXPAugggtQwTSkccFkuPDKkAB%2FvawAJ03%2BCSlhBeSXECMO77OVtFg8BZNo%2FnBZPOfEXq78VX3tfCnBr72Pi233MZpOw3kOaZIFWVDil4P1lETzrLgtzjH%2BD9%2BosTF0Dhz8wcf4MVo%2Bxv%2FlT0%2BzcTLH3yWICnRDTI6XMCcIgPt6%2BekGnuhNlxNnWxbMIwbmNw2zaJgdXnGWBrPFwMygHEcFeiXKxChCW0E8w2DF4RLTmxzykwFicLdY3C0A4zi4C%2BNaFVknaflcCvsueNv8jeaPYRpJhd2z%2BWBH7u6wX317db97g5fFH87VvxM0%2Fv359XyAWNgfwuzvw9%2Fqb62lXBzcPqeUWwJ09qU5P7q%2FvvjhnQ2e08f403d0jQaWD8H9VcMtBW5kcuJty8KbXLiBd%2FjjOYgXh2Sv%2FE3DLhx2VyHs4Ge2WNhvsK8SZps4ado5X%2BeQIZtyzpFhsqjbAOqOKQt11lL%2FEuQv05iLwdxCDOaAdzaShDm4wRPDcJ3ZHs6nR3lAFD%2B6i5MchGO8VAGH3PLhWZS%2FcXH7HrMZgThNnufTcFr9IXyNsr%2Fz3%2FEOWz762vjLyWvzwdtKLNZyd5E8p5Nw3ReuAo%2FhtBW%2BXYmZMbRtx2rBNvDQ6v05DeMgi362g8AQbNU7XuUKafl2PmozxKL8tfL7VS9qBl3p6xhDZLkj20SGY7qm77cu65hDo%2FFjO077XbJis1%2FzLm%2Fg10vu7xdh6xUFJ%2Bubur2bweqjg7F5cHR8HaY%2FI4w3fnCMqeLG%2BUZzl%2BLfHkoVXa2QhcsgDxjOyTr%2BLHfL51K0b9BY1obgj9obwgDS%2FAjaDwRofvBGO8yN%2FhDOn7UKEKb2DZuBHMBclt4HMWdVwDQNXg7PP19o436J%2F1px2SkcC4FtCgjYzG%2B%2FTb6P3E9jdONNzjLr%2Fsf3u4HPqnesCYkA4zvymDwk8yA%2BXa5SWnz5nMukiLrlIvQ9zLK3SpCD5yxpmwFE8ZPfv7aMAFjx4xufvpXWAtZQZKF8JbIQWVi%2BuHjUevVVmEb4ruUZoi2EumlKwLGvSjeX2nKNcUL0ZKfNwW087BazsxmBP43DWZjLjt7oRdn6VnufBxNuSrd5xOr2lblXvc3DErOX%2Bzz4kW1zlZDfXoQveZlFaQJXiyutYL0BbG3pUTvAwEGccT1p1r3NbgGFqRfW2%2F9YW33d20EtW3uZhYcNVbbw5jyYhWQv0EIvS%2BgtKGenVO87rCbQ%2Fh2npDv8DNgXxe%2Bykn6S%2FBumt5EWdaGijqjyyoHpj3oWdXeFqBcE0MqdS%2BTddyjyrFmXJ%2B60xAuW%2BLp%2Beo8kfkXwtkzcaoHnEfi9DuLCHxk6PZEfntAiL7pKg07XWP2na9gyjULk6%2FMzWup5pN7j5sLeSD1bfFlWZWmpFyz19enD%2FVH0xPRgvPgk0IqeU%2BR9biLsi8h7rDPPoCyuPItkXNHQMLyDdsZ15BxIz7g2E6lr49ediVQJlVnIcWhTwKTi9LzFWYg%2BjTdw6BOXKyqwMNDBW%2BNplRBt8qEto%2BOzMa8YbfoKu%2F0K%2FEv5uYWWhJHj4JyiQfTUKvJvxtRNCOhAakIQJxHtkW7PSZvWecylVnBSGKAcJ8h%2BeUBdHw09zxuNXHNkeya5Bb8CvGzVyS7wiqkl3qn%2Bx90z7iC6mhTR58C42dK9r8hmCxvSVLcZdCO%2FhR0iE3mLFm5E2xPcyFtMwEM18pudSHgP%2BwTvkQNFbHEQDTHd44HfkGUupZotbDDsvbPF3y%2B2uD4FcX0KeWO2MJdSzZYRa2LmxxvGCfZdkzjWMbQu9vLH0AY01mD1m9IQ2oi1QBdZ%2BHSYF0KVITT8%2Fyyahfo0e3cwrRal9xNM89lgWg69ln4F0m%2F1Xv7uQ%2FEifAOM%2ByjMW%2Fsd4V%2Bzt6dQC%2F56wff5ybA3gs8mznLBx3KWsXBrmd9S5k06qA10L6l7WraaExmyqp7rEr12v5qy6jlvWpML%2FZo6aC36sBztIvogBaSJPjJYpY8ZsNT5hAVNK6A8GXOE70aw8lyMpsaO1CCKvkdqaIugJ%2Bw9tppCNfZwJECbBEJNAroDMWQSIB80CWQdgauNDcokiGqTgFRTlVqgbH%2BhlQDPRrCUqp18A4gQJKwrgQ9sponTQNDUkEoNGyi5A6khT0lAqShtIKgAH2psqxh8tsaewVl86V2VeCKFd2iI%2FLoST03h3Uv0mv2f%2BZbcfr29%2BXb0P%2FPs5DYdWBWAfRTeMTlG%2Bng8b%2F6JuVDdWktR%2BgkZYrOVm4G8JwlFiy4tEQYnfSHpaLJhxf8cmsKE0%2B0bTbRZqbXiiiPiY%2B8J7BZTarYl7J27gXTYoSDQf0yIGdnbtsq0fyHerGxQmNFmIESZbY6C8xJ9WGMDutTQ8p3tyIIMNPQcv%2F4hhX%2FLC1tD31PMH5ely563Rxxg9plN8uULI0s%2B%2B7p1FtAfEe7JTLLB4hokwpTDdBv6jR%2BnzTjbttXSjexOPVYvNj3PdptNNDRsdx9oxHs6jNR37YlWHdD58K3LbJk911ZcN4lIX7JmuiSI5mXlHEVYnS3ZMlvC9BpwiZXdVTkjsYLCZC2qAhg98ocn8FnLzfsplkImG%2FVehFmRGT0s%2Fq%2BRl4I8UBChGnko5K3zHSrAByoiVIPPRjP16AeR6t2x2%2B4GIo971O7AoC8t8VtJ%2FPvrLZIPUdDg9wN%2B%2F7regkIPGnwV4Pev6y2HgVVhmDOPNFmNABUaGsjmDVLZbqtAohV6XxWkwpeiY1RK5sOQ%2BqHOyFVNIsEB0E3bBdH5G1SNd5bayAdZ%2FQTdV5TcGBxsYoOsmMNmR8AeYKFowhEHrrueR1HAnSkPsLdtTMFeSXVuEZiochZMsqS4T1dBVg4WpIisYIggoqPD4ElrWVME4YQOc6c%2BJD8LNY0pRwbOaL9SkF%2Fp0Js2b4taYogIx5%2BVFD1pikF%2FreSIdyMFxBDAT8wepFnku3AJd2NMvI4ZC8fe4ZRzU5acIzZgfDHL70Qwz%2FTocJGbPD1OcDmLQEVDBXiTZ7V8NWdEgy4GdLpf0j6AvlkFu7ACO9eizkUYnoIRsJ2VS7zHIkghhzG0kE%2FVudWVxxKq9VzLpxhkb%2Bl%2FjWzayvT4%2FK%2FllYSOiYfhUNkue3sqrR3P2kdRp02d011OKd%2B1nruecC%2FeS4cZAIW1VzPglyznNuko3tZo2p1Xkg2n2J7Qats7grPArdFeccWm7UrmTBw3Vzr3ENlcec8NpmGu8EZpe9pXmC4NvFzp3qEkcwUYhFy1%2B%2BH3XUg4YoLBzu3LFQGJ3VycX8qfMX13SNXAAG0BTdKRoU1hWR6NzZY7fik%2BDE0FHa2CRWinZmAQ1iLy3vBHZkvcfi%2FfV2MtGGuou5dSrInOa2B9GeHvXPVuKQenLbu3VP7ZQjNBNBOgXl9qmcAGBZpMKOPUNQ9yBRktomSer2s6CKdDXczaHx9Yn3INHzQN5NCA1%2FCTRwM2UtSkQT1NtWZCtaLJIJ4MTu%2BWIUezpzgq4Cth%2FlJ9UrQVxjOMVlHFVoF6U9SsDRADvMUCbwEgx8FdGNclFtZJWj6XAr8L3zaBo%2FljmEZycR9xWomy8tc2m81ahFkl6PqEoxzQ%2Fd53fmAOSN7xsWn4Ha4yCUpS4I9mIUPzQ0aUAPWtDBxWGWB%2BaFr0SwubU1fIowWoLMpAQpsY7eCCpob0uGLfIQZgTHe0IGbEsgTSNMpzVXdJEoeBroUVzwS%2F7%2BACMJU6WuSKgqaB5oAkDtQzBfvjABtgihalTtCbgUIi2H0bksDI6wdiSC4JsDr4qBkhmBFkd%2B6PEeCwAWJDwpygU1WaFYJZ4fduPrIlCA91GGoVLZbhaE0MOcSwzb6tSR%2FcLqhIldYkPVADOHqlmBrgnlEcwGkwAj6Qo%2BHfFX6vb9MSGmmaz6%2BmkNZHr7YsVfSoQ9XLqfctyKHDlvS5GHGnLYFpphPsO%2BpMFY%2BUixleCiIuS8ih4aVpeI8l5VFDLgVysGxRLeRsAHESJ4tQAy4FcLA6US3gbLQQG3IfMbBHk6KFggZeBvBwHaJa5Nnw4DQNXogNr1GXgDpYdqgWddanz1EvckQadCmgg%2BWFakFnvfUc9CoppGGXAjtYXagWdraSKIddFxhKddsMhTbd%2FPbb5PvI%2FTRGN97kLLPuf3y%2FA9qflabc4sA7%2FnyF79HJpy8f8T%2BXp2c3%2BY1bFgf9dXH%2Be7708dPHU%2FzPn58v8kdXR5%2BvTw%2B8E80V4VyxOK1AS9oeAcydvAzyE8XzDQN6%2BjwyN%2FokyEd1zrTZRjymC5LBlMYGdu%2B4nqQhFrCxeXBk6B2go4Ue4ifBmigfBLoIbQH24mWDunESTK9%2FPOMv20jlHH25uQnTWTQP4jMsgPhG3UcPz2lQHPnQ5JBDDigeCJJDwAkVkByscsC7QZCFhAqHVa%2BCMTnI3rQm1lHmt5JV5O%2BaO8K5AwxvA7lDtz4RqEzgANP4MUiDSdYoTcb3q17Mf8fYPqfaO5FCCwTVp8pSOCs%2BNBuCKneV0tQ4JJuCZoAcBkCFqYoZwNFXTZ93FY88VIAKIS%2FLnGC9CybXVNia45uLTx%2B16IsnAK9RIM3bAMINdfphaQ4YT8u%2B7UeaB%2BKdTqNvrxMINCyTj5oIyohgcWoEeURgTYEiNYWhXc0DfZpVIiUcIH%2BhlhKs36jrDGUiPgKyEGoRBzxCjbg8xC1gRJdixNmKBF1aKhdzq3%2B3X%2Bn0DqUNtNej1MdQhRHVWn1gUoFe7tEbzFkEtc2yETDp57Sc4nd7Eb6E6fFzVPbKo6ikT59sO9KRHqMxqKf8dBUq1BWO4ncPslmwx9lLGpDoUbES5Stai3RoEXNzW7H3WkYTLFgvAgc1D4qkdWNJE0E4Efo%2FqmKC7S3KmtZS%2FgkTqjVNBTlU6P8QiwkeVK%2FqXNtcOKg76WodIYkPe3C2hYQ1%2B5rpftCc6L4c8N450902Rwetqe72Wn9FwVB3ss93ziKs7TPZQ92RQc2lHFi00cnr2iCT9m0sj%2Fo8K5wb7KMGb42nVYK5wYcmp2uXPC%2BvKdZ3stgjAEQPVu19Vnbp0LW%2BAl0qj%2FaobOBAP4LCMciSNj7ZYuMxmA7GLJmGuiKvSy%2FWkrXTrHS1ATibjaTkybYqmrI4PP988ZsGXjjwgK%2BkGHgyh1cXXO1QcLU58IBnBAIvbYcnYbu2Y%2FQh3%2BAbDtGHcsPvMgj0RrArH0a9awAXPglc7vwacwmY12eqelT7SvNuxAmuPWI%2BJ3gzy7vbHSW2Tqc7WqFoDFGdr6ugc53VyO3oxNaDyWt%2FgMSwNvZhDdqHdWh3eIUPu7wUeWJyf78I5STxgOGX6zj4S05GZ0C33C0DF8yVHMWD7hEw4FLjuTWeyKCz9cylpAPK%2Boa7ALptLPTXIIJNi%2BP2RLA9f%2Bg3fkaKaWFqOe%2FebfnhtbfcuDcPOJsr3kluwJnYTIozL1UGhfze2GtWJ0%2FqHWq5KX1t2q1CdiiOIrFKoJomLZiKMTiFgpvtO3WZQX3ijCEzrBbWvrcd2kYH2mJTbOud8k6nhnSY2RMKWL1QYAcgqySrbzeog4aG4a6njnwK%2BAADwD2AnI2QnWS1jbb2cOk2ASs03qZqinmfKnMvSkmBvGULh%2F5zJo1DpaMR0weC16Tx3K4riTNRQTTZ2h%2BNJtMFnhfNbl5IRpONT%2B%2BC5h4cAjD3iyoeogC20XsVfI6h6b%2B64Ns0msa227hrdl1JMpqb5SjegeDvF1PcES3328YwVDIFPjvoscxQ6XraVI2m4b0DzxP2Jsi4h07PkwwpUu95whRgd4txgu9eEsebHQTTlYzVjeQ%2FHEZ5bANoGBlcyUgiXeL7CrEena5p6ahvWC9Xe1nUCGPPkYjUpW2iYectaZTWSYzNVi6y8OnwPJiFWCyK3tXjG6w7dVWTDPhdzi1fntSDJ75IYaOualTIBZVFjTAXAE9RoT%2FQ9C95U47YayhPetVJx6HReUosfyTZIfCFm%2FmwD2m69pBUJdYVL7479Izlj8XlVG6ccKAiJvhtPYqBu2UcQIparJGCXYEs1K6KTFfFZmppuF0V0ilV%2BGZlsYaLVlTbKKpapN6Pq0JOJWtXZXtXZXPYFboqa3Vq21RdhqsKe7UZvdIbgeSNgNd7EdHLCKYE2Lqk3b6GtKfQbJDMBt6D2NLYgEw2mq2DGQwB2vawLDZA8zEUWwlggyt9ZrOf3QGalqGYD5uVqwk6vWcMbc9uRyiQKT9C0VkA6VdfnvdYH7lw3XbF9FfjuWNUo85DLTtlbpljp08JMBfqPNJXf32ndSXHb19HyIk%2FmLZsJVfRkU%2FnadUEPwZMorYmUWf0wxdg6sCkYGu2ZlhTtMc%2B%2FKetHF5F5nLzYW%2BiHxwVXjr6IRr2vhO1QOeZWuJzAHS7dymw956gBTrP1Lj%2FdYHfXwMvBfjes7FAj7Ea%2BM9PGnUpqCttMgPDzuY2athPkpe5Bl4O8BZnizF5wLPhKh28VEgAl222rJgAKuNT7H3cKZpU3ZOtg0mut%2Frm79rm2KL9d2fr7jJM0YtDe%2Fi88SREH%2FpCygJKQIOaD%2BH8eUUb9xWxJGPnINEvP%2FzFpKtmPCglYkNxI9L4XfgeA3ShYRvXavXSpV4cfjasiRpB0EvTLg40Khx%2FX%2BM%2BCuNpmQvL3p7YSjoN%2FY7QQ5EjtdBDhoWGXgH0UN9itdCLbQOwvdW4lph7crRXpKVo0crfoTtJSD7c67AZorNgkiXF%2FcQXf0ymB6YbzHKZnN8tniCt3wb%2F5THKwmssrPlfX9Lgqc0UWXbUiOm%2FBsVkEUAL%2Bjj1NlLljf7858PT%2Fefk29fPf%2Funf5x6lwvSQIwtQjeugiwL0%2Fl%2B3kmHaXALjK%2Bt63Z2vJP4YZokWZPU%2BIs%2BluU21un%2FAw%3D%3D)

#### You can find the classes in the following folders:

- [Folder where all the classes are listed] (https://github.com/FEUP-LDTS-2022/project-l07gr08/tree/States/src/main/java/pt/up/fe/edu/hero)


 
