import java.util.ArrayList;
import java.util.Scanner;

/**
 * LMH_Logic class contains all information needed to perform
 * full functionality of Legends: Heroes and Monsters.
 */
public class LMH_Logic {
    /**
     * Each game contains:
     * 1. One player(max 3 heroes)
     * 2. Monsters(same number as heroes)
     * 3. Character Library to pick heroes and random create appropriate monsters
     * 4. Market for heroes to buy/sell
     * 5. Board for players to move
     * 6. Coordinates of heroes
     */
    LMHPlayer player;
    ArrayList<Monster> monsterArrayList = new ArrayList<>();
    CharacterLibrary chl;
    Market market;
    LMHBoard board;
    public int[] location = new int[]{0, 0};

    /**
     * No-arg constructor
     */
    LMH_Logic(){}

    /**
     * Prints welcome messages.
     */
    private void printWelcomeMsg() {
        String banner =
            Color.BLUE +
            ".__                                           .___       " + "\n" +
            "|  |   ____   ____    ____   ____   ____    __| _/       " + "\n" +
            "|  | _/ __ \\ /    \\  / ___\\_/ __ \\ /    \\  / __ |   " + "\n" +
            "|  |_\\  ___/|   |  / /_/  >  ___/|   |  \\/ /_/ |       " + "\n" +
            "|____/\\___  >___|  /\\___  / \\___  >___|  /\\____ |    " + "\n" +
            "          \\/     \\//_____/      \\/     \\/      \\/ z " + Color.RESET + "\n";

        System.out.println(banner);

        String swordArt =
            Color.RED +
            "            (O)                                            " + "\n" +
            "            <M                                             " + "\n" +
            "o          <M  Wecome to Legends: Monsters and Heroes      " + "\n" +
            "/| ......  /:M\\---------------------------------,,,,,,    " + "\n" +
            "(O)[]XXXXXX[]I:K+}=====<{H}>===================-------->   " + "\n" +
            "\\| ^^^^^^  \\:W/---------------------------------''''''   " + "\n" +
            "o          <W  Have fun!                                   " + "\n" +
            "            <W                                             " + "\n" +
            "            (O)                                            " + Color.RESET + "\n";

        System.out.println(swordArt);
    }

    /**
     * Initialize and generate necessary components of the game.
     */
    public void prep() {
        Scanner sc = new Scanner(System.in);

        printWelcomeMsg();

        System.out.print(Color.YELLOW + "I need your name first: " + Color.RESET);
        String pName = sc.next();

        this.player = new LMHPlayer(pName);
        this.chl = new CharacterLibrary();
        this.market = new Market();
        this.board = new LMHBoard();
        this.board.setBoard();

        // create heroes
        HeroCreation hc = new HeroCreation();
        hc.prep(player);

        // init hero positions on map
        board.getCell(0, 0).setHeroOn(true);

        // after all components are ready, start the game.
        start();
        sc.close();
    }

    /**
     * At this point, we display the map, and let player give instructions
     * after any instruction completes, the loop will go again unless player
     * wants to leave
     */
    public void start() {
        boolean isQuit = false;

        do {
            for (int i = 0; i < player.heroArrayList.size(); i++) {
                Hero hero = player.heroArrayList.get(i);
                String heroAction = hero.takeAction(board);
                if (heroAction.equalsIgnoreCase("Q")) isQuit = true;
                else if (!heroAction.equalsIgnoreCase("I")) playerMove(hero, heroAction);
            }
        } while(!isQuit);

        System.out.println(Color.RESET + "Goodbye. " + player.getPlayerName());
    }

    /**
     * This function let player handle event after moving to a certain direction.
     *
     * @param hero The hero who will hanle an event.
     */
    public void playerMove(Hero hero, String fromMark) {
        // player encounter monsters if that cell is not market
        if (fromMark.equals(".")) {
            double luck = Math.random();
            // in this game, player has 30% chance encounter monsters
            if (luck < 0.3) fight();
        }
        // or player will be asked whether he/she goes shopping
        else {
            Scanner sc = new Scanner(System.in);
            String decision;
            System.out.print(Color.YELLOW + "You've arrived a market, input \"Y\" to buy/sell: ");
            decision = sc.next();

            if (decision.equalsIgnoreCase("Y")) {
                // display the hero's name, level, and money
                hero.displayInfoInMarket();

                // shopping!
                market.shopping(hero);
            }
        }
    }

