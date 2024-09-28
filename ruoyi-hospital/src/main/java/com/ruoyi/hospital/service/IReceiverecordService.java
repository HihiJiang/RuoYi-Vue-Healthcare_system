package com.ruoyi.hospital.service;

import java.util.List;
import com.ruoyi.hospital.domain.Receiverecord;

/**
 * 接诊记录Service接口
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
public interface IReceiverecordService 
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
     * 批量删除接诊记录
     * 
     * @param recordsIds 需要删除的接诊记录主键集合
     * @return 结果
     */
    public int deleteReceiverecordByRecordsIds(String[] recordsIds);

    /**
     * 删除接诊记录信息
     * 
     * @param recordsId 接诊记录主键
     * @return 结果
     */
    public int deleteReceiverecordByRecordsId(String recordsId);
}
