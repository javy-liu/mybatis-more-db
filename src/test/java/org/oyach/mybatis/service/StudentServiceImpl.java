package org.oyach.mybatis.service;

import org.oyach.mybatis.dao.StudentMapper;
import org.oyach.mybatis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/12
 * @since 0.0.1
 */
@Service
//@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String say() {
        Student student = new Student();
        student.setId(8L);
        student.setName("oaych");
        long row = studentMapper.insertStudent(student);
        return row + "";
    }
}
