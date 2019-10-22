// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!
public class Robbery {

    // Using DP: Get the maximum value with capacity C and n items
    public int maximizeRobWorthRecur(
            int capacity,
            int[] sizes,
            int[] worths
    ) {
        int[] land_worths;
        int total_counter = 0;
        for(int item : sizes){
            total_counter += item;
        }
        land_worths = new int[total_counter];
        int counter = 0;
        for(int i = 0; i < sizes.length; i++){
            int size_item = sizes[i];
            for(int j = 0; j < size_item; j++){
                land_worths[counter] = worths[i];
                counter++;
            }
        }
        int[] sub_solutions = new int[capacity + 1];
        
        for (int i = 1; i <= capacity; i++) {
            int Max = -1;
            for (int j = 0; j < i; j++)
                Max = Math.max(Max, land_worths[j] + sub_solutions[i - j - 1]);
            sub_solutions[i] = Max;
        }
        return sub_solutions[capacity];

    }
    private int getMaxAsRecursive(int[] land_worth, int capacity){
        if (capacity <= 0) {
            return 0;
        }
        int Max = -1;
        for (int i = 0; i < capacity; i++) {
            Max = Math.max(Max, land_worth[i] + getMaxAsRecursive(land_worth, capacity - i - 1));
        }
        return Max;
    }
    public int maximizeRobWorthBottomUp(
            int capacity,
            int[] sizes,
            int[] worths
    ) {
        // fill in here, change the return
        int[] land_worths;
        int total_counter = 0;
        for(int item : sizes){
            total_counter += item;
        }
        land_worths = new int[total_counter];
        int counter = 0;
        for(int i = 0; i < sizes.length; i++){
            int size_item = sizes[i];
            for(int j = 0; j < size_item; j++){
                land_worths[counter] = worths[i];
                counter++;
            }
        }
        int[] sub_solutions = new int[capacity + 1];
        
        for (int i = 1; i <= capacity; i++) {
            int Max = -1;
            for (int j = 0; j < i; j++)
                Max = Math.max(Max, land_worths[j] + sub_solutions[i - j - 1]);
            sub_solutions[i] = Max;
        }
        return sub_solutions[capacity];
    }

    /**
     * Bonus: figure out which items exactly Takes in a DP DPTable Returns an
     * int array of the individual worths of the items you took
     */
    public int[] takeRobInventory(int[][] DPTable) {
        // fill in here, change the return
        return new int[DPTable.length];
    }

    public static void main(String[] args) {
        Robbery r = new Robbery();
        int bagCapacity = 40;
        int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
        int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};

        int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths);
        System.out.println("Max worth of the bag: " + maxWorthRecur);
        int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
        System.out.println("Max worth of the bag: " + maxWorthBottomUp);

        // Bonus: Fill in the helper method takeRobInventory that could help you
        //figure out which items go into the bag that make it max worth. Feel free
        //to change up the parameters and returned types + values of the helper
        // methods above.
        // int[] itemsPicked = r.takeRobInventory();
        // System.out.println("The items we take are worth:")
        // for (int i = 0; i < results.length; i++) {
        // 	System.out.print(results[i] + " ");
    }
}
