import request from '@/utils/request'

// 查询医生看病流程列表
export function listAction(query) {
  return request({
    url: '/medical/action/list',
    method: 'get',
    params: query
  })
}

// 查询医生看病流程详细
export function getAction(actionId) {
  return request({
    url: '/medical/action/' + actionId,
    method: 'get'
  })
}

// 新增医生看病流程
export function addAction(data) {
  return request({
    url: '/medical/action',
    method: 'post',
    data: data
  })
}

// 修改医生看病流程
export function updateAction(data) {
  return request({
    url: '/medical/action',
    method: 'put',
    data: data
  })
}

// 删除医生看病流程
export function delAction(actionId) {
  return request({
    url: '/medical/action/' + actionId,
    method: 'delete'
  })
}
