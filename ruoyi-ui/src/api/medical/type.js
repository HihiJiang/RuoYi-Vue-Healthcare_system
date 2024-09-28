import request from '@/utils/request'

// 查询医疗服务列表
export function listType(query) {
  return request({
    url: '/medical/type/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务详细
export function getType(servicetypeId) {
  return request({
    url: '/medical/type/' + servicetypeId,
    method: 'get'
  })
}

// 新增医疗服务
export function addType(data) {
  return request({
    url: '/medical/type',
    method: 'post',
    data: data
  })
}

// 修改医疗服务
export function updateType(data) {
  return request({
    url: '/medical/type',
    method: 'put',
    data: data
  })
}

// 删除医疗服务
export function delType(servicetypeId) {
  return request({
    url: '/medical/type/' + servicetypeId,
    method: 'delete'
  })
}

// 根据serviceType=“pacs/lis”查询医疗服务大类信息
export function queryServiceTypeByDetials(servicetypeId) {
  return request({
    url: '/medical/type/queryServiceTypeByDetials/' + servicetypeId,
    method: 'get'
  })
}


