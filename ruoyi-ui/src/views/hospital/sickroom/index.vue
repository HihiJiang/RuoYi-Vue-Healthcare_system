<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病房名称" prop="roomName">
        <el-input
          v-model="queryParams.roomName"
          placeholder="请输入病房名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病房规格" prop="roomSize">
        <el-select v-model="queryParams.roomSize" placeholder="请选择病房规格" clearable>
          <el-option
            v-for="dict in dict.type.room_size"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="床位数" prop="roomNum">
        <el-input
          v-model="queryParams.roomNum"
          placeholder="请输入床位数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属科室" prop="roomDept">
        <el-select v-model="queryParams.roomDept" placeholder="请选择病房科室" clearable>
          <el-option
            v-for="dict in dict.type.room_dept"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="病房地址" prop="roomAddr">
        <el-input
          v-model="queryParams.roomAddr"
          placeholder="请输入病房地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="备注" prop="roomRemark">
        <el-input
          v-model="queryParams.roomRemark"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          v-hasPermi="['hospital:sickroom:add']"
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
          v-hasPermi="['hospital:sickroom:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <router-link :to="'/hospital/bedname/index/' + this.tzname">
          <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="fc"
        >分床</el-button>
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:sickroom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:sickroom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :border="true" :data="sickroomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="病房ID" align="center" prop="roomId" v-if="false" />
      <el-table-column label="病房名称" align="center" prop="roomName">
        <template slot-scope="scope">
          <router-link :to="'/hospital/bedname/index/' + scope.row.roomName" class="link-type">
            <span>{{ scope.row.roomName }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="病房规格" align="center" prop="roomSize">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.room_size" :value="scope.row.roomSize"/>
        </template>
      </el-table-column>
      <el-table-column label="所属科室" align="center" prop="roomDept">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.room_dept" :value="scope.row.roomDept"/>
      </template>
      </el-table-column>
      <el-table-column label="病房地址" align="center" prop="roomAddr" />
      <!-- <el-table-column label="备注" align="center" prop="roomRemark" /> -->
      <el-table-column label="床位数" align="center" prop="roomNum" />
      <el-table-column label="已住人数" align="center" prop="countNum" />
      <el-table-column label="空床数量" align="center" prop="nullNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:sickroom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:sickroom:remove']"
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

    <!-- 添加或修改病房记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病房名称" prop="roomName">
          <el-input v-model="form.roomName" placeholder="请输入病房名称" />
        </el-form-item>
        <el-form-item label="病房规格" prop="roomSize">
          <el-select v-model="form.roomSize" placeholder="请选择病房规格">
            <el-option
              v-for="dict in dict.type.room_size"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="床位数" prop="roomNum">
          <el-input v-model="form.roomNum" placeholder="请输入床位数" />
        </el-form-item>
        <el-form-item label="所属科室" prop="roomDept">
          <el-select v-model="form.roomDept" placeholder="所属科室">
            <el-option
              v-for="dict in dict.type.room_dept"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="病房地址" prop="roomAddr">
          <el-input v-model="form.roomAddr" placeholder="请输入病房地址" />
        </el-form-item>
        <!-- <el-form-item label="备注" prop="roomRemark">
          <el-input v-model="form.roomRemark" placeholder="请输入备注" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSickroom, getSickroom, delSickroom, addSickroom, updateSickroom ,listBed} from "@/api/hospital/sickroom";

export default {
  name: "Sickroom",
  dicts: ['room_size','room_dept'],
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
      // 分床状态
      fc: true,
      //分床状态判断集合
      fcflist: [],
      //分床按钮跳转时的参数
      tznamelist:[],
      tzname :null,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 病房记录表格数据
      sickroomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roomName: null,
        roomSize: null,
        roomNum: null,
        roomDept: null,
        roomAddr: null,
        roomRemark: null,
        countNum:null,
        nullNum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.queryParams.roomName = this.$route.params && this.$route.params.roomName;
    this.getList();
  },
  methods: {
    /** 查询病房记录列表 */
    getList() {
      this.loading = true;
      listSickroom(this.queryParams).then(response => {
        this.sickroomList = response.rows;
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
        roomId: null,
        roomName: null,
        roomSize: null,
        roomNum: null,
        roomDept: null,
        roomAddr: null,
        roomRemark: null,
        countNum:null,
        nullNum: null,
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
      this.ids = selection.map(item => item.roomId)
      this.fcflist = selection.map(item => item.nullNum)
      this.tznamelist = selection.map(item => item.roomName)
      //循环tznamelist
      if(this.tznamelist.length == 0){
        this.tzname = null;
      }else{
      for(let i=0;i<this.tznamelist.length;i++){
        this.tzname = this.tznamelist[i]
        }
      }
      this.fc = selection.length !== 1
      //循环fcflist
      if(this.fcflist.length == 0){
        this.fc = true;
      }
      if(this.fcflist.length > 1){
        this.fc = true;
      }else{
      for(let i=0;i<this.fcflist.length;i++){
        console.log(this.fcflist[i]);
        if(this.fcflist[i]!='0'){
          this.fc = false;
        }else{
          this.fc = true;
        }
        }
      }
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加病房";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roomId = row.roomId || this.ids
      getSickroom(roomId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改病房";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roomId != null) {
            updateSickroom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSickroom(this.form).then(response => {
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
      const roomIds = row.roomId || this.ids;
      this.$modal.confirm('是否确认删除病房记录编号为"' + roomIds + '"的数据项？').then(function() {
        return delSickroom(roomIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/sickroom/export', {
        ...this.queryParams
      }, `sickroom_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
