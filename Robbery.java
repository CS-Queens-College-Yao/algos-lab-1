import java.util.ArrayList;

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
			return 2;
	}

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		 ArrayList <Integer> fraction = new ArrayList<>();
		 int value = 0;
		 int lastpick = 0;
		 int max = 0;
	       for(int i=0; i<sizes.length;i++) {
	    	   fraction.add(worths[i]/sizes[i]);
	       }
	       for(int i =0;i<fraction.size();i++) {
	    	   System.out.print("indext "+i+" "+fraction.get(i)+" ");
	       }
	      while(capacity >= 0) {
	    	  System.out.println("capacity :"+capacity);
	    	  int sum=0;
	     for (int i=0;i<fraction.size();i++) {
	    	 if(fraction.get(i)!= -1)
	    		 sum+= i;
	     }
	     if(sum == 0) {
	    	 return value;
	     }
	    	  max = fraction.get(0);
	      for(int i=0;i<fraction.size();i++) {
	    	
	    		   if(fraction.get(i) > max) {
	    			   max = fraction.get(i);
	    		  }
	        }
	       if(capacity - sizes[fraction.indexOf(max)]>=0) {
	    	   System.out.println(fraction.indexOf(max));
	    	   value += worths[fraction.indexOf(max)];
	    	   capacity -= sizes[fraction.indexOf(max)];
	    	   fraction.set(fraction.indexOf(max),-1);
	    	   lastpick =fraction.indexOf(max);
	    	   
	       }
	       else {
	    	   fraction.set(fraction.indexOf(max),-1);
	       }
	      }
         value-=worths[lastpick];
		// fill in here, change the return
		
		return value;
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
