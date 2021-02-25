package com.netmind.dao;

import com.netmind.model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentDao {

  private static ArrayList<Student> students = null;

  static {
    students = new ArrayList<Student>();
  }

  public boolean add(Student student) {
    addStudentToFile(student);
    return students.add(student);
  }

  public static void read() {
    for (int iterator = 0; iterator < students.size(); iterator++) {
      System.out.println(students.get(iterator));
    }
  }

  public void addStudentToFile(Student student) {
    try (FileWriter writer = new FileWriter("output\\students.txt", true);) {
      writer.write(student.toString());
      writer.write(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
