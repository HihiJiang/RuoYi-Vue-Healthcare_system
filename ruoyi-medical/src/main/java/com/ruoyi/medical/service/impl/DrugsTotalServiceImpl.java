package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DrugsTotalMapper;
import com.ruoyi.medical.domain.DrugsTotal;
import com.ruoyi.medical.service.IDrugsTotalService;

/**
 * 取药记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@Service
public class DrugsTotalServiceImpl implements IDrugsTotalService 
{
    @Autowired
    private DrugsTotalMapper drugsTotalMapper;

    /**
     * 查询取药记录
     * 
     * @param totalId 取药记录主键
     * @return 取药记录
     */
    @Override
    public DrugsTotal selectDrugsTotalByTotalId(String totalId)
    {
        return drugsTotalMapper.selectDrugsTotalByTotalId(totalId);
    }

    /**
     * 查询取药记录列表
     * 
     * @param drugsTotal 取药记录
     * @return 取药记录
     */
    @Override
    public List<DrugsTotal> selectDrugsTotalList(DrugsTotal drugsTotal)
    {
        return drugsTotalMapper.selectDrugsTotalList(drugsTotal);
    }

    /**
     * 新增取药记录
     * 
     * @param drugsTotal 取药记录
     * @return 结果
     */
    @Override
    public int insertDrugsTotal(DrugsTotal drugsTotal)
    {
        return drugsTotalMapper.insertDrugsTotal(drugsTotal);
    }

    /**
     * 修改取药记录
     * 
     * @param drugsTotal 取药记录
     * @return 结果
     */
    @Override
    public int updateDrugsTotal(DrugsTotal drugsTotal)
    {
        return drugsTotalMapper.updateDrugsTotal(drugsTotal);
    }

    /**
     * 批量删除取药记录
     * 
     * @param totalIds 需要删除的取药记录主键
     * @return 结果
     */
    @Override
    public int deleteDrugsTotalByTotalIds(String[] totalIds)
    {
        return drugsTotalMapper.deleteDrugsTotalByTotalIds(totalIds);
    }

    /**
     * 删除取药记录信息
     * 
     * @param totalId 取药记录主键
     * @return 结果
     */
    @Override
    public int deleteDrugsTotalByTotalId(String totalId)
    {
        return drugsTotalMapper.deleteDrugsTotalByTotalId(totalId);
    }
}
