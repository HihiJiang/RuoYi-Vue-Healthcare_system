package com.ruoyi.medical.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.vo.DoctorReceiverecordVo;
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
import com.ruoyi.medical.domain.DoctorReceiverecord;
import com.ruoyi.medical.service.IDoctorReceiverecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接诊记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/medical/receiverecord")
public class DoctorReceiverecordController extends BaseController
{
    @Autowired
    private IDoctorReceiverecordService doctorReceiverecordService;

    /**
     * 查询接诊记录列表
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoctorReceiverecordDTO doctorReceiverecord)
    {
        startPage();
        List<DoctorReceiverecordVo> list = doctorReceiverecordService.selectDoctorReceiverecordList(doctorReceiverecord);
        return getDataTable(list);
    }

    /**
     * 导出接诊记录列表
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:export')")
    @Log(title = "接诊记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorReceiverecordDTO doctorReceiverecord)
    {
        List<DoctorReceiverecordVo> list = doctorReceiverecordService.selectDoctorReceiverecordList(doctorReceiverecord);
        ExcelUtil<DoctorReceiverecordVo> util = new ExcelUtil<DoctorReceiverecordVo>(DoctorReceiverecordVo.class);
        util.exportExcel(response, list, "接诊记录数据");
    }

    /**
     * 获取接诊记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:query')")
    @GetMapping(value = "/{recordsId}")
    public AjaxResult getInfo(@PathVariable("recordsId") String recordsId)
    {
        return success(doctorReceiverecordService.selectDoctorReceiverecordByRecordsId(recordsId));
    }

    /**
     * 新增接诊记录
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:add')")
    @Log(title = "接诊记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoctorReceiverecord doctorReceiverecord)
    {
        return toAjax(doctorReceiverecordService.insertDoctorReceiverecord(doctorReceiverecord));
    }

    /**
     * 修改接诊记录
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:edit')")
    @Log(title = "接诊记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoctorReceiverecord doctorReceiverecord)
    {
        return toAjax(doctorReceiverecordService.updateDoctorReceiverecord(doctorReceiverecord));
    }

    /**
     * 删除接诊记录
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:remove')")
    @Log(title = "接诊记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordsIds}")
    public AjaxResult remove(@PathVariable String[] recordsIds)
    {
        return toAjax(doctorReceiverecordService.deleteDoctorReceiverecordByRecordsIds(recordsIds));
    }


    /**
     * 添加检验、检查记录
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:add')")
    @Log(title = "接诊记录", businessType = BusinessType.INSERT)
    @PostMapping("/addLisAndPacs")
    public AjaxResult addLisAndPacs(@RequestBody DoctorReceiverecordDTO doctorReceiverecord)
    {
      return doctorReceiverecordService.addLisAndPacs(doctorReceiverecord);
    }

    /**
     * 添加处置药方记录
     */
    @PreAuthorize("@ss.hasPermi('medical:receiverecord:add')")
    @Log(title = "接诊记录", businessType = BusinessType.INSERT)
    @PostMapping("/addDrugs")
    public AjaxResult addDrugs(@RequestBody DoctorReceiverecordDTO doctorReceiverecord)
    {
        return doctorReceiverecordService.addDrugs(doctorReceiverecord);
    }
}
