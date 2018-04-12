<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(opts) {
		var t = $('#layout_center_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div id="layout_center_tabs" name="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false,showHeader:false,closable : true" style="overflow: hidden;">
	<table id="center_table"></table>
	<div title="首页"></div>
</div>