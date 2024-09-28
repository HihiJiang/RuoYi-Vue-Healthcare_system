import request from '@/utils/request'

// 查询住院登记列表
export function listSplitbed(query) {
  return request({
    url: '/hospital/splitbed/list',
    method: 'get',
    params: query
  })
}

// 查询住院登记详细
export function getSplitbed(splitbedId) {
  return request({
    url: '/hospital/splitbed/' + splitbedId,
    method: 'get'
  })
}

// 新增住院登记
export function addSplitbed(data) {
  return request({
    url: '/hospital/splitbed',
    method: 'post',
    data: data
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

// 删除住院登记
export function delSplitbed(splitbedId) {
  return request({
    url: '/hospital/splitbed/' + splitbedId,
    method: 'delete'
  })
}
