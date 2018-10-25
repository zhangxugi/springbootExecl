package com.example.demo.service;

import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2018/10/25 0025.
 */

@Service
public class TeacherImpl implements Teacharservice{
@Autowired private TeacherMapper teacherMapper;
    @Override
    public List<Teacher> teacherinfor() {
        return teacherMapper.teacherinfor();
    }
    @Override
    public void saveExcelList(List<Teacher> typeLists) {
        for (Teacher types : typeLists) {
            //调用mapper的保存方法
            teacherMapper.save(types);
        }

    }


}
