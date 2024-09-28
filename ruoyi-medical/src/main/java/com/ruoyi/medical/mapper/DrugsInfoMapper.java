package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.DrugsInfo;

/**
 * 药品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public interface DrugsInfoMapper 
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
     * 删除药品信息
     * 
     * @param drugsId 药品信息主键
     * @return 结果
     */
    public int deleteDrugsInfoByDrugsId(Long drugsId);

    /**
     * 批量删除药品信息
     * 
     * @param drugsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDrugsInfoByDrugsIds(Long[] drugsIds);
}
