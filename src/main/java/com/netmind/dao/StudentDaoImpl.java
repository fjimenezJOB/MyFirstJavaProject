package com.netmind.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import com.google.gson.Gson;
import com.netmind.dao.contracts.StudentDao;
import com.netmind.model.Student;

import org.apache.log4j.Logger;

public class StudentDaoImpl implements StudentDao {

  final static Logger logger = Logger.getLogger(StudentDaoImpl.class);
  static Properties prop = null;
  static InputStream input = null;

  static {

    prop = new Properties();
    try {
      input = StudentDaoImpl.class.getResourceAsStream("/config.properties");
      prop.load(input);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ExceptionInInitializerError(e);
    }
  }

  public boolean add(Student student) {
    addStudentToTxtFile(student);
    ArrayList<Student> students = readAllStudentTxt();
    return addStudentToJsonFile(students);
  }

  public boolean addStudentToTxtFile(Student student) {

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

  public boolean addStudentToJsonFile(ArrayList<Student> students) {
    logger.info("Starting to write Student in Json File");
    File archive = new File(prop.getProperty("JsonFilename"));
    archive.delete();

    try (FileWriter writer = new FileWriter(prop.getProperty("JsonFilename"), true);) {
      Gson gson = new Gson();
      gson.toJson(students, writer);
      return true;
    } catch (Exception ex) {
      logger.error("Error ocurred writing Student in json file", ex);
      return false;
    }
  }

  public ArrayList<Student> readAllStudentTxt() {

    ArrayList<Student> students = new ArrayList<Student>();
    try {
      
      File myObj = new File(prop.getProperty("TxtFilename"));
      Scanner myReader = new Scanner(myObj);

      while (myReader.hasNextLine()) {
        Student student = new Student();
        String data = myReader.nextLine();
        String[] separatedStringStudent = data.split(",");
        UUID uid = UUID.fromString(separatedStringStudent[0]);
        student.setUUID(uid);
        student.setName(separatedStringStudent[1]);
        student.setSurname(separatedStringStudent[2]);
        student.setAge(Integer.parseInt(separatedStringStudent[3]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(separatedStringStudent[4], formatter);
        student.setDateOfBirth(dateTime);
        students.add(student);

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      logger.error("An error occurred while try to read a txt file", e);
      e.printStackTrace();
    }
    return students;
  }
}
