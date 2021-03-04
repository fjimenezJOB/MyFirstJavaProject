package com.netmind.dao.contracts;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.netmind.model.Student;

public interface StudentDao {

    public boolean add(Student student);
    public boolean addStudentToFile(Student student);
    public ArrayList<Student> readAllStudentTxt() throws IOException, ParseException;
}
