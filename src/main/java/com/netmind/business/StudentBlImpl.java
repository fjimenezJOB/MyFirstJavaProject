package com.netmind.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.netmind.business.contracts.StudentBl;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.model.Student;

public class StudentBlImpl implements StudentBl {

    static StudentDaoImpl studentDao = new StudentDaoImpl();

    public boolean add(Student student) {
        return studentDao.add(student);
    }

    public void updateStudent(String name) {

        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        student = studentDao.findStudentByName(name);
        System.out.println(student.toString());

        System.out.println("which student data do you want to update?\n1. Name\n2. Surname\n3. Date Of birth\n");
        int decission = scanner.nextInt();
        String dataToUpdate = null;

        switch (decission) {

        case 1:
            System.out.println("Enter the name:");
            dataToUpdate = scanner.next();
            student.setName(dataToUpdate);
            break;

        case 2:

            System.out.println("Enter the surname:");
            dataToUpdate = scanner.next();
            student.setSurname(dataToUpdate);
            break;

        case 3:
            System.out.println("Enter the Date of birth (yyyy/MM/dd):");
            dataToUpdate = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate dateTime = LocalDate.parse(dataToUpdate, formatter);
            student.setDateOfBirth(dateTime);
            break;
        }
        studentDao.updateStudent(student);
    }
}
