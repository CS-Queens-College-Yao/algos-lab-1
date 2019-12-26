/**
 * HolidaySpecial
 */

/**
 * This class implements a scheduler to assign cooks to steps in a special
 * recipe. There are three test cases in the main method. Please read through
 * the whole file before attempting to code the solution.
 *
 * You will only be graded on code you add to the makeShifts method.
 * Do not mess with the existing formatting and identation.
 * You don't need to use the helper methods, but if they come in handy setting
 * up a custom test case, feel free to use them.
 */
public class HolidaySpecial {
    /**
     * The actual greedy scheduler you will be implementing!
     *
     * @param numCooks    The number of cooks who can participate, m
     * @param numSteps    The number of steps in the recipe, n
     * @param signUpTable An easy lookup tool, signUpTable[x][Y] = cook X signed up or did not sign up for step Y.
     *                    Example:
     *                    signUpTable[1][3] = 1 if cook 1 signed up for Step 3
     *                    signUpTable[1][3] = 0 if cook 1 didn't sign up for Step 3
     * @return scheduleTable: a table similar to the signUpTable where scheduleTable[X][Y] = 1 means
     * cook X is assigned to step Y in an optimal schedule
     */

    public int[][] makeShifts(int numCooks, int numSteps, int[][] signUpTable) {
        // Your scheduleTable is initialized as all 0's so far. Your code will put 1's
        // in the table in the right places based on the return description
        int[][] scheduleTable = new int[numCooks + 1][numSteps + 1];

        int RecipesStep = 1;
        int start = 1;//starting position
        int end = 0;//ending position
        while (RecipesStep <= numSteps) {   // current recipes step does not exceeds max number of steps

            int cook = 0;                // keep track of Cook #
            int Max_continue = 0;           // keep track of most continue # of steps
            for (int i = 1; i <= numCooks; i++) {
                int current_continue = 0;
                for (int j = start; j <= numSteps; j++) {
                    if (signUpTable[i][j] == 1)        // if cook i signed up for step j
                        current_continue++;
                    else
                        break;
                }
                if (current_continue > Max_continue) {
                    Max_continue = current_continue;
                    cook = i; // cook # = [i] in signUpTable
                }
            }
            RecipesStep = RecipesStep + Max_continue;
            end = end + Max_continue;
            for (int s = start; s <= end; s++) {
                scheduleTable[cook][s] = signUpTable[cook][s];   // update the cook # does steps S to scheduleTable
            }
            start = end + 1;
        }

        return scheduleTable;


    }

    /**
     * Makes the convenient lookup table based on the steps each cook says they can do
     *
     * @param numSteps    the number of steps in the recipe
     * @param cookSignUps cook sign ups ex: {{1, 2, 4}, {3, 5}, {6, 7}}
     * @return a lookup table so if we want to know if cook x can do step y,
     * lookupTable[x][y] = 1 if cook x can do step y
     * lookupTable[x][y] = 0 if cook x cannot do step y
     */
    public int[][] makeSignUpLookup(int numSteps, int[][] cookSignUps) {
        int numCooks = cookSignUps.length;
        int[][] lookupTable = new int[numCooks + 1][numSteps + 1];
        for (int cook = 1; cook <= numCooks; cook++) {
            int[] signedUpSteps = cookSignUps[cook - 1];
            for (int i = 0; i < signedUpSteps.length; i++) {
                lookupTable[cook][signedUpSteps[i]] = 1;
            }
        }
        return lookupTable;
    }

