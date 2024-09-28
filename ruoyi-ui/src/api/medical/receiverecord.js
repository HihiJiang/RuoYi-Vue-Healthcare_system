import request from '@/utils/request'

// 查询接诊记录列表
export function listReceiverecord(query) {
  return request({
    url: '/medical/receiverecord/list',
    method: 'get',
    params: query
  })
}

// 查询接诊记录详细
export function getReceiverecord(recordsId) {
  return request({
    url: '/medical/receiverecord/' + recordsId,
    method: 'get'
  })
}

// 新增接诊记录
export function addReceiverecord(data) {
  return request({
    url: '/medical/receiverecord',
    method: 'post',
    data: data
  })
}

// 修改接诊记录
export function updateReceiverecord(data) {
  return request({
    url: '/medical/receiverecord',
    method: 'put',
    data: data
  })
}

// 删除接诊记录
export function delReceiverecord(recordsId) {
  return request({
    url: '/medical/receiverecord/' + recordsId,
    method: 'delete'
  })
}



// 添加检验、检查
export function addLisAndPacs(data) {
  return request({
    url: '/medical/receiverecord/addLisAndPacs',
    method: 'post',
    data: data
  })
}

//添加处置药房记录
export function addDrugs(data) {
  return request({
    url: '/medical/receiverecord/addDrugs',
    method: 'post',
    data: data
  })
}