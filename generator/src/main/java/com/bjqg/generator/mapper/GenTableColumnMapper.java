package com.bjqg.generator.mapper;

import com.bjqg.generator.domain.GenTableColumn;

import java.util.List;

/**
 * @author: lbj
 * @date: 2023/5/4 11:01
 */
public interface GenTableColumnMapper {

    /**
     *
     * @param: tableName    表名称
     * @return : {@link List<GenTableColumn>}
     * @author : lbj
     * @description: <根据表名称查询列信息>
     * @date : 2023/5/4 11:05
     */
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     *
     * @param: tableId  表ID
     * @return : {@link List< GenTableColumn>}
     * @author : lbj
     * @description: <查询业务字段列表>
     * @date : 2023/5/4 11:11
     */
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    /** 新增业务字段 */
    public int insertGenTableColumn(GenTableColumn genTableColumn);

    /** 修改业务字段 */
    public int updateGenTableColumn(GenTableColumn genTableColumn);

    /** 删除业务字段 */
    public int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

    /** 批量删除业务字段 */
    public int deleteGenTableColumnByIds(Long[] ids);
}
