import java.util.ArrayList;
import java.util.Scanner;

public class LoV_Logic {

    LMHPlayer player;
    ArrayList<Monster> monsterArrayList = new ArrayList<>();
    CharacterLibrary chl;
    Market market;
    LoVBoard board;

    /**
     * Necessary preparation at the begining of the LoV game.
     * Initialize player, game board, market and creates heros, etc.
    */
    public void prep() {
        Scanner sc = new Scanner(System.in);

        System.out.print(Color.YELLOW + "I need your name first: " + Color.RESET);
        String pName = sc.next();

        this.player = new LMHPlayer(pName);
        this.chl = new CharacterLibrary();
        this.market = new Market();
        this.board = new LoVBoard(3);
        this.board.setBoard();

        // create heroes
        HeroCreation hc = new HeroCreation(3);
        hc.prep(player);

        //set heroes' initial location
        board.getTile(7, 0).setHeroOn(true);
        player.heroArrayList.get(0).setPos(7, 0);
        board.getTile(7, 3).setHeroOn(true);
        player.heroArrayList.get(1).setPos(7, 3);
        board.getTile(7, 6).setHeroOn(true);
        player.heroArrayList.get(2).setPos(7, 6);

        // after all components are ready, start the game.
        start();
        sc.close();
    }

    /**
     * Play the game one round after another.
    */
    public void start() {
        Scanner sc = new Scanner(System.in);
        String playerChoice = "Q";
        int numRound = 0;

        do {
            //avoid each hero heal for many times
            for(int i = 0; i < player.heroArrayList.size(); i++) {
                player.heroArrayList.get(i).heal();
            }

            // Genetate new monsters every 8 rounds
            if(numRound % 8 == 0) {
                numRound = 0;
                spawnMonsters();
            }

            for(int i = 0; i < player.heroArrayList.size(); i++) {
                Hero hero = player.heroArrayList.get(i);

                // player would be provided an option to purchase if available at beginning of each round
                if (board.getTile(hero.x, hero.y) instanceof Nexus) {
                    board.displayBoard();
                    nexusOp(i);
                }

                board.displayBoard();
                System.out.print(Color.YELLOW + "Hero No." + (i + 1) + ", " +
                    player.heroArrayList.get(i).getName() + ". Pick a move: ");
                playerChoice = sc.next();

                // whether player wants to move
                if (playerChoice.equalsIgnoreCase("W") || playerChoice.equalsIgnoreCase("S") ||
                    playerChoice.equalsIgnoreCase("A") || playerChoice.equalsIgnoreCase("D")) {
                    if (!playerMove(hero, playerChoice)) i--;  // failed to move
                    if (hero.x == 0){
                        board.displayBoard();
                        System.out.println(Color.ORANGE + "Heroes won!" + Color.RESET);
                        playerChoice = "Q";
                        break;
                    }
                }
                // or want to fight
                else if (playerChoice.equalsIgnoreCase("F")){
                    if (!fight(hero)) i--;
                }
                // or wants to see information and inventory
                else if (playerChoice.equalsIgnoreCase("I")) {
                    player.displayInfoNotInFight();
                    System.out.print(Color.YELLOW + "Do you want to switch your weapon/armor, " +
                        "use potions, or learn a spell? Input \"Y\" to operate: ");
                    String ans = sc.next();
                    if (ans.equalsIgnoreCase("Y"))
                        player.heroArrayList.get(i).itemOp(false);
                    i--;
                }
                else if (playerChoice.equalsIgnoreCase("T")) {
                    if (!teleport(i)) i--; //If tp fail due to no available target
                }
                else if (playerChoice.equalsIgnoreCase("B")) {
                    back(i);
                }
                else if (!playerChoice.equalsIgnoreCase("Q")){
                    // Invalid action
                    System.out.println(Color.RED + "Invalid action, please re-enter it." + Color.RESET);
                    i--;
                }
                else
                    break;
            }
            monsterTime();
            numRound++;
        } while(!playerChoice.equalsIgnoreCase("Q"));
        System.out.println(Color.RESET + "Goodbye. " + player.getPlayerName());
    }

