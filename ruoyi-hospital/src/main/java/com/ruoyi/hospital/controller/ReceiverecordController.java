package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.hospital.domain.Receiverecord;
import com.ruoyi.hospital.service.IReceiverecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接诊记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/hospital/receiverecord")
public class ReceiverecordController extends BaseController
{
    @Autowired
    private IReceiverecordService receiverecordService;

    /**
     * 查询接诊记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(Receiverecord receiverecord)
    {
        startPage();
        List<Receiverecord> list = receiverecordService.selectReceiverecordList(receiverecord);
        return getDataTable(list);
    }

    /**
     * 导出接诊记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:export')")
    @Log(title = "接诊记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Receiverecord receiverecord)
    {
        List<Receiverecord> list = receiverecordService.selectReceiverecordList(receiverecord);
        ExcelUtil<Receiverecord> util = new ExcelUtil<Receiverecord>(Receiverecord.class);
        util.exportExcel(response, list, "接诊记录数据");
    }

    /**
     * 获取接诊记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:query')")
    @GetMapping(value = "/{recordsId}")
    public AjaxResult getInfo(@PathVariable("recordsId") String recordsId)
    {
        return success(receiverecordService.selectReceiverecordByRecordsId(recordsId));
    }

    /**
     * 新增接诊记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:add')")
    @Log(title = "接诊记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Receiverecord receiverecord)
    {
        return toAjax(receiverecordService.insertReceiverecord(receiverecord));
    }

    /**
     * 修改接诊记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:edit')")
    @Log(title = "接诊记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Receiverecord receiverecord)
    {
        return toAjax(receiverecordService.updateReceiverecord(receiverecord));
    }

    /**
     * 删除接诊记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:receiverecord:remove')")
    @Log(title = "接诊记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordsIds}")
    public AjaxResult remove(@PathVariable String[] recordsIds)
    {
        return toAjax(receiverecordService.deleteReceiverecordByRecordsIds(recordsIds));
    }
}
