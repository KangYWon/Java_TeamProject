package TeamProject;

public class Division extends Arithmetic {
    @Override
	public boolean checkAnswer(int a, int b, int userAnswer) {
		return a/b == userAnswer;
	}
	
	@Override
    public String getOperator() {
        return "/";
    }
}
