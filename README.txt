Name: Hanlin Zou
Email: hzou7@bu.edu
BU ID: U96634471

Compilation instructions:
Windows Intelli J/macOS terminal Recommended.
This program runs perfectly on Intelli J and terminal of Mac.
However, better run it on somewhere with a background with darker color.(I mean Intelli J:))

For user using cmd or terminal, may have the problem that ASCI color doesn't show correctly.
Try Example:
> javac *.java
> java Main

More bonus, happier life:
1. Colorful printing, this program uses different color to print messages. General messages, announcement
messages, prompt messages, select messages, information messages, error messages, damage messages etc.
2. Use I/O to select heroes that user wants instead of random heroes.
3. Monsters are generated at the moment after heroes move, and each monster has same level as one hero.
For example heroes levels are 7 and 5, a monster with level 7 and one with level 5 will be generated.
4. If a hero doesn't have enough mana to cast a spell, it will switch to a regular attack.
5. While in the market, heroes can stay as long as they want, doesn't need to re-enter the market.
5. Damage transforms are more reasonable, would cause monster die after one kill. Same for defense and dodge.

**Important notes before running:
1. File scanning process takes place in CharacterLibrary.java and Market.java. The file path should be
functional with no change required.

*Not-that-important notes before running:
1. If you want to switch weapons/armors, drink potions, learn spells and check inventory, you'll need
to input I/i to display your information first, then you'll be asked whether you want to do an operation.
2. Spell can't be dodged in this game.
3. We ignore the requirement of number of hands of a weapon, and whether an armor item is a shield or a cloth.
Which means we only allow one hero to equip one weapon and one armor item at same time.
4. If all heroes die, or a hero gets revived, whom will have 50% health back, lose half money
and 0% mana available.

Design pattern:
1. Four classes are inherited from Item class: Armor, Weapon, Potion, Spell
2. Two classes are inherited from Character class: Hero, Monster
3. Two more specified customized classes for LMH game inherited from Board, Player: LMHBoard, LMHPlayer
4. Character.java is an abstract class since hero and monster have different functions to calculate damage,
defense, and dodge probability.
5. One Interface class designed for creating more players and teams.

Description:
1. Main: The class to initiate a game.
2. Game: Game center, which will ask user what kind of game he/she wants to play.
3. RPGGAME: RPG game center, which will ask user what RPG game he/she wants to play.
4. Cell: Smallest component to construct a Board for RPG players to move around.
5. Board: The world map in an RPG game, composed by Cell objects and also some variables of its own.
6. LMHBoard: A more detailed Board object designed for LMH game, which contains some unique elements.
7. CharacterLibrary: An inventory class that holds all heroes, monsters, and their initial information.
8. Character: An object class that represent a character in different games.
9. Hero: An example, inherited from Character class, that represents a hero in LMH game.
10. Monster: An example, inherited from Character class, that represents a monster in LMH game.
11. Player_Interface: Designed to satisfy potential scalability and extendability purpose.
    Convenient to build features such as playerId, teams, etc.
12. Player: A class with implement on player interface. Which can be further designed.
    Provided with some supplement method to make player functional.
13. LMHPlayer: A unique LMH player that inherited from Player class. It contains some unique elements
    of LMH game.
14. Item: An abstract class that represent a thing in LMH game.
15. Armor: An example, inherited from Item class, that represents an armor or a shield.
16. Weapon: An example, inherited from Item class, that represents a weapon of heroes.
17. Potion: An example, inherited from Item class, that represents a potion that can be consumed by heroes and
increase their vital or skill.
18. Spell: An example, inherited from Item class, that represents a spell can be learned and cast by heroes.
19. Market: An inventory class that holds all items, and their detailed information for players to purchase.
20. LMH_Logic: Contains the whole logic of Legends: Monsters and heroes.