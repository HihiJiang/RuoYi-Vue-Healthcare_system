package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.Receiverecord;

/**
 * 接诊记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
public interface ReceiverecordMapper 
{
    /**
     * 查询接诊记录
     * 
     * @param recordsId 接诊记录主键
     * @return 接诊记录
     */
    public Receiverecord selectReceiverecordByRecordsId(String recordsId);

    /**
     * 查询接诊记录列表
     * 
     * @param receiverecord 接诊记录
     * @return 接诊记录集合
     */
    public List<Receiverecord> selectReceiverecordList(Receiverecord receiverecord);

    /**
     * 新增接诊记录
     * 
     * @param receiverecord 接诊记录
     * @return 结果
     */
    public int insertReceiverecord(Receiverecord receiverecord);

    /**
     * 修改接诊记录
     * 
     * @param receiverecord 接诊记录
     * @return 结果
     */
    public int updateReceiverecord(Receiverecord receiverecord);

    /**
     * 删除接诊记录
     * 
     * @param recordsId 接诊记录主键
     * @return 结果
     */
    public int deleteReceiverecordByRecordsId(String recordsId);

    /**
     * 批量删除接诊记录
     * 
     * @param recordsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceiverecordByRecordsIds(String[] recordsIds);
}
