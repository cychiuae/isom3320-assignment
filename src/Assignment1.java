import java.util.Scanner;

public class Assignment1 {
	public static void main(String... args) {
		Class isomClass = new Class();
		
		System.out.print("Please input the file location: ");
		Scanner input = new Scanner(System.in);
		String fileLocation = input.next();
		input.close();
		
		isomClass.readStudentData(fileLocation);
		isomClass.sortTheStudent();
		isomClass.printStudentData();
	}
}
