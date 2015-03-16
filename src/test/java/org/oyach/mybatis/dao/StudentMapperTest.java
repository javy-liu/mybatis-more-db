package org.oyach.mybatis.dao;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.oyach.mybatis.AbstractAppTest;
import org.oyach.mybatis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

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
        student.setId(7L);
        student.setName("oaych");

        studentMapper.insertStudent(student);
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
}