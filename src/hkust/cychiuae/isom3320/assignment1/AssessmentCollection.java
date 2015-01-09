package hkust.cychiuae.isom3320.assignment1;

import java.util.ArrayList;

public class AssessmentCollection {
	private ArrayList<AssessmentSet> assessmentSet;
	
	public AssessmentCollection() {
		this.assessmentSet = new ArrayList<AssessmentSet>();
	}
	
	public void addAddessment(AssessmentSet assessmentSet) {
		this.assessmentSet.add(assessmentSet);
	}
	
	public ArrayList<AssessmentSet> getAssessmentSet() {
		return this.assessmentSet;
	}
}
