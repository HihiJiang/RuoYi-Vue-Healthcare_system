<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医生ID" prop="recordsDoctorid">
        <el-input
          v-model="queryParams.recordsDoctorid"
          placeholder="请输入医生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接诊时间" prop="recordsCreatedate">
        <el-date-picker clearable
          v-model="queryParams.recordsCreatedate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择接诊时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上午/下午" prop="recordsAmpm">
        <el-input
          v-model="queryParams.recordsAmpm"
          placeholder="请输入上午/下午"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科室" prop="recordsDeptid">
        <el-input
          v-model="queryParams.recordsDeptid"
          placeholder="请输入科室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="挂号顺序号" prop="recordsNo">
        <el-input
          v-model="queryParams.recordsNo"
          placeholder="请输入挂号顺序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门诊卡ID" prop="outpatientId">
        <el-input
          v-model="queryParams.outpatientId"
          placeholder="请输入门诊卡ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人ID" prop="operatorId">
        <el-input
          v-model="queryParams.operatorId"
          placeholder="请输入操作人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="录入时间" prop="enterdate">
        <el-date-picker clearable
          v-model="queryParams.enterdate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择录入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospital:receiverecord:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hospital:receiverecord:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:receiverecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:receiverecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiverecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="接诊记录ID" align="center" prop="recordsId" />
      <el-table-column label="医生ID" align="center" prop="recordsDoctorid" />
      <el-table-column label="诊断状态，1门诊2住院" align="center" prop="recordsDiagnosticStatus" />
      <el-table-column label="接诊状态，0未到1候诊2接诊3顺延4结束" align="center" prop="recordsStatus" />
      <el-table-column label="接诊时间" align="center" prop="recordsCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordsCreatedate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上午/下午" align="center" prop="recordsAmpm" />
      <el-table-column label="科室" align="center" prop="recordsDeptid" />
      <el-table-column label="挂号顺序号" align="center" prop="recordsNo" />
      <el-table-column label="门诊卡ID" align="center" prop="outpatientId" />
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="录入时间" align="center" prop="enterdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enterdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:receiverecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:receiverecord:remove']"
          >删除</el-button>
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

    <!-- 添加或修改接诊记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="医生ID" prop="recordsDoctorid">
          <el-input v-model="form.recordsDoctorid" placeholder="请输入医生ID" />
        </el-form-item>
        <el-form-item label="接诊时间" prop="recordsCreatedate">
          <el-date-picker clearable
            v-model="form.recordsCreatedate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择接诊时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上午/下午" prop="recordsAmpm">
          <el-input v-model="form.recordsAmpm" placeholder="请输入上午/下午" />
        </el-form-item>
        <el-form-item label="科室" prop="recordsDeptid">
          <el-input v-model="form.recordsDeptid" placeholder="请输入科室" />
        </el-form-item>
        <el-form-item label="挂号顺序号" prop="recordsNo">
          <el-input v-model="form.recordsNo" placeholder="请输入挂号顺序号" />
        </el-form-item>
        <el-form-item label="门诊卡ID" prop="outpatientId">
          <el-input v-model="form.outpatientId" placeholder="请输入门诊卡ID" />
        </el-form-item>
        <el-form-item label="操作人ID" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作人ID" />
        </el-form-item>
        <el-form-item label="录入时间" prop="enterdate">
          <el-date-picker clearable
            v-model="form.enterdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择录入时间">
          </el-date-picker>
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
import { listReceiverecord, getReceiverecord, delReceiverecord, addReceiverecord, updateReceiverecord } from "@/api/hospital/receiverecord";

export default {
  name: "Receiverecord",
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
      // 接诊记录表格数据
      receiverecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordsDoctorid: null,
        recordsDiagnosticStatus: null,
        recordsStatus: null,
        recordsCreatedate: null,
        recordsAmpm: null,
        recordsDeptid: null,
        recordsNo: null,
        outpatientId: null,
        operatorId: null,
        enterdate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询接诊记录列表 */
    getList() {
      this.loading = true;
      listReceiverecord(this.queryParams).then(response => {
        this.receiverecordList = response.rows;
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
        recordsId: null,
        recordsDoctorid: null,
        recordsDiagnosticStatus: null,
        recordsStatus: null,
        recordsCreatedate: null,
        recordsAmpm: null,
        recordsDeptid: null,
        recordsNo: null,
        outpatientId: null,
        operatorId: null,
        enterdate: null
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
      this.ids = selection.map(item => item.recordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加接诊记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordsId = row.recordsId || this.ids
      getReceiverecord(recordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改接诊记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordsId != null) {
            updateReceiverecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReceiverecord(this.form).then(response => {
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
      const recordsIds = row.recordsId || this.ids;
      this.$modal.confirm('是否确认删除接诊记录编号为"' + recordsIds + '"的数据项？').then(function() {
        return delReceiverecord(recordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/receiverecord/export', {
        ...this.queryParams
      }, `receiverecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
