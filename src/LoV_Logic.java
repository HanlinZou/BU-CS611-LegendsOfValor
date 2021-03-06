import java.util.ArrayList;
import java.util.Scanner;

public class LoV_Logic {

    LMHPlayer player;
    ArrayList<Monster> monsterArrayList = new ArrayList<>();
    CharacterLibrary chl;
    Market market;
    LoVBoard board;

    /**
     * Necessary preparation at the beginning of the LoV game.
     * Initialize player, game board, market and creates heroes, etc.
    */
    public void prep() {
        Scanner sc = new Scanner(System.in);

        System.out.print(Color.getColor().YELLOW + "I need your name first: " + Color.getColor().RESET);
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

            // Generate new monsters every 8 rounds
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
                System.out.print(Color.getColor().YELLOW + "Hero No." + (i + 1) + ", " +
                    player.heroArrayList.get(i).getName() + ". Pick a move: ");
                playerChoice = sc.next();

                // whether player wants to move
                if (playerChoice.equalsIgnoreCase("W") || playerChoice.equalsIgnoreCase("S") ||
                    playerChoice.equalsIgnoreCase("A") || playerChoice.equalsIgnoreCase("D")) {
                    if (!playerMove(hero, playerChoice)) i--;  // failed to move
                    if (hero.x == 0){
                        board.displayBoard();
                        System.out.println(Color.getColor().ORANGE + "Heroes won!" + Color.getColor().RESET);
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
                    System.out.print(Color.getColor().YELLOW + "Do you want to switch your weapon/armor, " +
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
                    System.out.println(Color.getColor().RED + "Invalid action, please re-enter it." + Color.getColor().RESET);
                    i--;
                }
                else
                    break;
            }
            boolean result = monsterTime();
            if (result){
                board.displayBoard();
                System.out.println(Color.getColor().RED + "Monsters won!" + Color.getColor().RESET);
                playerChoice = "Q";
                break;
            }
            numRound++;
        } while(!playerChoice.equalsIgnoreCase("Q"));
        System.out.println(Color.getColor().RESET + "Goodbye. " + player.getPlayerName());
    }

