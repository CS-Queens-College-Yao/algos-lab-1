

public class Robbery {
	public static void main(String[] args) {
		
		Robbery r = new Robbery();
		
		int bagCapacity = 20;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity,itemSizes, itemWorths,itemWorths.length);
		System.out.println("Recursive Version :");
		System.out.println("Max worth of the bag: " + maxWorthRecur+"\n\n\nDynamic Programming Version:");
		
		r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
	}
}