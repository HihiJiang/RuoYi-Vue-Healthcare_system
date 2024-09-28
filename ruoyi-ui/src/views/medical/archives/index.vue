<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="patientIdcard">
        <el-input
          v-model="queryParams.patientIdcard"
          placeholder="请输入患者身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者户籍" prop="patientHr">
        <el-input
          v-model="queryParams.patientHr"
          placeholder="请输入患者户籍"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="家庭地址" prop="patientAddress">
        <el-input
          v-model="queryParams.patientAddress"
          placeholder="请输入家庭地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="patientPhone">
        <el-input
          v-model="queryParams.patientPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建日期" prop="patientCreateDate">
        <el-date-picker clearable
          v-model="queryParams.patientCreateDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetEmpty">清空</el-button>
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
          v-hasPermi="['medical:archives:add']"
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
          v-hasPermi="['medical:archives:edit']"
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
          v-hasPermi="['medical:archives:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:archives:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- <el-table v-loading="loading" :data="archivesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="患者ID" align="center" prop="patientId"  width="142"/>
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <el-table-column label="患者性别" align="center" prop="patientSex">
      <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.patientSex"/>
      </template>
      </el-table-column>
      <el-table-column label="患者生日" align="center" prop="patientBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patientBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" align="center" prop="patientIdcard" width="160"/>
      <el-table-column label="患者户籍" align="center" prop="patientHr">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patient_hr" :value="scope.row.patientHr"/>
        </template>
      </el-table-column>
      <el-table-column label="家庭地址" align="center" prop="patientAddress" />
      <el-table-column label="患者民族" align="center" prop="patientNation">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patient_nation" :value="scope.row.patientNation"/>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center" prop="patientPhone" width="110"/>
      <el-table-column label="创建日期" align="center" prop="patientCreateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patientCreateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除标记" align="center" prop="deleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:archives:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:archives:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->

    <el-table v-loading="loading" :data="archivesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="患者编号" align="center" prop="patientId" width="150"/>
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <el-table-column label="患者性别" align="center" prop="patientSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.patientSex"/>
        </template>
      </el-table-column>
      <!-- <span>{{ parseTime(scope.row.patientBirthday, '{y}-{m}-{d}') }}</span> -->
      <!-- <el-table-column label="患者生日" align="center" prop="patientBirthday" width="180">
        <template slot-scope="scope">
          
          <span>{{ calculateAge(scope.row.patientBirthday) }}</span> 
        </template>
      </el-table-column> -->
      <el-table-column label="身份证号" align="center" prop="patientIdcard" width="180">
        <!-- 这是身份证号中间的数据用*来展示 -->
        <template slot-scope="scope">
          {{ scope.row.patientIdcard.substring(0,6)+"********"+scope.row.patientIdcard.substring(14) }}
        </template>
      </el-table-column>

      <el-table-column label="患者户籍" align="center" prop="patientHr">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patient_hr" :value="scope.row.patientHr"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="家庭地址" align="center" prop="patientAddress" /> -->
      <el-table-column label="患者民族" align="center" prop="patientNation">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.patient_nation" :value="scope.row.patientNation"/>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center" prop="patientPhone" width="120"/>
      <el-table-column label="创建日期" align="center" prop="patientCreateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patientCreateDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:archives:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:archives:remove']"
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

 
        <!-- 添加或修改患者档案信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="患者姓名" prop="patientName">
              <el-input v-model="form.patientName" placeholder="请输入患者姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-form-item label="患者性别" prop="patientSex">
                <el-select style="width:100%"
                 v-model="form.patientSex" placeholder="请选择患者性别">
                  <el-option
                    v-for="dict in dict.type.sys_user_sex"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
          </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item label="患者生日" prop="patientBirthday">
              <el-date-picker clearable style="width:100%"
                v-model="form.patientBirthday"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择患者生日">
          </el-date-picker>
        </el-form-item> -->

          </el-col>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="patientIdcard">
              <el-input v-model="form.patientIdcard" placeholder="请输入患者身份证号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="患者户籍" prop="patientHr">
          <el-select style="width:100%"
          v-model="form.patientHr" placeholder="请选择患者户籍">
            <el-option
              v-for="dict in dict.type.patient_hr"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="患者民族" prop="patientNation">
          <el-select style="width:100%"
          v-model="form.patientNation" placeholder="请选择患者民族">
            <el-option
              v-for="dict in dict.type.patient_nation"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="联系电话" prop="patientPhone">
          <el-input v-model="form.patientPhone" placeholder="请输入联系电话" />
        </el-form-item>

        <el-form-item label="家庭地址" prop="patientAddress">
          <el-input type="textarea" resize="none" rows="5" v-model="form.patientAddress" placeholder="请输入家庭地址" />
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
import { listArchives, getArchives, delArchives, addArchives, updateArchives } from "@/api/medical/archives";

