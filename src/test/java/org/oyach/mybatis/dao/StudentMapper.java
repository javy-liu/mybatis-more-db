package org.oyach.mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.oyach.mybatis.annotation.UseDataSource;
import org.oyach.mybatis.domain.Student;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
public interface StudentMapper {

//    @Transactional(readOnly = true)
//    @UseDataSource(type = "read", dataSource = {"db_01"})
    @Insert("insert into student(id, name) values(#{id},#{name})")
    long insertStudent(Student student);
}
