package com.example.demo.mapper;

import com.example.demo.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2018/10/25 0025.
 */

public interface TeacherMapper extends JpaRepository<Teacher,Long> {
    @Query(value = "select * from Teachers",nativeQuery = true)
  List<Teacher> teacherinfor();

 Teacher save(Teacher types);
}
