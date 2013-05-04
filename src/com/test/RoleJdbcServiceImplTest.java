package com.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.beans.Role;
import com.service.RoleJdbcServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/applicationContext.xml")
public class RoleJdbcServiceImplTest {
	private RoleJdbcServiceImpl roleDB;
	private Role testRole;
	private Role selectedRole;
	
	public static String longString50="12345678901234567890123456789012345678901234567890";
	
	@Before
    public void setUp() {
		//initialize RoleJdbcServiceImpl to setup database connection
		roleDB = new RoleJdbcServiceImpl(); 
		testRole = new Role();
		selectedRole = new Role();
    }

    public int insert(Role role) {
    	return roleDB.insert(role);
    }
    
    public Role select(int id) {
    	return roleDB.selectById(id);
    }
    
    public int update(Role role) {
    	return roleDB.update(role);
    }
    
    public int delete(int id) {
    	return roleDB.delete(id);
    }
    
    protected void setRoleSampleValues() {
    	testRole.setName("Test Role");
    }
    
    protected void setNewRoleSampleValues() {
    	testRole.setName("Test Role2");
    }
    

    //Try and Insert a Role with bad data into the DB
    @Test(expected=DataIntegrityViolationException.class)
    public void InsertRoleWithBadDataIntoDB() throws DataIntegrityViolationException {
    	//Create test role with bad values
    	//Company exceeds 45 characters
    	testRole.setName(longString50);
    	
    	//Try and insert bad role
    	int	count = this.insert(testRole);
    	
    	//Test insert - no role should have been created
    	assertEquals(0,count);
    }
    
    
    //Insert Role into DB
    @Test 
    public void InsertUpdateDeleteRole() {
    	//INSERT
    	//Setup fields for Role
    	setRoleSampleValues();

    	//Insert Role into database and to get its role_id from the DB
    	//Make sure it is not 0 and set the testRole's id to that number
    	int id = this.insert(testRole);
    	assertTrue(id !=0);  
    	testRole.setId(id);

    	//Retrieve the role from the DB by using the id number
    	selectedRole = this.select(testRole.getId());
    	
    	//Test to see if the role is indeed the one we inserted
    	assertEquals(testRole.getName(),selectedRole.getName());

    	
    	//UPDATE
    	//Set testRole to new sample values
    	setNewRoleSampleValues();
    	testRole.setId(id);
    	
    	//Perform update test
    	assertEquals(1,this.update(testRole));

    	
    	//DELETE
    	//Delete test record, should only return one record that was deleted
    	assertEquals(1,this.delete(id));

    	//Try retrieving the deleted record id from the DB
    	testRole = this.select(id);
    	assertEquals("Try retrieving the deleted record",0,testRole.getId());

    }
    
    @Test
    public void DeleteRoleNotInDB() {
    	//Get the last inserted role_id
    	int id = roleDB.getLastId();
    	
    	//Try and delete a role that is 1 more than the last role
    	assertEquals(0,this.delete(id+1));
    }
    
    @Test(expected=DuplicateKeyException.class)
    public void InsertRoleAlreadyInDB() throws DuplicateKeyException {
    	//Get the last role's id
    	int id = roleDB.getLastId();

    	//Retrieve the role from the DB and set it to testRole
    	testRole = this.select(id);
    	
    	//Try and write the same role into the DB
    	assertEquals(0,this.insert(testRole));
    }

   
}
