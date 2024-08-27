package com.example.RelationalMappingPractice;

import com.example.RelationalMappingPractice.dao.CourseDAO;
import com.example.RelationalMappingPractice.dao.DepartmentDAO;
import com.example.RelationalMappingPractice.dao.TeacherDAO;
import com.example.RelationalMappingPractice.dao.TeacherDetailDAO;
import com.example.RelationalMappingPractice.entity.Course;
import com.example.RelationalMappingPractice.entity.Department;
import com.example.RelationalMappingPractice.entity.Teacher;
import com.example.RelationalMappingPractice.entity.TeacherDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RelationalMappingPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalMappingPracticeApplication.class, args);
	}

   	@Bean
	@Autowired
	public CommandLineRunner init(TeacherDAO teacherDAO, TeacherDetailDAO teacherDetailDAO, CourseDAO courseDAO, DepartmentDAO departmentDAO) {
		return runner ->{
//		    createTeacher(teacherDAO);
//			findTeacherDetail(teacherDetailDAO,1);
//			findDepartmentById(departmentDAO, 1);
//			updateDepartment(departmentDAO,1);
//			deleteTeacherById(teacherDAO, 3);
//			findCourseById(courseDAO);
//			findCourseByIdJoinFetch(courseDAO);

		};
	}

	private void findCourseByIdJoinFetch(CourseDAO courseDAO) {
		Course course = courseDAO.findCourseByIdJoinFetch(1);
		System.out.println(course);
		System.out.println("Teacher: "+course.getTeacher());
	}

	private void findCourseById(CourseDAO courseDAO) {
		Course course = courseDAO.findCourseById(1);
		System.out.println(course);
		System.out.println("Teacher: "+course.getTeacher());
	}


	private void deleteTeacherById(TeacherDAO teacherDAO, int i) {
	  	teacherDAO.deleteTeacherById(i);
	}

	private void updateDepartment(DepartmentDAO departmentDAO, int i) {
	   Department department = new Department();
	   department.setId(i);
	   department.setName("Department " + i);
	   department.setDescription("Department Description " + i);
	   department.setTeacherList(null);
	   System.out.println("updateDepartment: " + departmentDAO.updateDepartment(department));
	}

	private void findDepartmentById(DepartmentDAO departmentDAO, int i) {
		Department department = departmentDAO.getDepartmentById(i);
		System.out.println(department);
		department.getTeacherList().forEach(teacher -> System.out.println(teacher));
	}

	private void findTeacherDetail(TeacherDetailDAO teacherDetailDAO, int i) {
		TeacherDetail teacherDetail = teacherDetailDAO.findTeacherDetailById(i);
		System.out.println(teacherDetail);
		System.out.println(teacherDetail.getTeacher());
	}

	private void findTeacherById(TeacherDAO teacherDAO, int i) {
		Teacher teacher = teacherDAO.findTeacherById(i);
		System.out.println(teacher);
		System.out.println(teacher.getTeacherDetail().toString());
	}

	private void createTeacher(TeacherDAO teacherDAO) {
		Teacher teacher = new Teacher();
		teacher.setName("Quang Hau");
		teacher.setPassword("1234");
		TeacherDetail teacherDetail = new TeacherDetail();
		teacherDetail.setPhoneNumber("090123456");
		teacherDetail.setAddress("Chau Lau");
		teacher.setTeacherDetail(teacherDetail);

		System.out.println("Save teacher: " + teacher);
		teacherDAO.save(teacher);
		System.out.println("Done");
	}

}
