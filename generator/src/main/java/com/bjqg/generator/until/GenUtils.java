package com.bjqg.generator.until;

import com.bjqg.common.GenConstants;
import com.bjqg.generator.config.GenConfig;
import com.bjqg.generator.domain.GenTable;
import com.bjqg.generator.domain.GenTableColumn;
import com.bjqg.web.utils.common.StringUtils;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

/**
 * @author: lbj
 * @date: 2023/5/5 9:52
 */
public class GenUtils {

    /**
     * 初始化表信息
     */
    public static void initTable(GenTable genTable,String operName){
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(GenConfig.getAuthor());
        genTable.setCreateBy(operName);
    }


    public static void initColumnField(GenTableColumn column,GenTable genTable){
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(genTable.getTableId());
        column.setCreateBy(genTable.getCreateBy());
        // 设置Java字段名
        column.setJavaField(StringUtils.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(GenConstants.TYPE_STRING);
        column.setQueryType(GenConstants.QUERY_EQ);
        if (arraysContains(GenConstants.COLUMNTYPE_STR,dataType) || arraysContains(GenConstants.COLUMNTYPE_TEXT,dataType)){
            // 字符串长度超过500设置为文本
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }else if (arraysContains(GenConstants.COLUMNTYPE_TIME,dataType)){
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        }else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER,dataType)){
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点数，统一用BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0){
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10){
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段(默认所有字段都要插入)
        column.isInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT,columnName) && !column.isPk()){
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST,columnName) && !column.isPk()){
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk())
        {
            column.setIsQuery(GenConstants.REQUIRE);
        }
        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name"))
        {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status"))
        {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type")
                || StringUtils.endsWithIgnoreCase(columnName, "sex"))
        {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "image"))
        {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
        }
        // 文件字段设置文件上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "file"))
        {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "content"))
        {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType,"(") > 0){
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }else {
            return 0;
        }
    }

    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text,"?;表|bj","");
    }

    public static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType,"(") > 0){
            return StringUtils.substringBefore(columnType,"(");
        }else {
            return columnType;
        }
    }

    public static String getBusinessName(String tableName) {
        int lastIndexOf = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return StringUtils.substring(tableName,lastIndexOf + 1,nameLength);
    }

    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return StringUtils.substring(packageName,lastIndex + 1,nameLength);
    }

    public static String convertClassName(String tableName) {
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)){
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName,searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    public static String replaceFirst(String replacement, String[] searchList) {
        String text = replacement;
        for (String searchString : searchList) {
            if (replacement.startsWith(searchString)){
                replacement.replaceFirst(searchString,"");
                break;
            }
        }
        return text;
    }
}
