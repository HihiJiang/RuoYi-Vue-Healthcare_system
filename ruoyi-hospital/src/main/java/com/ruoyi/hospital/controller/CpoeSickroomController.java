package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.DTO.CpoeSickroomDTO;
import com.ruoyi.hospital.Vo.CpoeSickroomVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.hospital.domain.CpoeSickroom;
import com.ruoyi.hospital.service.ICpoeSickroomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病房记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/hospital/sickroom")
public class CpoeSickroomController extends BaseController
{
    @Autowired
    private ICpoeSickroomService cpoeSickroomService;

    /**
     * 查询病房记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:list')")
    @GetMapping("/list")
    public TableDataInfo list(CpoeSickroomDTO cpoeSickroom)
    {
        startPage();
        List<CpoeSickroomVo> list = cpoeSickroomService.selectCpoeSickroomList(cpoeSickroom);
        return getDataTable(list);
    }

    /**
     * 导出病房记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:export')")
    @Log(title = "病房记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CpoeSickroomDTO cpoeSickroom)
    {
        List<CpoeSickroomVo> list = cpoeSickroomService.selectCpoeSickroomList(cpoeSickroom);
        ExcelUtil<CpoeSickroomVo> util = new ExcelUtil<CpoeSickroomVo>(CpoeSickroomVo.class);
        util.exportExcel(response, list, "病房记录数据");
    }

    /**
     * 获取病房记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(cpoeSickroomService.selectCpoeSickroomByRoomId(roomId));
    }

    /**
     * 新增病房记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:add')")
    @Log(title = "病房记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CpoeSickroom cpoeSickroom)
    {
        return toAjax(cpoeSickroomService.insertCpoeSickroom(cpoeSickroom));
    }

    /**
     * 修改病房记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:edit')")
    @Log(title = "病房记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CpoeSickroom cpoeSickroom)
    {
        return toAjax(cpoeSickroomService.updateCpoeSickroom(cpoeSickroom));
    }

    /**
     * 删除病房记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:sickroom:remove')")
    @Log(title = "病房记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(cpoeSickroomService.deleteCpoeSickroomByRoomIds(roomIds));
    }
}
