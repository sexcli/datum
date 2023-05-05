package com.bjqg.web.utils.poi;

/**
 * @author: lbj
 * @date: 2023/5/4 15:52
 */
public interface ExcelHandlerAdapter {

    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args excel注解args参数组
     *
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
