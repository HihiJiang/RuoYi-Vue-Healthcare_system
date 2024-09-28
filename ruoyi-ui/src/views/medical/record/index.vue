<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="门诊卡号" prop="outpatientId">
        <el-input readonly="readonly" 
        v-model="queryParams.outpatientId" />
      </el-form-item>
      <el-form-item label="充值取现" prop="rechargeType">
        <el-select v-model="queryParams.rechargeType" placeholder="请选择充值取现类型" clearable>
          <el-option v-for="dict in dict.type.recharge_type" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>



    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" :index="indexMethod"/>
      <!-- <el-table-column label="门诊卡充值记录ID" align="center" prop="rechargeId" /> -->
      <el-table-column label="金额" align="center" prop="rechargeAmount" />
      <el-table-column label="充值取现类型" align="center" prop="rechargeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.recharge_type" :value="scope.row.rechargeType" />
        </template>
      </el-table-column>
      <el-table-column label="充值取现日期" align="center" prop="rechargeCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rechargeCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:record:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['medical:record:remove']">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改门诊卡充值取现记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="充值取现金额" prop="rechargeAmount">
          <el-input v-model="form.rechargeAmount" placeholder="请输入充值取现金额" />
        </el-form-item>
        <el-form-item label="类型，1充值2取现" prop="rechargeType">
          <el-select v-model="form.rechargeType" placeholder="请选择类型，1充值2取现">
            <el-option v-for="dict in dict.type.recharge_type" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="充值取现日期" prop="rechargeCreatedate">
          <el-date-picker clearable v-model="form.rechargeCreatedate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择充值取现日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="门诊卡编号" prop="outpatientId">
          <el-input v-model="form.outpatientId" placeholder="请输入门诊卡编号" />
        </el-form-item>
        <el-form-item label="操作人ID" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作人ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/medical/record";

export default {
  name: "Record",
  dicts: ['recharge_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 门诊卡充值取现记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        rechargeAmount: null,
        rechargeType: null,
        rechargeCreatedate: null,
        outpatientId: null,
        operatorId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        rechargeCreatedate: [
          { required: true, message: "充值取现日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.queryParams.outpatientId = this.$route.params && this.$route.params.outpatientId;
    this.getList();
  },
  methods: {
    indexMethod(index) {
        return index+1;
      },
    /** 查询门诊卡充值取现记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        rechargeId: null,
        rechargeAmount: null,
        rechargeType: null,
        rechargeCreatedate: null,
        outpatientId: null,
        operatorId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.rechargeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门诊卡充值取现记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rechargeId = row.rechargeId || this.ids
      getRecord(rechargeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门诊卡充值取现记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.rechargeId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const rechargeIds = row.rechargeId || this.ids;
      this.$modal.confirm('是否确认删除门诊卡充值取现记录编号为"' + rechargeIds + '"的数据项？').then(function () {
        return delRecord(rechargeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
