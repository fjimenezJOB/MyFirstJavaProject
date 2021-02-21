package com.netmind.dao;

import java.util.ArrayList;

import com.netmind.model.Student;

public class StudentDao {
    
    public static ArrayList<Student> students = new ArrayList<Student>();

    public static void add(Student student) {
        students.add(student);
    }

    public static void read() {
        for (int iterator = 0; iterator < students.size(); iterator++) {
            System.out.println(students.get(iterator));
        }
    }

}
