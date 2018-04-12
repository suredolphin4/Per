package com.performance.service.impl;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.SalaryStatistic;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.SalaryStatisticServiceI;
import com.performances.model.TSalaryStatistic;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyh on 2016/4/1.
 */
@Transactional
@Service
public class SalaryStatisticService extends BaseServiceImpl implements SalaryStatisticServiceI{
    @Autowired
    private BaseDaoI<TSalaryStatistic> salStaDao;

    //Excel导入服务
    @Autowired
    private ImportExcelServiceI<TSalaryStatistic> _importExcel;

    private static final String tableName = "salary_statistic";
    private String hsql;
    private Map<String, Object> params;

    @Override
    public DataGrid datagrid(SalaryStatistic salSta) {
        DataGrid dg = new DataGrid();

        String hql = "from TSalaryStatistic t ";
        Map<String, Object> params = new HashMap<>();
        hql = addWhere(salSta, hql, params);
        String totalHql = "select count(*) " + hql;
        hql = addOrder(salSta, hql);
        try {
            this.hsql = hql;
            this.params = params;
            List<TSalaryStatistic> tps = salStaDao.find(hql, params, salSta.getPage(), salSta.getRows());
            List<SalaryStatistic> ps = new ArrayList<>();
            changeModel(tps, ps);
            dg.setTotal(salStaDao.count(totalHql, params));
            dg.setRows(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dg;
    }

    @Override
    public DataGrid datagrid(SalaryStatistic salSta, String usercode) {
        DataGrid dg = new DataGrid();

        String hql = "from TSalaryStatistic t ";
        Map<String, Object> params = new HashMap<>();
        hql = addWhere(salSta, hql, params);
        hql += " and usercode = :usercode";
        params.put("usercode", usercode);
        String totalHql = "select count(*) " + hql;
        hql = addOrder(salSta, hql);
        try {
            this.hsql = hql;
            this.params = params;
            List<TSalaryStatistic> tps = salStaDao.find(hql, params, salSta.getPage(), salSta.getRows());
            List<SalaryStatistic> ps = new ArrayList<>();
            changeModel(tps, ps);
            dg.setTotal(salStaDao.count(totalHql, params));
            dg.setRows(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dg;
    }

    @Override
    public String exportExcel() {
        return _importExcel.exportExcel("SalaryStatistic", tableName, hsql, params);
    }

    private String addWhere(SalaryStatistic salSta, String hsql, Map<String, Object> params){
        StringBuilder sqlStr = new StringBuilder(hsql);
        sqlStr.append("where 1=1 ");
        String s_username = salSta.getS_username();
        if(s_username != null && !s_username.isEmpty()){
            sqlStr.append(" and username like :username");
            params.put("username", "%%" + s_username + "%%");
        }

//        String s_domain = salSta.getS_domain();
//        if(s_domain != null && !s_domain.isEmpty()){
//            sqlStr.append(" and domain like :domain");
//            params.put("domain", "%%" + s_domain + "%%");
//        }

        //4. 用户编号(高级检索)
        String search_usercode = salSta.getSearch_usercode();
        if (search_usercode != null && !search_usercode.isEmpty()) {
            sqlStr.append(" and (");
            sqlStr.append(" t.usercode = :search_usercode ");
            sqlStr.append(")");
            params.put("search_usercode",  search_usercode);
        }

        return sqlStr.toString();
    }

    private String addOrder(SalaryStatistic salSta, String hsql){

        return hsql;
    }

    private void changeModel(List<TSalaryStatistic> l, List<SalaryStatistic> nl) {
        if (l != null && l.size() > 0) {
            for (TSalaryStatistic t : l) {
                SalaryStatistic u = new SalaryStatistic();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }

    /**
     * 执行“计算工资”存储过程
     */
    @Override
    public boolean  calculateSalary(){
        String calSalarySQL = "CALL per.proc_salary_calc()";
        int result = salStaDao.executeSqlQuery(calSalarySQL);
        if(result != -1){
            return true;
        }else{
            return false;
        }
    }

}


