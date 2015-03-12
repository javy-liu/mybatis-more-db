package org.oyach.mybatis.service;

import org.junit.Test;
import org.oyach.mybatis.AbstractAppTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StudentServiceTest extends AbstractAppTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSay() throws Exception {
        studentService.say();
    }
}