/**
 * Running Trials
 */
public class RunningTrials {
    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int days) {
    	int minTests = 0;
        
        if (possibleSpeeds <= 1) {
            return 1;
        }
       
        // If there is only 1 day test all possible speeds
        if (days == 1) {
            return possibleSpeeds;
        }
        
        // If there is 0 day, can't test so return 0
        if (days == 0)
            return 0;
        minTests = Integer.MAX_VALUE;
        int result = 0;
       
        // Comparison between training days on same speed vs
        // days testing, to finding the converging point
        for (int i = 1; i <= possibleSpeeds; ++i) {
            result = Math.max(runTrialsRecur(i - 1, days - 1),
                    runTrialsRecur(possibleSpeeds - i, days));
            if (result < minTests)
                minTests = result;
        }
        return minTests + 1;
    }


    public int runTrialsBottomUp(int possibleSpeeds, int days) {
        int minTests = 0;
        int tests[][] = new int[possibleSpeeds + 1][days + 1];
        // initialize the table. First column is 0 as buffer to prevent index out of bound error
        // 1 = lowest possible days
        for (int i = 1; i <= days; i++) {
            tests[1][i] = 1;
            tests[0][i] = 0;
        }
        // first row is for buffer to prevent index out of bound error
        // second row is to initialize with all possible speeds
        for (int j = 1; j <= possibleSpeeds; j++) {
            tests[j][0] = 0;
            tests[j][1] = j;
        }
        int result = 0;
        //Outer 2 nested loop is to traverse through the table
        for (int i = 2; i <= days; i++) {
            for (int j = 2; j <= possibleSpeeds; j++) {
                tests[j][i] = Integer.MAX_VALUE; //initializing the table to max for empty table section
                //checking through of all possible speeds
                for (int k = 1; k <= j; k++) {
                    //optimal substructure that compares between training days on same speed vs
                    //days testing, to finding the converging point, updating based on higher minTest result
                    //between the two
                    result = 1 + Math.max(tests[k - 1][i - 1], tests[j - k][i]);
                    if (result < tests[j][i])
                        tests[j][i] = result;
                }
            }
        }
        //return the last row last column
        minTests = tests[possibleSpeeds][days];
        return minTests;
    }

    public static void main(String args[]) {
        RunningTrials running = new RunningTrials();
       //int s, d;
       //s = 10;
       //d = 1;

        //Leave the lines below as they are
        int minTrials1Recur = running.runTrialsRecur(12, 5);
        int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
        int minTrials2Recur = running.runTrialsRecur(6, 2);
        int minTrials2Bottom = running.runTrialsBottomUp(6, 2);
        System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
        System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);

    }
}