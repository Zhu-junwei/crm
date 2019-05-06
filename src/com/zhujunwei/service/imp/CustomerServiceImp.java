package com.zhujunwei.service.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhujunwei.dao.CustomerDao;
import com.zhujunwei.domain.Customer;
import com.zhujunwei.domain.PageBean;
import com.zhujunwei.service.CustomerService;

public class CustomerServiceImp implements CustomerService {
	
	//注入Dao
	private CustomerDao customerDao ;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public void save(Customer customer) {
		this.customerDao.save(customer);
		
	}
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示记录数
		pageBean.setPageSize(pageSize);
		//调用DAO
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示数据的集合
		Integer begin = (currPage - 1)*pageSize ;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
		
	}
	@Override
	// 业务层修改客户的方法:
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
}
