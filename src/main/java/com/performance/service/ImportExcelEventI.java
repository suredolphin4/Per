package com.performance.service;

public interface ImportExcelEventI<T> {
	
	/**
	 * 预处理保存模型事件
	 * @param model
	 * @return
	 */
	public T BeforeSaveModel(T model);
	
	/**
	 * 判断是否有效模型
	 * @param model
	 * @return
	 */
	public boolean IsValidModel(T model);
	
	/**
	 * 保存新增模型事件之后
	 * @param model
	 */
	public void AfterSaveModel(T model);
	
	/**
	 * 保存更新模型事件之后
	 * @param model
	 */
	public void AfterUpdateModel(T model);
}
