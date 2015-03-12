package org.oyach.mybatis.dao;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.oyach.mybatis.AbstractAppTest;
import org.oyach.mybatis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

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

}