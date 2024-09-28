package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DrugsInfo;

/**
 * 药品信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public interface IDrugsInfoService 
{
    /**
     * 查询药品信息
     * 
     * @param drugsId 药品信息主键
     * @return 药品信息
     */
    public DrugsInfo selectDrugsInfoByDrugsId(Long drugsId);

    /**
     * 查询药品信息列表
     * 
     * @param drugsInfo 药品信息
     * @return 药品信息集合
     */
    public List<DrugsInfo> selectDrugsInfoList(DrugsInfo drugsInfo);

    /**
     * 新增药品信息
     * 
     * @param drugsInfo 药品信息
     * @return 结果
     */
    public int insertDrugsInfo(DrugsInfo drugsInfo);

    /**
     * 修改药品信息
     * 
     * @param drugsInfo 药品信息
     * @return 结果
     */
    public int updateDrugsInfo(DrugsInfo drugsInfo);

    /**
     * 批量删除药品信息
     * 
     * @param drugsIds 需要删除的药品信息主键集合
     * @return 结果
     */
    public int deleteDrugsInfoByDrugsIds(Long[] drugsIds);

    /**
     * 删除药品信息信息
     * 
     * @param drugsId 药品信息主键
     * @return 结果
     */
    public int deleteDrugsInfoByDrugsId(Long drugsId);

    /**
     * 根据药品的拼音字段模糊查询
     * @param drugsInfo
     * @return
     */
    public  List<DrugsInfo> selectDrugsByPingyin(DrugsInfo drugsInfo);
}
