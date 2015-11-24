Concentration
=============

![](Media/Concentration.jpg)

* * * * *

Introduction
------------

[Concentration](http://en.wikipedia.org/wiki/Concentration_%28game%29)
is a card game which is played with a deck of cards and each cards in
the deck has a matching card. To set up the game, the cards are
shuffled, and then all of the cards are placed face down on a surface.
The game is played in turns in which two cards are flipped face-up. If
the cards match, then they are left face up, otherwise they are turned
back over, and another round begins. The object of the game is to turn
over every pair of matching cards.

In this lab you will enhance a text-based Concentration program by
creating a graphical user interface (GUI) for the game so that a user
can play a single person version of the game by clicking on buttons.

* * * * *

Pre-Lab Work
------------

-   The Java Tutorial:
    [Swing](http://docs.oracle.com/javase/tutorial/uiswing/)\
-   Fetch the jar file by clicking
    [here](http://www.cs.rit.edu/~csci242/pub/Binaries/labConcentration.jar).

* * * * *

Implementation
--------------

You will complete this lab on your own.

### Java Documentation/Code

You are provided with a complete implementation of the model and a
text-based view-controller. You will need to modify the model slightly
to connect it with a GUI; it should become
[Observable](http://www.cs.rit.edu/usr/local/jdk/docs/api/java/util/Observable.html).
The interface given is the interface it should have, and so there are
clear limits concerning the extent of the modifications. Your modified
model should continue to work with the text-based view-controller. The
other classes should not be modified.

-   [`CardFace`](Doc/CardFace.html) class
-   [`Card`](Doc/Card.html) class
-   [`CardBack`](Doc/CardBack.html) class
-   [`ConcentrationModel`](Doc/ConcentrationModel.html) class
-   [`TextViewControl`](Doc/TextViewControl.html) class

The text-based game is run as follows.

    $ java TextViewControl

You will implement the following classes.

-   [`CardButton`](Doc/CardButton.html) class
-   [`CheatFrame`](Doc/CheatFrame.html) class
-   [`GViewControl`](Doc/GViewControl.html) class

The GUI-based game should be run as follows.

    $ java GViewControl

### Description

In any GUI application, there are two main concerns: how to layout the
components of the GUI to make it appear as you want, and what
functionality to attach to the components to make it behave as you want.

In this lab, we will specify both the look and behavior, but not the
details of how these should be achieved. (Of course much of the behavior
will be determined by the model.) The GUI itself should look
approximately like this (the exact look will depend on your operating
system) and should respond reasonably to being resized:

![](Media/con_init.png)

![](Media/con_mid.png)

![](Media/con_mid_cheat.png)

Initial puzzle

Puzzle in the middle of play

Cheat window

The first thing you will need to do is decide how you will lay out the
GUI to achieve this look. Will you need any invisible components
(JPanels)? What layouts will you use?

Your UI must contain the following parts:

-   A title bar with "***YourName*: Concentration Game**" as the label
    (where *YourName* is replaced with your name).
-   A message area that tells the user what move it is and what they
    should be doing next
-   The grid with all the cards
-   A reset button that turns all the cards face-down and scrambles
    them.
-   A cheat button that generates a window 75% the size of the game
    window with all of the cards showing.
-   An undo button that turns the most recent card selected back down
    unless it was part of a match.

The user will use the mouse to make selections of cards, each card
selected should be exposed to the user. The UI should work together with
the Model to control which cards to expose and which cards to hide.

### Implementation Hints

Before you begin coding, make sure you understand how the class
ConcentrationModel works. The main things to consider are the UI layout,
how you will represent the cards, and how the UI will interact with the
model.

The
[Observer](http://www.cs.rit.edu/usr/local/jdk/docs/api/java/util/Observer.html)
and
[Observable](http://www.cs.rit.edu/usr/local/jdk/docs/api/java/util/Observable.html)
classes can simplify the interaction between the View/Control and the
Model. You can make the View/Control an `Observer` of the `Observable`
Model. Then the Control notifies the Model when the user does an action.
If the user's action causes a change to the state of the board, then the
Model can use the `notifyObservers` method to inform the View (through
the `update` method) that the board has changed, and needs to be
re-drawn.

Try to make the grid look the same as the examples. For colored faces
for the cards, you will need to make sure that the colors (see
[Color](http://www.cs.rit.edu/usr/local/jdk/docs/api/java/awt/Color.html))
of the cards are visible. Assuming that you have used JButtons to
implement the grid cells, the following method calls on each will make
them look reasonable in most cases (i.e., on the mac):

        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

Also note that calling a method to change the appearance of an
already-shown GUI may not have immediate effect. Calling `validate()` on
the window will cause a redraw with the updated appearance.

* * * * *
