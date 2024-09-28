package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.Doctorsorderhandle;

/**
 * 医嘱执行记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface DoctorsorderhandleMapper 
{
    /**
     * 查询医嘱执行记录
     * 
     * @param ordershId 医嘱执行记录主键
     * @return 医嘱执行记录
     */
    public Doctorsorderhandle selectDoctorsorderhandleByOrdershId(Long ordershId);

    /**
     * 查询医嘱执行记录列表
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 医嘱执行记录集合
     */
    public List<Doctorsorderhandle> selectDoctorsorderhandleList(Doctorsorderhandle doctorsorderhandle);

    /**
     * 新增医嘱执行记录
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 结果
     */
    public int insertDoctorsorderhandle(Doctorsorderhandle doctorsorderhandle);

    /**
     * 修改医嘱执行记录
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 结果
     */
    public int updateDoctorsorderhandle(Doctorsorderhandle doctorsorderhandle);

    /**
     * 删除医嘱执行记录
     * 
     * @param ordershId 医嘱执行记录主键
     * @return 结果
     */
    public int deleteDoctorsorderhandleByOrdershId(Long ordershId);

    /**
     * 批量删除医嘱执行记录
     * 
     * @param ordershIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorsorderhandleByOrdershIds(Long[] ordershIds);
}
