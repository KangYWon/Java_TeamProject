package TeamProject;
import java.util.*;

public class GameRound {
	private Arithmetic arithmetic;
	private int digits;
	private Scanner s = new Scanner(System.in);
	
	public GameRound(Arithmetic arithmetic, int digits) {
		this.arithmetic = arithmetic;
		this.digits = digits;
	}
	
	public int play() {
		int correctAnswers =0;
		List<Integer> wrongAnswers = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			int a = arithmetic.generateQuestion(digits);
			int b = arithmetic.generateQuestion(digits);
			String operator = arithmetic.getOperator();
			System.out.printf("[%d]: %d %s %d = ", i, a, operator,b);
			int userAnswer = s.nextInt();
			
			if (arithmetic.checkAnswer(a, b, userAnswer)) {
                correctAnswers++;
            } else {
                wrongAnswers.add(i);
            }
		}
		System.out.printf("YOU GAT %d OUT OF 10 CORRECT!.\n", correctAnswers);
        if (!wrongAnswers.isEmpty()) {
            System.out.println("Wrong question numbers: " + wrongAnswers);
        }

        return correctAnswers * 10;
	}
}
