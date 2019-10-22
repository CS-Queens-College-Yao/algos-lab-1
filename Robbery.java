import java.util.Arrays;

//You have a heist getaway sack with a capacity, and n items in front of you
//with sizes and worths. Figure out the maximum value you could
//get with the items.

//You are encouraged to make helper functions!

public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// fill in here, change the return
		// size of our item array
        int n = sizes.length;

        // base case when there is no more items to compare
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If weight of the nth item in the list is more than the capacity ,
        // then this item cannot be added in the sack
        if (sizes[n - 1] > capacity) {
            return maximizeRobWorthRecur(capacity, Arrays.copyOf(sizes, n - 1), Arrays.copyOf(worths, n - 1));
        } //
        // Now there are two cases :
        // a. include the n-th item in the sack
        // b. don't include it in the sack
        // such that the sack has items whose worth is "maximized"
        // 'iff' the n-th item is included in the sack we decrease the capacity of the sack by it's size
        else {
            return Math.max(worths[n - 1] + maximizeRobWorthRecur(capacity - sizes[n - 1], Arrays.copyOf(sizes, n - 1), Arrays.copyOf(worths, n - 1)),
                    maximizeRobWorthRecur(capacity, Arrays.copyOf(sizes, n - 1), Arrays.copyOf(worths, n - 1)));
        }
    }


	
	
	
	public int[][] maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// fill in here, change the return
		 int i, j, n = sizes.length;

	        // building the knapsack table denoted by K
	        int K[][] = new int[n + 1][capacity + 1];

	        // building the table in bottom up manner
	        for (i = 0; i <= n; i++) {
	            for (j = 0; j <= capacity; j++) {
	                if (i == 0 || j == 0) {
	                    K[i][j] = 0;
	                } else if (sizes[i - 1] <= j) {
	                    K[i][j] = Math.max(worths[i - 1] + K[i - 1][j - sizes[i - 1]], K[i - 1][j]);
	                } else {
	                    K[i][j] = K[i - 1][j];
	                }
	            }
	        }

	        return K; 
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
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
		int maxWorthBottomUp[][] = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
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
