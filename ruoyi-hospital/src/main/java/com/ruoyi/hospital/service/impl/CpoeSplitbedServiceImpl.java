package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeSplitbedDTO;
import com.ruoyi.hospital.Vo.CpoeSplitbedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.CpoeSplitbedMapper;
import com.ruoyi.hospital.domain.CpoeSplitbed;
import com.ruoyi.hospital.service.ICpoeSplitbedService;

/**
 * 住院登记Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@Service
public class CpoeSplitbedServiceImpl implements ICpoeSplitbedService 
{
    @Autowired
    private CpoeSplitbedMapper cpoeSplitbedMapper;

    /**
     * 查询住院登记
     * 
     * @param splitbedId 住院登记主键
     * @return 住院登记
     */
    @Override
    public CpoeSplitbed selectCpoeSplitbedBySplitbedId(Long splitbedId)
    {
        return cpoeSplitbedMapper.selectCpoeSplitbedBySplitbedId(splitbedId);
    }

    /**
     * 查询住院登记列表
     * 
     * @param cpoeSplitbed 住院登记
     * @return 住院登记
     */
    @Override
    public List<CpoeSplitbedVo> selectCpoeSplitbedList(CpoeSplitbedDTO cpoeSplitbed)
    {
        return cpoeSplitbedMapper.selectCpoeSplitbedList(cpoeSplitbed);
    }

    /**
     * 新增住院登记
     * 
     * @param cpoeSplitbed 住院登记
     * @return 结果
     */
    @Override
    public int insertCpoeSplitbed(CpoeSplitbed cpoeSplitbed)
    {
        return cpoeSplitbedMapper.insertCpoeSplitbed(cpoeSplitbed);
    }

    /**
     * 修改住院登记
     * 
     * @param cpoeSplitbed 住院登记
     * @return 结果
     */
    @Override
    public int updateCpoeSplitbed(CpoeSplitbed cpoeSplitbed)
    {
        return cpoeSplitbedMapper.updateCpoeSplitbed(cpoeSplitbed);
    }

    /**
     * 批量删除住院登记
     * 
     * @param splitbedIds 需要删除的住院登记主键
     * @return 结果
     */
    @Override
    public int deleteCpoeSplitbedBySplitbedIds(Long[] splitbedIds)
    {
        return cpoeSplitbedMapper.deleteCpoeSplitbedBySplitbedIds(splitbedIds);
    }

    /**
     * 删除住院登记信息
     * 
     * @param splitbedId 住院登记主键
     * @return 结果
     */
    @Override
    public int deleteCpoeSplitbedBySplitbedId(Long splitbedId)
    {
        return cpoeSplitbedMapper.deleteCpoeSplitbedBySplitbedId(splitbedId);
    }
}