    /**
     * Hanldes monster generation.
     * Genetates monsters with the same level as heros.
    */
    public void spawnMonsters(){
        // btw, each monster has same level as heroes
        int[] heroLV = new int[3];
        for (int i = 0; i < 3; i++) {
            heroLV[i] = player.heroArrayList.get(i).getLevel();
        }

        // search for monster with same level as hero and add it in monster list
        for (int i = 0; i < 3; i++) {
            if(i % 3 == 0) {
                for(int j = 0; j < chl.dragonArrayList.size(); j++) {
                    if (chl.dragonArrayList.get(j).getLevel() == heroLV[i]) {
                        monsterArrayList.add(new Monster(chl.dragonArrayList.get(j).getName(),
                            chl.dragonArrayList.get(j).getLevel(), chl.dragonArrayList.get(j).getHP(),
                            chl.dragonArrayList.get(j).getDamage(), chl.dragonArrayList.get(j).getDefense(),
                            chl.dragonArrayList.get(j).getDodge()));
                        break;
                    }
                }
            }
            else if (i % 3 == 1) {
                for (int j = 0; j < chl.exoArrayList.size(); j++) {
                    if (chl.exoArrayList.get(j).getLevel() == heroLV[i]) {
                        monsterArrayList.add(new Monster(chl.exoArrayList.get(j).getName(),
                            chl.exoArrayList.get(j).getLevel(), chl.exoArrayList.get(j).getHP(),
                            chl.exoArrayList.get(j).getDamage(), chl.exoArrayList.get(j).getDefense(),
                            chl.exoArrayList.get(j).getDodge()));
                        break;
                    }
                }
            }
            else {
                for (int j = 0; j < chl.spiritArrayList.size(); j++) {
                    if (chl.spiritArrayList.get(j).getLevel() == heroLV[i]) {
                        monsterArrayList.add(new Monster(chl.spiritArrayList.get(j).getName(),
                            chl.spiritArrayList.get(j).getLevel(), chl.spiritArrayList.get(j).getHP(),
                            chl.spiritArrayList.get(j).getDamage(), chl.spiritArrayList.get(j).getDefense(),
                            chl.spiritArrayList.get(j).getDodge()));
                        break;
                    }
                }
            }
        }

        board.getTile(0, 1).setMonsterOn(true);
        monsterArrayList.get(monsterArrayList.size() - 3).setPos(0, 1);
        board.getTile(0, 4).setMonsterOn(true);
        monsterArrayList.get(monsterArrayList.size() - 2).setPos(0, 4);
        board.getTile(0, 7).setMonsterOn(true);
        monsterArrayList.get(monsterArrayList.size() - 1).setPos(0, 7);
    }

    /**
     * Hanldes events on nexus cells.
     * Heros can choose to buy or sell items on nexus cells.
    */
    public void nexusOp(int heroIndex){
        Scanner sc = new Scanner(System.in);
        String decision;
        System.out.print(Color.YELLOW + "Hero No." + (heroIndex+1) + ", " +
            player.heroArrayList.get(heroIndex).getName() + ". You're at Nexus, input \"Y\" to buy/sell: ");
        decision = sc.next();

        if (decision.equalsIgnoreCase("Y")) {
            boolean leave = false;
            while (!leave) {
                // display all heroes' names, levels, and money
                player.displayInfoInMarket();
                market.shopping(player.heroArrayList.get(heroIndex));
                // make sure player wants to leave
                System.out.print(Color.YELLOW + "Are you done shopping? 'N' = stay, any other keys = leave ");
                decision = sc.next();
                if (!decision.equalsIgnoreCase("N"))
                    leave = true;
            }
        }
    }

