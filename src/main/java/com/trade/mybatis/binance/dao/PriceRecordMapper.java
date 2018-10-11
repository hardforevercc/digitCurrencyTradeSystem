package com.trade.mybatis.binance.dao;

import com.trade.mybatis.binance.model.PriceRecord;
import com.trade.mybatis.binance.model.PriceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PriceRecordMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PriceRecordExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PriceRecord record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PriceRecord record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PriceRecord> selectByExample(PriceRecordExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PriceRecord selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PriceRecord record, @Param("example") PriceRecordExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PriceRecord record, @Param("example") PriceRecordExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PriceRecord record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PriceRecord record);
}