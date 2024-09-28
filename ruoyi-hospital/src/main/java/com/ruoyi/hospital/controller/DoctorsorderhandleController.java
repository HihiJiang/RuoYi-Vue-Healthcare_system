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
import com.ruoyi.hospital.domain.Doctorsorderhandle;
import com.ruoyi.hospital.service.IDoctorsorderhandleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医嘱执行记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/hospital/doctorsorderhandle")
public class DoctorsorderhandleController extends BaseController
{
    @Autowired
    private IDoctorsorderhandleService doctorsorderhandleService;

    /**
     * 查询医嘱执行记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:list')")
    @GetMapping("/list")
    public TableDataInfo list(Doctorsorderhandle doctorsorderhandle)
    {
        startPage();
        List<Doctorsorderhandle> list = doctorsorderhandleService.selectDoctorsorderhandleList(doctorsorderhandle);
        return getDataTable(list);
    }

    /**
     * 导出医嘱执行记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:export')")
    @Log(title = "医嘱执行记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Doctorsorderhandle doctorsorderhandle)
    {
        List<Doctorsorderhandle> list = doctorsorderhandleService.selectDoctorsorderhandleList(doctorsorderhandle);
        ExcelUtil<Doctorsorderhandle> util = new ExcelUtil<Doctorsorderhandle>(Doctorsorderhandle.class);
        util.exportExcel(response, list, "医嘱执行记录数据");
    }

    /**
     * 获取医嘱执行记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:query')")
    @GetMapping(value = "/{ordershId}")
    public AjaxResult getInfo(@PathVariable("ordershId") Long ordershId)
    {
        return success(doctorsorderhandleService.selectDoctorsorderhandleByOrdershId(ordershId));
    }

    /**
     * 新增医嘱执行记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:add')")
    @Log(title = "医嘱执行记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Doctorsorderhandle doctorsorderhandle)
    {
        return toAjax(doctorsorderhandleService.insertDoctorsorderhandle(doctorsorderhandle));
    }

    /**
     * 修改医嘱执行记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:edit')")
    @Log(title = "医嘱执行记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Doctorsorderhandle doctorsorderhandle)
    {
        return toAjax(doctorsorderhandleService.updateDoctorsorderhandle(doctorsorderhandle));
    }

    /**
     * 删除医嘱执行记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorderhandle:remove')")
    @Log(title = "医嘱执行记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ordershIds}")
    public AjaxResult remove(@PathVariable Long[] ordershIds)
    {
        return toAjax(doctorsorderhandleService.deleteDoctorsorderhandleByOrdershIds(ordershIds));
    }
}
