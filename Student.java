package sdbms;

//Bean class
public class Student {
	private String id;
	static int count = 101;
	private int age;
	private String name;
	private int marks;

	public Student(int age, String name, int marks) {
		this.id = "JSP" + count;
		count++;
		this.age = age;
		this.name = name;
		this.marks = marks;

	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return " ID :" + id + "  Age :" + age + " Name :" + name + "  Marks :" + marks;
	}
}
