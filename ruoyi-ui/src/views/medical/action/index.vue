<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="消费事项" prop="actionPartname">
        <el-input
          v-model="queryParams.actionPartname"
          placeholder="请输入消费事项"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    
      <el-form-item label="患者类型" prop="actionPatienttype">
        <el-select v-model="queryParams.actionPatienttype" placeholder="请选择患者类型" clearable>
          <el-option
            v-for="dict in dict.type.action_patienttype"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="单价" prop="actionPartmoney">
        <el-input
          v-model="queryParams.actionPartmoney"
          placeholder="请输入单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="诊断类型" prop="actionType">
        <el-select v-model="queryParams.actionType" placeholder="请选择诊断类型" clearable>
          <el-option
            v-for="dict in dict.type.action_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="接诊ID" prop="recordsId">
        <el-input
        readonly="readonly"
          v-model="queryParams.recordsId"
          placeholder="请输入接诊记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    

    <el-table v-loading="loading" :data="actionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" :index="indexMethod"/>
      <el-table-column label="消费事项" align="center" prop="actionPartname" />
      <el-table-column label="单价" align="center" prop="actionPartmoney" />
      <el-table-column label="消费数量" align="center" prop="actionPartnumber" />
      <el-table-column label="患者类型" align="center" prop="actionPatienttype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.action_patienttype" :value="scope.row.actionPatienttype"/>
        </template>
      </el-table-column>
      
      <el-table-column label="诊断类型" align="center" prop="actionType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.action_type" :value="scope.row.actionType"/>
        </template>
      </el-table-column>

    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改医生看病流程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="消费事项，如：检查CT、检验血常规" prop="actionPartname">
          <el-input v-model="form.actionPartname" placeholder="请输入消费事项，如：检查CT、检验血常规" />
        </el-form-item>
        <el-form-item label="消费数量" prop="actionPartnumber">
          <el-input v-model="form.actionPartnumber" placeholder="请输入消费数量" />
        </el-form-item>
        <el-form-item label="患者类型，1门诊患者2住院患者" prop="actionPatienttype">
          <el-select v-model="form.actionPatienttype" placeholder="请选择患者类型，1门诊患者2住院患者">
            <el-option
              v-for="dict in dict.type.action_patienttype"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单价" prop="actionPartmoney">
          <el-input v-model="form.actionPartmoney" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="类型，1检查2检验3处置" prop="actionType">
          <el-select v-model="form.actionType" placeholder="请选择类型，1检查2检验3处置">
            <el-option
              v-for="dict in dict.type.action_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="接诊记录ID" prop="recordsId">
          <el-input v-model="form.recordsId" placeholder="请输入接诊记录ID" />
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
import { listAction, getAction, delAction, addAction, updateAction } from "@/api/medical/action";

export default {
  name: "Action",
  dicts: ['action_type', 'action_patienttype'],
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
      // 医生看病流程表格数据
      actionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        actionPartname: null,
        actionPartnumber: null,
        actionPatienttype: null,
        actionPartmoney: null,
        actionType: null,
        recordsId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.queryParams.recordsId = this.$route.params && this.$route.params.recordsId;
    this.getList();
  },
  methods: {
    indexMethod(index) {
        return index+1;
      },
    /** 查询医生看病流程列表 */
    getList() {
      this.loading = true;
      listAction(this.queryParams).then(response => {
        this.actionList = response.rows;
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
        actionId: null,
        actionPartname: null,
        actionPartnumber: null,
        actionPatienttype: null,
        actionPartmoney: null,
        actionType: null,
        recordsId: null
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
      this.ids = selection.map(item => item.actionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医生看病流程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const actionId = row.actionId || this.ids
      getAction(actionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医生看病流程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.actionId != null) {
            updateAction(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAction(this.form).then(response => {
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
      const actionIds = row.actionId || this.ids;
      this.$modal.confirm('是否确认删除医生看病流程编号为"' + actionIds + '"的数据项？').then(function() {
        return delAction(actionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/action/export', {
        ...this.queryParams
      }, `action_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
