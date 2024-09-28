package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DrugsTotal;

/**
 * 取药记录Service接口
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public interface IDrugsTotalService 
{
    /**
     * 查询取药记录
     * 
     * @param totalId 取药记录主键
     * @return 取药记录
     */
    public DrugsTotal selectDrugsTotalByTotalId(String totalId);

    /**
     * 查询取药记录列表
     * 
     * @param drugsTotal 取药记录
     * @return 取药记录集合
     */
    public List<DrugsTotal> selectDrugsTotalList(DrugsTotal drugsTotal);

    /**
     * 新增取药记录
     * 
     * @param drugsTotal 取药记录
     * @return 结果
     */
    public int insertDrugsTotal(DrugsTotal drugsTotal);

    /**
     * 修改取药记录
     * 
     * @param drugsTotal 取药记录
     * @return 结果
     */
    public int updateDrugsTotal(DrugsTotal drugsTotal);

    /**
     * 批量删除取药记录
     * 
     * @param totalIds 需要删除的取药记录主键集合
     * @return 结果
     */
    public int deleteDrugsTotalByTotalIds(String[] totalIds);

    /**
     * 删除取药记录信息
     * 
     * @param totalId 取药记录主键
     * @return 结果
     */
    public int deleteDrugsTotalByTotalId(String totalId);
}
