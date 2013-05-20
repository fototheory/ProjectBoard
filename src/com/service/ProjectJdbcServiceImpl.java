package com.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import com.beans.Discipline;
import com.beans.Profile;
import com.beans.Project;
import com.beans.Role;
import com.beans.Status;
import com.beans.User;
import com.dao.DisciplineJdbcDaoImpl;
import com.dao.ProfileJdbcDaoImpl;
import com.dao.ProjectJdbcDaoImpl;
import com.dao.RoleJdbcDaoImpl;
import com.dao.StatusJdbcDaoImpl;
import com.dao.UserJdbcDaoImpl;
import com.encryption.URLEncryption;
import com.mail.MailManager;

/**
 * <code>ProjectJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Project</code> are implemented
 * Any controller with <code>Project</code> related activities will instantiate this class
 * This service instantiates <code>ProjectJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class ProjectJdbcServiceImpl implements SpringJdbcService<Project> {
	//instantiates ProjectJdbcDaoImpl project related database transaction
	ProjectJdbcDaoImpl projectJdbcDao = new ProjectJdbcDaoImpl();
	//instantiates ProjectJdbcDaoImpl user related database transaction
	RoleJdbcDaoImpl roleJdbcDao = new RoleJdbcDaoImpl();
	private static final String SEP = "=-=";
	
	//getter/setter for the attribute
	public ProjectJdbcDaoImpl getSpringJdbcDao() {
		return projectJdbcDao;
	}

	public void setSpringJdbcDao(ProjectJdbcDaoImpl springJdbcDao) {
		this.projectJdbcDao = springJdbcDao;
	}
	
	/**
	 * Select a project by its id
	 * @param id a project id
	 * @return returns empty project object if a project_id doesn't have match in database, 
	 * otherwise, returns a project record
	 */
	@Override
	public Project selectById(int id) {
		//select user by user_id
		return projectJdbcDao.selectById(id);
	}
	
	/**
	 * Add a project to the database
	 * @param proj a project object
	 * @return the project's id number
	 */
	public int add(Project proj) {
		return projectJdbcDao.insert(proj);
	}
	
    /**
	 * Check user's authenticity
	 * @param email is passed from <code>LoginController</code> through login.jsp
	 * @param password is passed from <code>LoginController</code> through login.jsp
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	public Project loginCheck(String email, String password) {
		//get user with matching email and password
		return projectJdbcDao.loginCheck(email, password);
	}
	
	/**
	 * Return the record project count base on the user's role
	 * @param id the project's id
	 * @param roleFld the role's database field name
	 * @return record count
	 */
	public int countByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		return projectJdbcDao.countByRole(id, projectFldByRole(role.getName()));
	}
	
	/**
	 * Get a collection of unarchived projects by the user's role 
	 * @param id the project's id
	 * @param roleFld the role's database field name
	 * @return returns a collection of unarchived projects
	 */
	public Collection<Map<String, String>> selectByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		List<Project> projList = projectJdbcDao.selectByRole(id, projectFldByRole(role.getName()));
		//based on status fetch and format related data
		//loop through each project object
		
		Collection<Map<String, String>> projectList = new HashSet<Map<String, String>>();
		for (int i = 0; i < projList.size(); i++) {
			projectList.add(formatProjectItem(projList.get(i)));
		}
		return projectList;
	}
	
	/**
	 * Get a collection of archived projects by the user's role 
	 * @param id the project's id
	 * @param roleFld the role's database field name
	 * @return returns a collection of archived projects
	 */
	public Collection<Map<String, String>> selectArchivedByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		List<Project> projList = projectJdbcDao.selectArchivedByRole(id, projectFldByRole(role.getName()));
		//based on status fetch and format related data
		//loop through each project object
		
		Collection<Map<String, String>> projectList = new HashSet<Map<String, String>>();
		for (int i = 0; i < projList.size(); i++) {
			projectList.add(formatProjectItem(projList.get(i)));
		}
		return projectList;
	}
	

	/**
	 * Get a collection of all unarchived projects
	 * @return a collection of all unarchived projects
	 */
	public Collection<Map<String, String>> selectAll() {
		//get role name
		List<Project> projList = projectJdbcDao.selectAll();
		//based on status fetch and format related data
		//loop through each project object
		
		Collection<Map<String, String>> projectList = new HashSet<Map<String, String>>();
		for (int i = 0; i < projList.size(); i++) {
			projectList.add(formatProjectItem(projList.get(i)));
		}
		return projectList;
	}
	
	/**
	 * Get a collection of all archived projects
	 * @return a collection of all archived projects
	 */
	public Collection<Map<String, String>> selectAllArchived() {
		//get role name
		List<Project> projList = projectJdbcDao.selectAllARchived();
		//based on status fetch and format related data
		//loop through each project object
		
		Collection<Map<String, String>> projectList = new HashSet<Map<String, String>>();
		for (int i = 0; i < projList.size(); i++) {
			projectList.add(formatProjectItem(projList.get(i)));
		}
		return projectList;
	}
	
	/**
	 * Update a project
	 * @param proj the project object to be updated
	 * @return returns the number of records affected by the update
	 */
	public int updateProjectInfo(Project project) {
		//get user with matching email and password
		return projectJdbcDao.update(project);
	}
	
	/**
	 * Delete a project by its id
	 * @param projId the project's id to be deleted
	 * @return returns the number of records deleted
	 */
	public int delete(int projId) {
		//delete project with matching project id
		return projectJdbcDao.delete(projId);
	}
	
	/*
	 * updateProjectStatusLead - send email to all lead faculty
	 *
	 */
	/**
	 * Send email to all lead faculty and update the project's status
	 * @param serverURL the projectboard's server URL
	 * @param mailManager the MailManager
	 * @param projId the project's id to be deleted
	 * @param stat the status 
	 * @param dispId the discipline id of the faculty member
	 * @return returns 0 or 1; 0 for no update, 1 for project updated
	 */
	public int updateProjectStatusLead(String serverURL, MailManager mailManager, int projId, String stat, int dispId) {
		int statId = 0; 
		statId = fetchStatusId(stat);
		if(statId>0) {
			//get all leads in discipline
			List<User> leads = fetchAllLeads(dispId);
			if(leads.size()>0) {
				String[] messages = this.createRequestMsg(projId, "email");
				String subject = messages[0];
				messages[1] += "\n\nIf you are willing to accept this project, please click on following link: ";
				//send email to each lead
				for(User lead : leads){
					String leadName = lead.getFname()+" "+lead.getLname()+"\n\r";
					String link = serverURL + "/acceptNewProject.do?key=";
					String key = projId+SEP+lead.getId();
					URLEncryption newEnc = new URLEncryption();
					String wholeMsg = "";
					try {
						byte[] encodedBytes = newEnc.encodeURL(key);
					    String encodedURL = new String(Base64.encodeBase64(encodedBytes));
					    wholeMsg = leadName+messages[1]+link+encodedURL;
					}	
					catch(Exception e) {
						System.out.println(e.toString());
					}
					boolean res = mailManager.sendMail("webmaster.nuproactive@gmail.com",lead.getEmail(),subject,wholeMsg);
					
					if (!res) {
						System.out.println("Error sending email to "+lead.getEmail()+".");
					}
				}
				//update status
				return projectJdbcDao.updateStatus(projId, statId);
			}
		}
		return 0;
	}

	/**
	 * Update the project's negotiating faculty user
	 * @param projId the project's id
	 * @param negId the negotiating faculty user' id
	 * @return returns 0 or 1; 0 for no update, 1 for project updated
	 */	
	public int assignNeg(int projId, int negId) {
		int statId = 0; 
		statId = fetchStatusId("Neg Assigned");
		if(statId>0) {
			return projectJdbcDao.updateStatusWithFac(projId, statId, projectFldByRole("Negotiating faculty"), negId);
		}
		return 0;
	}
	
	/**
	 * Create the email request message
	 * @param projId the project's id
	 * @param type the type of line break for the message
	 * @return returns the formated email request message
	 */	
	public String[] createRequestMsg(int projId, String type) {
		//get project information
		Project submittedProj = projectJdbcDao.selectById(projId);
		//get sponsor information
		//instantiates UserJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		User sponsor = userJdbcDao.selectById(submittedProj.getSponsorId());
		//decide line break for email or html
		String lineBreak = (type.equals("html"))?"<br />":"\n";
		String messages[] = new String[2];
		messages[0] = "NU Capstone Project Board - Accept Project: " + submittedProj.getTitle();
		messages[1] = "Sponsor: "+ sponsor.getFname() +" " + sponsor.getLname()
				+ " has submitted new project: "+ lineBreak + 
				"Title: "+ submittedProj.getTitle() + lineBreak + 
				"Description: "+ submittedProj.getDesc()+ lineBreak + 
				"Due Date: "+ submittedProj.getDue()+ lineBreak + 
				"Are you willing to accept this project?";
		return messages;
	}
	
	/**
	 * Retrieve project and lead information based on the email sent to faculty
	 * @param key the key to be decoded
	 * @return returns the decoded key
	 */	
	public String[] decodeKey(String key) {
		try {
			byte[] encodedBytes = Base64.decodeBase64(key.getBytes("UTF8"));
			URLEncryption newEnc = new URLEncryption();
			String decodedString = "";
			decodedString = newEnc.decodeURL(encodedBytes);
			return separateString(decodedString);	
		}	
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return new String[]{};
	}
	
	/**
	 * Utility function to separate a string based the separator "=-="
	 * @param combined the string to be separated
	 * @return returns a separated string array
	 */	
	protected String[] separateString(String combined) {
		return combined.split(SEP);
	}

	/**
	 * Assign lead faculty to the project
	 * @param leadId the lead faculty's user id
	 * @param projId the project's id
	 * @param statId the status' id
	 * @return returns 0 or 1; 0 for no update, 1 for project updated
	 */	
	public int leadAcceptProj(int leadId, int projId, int statId) {
		if(statId>0) {
			if(leadId>0) {
				return projectJdbcDao.updateStatusWithFac(projId, statId, "project_lead_faculty",leadId);
			}
		}
		return 0;
	}
	
	/**
	 * Assign lead faculty to the project based on discipline id
	 * @param projId the project's id
	 * @param stat the status
	 * @param dispId the discipline id
	 * @return returns 0 or 1; 0 for no update, 1 for project updated
	 */	
	public int updateProjectStatusWithFac(int projId, String stat, int dispId) {
		int statId = 0; 
		statId = fetchStatusId(stat);
		if(statId>0) {
			//get lead faculty id
			int leadId = fetchLeadId(dispId);
			if(leadId>0) {
				return projectJdbcDao.updateStatusWithFac(projId, statId, "project_lead_faculty",leadId);
			}
		}
		return 0;
	}
	
	/**
	 * Check to see if project is accepted by the lead faculty
	 * @param projId the project's id
	 * @param leadid the lead faculty's user id
	 * @return returns 0 or 1; 0 for no, 1 for yes
	 */	
	public int checkProjAcceptedbyLead(int projId, int leadId) {
		return projectJdbcDao.checkAcceptedbyLead(projId, leadId);
	}
	
	/**
	 * Retrieve the sponsor's company
	 * @param projId the project's id
	 * @return return the company name for the sponsor
	 */	
	public String getSponsorCompany(int projId) {
		Project proj = projectJdbcDao.selectById(projId);
		//instantiates UserJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		User sponsor = userJdbcDao.selectById(proj.getSponsorId());
		//instantiates ProfileJdbcDaoImpl user related database transaction
		ProfileJdbcDaoImpl profileJdbcDao = new ProfileJdbcDaoImpl();
		Profile sponsorProfile = profileJdbcDao.selectById(sponsor.getProfileId());
		return sponsorProfile.getCompany();
	}
	
	/**
	 * Archive a project
	 * @param projId the project's id
	 * @return returns the number of records affected by the update
	 */
	public int archiveProject(int projId) {
		return projectJdbcDao.archiveProject(projId);
	}

	/**
	 * Get the lead faculty's id based on their discipline
	 * @param dispId the discipline for the lead faculty
	 * @return returns the id number of the lead faculty for the given discipline
	 */
	protected int fetchLeadId(int dispId) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		return userJdbcDao.getLeadId(dispId);
	}
	
	/**
	 * Get All the lead faculty members id based on their discipline
	 * @param dispId the discipline for the lead faculty
	 * @return returns the list of lead faculty members
	 */
	protected List<User> fetchAllLeads(int dispId) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		return userJdbcDao.getAllLeadsInDisp(dispId);
	}
	
	/**
	 * Get the status id by the status name
	 * @param stat the status name
	 * @return returns the id number for the status
	 */
	protected int fetchStatusId(String stat) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		StatusJdbcDaoImpl statJdbcDao = new StatusJdbcDaoImpl();
		return statJdbcDao.selectIdByName(stat);
	}
	
	/**
	 * Format the project item
	 * @param proj the Project object
	 * @return returns the project item
	 */
	protected Map<String, String> formatProjectItem(Project proj) {
		Map<String, String> projItem = new LinkedHashMap<String, String>();
		//set information required for any status
		projItem.put("ID",Integer.toString(proj.getId()));
		projItem.put("DispID",Integer.toString(proj.getDispId()));
		projItem.put("Title",proj.getTitle());
		projItem.put("Description",proj.getDesc());
		if(proj.getDue()!= null) {
			projItem.put("Due Date",proj.getDue().toString());
		}
		if(proj.getDateCreated()!= null) {
			projItem.put("Date Created",proj.getDateCreated().toString());
		}
		//instantiates UserJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		User sponsor = userJdbcDao.selectById(proj.getSponsorId());
		projItem.put("Sponsor",sponsor.getFname()+" "+sponsor.getLname());
		//instantiates DisciplineJdbcDaoImpl user related database transaction
		DisciplineJdbcDaoImpl dispJdbcDao = new DisciplineJdbcDaoImpl();
		Discipline disp = dispJdbcDao.selectById(proj.getDispId());
		projItem.put("Discipline",disp.getName());
		//check status
		//instantiates StatusJdbcDaoImpl status related database transaction
		StatusJdbcDaoImpl statusJdbcDao = new StatusJdbcDaoImpl();
		Status stat = statusJdbcDao.selectById(proj.getStatusId());
		projItem.put("Status",stat.getName());
		int proiority = proiorityStatus(stat.getName());
		//set additional fields
		if(proiority > 4) {
			//project completed
			projItem.put("Date Completed",proj.getDateCompleted().toString());
			String archivedMsg = (proj.isArchived()>0)?"Yes":"No";
			projItem.put("Archived", archivedMsg);
		}
		if(proiority > 3) {
			//project in progress
			//for now just ID
			projItem.put("Group",Integer.toString(proj.getGroupId()));
		}
		if(proiority > 2) {
			//project assigned
			projItem.put("Date Started",proj.getDateStarted().toString());
		}
		if(proiority > 1) {
			//project posted
			if(proj.getCapId()>0) {
				User capstone = userJdbcDao.selectById(proj.getCapId());
				projItem.put("Capstone Faculty",capstone.getFname()+" "+capstone.getLname());
			}
			User neg = userJdbcDao.selectById(proj.getNegId());
			projItem.put("Negotiating Faculty",neg.getFname()+" "+neg.getLname());
		}
		if(proiority > 0) {
			//project submitted
			if(proj.getLeadId()>0) {
				User lead = userJdbcDao.selectById(proj.getLeadId());
				projItem.put("Lead Faculty",lead.getFname()+" "+lead.getLname());
				projItem.put("Man Hours",Integer.toString(proj.getManHours()));
			}
			else {
				projItem.put("Lead Faculty","Waiting for lead faculty to accept project");
			}
		}
		return projItem;
	}
	
	/**
	 * Set number for status that higher number can contain
	 * lower number elements in map
	 * @param roleName of the user
	 * @return returns count of user found
	 */
	protected int proiorityStatus(String stat) {
		int priority = 0;
		switch(stat) {
		 case "Completed": 
			 	priority = 5;
	        	break;
	        case "In Progress": 
	        	priority = 4;
	        	break;
	        case "Assigned": 
	        	priority = 3;
	        	break;
	        case "Posted": 
	        	priority = 2;
	        	break;
	        case "Submitted": 
	        	priority = 1;
	        	break;
	        default:
	        	break;
		}
		return priority;
	}
	
	/**
	 * Fetch project field name based on role
	 * @param roleName of the user
	 * @return returns count of user found
	 */
	protected String projectFldByRole(String roleName) {
		String fld = "";
		switch (roleName) {
        case "Sponsor": 
        	fld = "project_sponsor";
        	break;
        case "Lead faculty": 
        	fld = "project_lead_faculty";
        	break;
        case "Negotiating faculty": 
        	fld = "project_negotiating_faculty";
        	break;
        case "Capstone faculty": 
        	fld = "project_capstone_faculty";
        	break;
        default:
        	break;
		}
		return fld;
	}	
}

