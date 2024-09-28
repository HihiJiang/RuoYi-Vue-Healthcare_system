package com.ruoyi.medical.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.PersonArchivesMapper;
import com.ruoyi.medical.domain.PersonArchives;
import com.ruoyi.medical.service.IPersonArchivesService;

/**
 * 患者档案信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
@Service
public class PersonArchivesServiceImpl implements IPersonArchivesService 
{
    @Autowired
    private PersonArchivesMapper personArchivesMapper;

    /**
     * 查询患者档案信息
     * 
     * @param patientId 患者档案信息主键
     * @return 患者档案信息
     */
    @Override
    public PersonArchives selectPersonArchivesByPatientId(String patientId)
    {
        return personArchivesMapper.selectPersonArchivesByPatientId(patientId);
    }

    /**
     * 查询患者档案信息列表
     * 
     * @param personArchives 患者档案信息
     * @return 患者档案信息
     */
    @Override
    public List<PersonArchives> selectPersonArchivesList(PersonArchives personArchives)
    {
        return personArchivesMapper.selectPersonArchivesList(personArchives);
    }

    /**
     * 新增患者档案信息
     * 
     * @param personArchives 患者档案信息
     * @return 结果
     */
    @Override
    public int insertPersonArchives(PersonArchives personArchives)
    {

        String id = "PH"+(new Random().nextInt(90000000)+10000000)+""+(new SimpleDateFormat("mmss").format(new Date()));
        personArchives.setPatientId(id);
        return personArchivesMapper.insertPersonArchives(personArchives);
    }

    /**
     * 修改患者档案信息
     * 
     * @param personArchives 患者档案信息
     * @return 结果
     */
    @Override
    public int updatePersonArchives(PersonArchives personArchives)
    {
        return personArchivesMapper.updatePersonArchives(personArchives);
    }

    /**
     * 批量删除患者档案信息
     * 
     * @param patientIds 需要删除的患者档案信息主键
     * @return 结果
     */
    @Override
    public int deletePersonArchivesByPatientIds(String[] patientIds)
    {
        return personArchivesMapper.deletePersonArchivesByPatientIds(patientIds);
    }

    /**
     * 删除患者档案信息信息
     * 
     * @param patientId 患者档案信息主键
     * @return 结果
     */
    @Override
    public int deletePersonArchivesByPatientId(String patientId)
    {
        return personArchivesMapper.deletePersonArchivesByPatientId(patientId);
    }
}
