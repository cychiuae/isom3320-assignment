package hkust.cychiuae.isom3320.assignment1;

public class Assessment {
	private String assessmentName;
	private float score;
	
	//Creating an assessment
	public Assessment(String name, float score) {
		this.assessmentName = name;
		this.score = score;
	}

	//Get the assessment name
	public String getAssessmentName() {
		return assessmentName;
	}

	//Get the score of this assessment
	public float getScore() {
		return score;
	}
}
