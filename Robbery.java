// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!

public class Robbery {

	public int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public int min(int a, int b) {
		if (a < b)
			return a;
		else
			return b;
	}

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(int capacity, int[] sizes, int[] worths) {
		// fill in here, change the return
		return 2;
	}

	public int maximizeRobWorthBottomUp(int capacity, int[] sizes, int[] worths) {
		// fill in here, change the return
		int[][] values = new int[sizes.length + 1][capacity + 1];
		for (int i = 0; i <= capacity; i++) {
			values[0][i] = 0;
		}

		for (int i = 1; i <= sizes.length; i++) {
			for (int k = 0; k < capacity; i++) {
				if (worths[i - 1] > k)
					values[i][k] = values[i - 1][k];
				else
					values[i][k] = max(i, k);
			}
		}
		return 2;
	}

	/**
	 * Bonus: figure out which items exactly Takes in a DP DPTable Returns an int
	 * array of the individual worths of the items you took
	 */
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = { 2, 25, 6, 13, 1, 15, 8, 5, 17, 4 };
		int[] itemWorths = { 35, 120, 900, 344, 29, 64, 67, 95, 33, 10 };

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);

		// Bonus: Fill in the helper method takeRobInventory that could help you
		// figure out which items go into the bag that make it max worth. Feel free
		// to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// System.out.print(results[i] + " ");
	}
}
