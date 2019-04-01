import java.util.Arrays;
import java.util.Scanner;

public class YahtzeeGame {
	
	Scanner sc = new Scanner(System.in);
	Scorecard scorecard = new Scorecard(); 
	private static YahtzeeDice gameDice = new YahtzeeDice();
	
	public YahtzeeGame() {
		//nothing needed here (empty constructor)
	}
	
	public void playRound() {
		System.out.println("Current roll: " + Arrays.toString(gameDice.roll())); 
		hold();
	}
	
	private void hold() {
		int[] dice = gameDice.getDiceValues();
		int turns = 2;
		while(turns > 0) {
			dice = gameDice.getDiceValues();
			System.out.println("You have " + turns + " turns left in this round.");
			System.out.print("Do you want to re-roll? ");
			if(sc.next().equalsIgnoreCase("y")) {
				System.out.print("Hold first die? (" + dice[0] + ") ");
				boolean die1 = sc.nextBoolean();
				System.out.print("Hold second die? (" + dice[1] + ") ");
				boolean die2 = sc.nextBoolean();
				System.out.print("Hold third die? (" + dice[2] + ") ");
				boolean die3 = sc.nextBoolean();
				System.out.print("Hold fourth die? (" + dice[3] + ") ");
				boolean die4 = sc.nextBoolean();
				System.out.print("Hold fifth die? (" + dice[4] + ") ");
				boolean die5 = sc.nextBoolean();
				System.out.println("Re-rolling...");
				--turns;
				gameDice.roll(new boolean[]{die1, die2, die3, die4, die5});
				System.out.println(gameDice.toString()); 
			} else {
				turns = 0;
			}
		}
		System.out.print("Make a choice for your dice: ");
		sc.nextLine();
		String s = sc.nextLine();
		while(!scorecard.score.containsKey(s)) {
			System.out.print("Make a valid choice: ");
			s = sc.nextLine();
		}
		if(!scorecard.canPut(s)) {
			System.out.print("That spot is already taken! ");
			s = sc.nextLine();
		}
		scorecard.score.put(s, scorecard.place(s, gameDice.getDiceValues()));
		System.out.println("-----------------------------");
		System.out.println(scorecard.score.get(s) + " has been added to " + s);
		System.out.println("-----------------------------");
		getChoices(); 
	}
	private void getChoices() {
		System.out.printf("|%16s|%5s|%n", "Ones", isTaken("Ones"));
		System.out.printf("|%16s|%5s|%n", "Twos", isTaken("Twos"));
		System.out.printf("|%16s|%5s|%n", "Threes", isTaken("Threes"));
		System.out.printf("|%16s|%5s|%n", "Fours", isTaken("Fours"));
		System.out.printf("|%16s|%5s|%n", "Fives", isTaken("Fives"));
		System.out.printf("|%16s|%5s|%n", "Sixes", isTaken("Sixes"));
		System.out.printf("|%16s|%5s|%n", "Three of a Kind", isTaken("Three of a Kind"));
		System.out.printf("|%16s|%5s|%n", "Four of a Kind", isTaken("Four of a Kind"));
		System.out.printf("|%16s|%5s|%n", "Full House", isTaken("Full House"));
		System.out.printf("|%16s|%5s|%n", "Small Straight", isTaken("Small Straight"));
		System.out.printf("|%16s|%5s|%n", "Large Straight", isTaken("Large Straight"));
		System.out.printf("|%16s|%5s|%n", "Chance", isTaken("Chance"));
		System.out.printf("|%16s|%5s|%n", "Yahtzee", isTaken("Yahtzee"));
	}
	
	private String isTaken(String s) {
		if(scorecard.score.get(s) == -1) return "O";
		return "X";
	}
	
	public void getFinalScore() {
		int numberTotal = scorecard.score.get("Ones") 
				+ scorecard.score.get("Twos")
			+ scorecard.score.get("Threes")
			+ scorecard.score.get("Fours")
			+ scorecard.score.get("Fives")
			+ scorecard.score.get("Sixes");
		int bonus = numberTotal >= 63 ? 35 : 0; 
		System.out.println("------------------------");
		System.out.printf("|%16s|%5d|%n", "Ones", scorecard.score.get("Ones"));
		System.out.printf("|%16s|%5d|%n", "Twos", scorecard.score.get("Twos"));
		System.out.printf("|%16s|%5d|%n", "Threes", scorecard.score.get("Threes"));
		System.out.printf("|%16s|%5d|%n", "Fours", scorecard.score.get("Fours"));
		System.out.printf("|%16s|%5d|%n", "Fives", scorecard.score.get("Fives"));
		System.out.printf("|%16s|%5d|%n", "Sixes", scorecard.score.get("Sixes"));
		System.out.println("|----------------------|");
		System.out.printf("|%16s|%5d|%n", "Sum", numberTotal);
		System.out.printf("|%16s|%5d|%n", "Bonus", bonus);
		System.out.println("|----------------------|");
		System.out.printf("|%16s|%5d|%n", "Three of a Kind", scorecard.score.get("Three of a Kind"));
		System.out.printf("|%16s|%5d|%n", "Four of a Kind", scorecard.score.get("Four of a Kind"));
		System.out.printf("|%16s|%5d|%n", "Full House", scorecard.score.get("Full House"));
		System.out.printf("|%16s|%5d|%n", "Small Straight", scorecard.score.get("Small Straight"));
		System.out.printf("|%16s|%5d|%n", "Large Straight", scorecard.score.get("Large Straight"));
		System.out.printf("|%16s|%5d|%n", "Chance", scorecard.score.get("Chance"));
		System.out.printf("|%16s|%5d|%n", "Yahtzee", scorecard.score.get("Yahtzee"));
		System.out.println("|----------------------|");
		System.out.printf("|%16s|%5d|%n", "Total", scorecard.getTotal() + bonus);
		System.out.println("------------------------");
	}
}
