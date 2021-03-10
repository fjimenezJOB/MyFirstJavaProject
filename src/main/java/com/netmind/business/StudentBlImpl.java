package com.netmind.business;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.netmind.business.contracts.StudentBl;
import com.netmind.dao.StudentDaoImpl;
import com.netmind.model.Student;

public class StudentBlImpl implements StudentBl {

    public boolean add(Student student) {

        StudentDaoImpl studentDao = new StudentDaoImpl();
        // student.setAge(calculateAge(student.getDateOfBirth()));
        return studentDao.add(student);
    }
}
