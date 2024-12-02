import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Student implements Comparable<Student> {
    private String name;
    private String address;
    private double GPA;

    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    // Getters and setters

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", GPA: " + GPA;
    }
}

public class StudentDataEntry {

    public static void main(String[] args) {
        List<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        // Prompt user for student data entry
        while (true) {
            System.out.println("Enter student data (or type 'exit' to finish):");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();

            double GPA;
            while (true) {
                System.out.print("GPA: ");
                try {
                    GPA = Double.parseDouble(scanner.nextLine());
                    if (GPA >= 0 && GPA <= 4.0) {
                        break;
                    } else {
                        System.out.println("GPA must be between 0 and 4.0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA. Please enter a numeric value.");
                }
            }

            studentList.add(new Student(name, address, GPA));
        }

        // Sort the student list by name
        Collections.sort(studentList);

        // Write the sorted student list to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt"))) {
            for (Student student : studentList) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Student data has been written to student_data.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}