export default {
  name: "Archives",
  dicts:['patient_nation','patient_hr','sys_user_sex'],
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
      // 患者档案信息表格数据
      archivesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientName: null,
        patientSex: null,
        patientBirthday: null,
        patientIdcard: null,
        patientHr: null,
        patientAddress: null,
        patientPhone: null,
        patientCreateDate: null,
        deleted: null,
       
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patientName: [
          { required: true, message: "患者姓名不能为空", trigger: "blur" }
        ],
        patientSex: [
          { required: true, message: "患者性别不能为空", trigger: "change" }
        ],
        patientIdcard: [
          { required: true, message: "患者身份证号不能为空", trigger: "blur" },
          {validator: (rule, value, callback) => {
        if (value.length < 18 || value.length > 18) {
          callback(new Error('请输入正确的身份证号'));
        }else {
          callback();
        }
      },trigger: 'blur'}
        ],
        patientPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" },
          {  
          pattern: /1[3|5|7|8|][0-9]{9}/, // 11位数字的正则表达式  
          message: "请输入正确的手机号码",  
          trigger: "blur" // 触发验证的时机  
        }  
        ],
        patientCreateDate: [
          { required: true, message: "创建日期不能为空", trigger: "blur" }
        ],
        patientHr:[
        { required: true, message: "患者户籍不能为空", trigger: "change" }
        ],
        patientNation:[
        { required: true, message: "患者民族不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.queryParams.patientIdcard = this.$route.params && this.$route.params.patientIdcard;
   
    this.getList();
  },
  methods: {
    /** 查询患者档案信息列表 */
    getList() {
      this.loading = true;
      listArchives(this.queryParams).then(response => {
        this.archivesList = response.rows;
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
        patientId: null,
        patientName: null,
        patientSex: null,
        patientBirthday: null,
        patientIdcard: null,
        patientHr: null,
        patientAddress: null,
        patientPhone: null,
        patientCreateDate: null,
        deleted:null,
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
    /** 清空按钮操作 */
    resetEmpty(){
      this.queryParams.pageNum= 1,
      this.queryParams.pageSize=10,
      this.queryParams.patientName=null,
      this.queryParams.patientIdcard=null,
      this.queryParams.patientHr=null,
      this.queryParams.patientAddress=null,
      this.queryParams.patientPhone=null,
      this.queryParams.patientCreateDate=null
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.patientId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加患者档案信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const patientId = row.patientId || this.ids
      getArchives(patientId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改患者档案信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patientId != null) {
            updateArchives(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArchives(this.form).then(response => {
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
      const patientIds = row.patientId || this.ids;
      this.$modal.confirm('是否确认删除患者档案信息编号为"' + patientIds + '"的数据项？').then(function() {
        return delArchives(patientIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/archives/export', {
        ...this.queryParams
      }, `archives_${new Date().getTime()}.xlsx`)
    },
      // 将字符串日期转换为日期对象  
      parseDate(dateString) {  
      const parts = dateString.split('-').map(Number);  
      return new Date(parts[0], parts[1] - 1, parts[2]); // 注意月份是从0开始的  
    },  
     // 计算年龄  
     calculateAge(birthDateString) {  
      if (!birthDateString) return '未知';  
  
      const birthDate = this.parseDate(birthDateString);  
      const today = new Date();  
  
      let age = today.getFullYear() - birthDate.getFullYear();  //是获取当前年-指定的年
      const m = today.getMonth() - birthDate.getMonth();  //是获取当前月-指定的月
      // 如果m小于0（表示当前月份在出生月份之前），或者m等于0但today.getDate() < birthDate.getDate()（表示当前日期在出生日期之前），那么说明患者还没有过生日，所以我们需要将年龄减1。
      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {  
        age--;  
      }  
  
      return age;  
    } 
  }
};
</script>
