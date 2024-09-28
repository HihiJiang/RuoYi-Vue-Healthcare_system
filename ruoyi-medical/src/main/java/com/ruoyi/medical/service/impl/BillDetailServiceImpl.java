package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.BillDetailMapper;
import com.ruoyi.medical.domain.BillDetail;
import com.ruoyi.medical.service.IBillDetailService;

/**
 * 患者费用明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@Service
public class BillDetailServiceImpl implements IBillDetailService 
{
    @Autowired
    private BillDetailMapper billDetailMapper;

    /**
     * 查询患者费用明细
     * 
     * @param billdetailId 患者费用明细主键
     * @return 患者费用明细
     */
    @Override
    public BillDetail selectBillDetailByBilldetailId(Long billdetailId)
    {
        return billDetailMapper.selectBillDetailByBilldetailId(billdetailId);
    }

    /**
     * 查询患者费用明细列表
     * 
     * @param billDetail 患者费用明细
     * @return 患者费用明细
     */
    @Override
    public List<BillDetail> selectBillDetailList(BillDetail billDetail)
    {
        return billDetailMapper.selectBillDetailList(billDetail);
    }

    /**
     * 新增患者费用明细
     * 
     * @param billDetail 患者费用明细
     * @return 结果
     */
    @Override
    public int insertBillDetail(BillDetail billDetail)
    {
        return billDetailMapper.insertBillDetail(billDetail);
    }

    /**
     * 修改患者费用明细
     * 
     * @param billDetail 患者费用明细
     * @return 结果
     */
    @Override
    public int updateBillDetail(BillDetail billDetail)
    {
        return billDetailMapper.updateBillDetail(billDetail);
    }

    /**
     * 批量删除患者费用明细
     * 
     * @param billdetailIds 需要删除的患者费用明细主键
     * @return 结果
     */
    @Override
    public int deleteBillDetailByBilldetailIds(Long[] billdetailIds)
    {
        return billDetailMapper.deleteBillDetailByBilldetailIds(billdetailIds);
    }

    /**
     * 删除患者费用明细信息
     * 
     * @param billdetailId 患者费用明细主键
     * @return 结果
     */
    @Override
    public int deleteBillDetailByBilldetailId(Long billdetailId)
    {
        return billDetailMapper.deleteBillDetailByBilldetailId(billdetailId);
    }
}
