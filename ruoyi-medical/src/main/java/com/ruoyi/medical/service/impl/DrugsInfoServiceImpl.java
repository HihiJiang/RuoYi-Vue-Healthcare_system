package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DrugsInfoMapper;
import com.ruoyi.medical.domain.DrugsInfo;
import com.ruoyi.medical.service.IDrugsInfoService;

/**
 * 药品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@Service
public class DrugsInfoServiceImpl implements IDrugsInfoService 
{
    @Autowired
    private DrugsInfoMapper drugsInfoMapper;

    /**
     * 查询药品信息
     * 
     * @param drugsId 药品信息主键
     * @return 药品信息
     */
    @Override
    public DrugsInfo selectDrugsInfoByDrugsId(Long drugsId)
    {
        return drugsInfoMapper.selectDrugsInfoByDrugsId(drugsId);
    }

    /**
     * 查询药品信息列表
     * 
     * @param drugsInfo 药品信息
     * @return 药品信息
     */
    @Override
    public List<DrugsInfo> selectDrugsInfoList(DrugsInfo drugsInfo)
    {
        return drugsInfoMapper.selectDrugsInfoList(drugsInfo);
    }

    /**
     * 新增药品信息
     * 
     * @param drugsInfo 药品信息
     * @return 结果
     */
    @Override
    public int insertDrugsInfo(DrugsInfo drugsInfo)
    {
        return drugsInfoMapper.insertDrugsInfo(drugsInfo);
    }

    /**
     * 修改药品信息
     * 
     * @param drugsInfo 药品信息
     * @return 结果
     */
    @Override
    public int updateDrugsInfo(DrugsInfo drugsInfo)
    {
        return drugsInfoMapper.updateDrugsInfo(drugsInfo);
    }

    /**
     * 批量删除药品信息
     * 
     * @param drugsIds 需要删除的药品信息主键
     * @return 结果
     */
    @Override
    public int deleteDrugsInfoByDrugsIds(Long[] drugsIds)
    {
        return drugsInfoMapper.deleteDrugsInfoByDrugsIds(drugsIds);
    }

    /**
     * 删除药品信息信息
     * 
     * @param drugsId 药品信息主键
     * @return 结果
     */
    @Override
    public int deleteDrugsInfoByDrugsId(Long drugsId)
    {
        return drugsInfoMapper.deleteDrugsInfoByDrugsId(drugsId);
    }

    @Override
    public List<DrugsInfo> selectDrugsByPingyin(DrugsInfo drugsInfo) {
        return drugsInfoMapper.selectDrugsInfoList(drugsInfo);
    }
}
