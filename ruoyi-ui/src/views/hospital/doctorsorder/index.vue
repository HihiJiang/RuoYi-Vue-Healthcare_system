<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医嘱编号" prop="ordersId">
        <el-input
          v-model="queryParams.ordersId"
          placeholder="请输入医嘱编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医生姓名" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入医生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="医嘱类别" prop="ordersStatus">
        <el-select v-model="queryParams.ordersStatus" placeholder="请选择医嘱类别" clearable>
          <el-option
            v-for="dict in dict.type.ordersh_result_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item label="执行状态" prop="ordersType">
        <el-select v-model="queryParams.ordersType" placeholder="请选择执行状" clearable>
          <el-option
            v-for="dict in dict.type.orders_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="医嘱" prop="ordersDesc">
        <el-input
          v-model="queryParams.ordersDesc"
          placeholder="请输入医嘱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="创建时间" prop="ordersCreatedate">
        <el-date-picker clearable
          v-model="queryParams.ordersCreatedate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
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
          v-hasPermi="['hospital:doctorsorder:add']"
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
          v-hasPermi="['hospital:doctorsorder:edit']"
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
          v-hasPermi="['hospital:doctorsorder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:doctorsorder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="doctorsorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="医嘱编号" align="center" prop="ordersId" />
      <!-- <el-table-column label="患者ID" align="center" prop="patientId" /> -->
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <!-- <el-table-column label="医生ID" align="center" prop="doctorId" /> -->
      <el-table-column label="医生姓名" align="center" prop="nickName" />
      <!-- <el-table-column label="医嘱类别" align="center" prop="ordersStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ordersh_result_type" :value="scope.row.ordersStatus"/>
        </template>
      </el-table-column> -->
      <el-table-column label="执行状态" align="center" prop="ordersType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.orders_type" :value="scope.row.ordersType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="医嘱" align="center" prop="ordersDesc" /> -->
      <el-table-column label="创建时间" align="center" prop="ordersCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ordersCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:doctorsorder:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:doctorsorder:remove']"
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

    <!-- 添加或修改医嘱记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="患者ID" prop="patientId">
          <el-input v-model="form.patientId" placeholder="请输入患者ID" />
        </el-form-item>
        <el-form-item label="医生ID" prop="doctorId">
          <el-input v-model="form.doctorId" placeholder="请输入医生ID" />
        </el-form-item>
        <el-form-item label="医嘱类别" prop="ordersStatus">
          <el-select v-model="form.ordersStatus" placeholder="请选择医嘱类别">
            <el-option
              v-for="dict in dict.type.ordersh_result_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="执行状态" prop="ordersType">
          <el-select v-model="form.ordersType" placeholder="请选择执行状态">
            <el-option
              v-for="dict in dict.type.orders_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="医嘱" prop="ordersDesc">
          <el-input v-model="form.ordersDesc" placeholder="请输入医嘱" />
        </el-form-item>
        <el-form-item label="创建时间" prop="ordersCreatedate">
          <el-date-picker clearable
            v-model="form.ordersCreatedate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
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
import { listDoctorsorder, getDoctorsorder, delDoctorsorder, addDoctorsorder, updateDoctorsorder } from "@/api/hospital/doctorsorder";

export default {
  name: "Doctorsorder",
  dicts: ['ordersh_result_type', 'orders_type'],
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
      // 医嘱记录表格数据
      doctorsorderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ordersId: null,
        patientId: null,
        doctorId: null,
        ordersStatus: null,
        ordersType: null,
        ordersDesc: null,
        ordersCreatedate: null,
        nickName: null,
        patientName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ordersCreatedate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询医嘱记录列表 */
    getList() {
      this.loading = true;
      listDoctorsorder(this.queryParams).then(response => {
        this.doctorsorderList = response.rows;
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
        ordersId: null,
        patientId: null,
        doctorId: null,
        ordersStatus: null,
        ordersType: null,
        ordersDesc: null,
        ordersCreatedate: null,
        nickName: null,
        patientName: null,
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
      this.ids = selection.map(item => item.ordersId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医嘱记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ordersId = row.ordersId || this.ids
      getDoctorsorder(ordersId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医嘱记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ordersId != null) {
            updateDoctorsorder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDoctorsorder(this.form).then(response => {
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
      const ordersIds = row.ordersId || this.ids;
      this.$modal.confirm('是否确认删除医嘱记录编号为"' + ordersIds + '"的数据项？').then(function() {
        return delDoctorsorder(ordersIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/doctorsorder/export', {
        ...this.queryParams
      }, `doctorsorder_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
