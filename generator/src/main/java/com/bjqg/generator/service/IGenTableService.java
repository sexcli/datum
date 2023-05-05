package com.bjqg.generator.service;

import com.bjqg.generator.domain.GenTable;

import java.util.List;
import java.util.Map;

/**
 * @author: lbj
 * @date: 2023/5/4 11:19
 */
public interface IGenTableService {

    /** 查询业务列表 */
    public List<GenTable> selectGenTableList(GenTable genTable);

    /** 查询数据库业务列表 */
    public List<GenTable> selectDbTableList(GenTable genTable);

    /** 查询数据库列表 */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /** 查询所有列表 */
    public List<GenTable> selectGenTableAll();

    /** 查询业务信息 */
    public GenTable selectGenTableById(Long id);

    /** 修改业务 */
    public void updateGenTable(GenTable genTable);

    /** 删除业务 */
    public void deleteGenTableByIds(Long[] ids);

    /** 导入表结构 */
    public void importGenTable(List<GenTable> tableList);

    /** 预览代码 */
    public Map<String,String> previewCode(Long tableId);

    /** 生成代码(下载方式) */
    public byte[] downloadCode(String tableName);

    /** 生成代码(自定义路径) */
    public void generatorCode(String tableName);

    /** 同步数据库 */
    public void synchDb(String tableName);

    /** 批量生成代码(下载方式) */
    public byte[] downloadCode(String[] tableNames);

    /** 修改保存参数校验 */
    public void validateEdit(GenTable genTable);
}
