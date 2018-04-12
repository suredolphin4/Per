package com.performance.action;

import com.mysql.jdbc.CallableStatement;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.*;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.SalaryStatistic;
import com.performance.pagemodel.Role;
import com.performance.service.SalaryStatisticServiceI;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by zyh on 2016/4/3.
 */

@Action(value = "salarystatisticAction", results = {
        @Result(name = "toSalarystatistic", location = "/performance/modules/salarystatistic/salarystatistic.jsp")
})
@Namespace("/")
@ParentPackage("basePackage")
public class SalaryStatisticAction extends BaseAction implements SessionAware, ModelDriven<SalaryStatistic> {

    @Autowired
    private SalaryStatisticServiceI salaryStaService;

    private SalaryStatistic salaryStatistic = new SalaryStatistic();

    private Map<String, Object> sessionMap;

    @Override
    public SalaryStatistic getModel() {
        return salaryStatistic;
    }

    public String toSalarystatistic(){
        return "toSalarystatistic";
    }

    public void datagrid(){
        List<Role> list= (List<Role>)sessionMap.get("userRoles" +  getUserCode());
        Role role = list.get(0);
        DataGrid dataGrid = null;

        //TODO 硬编码,低级错误
        if("管理员".equals(role.getRolename())){
            dataGrid = salaryStaService.datagrid(this.salaryStatistic);
        }else if("科研人员".equals(role.getRolename())){
            dataGrid = salaryStaService.datagrid(this.salaryStatistic, getUserCode());
        }

        super.writeJson(dataGrid);
    }

    public void downloadExcel(){
        HttpServletResponse response = ServletActionContext.getResponse();
        String fileName = salaryStaService.exportExcel();
        response.setContentType("text/html;charset=utf-8");
        Json j = new Json();
        j.setSuccess(true);
        j.setObj(fileName);
        super.writeJson(j);
    }

    /**
     * 计算工资
     * @added by zxbing 2016.04.05
     */
    public void calculateSalary(){
        Json j = new Json();
        boolean calResult = salaryStaService.calculateSalary();
        if(calResult){
            j.setSuccess(true);
            j.setMsg("工资计算完成");
        }else {
            j.setSuccess(false);
            j.setMsg("工资计算失败！");
        }

        super.writeJson(j);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.sessionMap = session;
    }
}

