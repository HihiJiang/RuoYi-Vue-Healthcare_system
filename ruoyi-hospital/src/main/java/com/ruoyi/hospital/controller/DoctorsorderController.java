package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.DTO.DoctorsorderDTO;
import com.ruoyi.hospital.Vo.DoctorsorderVo;
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
import com.ruoyi.hospital.domain.Doctorsorder;
import com.ruoyi.hospital.service.IDoctorsorderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医嘱记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/hospital/doctorsorder")
public class DoctorsorderController extends BaseController
{
    @Autowired
    private IDoctorsorderService doctorsorderService;

    /**
     * 查询医嘱记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoctorsorderDTO doctorsorder)
    {
        startPage();
        List<DoctorsorderVo> list = doctorsorderService.selectDoctorsorderList(doctorsorder);
        return getDataTable(list);
    }

    /**
     * 导出医嘱记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:export')")
    @Log(title = "医嘱记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorsorderDTO doctorsorder)
    {
        List<DoctorsorderVo> list = doctorsorderService.selectDoctorsorderList(doctorsorder);
        ExcelUtil<DoctorsorderVo> util = new ExcelUtil<DoctorsorderVo>(DoctorsorderVo.class);
        util.exportExcel(response, list, "医嘱记录数据");
    }

    /**
     * 获取医嘱记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:query')")
    @GetMapping(value = "/{ordersId}")
    public AjaxResult getInfo(@PathVariable("ordersId") Long ordersId)
    {
        return success(doctorsorderService.selectDoctorsorderByOrdersId(ordersId));
    }

    /**
     * 新增医嘱记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:add')")
    @Log(title = "医嘱记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Doctorsorder doctorsorder)
    {
        return toAjax(doctorsorderService.insertDoctorsorder(doctorsorder));
    }

    /**
     * 修改医嘱记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:edit')")
    @Log(title = "医嘱记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Doctorsorder doctorsorder)
    {
        return toAjax(doctorsorderService.updateDoctorsorder(doctorsorder));
    }

    /**
     * 删除医嘱记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:doctorsorder:remove')")
    @Log(title = "医嘱记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ordersIds}")
    public AjaxResult remove(@PathVariable Long[] ordersIds)
    {
        return toAjax(doctorsorderService.deleteDoctorsorderByOrdersIds(ordersIds));
    }
}
