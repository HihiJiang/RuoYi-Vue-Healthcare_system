package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.ReceiverecordMapper;
import com.ruoyi.hospital.domain.Receiverecord;
import com.ruoyi.hospital.service.IReceiverecordService;

/**
 * 接诊记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
@Service
public class ReceiverecordServiceImpl implements IReceiverecordService 
{
    @Autowired
    private ReceiverecordMapper receiverecordMapper;

    /**
     * 查询接诊记录
     * 
     * @param recordsId 接诊记录主键
     * @return 接诊记录
     */
    @Override
    public Receiverecord selectReceiverecordByRecordsId(String recordsId)
    {
        return receiverecordMapper.selectReceiverecordByRecordsId(recordsId);
    }

    /**
     * 查询接诊记录列表
     * 
     * @param receiverecord 接诊记录
     * @return 接诊记录
     */
    @Override
    public List<Receiverecord> selectReceiverecordList(Receiverecord receiverecord)
    {
        return receiverecordMapper.selectReceiverecordList(receiverecord);
    }

    /**
     * 新增接诊记录
     * 
     * @param receiverecord 接诊记录
     * @return 结果
     */
    @Override
    public int insertReceiverecord(Receiverecord receiverecord)
    {
        return receiverecordMapper.insertReceiverecord(receiverecord);
    }

    /**
     * 修改接诊记录
     * 
     * @param receiverecord 接诊记录
     * @return 结果
     */
    @Override
    public int updateReceiverecord(Receiverecord receiverecord)
    {
        return receiverecordMapper.updateReceiverecord(receiverecord);
    }

    /**
     * 批量删除接诊记录
     * 
     * @param recordsIds 需要删除的接诊记录主键
     * @return 结果
     */
    @Override
    public int deleteReceiverecordByRecordsIds(String[] recordsIds)
    {
        return receiverecordMapper.deleteReceiverecordByRecordsIds(recordsIds);
    }

    /**
     * 删除接诊记录信息
     * 
     * @param recordsId 接诊记录主键
     * @return 结果
     */
    @Override
    public int deleteReceiverecordByRecordsId(String recordsId)
    {
        return receiverecordMapper.deleteReceiverecordByRecordsId(recordsId);
    }
}
