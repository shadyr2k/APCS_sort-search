import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scorecard { 
	
	public HashMap<String,Integer> score = new HashMap<String,Integer>();	
	
	public Scorecard () {
		score.put("Ones", -1);
		score.put("Twos", -1);
		score.put("Threes", -1);
		score.put("Fours", -1);
		score.put("Fives", -1);
		score.put("Sixes", -1);
		score.put("Three of a Kind", -1);
		score.put("Four of a Kind", -1);
		score.put("Full House", -1);
		score.put("Small Straight", -1);
		score.put("Large Straight", -1);
		score.put("Chance", -1);
		score.put("Yahtzee", -1);
	}
	
	public boolean canPut(String s) {
		return score.get(s) == -1;
	}
	
	public int getTotal() {
		int i = 0;
		for(Object k : score.keySet()) {
			i += score.get(k);
		}
		return i;
	}
	
	public int place(String s, int[] a) {
		ArrayList<Integer> i = (ArrayList<Integer>) IntStream.of(a).boxed().collect(Collectors.toList());
		switch(s.toLowerCase()) {
		case "ones": return Collections.frequency(i, 1);
		case "twos": return 2 * Collections.frequency(i, 2);
		case "threes": return 3 * Collections.frequency(i, 3);
		case "fours": return 4 * Collections.frequency(i, 4);
		case "fives": return 5 * Collections.frequency(i, 5);
		case "sixes": return 6 * Collections.frequency(i, 6);
		case "three of a kind": 
			for(int k = 1; k <= 6; ++k) {
				if(Collections.frequency(i, k) >= 3)  return sum(i);
			}
			return 0;
		case "four of a kind": 
			for(int k = 1; k <= 6; ++k) {
				if(Collections.frequency(i, k) >= 4)  return sum(i);
			}
			return 0;
		case "full house": 
			boolean two = false;
			boolean three = false;
			for(int k = 1; k <= 6; ++k) {
				if(Collections.frequency(i, k) == 2) two = true;
				if(Collections.frequency(i, k) == 3) three = true;
			}
			if(two && three) return 25;
			return 0;
		case "small straight": 
			convert(i);
			if((i.get(0) + 1 == i.get(1) && i.get(1) + 1 == i.get(2) && i.get(2) + 1 == i.get(3)) || (i.get(1) + 1 == i.get(2) && i.get(2) + 1 == i.get(3) && i.get(3) + 1 == i.get(4))) 
				return 30;
			return 0;
		case "large straight": 
			Collections.sort(i);
			if(i.get(0) + 1 == i.get(1) && i.get(1) + 1 == i.get(2) && i.get(2) + 1 == i.get(3) && i.get(3) + 1 == i.get(4))
				return 40;
			return 0;
		case "chance": 
			return sum(i);
		case "yahtzee":
			Collections.sort(i);
			if(i.get(0) == i.get(4)) return 50;
			else return 0;
		}
		return 0;
	}
	
	public int sum(ArrayList<Integer> a) {
		int count = 0;
		for(Integer i : a) {
			count += i;
		}
		return count;
	}
	
	private void convert(ArrayList<Integer> a) {
		Set<Integer> set = new HashSet<>();
		set.addAll(a);
		a.clear(); a.addAll(set); Collections.sort(a);
	}
}
