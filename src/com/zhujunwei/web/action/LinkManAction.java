package com.zhujunwei.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhujunwei.domain.Customer;
import com.zhujunwei.domain.LinkMan;
import com.zhujunwei.domain.PageBean;
import com.zhujunwei.service.CustomerService;
import com.zhujunwei.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象
	LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	//注入service
	private LinkManService linkManService ;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	} 
	//注入客户管理的Service
	private CustomerService customerService ;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//使用set方法的方式接收数据
	//分页参数
	private Integer currPage = 1;
	private Integer pageSize = 3;
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1 ;
		}
		this.currPage = currPage;
	}
	//使用set方法接受每页显示记录数
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3 ;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 分页查询客户的方法：findAll
	 */
	public String findAll() {
		// 接收参数：分页参数
		// 最好使用DetachedCriteria对象（条件查询--带分页）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		if (linkMan.getLkm_name()!=null) {
			//设置按名称查询的条件
			detachedCriteria.add(Restrictions.ilike("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		//设置性别条件
		if (linkMan.getLkm_gender()!=null && !"".equals(linkMan.getLkm_gender())) {
			//设置按性别查询的条件
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		// 调用业务层查询:
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 跳转到添加页面的方法saveUI
	 */
	public String saveUI() {
		//查询所有客户
		List<Customer> list = customerService.findAll();
		//将list集合保存到值栈中
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * 保存联系人的方法
	 */
	public String save() {
		//条用业务层保存联系人
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/**
	 * 跳转到编辑页面的方法
	 */
	public String edit() {
		//查询某个联系人，查询所有客户
		//查询所有客户
		List<Customer> list = customerService.findAll();
		//根据id查询联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		
		//将list和linkMan的对象带到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		
		return "editSuccess";
	}
	
	/**
	 * 修改联系人的方法
	 */
	public String update() {
		//调用业务层
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * 删除联系人
	 */
	public String delete() {
		//调用业务层：先查询联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		//删除联系人
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}

}
