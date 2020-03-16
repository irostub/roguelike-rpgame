# Console Roguelike Game

    
RoguelikeRPG project is intended to become familiar with Java classes. Finished designing the class, **now writing data input/output**. a lot of exceptions left, but this should be done after I / O is finished. 


## Project Environment

- Windows 10 (build 18363.720)
- JDK Compiler Version 1.8
- Code text encoding used MS949


## Class Diagram

```mermaid

graph LR
AA[Galen] --> X[Monster]
AB[Ghuol] --> X
AC[Goblin] --> X
AD[Golem] --> X
AE[Skeletom] --> X
AF[Troll] --> X
AG[Yaso] --> X

BA[Portion] --> Y[Food]
BB[BigPortion]  --> Y
CD[Inventory] --- CA


Y -.-> CA[Item]
CA --- CC[Tile]
X --- CC
CB[Player] --- Z[GameManager]
CC --- Z

Z --- ZZ[Main]

```