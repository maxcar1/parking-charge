package com.maxcar.stock.controller;

import com.github.pagehelper.PageInfo;
import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.ExcelUtil;
import com.maxcar.stock.pojo.CarRecord;
import com.maxcar.stock.service.CarRecordService;
import com.maxcar.user.entity.User;
import com.maxcar.web.aop.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chiyanlong on 2018/8/23.
 */
@RequestMapping("/api/carRecord")
@RestController
public class CarRecordController extends BaseController {

    @Autowired
    private CarRecordService carRecordService;

//    @Value(value="${headImgPath}")    //后台图片保存地址
//    private String headImgPath;
//
//    @Value(value="${uploadHost}")
//    private String uploadHost;    //项目host路径
//
//    @Value(value="${sysUserDefImg}")
//    private String sysUserDefImg;    //系统用户默认头像

    /**
     * 统计车进出道闸量
     * （因为参数过多，使用post）
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getCarRecordCounts", method = RequestMethod.POST)
    @OperationAnnotation(title = "统计车进出道闸量")
    public InterfaceResult getCarRecordCounts(@RequestBody CarRecord carRecord) {
        InterfaceResult result = new InterfaceResult();
        PageInfo pageInfo = carRecordService.listCarRecordCounts(carRecord);
        result.InterfaceResult200(pageInfo);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/exportCarRecord", method = RequestMethod.POST)
    public void exportCarRecord(@RequestBody CarRecord carRecord, HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("name", "".equals(name) || null == name ? null : name);
//        params.put("phone", "".equals(phone) || null == phone ? null : phone);
//        params.put("email", "".equals(email) || null == email ? null : email);
//        params.put("roleId", "".equals(roleId) || null == roleId ? null : roleId);
//        params.put("createTimeStart", "".equals(createTimeStart) || null == createTimeStart ? null : createTimeStart+" 00:00:00");
//        params.put("createTimeEnd", "".equals(createTimeEnd) || null == createTimeEnd ? null : createTimeEnd+" 23:59:59");
//        params.put("status", "".equals(status) || null == status ? null : status);
//        params.put("departmentId", "".equals(departmentId) || null == departmentId ? null : departmentId);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTime = sdf.format(d);
        String fileName = "道闸出入量-" + formatTime;
        //填充projects数据
        PageInfo pageInfo = carRecordService.listCarRecordCounts(carRecord);
        List<Map<String, Object>> list = createExcelRecord(pageInfo.getList());
        String columnNames[] = {"序号", "道闸", "类型", "出入次数"};//列名
        String keys[] = {"id", "channelName", "typeName", "counts"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        res.reset();
        res.setContentType("application/vnd.ms-excel;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = res.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    /**
     * 生成Excel数据
     *
     * @param carRecordList
     * @return
     */
    private List<Map<String, Object>> createExcelRecord(List<CarRecord> carRecordList) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        CarRecord carRecord = null;
        for (int j = 0; j < carRecordList.size(); j++) {
            carRecord = carRecordList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", carRecord.getId());
            mapValue.put("channelName", carRecord.getChannelName());
            mapValue.put("typeName", carRecord.getTypeName());
            mapValue.put("counts", carRecord.getCounts());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
