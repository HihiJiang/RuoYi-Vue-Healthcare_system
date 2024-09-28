<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="药品名称" prop="drugsName">
        <el-input
          v-model="queryParams.drugsName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="拼音名" prop="drugsPinyin">
        <el-input
          v-model="queryParams.drugsPinyin"
          placeholder="请输入拼音名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="drugsSize">
        <el-input
          v-model="queryParams.drugsSize"
          placeholder="请输入规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品描述" prop="drugsDesc">
        <el-input
          v-model="queryParams.drugsDesc"
          placeholder="请输入药品描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产厂家" prop="drugsFactory">
        <el-input
          v-model="queryParams.drugsFactory"
          placeholder="请输入生产厂家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有效期" prop="drugsExpire">
        <el-date-picker clearable
          v-model="queryParams.drugsExpire"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择有效期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="药品状态，1在用2作废" prop="drugsStatus">
        <el-select v-model="queryParams.drugsStatus" placeholder="请选择药品状态，1在用2作废" clearable>
          <el-option
            v-for="dict in dict.type.drugs_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="进价" prop="drugsInpay">
        <el-input
          v-model="queryParams.drugsInpay"
          placeholder="请输入进价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售价" prop="drugsOutpay">
        <el-input
          v-model="queryParams.drugsOutpay"
          placeholder="请输入售价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="drugsCreatetime">
        <el-date-picker clearable
          v-model="queryParams.drugsCreatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作人ID" prop="operatorId">
        <el-input
          v-model="queryParams.operatorId"
          placeholder="请输入操作人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['medical:drugsinfo:add']"
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
          v-hasPermi="['medical:drugsinfo:edit']"
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
          v-hasPermi="['medical:drugsinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:drugsinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="drugsinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="药品ID" align="center" prop="drugsId" />
      <el-table-column label="药品名称" align="center" prop="drugsName" />
      <el-table-column label="拼音名" align="center" prop="drugsPinyin" />
      <el-table-column label="规格" align="center" prop="drugsSize" />
      <el-table-column label="药品描述" align="center" prop="drugsDesc" />
      <el-table-column label="生产厂家" align="center" prop="drugsFactory" />
      <el-table-column label="有效期" align="center" prop="drugsExpire" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.drugsExpire, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="药品状态，1在用2作废" align="center" prop="drugsStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.drugs_status" :value="scope.row.drugsStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="进价" align="center" prop="drugsInpay" />
      <el-table-column label="售价" align="center" prop="drugsOutpay" />
      <el-table-column label="创建时间" align="center" prop="drugsCreatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.drugsCreatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:drugsinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:drugsinfo:remove']"
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

    <!-- 添加或修改药品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="药品名称" prop="drugsName">
          <el-input v-model="form.drugsName" placeholder="请输入药品名称" />
        </el-form-item>
        <el-form-item label="拼音名" prop="drugsPinyin">
          <el-input v-model="form.drugsPinyin" placeholder="请输入拼音名" />
        </el-form-item>
        <el-form-item label="规格" prop="drugsSize">
          <el-input v-model="form.drugsSize" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="药品描述" prop="drugsDesc">
          <el-input v-model="form.drugsDesc" placeholder="请输入药品描述" />
        </el-form-item>
        <el-form-item label="生产厂家" prop="drugsFactory">
          <el-input v-model="form.drugsFactory" placeholder="请输入生产厂家" />
        </el-form-item>
        <el-form-item label="有效期" prop="drugsExpire">
          <el-date-picker clearable
            v-model="form.drugsExpire"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择有效期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="药品状态，1在用2作废" prop="drugsStatus">
          <el-select v-model="form.drugsStatus" placeholder="请选择药品状态，1在用2作废">
            <el-option
              v-for="dict in dict.type.drugs_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="进价" prop="drugsInpay">
          <el-input v-model="form.drugsInpay" placeholder="请输入进价" />
        </el-form-item>
        <el-form-item label="售价" prop="drugsOutpay">
          <el-input v-model="form.drugsOutpay" placeholder="请输入售价" />
        </el-form-item>
        <el-form-item label="创建时间" prop="drugsCreatetime">
          <el-date-picker clearable
            v-model="form.drugsCreatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
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
import { listDrugsinfo, getDrugsinfo, delDrugsinfo, addDrugsinfo, updateDrugsinfo } from "@/api/medical/drugsinfo";

export default {
  name: "Drugsinfo",
  dicts: ['drugs_status'],
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
      // 药品信息表格数据
      drugsinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        drugsName: null,
        drugsPinyin: null,
        drugsSize: null,
        drugsDesc: null,
        drugsFactory: null,
        drugsExpire: null,
        drugsStatus: null,
        drugsInpay: null,
        drugsOutpay: null,
        drugsCreatetime: null,
        operatorId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        drugsCreatetime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药品信息列表 */
    getList() {
      this.loading = true;
      listDrugsinfo(this.queryParams).then(response => {
        this.drugsinfoList = response.rows;
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
        drugsId: null,
        drugsName: null,
        drugsPinyin: null,
        drugsSize: null,
        drugsDesc: null,
        drugsFactory: null,
        drugsExpire: null,
        drugsStatus: null,
        drugsInpay: null,
        drugsOutpay: null,
        drugsCreatetime: null,
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
      this.ids = selection.map(item => item.drugsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加药品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const drugsId = row.drugsId || this.ids
      getDrugsinfo(drugsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.drugsId != null) {
            updateDrugsinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDrugsinfo(this.form).then(response => {
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
      const drugsIds = row.drugsId || this.ids;
      this.$modal.confirm('是否确认删除药品信息编号为"' + drugsIds + '"的数据项？').then(function() {
        return delDrugsinfo(drugsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/drugsinfo/export', {
        ...this.queryParams
      }, `drugsinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
