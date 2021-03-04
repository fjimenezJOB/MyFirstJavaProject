package com.netmind.presentation;

import com.netmind.business.StudentBlImpl;
import com.netmind.dao.StudentDao;
import com.netmind.model.Student;
import com.netmind.model.EnumStudents;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

  public static int hello() {

    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "******** MENU ********\n\nWellcome, what you want to do?\n\n1. Add new student.\n2. Calculate oldest student.\n3. Calculate the average age of students.\n4. Exit");
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
            StudentDao dao = new StudentDao();
            Menu.askNewStudent(student);
            // dao.readAllStudentTxt();
            break;
          case CALCULATE_OLDST_STUDENT:
            break;
          case CALCULATE_AVERAGE_AGE:
            break;
          case EXIT:
            interruptor = false;

            break;
          default:
            Menu.hello();
        }
      } while (interruptor);
    } catch (Exception ex) {

      System.out.println(ex.getMessage() + " - " + "Try to insert a number of options in menu.");
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
}
