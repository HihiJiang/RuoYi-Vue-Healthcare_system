package com.ruoyi.hospital.service;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeSplitbedDTO;
import com.ruoyi.hospital.Vo.CpoeSplitbedVo;
import com.ruoyi.hospital.domain.CpoeSplitbed;

/**
 * 住院登记Service接口
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public interface ICpoeSplitbedService 
{
    /**
     * 查询住院登记
     * 
     * @param splitbedId 住院登记主键
     * @return 住院登记
     */
    public CpoeSplitbed selectCpoeSplitbedBySplitbedId(Long splitbedId);

    /**
     * 查询住院登记列表
     * 
     * @param cpoeSplitbed 住院登记
     * @return 住院登记集合
     */
    public List<CpoeSplitbedVo> selectCpoeSplitbedList(CpoeSplitbedDTO cpoeSplitbed);

    /**
     * 新增住院登记
     * 
     * @param cpoeSplitbed 住院登记
     * @return 结果
     */
    public int insertCpoeSplitbed(CpoeSplitbed cpoeSplitbed);

    /**
     * 修改住院登记
     * 
     * @param cpoeSplitbed 住院登记
     * @return 结果
     */
    public int updateCpoeSplitbed(CpoeSplitbed cpoeSplitbed);

    /**
     * 批量删除住院登记
     * 
     * @param splitbedIds 需要删除的住院登记主键集合
     * @return 结果
     */
    public int deleteCpoeSplitbedBySplitbedIds(Long[] splitbedIds);

    /**
     * 删除住院登记信息
     * 
     * @param splitbedId 住院登记主键
     * @return 结果
     */
    public int deleteCpoeSplitbedBySplitbedId(Long splitbedId);
}
