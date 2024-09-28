package com.ruoyi.medical.service.impl;

import java.util.List;

import com.ruoyi.medical.dto.LisInfoDTO;
import com.ruoyi.medical.vo.LisInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.LisInfoMapper;
import com.ruoyi.medical.domain.LisInfo;
import com.ruoyi.medical.service.ILisInfoService;

/**
 * Lis检验信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class LisInfoServiceImpl implements ILisInfoService 
{
    @Autowired
    private LisInfoMapper lisInfoMapper;

    /**
     * 查询Lis检验信息
     * 
     * @param lisId Lis检验信息主键
     * @return Lis检验信息
     */
    @Override
    public LisInfo selectLisInfoByLisId(Long lisId)
    {
        return lisInfoMapper.selectLisInfoByLisId(lisId);
    }

    /**
     * 查询Lis检验信息列表
     * 
     * @param lisInfo Lis检验信息
     * @return Lis检验信息
     */
    @Override
    public List<LisInfoVo> selectLisInfoList(LisInfoDTO lisInfo)
    {
        return lisInfoMapper.selectLisInfoList(lisInfo);
    }

    /**
     * 新增Lis检验信息
     * 
     * @param lisInfo Lis检验信息
     * @return 结果
     */
    @Override
    public int insertLisInfo(LisInfo lisInfo)
    {
        return lisInfoMapper.insertLisInfo(lisInfo);
    }

    /**
     * 修改Lis检验信息
     * 
     * @param lisInfo Lis检验信息
     * @return 结果
     */
    @Override
    public int updateLisInfo(LisInfo lisInfo)
    {
        return lisInfoMapper.updateLisInfo(lisInfo);
    }

    /**
     * 批量删除Lis检验信息
     * 
     * @param lisIds 需要删除的Lis检验信息主键
     * @return 结果
     */
    @Override
    public int deleteLisInfoByLisIds(Long[] lisIds)
    {
        return lisInfoMapper.deleteLisInfoByLisIds(lisIds);
    }

    /**
     * 删除Lis检验信息信息
     * 
     * @param lisId Lis检验信息主键
     * @return 结果
     */
    @Override
    public int deleteLisInfoByLisId(Long lisId)
    {
        return lisInfoMapper.deleteLisInfoByLisId(lisId);
    }
}
