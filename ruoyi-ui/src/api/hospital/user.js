import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";
// 查询用户信息列表
export function listUser(query) {
  return request({
    url: '/hospital/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUser(userId) {
  return request({
    url: '/hospital/user/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addUser(data) {
  return request({
    url: '/hospital/user',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUser(data) {
  return request({
    url: '/hospital/user',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUser(userId) {
  return request({
    url: '/hospital/user/' + userId,
    method: 'delete'
  })
}



// 查询部门下拉树结构
export function deptTreeSelect() {
  return request({
    url: '/system/user/deptTree',
    method: 'get'
  })
}

// 查询患者档案信息列表
export function listArchives(query) {
  return request({
    url: '/medical/archives/list',
    method: 'get',
    params: query
  })
}

// 修改住院登记
export function updateSplitbed(data) {
  return request({
    url: '/hospital/splitbed',
    method: 'put',
    data: data
  })
}

// 用户密码重置
// export function updateUserPwd(oldPassword, newPassword) {
//   const data = {
//     oldPassword,
//     newPassword
//   }
//   return request({
//     url: '/system/user/profile/updatePwd',
//     method: 'put',
//     params: data
//   })
// }

// // 用户状态修改
// export function changeUserStatus(userId, status) {
//   const data = {
//     userId,
//     status
//   }
//   return request({
//     url: '/system/user/changeStatus',
//     method: 'put',
//     data: data
//   })
// }