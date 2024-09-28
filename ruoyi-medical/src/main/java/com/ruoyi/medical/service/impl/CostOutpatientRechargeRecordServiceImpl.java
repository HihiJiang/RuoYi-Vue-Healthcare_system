package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.CostOutpatientRechargeRecordMapper;
import com.ruoyi.medical.domain.CostOutpatientRechargeRecord;
import com.ruoyi.medical.service.ICostOutpatientRechargeRecordService;

/**
 * 门诊卡充值取现记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-07
 */
@Service
public class CostOutpatientRechargeRecordServiceImpl implements ICostOutpatientRechargeRecordService 
{
    @Autowired
    private CostOutpatientRechargeRecordMapper costOutpatientRechargeRecordMapper;

    /**
     * 查询门诊卡充值取现记录
     * 
     * @param rechargeId 门诊卡充值取现记录主键
     * @return 门诊卡充值取现记录
     */
    @Override
    public CostOutpatientRechargeRecord selectCostOutpatientRechargeRecordByRechargeId(Long rechargeId)
    {
        return costOutpatientRechargeRecordMapper.selectCostOutpatientRechargeRecordByRechargeId(rechargeId);
    }

    /**
     * 查询门诊卡充值取现记录列表
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 门诊卡充值取现记录
     */
    @Override
    public List<CostOutpatientRechargeRecord> selectCostOutpatientRechargeRecordList(CostOutpatientRechargeRecord costOutpatientRechargeRecord)
    {
        return costOutpatientRechargeRecordMapper.selectCostOutpatientRechargeRecordList(costOutpatientRechargeRecord);
    }

    /**
     * 新增门诊卡充值取现记录
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 结果
     */
    @Override
    public int insertCostOutpatientRechargeRecord(CostOutpatientRechargeRecord costOutpatientRechargeRecord)
    {
        return costOutpatientRechargeRecordMapper.insertCostOutpatientRechargeRecord(costOutpatientRechargeRecord);
    }

    /**
     * 修改门诊卡充值取现记录
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 结果
     */
    @Override
    public int updateCostOutpatientRechargeRecord(CostOutpatientRechargeRecord costOutpatientRechargeRecord)
    {
        return costOutpatientRechargeRecordMapper.updateCostOutpatientRechargeRecord(costOutpatientRechargeRecord);
    }

    /**
     * 批量删除门诊卡充值取现记录
     * 
     * @param rechargeIds 需要删除的门诊卡充值取现记录主键
     * @return 结果
     */
    @Override
    public int deleteCostOutpatientRechargeRecordByRechargeIds(Long[] rechargeIds)
    {
        return costOutpatientRechargeRecordMapper.deleteCostOutpatientRechargeRecordByRechargeIds(rechargeIds);
    }

    /**
     * 删除门诊卡充值取现记录信息
     * 
     * @param rechargeId 门诊卡充值取现记录主键
     * @return 结果
     */
    @Override
    public int deleteCostOutpatientRechargeRecordByRechargeId(Long rechargeId)
    {
        return costOutpatientRechargeRecordMapper.deleteCostOutpatientRechargeRecordByRechargeId(rechargeId);
    }
}
