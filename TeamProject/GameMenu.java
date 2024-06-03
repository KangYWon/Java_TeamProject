package TeamProject;
import java.util.*;

public class GameMenu implements Game {
	private Scanner s = new Scanner(System.in);
	private int totalScore =0;
	
	@Override
	public void start() {
		while(true) {
			System.out.println("-----------Game Start!-----------");
			System.out.print("[1] Add\n[2] Subtract\n[3] Multiplication\n[4] Divide\n[5] Exit");
			System.out.println("Choose an operation: ");
			int choice = s.nextInt();
			
			if(choice ==5) {
				System.out.println("Exiting the Game.");
				System.out.println("Your total score: "+ totalScore);
				System.out.println("GOOD BYE~");
				break;
			}
			
			System.out.print("Enter the number of digits: ");
			int digits = s.nextInt();
			
			Arithmetic arithmetic;
			switch(choice) {
			case 1:
				arithmetic = new Addition();
				break;
			case 2: 
				arithmetic = new Subtraction();
				break;
			//case 3, case 4
			default:
				System.out.println("Invalid choide!!");
				continue;
	
			}
			
			GameRound gameRound = new GameRound(arithmetic, digits);
			int score = gameRound.play();
			totalScore += score;
			System.out.println("Current total score: "+ totalScore);
		}
	}
}
