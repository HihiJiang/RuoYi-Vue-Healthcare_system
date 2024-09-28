package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.LisInfo;
import com.ruoyi.medical.dto.LisInfoDTO;
import com.ruoyi.medical.vo.LisInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * Lis检验信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface LisInfoMapper 
{
    /**
     * 查询Lis检验信息
     * 
     * @param lisId Lis检验信息主键
     * @return Lis检验信息
     */
    public LisInfo selectLisInfoByLisId(Long lisId);

    /**
     * 查询Lis检验信息列表
     * 
     * @param lisInfo Lis检验信息
     * @return Lis检验信息集合
     */
    public List<LisInfoVo> selectLisInfoList(LisInfoDTO lisInfo);

    /**
     * 新增Lis检验信息
     * 
     * @param lisInfo Lis检验信息
     * @return 结果
     */
    public int insertLisInfo(LisInfo lisInfo);

    /**
     * 修改Lis检验信息
     * 
     * @param lisInfo Lis检验信息
     * @return 结果
     */
    public int updateLisInfo(LisInfo lisInfo);

    /**
     * 删除Lis检验信息
     * 
     * @param lisId Lis检验信息主键
     * @return 结果
     */
    public int deleteLisInfoByLisId(Long lisId);

    /**
     * 批量删除Lis检验信息
     * 
     * @param lisIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLisInfoByLisIds(Long[] lisIds);

    /**
     * 批量新增检验记录
     * @param lst
     * @return
     */
    public int addLisInfoBatch(@Param("lst") List<LisInfo> lst);
}
