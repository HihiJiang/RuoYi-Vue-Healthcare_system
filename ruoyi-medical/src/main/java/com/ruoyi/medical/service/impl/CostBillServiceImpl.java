package com.ruoyi.medical.service.impl;

import java.util.List;

import com.ruoyi.medical.dto.CostBillDto;
import com.ruoyi.medical.vo.CostBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.CostBillMapper;
import com.ruoyi.medical.domain.CostBill;
import com.ruoyi.medical.service.ICostBillService;

/**
 * 患者费用账单Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@Service
public class CostBillServiceImpl implements ICostBillService 
{
    @Autowired
    private CostBillMapper costBillMapper;

    /**
     * 查询患者费用账单
     * 
     * @param billId 患者费用账单主键
     * @return 患者费用账单
     */
    @Override
    public CostBill selectCostBillByBillId(String billId)
    {
        return costBillMapper.selectCostBillByBillId(billId);
    }

    /**
     * 查询患者费用账单列表
     * 
     * @param costBill 患者费用账单
     * @return 患者费用账单
     */
    @Override
    public List<CostBillVo> selectCostBillList(CostBillDto costBill)
    {
        return costBillMapper.selectCostBillList(costBill);
    }

    /**
     * 新增患者费用账单
     * 
     * @param costBill 患者费用账单
     * @return 结果
     */
    @Override
    public int insertCostBill(CostBill costBill)
    {
        return costBillMapper.insertCostBill(costBill);
    }

    /**
     * 修改患者费用账单
     * 
     * @param costBill 患者费用账单
     * @return 结果
     */
    @Override
    public int updateCostBill(CostBill costBill)
    {
        return costBillMapper.updateCostBill(costBill);
    }

    /**
     * 批量删除患者费用账单
     * 
     * @param billIds 需要删除的患者费用账单主键
     * @return 结果
     */
    @Override
    public int deleteCostBillByBillIds(String[] billIds)
    {
        return costBillMapper.deleteCostBillByBillIds(billIds);
    }

    /**
     * 删除患者费用账单信息
     * 
     * @param billId 患者费用账单主键
     * @return 结果
     */
    @Override
    public int deleteCostBillByBillId(String billId)
    {
        return costBillMapper.deleteCostBillByBillId(billId);
    }
}
