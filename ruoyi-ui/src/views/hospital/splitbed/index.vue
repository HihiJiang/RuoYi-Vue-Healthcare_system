<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item  label="接诊记录ID" prop="recordsId">
        <el-input
          v-model="queryParams.recordsId"
          placeholder="请输入接诊记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="患者姓名" prop="phName">
        <el-input
          v-model="queryParams.phName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医生姓名" prop="doctorName">
        <el-input
          v-model="queryParams.doctorName"
          placeholder="请输入医生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间名称" prop="rmName">
        <el-input
          v-model="queryParams.rmName"
          placeholder="请输入房间名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="床位名称" prop="bedName">
        <el-input
          v-model="queryParams.bedName"
          placeholder="请输入床位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属科室" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择所属科室" clearable>
          <el-option
            v-for="dict in dict.type.room_dept"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分床状态" prop="splitbedStatus">
        <el-select v-model="queryParams.splitbedStatus" placeholder="请选择分床状态" clearable>
          <el-option
            v-for="dict in dict.type.splitbed_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="创建时间" prop="splitbedCreatedate">
        <el-date-picker clearable
          v-model="queryParams.splitbedCreatedate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="入院时间" prop="splitbedIndate">
        <el-date-picker clearable
          v-model="queryParams.splitbedIndate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入院时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="出院时间" prop="splitbedOutdate">
        <el-date-picker clearable
          v-model="queryParams.splitbedOutdate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出院时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospital:splitbed:add']"
        >新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hospital:splitbed:edit']"
        >修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:splitbed:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:splitbed:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="splitbedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="分床ID" align="center" prop="splitbedId" /> -->
      <el-table-column label="接诊记录" align="center" prop="recordsId" />
      <!-- <el-table-column label="患者ID" align="center" prop="patientId" /> -->
      <el-table-column label="患者身份证" align="center" prop="patientIdcard" />
      <el-table-column label="患者姓名" align="center" prop="phName" >
        <template slot-scope="scope">
          <router-link :to="'/medical/archivesname/index/' + scope.row.patientIdcard" class="link-type">
            <span>{{ scope.row.phName }}</span>
          </router-link>
        </template>
      </el-table-column>
      <!-- <el-table-column label="医生ID" align="center" prop="doctorId" /> -->
      <el-table-column label="医生姓名" align="center" prop="doctorName">
        <template slot-scope="scope">
          <label v-if="scope.row.doctorName != null">{{ scope.row.doctorName }}</label>
          <router-link v-else :to="'/hospital/userfp/index/'+scope.row.deptId+'/'+scope.row.patientIdcard+'/'+scope.row.splitbedId">
          <el-button
          type="success"
          plain
          size="mini"
        >选择医生</el-button>
        </router-link>
        </template>
      </el-table-column>
      <!-- <el-table-column label="床位ID" align="center" prop="bedId" /> -->
      <!-- <el-table-column label="所属科室" align="center" v-if="false" prop="deptId"/> -->
      <el-table-column label="所属科室" align="center" prop="deptId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.room_dept" :value="scope.row.deptId"/>
        </template>
      </el-table-column>
      <el-table-column label="房间名称" align="center" prop="rmName" />
      <el-table-column label="床位名称" align="center" prop="bedName" />
      <el-table-column label="分床状态" align="center" prop="splitbedStatus">
        <template slot-scope="scope">
          <dict-tag v-if="scope.row.splitbedStatus == '1'" :options="dict.type.splitbed_status" :value="scope.row.splitbedStatus"/>
          <router-link v-else :to="'/hospital/beddept/index/'+scope.row.deptId+'/0/'+scope.row.patientIdcard+'/'+scope.row.patientId">
          <el-button
          type="success"
          plain
          size="mini"
        >分床</el-button>
        </router-link>
        </template>
      </el-table-column>
      <!-- <el-table-column label="创建时间" align="center" prop="splitbedCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.splitbedCreatedate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column v-if="false" label="登记时间" align="center" prop="splitbedIndate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.splitbedIndate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="出院时间" align="center" prop="splitbedOutdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.splitbedOutdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="备注" align="center" prop="splitbedRemark" /> -->
      <el-table-column v-if="false" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:splitbed:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:splitbed:remove']"
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

    <!-- 添加或修改住院登记对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="接诊记录ID" prop="recordsId">
          <el-input v-model="form.recordsId" placeholder="请输入接诊记录ID" />
        </el-form-item>
        <el-form-item label="患者ID" prop="patientId">
          <el-input v-model="form.patientId" placeholder="请输入患者ID" />
        </el-form-item>
        <el-form-item label="医生姓名" prop="doctorName">
          <el-input v-model="form.doctorId" placeholder="请输入医生姓名" />
        </el-form-item>
        <el-form-item label="床位ID" prop="bedId">
          <el-input v-model="form.bedId" placeholder="请输入床位ID" />
        </el-form-item>
        <el-form-item label="所属科室" prop="deptId">
          <el-select v-model="form.deptId" placeholder="请选择所属科室">
            <el-option
              v-for="dict in dict.type.room_dept"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分床状态" prop="splitbedStatus">
          <el-select v-model="form.splitbedStatus" placeholder="请选择分床状态">
            <el-option
              v-for="dict in dict.type.splitbed_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="splitbedCreatedate">
          <el-date-picker clearable
            v-model="form.splitbedCreatedate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="入院时间" prop="splitbedIndate">
          <el-date-picker clearable
            v-model="form.splitbedIndate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入院时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="出院时间" prop="splitbedOutdate">
          <el-date-picker clearable
            v-model="form.splitbedOutdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出院时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="splitbedRemark">
          <el-input v-model="form.splitbedRemark" type="textarea" placeholder="请输入内容" />
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
import { listSplitbed, getSplitbed, delSplitbed, addSplitbed, updateSplitbed } from "@/api/hospital/splitbed";

export default {
  name: "Splitbed",
  dicts: ['splitbed_status', 'room_dept'],
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
      // 住院登记表格数据
      splitbedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordsId: null,
        phName: null,
        patientId: null,
        patientIdcard: null,
        doctorId: null,
        doctorName: null,
        bedId: null,
        deptId: null,
        rmName: null,
        bedName: null,
        splitbedStatus: null,
        splitbedCreatedate: null,
        splitbedIndate: null,
        splitbedOutdate: null,
        splitbedRemark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        splitbedCreatedate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  
  inject:["reload"],
  methods: {
    /** 查询住院登记列表 */
    getList() {
      
      this.loading = true;
      listSplitbed(this.queryParams).then(response => {
        this.splitbedList = response.rows;
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
        splitbedId: null,
        recordsId: null,
        phName: null,
        patientId: null,
        patientIdcard: null,
        doctorId: null,
        doctorName: null,
        bedId: null,
        deptId: null,
        rmName: null,
        bedName: null,
        splitbedStatus: null,
        splitbedCreatedate: null,
        splitbedIndate: null,
        splitbedOutdate: null,
        splitbedRemark: null
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
      this.ids = selection.map(item => item.splitbedId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加住院登记";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const splitbedId = row.splitbedId || this.ids
      getSplitbed(splitbedId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改住院登记";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.splitbedId != null) {
            updateSplitbed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSplitbed(this.form).then(response => {
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
      const splitbedIds = row.splitbedId || this.ids;
      this.$modal.confirm('是否确认删除住院登记编号为"' + splitbedIds + '"的数据项？').then(function() {
        return delSplitbed(splitbedIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/splitbed/export', {
        ...this.queryParams
      }, `splitbed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
