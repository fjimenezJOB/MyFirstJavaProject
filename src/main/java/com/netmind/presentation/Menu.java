package com.netmind.presentation;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

import com.netmind.business.StudentBlImpl;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.model.EnumStudents;
import com.netmind.model.Student;

public class Menu {

  public static int hello() {

    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "******** MENU ********\n\nWellcome, what you want to do?\n\n1. Add new student.\n2. Calculate oldest student.\n3. Calculate the average age of students.\n4. Update Student\n5. Exit");
    int decission = scanner.nextInt();

    return decission;
  }

  public static void decission() {

    Boolean interruptor = true;

    try {
      do {
        int decission = Menu.hello();
        EnumStudents enumStudent = EnumStudents.fromValue(decission);

        switch (enumStudent) {
        case ADD_STUDENT:
          Student student = new Student();
          StudentDaoImpl dao = new StudentDaoImpl();
          askNewStudent(student);
          dao.readAllStudentTxt();
          break;
        case CALCULATE_OLDST_STUDENT:
          break;
        case CALCULATE_AVERAGE_AGE:
          break;
        case UPDATE_STUDENT:
          AskUpdateStudent();
          break;
        case EXIT:
          interruptor = false;
          break;

        default:
          Menu.hello();
        }
      } while (interruptor);
    } catch (Exception ex) {

      ex.printStackTrace();
    }
  }

  public static void askNewStudent(Student student) throws IOException, ParseException {
    StudentBlImpl studentBl = new StudentBlImpl();

    Scanner scanner = new Scanner(System.in);
    student.setIdStudent(UUID.randomUUID().toString());

    System.out.println("Enter the name:");
    student.setName(scanner.nextLine());

    System.out.println("Enter the surname:");
    student.setSurname(scanner.nextLine());

    System.out.println("Enter the date of birth (yyyy/mm/dd):");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate dateTime = LocalDate.parse(scanner.nextLine(), formatter);
    student.setDateOfBirth(dateTime);

    studentBl.add(student);
  }

  public static void AskUpdateStudent() {
    StudentBlImpl studentBl = new StudentBlImpl();
    System.out.println("Introduce the name of student to update:");
    Scanner scanner = new Scanner(System.in);
    studentBl.updateStudent(scanner.nextLine());
  }
}
