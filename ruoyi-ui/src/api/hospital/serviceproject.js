import request from '@/utils/request'

// 查询医疗服务项目列表
export function listServiceproject(query) {
  return request({
    url: '/hospital/serviceproject/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务项目详细
export function getServiceproject(serviceprojectId) {
  return request({
    url: '/hospital/serviceproject/' + serviceprojectId,
    method: 'get'
  })
}

// 新增医疗服务项目
export function addServiceproject(data) {
  return request({
    url: '/hospital/serviceproject',
    method: 'post',
    data: data
  })
}

// 修改医疗服务项目
export function updateServiceproject(data) {
  return request({
    url: '/hospital/serviceproject',
    method: 'put',
    data: data
  })
}

// 删除医疗服务项目
export function delServiceproject(serviceprojectId) {
  return request({
    url: '/hospital/serviceproject/' + serviceprojectId,
    method: 'delete'
  })
}
