<template>
  <div id="app">
    <router-view  v-if="ifreload"/>
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  },
  data() {
    return {
		ifreload: true,
    };
  },
  provide() {
    //提供
    return {
      reload: this.reload,
    };
  },
  methods: {
    reload() {
      this.ifreload = false;
      this.$nextTick(function () {
        this.ifreload = true;
      });
    },
  },
}
</script>
<style >
#app .theme-picker {
  display: none;
}

/* elementUI dialog弹出框样式优化 */
.el-dialog {
		position: absolute;
		top: 50%;
		left: 50%;
		margin: 0 !important;
		transform: translate(-50%, -50%);
		max-height: calc(100% - 30px);
		max-width: calc(100% - 30px);
		display: flex;
		flex-direction: column;
		/* 圆角效果 */
		border-radius: 0.4em;
	}

	.el-dialog>.el-dialog__body {
		overflow: auto;
	}

	.el-dialog__header {
		background-color: #F5F7FA;
		border-bottom: 1px solid #ccc;
		font-size: 14px;
		font-weight: bold;
		padding: 15px 20px 15px;
		/* 圆角效果 */
		border-radius: 0.4em 0.4em 0em 0em;
	}

	.el-dialog__body {
		padding: 20px 15px 0px 15px;
	}

	.el-dialog__footer {
		background-color: #F5F7FA;
		border-top: 1px solid #ccc;
		padding: 15px;
		/* 圆角效果 */
		border-radius: 0em 0em 0.4em 0.4em;
	}
</style>
