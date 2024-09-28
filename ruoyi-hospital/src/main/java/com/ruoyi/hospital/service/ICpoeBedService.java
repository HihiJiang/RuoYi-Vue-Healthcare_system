package com.ruoyi.hospital.service;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeBedDTO;
import com.ruoyi.hospital.Vo.CpoeBedVo;
import com.ruoyi.hospital.domain.CpoeBed;

/**
 * 床位记录Service接口
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
public interface ICpoeBedService 
{
    /**
     * 查询床位记录
     * 
     * @param bedId 床位记录主键
     * @return 床位记录
     */
    public CpoeBed selectCpoeBedByBedId(Long bedId);

    /**
     * 查询床位记录列表
     * 
     * @param cpoeBed 床位记录
     * @return 床位记录集合
     */
    public List<CpoeBedVo> selectCpoeBedList(CpoeBedDTO cpoeBed);

    /**
     * 新增床位记录
     * 
     * @param cpoeBed 床位记录
     * @return 结果
     */
    public int insertCpoeBed(CpoeBed cpoeBed);

    /**
     * 修改床位记录
     * 
     * @param cpoeBed 床位记录
     * @return 结果
     */
    public int updateCpoeBed(CpoeBed cpoeBed);

    /**
     * 批量删除床位记录
     * 
     * @param bedIds 需要删除的床位记录主键集合
     * @return 结果
     */
    public int deleteCpoeBedByBedIds(Long[] bedIds);

    /**
     * 删除床位记录信息
     * 
     * @param bedId 床位记录主键
     * @return 结果
     */
    public int deleteCpoeBedByBedId(Long bedId);
}
