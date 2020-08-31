Start up
Once the game is started, you will be prompted to choose between 3 difficulties (easy, medium, hard)
easy - small map
medium - medium map
hard - large map

different maps can be used but must be named in the format - size_map.txt.

once the map is chose you will start in a random position on the map, the bot will similiary start in a differeerandom location. 

Aim of the game is to roam the dungeon and pickup gold and exit the map before the bot catches you. The amount of gold needed to win varies on the difficulty of the map.

P - is the human players character
B - the bots character
G - gold that the player can pickup
# - walls, cannot walk through them
E - exit tile, the player must be on this tile and have enough gold to quit and win the game. 
. - empty floor, nothing on it. 

On the players turn they must enter 1 of the 6 commands, all of them take a whole turn to perform. 

hello - displays amount of gold needed to win

gold - displays how much gold you have

move <direction> - moves you in specified direction, direction must be a single letter N - north S - south E - east W - west

pickup - picks gold up if player is on a gold space. If pickup successful prints out amount of gold player has

look - prints out 5x5 grid around player. Visible areas outside map shown as #

quit - quits the game, WIN is displayed if player quits on a exit tile with enough gold, LOSE if player gets caught by bot or quits without hitting the above win conditions.

all inputs are not case sensitive. 