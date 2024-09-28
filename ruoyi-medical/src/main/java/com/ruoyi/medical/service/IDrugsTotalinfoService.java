package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DrugsTotalinfo;

/**
 * 取药信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public interface IDrugsTotalinfoService 
{
    /**
     * 查询取药信息
     * 
     * @param totalinfoId 取药信息主键
     * @return 取药信息
     */
    public DrugsTotalinfo selectDrugsTotalinfoByTotalinfoId(Long totalinfoId);

    /**
     * 查询取药信息列表
     * 
     * @param drugsTotalinfo 取药信息
     * @return 取药信息集合
     */
    public List<DrugsTotalinfo> selectDrugsTotalinfoList(DrugsTotalinfo drugsTotalinfo);

    /**
     * 新增取药信息
     * 
     * @param drugsTotalinfo 取药信息
     * @return 结果
     */
    public int insertDrugsTotalinfo(DrugsTotalinfo drugsTotalinfo);

    /**
     * 修改取药信息
     * 
     * @param drugsTotalinfo 取药信息
     * @return 结果
     */
    public int updateDrugsTotalinfo(DrugsTotalinfo drugsTotalinfo);

    /**
     * 批量删除取药信息
     * 
     * @param totalinfoIds 需要删除的取药信息主键集合
     * @return 结果
     */
    public int deleteDrugsTotalinfoByTotalinfoIds(Long[] totalinfoIds);

    /**
     * 删除取药信息信息
     * 
     * @param totalinfoId 取药信息主键
     * @return 结果
     */
    public int deleteDrugsTotalinfoByTotalinfoId(Long totalinfoId);
}
