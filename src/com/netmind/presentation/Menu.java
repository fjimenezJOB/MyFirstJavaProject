package com.netmind.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import com.netmind.model.Student;
import com.netmind.business.StudentBl;
import com.netmind.dao.StudentDao;

public class Menu {

    public static int hello() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "******** MENU ********\n\nWellcome, what you want to do?\n\n1. Add new student.\n2. Calculate oldest student.\n3. Calculate the average age of students.\n4. Exit");
        int decission = scanner.nextInt();
        return decission;
    }

    public static void askNewStudent(){
        Scanner scanner = new Scanner(System.in);

        String id = UUID.randomUUID().toString();
        
        System.out.println("Enter the name:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();

        System.out.println("Enter the date of birth (dd/mm/yyyy):");
        String dateOfBirth = scanner.nextLine();

        Integer age = StudentBl.calculateAge(dateOfBirth);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBirthToDate;
        try {
            dateOfBirthToDate = format.parse(dateOfBirth);
            Student newStudent = new Student(id, name, surname, age, dateOfBirthToDate );
            StudentDao.add(newStudent);
            StudentDao.read();
    

        } catch (ParseException ex) {
            System.out.println(ex);
        }
    }
}