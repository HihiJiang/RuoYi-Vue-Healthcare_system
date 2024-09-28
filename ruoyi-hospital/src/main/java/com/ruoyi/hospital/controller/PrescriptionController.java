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
import com.ruoyi.hospital.domain.Prescription;
import com.ruoyi.hospital.service.IPrescriptionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住院治疗处方Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/hospital/prescription")
public class PrescriptionController extends BaseController
{
    @Autowired
    private IPrescriptionService prescriptionService;

    /**
     * 查询住院治疗处方列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:list')")
    @GetMapping("/list")
    public TableDataInfo list(Prescription prescription)
    {
        startPage();
        List<Prescription> list = prescriptionService.selectPrescriptionList(prescription);
        return getDataTable(list);
    }

    /**
     * 导出住院治疗处方列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:export')")
    @Log(title = "住院治疗处方", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Prescription prescription)
    {
        List<Prescription> list = prescriptionService.selectPrescriptionList(prescription);
        ExcelUtil<Prescription> util = new ExcelUtil<Prescription>(Prescription.class);
        util.exportExcel(response, list, "住院治疗处方数据");
    }

    /**
     * 获取住院治疗处方详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:query')")
    @GetMapping(value = "/{presId}")
    public AjaxResult getInfo(@PathVariable("presId") Long presId)
    {
        return success(prescriptionService.selectPrescriptionByPresId(presId));
    }

    /**
     * 新增住院治疗处方
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:add')")
    @Log(title = "住院治疗处方", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Prescription prescription)
    {
        return toAjax(prescriptionService.insertPrescription(prescription));
    }

    /**
     * 修改住院治疗处方
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:edit')")
    @Log(title = "住院治疗处方", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Prescription prescription)
    {
        return toAjax(prescriptionService.updatePrescription(prescription));
    }

    /**
     * 删除住院治疗处方
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescription:remove')")
    @Log(title = "住院治疗处方", businessType = BusinessType.DELETE)
	@DeleteMapping("/{presIds}")
    public AjaxResult remove(@PathVariable Long[] presIds)
    {
        return toAjax(prescriptionService.deletePrescriptionByPresIds(presIds));
    }
}
