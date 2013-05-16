package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.beans.User;
import com.service.UserJdbcServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/springMail.xml")
public class UserJdbcServiceImplTest {
	private UserJdbcServiceImpl userDB;
	private User testUser;
	private User selectedUser;
	
	public static String longString50="12345678901234567890123456789012345678901234567890";
	
	@Before
    public void setUp() {
		//initialize UserJdbcServiceImpl to setup database connection
		userDB = new UserJdbcServiceImpl(); 
		testUser = new User();
		selectedUser = new User();
    }

	   public int insert(User user) {
	    	return userDB.addNewUser(user);
	    }
	    
	    public User select(int id) {
	    	return userDB.selectById(id);
	    }
	    
	    public int update(User user) {
	    	return userDB.updateUser(user);
	    }
	    
	    public int delete(int id) {
	    	return userDB.deleteUser(id);
	    }
	    
	    protected void setUserSampleValues() {
	    	testUser.setEmail("test1@test.com");
	    	testUser.setPassword("test1");
	    	testUser.setFname("Test1");
	    	testUser.setLname("User1");
	    	testUser.setDisciplineId(1); //Computer Science
	    	testUser.setRoleId(2); //Student
	    }
	    
	    protected void setNewUserSampleValues() {
	    	testUser.setEmail("test2@test.com");
	    	testUser.setPassword("test2");
	    	testUser.setFname("Test2");
	    	testUser.setLname("User2");
	    	testUser.setDisciplineId(1); //Computer Science
	    	testUser.setRoleId(2); //Student
	    }
	    

	    //Try and Insert a User with bad data into the DB
	    @Test(expected=DataIntegrityViolationException.class)
	    public void InsertUserWithBadDataIntoDB() throws DataIntegrityViolationException {
	    	//Create test user with bad values
	    	//Company exceeds 45 characters
	    	testUser.setEmail(longString50);
	    	
	    	//Try and insert bad user
	    	int	count = this.insert(testUser);
	    	
	    	//Test insert - no user should have been created
	    	assertEquals(0,count);
	    }
	    
	    
	    //Insert User into DB
	    @Test 
	    public void InsertUpdateDeleteUser() {
	    	//INSERT
	    	//Setup fields for User
	    	setUserSampleValues();

	    	//Insert User into database and to get its user_id from the DB
	    	//Make sure it is not 0 and set the testUser's id to that number
	    	int id = this.insert(testUser);
	    	assertTrue(id !=0);  
	    	testUser.setId(id);

	    	//Retrieve the user from the DB by using the id number
	    	selectedUser = this.select(testUser.getId());
	    	
	    	//Test to see if the user is indeed the one we inserted
	    	assertEquals(testUser.getEmail(),selectedUser.getEmail());

	    	
	    	//UPDATE
	    	//Set testUser to new sample values
	    	setNewUserSampleValues();
	    	testUser.setId(id);
	    	
	    	//Perform update test
	    	//assertEquals(1,this.update(testUser));

	    	
	    	//DELETE
	    	//Delete test record, should only return one record that was deleted
	    	assertEquals(1,this.delete(id));

	    	//Try retrieving the deleted record id from the DB
	    	testUser = this.select(id);
	    	assertEquals("Try retrieving the deleted record",0,testUser.getId());
	    }
	    
	    @Test
	    public void DeleteUserNotInDB() {
	    	//Get the last inserted user_id
	    	int id = userDB.getLastUserId();
	    	
	    	//Try and delete a user that is 1 more than the last user
	    	assertEquals(0,this.delete(id+1));
	    }
	    
	    @Test(expected=DuplicateKeyException.class)
	    public void InsertUserAlreadyInDB() throws DuplicateKeyException {
	    	//Get the last user's id
	    	int id = userDB.getLastUserId();

	    	//Retrieve the user from the DB and set it to testUser
	    	testUser = this.select(id);
	    	
	    	//Try and write the same user into the DB
	    	assertEquals(0,this.insert(testUser));
	    }
	}
