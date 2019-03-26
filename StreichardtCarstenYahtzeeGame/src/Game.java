public class Game {
	
	private static YahtzeeGame game = new YahtzeeGame();
	private static final int rounds = 13;
	static int roundNum = 0;
	
	public static void main(String[] xd) { 
		System.out.println("Welcome to Yahtzee!");
		while(roundNum < 13) {
			++roundNum;
			System.out.println("\n~~ Starting Round " + roundNum + "/" + rounds + " ~~");
			game.playRound();
		}
		System.out.println("Game over!");
		game.getFinalScore();
	}
}
