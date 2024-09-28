package com.ruoyi.medical.controller;

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
import com.ruoyi.medical.domain.DoctorResulthandle;
import com.ruoyi.medical.service.IDoctorResulthandleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医生看病治疗方案Controller
 * 
 * @author ruoyi
 * @date 2024-06-15
 */
@RestController
@RequestMapping("/medical/resulthandle")
public class DoctorResulthandleController extends BaseController
{
    @Autowired
    private IDoctorResulthandleService doctorResulthandleService;

    /**
     * 查询医生看病治疗方案列表
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoctorResulthandle doctorResulthandle)
    {
        startPage();
        List<DoctorResulthandle> list = doctorResulthandleService.selectDoctorResulthandleList(doctorResulthandle);
        return getDataTable(list);
    }

    /**
     * 导出医生看病治疗方案列表
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:export')")
    @Log(title = "医生看病治疗方案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorResulthandle doctorResulthandle)
    {
        List<DoctorResulthandle> list = doctorResulthandleService.selectDoctorResulthandleList(doctorResulthandle);
        ExcelUtil<DoctorResulthandle> util = new ExcelUtil<DoctorResulthandle>(DoctorResulthandle.class);
        util.exportExcel(response, list, "医生看病治疗方案数据");
    }

    /**
     * 获取医生看病治疗方案详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:query')")
    @GetMapping(value = "/{treatId}")
    public AjaxResult getInfo(@PathVariable("treatId") Long treatId)
    {
        return success(doctorResulthandleService.selectDoctorResulthandleByTreatId(treatId));
    }

    /**
     * 新增医生看病治疗方案
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:add')")
    @Log(title = "医生看病治疗方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoctorResulthandle doctorResulthandle)
    {
        return toAjax(doctorResulthandleService.insertDoctorResulthandle(doctorResulthandle));
    }

    /**
     * 修改医生看病治疗方案
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:edit')")
    @Log(title = "医生看病治疗方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoctorResulthandle doctorResulthandle)
    {
        return toAjax(doctorResulthandleService.updateDoctorResulthandle(doctorResulthandle));
    }

    /**
     * 删除医生看病治疗方案
     */
    @PreAuthorize("@ss.hasPermi('medical:resulthandle:remove')")
    @Log(title = "医生看病治疗方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{treatIds}")
    public AjaxResult remove(@PathVariable Long[] treatIds)
    {
        return toAjax(doctorResulthandleService.deleteDoctorResulthandleByTreatIds(treatIds));
    }
}
