package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.BillDetail;

/**
 * 患者费用明细Service接口
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public interface IBillDetailService 
{
    /**
     * 查询患者费用明细
     * 
     * @param billdetailId 患者费用明细主键
     * @return 患者费用明细
     */
    public BillDetail selectBillDetailByBilldetailId(Long billdetailId);

    /**
     * 查询患者费用明细列表
     * 
     * @param billDetail 患者费用明细
     * @return 患者费用明细集合
     */
    public List<BillDetail> selectBillDetailList(BillDetail billDetail);

    /**
     * 新增患者费用明细
     * 
     * @param billDetail 患者费用明细
     * @return 结果
     */
    public int insertBillDetail(BillDetail billDetail);

    /**
     * 修改患者费用明细
     * 
     * @param billDetail 患者费用明细
     * @return 结果
     */
    public int updateBillDetail(BillDetail billDetail);

    /**
     * 批量删除患者费用明细
     * 
     * @param billdetailIds 需要删除的患者费用明细主键集合
     * @return 结果
     */
    public int deleteBillDetailByBilldetailIds(Long[] billdetailIds);

    /**
     * 删除患者费用明细信息
     * 
     * @param billdetailId 患者费用明细主键
     * @return 结果
     */
    public int deleteBillDetailByBilldetailId(Long billdetailId);
}
