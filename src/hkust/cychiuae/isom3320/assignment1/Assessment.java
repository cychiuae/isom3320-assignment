package hkust.cychiuae.isom3320.assignment1;

public class Assessment {
	private String assessmentName;
	private float score;
	
	public Assessment(String name, float score) {
		this.assessmentName = name;
		this.score = score;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public float getScore() {
		return score;
	}
}
