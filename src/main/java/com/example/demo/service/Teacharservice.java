package com.example.demo.service;


import com.example.demo.pojo.Teacher;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/10/25 0025.
 */
@Service
public interface Teacharservice {
    //导出
    public List<Teacher> teacherinfor();
//导入
    void saveExcelList(List<Teacher> typeLists);
}
