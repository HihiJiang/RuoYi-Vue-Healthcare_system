<template>
  <div class="app-container">
   
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-row>
        <el-col :span="16">
          <el-form-item label="房间名称" prop="roomName">
        <el-input
          v-model="queryParams.roomName"
          placeholder="请输入房间名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间地址" prop="roomAddr">
        <el-input
          v-model="queryParams.roomAddr"
          placeholder="请输入房间地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属科室" prop="roomDept">
        <el-select v-model="queryParams.roomDept" placeholder="请选择床位状态" clearable>
          <el-option
            v-for="dict in dict.type.room_dept"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="床位名称" prop="bedName">
        <el-input
          v-model="queryParams.bedName"
          placeholder="请输入床位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="床位备注" prop="bedRemark">
        <el-input
          v-model="queryParams.bedRemark"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="床位状态" prop="bedStatus">
        <el-select v-model="queryParams.bedStatus" placeholder="请选择床位状态" clearable>
          <el-option
            v-for="dict in dict.type.bed_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    
    
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospital:bed:add']"
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
          v-hasPermi="['hospital:bed:edit']"
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
          v-hasPermi="['hospital:bed:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:bed:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-divider content-position="left" v-if="queryParams1.patientIdcard != null">患者信息</el-divider>
    <el-descriptions v-if="queryParams1.patientIdcard != null"  class="margin-top"  :column="3" size="mini" :border="true">
      <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        患者姓名
      </template >
      {{ queryParams1.patientName }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 256 256"><path fill="currentColor" d="M208 26h-40a6 6 0 0 0 0 12h25.52l-30 29.94A62 62 0 1 0 114 173.7V194H88a6 6 0 0 0 0 12h26v26a6 6 0 0 0 12 0v-26h26a6 6 0 0 0 0-12h-26v-20.3a62 62 0 0 0 45.28-96.5L202 46.48V72a6 6 0 0 0 12 0V32a6 6 0 0 0-6-6m-88 136a50 50 0 1 1 50-50a50.06 50.06 0 0 1-50 50"/></svg>
        性别
      </template>
      <template>
          <dict-tag :options="dict.type.sys_user_sex" :value="queryParams1.patientSex"/>
      </template>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        联系电话
      </template>
      {{ queryParams1.patientPhone }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets">
          所属科室
        </i>
      </template>
      <template >
          <dict-tag :options="dict.type.room_dept" :value="queryParams1.deptId"/>
        </template>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-office-building"></i>
        居住地址
      </template>
      {{queryParams1.patientAddress}}
    </el-descriptions-item>
  </el-descriptions>
  <el-divider content-position="left" v-if="queryParams1.patientIdcard != null">空床信息</el-divider>

    <el-table v-loading="loading"  :data="bedList" @selection-change="handleSelectionChange">
      <el-table-column v-if="queryParams1.patientIdcard == null" type="selection" width="55" align="center" />
      <el-table-column label="床位ID" align="center" prop="bedId" v-if="false"/>
      <el-table-column label="房间ID" align="center" prop="roomId" v-if="false"/>
      <el-table-column label="所属科室" align="center" prop="roomDept">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.room_dept" :value="scope.row.roomDept"/>
        </template>
      </el-table-column>
      <el-table-column label="房间名称" align="center" prop="roomName">
        <template slot-scope="scope">
          <router-link :to="'/hospital/bedname/index/' + scope.row.roomName" class="link-type">
            <span>{{ scope.row.roomName }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="房间地址" align="center" prop="roomAddr" />
      <el-table-column label="床位名称" align="center" prop="bedName" />
      <el-table-column label="床位费" align="center" prop="bedFee" />
      <el-table-column label="空调费" align="center" prop="bedAirFee" />
      <el-table-column label="床位状态" align="center" prop="bedStatus">
        <template  slot-scope="scope">
          <dict-tag v-if="queryParams1.patientIdcard == null"  :options="dict.type.bed_status" :value="scope.row.bedStatus"/>
          <el-button v-else @click="fenchuang(scope.row.bedId)"  type="success" plain slot="reference">确认分床</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="queryParams1.patientIdcard == null" label="备注" align="center" prop="bedRemark" />
      <el-table-column v-if="queryParams1.patientIdcard == null" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:bed:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:bed:remove']"
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

    <!-- 添加或修改床位记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="床位名称" prop="bedName">
          <el-input v-model="form.bedName" placeholder="请输入床位名称" />
        </el-form-item>
        <el-form-item label="床位费" prop="bedFee">
          <el-input v-model="form.bedFee" placeholder="请输入床位费" />
        </el-form-item>
        <el-form-item label="空调费" prop="bedAirFee">
          <el-input v-model="form.bedAirFee" placeholder="请输入空调费" />
        </el-form-item>
        <el-form-item label="床位状态" prop="bedStatus">
          <el-select v-model="form.bedStatus" placeholder="请选择床位状态，0=空床1=已住">
            <el-option
              v-for="dict in dict.type.bed_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="bedRemark">
          <el-input v-model="form.bedRemark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="房间ID" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间ID" />
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


import { listBed, getBed, delBed, addBed, updateBed ,listArchives,updateSplitbed} from "@/api/hospital/bed";

export default {
  name: "Bed",
  dicts: ['bed_status','room_dept','sys_user_sex'],
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
      // 床位记录表格数据
      bedList: [],
      archivesList :[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bedName: null,
        bedFee: null,
        bedAirFee: null,
        bedStatus: null,
        bedRemark: null,
        roomId: null,
        roomDept: null,
        roomName: null,
        roomAddr: null,
      },
      //患者信息
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        patientId: null,
        patientName: null,
        patientSex: null,
        patientBirthday: null,
        patientIdcard: null,
        patientHr: null,
        patientAddress: null,
        patientPhone: null,
        patientCreateDate: null,
        deleted: null,
        deptId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      count: 0,
    };
  },
  created() {
    this.queryParams.roomName = this.$route.params && this.$route.params.roomName;
    this.queryParams.roomDept = this.$route.params && this.$route.params.roomDept;
    this.queryParams.bedStatus = this.$route.params && this.$route.params.bedStatus;
    this.queryParams1.patientIdcard = this.$route.params && this.$route.params.patientIdcard;
    this.queryParams1.patientId = this.$route.params && this.$route.params.patientId;
    this.queryParams1.deptId = this.$route.params && this.$route.params.roomDept;
    this.getList();
    this.getList1();
  },
  inject:["reload"],
  methods: {
    /** 查询床位记录列表 */
    getList() {
      this.loading = true;
      listBed(this.queryParams).then(response => {
        this.bedList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //获取患者信息
    getList1() {
      this.loading = true;
      listArchives(this.queryParams1).then(response => {
        this.archivesList = response.rows;
        //循环archivesList
        for (let i = 0; i < this.archivesList.length; i++) {
        this.queryParams1.patientId = this.archivesList[i].patientId;
        this.queryParams1.patientName = this.archivesList[i].patientName;
        this.queryParams1.patientSex = this.archivesList[i].patientSex;
        this.queryParams1.patientAddress = this.archivesList[i].patientAddress;
        this.queryParams1.patientPhone = this.archivesList[i].patientPhone;
        break;
        }
        this.loading = false;
      });
    },
    loadroom() {
      if(this.pd == true){
        this.count += 2;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        bedId: null,
        bedName: null,
        bedFee: null,
        bedAirFee: null,
        bedStatus: null,
        bedRemark: null,
        roomId: null,
        roomDept: null,
        roomName: null,
        roomAddr: null,
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
      this.ids = selection.map(item => item.bedId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加床位记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bedId = row.bedId || this.ids
      getBed(bedId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改床位记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bedId != null) {
            updateBed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBed(this.form).then(response => {
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
      const bedIds = row.bedId || this.ids;
      this.$modal.confirm('是否确认删除床位记录编号为"' + bedIds + '"的数据项？').then(function() {
        return delBed(bedIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
      /** 分床按钮操作 */
      fenchuang(bedid) {
      this.$modal.confirm('是否为患者"' +this.queryParams1.patientName+ '"分配床位').then(function() {
        const bedIds = {"bedId" :bedid,"bedStatus" : 1}
        return updateBed(bedIds);
      }).then(() => {
        // this.getList();
        let _this = this;
        let year = new Date().getFullYear(); //获取当前时间的年份
        let month = new Date().getMonth() + 1; //获取当前时间的月份
        let day = new Date().getDate(); //获取当前时间的天数
        let hours = new Date().getHours(); //获取当前时间的小时
        let minutes = new Date().getMinutes(); //获取当前时间的分数
        let seconds = new Date().getSeconds(); //获取当前时间的秒数
          //当小于 10 的是时候，在前面加 0
        if (hours < 10) {
          hours = "0" + hours;
        }
        if (minutes < 10) {
          minutes = "0" + minutes;
        }
        if (seconds < 10) {
          seconds = "0" + seconds;
        }
        //拼接格式化当前时间
        _this.times = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        const patient = {"patientId" :this.queryParams1.patientId,"bedId":bedid,"splitbedStatus" : 1,"splitbedIndate" :_this.times}
        updateSplitbed(patient)
        this.$modal.msgSuccess("分配成功");
        this.closePage();
        this.$router.push('/hospital/splitbed').then(() =>{
          this.reload();
        })
      }).catch(() => {});
    },
    closePage(){
      var currentView = this.$store.state.tagsView.visitedViews[0]
      for (currentView of this.$store.state.tagsView.visitedViews) {
        if (currentView.path === this.$route.path) {
          break
        }
      }
      this.$store.dispatch('tagsView/delView', currentView)
        .then(({ visitedViews }) => {
          if (currentView.path === this.$route.path) {
            const latestView = this.$store.state.tagsView.visitedViews.slice(-1)[0]
            if (latestView) {
              this.$router.push(latestView)
            } else {
              // 如果没有其他标签则跳转到首页
              if (currentView.name === '首页') {
                this.$router.replace({ path: '/redirect' + currentView.fullPath })
              } else {
                this.$router.push('/')
              }
            }
          }
        })
      },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/bed/export', {
        ...this.queryParams
      }, `bed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
