package com.ruoyi.hospital.mapper;

import java.util.List;

import com.ruoyi.hospital.DTO.CpoeSickroomDTO;
import com.ruoyi.hospital.Vo.CpoeSickroomVo;
import com.ruoyi.hospital.domain.CpoeSickroom;

/**
 * 病房记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
public interface CpoeSickroomMapper 
{
    /**
     * 查询病房记录
     * 
     * @param roomId 病房记录主键
     * @return 病房记录
     */
    public CpoeSickroom selectCpoeSickroomByRoomId(Long roomId);

    /**
     * 查询病房记录列表
     * 
     * @param cpoeSickroom 病房记录
     * @return 病房记录集合
     */
    public List<CpoeSickroomVo>selectCpoeSickroomList(CpoeSickroomDTO cpoeSickroom);

    /**
     * 新增病房记录
     * 
     * @param cpoeSickroom 病房记录
     * @return 结果
     */
    public int insertCpoeSickroom(CpoeSickroom cpoeSickroom);

    /**
     * 修改病房记录
     * 
     * @param cpoeSickroom 病房记录
     * @return 结果
     */
    public int updateCpoeSickroom(CpoeSickroom cpoeSickroom);

    /**
     * 删除病房记录
     * 
     * @param roomId 病房记录主键
     * @return 结果
     */
    public int deleteCpoeSickroomByRoomId(Long roomId);

    /**
     * 批量删除病房记录
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpoeSickroomByRoomIds(Long[] roomIds);
}
