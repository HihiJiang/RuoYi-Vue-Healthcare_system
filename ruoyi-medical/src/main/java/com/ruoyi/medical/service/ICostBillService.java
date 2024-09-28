package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.CostBill;
import com.ruoyi.medical.dto.CostBillDto;
import com.ruoyi.medical.vo.CostBillVo;

/**
 * 患者费用账单Service接口
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public interface ICostBillService 
{
    /**
     * 查询患者费用账单
     * 
     * @param billId 患者费用账单主键
     * @return 患者费用账单
     */
    public CostBill selectCostBillByBillId(String billId);

    /**
     * 查询患者费用账单列表
     * 
     * @param costBill 患者费用账单
     * @return 患者费用账单集合
     */
    public List<CostBillVo> selectCostBillList(CostBillDto costBill);

    /**
     * 新增患者费用账单
     * 
     * @param costBill 患者费用账单
     * @return 结果
     */
    public int insertCostBill(CostBill costBill);

    /**
     * 修改患者费用账单
     * 
     * @param costBill 患者费用账单
     * @return 结果
     */
    public int updateCostBill(CostBill costBill);

    /**
     * 批量删除患者费用账单
     * 
     * @param billIds 需要删除的患者费用账单主键集合
     * @return 结果
     */
    public int deleteCostBillByBillIds(String[] billIds);

    /**
     * 删除患者费用账单信息
     * 
     * @param billId 患者费用账单主键
     * @return 结果
     */
    public int deleteCostBillByBillId(String billId);
}
