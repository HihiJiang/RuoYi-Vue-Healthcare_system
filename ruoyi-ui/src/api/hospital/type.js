import request from '@/utils/request'

// 查询医疗服务类型列表
export function listType(query) {
  return request({
    url: '/hospital/type/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务类型详细
export function getType(servicetypeId) {
  return request({
    url: '/hospital/type/' + servicetypeId,
    method: 'get'
  })
}

// 新增医疗服务类型
export function addType(data) {
  return request({
    url: '/hospital/type',
    method: 'post',
    data: data
  })
}

// 修改医疗服务类型
export function updateType(data) {
  return request({
    url: '/hospital/type',
    method: 'put',
    data: data
  })
}

// 删除医疗服务类型
export function delType(servicetypeId) {
  return request({
    url: '/hospital/type/' + servicetypeId,
    method: 'delete'
  })
}
