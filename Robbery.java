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
		
		return 2;
	}

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		//arrange items by value
		int[] values = new int[sizes.length];
		for(int i = 0; i < values.length; i++)
			values[i] = worths[i] / sizes[i];
		
		Arrays.sort(values);
		
		int pos = values.length - 1; //position of the item with the most value
		int itemWithMostValue = values[pos];
		int max_value = 0;
		
		while(capacity > 0 && capacity > itemWithMostValue){
			
			if(capacity - itemWithMostValue >= 0){
				capacity = capacity - itemWithMostValue;
				max_value = max_value + itemWithMostValue;
			}else if(pos > 0){ //switch to next item with the most value
				pos = pos - 1;
				itemWithMostValue = values[pos];
			}
			
		}
		
		return max_value;
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
