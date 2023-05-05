package com.bjqg.web.utils.common;

import com.bjqg.web.core.page.PageDomain;
import com.bjqg.web.core.page.TableSupport;
import com.bjqg.web.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;

/**
 * @author: lbj
 * @date: 2023/5/4 14:22
 */
public class PageUtils extends PageHelper {

    /** 设置请求分页数据 */
    public static void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum,pageSize,orderBy).setReasonable(reasonable);
    }

    /** 清理分页的线程变量 */
    public static void clearPage(){
        PageHelper.clearPage();
    }
}
