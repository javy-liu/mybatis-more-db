package org.oyach.mybatis.dao;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.oyach.mybatis.AbstractAppTest;
import org.oyach.mybatis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ComponentScan()
public class StudentMapperTest extends AbstractAppTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;



    @Test
    public void testInsertStudent() throws Exception {
        Student student = new Student();
        student.setName("oyach001");

        studentMapper.insertStudent(student);

        System.out.println(student);
    }

    @Test
    public void testSelectAllUser() throws Exception {
        List<Student> students = studentMapper.selectAllUser();
        System.out.println(students);
    }

    @Test
    public void testSelectAllUsers() throws Exception {
        List<Student> students = studentMapper.selectAllUsers(-1L);
        System.out.println(students);
    }

    @Test
    public void testInsertStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        Student student01 = new Student();
        student01.setName("oyach01");

        Student student02 = new Student();
        student02.setName("oyach02");

        Student student03 = new Student();
        student03.setName("oyach03");

        students.add(student01);
        students.add(student02);
        students.add(student03);
        studentMapper.insertStudents(students);

        System.out.println(students);
    }
}