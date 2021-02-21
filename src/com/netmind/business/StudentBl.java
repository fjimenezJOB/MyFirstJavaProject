package com.netmind.business;

import java.util.Calendar;

public class StudentBl {

    public static int calculateAge(String dateOfBirth) {

        // Calculo de la edad
        Calendar cal = Calendar.getInstance();

        String[] dateOfBirthSeparated = dateOfBirth.split("/");

        Integer yearOfbirth = Integer.parseInt(dateOfBirthSeparated[2]);
        Integer actualYear = cal.get(Calendar.YEAR);

        Integer monthOfBirth = Integer.parseInt(dateOfBirthSeparated[1]);
        Integer actualMonth = cal.get(Calendar.MONTH);

        Integer ageCalculated = actualYear - yearOfbirth;

        if (actualMonth < monthOfBirth) {
            ageCalculated -= 1;
        }

        return ageCalculated;

    }

}