    /**
     * This validates the input data about the recipe step sign-ups.
     *
     * @param numCooks the number of cooks
     * @param numSteps the number of steps
     * @param signUps  the data given about which steps each cook can do
     * @return true or false whether the input sign-ups match the given number of
     * cooks and steps, and whether all the steps are guaranteed at least
     * one cook.
     */
    public boolean inputsValid(int numCooks, int numSteps, int signUps[][]) {
        int cookSignUps = signUps.length;

        // Check if there are any cooks or signups
        if (numCooks < 1 || cookSignUps < 1) {
            System.out.println("You either did not put in any cook or any signups");
            return false;
        }

        // Check if the number of cooks and sign-up rows matches
        if (numCooks != cookSignUps) {
            System.out.println("You input " + numCooks + " cooks but your signup suggests " + signUps.length);
            return false;
        }

        // Check that all steps are guaranteed in the signups
        int[] stepsGuaranteed = new int[numSteps + 1];
        for (int i = 0; i < cookSignUps; i++) {
            for (int j = 0; j < signUps[i].length; j++) {
                stepsGuaranteed[signUps[i][j]] = 1;
            }
        }
        for (int step = 1; step <= numSteps; step++) {
            if (stepsGuaranteed[step] != 1) {
                System.out.println("Your signup is incomplete because not all steps are guaranteed.");
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the optimal schedule by listing which steps each cook will do
     * Example output is cook 1: 1, 3, 4
     *
     * @param schedule The table of 0's and 1's of the optimal schedule, where
     *                 schedule[x][y] means whether in the optimal schedule cook x is doing step y
     */
    public void printFinalSchedule(int[][] schedule) {
        for (int cook = 1; cook < schedule.length; cook++) {
            int[] curcookSchedule = schedule[cook];
            System.out.print("cook " + cook + ": ");
            for (int step = 1; step < curcookSchedule.length; step++) {
                if (curcookSchedule[step] == 1) {
                    System.out.print(step + " ");
                }
            }
            System.out.println("");
        }
    }
    /**
     * This sets up the scheduling test case and calls the scheduling method.
     *
     * @param recipeName the name of the recipe to print!
     * @param numCooks   the number of cooks
     * @param numSteps   the number of steps
     * @param signUps    which steps each cook can do, in order of cooks and steps
     */
    public void signUpScheduleShifts(String recipeName, int numCooks, int numSteps, int[][] signUps) {
        System.out.println("-----" + recipeName + "-----");
        if (!inputsValid(numCooks, numSteps, signUps)) {
            System.out.println("recipe signup info is invalid");
            return;
        }
        int[][] signUpsLookup = makeSignUpLookup(numSteps, signUps);
        int[][] schedule = makeShifts(numCooks, numSteps, signUpsLookup);
        printFinalSchedule(schedule);
        System.out.println("");
    }

    /**
     * You can make additional test cases using the same format. In fact the helper functions
     * I've provided will even check your test case is set up correctly. Do not touch any of
     * of the existing lines. Just make sure to comment out or delete any of your own test cases
     * when you submit. The three recipe test cases existing in this main method should be
     * the only output when running this file.
     */
    public static void main(String args[]) {
        HolidaySpecial sp = new HolidaySpecial();

        // recipe 1: Example 1 from README, 4 cooks, 8 steps
        int[][] cookSignUps1 = {{2, 3, 4, 5, 6}, {5, 7, 8}, {1, 3, 4, 8}, {1, 5, 7, 8}};
        sp.signUpScheduleShifts("Homemade cranberry bread", 4, 8, cookSignUps1);

        // recipe 2: Example 2 from README, 3 cooks, 6 steps:
        int[][] cookSignUps2 = {{2, 3, 4, 5}, {1, 2, 3, 4}, {1, 2, 4, 5, 6}};
        sp.signUpScheduleShifts("Daal", 3, 6, cookSignUps2);

        // recipe 3: 6 cooks, 11 steps
        int[][] cookSignUps3 = {{7, 10, 11}, {2, 3, 4, 5, 7}, {1, 5, 10}, {8, 9, 10}, {5, 6, 7, 8}, {1, 3, 4, 8}};
        sp.signUpScheduleShifts("Seafood Paella", 6, 11, cookSignUps3);
    }
}