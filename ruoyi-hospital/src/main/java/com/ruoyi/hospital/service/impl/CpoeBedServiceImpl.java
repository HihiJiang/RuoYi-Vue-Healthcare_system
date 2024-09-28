package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeBedDTO;
import com.ruoyi.hospital.Vo.CpoeBedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.CpoeBedMapper;
import com.ruoyi.hospital.domain.CpoeBed;
import com.ruoyi.hospital.service.ICpoeBedService;

/**
 * 床位记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
@Service
public class CpoeBedServiceImpl implements ICpoeBedService 
{
    @Autowired
    private CpoeBedMapper cpoeBedMapper;

    /**
     * 查询床位记录
     * 
     * @param bedId 床位记录主键
     * @return 床位记录
     */
    @Override
    public CpoeBed selectCpoeBedByBedId(Long bedId)
    {
        return cpoeBedMapper.selectCpoeBedByBedId(bedId);
    }

    /**
     * 查询床位记录列表
     * 
     * @param cpoeBed 床位记录
     * @return 床位记录
     */
    @Override
    public List<CpoeBedVo> selectCpoeBedList(CpoeBedDTO cpoeBed)
    {
        return cpoeBedMapper.selectCpoeBedList(cpoeBed);
    }

    /**
     * 新增床位记录
     * 
     * @param cpoeBed 床位记录
     * @return 结果
     */
    @Override
    public int insertCpoeBed(CpoeBed cpoeBed)
    {
        return cpoeBedMapper.insertCpoeBed(cpoeBed);
    }

    /**
     * 修改床位记录
     * 
     * @param cpoeBed 床位记录
     * @return 结果
     */
    @Override
    public int updateCpoeBed(CpoeBed cpoeBed)
    {
        return cpoeBedMapper.updateCpoeBed(cpoeBed);
    }

    /**
     * 批量删除床位记录
     * 
     * @param bedIds 需要删除的床位记录主键
     * @return 结果
     */
    @Override
    public int deleteCpoeBedByBedIds(Long[] bedIds)
    {
        return cpoeBedMapper.deleteCpoeBedByBedIds(bedIds);
    }

    /**
     * 删除床位记录信息
     * 
     * @param bedId 床位记录主键
     * @return 结果
     */
    @Override
    public int deleteCpoeBedByBedId(Long bedId)
    {
        return cpoeBedMapper.deleteCpoeBedByBedId(bedId);
    }
}
