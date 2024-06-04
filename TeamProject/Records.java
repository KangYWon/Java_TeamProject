package TeamProject;

public class Records {
    private static Records records;
    private int totalScore;

    private Records() {
        totalScore = 0;
    }

    public static Records createRecords() {
        if (records == null) records = new Records();
        return records;
    }

    // 라운드 별 점수와 틀린 문제 저장
}
