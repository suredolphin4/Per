<%--
  Created by IntelliJ IDEA.
  User: zxbing
  Date: 15-11-11
  Time: 下午4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../../../layout/center.jsp"></jsp:include>

<!-- 工具栏 -->
<div id="tb">
  <a id="lw_statusfilter" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stutafilter', plain:true" onclick="proStaAdvancedSearch()">高级查询</a>
  <a id="export" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-export', plain:true" onclick="proStaExportExcel()">导出</a>
  <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload', plain:true" onclick="refreshfunc()">刷新</a>
  <a id="totalProjectCalc" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-audit', plain:true" onclick="projectTotalCalculate()">项目统计更新计算</a>
</div>

<!-- 高级搜索 -->
<div id="proStaAdvancedSearch_dialog" class="easyui-dialog" data-options="region:'north',title:'高级检索',buttons:'#ps_advanced_search_btn',border:false,closed:true,modal:true"
     style="width:580px;height: 200px;padding-top:20px;padding-left:20px">
  <table class="altrowstable" cellspacing=”0″ cellpadding=”2″>
    <tr>
      <th>用户名</th>
      <td><input id="ps_username" name="ps_username"  class="easyui-textbox" data-options="width:'150px'" /></td>
      <th>领域</th>
      <td><input id="ps_domain" name="ps_domain"  class="easyui-textbox" data-options="width:'150px'" /></td>
    </tr>
    <tr>
      <th>用户编号</th>
      <td><input id="ps_usercode" name="ps_usercode"  class="easyui-textbox" data-options="width:'150px'" /></td>
      </tr>
  </table>
</div>
<div id="ps_advanced_search_btn" >
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="ps_adv_search_btn_ok()">搜索</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="ps_adv_search_btn_cancel()">取消</a>
</div>
