import request from '@/utils/request'

// 查询医疗服务项目列表
export function listProject(query) {
  return request({
    url: '/medical/project/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务项目详细
export function getProject(serviceprojectId) {
  return request({
    url: '/medical/project/' + serviceprojectId,
    method: 'get'
  })
}

// 新增医疗服务项目
export function addProject(data) {
  return request({
    url: '/medical/project',
    method: 'post',
    data: data
  })
}

// 修改医疗服务项目
export function updateProject(data) {
  return request({
    url: '/medical/project',
    method: 'put',
    data: data
  })
}

// 删除医疗服务项目
export function delProject(serviceprojectId) {
  return request({
    url: '/medical/project/' + serviceprojectId,
    method: 'delete'
  })
}

// 根据消费大类查询子类信息
export function queryServiceProjectBySid(serviceId) {
  return request({
    url: '/medical/project/queryServiceProjectBySid/' + serviceId,
    method: 'get'
  })
}

