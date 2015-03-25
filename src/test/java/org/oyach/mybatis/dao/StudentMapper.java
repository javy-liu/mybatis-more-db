package org.oyach.mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.oyach.mybatis.annotation.UseDataSource;
import org.oyach.mybatis.domain.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuzhenyuan
 * @version Last modified 15/3/6
 * @since 0.0.1
 */
@Transactional(readOnly = true)
public interface StudentMapper {

    @Transactional(readOnly = true)
    @UseDataSource(dataSource = {
            @UseDataSource.DataSource(name = "db_01", type = "read")
    })
    @Insert("insert into student(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insertStudent(Student student);

    @UseDataSource(dataSource = {
            @UseDataSource.DataSource(name = "db_01", type = "read"),
            @UseDataSource.DataSource(name = "db_02", type = "read")
    })
    @Insert("insert into student(id, name) values(#{id},#{name})")
    long aainsertStudent(Student student);


    @Select("select id, name from student")
    List<Student> selectAllUser();

    @UseDataSource(dataSource = {
            @UseDataSource.DataSource(name = "db_01"),
            @UseDataSource.DataSource(name = "db_02")
    })
    @Select("select id, name from student where id != #{id}")
    List<Student> selectAllUsers(long id);

    @Transactional
    long insertStudents(List<Student> students);
}
