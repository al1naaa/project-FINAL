package education;

public class Mark {
    private int firstAssessment;
    private int secondAssessment;
    private int finalExam;

    public Mark(int firstAssessment, int secondAssessment, int finalExam) {
        this.firstAssessment = firstAssessment;
        this.secondAssessment = secondAssessment;
        this.finalExam = finalExam;
    }

    public int calculateFinalScore() {
        return firstAssessment + secondAssessment + finalExam;
    }

    public int getFirstAssessment() {
        return firstAssessment;
    }

    public int getSecondAssessment() {
        return secondAssessment;
    }

    public int getFinalExam() {
        return finalExam;
    }
}