package hkust.cychiuae.isom3320.assignment1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Class {
	private ArrayList<Student> students;
	private AssessmentCollection assessmentCollection;
	private int asg1Percentage;
	private int asg2Percentage;
	private int midtermPercentage;
	private int finalPercentage;
	
	public Class() {
		this.students = new ArrayList<Student>();
		this.assessmentCollection = new AssessmentCollection();
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public float calculateStudentOverallScore(Student s) {
		float overallScore = 0;
		for(AssessmentSet aS : this.assessmentCollection.getAssessmentSet()) {
			overallScore += s.getAssessmentByName(aS.getAssessmentName()).getScore() * aS.getAssessmentPercentage();
		}
		return overallScore / 100;
	}
	
	public void rankTheStudents() {
		int rank = 1;
		
		for(Student s : this.students) {
			int indexOfStudent = this.students.indexOf(s);
			
			if(indexOfStudent == 0) {
				s.setRank(rank);
			}
			else if(this.calculateStudentOverallScore(s) == this.calculateStudentOverallScore(this.students.get(indexOfStudent - 1))){
				s.setRank(rank);
			}
			else {
				rank = indexOfStudent + 1;
				s.setRank(rank);
			}
		}
	}
	
	public void readStudentData(String fileLocation) {
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(fileLocation));
			
			String firstLine = in.readLine();
			String[] firstLineData = firstLine.split(",");
			
			for(int i = 2; i < firstLineData.length; i += 2) {
				String assessmentName = firstLineData[i].trim();
				int assessmentPercentage = Integer.parseInt(firstLineData[i + 1].trim());
				AssessmentSet assessmentSet = new AssessmentSet(assessmentName, assessmentPercentage);
				this.assessmentCollection.addAddessment(assessmentSet);
			}

			String line = null;
			while((line = in.readLine()) != null) {
				String[] lineData = line.split(",");
				Student student = new Student(lineData[0].trim(), lineData[1].trim());
				
				for(int i = 2; i < lineData.length; i++) {
					Assessment assessment = new Assessment(this.assessmentCollection.getAssessmentSet().get(i - 2).getAssessmentName(), Float.parseFloat(lineData[i].trim()));
					student.addAssessment(assessment);
				}
				
				this.students.add(student);
			}
			
			in.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
	
	public void printStudentData() {		
		float totalOverall = 0;
		
		System.out.printf("%-9s%-21s", "ID", "Name");
		
		for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
			int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
			String format = "%-" + assessmentNameLength +"s";
			System.out.printf(format, assessmentSet.getAssessmentName());
		}
		
		System.out.printf("%-8s%s", "Overall", "Rank");
		
		System.out.println();
		
		for(Student s : this.students) {
			System.out.printf("%-9s%-21s", s.getStudentId(), s.getName());
			
			for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
				float assessmentScore = s.getAssessmentByName(assessmentSet.getAssessmentName()).getScore();
				assessmentSet.addClassTotalScore(assessmentScore);
				
				int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
				String format = "%-" + assessmentNameLength +".1f";
				
				System.out.printf(format, assessmentScore);
			}
			
			float studentOverallScore = calculateStudentOverallScore(s);
			totalOverall += studentOverallScore;
			
			System.out.printf("%-8.1f", studentOverallScore);
			
			System.out.printf("%d", s.getRank());
			
			System.out.println();
		}
		
		System.out.printf("%30s", "Average:");
		
		for(AssessmentSet assessmentSet : this.assessmentCollection.getAssessmentSet()) {
			int assessmentNameLength = assessmentSet.getAssessmentName().length() + 1;
			String format = "%-" + assessmentNameLength +".1f";
			
			System.out.printf(format, assessmentSet.getClassTotal() / this.students.size());
		}
		
		System.out.printf("%-8.1f", totalOverall / this.students.size());
	}
	
	public void sortTheStudent() {
		this.students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				float student1OverallScore = Class.this.calculateStudentOverallScore(o1);
				float student2OverallScore = Class.this.calculateStudentOverallScore(o2);
			
				if(student1OverallScore > student2OverallScore) {
					return -1;
				}
				else if(student1OverallScore == student2OverallScore) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});
	}
}