    /**
     * Prints monsters
     */
    private void diaplayMonsters() {
        if (monsterArrayList.isEmpty()) return;

        System.out.println(Color.PURPLE + "Monsters: " + Color.RESET);

        String border = Color.PURPLE +
            "-------------------------------------------------------------------" +
            Color.RESET + "\n";

        System.out.print(border);
        System.out.println("Name                       Level    HP        Damage    Defense");
        System.out.print(border);

        for (int i = 0; i < monsterArrayList.size(); i++) {
            Monster m = monsterArrayList.get(i);

            String name = (i + 1) + ". " + m.getName();
            String hp = m.getCurrentHP() + "/" + m.getHP();
            int level = m.getLevel();
            int damage = m.getDamage();
            int defense = m.getDefense();

            System.out.printf("%-26s %-8d %-9s %-9d %-11d", name, level, hp, damage, defense);
            System.out.println();
        }
        System.out.println(border);
    }

    /**
     * This method consists round turn fight until all heroes or all monsters die
     */
    public void fight() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Color.BLACK + "You encountered (a) monster(s), kill them.");

        // generate exact number of monsters as heroes
        int monsterNum = player.heroArrayList.size();

        // btw, each monster has same level as heroes
        int[] heroLV = new int[monsterNum];
        for (int i = 0; i < monsterNum; i++) {
            heroLV[i] = player.heroArrayList.get(i).getLevel();
        }

        // search for monster with same level as hero and add it in monster list
        for (int i = 0; i < monsterNum; i++) {
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

        // fight begins here
        int aliveHero = player.heroArrayList.size();
        int round = 0;
        int targetNo;

        while (aliveHero != 0 && monsterNum != 0) {
            // hero goes first
            if (round % 2 == 0) {
                // first display in-fight info for both sides
                player.displayInfoInFight();
                diaplayMonsters();

                for (int i = 0; i < player.heroArrayList.size(); ++i) {
                    if (player.heroArrayList.get(i).getCurrentHP() > 0) {
                        // if current hero hasn't died, ask player to pick an action
                        System.out.println(Color.GREEN + player.heroArrayList.get(i).getName() +
                                ", your turn:\n1. Regular Attack\n2. Cast a spell\n3. Take a potion/Switch equipments");
                        String decision = sc.next();
                        while (!decision.matches("^[1-3]$")) {
                            System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                            decision = sc.next();
                        }
                        switch (decision) {
                            case "1":
                                targetNo = i;
                                // handle cases when any monster already dead
                                while (monsterArrayList.size() < targetNo + 1)
                                    targetNo--;

                                // regular attack
                                player.heroArrayList.get(i).regularAttack(monsterArrayList.get(targetNo));

                                // check whether the monster is dead
                                if (monsterArrayList.get(targetNo).getCurrentHP() < 0) {
                                    //if yes, decrease monster number and remove the dead monster from list
                                    System.out.println(Color.GREEN + player.heroArrayList.get(i).getName() +
                                            " just killed " + monsterArrayList.get(targetNo).getName());
                                    monsterNum--;
                                    monsterArrayList.remove(targetNo);
                                }
                                break;
                            case "2":
                                targetNo = i;
                                // handle cases when any monster already dead
                                while (monsterArrayList.size() < targetNo + 1)
                                    targetNo--;

                                // let hero cast a spell if learned any
                                if (player.heroArrayList.get(i).getLearnedSpell().size() > 0)
                                    player.heroArrayList.get(i).castSpell(monsterArrayList.get(targetNo));
                                else {
                                    System.out.println(Color.RED + "You haven't learned a spell, retry.");
                                    i--;
                                }

                                // check whether the monster is dead
                                if (monsterArrayList.get(targetNo).getCurrentHP() < 0) {
                                    //if yes, decrease monster number and remove the dead monster from list
                                    System.out.println(Color.GREEN + player.heroArrayList.get(i).getName() +
                                            " just killed " + monsterArrayList.get(targetNo).getName());
                                    monsterNum--;
                                    monsterArrayList.remove(targetNo);

                                }
                                break;
                            case "3":
                                // do one in-fight inventory operation
                                player.heroArrayList.get(i).itemOp(true);
                                break;
                        }
                    }
                }
            }
            // monsters' turn
            else {
                for (int i = 0; i < monsterArrayList.size(); i++) {
                    targetNo = i;
                    // handle cases when any hero died
                    if (player.heroArrayList.get(targetNo).getCurrentHP() < 0) {
                        //find first alive hero to attack
                        for (int j = 0; j < player.heroArrayList.size(); j++) {
                            if (player.heroArrayList.get(j).getCurrentHP() > 0) {
                                targetNo = j;
                                break;
                            }
                        }
                    }
                    monsterArrayList.get(i).regularAttack(player.heroArrayList.get(targetNo));
                    if(player.heroArrayList.get(targetNo).getCurrentHP() < 0){
                        aliveHero--;
                        System.out.println(Color.RED + player.heroArrayList.get(targetNo).getName() + " got killed.");
                    }
                }
            }
            // after each round, heroes regain
            for (int i = 0; i < player.heroArrayList.size(); i++) {
                if(player.heroArrayList.get(i).getCurrentHP() > 0)
                    player.heroArrayList.get(i).heal();
            }
            round++;
        }

        // all heroes died
        if(aliveHero == 0) {
            System.out.println(Color.RED + "You lost the fight, all heroes lost half their money.");
            for(int i = 0; i < player.heroArrayList.size(); i++)
                player.heroArrayList.get(i).lose();
        }
        // heroes win
        else {
            for(int i = 0; i < player.heroArrayList.size(); i++) {
                //reward each hero based on if it's alive
                if(player.heroArrayList.get(i).currentHP > 0) {
                    System.out.println(Color.GREEN + player.heroArrayList.get(i).getName() +
                            ", your survival will be rewarded!");
                    player.heroArrayList.get(i).win();

                    // after gaining experience, check if enough to achieve next level
                    if(player.heroArrayList.get(i).getCurrentXP() >= player.heroArrayList.get(i).getXP())
                        player.heroArrayList.get(i).LVUP();
                }
                else {
                    System.out.println(Color.GREEN + player.heroArrayList.get(i).getName() +
                            ", you're revived by your friends");
                    player.heroArrayList.get(i).revive();
                }
            }
        }
    }

    /**
     * Used to switch equipments, drink potion, and learn spells
     * @param inFight whether during a fight
     * @param inFightHeroNo if during a fight, this indicates the specific hero
     */
    public void itemOp(boolean inFight, int inFightHeroNo) {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        String isDone;
        String decision;
        int heroNo;

        while (!done) {
            // if not in fight
            if(!inFight) {
                //display all heroes
                for (int i = 0; i < player.heroArrayList.size(); i++) {
                    System.out.println(Color.GREEN + (i + 1) + ". " + player.heroArrayList.get(i).getName());
                }
                System.out.print(Color.YELLOW + "Which hero you want to operate? ");
                decision = sc.next();
                while (!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                        Integer.parseInt(decision) > player.heroArrayList.size()) {
                    System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                    decision = sc.next();
                }
                // assign the value after validate hero index
                heroNo = Integer.parseInt(decision) - 1;
            }
            else
                // if during a fight, hero index is ready
                heroNo = inFightHeroNo;

            //Then display equipped items and inventory
            player.heroArrayList.get(heroNo).displayEquips();
            System.out.print(Color.YELLOW + "Which item you want to operate: ");
            decision = sc.next();
            //validate input
            while(!decision.matches("[0-9]*") || Integer.parseInt(decision) < 1 ||
                    Integer.parseInt(decision) > player.heroArrayList.get(heroNo).getStorage().size() + 2) {
                System.out.print(Color.RED + "Your selection is invalid, try again: " + Color.RESET);
                decision = sc.next();
            }
            int itemNo = Integer.parseInt(decision);

            // take off equipped weapon
            if(itemNo < 3)
                player.heroArrayList.get(heroNo).takeOff(itemNo);
            else {
                //inventory operation so -3
                itemNo -= 3;
                // equip weapon/armor
                if (player.heroArrayList.get(heroNo).getStorage().get(itemNo) instanceof Weapon ||
                        player.heroArrayList.get(heroNo).getStorage().get(itemNo) instanceof Armor)
                    player.heroArrayList.get(heroNo).takeOn(itemNo);
                // drink potion
                else if (player.heroArrayList.get(heroNo).getStorage().get(itemNo) instanceof Potion) {
                    player.heroArrayList.get(heroNo).drinkPotion(
                            (Potion) player.heroArrayList.get(heroNo).getStorage().get(itemNo));
                    player.heroArrayList.get(heroNo).getStorage().remove(itemNo);
                }
                // learn a spell
                else {
                    if(!inFight)
                        player.heroArrayList.get(heroNo).learnSpell(itemNo);
                    else {
                        System.out.println(Color.RED + "You can't learn a spell during a fight!");
                        itemOp(true, inFightHeroNo);
                    }
                }
            }

            //ask if player wants another operation if not during a fight
            if(!inFight) {
                System.out.print(Color.YELLOW + "Are you done? Input \"N\" to do another operation: ");
                isDone = sc.next();
                if(!isDone.equalsIgnoreCase("N"))
                    done = true;
            }
            else
                done = true;
        }
    }
}
