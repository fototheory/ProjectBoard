package com.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.beans.Profile;
import com.service.ProfileJdbcServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/springMail.xml")
public class ProfileJdbcServiceImplTest {
	private ProfileJdbcServiceImpl profileDB;
	private Profile testProfile;
	private Profile selectedProfile;
	
	public static String longString50="12345678901234567890123456789012345678901234567890";
	
	@Before
    public void setUp() {
		//initialize ProfileJdbcServiceImpl to setup database connection
		profileDB = new ProfileJdbcServiceImpl(); 
		testProfile = new Profile();
		selectedProfile = new Profile();
    }

    public int insert(Profile profile) {
    	return profileDB.insert(profile);
    }
    
    public Profile select(int id) {
    	return profileDB.selectById(id);
    }
    
    public int update(Profile profile) {
    	return profileDB.update(profile);
    }
    
    public int delete(int id) {
    	return profileDB.delete(id);
    }
    
    protected void setProfileSampleValues() {
    	testProfile.setCompany("Test Company");
    	testProfile.setPhone("1234567890");
    	testProfile.setSkills("Test Skills");
    }
    
    protected void setNewProfileSampleValues() {
    	testProfile.setCompany("Test Company2");
    	testProfile.setPhone("0987654321");
    	testProfile.setSkills("Test Skills2");
    }
    

    //Try and Insert a Profile with bad data into the DB
    @Test(expected=DataIntegrityViolationException.class)
    public void InsertProfileWithBadDataIntoDB() throws DataIntegrityViolationException{
    	//Create test profile with bad values
    	//Company exceeds 45 characters & phone exceeds 10
    	testProfile.setCompany("12345678901234567890123456789012345678901234567890");
    	testProfile.setPhone("123-456-7890");
    	testProfile.setSkills("Test Skills");
    	
    	//Try and insert bad profile
    	int	count = this.insert(testProfile);
    	
    	//Test insert - no profile should have been created
    	assertEquals(0,count);
    }
    
    
    //Insert Profile into DB
    @Test 
    public void InsertUpdateDeleteProfile() {
    	//INSERT
    	//Setup fields for Profile
    	setProfileSampleValues();

    	//Insert Profile into database and to get its project_id from the DB
    	//Make sure it is not 0 and set the testProfile's id to that number
    	int id = this.insert(testProfile);
    	assertTrue(id !=0);  
    	testProfile.setId(id);

    	//Retrieve the profile from the DB by using the id number
    	selectedProfile = this.select(testProfile.getId());
    	
    	//Test to see if the profile is indeed the one we inserted
    	assertEquals(testProfile.getCompany(),selectedProfile.getCompany());
    	assertEquals(testProfile.getPhone(),selectedProfile.getPhone());
    	assertEquals(testProfile.getSkills(),selectedProfile.getSkills());

    	
    	//UPDATE
    	//Set testProfile to new sample values
    	setNewProfileSampleValues();
    	testProfile.setId(id);
    	
    	//Perform update test
    	assertEquals(1,this.update(testProfile));

    	
    	//DELETE
    	//Delete test record, should only return one record that was deleted
    	assertEquals(1,this.delete(id));

    	//Try retrieving the deleted record id from the DB
    	testProfile = this.select(id);
    	assertEquals("Try retrieving the deleted record",0,testProfile.getId());

    }
    
    @Test
    public void DeleteProfileNotInDB(){
    	//Get the last inserted profile_id
    	int id = profileDB.getLastId();
    	
    	//Try and delete a profile that is 1 more than the last profile
    	assertEquals(0,this.delete(id+1));
    }

   
}
