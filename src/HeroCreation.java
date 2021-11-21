import java.util.Scanner;

/**
 * HeroCreation adopts builder pattern to create a list of hero objects easily.
 * In this way, we don't need to deal with the complex creating steps outside it.
 */
public class HeroCreation {
    CharacterLibrary chl = new CharacterLibrary();
    private int numHeroes;

    HeroCreation() {
    }

    HeroCreation(int num){
        numHeroes = num;
    }

    /**
     * Interacts with player and creates a list of heroes.
     *
     * @param player A LMHPlayer object.
     */
    public void prep(LMHPlayer player) {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= numHeroes; i++) {
            // prompt user the position and specific hero to generate
            System.out.println("Select your No." + Color.getColor().RED + i + Color.getColor().RESET + " hero:");
            System.out.println(Color.getColor().GREEN + "1. Warriors  2. Sorcerers  3. Paladins" + Color.getColor().RESET);
            System.out.print(Color.getColor().YELLOW + "What position will your No." + i + " hero to be: " + Color.getColor().RESET);
            String posChoice = sc.next();

            while (!posChoice.matches("^[1-3]$")) {
                System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
                posChoice = sc.next();
            }

            int heroChoice = heroSelection(posChoice);
            createHero(player, Integer.parseInt(posChoice), heroChoice - 1);
        }
    }

    /**
     * Check if the input is an int and in the range of (min, max).
     *
     * @param input The input
     * @param min   Low bounder
     * @param max   High bounder
     * @return The input is valid or not.
     */
    private boolean checkIntInput(String input, int min, int max) {
        return input.matches("[0-9]*") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max;
    }

    /**
     * This function interact with player and let player pick the hero that he/she
     * wants
     *
     * @param posChoice position of heroes(war/sor/pal)
     * @return index of hero in the hero list
     */
    public int heroSelection(String posChoice) {
        Scanner sc = new Scanner(System.in);

        // display all heroes based on the position that player required
        if (posChoice.equals("1"))
            chl.displayWarrior();
        else if (posChoice.equals("2"))
            chl.displaySorcerer();
        else
            chl.displayPaladin();

        System.out.print(Color.getColor().YELLOW + "Pick your favorite one: " + Color.getColor().RESET);
        String heroChoice = sc.next();

        // after get the result, we verify if it's valid
        while(posChoice.equals("1") && !checkIntInput(heroChoice, 1, chl.warriorArrayList.size())) {
            System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            heroChoice = sc.next();
        }

        while(posChoice.equals("2") && !checkIntInput(heroChoice, 1, chl.sorcererArrayList.size())) {
            System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            heroChoice = sc.next();
        }

        while(posChoice.equals("3") && !checkIntInput(heroChoice, 1, chl.paladinArrayList.size())) {
            System.out.print(Color.getColor().RED + "Your selection is invalid, try again: " + Color.getColor().RESET);
            heroChoice = sc.next();
        }

        return Integer.parseInt(heroChoice);
    }

    /**
     * In this function, we generate the required hero and add it to player's hero
     * list after we acquired player's requirements
     *
     * @param player player
     * @param pos    position of heroes
     * @param rank   the index in that specified hero list
     */
    public void createHero(LMHPlayer player, int pos, int rank) {
        switch (pos) {
            case (1):
                player.heroArrayList.add(chl.warriorArrayList.remove(rank));
                break;
            case (2):
                player.heroArrayList.add(chl.sorcererArrayList.remove(rank));
                break;
            case (3):
                player.heroArrayList.add(chl.paladinArrayList.remove(rank));
                break;
            }
    }
}
