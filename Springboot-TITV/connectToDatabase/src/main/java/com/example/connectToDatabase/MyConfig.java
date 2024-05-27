package com.example.connectToDatabase;

import com.example.connectToDatabase.DAO.IStudentDAO;
import com.example.connectToDatabase.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Scanner;

@Configuration
public class MyConfig {

    @Bean
    @Autowired
    public CommandLineRunner getCommandLineRunner(IStudentDAO studentDAO) {
        return runner -> {
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                showMenu();
                choice = sc.nextInt();
                if (choice == 1) {
                    addSt(studentDAO);
                }else if (choice == 2) {
                    findById(studentDAO);
                }else if (choice == 3) {
                    findAll(studentDAO);
                }else if (choice == 4) {
                    findByFirstName(studentDAO);
                }else if (choice == 5) {
                    delete(studentDAO);
                }else if (choice == 6) {
                    update2(studentDAO);
                }else if (choice == 7) {
                    deleteByFirstName(studentDAO);
                }
            }while (choice != 8);
        };
    }

    public void showMenu(){
        System.out.println("\n===============Menu==============");
        System.out.println("1. Add Student");
        System.out.println("2. Find Student By Id");
        System.out.println("3. Find All Student");
        System.out.println("4. Find Students By First Name");
        System.out.println("5. Delete By Id");
        System.out.println("6. Update Students");
        System.out.println("7. DeleteBy First Name");
        System.out.println("8. Exit");
        System.out.print("Your Choice: ");
    }

    public void addSt(IStudentDAO studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student First Name: ");
        String fName =  sc.next();
        System.out.print("Enter Student Last Name: ");
        String lName = sc.next();
        System.out.print("Enter Student Email: ");
        String email = sc.next();
        studentDAO.save(new StudentEntity(fName, lName, email));
    }

    public void findById(IStudentDAO studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        StudentEntity student = studentDAO.findById(id);
        if (student == null) {
            System.out.println("Student Not Found");
        }else{
            System.out.println("Student Found");
            System.out.println(student.toString());
        }
    }

    public void findByFirstName(IStudentDAO studentDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student first Name: ");
        String fName =  sc.nextLine();
        List<StudentEntity> students = studentDAO.findByName(fName);
        if (students.isEmpty()) {
            System.out.println("Student Not Found");
        }else{
            System.out.println("Student Found: ");
            students.forEach(st -> System.out.println(st.toString()));
        }
    }

    public void findAll(IStudentDAO studentDAO) {
        List<StudentEntity> students = studentDAO.findAll();
        if (students.isEmpty()) {
            System.out.println("Student Not Found");
        }else{
            System.out.println("Student Found: ");
            students.forEach(st -> System.out.println(st.toString()));
        }
    }

    public void update(IStudentDAO studentDAO){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student First Name: ");
        String fName =  sc.nextLine();
        System.out.print("Enter Student Last Name: ");
        String lName = sc.nextLine();
        System.out.print("Enter Student Email: ");
        String email = sc.nextLine();
        studentDAO.update(new StudentEntity(6,fName, lName, email));
    }

    public void update2(IStudentDAO studentDAO){
        Scanner sc = new Scanner(System.in);

//        System.out.print("Enter Student First Name: ");
//        String fName =  sc.nextLine();
        StudentEntity student = new StudentEntity();
//        student.setFirstName(fName);
//        student.setId(6);
        studentDAO.updateJpql(student);
    }




    public void delete(IStudentDAO studentDAO){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        StudentEntity student = new StudentEntity();
        student.setId(id);
        studentDAO.delete(student);
    }

    public void deleteByFirstName (IStudentDAO studentDAO){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String fName =  sc.nextLine();
        studentDAO.deleteByFirstName(fName);
    }


}
