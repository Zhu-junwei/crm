package com.zhujunwei.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhujunwei.domain.PageBean;
import com.zhujunwei.domain.SaleVisit;
import com.zhujunwei.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SaleVisitService saleVisitService ; 
	
	//使用模型驱动
	SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	//接收结束查询时间
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
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
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 设置条件：（在web层设置条件）
		if(saleVisit.getVisit_time() != null){
			detachedCriteria.add(Restrictions.ge("visit_time",saleVisit.getVisit_time()));
		}
		if(visit_end_time != null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		// 调用业务层查询:
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 拜访记录添加到添加页面的方法：saveUI
	 */
	public String saveUI() {
		return "saveUI";
	}
	
	/**
	 * 
	 */
	public String save() {
		saleVisitService.save(saleVisit);
		System.out.println(saleVisit);
		return "saveSuccess";
	}

}
