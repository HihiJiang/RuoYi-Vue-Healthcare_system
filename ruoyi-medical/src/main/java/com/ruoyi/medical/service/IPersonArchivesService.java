package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.PersonArchives;

/**
 * 患者档案信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
public interface IPersonArchivesService 
{
    /**
     * 查询患者档案信息
     * 
     * @param patientId 患者档案信息主键
     * @return 患者档案信息
     */
    public PersonArchives selectPersonArchivesByPatientId(String patientId);

    /**
     * 查询患者档案信息列表
     * 
     * @param personArchives 患者档案信息
     * @return 患者档案信息集合
     */
    public List<PersonArchives> selectPersonArchivesList(PersonArchives personArchives);

    /**
     * 新增患者档案信息
     * 
     * @param personArchives 患者档案信息
     * @return 结果
     */
    public int insertPersonArchives(PersonArchives personArchives);

    /**
     * 修改患者档案信息
     * 
     * @param personArchives 患者档案信息
     * @return 结果
     */
    public int updatePersonArchives(PersonArchives personArchives);

    /**
     * 批量删除患者档案信息
     * 
     * @param patientIds 需要删除的患者档案信息主键集合
     * @return 结果
     */
    public int deletePersonArchivesByPatientIds(String[] patientIds);

    /**
     * 删除患者档案信息信息
     * 
     * @param patientId 患者档案信息主键
     * @return 结果
     */
    public int deletePersonArchivesByPatientId(String patientId);
}
