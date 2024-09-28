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
import com.ruoyi.hospital.domain.Prescriptiontotal;
import com.ruoyi.hospital.service.IPrescriptiontotalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住院治疗处方总Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/hospital/prescriptiontotal")
public class PrescriptiontotalController extends BaseController
{
    @Autowired
    private IPrescriptiontotalService prescriptiontotalService;

    /**
     * 查询住院治疗处方总列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:list')")
    @GetMapping("/list")
    public TableDataInfo list(Prescriptiontotal prescriptiontotal)
    {
        startPage();
        List<Prescriptiontotal> list = prescriptiontotalService.selectPrescriptiontotalList(prescriptiontotal);
        return getDataTable(list);
    }

    /**
     * 导出住院治疗处方总列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:export')")
    @Log(title = "住院治疗处方总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Prescriptiontotal prescriptiontotal)
    {
        List<Prescriptiontotal> list = prescriptiontotalService.selectPrescriptiontotalList(prescriptiontotal);
        ExcelUtil<Prescriptiontotal> util = new ExcelUtil<Prescriptiontotal>(Prescriptiontotal.class);
        util.exportExcel(response, list, "住院治疗处方总数据");
    }

    /**
     * 获取住院治疗处方总详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:query')")
    @GetMapping(value = "/{prestId}")
    public AjaxResult getInfo(@PathVariable("prestId") Long prestId)
    {
        return success(prescriptiontotalService.selectPrescriptiontotalByPrestId(prestId));
    }

    /**
     * 新增住院治疗处方总
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:add')")
    @Log(title = "住院治疗处方总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Prescriptiontotal prescriptiontotal)
    {
        return toAjax(prescriptiontotalService.insertPrescriptiontotal(prescriptiontotal));
    }

    /**
     * 修改住院治疗处方总
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:edit')")
    @Log(title = "住院治疗处方总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Prescriptiontotal prescriptiontotal)
    {
        return toAjax(prescriptiontotalService.updatePrescriptiontotal(prescriptiontotal));
    }

    /**
     * 删除住院治疗处方总
     */
    @PreAuthorize("@ss.hasPermi('hospital:prescriptiontotal:remove')")
    @Log(title = "住院治疗处方总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{prestIds}")
    public AjaxResult remove(@PathVariable Long[] prestIds)
    {
        return toAjax(prescriptiontotalService.deletePrescriptiontotalByPrestIds(prestIds));
    }
}
