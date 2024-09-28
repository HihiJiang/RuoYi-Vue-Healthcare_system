package com.ruoyi.medical.service.impl;

import java.util.List;

import com.ruoyi.medical.dto.PacsInfoDTO;
import com.ruoyi.medical.vo.PacsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.PacsInfoMapper;
import com.ruoyi.medical.domain.PacsInfo;
import com.ruoyi.medical.service.IPacsInfoService;

/**
 * pacs影像信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class PacsInfoServiceImpl implements IPacsInfoService 
{
    @Autowired
    private PacsInfoMapper pacsInfoMapper;

    /**
     * 查询pacs影像信息
     * 
     * @param pacsId pacs影像信息主键
     * @return pacs影像信息
     */
    @Override
    public PacsInfo selectPacsInfoByPacsId(Long pacsId)
    {
        return pacsInfoMapper.selectPacsInfoByPacsId(pacsId);
    }

    /**
     * 查询pacs影像信息列表
     * 
     * @param pacsInfo pacs影像信息
     * @return pacs影像信息
     */
    @Override
    public List<PacsInfoVo> selectPacsInfoList(PacsInfoDTO pacsInfo)
    {
        return pacsInfoMapper.selectPacsInfoList(pacsInfo);
    }

    /**
     * 新增pacs影像信息
     * 
     * @param pacsInfo pacs影像信息
     * @return 结果
     */
    @Override
    public int insertPacsInfo(PacsInfo pacsInfo)
    {
        return pacsInfoMapper.insertPacsInfo(pacsInfo);
    }

    /**
     * 修改pacs影像信息
     * 
     * @param pacsInfo pacs影像信息
     * @return 结果
     */
    @Override
    public int updatePacsInfo(PacsInfo pacsInfo)
    {
        return pacsInfoMapper.updatePacsInfo(pacsInfo);
    }

    /**
     * 批量删除pacs影像信息
     * 
     * @param pacsIds 需要删除的pacs影像信息主键
     * @return 结果
     */
    @Override
    public int deletePacsInfoByPacsIds(Long[] pacsIds)
    {
        return pacsInfoMapper.deletePacsInfoByPacsIds(pacsIds);
    }

    /**
     * 删除pacs影像信息信息
     * 
     * @param pacsId pacs影像信息主键
     * @return 结果
     */
    @Override
    public int deletePacsInfoByPacsId(Long pacsId)
    {
        return pacsInfoMapper.deletePacsInfoByPacsId(pacsId);
    }
}
