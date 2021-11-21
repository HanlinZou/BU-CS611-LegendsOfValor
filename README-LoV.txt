Authors
================================

- Name: Hanlin Zou
  Email: hzou7@bu.edu
  BU ID: U96634471

- Name: Xiansong Li
  Email: xiansong@bu.edu
  BU ID: U55619815

- Name: Xiaohan Zou
  Email: zxh@bu.edu
  BU ID: U18269004


Environments
================================

Tested on Windows and macOS Big Sur.



Compilation Instructions
================================

> javac *.java
> java Main



Class Description
================================

Game
---------------------------------
1. Main.java: The class to initiate a game.
2. Game.java: Game center, which will ask user to choose a game to play.
3. RPGGAME.java: RPG game center, which will ask user to choose a RPG game to play.
4. LMH_Logic.java: Implements the whole logic of Legends: Monsters and heroes.
5. LoV_Logic.java: Implements the whole logic of Legends of Valor.
6. Player_Interface.java: Defines interfaces for an RPG game player (or team).
7. Player.java: A basic implementation of player interface.
8. LMHPlayer.java: Maintains information of a Player in LMH game.


Game Map
---------------------------------
9. Cell.java: Smallest component to construct a Board for RPG players to move around.
10. Tile.java: For constructing a map for LoV game.
11. CellType.java: Maintains a list of available LoV map cell types.
12. BuffTile.java: A cell which can give hero standing on it a buff, defines buff-related interfaces like `cellEffect` to be implemented by its subclasses.
13. Bush.java: Implements a bush tile for LoV game, inherits from class BuffTile.
14. Cave.java: Implements a cave tile for LoV game, inherits from class BuffTile.
15. Koulou.java: Implements a koulou tile for LoV game, inherits from class BuffTile.
16. Plain.java: Implements a plain tile for LoV game, inherits from class Tile.
17. Nexus.java: Implements a nexus tile for LoV game, inherits from class Tile.
18. Inaccessible.java: Implements of an inaccessable tile for LoV, inherits from class Tile.
19. Board.java: A basic world map class for an RPG game, composed by Cell objects and also some variables of its own.
20. LMHBoard: A more detailed Board object designed for LMH game, which contains some unique elements.
21. LoVBoard: A more detailed Board object designed for LoV game, which contains some unique elements.
22. CellFactory.java: Using factory method to create board cell for LoV game easily.
23. Drawable.java: Defines interfaces that each board tile should implements to print them in terminal easily.
24. Market.java: Maintains information of all available items and implements methods for items trading.


Characters
---------------------------------
25. Fight.java: Defines interfaces for all hittable characters.
26. Character.java: Maintains information and defines necessary interfaces for a Character.
27. Monster.java: Maintains information and implements necessary interfaces for a Monster, inherits from class Character.
28. Dragon.java: Maintains information and actions for a dragon monster.
29. Exoskeleton.java: Maintains information and actions for an exoskeleton monster.
30. Spirit.java: Maintains information and actions for a Spirit monster.
31. Hero.java: Maintains information and implements necessary interfaces for a Hero, inherits from class Character.
32. Paladin.java: Maintains information and actions for a Paladin hero.
33. Sorcerer.java: Maintains information and actions for a Sorcerer hero.
34. Warrior.java: Maintains information and actions for a Warrior hero.
35. CharacterLibrary.java: Maintains information about the contents of all Characters, including Heroes and Monsters that heroes might encounter.
36. HeroCreation.java: Uses builder pattern to create a list of hero objects easily.


Items
---------------------------------
37. Equipable.java: Defines interfaces for all equipable items.
38: ItemTrade.java: Defines interfaces for all tradable items.
39: Consumable.java: Defines interfaces for all consumable items (like potion).
40: Castable.java: Defines interfaces for all castable items (like spells).
41: Item.java: Represents an Item.
42: Armor.java: Maintains information and implements necessary methods for an Armor Item, inherits from class Item.
43: Weapon.java: Maintains information and implements necessary methods for a Weapon Item, inherits from class Item.
44: Spell.java: Maintains information and implements necessary methods for a Spell Item, inherits from class Item.
45: FireSpell.java: Maintains information and implements necessary methods for a Fire Spell Item, inherits from class Spell.
46: IceSpell.java: Maintains information and implements necessary methods for an Ice Spell Item, inherits from class Spell.
47: LightningSpell.java: Maintains information and implements necessary methods for a Lightning Spell Item, inherits from class Spell.
48: Potion.java: Maintains information and implements necessary methods for a Potion Item, inherits from class Item.


Utils
---------------------------------
49. Color.java: A static utility class providing ASCI color code.



Design Patterns
================================

1. Template Pattern
---------------------------------
We defined sketelons for Characters, Items and Map Cells, and implements the details in subclasses.

2. Factory Pattern
---------------------------------
We used factory methods for creating LoV map cells easily, see CellFactory.java.

3. Builder Pattern
---------------------------------
Class HeroCreation serves as a builder, so that the construction of Hero objects is seperated from their representation and we can get a created Hero object method easily.

4. Facade Pattern
---------------------------------
Class Bush, Cave and Koulou uses facade pattern to provide a simple interface for adding/removing buff to/from Heroes, masking complex adding/removing steps.

5. Singleton Pattern
---------------------------------
Ensures that Color class has only one instance, and provides a global point of access to it


Bonus
================================
1. We designed and implemented a colorful and fancy terminal UI.
2. We provided a natural and smooth user experience.
3. We used meaningful and suitable design patterns in this project to improve the OOD.
4. The input is getting parsed from the input files and is not harcoded.
5. We wrote detailed comments following the practice of Javadoc.
