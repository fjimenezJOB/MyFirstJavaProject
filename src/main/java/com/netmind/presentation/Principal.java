package com.netmind.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.netmind.dao.StudentDaoImpl;
import com.netmind.model.Student;
public class Principal {

  public static void main(String[] args) {
    // Menu.decission();
    StudentDaoImpl dao = new StudentDaoImpl();
    ArrayList<Student> students = dao.readAllStudentTxt();
    Student student = students.get(0);
    student.setName("CACACULOPEDOPIS");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate dateTime = LocalDate.parse("1500/05/03", formatter);
    student.setDateOfBirth(dateTime);
    dao.updateTxtFile(student);
  }
}