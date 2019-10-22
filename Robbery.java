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
		// fill in here, change the return
			return knapSackRecur(capacity, sizes, worths, worths.length);
	}

	public int max(int a, int b) {return (a>b)? a:b;}
	
	public int knapSackRecur(int cap, int weights[], int values[], int n){
		if (n ==0 || cap ==0){
			return 0;
		}
		if (weights[n-1] > cap){
			return knapSackRecur(cap, weights, values, n-1);
		}
		else return max(values[n-1] + knapSackRecur(cap-weights[n-1], weights, values, n-1), knapSackRecur(cap, weights, values, n-1));

	}

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// fill in here, change the return
		return knapSackBottomUp(capacity, sizes, worths, worths.length);
	}
	public int knapSackBottomUp(int cap, int weights[], int values[], int n){
		int i, w;
		int K[][] = new int [n+1][cap+1];


		for(i=0;i<=n;i++){
			for(w=0;w<=cap;w++){
				if(i==0 || w==0){
					K[i][w]=0;
				}
				else if (weights[i-1]<=w){
					K[i][w] = max(values[i-1] + K[i-1][w-weights[i-1]], K[i-1][w]);
				}
				else{
					K[i][w]=K[i-1][w];
				}
			}
		}
		return K[n][cap];

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
