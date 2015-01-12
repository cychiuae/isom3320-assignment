package hkust.cychiuae.isom3320.assignment1;
import java.util.Scanner;

public class Assignment1 {
	
	/*
	 * The main of this assignment
	 * */
	
	public static void main(String... args) {
		//Create a class object
		Class isomClass = new Class();
		
		//Ask user to input the location of data file
		System.out.print("Please input the file location: ");
		Scanner input = new Scanner(System.in);
		String fileLocation = input.next();
		input.close();
		
		//Do some operation
		isomClass.readStudentData(fileLocation);
		isomClass.sortTheStudent();
		isomClass.rankTheStudents();
		isomClass.printStudentData();
	}
}
