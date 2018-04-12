package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Person;
import com.performances.model.TPerson;

public interface PersonServiceI {

	public Person save(Person p);

	public DataGrid datagrid(Person p);
	
	public DataGrid getPeronByCode(String code);

	public void remove(String ids);

	public TPerson findPerson(String name);

	public Person edit(Person p);

	public Person findPersonById(String Personid);

	public List<Person> ThesisByPoi(FileInputStream fis);
	
	public List<TPerson> findPersonsByHQL(String domainString);

	public String exportExcel();

}
