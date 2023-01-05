# Minesweeper
Minesweeper Game using Java Swing

When the application is opened, the first frame should be displayed. It will show the title of the game, a choice of difficulty, and a button to start the game. 

There are two difficulty levels, normal and hard. Normal difficulty will have 10 bombs on a 10 by 10 grid and hard difficulty will have 25 bombs on a 25 by 25 grid. 

Once the user presses the button to start the game, frame 2 will be displayed. Frame 2 displays a grid of panels, a flag counter to show how many flags are left to place, a panel to display simple instructions, and buttons, reset and menu. Reset will display a JDialog when clicked to ask if the user wants to reset the game in case the user clicked the button on accident. Similar to the reset button, the menu button will also display a JDialog and will send the user back to frame 1. 

The user will have to press one of the panels on the grid to start. The panel will hide and reveal how many bombs are surrounding it. If it has no bombs, the surrounding panels that also have no bombs near it and the panels outside of those with bombs near it should also hide. No bombs should be revealed. 

To place a flag, the user should click the key “F” on the panel. The flag counter should go down 1 flag and a flag should be placed on the panel.

Once all the panels are revealed except the bombs or if the user clicked on a bomb, the user should be sent to frame 3. Frame 3 will display if the user won or failed. Also it will have a difficulty chooser and a start button for a new game. If the user clicks on the start button, the user will be sent back to frame 2 for a new game.
