package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.CostOutpatientRechargeRecord;

/**
 * 门诊卡充值取现记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-07
 */
public interface CostOutpatientRechargeRecordMapper 
{
    /**
     * 查询门诊卡充值取现记录
     * 
     * @param rechargeId 门诊卡充值取现记录主键
     * @return 门诊卡充值取现记录
     */
    public CostOutpatientRechargeRecord selectCostOutpatientRechargeRecordByRechargeId(Long rechargeId);

    /**
     * 查询门诊卡充值取现记录列表
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 门诊卡充值取现记录集合
     */
    public List<CostOutpatientRechargeRecord> selectCostOutpatientRechargeRecordList(CostOutpatientRechargeRecord costOutpatientRechargeRecord);

    /**
     * 新增门诊卡充值取现记录
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 结果
     */
    public int insertCostOutpatientRechargeRecord(CostOutpatientRechargeRecord costOutpatientRechargeRecord);

    /**
     * 修改门诊卡充值取现记录
     * 
     * @param costOutpatientRechargeRecord 门诊卡充值取现记录
     * @return 结果
     */
    public int updateCostOutpatientRechargeRecord(CostOutpatientRechargeRecord costOutpatientRechargeRecord);

    /**
     * 删除门诊卡充值取现记录
     * 
     * @param rechargeId 门诊卡充值取现记录主键
     * @return 结果
     */
    public int deleteCostOutpatientRechargeRecordByRechargeId(Long rechargeId);

    /**
     * 批量删除门诊卡充值取现记录
     * 
     * @param rechargeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCostOutpatientRechargeRecordByRechargeIds(Long[] rechargeIds);
}