    /**
     * Handles monster generation.
     * Generates monsters with the same level as heroes.
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
     * Handles events on nexus cells.
     * Heroes can choose to buy or sell items on nexus cells.
    */
    public void nexusOp(int heroIndex){
        Scanner sc = new Scanner(System.in);
        String decision;
        System.out.print(Color.getColor().YELLOW + "Hero No." + (heroIndex+1) + ", " +
            player.heroArrayList.get(heroIndex).getName() + ". You're at Nexus, input \"Y\" to buy/sell: ");
        decision = sc.next();

        if (decision.equalsIgnoreCase("Y")) {
            boolean leave = false;
            while (!leave) {
                // display all heroes' names, levels, and money
                player.displayInfoInMarket();
                market.shopping(player.heroArrayList.get(heroIndex));
                // make sure player wants to leave
                System.out.print(Color.getColor().YELLOW + "Are you done shopping? 'N' = stay, any other keys = leave ");
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
            System.out.println(Color.getColor().RED + "Your can't go outside the map, please re-enter your move: " + Color.getColor().RESET);
            return false;
        } else if (
            (direction.equalsIgnoreCase("A") && (board.getTile(hero.x, hero.y - 1) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("D") && (board.getTile(hero.x, hero.y + 1) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("W") && (board.getTile(hero.x - 1, hero.y) instanceof Inaccessible)) ||
            (direction.equalsIgnoreCase("S") && (board.getTile(hero.x + 1, hero.y) instanceof Inaccessible))
        ) {
            // Move to Inaccessible Cell
            System.out.println(Color.getColor().RED + "Inaccessible, please re-enter your move: " + Color.getColor().RESET);
            return false;
        }

        int fromX = hero.x;
        int fromY = hero.y;

        if (direction.equalsIgnoreCase("W")) {
            //hero at left tile and there's a monster at right tile or at same tile
            if(fromY % 3 == 0 &&
                (board.getTile(fromX, fromY).getMonsterOn() || board.getTile(fromX, fromY + 1).getMonsterOn())) {
                System.out.println(Color.getColor().RED + "Kill the monster first, please re-enter your move: " + Color.getColor().RESET);
                return false;
            }
            //hero at right tile and there's a monster at left tile or at same tile
            else if(fromY % 3 != 0 &&
                (board.getTile(fromX, fromY).getMonsterOn() || board.getTile(fromX, fromY - 1).getMonsterOn())) {
                System.out.println(Color.getColor().RED + "Kill the monster first, please re-enter your move: " + Color.getColor().RESET);
                return false;
            }
            // avoid two heroes at same tile
            if(board.getTile(fromX - 1, fromY).getHeroOn()){
                System.out.println(Color.getColor().RED + "A hero in front of you, please re-enter your move: " + Color.getColor().RESET);
                return false;
            }
        }
        else if (direction.equalsIgnoreCase("S") && board.getTile(fromX + 1, fromY).getHeroOn()) {
            System.out.println(Color.getColor().RED + "A hero is behind you, please re-enter your move: " + Color.getColor().RESET);
            return false;
        }
        else if (direction.equalsIgnoreCase("A") && board.getTile(fromX, fromY - 1).getHeroOn()) {
            System.out.println(Color.getColor().RED + "A hero is on your left, please re-enter your move: " + Color.getColor().RESET);
            return false;
        }
        else if (direction.equalsIgnoreCase("D") && board.getTile(fromX, fromY + 1).getHeroOn()) {
            System.out.println(Color.getColor().RED + "A hero is on your right, please re-enter your move: " + Color.getColor().RESET);
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
     * Teleports the given player to one lane's Nexus.
     *
     * @param heroIndex Index the hero to be teleported.
    */
    public boolean teleport(int heroIndex) {
        String laneIndex;
        Scanner sc = new Scanner(System.in);
        System.out.print("Which lane you want to teleport to? 1 top lane 2. mid lane 3. bot lane 4. cancel tp: ");
        laneIndex = sc.next();

        while (!laneIndex.matches("^[1-4]$") ||
            (laneIndex.equals("1") && board.getTile(7, 0).getHeroOn() && board.getTile(7, 1).getHeroOn()) ||
            (laneIndex.equals("2") && board.getTile(7, 3).getHeroOn() && board.getTile(7, 4).getHeroOn()) ||
            (laneIndex.equals("3") && board.getTile(7, 6).getHeroOn() && board.getTile(7, 7).getHeroOn())) {
            if (!laneIndex.matches("^[1-4]$"))
                System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            else if(laneIndex.equals("1") && board.getTile(7, 0).getHeroOn() && board.getTile(7, 1).getHeroOn()){
                System.out.print(Color.getColor().RED + "Top lane's Nexus if full, try again: " + Color.getColor().RESET);
            }
            else if(laneIndex.equals("2") && board.getTile(7, 3).getHeroOn() && board.getTile(7, 4).getHeroOn()){
                System.out.print(Color.getColor().RED + "Mid lane's Nexus if full, try again: " + Color.getColor().RESET);
            }
            else if(laneIndex.equals("3") && board.getTile(7, 6).getHeroOn() && board.getTile(7, 7).getHeroOn()){
                System.out.print(Color.getColor().RED + "Bot lane's Nexus if full, try again: " + Color.getColor().RESET);
            }
            laneIndex = sc.next();
        }

        int lane = Integer.parseInt(laneIndex);
        int oldX = player.heroArrayList.get(heroIndex).x;
        int oldY = player.heroArrayList.get(heroIndex).y;
        int x = 7;
        int y;
        if(lane == 4)
            return false;
        else if(lane == 3){
            if(board.getTile(7, 6).getHeroOn())
                y = 7;
            else
                y = 6;
        }
        else if(lane == 2){
            if(board.getTile(7, 3).getHeroOn())
                y = 4;
            else
                y = 3;
        }
        else{
            if(board.getTile(7, 0).getHeroOn())
                y = 1;
            else
                y = 0;
        }
        // teleport to your teammate's back
        board.getTile(oldX, oldY).setHeroOn(false);
        player.heroArrayList.get(heroIndex).setPos(x, y);
        board.getTile(x,y).setHeroOn(true);

        return true;
    }

    /**
     * Moves the given player back to heroes' nexus.
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
     * Handles heroes' fight events.
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
            System.out.println(Color.getColor().RED + "You have no target to initiate a fight. ");
            return false;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print(Color.getColor().YELLOW + "Do you want to regular attack or cast a spell? 1. regular 2. spell ");
        String decision = sc.next();
        while (!decision.matches("^[1-2]$")) {
            System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            decision = sc.next();
        }
        switch (decision){
            case "1":
                // regular attack
                hero.regularAttack(monster);

                // check whether the monster is dead
                if (monster.getCurrentHP() <= 0) {
                    //if yes, award the hero and remove the dead monster from list
                    System.out.println(Color.getColor().GREEN + hero.getName() + " just killed " + monster.getName());
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
                    System.out.println(Color.getColor().RED + "There is a monster but you haven't learned any spell, retry.");
                    return false;
                }

                // check whether the monster is dead
                if (monster.getCurrentHP() <= 0) {
                    //if yes, award the hero and remove the dead monster from list
                    System.out.println(Color.getColor().GREEN + hero.getName() + " just killed " + monster.getName());
                    hero.win();
                    board.getTile(monster.getX(), monster.getY()).setMonsterOn(false);
                    monsterArrayList.remove(monsterIndex);
                }
                break;
        }
        monsterArrayList.get(monsterIndex).displayInfoInFight(true, -1);

        return true;
    }

    /**
     * Handles monsters' fight events.
     */
    public boolean monsterTime() {
        for (Monster monster : monsterArrayList) {
            int x = monster.getX();
            int y = monster.getY();

            /*
             * 1. same tile
             * 2. front tile
             * 3. left tile
             * 4. diagonal tile
             */
            int[] dx = new int[]{0, 1,  0,  1};
            int[] dy = new int[]{0, 0, -1, -1};

            boolean isFight = false;

            // Attack hero if possible
            for (int i = 0; i < dx.length; i++) {
                int toX = x + dx[i];
                int toY = y + dy[i];

                if (board.getTile(toX, toY).getHeroOn()) {
                    for (Hero hero : player.heroArrayList) {
                        if (hero.getX() == toX && hero.getY() == toY) {
                            monster.regularAttack(hero);
                            hero.displayInfoInFight(true, -1);
                            break;
                        }
                    }
                    isFight = true;
                    break;
                }
            }

            // If no fight, no monster at front tile and no hero around, move forward
            if (!isFight && !board.getTile(x + 1, y).getMonsterOn() && !board.getTile(x, y).getHeroOn() && !board.getTile(x, y - 1).getHeroOn()) {
                board.getTile(x, y).setMonsterOn(false);
                monster.setPos(x + 1, y);
                board.getTile(x + 1, y).setMonsterOn(true);
                if(board.getTile(x + 1, y) instanceof Nexus)
                    return true;
            }
        }



        // check if any hero died
        for (int k = 0; k < player.heroArrayList.size(); k++) {
            if (player.heroArrayList.get(k).getCurrentHP() <= 0) {
                System.out.println(Color.getColor().RED + player.heroArrayList.get(k).getName() + " died during the fight.");
                //revive and punish
                player.heroArrayList.get(k).lose();
                //remove old position
                board.getTile(player.heroArrayList.get(k).getX(), player.heroArrayList.get(k).getY()).setHeroOn(false);
                //update new position
                player.heroArrayList.get(k).setPos(7, player.heroArrayList.get(k).getY());
                board.getTile(7, player.heroArrayList.get(k).getY()).setHeroOn(true);
            }
        }

        return false;
    }
}
