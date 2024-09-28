package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.PacsInfo;
import com.ruoyi.medical.dto.PacsInfoDTO;
import com.ruoyi.medical.vo.PacsInfoVo;

/**
 * pacs影像信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface IPacsInfoService 
{
    /**
     * 查询pacs影像信息
     * 
     * @param pacsId pacs影像信息主键
     * @return pacs影像信息
     */
    public PacsInfo selectPacsInfoByPacsId(Long pacsId);

    /**
     * 查询pacs影像信息列表
     * 
     * @param pacsInfo pacs影像信息
     * @return pacs影像信息集合
     */
    public List<PacsInfoVo> selectPacsInfoList(PacsInfoDTO pacsInfo);

    /**
     * 新增pacs影像信息
     * 
     * @param pacsInfo pacs影像信息
     * @return 结果
     */
    public int insertPacsInfo(PacsInfo pacsInfo);

    /**
     * 修改pacs影像信息
     * 
     * @param pacsInfo pacs影像信息
     * @return 结果
     */
    public int updatePacsInfo(PacsInfo pacsInfo);

    /**
     * 批量删除pacs影像信息
     * 
     * @param pacsIds 需要删除的pacs影像信息主键集合
     * @return 结果
     */
    public int deletePacsInfoByPacsIds(Long[] pacsIds);

    /**
     * 删除pacs影像信息信息
     * 
     * @param pacsId pacs影像信息主键
     * @return 结果
     */
    public int deletePacsInfoByPacsId(Long pacsId);
}
