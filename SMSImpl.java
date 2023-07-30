package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//Custom Exception is Imported
import custom_exception.InvalidChoiceException;
import custom_exception.StudentNotFoundException;

//Customsorting is Imported
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;

//Implementation Class
public class SMSImpl implements StudentManagementSystem {
	Scanner sc = new Scanner(System.in);
	/**
	 * We are using Collection As our Database Key is <String> -> Student Id & value
	 * is <Student> -> Student Object
	 */
	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() {
		// Accepting Age
		System.out.println("Enter the Age");
		int age = sc.nextInt();
		// Accepting Name+
		System.out.println("Enter the Name");
		String name = sc.next();
		// Accepting Marks
		System.out.println("Enter the Marks");
		int marks = sc.nextInt();
		// Creating Student Instance
		Student std = new Student(age, name, marks);
		// Adding Student id as Key& Student objectStudent as value IN Db(Map)
		db.put(std.getId(), std);

		System.out.println("Student Record Inserted Succesfully");
		System.out.println("Your Student Id is " + std.getId());
		// If we Want to print the Student Details
		// System.out.println("Your Student Details is "+std);

	}

	@Override
	public void displayStudent() {
		// Accepting student id and converting into upper Case Because
		// all id's should be considered as -> jsp101,Jsp102,JSP103
		System.out.println("Enter Student ID");
		String id = sc.next();
		id = id.toUpperCase();
		// Checking if the id is Present or not
		if (db.containsKey(id)) {
			System.out.println("Student Record Found");
			Student std = db.get(id); // Getting Student Object
			System.out.println("ID :" + std.getId());
			System.out.println("Age :" + std.getAge());
			System.out.println("Name :" + std.getName());
			System.out.println("Marks :" + std.getMarks());

		} else {
			try {
				String message = "Student with ID " + id + " Is not Found";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	public void displayAllStudent() {
		// Checking if the db is empty or not
		// if it is not empty ,we will display Student objects
		// if db is Empty,Throw an Exception
		if (!db.isEmpty()) {
			System.out.println("Student Record Are as follows");
			System.out.println("=============================");
			// Converting Map(db) into Set
			Set<String> keys = db.keySet(); // JSP101 JSP102 JSP103

			for (String key : keys) {
				// Printing reference variable as toString() is Overridden
				System.out.println(db.get(key));

			}
		} else {
			try {
				String message = "No Students Records to display !";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void removeStudent() {
		System.out.println("Enter the Student ID");
		String id = sc.next();// scan.next().toUpperCase();
		id = id.toUpperCase();
		if (db.containsKey(id)) {// Checking that if the key is present or not
			System.out.println("Student Record Found");
			System.out.println(db.get(id));
			db.remove(id); // Removing the Id
			System.out.println("Student Record Deleted Succesfully");

		} else {
			try {
				String message = "Student with id " + id + " is not Found";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void removeAllStudent() {
		if (!db.isEmpty()) {
			System.out.println("Number of Student Records Before deleting" + db.size());
			db.clear();
			System.out.println("Number of Student Records After deleting" + db.size());
			System.out.println("Student Records Deleted Succesfully");
		} else {
			try {
				String message = "No Students record to delete!";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void updateStudent() {
		System.out.println("Enter the Student id");
		String id = sc.next().toUpperCase();

		if (db.containsKey(id)) {
			System.out.println("Student Record Found");
			Student std = db.get(id);
			// Menu Driven program
			System.out.println("1:Update Age  \n2:Update Name ");
			System.out.println("3: Update Marks   \nEnter your Choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Age");
				int age = sc.nextInt(); // std.setAge(sc.next());
				std.setAge(age);
				System.out.println("Age uploaded Succesfully");
				break;
			case 2:
				System.out.println("Enter Name");
				String name = sc.next();
				std.setName(name);
				System.out.println("Name Uploaded Succesfully");
				break;
			case 3:
				System.out.println("Enter the Marks");
				int marks = sc.nextInt();
				std.setMarks(marks);
				System.out.println("Marks Uploaded Succesfully");

			default: {
				try {
					String message = "Invalid Choice,Kindly Enter valid choice";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			}

		} else {
			try {
				String Message = "Student with ID " + id + " is not found";
				throw new StudentNotFoundException(Message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void countStudent() {
		int size = db.size();
		System.out.println("The Total number Of Student is " + size);

	}

	@Override
	public void sortStudents() {

		if (db.size() >= 2) {
			// reference of list &Object of Array List Storing Student Objects
			List<Student> list = new ArrayList<Student>();

			// Coverting DB(Map) into Set using KeySet()
			Set<String> keys = db.keySet(); // JSP101 JSP102 JSP103

			// Traversing Keys(IDs)
			for (String key : keys) {
				Student std = db.get(key); // Getting Student Object
				list.add(std); // Adding Student Object Into List
			}
			System.out.println("1: Sort Student By  Id");
			System.out.println("2: Sort Student By  Age");
			System.out.println("3: Sort Student By  Name");
			System.out.println("4: Sort Student By  Marks");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Collections.sort(list, new SortStudentById());
				for (Student s : list) {
					System.out.println(s);
				}
				break;
			case 2:
				Collections.sort(list, new SortStudentByAge());
				for (Student s : list) {
					System.out.println(s);
				}
				break;
			case 3:
				Collections.sort(list, new SortStudentByName());
				for (Student s : list) {
					System.out.println(s);
				}
				break;
			case 4:
				Collections.sort(list, new SortStudentByMarks());
				for (Student s : list) {
					System.out.println(s);
				}
				break;
			default:
				try {
					String message = "Invalid Choice Kindly Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

		else {
			try {
				String message = "No Sufficient Records To Sort";
				throw new StudentNotFoundException(message);
			} catch (Exception e) { // We can handle it using StudentNotFoundException Also
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithHighestMarks() {
		if (db.size() >= 2) {
			List<Student> list = new ArrayList<Student>();

			Set<String> keys = db.keySet();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println("Student with Highest Marks");
			System.out.println(list.get(list.size() - 1));
		} else {
			try {
				String message = "No Sufficient Students Records To Find Highest Marks";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithLowestMarks() {
		if (db.size() >= 2) {
			List<Student> list = new ArrayList<Student>();

			Set<String> keys = db.keySet();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println("Student with Lowest Marks");
			System.out.println(list.get(0));
		} else {
			try {
				String message = "No Sufficient Students Records To Find Highest Marks";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