    /**
     * Handles movement and events encountered after moving for a given player.
     *
     * @param hero A Hero object.
     * @param direction The direction that the hero wants to move toward.
    */
    public boolean playerMove(Hero hero, String direction) {
        if (
            (direction.equalsIgnoreCase("A") && (hero.y - 1 < 0)) ||
            (direction.equalsIgnoreCase("D") && (hero.y + 1 >= board.getNumRow())) ||
            (direction.equalsIgnoreCase("W") && (hero.x - 1 < 0)) ||
            (direction.equalsIgnoreCase("S") && (hero.x + 1 >= board.getNumColumn()))
        ) {
            // Go outside the map
            System.out.println(Color.RED + "Your can't go outside the map, please re-enter your move: " + Color.RESET);
            return false;
        } else if (
            (direction.equalsIgnoreCase("A") && (board.getTile(hero.x, hero.y - 1) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("D") && (board.getTile(hero.x, hero.y + 1) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("W") && (board.getTile(hero.x - 1, hero.y) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("S") && (board.getTile(hero.x + 1, hero.y) instanceof Inaccessible))
        ) {
            // Move to Inaccessible Cell
            System.out.println(Color.RED + "Inaccessible, please re-enter your move: " + Color.RESET);
            return false;
        }

        int fromX = hero.x;
        int fromY = hero.y;

        if (direction.equalsIgnoreCase("W")) {
            //hero at left tile and there's a monster at right tile or at same tile
            if(fromY % 3 == 0 &&
                (board.getTile(fromX, fromY).getMonsterOn() || board.getTile(fromX, fromY + 1).getMonsterOn())) {
                System.out.println(Color.RED + "Kill the monster first, please re-enter your move: " + Color.RESET);
                return false;
            }
            //hero at right tile and there's a monster at left tile or at same tile
            else if(fromY % 3 != 0 &&
                (board.getTile(fromX, fromY).getMonsterOn() || board.getTile(fromX, fromY - 1).getMonsterOn())) {
                System.out.println(Color.RED + "Kill the monster first, please re-enter your move: " + Color.RESET);
                return false;
            }
            // avoid two heroes at same tile
            if(board.getTile(fromX - 1, fromY).getHeroOn()){
                System.out.println(Color.RED + "A hero in front of you, please re-enter your move: " + Color.RESET);
                return false;
            }
        }
        else if (direction.equalsIgnoreCase("S") && board.getTile(fromX + 1, fromY).getHeroOn()) {
            System.out.println(Color.RED + "A hero is behind you, please re-enter your move: " + Color.RESET);
            return false;
        }
        else if (direction.equalsIgnoreCase("A") && board.getTile(fromX, fromY - 1).getHeroOn()) {
            System.out.println(Color.RED + "A hero is on your left, please re-enter your move: " + Color.RESET);
            return false;
        }
        else if (direction.equalsIgnoreCase("D") && board.getTile(fromX, fromY + 1).getHeroOn()) {
            System.out.println(Color.RED + "A hero is on your right, please re-enter your move: " + Color.RESET);
            return false;
        }

        Tile fromTile = board.getTile(fromX, fromY);

        // check type of from tile and remove buff if necessary
        if (hero.getBuffed() && fromTile.isBuffTile()) {
            BuffTile buffFromTile = (BuffTile) fromTile;
            buffFromTile.removeEffect(hero);
        }

        // remove hero from the old position
        fromTile.setHeroOn(false);

        // update hero position
        if (direction.equalsIgnoreCase("A")) hero.setPos(hero.x, hero.y - 1);
        if (direction.equalsIgnoreCase("D")) hero.setPos(hero.x, hero.y + 1);
        if (direction.equalsIgnoreCase("W")) hero.setPos(hero.x - 1, hero.y);
        if (direction.equalsIgnoreCase("S")) hero.setPos(hero.x + 1, hero.y);

        Tile toTile = board.getTile(hero.x, hero.y);

        // add hero to the new position
        toTile.setHeroOn(true);

        // check type of to tile and add buff if necessary
        if (toTile.isBuffTile()) {
            BuffTile buffToTile = (BuffTile) toTile;
            buffToTile.cellEffect(hero);
        }

        return true;
    }

    /**
     * Teleports the given player to one teammate's back.
     *
     * @param heroIndex Index the hero to be teleported.
    */
    public boolean teleport(int heroIndex) {
        String mateIndex;
        Scanner sc = new Scanner(System.in);
        System.out.print("Which teammate you want to teleport to? 1-3. hero index 4. cancel tp: ");
        mateIndex = sc.next();

        while (!mateIndex.matches("^[1-4]$") || Integer.parseInt(mateIndex) - 1 == heroIndex ||
            player.heroArrayList.get(Integer.parseInt(mateIndex) - 1).x == 7) {
            if (!mateIndex.matches("^[1-4]$"))
                System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
            else if(Integer.parseInt(mateIndex) - 1 == heroIndex)
                System.out.print(Color.RED + "You can't teleport to yourself, try again: " + Color.RESET);
            else
                System.out.print(Color.RED + "You can't teleport to a hero at Nexus, try again: " + Color.RESET);
            mateIndex = sc.next();
        }

        int mate = Integer.parseInt(mateIndex) - 1;
        if(mate == 4)
            return false;

        int x = player.heroArrayList.get(mate).x + 1;
        int y = player.heroArrayList.get(mate).y;
        int oldX = player.heroArrayList.get(heroIndex).x;
        int oldY = player.heroArrayList.get(heroIndex).y;

        // teleport to your teammate's back
        board.getTile(oldX, oldY).setHeroOn(false);
        player.heroArrayList.get(heroIndex).setPos(x, y);
        board.getTile(x,y).setHeroOn(true);

        return true;
    }

    /**
     * Moves the given player back to heros' nexus.
     *
     * @param heroIndex Index the hero to be moved back.
    */
    public void back(int heroIndex) {
        int y;
        int oldX = player.heroArrayList.get(heroIndex).x;
        int oldY = player.heroArrayList.get(heroIndex).y;
        board.getTile(oldX, oldY).setHeroOn(false);
        if (oldY < 2)
            y = 0;
        else if (oldY < 5)
            y = 3;
        else
            y = 6;

        player.heroArrayList.get(heroIndex).setPos(7, y);
        board.getTile(7, y).setHeroOn(true);
    }

    /**
     * Handles heros' fight events.
     *
     * Priority of target pick: Hero first fight the monster at same tile,
     * then the one in front of him, then diagonal one.
     *
     * @param hero A Hero object to fight.
     * @return Trigger fight event successfully or not?
     */
    public boolean fight(Hero hero) {
        int x = hero.x;
        int y = hero.y;
        Monster monster = null;
        int monsterIndex = -1;

        //check whether a monster at same tile
        if (board.getTile(x, y).getMonsterOn()){
            for (int i = 0; i < monsterArrayList.size(); i++) {
                if (monsterArrayList.get(i).getX() == x && monsterArrayList.get(i).getY() == y) {
                    monster = monsterArrayList.get(i);
                    monsterIndex = i;
                }
            }
        }
        // Check whether a monster at front tile
        else if(board.getTile(x - 1, y).getMonsterOn()){
            for (int i = 0; i < monsterArrayList.size(); i++) {
                if (monsterArrayList.get(i).getX() == x - 1 && monsterArrayList.get(i).getY() == y) {
                    monster = monsterArrayList.get(i);
                    monsterIndex = i;
                }
            }
        }
        // Check whether a monster at right tile
        else if (y != board.getNumColumn() && board.getTile(x, y + 1).getMonsterOn()) {
            for (int i = 0; i < monsterArrayList.size(); i++) {
                if (monsterArrayList.get(i).getX() == x && monsterArrayList.get(i).getY() == y + 1) {
                    monster = monsterArrayList.get(i);
                    monsterIndex = i;
                }
            }
        }

        // Check if there's a monster at diagonal or right tile if hero on left tile
        // No need to check if there's a monster at diagonal or left tile if hero on right tile
        // because monsters only move forward or fight, and they are spawned on right tile
        else if (y != board.getNumColumn() && board.getTile(x - 1, y + 1).getMonsterOn()) {
            for (int i = 0; i < monsterArrayList.size(); i++) {
                if (monsterArrayList.get(i).getX() == x - 1 && monsterArrayList.get(i).getY() == y + 1) {
                    monster = monsterArrayList.get(i);
                    monsterIndex = i;
                }
            }
        }

        if (monster == null) {
            System.out.println(Color.RED + "You have no target to initiate a fight. ");
            return false;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print(Color.YELLOW + "Do you want to regular attack or cast a spell? 1. regular 2. spell ");
        String decision = sc.next();
        while (!decision.matches("^[1-2]$")) {
            System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
            decision = sc.next();
        }
        switch (decision){
            case "1":
                // regular attack
                hero.regularAttack(monster);

                // check whether the monster is dead
                if (monster.getCurrentHP() <= 0) {
                    //if yes, award the hero and remove the dead monster from list
                    System.out.println(Color.GREEN + hero.getName() + " just killed " + monster.getName());
                    hero.win();
                    board.getTile(monster.getX(), monster.getY()).setMonsterOn(false);
                    monsterArrayList.remove(monsterIndex);
                }
                break;
            case "2":
                // let hero cast a spell if learned any
                if (hero.getLearnedSpell().size() > 0)
                    hero.castSpell(monster);
                else {
                    System.out.println(Color.RED + "There is a monster but you haven't learned any spell, retry.");
                    return false;
                }

                // check whether the monster is dead
                if (monster.getCurrentHP() <= 0) {
                    //if yes, award the hero and remove the dead monster from list
                    System.out.println(Color.GREEN + hero.getName() + " just killed " + monster.getName());
                    hero.win();
                    board.getTile(monster.getX(), monster.getY()).setMonsterOn(false);
                    monsterArrayList.remove(monsterIndex);
                }
                break;
        }
        monsterArrayList.get(monsterIndex).displayInfo();

        return true;
    }

    /**
     * Handles monsters' fight events.
     */
    public void monsterTime() {
        for (Monster monster : monsterArrayList) {
            int x = monster.getX();
            int y = monster.getY();
            // if there's a hero at same tile, attack
            if (board.getTile(x, y).getHeroOn()) {
                for (int j = 0; j < player.heroArrayList.size(); j++) {
                    if (player.heroArrayList.get(j).getX() == x && player.heroArrayList.get(j).getY() == y)
                        monster.regularAttack(player.heroArrayList.get(j));
                }
            }
            // if there's a hero at front tile, attack
            else if (board.getTile(x + 1, y).getHeroOn()) {
                for (int j = 0; j < player.heroArrayList.size(); j++) {
                    if (player.heroArrayList.get(j).getX() == x + 1 && player.heroArrayList.get(j).getY() == y)
                        monster.regularAttack(player.heroArrayList.get(j));
                }
            }
            // if there's a hero at left tile, attack
            else if (board.getTile(x, y - 1).getHeroOn()) {
                for (int j = 0; j < player.heroArrayList.size(); j++) {
                    if (player.heroArrayList.get(j).getX() == x && player.heroArrayList.get(j).getY() == y - 1)
                        monster.regularAttack(player.heroArrayList.get(j));
                }
            }
            // if there's a hero at diagonal tile, attack
            else if (board.getTile(x + 1, y - 1).getHeroOn()) {
                for (int j = 0; j < player.heroArrayList.size(); j++) {
                    if (player.heroArrayList.get(j).getX() == x + 1 && player.heroArrayList.get(j).getY() == y - 1)
                        monster.regularAttack(player.heroArrayList.get(j));
                }
            }
            // if no monster at front tile and no hero around, move forward
            else if (!board.getTile(x + 1, y).getMonsterOn() && !board.getTile(x, y).getHeroOn() && !board.getTile(x, y - 1).getHeroOn()) {
                board.getTile(x, y).setMonsterOn(false);
                monster.setPos(x + 1, y);
                board.getTile(x + 1, y).setMonsterOn(true);
            }
        }

        //check if any hero died
        for (int k = 0; k < player.heroArrayList.size(); k++) {
            if (player.heroArrayList.get(k).getCurrentHP() <= 0) {
                System.out.println(Color.RED + player.heroArrayList.get(k).getName() + " died during the fight.");
                //revive and punish
                player.heroArrayList.get(k).lose();
                //remove old position
                board.getTile(player.heroArrayList.get(k).getX(), player.heroArrayList.get(k).getY()).setHeroOn(false);
                //update new position
                player.heroArrayList.get(k).setPos(7, player.heroArrayList.get(k).getY());
                board.getTile(7, player.heroArrayList.get(k).getY()).setHeroOn(true);
            }
        }
    }
}
