import request from '@/utils/request'

// 查询医嘱执行记录列表
export function listDoctorsorderhandle(query) {
  return request({
    url: '/hospital/doctorsorderhandle/list',
    method: 'get',
    params: query
  })
}

// 查询医嘱执行记录详细
export function getDoctorsorderhandle(ordershId) {
  return request({
    url: '/hospital/doctorsorderhandle/' + ordershId,
    method: 'get'
  })
}

// 新增医嘱执行记录
export function addDoctorsorderhandle(data) {
  return request({
    url: '/hospital/doctorsorderhandle',
    method: 'post',
    data: data
  })
}

// 修改医嘱执行记录
export function updateDoctorsorderhandle(data) {
  return request({
    url: '/hospital/doctorsorderhandle',
    method: 'put',
    data: data
  })
}

// 删除医嘱执行记录
export function delDoctorsorderhandle(ordershId) {
  return request({
    url: '/hospital/doctorsorderhandle/' + ordershId,
    method: 'delete'
  })
}
