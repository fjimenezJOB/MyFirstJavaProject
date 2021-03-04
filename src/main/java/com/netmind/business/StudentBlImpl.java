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
        student.setAge(calculateAge(student.getDateOfBirth()));
        return studentDao.add(student);
    }

    public int calculateAge(LocalDate dateOfBirth) {
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(dateOfBirth.atStartOfDay(defaultZoneId).toInstant());
        Period edad = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        return edad.getYears();

    }

}
