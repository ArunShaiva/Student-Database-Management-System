package sdbms;

import java.util.Scanner;

import custom_exception.InvalidChoiceException;

//Main Class
public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student Database Management System");
		System.out.println("---------------------------------------------");

		Scanner sc = new Scanner(System.in);

		StudentManagementSystem SMS = new SMSImpl(); // UPCASTING to achieve abstraction

		while (true) {
			System.out.println("1: Add Student\n2: Display Student\n3: Display All Student");
			System.out.println("4: Remove Student\n5: Remove All Students\n6: Update Student");
			System.out.println("7: Count Studnt\n8: Sort Student\n9: Find Student With HighestMarks");
			System.out.println("10:Find Student With Lowest Marks\n11: EXIT\nEnter Choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				SMS.addStudent();
				break;

			case 2:
				SMS.displayStudent();
				break;

			case 3:
				SMS.displayAllStudent();
				break;

			case 4:
				SMS.removeStudent();
				break;

			case 5:
				SMS.removeAllStudent();
				break;

			case 6:
				SMS.updateStudent();
				break;

			case 7:
				SMS.countStudent();
				break;

			case 8:
				SMS.sortStudents();
				break;

			case 9:
				SMS.getStudentWithHighestMarks();
				break;

			case 10:
				SMS.getStudentWithLowestMarks();
				break;

			case 11:
				System.out.println("Thank You");
				System.exit(0);

			default:
				try {
					String message = "Invalid Choice Kindly Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			System.out.println("=================================");

		}

	}

}
