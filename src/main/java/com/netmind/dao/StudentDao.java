package com.netmind.dao;

import com.netmind.model.Student;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

public class StudentDao {

  final static Logger logger = Logger.getLogger(StudentDao.class);
  static Properties prop = null;
  static InputStream input = null;

  static{
    prop = new Properties();
    try {
      input = StudentDao.class.getResourceAsStream("/config.properties");
      prop.load(input);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ExceptionInInitializerError(e);
    }
  }


  public boolean add(Student student) {
    return addStudentToFile(student);
  }

  public boolean addStudentToFile(Student student) {
    // Llamando al archivo properties para cargar el nombre del archivo
    try (FileWriter writer = new FileWriter(prop.getProperty("TxtFilename"), true);) {
      logger.info("Creating file if not exists");
      logger.info("Starting add Student in the txt file");
      writer.write(student.toTxtFile());
      writer.write(System.lineSeparator());
      logger.info("The sutudent is created");
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

  }

  public ArrayList<Student> readAllStudentTxt() throws IOException, ParseException {
    try (FileReader reader = new FileReader(prop.getProperty("TxtFilename"))) {
      if (reader.ready()) {
        logger.info("Starting read the txt file");
        try {
          BufferedReader bufferedReader = new BufferedReader(reader);
          Student student = new Student();
          ArrayList<Student> students = new ArrayList<Student>();
          String line;
          Integer index = 0;

          while ((line = bufferedReader.readLine()) != null) {
            ArrayList<String> studentsTxt = new ArrayList();

            studentsTxt.add(line);
            for (int iterator = 0; iterator < studentsTxt.size(); iterator++) {

              String[] separatedStringStudent = studentsTxt.get(index).split(",");
              UUID uid = UUID.fromString(separatedStringStudent[0]);
              student.setUUID(uid);
              student.setName(separatedStringStudent[1]);
              student.setSurname(separatedStringStudent[2]);
              student.setAge(Integer.parseInt(separatedStringStudent[3]));
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDate dateTime = LocalDate.parse(separatedStringStudent[4], formatter);
              student.setDateOfBirth(dateTime);
              students.add(student);
              index = index + 1;
            }
            return students;
          }
        } catch (IOException e) {
          logger.error(e.toString());
        }
      }
    }
    return null;
  }
}
