package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.beans.Discipline;
import com.service.DisciplineJdbcServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/springMail.xml")
public class DisciplineJdbcServiceImplTest {
	private DisciplineJdbcServiceImpl disciplineDB;
	private Discipline testDiscipline;
	private Discipline selectedDiscipline;
	
	public static String longString50="12345678901234567890123456789012345678901234567890";
	
	@Before
    public void setUp() {
		//initialize DisciplineJdbcServiceImpl to setup database connection
		disciplineDB = new DisciplineJdbcServiceImpl(); 
		testDiscipline = new Discipline();
		selectedDiscipline = new Discipline();
    }

	   public int insert(Discipline discipline) {
	    	return disciplineDB.add(discipline);
	    }
	    
	    public Discipline select(int id) {
	    	return disciplineDB.selectById(id);
	    }
	    
	    public int update(Discipline discipline) {
	    	return disciplineDB.update(discipline);
	    }
	    
	    public int delete(int id) {
	    	return disciplineDB.delete(id);
	    }
	    
	    protected void setDisciplineSampleValues() {
	    	testDiscipline.setName("Test Discipline");
	    }
	    
	    protected void setNewDisciplineSampleValues() {
	    	testDiscipline.setName("Test Discipline2");
	    }
	    

	    //Try and Insert a Discipline with bad data into the DB
	    @Test(expected=DataIntegrityViolationException.class)
	    public void InsertDisciplineWithBadDataIntoDB() throws DataIntegrityViolationException {
	    	//Create test discipline with bad values
	    	//Company exceeds 45 characters
	    	testDiscipline.setName(longString50);
	    	
	    	//Try and insert bad discipline
	    	int	count = this.insert(testDiscipline);
	    	
	    	//Test insert - no discipline should have been created
	    	assertEquals(0,count);
	    }
	    
	    
	    //Insert Discipline into DB
	    @Test 
	    public void InsertUpdateDeleteDiscipline() {
	    	//INSERT
	    	//Setup fields for Discipline
	    	setDisciplineSampleValues();

	    	//Insert Discipline into database and to get its discipline_id from the DB
	    	//Make sure it is not 0 and set the testDiscipline's id to that number
	    	int id = this.insert(testDiscipline);
	    	assertTrue(id !=0);  
	    	testDiscipline.setId(id);

	    	//Retrieve the discipline from the DB by using the id number
	    	selectedDiscipline = this.select(testDiscipline.getId());
	    	
	    	//Test to see if the discipline is indeed the one we inserted
	    	assertEquals(testDiscipline.getName(),selectedDiscipline.getName());

	    	
	    	//UPDATE
	    	//Set testDiscipline to new sample values
	    	setNewDisciplineSampleValues();
	    	testDiscipline.setId(id);
	    	
	    	//Perform update test
	    	assertEquals(1,this.update(testDiscipline));

	    	
	    	//DELETE
	    	//Delete test record, should only return one record that was deleted
	    	assertEquals(1,this.delete(id));

	    	//Try retrieving the deleted record id from the DB
	    	testDiscipline = this.select(id);
	    	assertEquals("Try retrieving the deleted record",0,testDiscipline.getId());
	    }
	    
	    @Test
	    public void DeleteDisciplineNotInDB() {
	    	//Get the last inserted discipline_id
	    	int id = disciplineDB.getLastDisciplineId();
	    	
	    	//Try and delete a discipline that is 1 more than the last discipline
	    	assertEquals(0,this.delete(id+1));
	    }
	    
	    @Test(expected=DuplicateKeyException.class)
	    public void InsertDisciplineAlreadyInDB() throws DuplicateKeyException {
	    	//Get the last discipline's id
	    	int id = disciplineDB.getLastDisciplineId();

	    	//Retrieve the discipline from the DB and set it to testDiscipline
	    	testDiscipline = this.select(id);
	    	
	    	//Try and write the same discipline into the DB
	    	assertEquals(0,this.insert(testDiscipline));
	    }
	}
