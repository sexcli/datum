package com.bjqg.generator.mapper;

import com.bjqg.generator.domain.GenTable;

import java.util.List;

/**
 * @author: lbj
 * @date: 2023/5/4 10:19
 */
public interface GenTableMapper {

    /**
     *
     * @param: genTable
     * @return : {@link List< GenTable>}
     * @author : lbj
     * @description: <查询业务列表>
     * @date : 2023/5/4 10:23
     */
    public List<GenTable> selectGenTableList(GenTable genTable);

    /**
     *
     * @param: genTable 业务信息
     * @return : {@link List< GenTable>}
     * @author : lbj
     * @description: <查询数据库列表>
     * @date : 2023/5/4 10:25
     */
    public List<GenTable> selectDbTableList(GenTable genTable);

    /**
     *
     * @param: tableNames   表名称组
     * @return : {@link List< GenTable>}
     * @author : lbj
     * @description: <查询数据库列表>
     * @date : 2023/5/4 10:30
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /** 查询所有表信息 */
    public List<GenTable> selectGenTableAll();

    /**
     *
     * @param: id 业务ID
     * @return : {@link GenTable}
     * @author : lbj
     * @description: <查询表ID业务信息>
     * @date : 2023/5/4 10:34
     */
    public GenTable selectGenTableById(Long id);

    /**
     *
     * @param: tableName 表名称
     * @return : {@link GenTable}
     * @author : lbj
     * @description: <查询表名称业务信息>
     * @date : 2023/5/4 10:39
     */
    public GenTable selectGenTableByName(String tableName);

    /** 新增业务 */
    public int insertGenTable(GenTable genTable);

    /** 修改业务 */
    public int updateGenTable(GenTable genTable);

    /** 删除业务 */
    public int deleteGenTableByIds(Long[] ids);
}
