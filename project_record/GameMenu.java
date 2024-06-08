package TeamProject;
import java.util.*;

public class GameMenu implements Game {
	private Scanner s = new Scanner(System.in);
	private int totalScore =0;
	
	@Override
	public void start() {
		Records records = Records.createRecords();
		
		while(true) {
			System.out.println("-----------Game Start!-----------");
			System.out.println("[1] Add\n[2] Subtract\n[3] Multiplication\n[4] Divide\n[5] View Records\n[6] Exit");
			System.out.println("Choose an operation: ");
			int choice = s.nextInt();
			
			if (choice == 5) {
				records.displayRecords();
				continue;
			}
			
			if(choice ==6) {
				System.out.println("Exiting the Game.");
				System.out.println("Your total score: "+ totalScore);
				System.out.println("GOOD BYE~");
				break;
			}
			
			Arithmetic arithmetic;
			switch(choice) {
			case 1:
				arithmetic = new Addition();
				break;
			case 2: 
				arithmetic = new Subtraction();
				break;
			case 3:
				arithmetic = new Multiplication();
				break;
			case 4:
				arithmetic = new Division();
				break;
			default:
				System.out.println("Invalid choide!!");
				continue;
	
			}
			
			GameRound gameRound = new GameRound(records, choice);
			int score = gameRound.play();
			totalScore += score;
			records.addScore(score);
			System.out.println("Current total score: "+ totalScore);
		}
	}
	@Override
	public void saveRecords() {
		// records implementation 저장
	}
}
