import request from '@/utils/request'

// 查询患者费用账单列表
export function listBill(query) {
  return request({
    url: '/medical/bill/list',
    method: 'get',
    params: query
  })
}

// 查询患者费用账单详细
export function getBill(billId) {
  return request({
    url: '/medical/bill/' + billId,
    method: 'get'
  })
}

// 新增患者费用账单
export function addBill(data) {
  return request({
    url: '/medical/bill',
    method: 'post',
    data: data
  })
}

// 修改患者费用账单
export function updateBill(data) {
  return request({
    url: '/medical/bill',
    method: 'put',
    data: data
  })
}

// 删除患者费用账单
export function delBill(billId) {
  return request({
    url: '/medical/bill/' + billId,
    method: 'delete'
  })
}
