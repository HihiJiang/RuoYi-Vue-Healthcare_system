package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeSickroomDTO;
import com.ruoyi.hospital.Vo.CpoeSickroomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.CpoeSickroomMapper;
import com.ruoyi.hospital.domain.CpoeSickroom;
import com.ruoyi.hospital.service.ICpoeSickroomService;

/**
 * 病房记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
@Service
public class CpoeSickroomServiceImpl implements ICpoeSickroomService 
{
    @Autowired
    private CpoeSickroomMapper cpoeSickroomMapper;

    /**
     * 查询病房记录
     * 
     * @param roomId 病房记录主键
     * @return 病房记录
     */
    @Override
    public CpoeSickroom selectCpoeSickroomByRoomId(Long roomId)
    {
        return cpoeSickroomMapper.selectCpoeSickroomByRoomId(roomId);
    }

    /**
     * 查询病房记录列表
     * 
     * @param cpoeSickroom 病房记录
     * @return 病房记录
     */
    @Override
    public List<CpoeSickroomVo> selectCpoeSickroomList(CpoeSickroomDTO cpoeSickroom)
    {
        return cpoeSickroomMapper.selectCpoeSickroomList(cpoeSickroom);
    }

    /**
     * 新增病房记录
     * 
     * @param cpoeSickroom 病房记录
     * @return 结果
     */
    @Override
    public int insertCpoeSickroom(CpoeSickroom cpoeSickroom)
    {
        return cpoeSickroomMapper.insertCpoeSickroom(cpoeSickroom);
    }

    /**
     * 修改病房记录
     * 
     * @param cpoeSickroom 病房记录
     * @return 结果
     */
    @Override
    public int updateCpoeSickroom(CpoeSickroom cpoeSickroom)
    {
        return cpoeSickroomMapper.updateCpoeSickroom(cpoeSickroom);
    }

    /**
     * 批量删除病房记录
     * 
     * @param roomIds 需要删除的病房记录主键
     * @return 结果
     */
    @Override
    public int deleteCpoeSickroomByRoomIds(Long[] roomIds)
    {
        return cpoeSickroomMapper.deleteCpoeSickroomByRoomIds(roomIds);
    }

    /**
     * 删除病房记录信息
     * 
     * @param roomId 病房记录主键
     * @return 结果
     */
    @Override
    public int deleteCpoeSickroomByRoomId(Long roomId)
    {
        return cpoeSickroomMapper.deleteCpoeSickroomByRoomId(roomId);
    }
}
