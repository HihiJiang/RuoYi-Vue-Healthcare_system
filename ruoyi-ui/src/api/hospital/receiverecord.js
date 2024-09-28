import request from '@/utils/request'

// 查询接诊记录列表
export function listReceiverecord(query) {
  return request({
    url: '/hospital/receiverecord/list',
    method: 'get',
    params: query
  })
}

// 查询接诊记录详细
export function getReceiverecord(recordsId) {
  return request({
    url: '/hospital/receiverecord/' + recordsId,
    method: 'get'
  })
}

// 新增接诊记录
export function addReceiverecord(data) {
  return request({
    url: '/hospital/receiverecord',
    method: 'post',
    data: data
  })
}

// 修改接诊记录
export function updateReceiverecord(data) {
  return request({
    url: '/hospital/receiverecord',
    method: 'put',
    data: data
  })
}

// 删除接诊记录
export function delReceiverecord(recordsId) {
  return request({
    url: '/hospital/receiverecord/' + recordsId,
    method: 'delete'
  })
}
