package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DrugsTotalinfoMapper;
import com.ruoyi.medical.domain.DrugsTotalinfo;
import com.ruoyi.medical.service.IDrugsTotalinfoService;

/**
 * 取药信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@Service
public class DrugsTotalinfoServiceImpl implements IDrugsTotalinfoService 
{
    @Autowired
    private DrugsTotalinfoMapper drugsTotalinfoMapper;

    /**
     * 查询取药信息
     * 
     * @param totalinfoId 取药信息主键
     * @return 取药信息
     */
    @Override
    public DrugsTotalinfo selectDrugsTotalinfoByTotalinfoId(Long totalinfoId)
    {
        return drugsTotalinfoMapper.selectDrugsTotalinfoByTotalinfoId(totalinfoId);
    }

    /**
     * 查询取药信息列表
     * 
     * @param drugsTotalinfo 取药信息
     * @return 取药信息
     */
    @Override
    public List<DrugsTotalinfo> selectDrugsTotalinfoList(DrugsTotalinfo drugsTotalinfo)
    {
        return drugsTotalinfoMapper.selectDrugsTotalinfoList(drugsTotalinfo);
    }

    /**
     * 新增取药信息
     * 
     * @param drugsTotalinfo 取药信息
     * @return 结果
     */
    @Override
    public int insertDrugsTotalinfo(DrugsTotalinfo drugsTotalinfo)
    {
        return drugsTotalinfoMapper.insertDrugsTotalinfo(drugsTotalinfo);
    }

    /**
     * 修改取药信息
     * 
     * @param drugsTotalinfo 取药信息
     * @return 结果
     */
    @Override
    public int updateDrugsTotalinfo(DrugsTotalinfo drugsTotalinfo)
    {
        return drugsTotalinfoMapper.updateDrugsTotalinfo(drugsTotalinfo);
    }

    /**
     * 批量删除取药信息
     * 
     * @param totalinfoIds 需要删除的取药信息主键
     * @return 结果
     */
    @Override
    public int deleteDrugsTotalinfoByTotalinfoIds(Long[] totalinfoIds)
    {
        return drugsTotalinfoMapper.deleteDrugsTotalinfoByTotalinfoIds(totalinfoIds);
    }

    /**
     * 删除取药信息信息
     * 
     * @param totalinfoId 取药信息主键
     * @return 结果
     */
    @Override
    public int deleteDrugsTotalinfoByTotalinfoId(Long totalinfoId)
    {
        return drugsTotalinfoMapper.deleteDrugsTotalinfoByTotalinfoId(totalinfoId);
    }
}
