package com.example.demo.controller;


import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.Teacharservice;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/25 0025.
 */
@Controller
public class TearCollertr {
    @Autowired private Teacharservice teacharservice;
    @Autowired private TeacherMapper teacherMapper;
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Teacher> classmateList = teacharservice.teacherinfor();

        String fileName = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "学号", "姓名", "身份类型", "登录密码"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Teacher teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getTnos());
            row1.createCell(1).setCellValue(teacher.getTnames());
            row1.createCell(2).setCellValue(teacher.getTypeses());
            row1.createCell(3).setCellValue(teacher.getTpasswords());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    //导入

    @RequestMapping("Excelfile")

    public  String upload(MultipartFile file, HttpServletRequest request) {
        try {
            List<Teacher> typeLists = new ArrayList<Teacher>();

            System.out.println("开始");
            //使用POI解析Excel文件
            //如果是xls，使用HSSFWorkbook；2003年的excel  如果是xlsx，使用XSSFWorkbook  2007年excel
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //根据名称获得指定Sheet对象
            HSSFSheet hssfSheet = workbook.getSheetAt(0);
            for (Row row : hssfSheet) {
                Teacher Type = new Teacher();
                int rowNum = row.getRowNum();
                if(rowNum == 0){//跳出第一行   一般第一行都是表头没有数据意义
                    continue;
                }
                if(row.getCell(0)!=null){//第1列数据
                    row.getCell(0).setCellType(CellType.STRING);
                    Type.setTnos(Integer.parseInt(row.getCell(0).getStringCellValue()));
                }
                if(row.getCell(1)!=null){//第2列数据
                   row.getCell(1).setCellType(CellType.STRING);
                    Type.setTnames(row.getCell(1).getStringCellValue());
                }
                if(row.getCell(2)!=null){//第3列
                    row.getCell(2).setCellType(CellType.STRING);
                    Type.setTypeses(row.getCell(2).getStringCellValue());
                }

//	    		 转换为Integer类型
                if(row.getCell(3)!=null){//第4列
                    row.getCell(3).setCellType(CellType.STRING);
                    //Type.setAdduserid(Integer.parseInt(row.getCell(3).getStringCellValue()));
                    Type.setTpasswords(row.getCell(3).getStringCellValue());
                }
                //  转换为日期类型
               /* if(row.getCell(4)!=null){//第5列
                    row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Type.setAddtime( HSSFDateUtil.getJavaDate(row.getCell(4).getNumericCellValue()));
                }*/

                typeLists.add(Type);
            }
            //调用service执行保存typeLists的方法
            teacharservice.saveExcelList(typeLists);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/list";
    }

@RequestMapping("list")
    public String select(Model model){
    List<Teacher> list=teacherMapper.findAll();
    model.addAttribute("lists",list);
        return "mian";
}


}
