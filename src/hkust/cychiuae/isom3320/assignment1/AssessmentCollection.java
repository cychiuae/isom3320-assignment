package hkust.cychiuae.isom3320.assignment1;

import java.util.ArrayList;

public class AssessmentCollection {
	private ArrayList<AssessmentSet> assessmentSet;
	
	//Creating a assessment set collection in a class
	public AssessmentCollection() {
		this.assessmentSet = new ArrayList<AssessmentSet>();
	}
	
	//Add an assessment set to this collection
	public void addAddessment(AssessmentSet assessmentSet) {
		this.assessmentSet.add(assessmentSet);
	}
	
	//Return the assessment sets
	public ArrayList<AssessmentSet> getAssessmentSet() {
		return this.assessmentSet;
	}
}
