package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.DTO.PharmacyDTO;
import com.ruoyi.hospital.Vo.PharmacyVo;
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
import com.ruoyi.hospital.domain.Pharmacy;
import com.ruoyi.hospital.service.IPharmacyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药房库存Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/hospital/pharmacy")
public class PharmacyController extends BaseController
{
    @Autowired
    private IPharmacyService pharmacyService;

    /**
     * 查询药房库存列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:list')")
    @GetMapping("/list")
    public TableDataInfo list(PharmacyDTO pharmacy)
    {
        startPage();
        List<PharmacyVo> list = pharmacyService.selectPharmacyList(pharmacy);
        return getDataTable(list);
    }

    /**
     * 导出药房库存列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:export')")
    @Log(title = "药房库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PharmacyDTO pharmacy)
    {
        List<PharmacyVo> list = pharmacyService.selectPharmacyList(pharmacy);
        ExcelUtil<PharmacyVo> util = new ExcelUtil<PharmacyVo>(PharmacyVo.class);
        util.exportExcel(response, list, "药房库存数据");
    }

    /**
     * 获取药房库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:query')")
    @GetMapping(value = "/{pharmacyId}")
    public AjaxResult getInfo(@PathVariable("pharmacyId") Long pharmacyId)
    {
        return success(pharmacyService.selectPharmacyByPharmacyId(pharmacyId));
    }

    /**
     * 新增药房库存
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:add')")
    @Log(title = "药房库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pharmacy pharmacy)
    {
        return toAjax(pharmacyService.insertPharmacy(pharmacy));
    }

    /**
     * 修改药房库存
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:edit')")
    @Log(title = "药房库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pharmacy pharmacy)
    {
        return toAjax(pharmacyService.updatePharmacy(pharmacy));
    }

    /**
     * 删除药房库存
     */
    @PreAuthorize("@ss.hasPermi('hospital:pharmacy:remove')")
    @Log(title = "药房库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pharmacyIds}")
    public AjaxResult remove(@PathVariable Long[] pharmacyIds)
    {
        return toAjax(pharmacyService.deletePharmacyByPharmacyIds(pharmacyIds));
    }
}
