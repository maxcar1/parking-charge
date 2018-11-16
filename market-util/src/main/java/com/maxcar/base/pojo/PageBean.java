package com.maxcar.base.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * Created by sunny on 2018/4/12.
 */
public class PageBean implements Serializable{
    // 传递的参数或是配置的参数
    private int currentPage=1; // 当前页
    private int pageSize = 20; // 每页显示多少条记录
    // 查询数据库
    private int recordCount; // 总记录数
    private List recordList;//集合
    // 计算
    private int pageCount; // 总页数
    private int beginPageIndex =  0; // 页码列表的开始索引（包含）
    private int endPageIndex = 20; // 页码列表的结束索引（包含）

    public PageBean() {
        beginPageIndex = currentPage*pageSize-pageSize;
        endPageIndex = currentPage*pageSize;
        pageCount = (recordCount + pageSize - 1) / pageSize;
    }

    /**
     * 只接受4个必要的属性，会自动的计算出其他3个属性的值
     *
     * @param currentPage
     * @param pageSize
     * @param recordCount
     */
    public PageBean(int currentPage, int pageSize, int recordCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordCount = recordCount;

        // 计算 pageCount
        pageCount = (recordCount + pageSize - 1) / pageSize;

        // 计算 beginPageIndex 与 endPageIndex
        // >> 总页码小于等于10页时，全部显示
        //        if (pageCount <= 10) {
            beginPageIndex = currentPage*pageSize-pageSize;
            endPageIndex = currentPage*pageSize;
        //        }
        //        else {
        //
        //        }
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        pageCount = (recordCount + pageSize - 1) / pageSize;
        this.recordCount = recordCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }
    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }

    public static void main(String[] args){
        PageBean bean = new PageBean(1,2,4);
        System.out.println(bean);
    }
}
