package com.netmind.business.contracts;

import java.time.LocalDate;

import com.netmind.model.Student;

public interface StudentBl {

    public boolean add(Student student);
    
    public int calculateAge(LocalDate dateOfBirth);
}
