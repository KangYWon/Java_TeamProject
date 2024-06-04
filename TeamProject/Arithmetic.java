package TeamProject;
import java.util.*;

public abstract class Arithmetic implements ArithmeticOperation{
	Random random = new Random();
	
	public abstract String getOperator();

	@Override
	public int generateQuestion(int digits) {
		int max = (int) Math.pow(10,  digits)-1;
		int min = (int) Math.pow(10, digits-1);
		int randomNum = random.nextInt(max-min+1)+min;
		return randomNum;
	}

	@Override
	public abstract boolean checkAnswer(int a, int b, int userAnswer);

}
