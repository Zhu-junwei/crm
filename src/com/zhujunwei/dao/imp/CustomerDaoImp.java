package com.zhujunwei.dao.imp;

import org.springframework.transaction.annotation.Transactional;

import com.zhujunwei.dao.CustomerDao;
import com.zhujunwei.domain.Customer;

@Transactional
public class CustomerDaoImp extends BaseDaoImp<Customer> implements CustomerDao {

}
