import request from '@/utils/request'

// 查询药品信息列表
export function listDrugsinfo(query) {
  return request({
    url: '/medical/drugsinfo/list',
    method: 'get',
    params: query
  })
}

// 查询药品信息详细
export function getDrugsinfo(drugsId) {
  return request({
    url: '/medical/drugsinfo/' + drugsId,
    method: 'get'
  })
}

// 新增药品信息
export function addDrugsinfo(data) {
  return request({
    url: '/medical/drugsinfo',
    method: 'post',
    data: data
  })
}

// 修改药品信息
export function updateDrugsinfo(data) {
  return request({
    url: '/medical/drugsinfo',
    method: 'put',
    data: data
  })
}

// 删除药品信息
export function delDrugsinfo(drugsId) {
  return request({
    url: '/medical/drugsinfo/' + drugsId,
    method: 'delete'
  })
}


// 新增药品信息
export function selectDrugsByPingyin(kw) {
  return request({
    url: '/medical/drugsinfo/selectDrugsByPingyin/'+kw,
    method: 'get'
  })
}