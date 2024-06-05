package TeamProject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class GameMenu implements Game {
	// 모든 정보 담는 records 싱글턴 생성하고, 라운드마다 틀린 거 저장
	// 최종적으로 records의 내용 저장 
	private Records records = Records.createRecords();
	private Scanner s = new Scanner(System.in);
	private int round = 0;
	
	@Override
	public void start() {
		while(true) {
			System.out.println("-----------Game Start!-----------");
			System.out.println("[1] Add\n[2] Subtract\n[3] Multiplication\n[4] Divide\n[5] Print the records\n[6] Save\n[7] Exit");
			System.out.println("Choose an operation: ");
			int choice = s.nextInt();
			
			if (choice <= 4) { 
				GameRound gameRound = new GameRound(records, choice);
				gameRound.play();
				round += 1;
				// totalScore += score; => 각 라운드 내에서 record에 저장
				// System.out.println("Current total score: "+ totalScore);
			} else if (choice == 5) {
				// records로부터 print
			} else if (choice == 6) {
				saveRecords();
			} else if (choice == 7) {
				System.out.println("Exiting the Game.");
				System.out.println("GOOD BYE~");
				break;
			}
		}
	}

	public void saveRecords() {
		System.out.println("insert the file name : ");
		String fileName = s.next();
		
		PrintWriter outputStream = null;
		while (outputStream == null) {
			try {
				outputStream = new PrintWriter(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("Error opening the file " + fileName);
				System.out.println("Retype the file name : ");
				fileName = s.next();
			}
		}

		for (int i = 0; i < round; i++) {
			// records의 기록 가져와서 저장
			// outputStream.println(line);
		}
		outputStream.close();
		System.out.println("The records were saved into " + fileName);
	}
}
