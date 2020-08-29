package com.kuaizi.bean;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-21:29
 */
//页面对象
public class Page<T> {
    //设置页面展示的容量大小
    public static final Integer PAGE_SIZE=4;
    //起始页
    private Integer pageNo;
    //总页数
    private Integer pageTotal;
    //页面容量
    private Integer pageSize=PAGE_SIZE;
    //总的记录数
    private Integer pageTotalCount;
    //当前页的数据
    private List<T> items;
    //分页条的请求地址
    private String url;

    public Integer getPageNO() {
        return pageNo;
    }

    public void setPageNO(Integer pageNo) {
        if(pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo =pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }



}
