package com.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.beans.Project;
import com.dao.ProjectJdbcDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/springMail.xml")
public class ProjectJdbcDaoImplTest {
	private ProjectJdbcDaoImpl projDB;
	private Project proj;
	private Project selectedProj;
	
	@Before
    public void setUp() {
		//initialize ProjectJdbcDaoImpl to setup database connection
		projDB = new ProjectJdbcDaoImpl(); 
		proj = new Project();
		selectedProj = new Project();
    }

    public int insert() {
    	return projDB.insert(proj);
    }
    
    public Project select(int id) {
    	return projDB.selectById(id);
    }
    
    public void update() {
    	
    }
    
    public int delete(int id) {
    	return projDB.delete(id);
    }
    
    protected void setProjectValues() {
    	//manually set the project object values by look at related tables
    	proj.setSponsorId(10);
    	proj.setTitle("Test Title");
    	proj.setDesc("Test Desc");
    	//set status new
    	proj.setStatusId(1);
    	//set discipline to computer science
    	proj.setDispId(1);
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	try {
    		Date due = sdf.parse("05/02/2013");
    		proj.setDue(due);
    	}
    	catch(Exception e) {
    		Assert.fail(e.getMessage());
    	}    	
    }
    
    @Test public void ProjectDBInteraction() {
    	this.setProjectValues();
    	int id = this.insert();
    	assertEquals(true,(id!=0));  
    	proj.setId(id);
    	selectedProj = this.select(proj.getId());
    	//check all the values match with selected record
    	assertEquals(proj.getTitle(),selectedProj.getTitle());
    	assertEquals(proj.getDesc(),selectedProj.getDesc());
    	assertEquals(proj.getStatusId(),selectedProj.getStatusId());
    	assertEquals(proj.getDispId(),selectedProj.getDispId());
    	//only one record should be deleted
    	assertEquals(1,this.delete(proj.getId()));
    	//try getting the deleted record
    	proj = this.select(proj.getId());
    	assertEquals(0,proj.getId());
    }
}
