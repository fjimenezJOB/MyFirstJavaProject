package com.netmind.dao;

import com.netmind.model.Student;
import java.util.ArrayList;

public class StudentDao {

  private static ArrayList<Student> students = null;

  static {
    students = new ArrayList<Student>();
  }

  public boolean add(Student student) {
    return students.add(student);
  }

  public static void read() {
    for (int iterator = 0; iterator < students.size(); iterator++) {
      System.out.println(students.get(iterator));
    }
  }
}
