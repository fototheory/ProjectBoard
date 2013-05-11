package com.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

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
	 * generic select by id
	 * @param id user_id of user table
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	@Override
	public Project selectById(int id) {
		//select user by user_id
		return projectJdbcDao.selectById(id);
	}
	
	public int add(Project proj) {
		return projectJdbcDao.insert(proj);
	}
	
    /**
	 * check user's authenticity
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
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
	 */
	public int countByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		return projectJdbcDao.countByRole(id, projectFldByRole(role.getName()));
	}
	
	/**
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
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
	
	public int updateProjectInfo(Project project) {
		//get user with matching email and password
		return projectJdbcDao.update(project);
	}
	
	public int delete(int projId) {
		//delete project with matching project id
		return projectJdbcDao.delete(projId);
	}
	
	/*
	 * updateProjectStatusLead - send email to all lead faculty
	 *
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
				}
				//update status
				return projectJdbcDao.updateStatus(projId, statId);
			}
		}
		return 0;
	}

	public int assignNeg(int projId, int negId) {
		int statId = 0; 
		statId = fetchStatusId("Neg Assigned");
		if(statId>0) {
			return projectJdbcDao.updateStatusWithFac(projId, statId, projectFldByRole("Negotiating faculty"), negId);
		}
		return 0;
	}
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
	
	/*
	 * decodeKey - retrieve project and lead information based on the email sent to faculty
	 *
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
	
	protected String[] separateString(String combined) {
		return combined.split(SEP);
	}
	
	public int leadAcceptProj(int leadId, int projId, int statId) {
		if(statId>0) {
			if(leadId>0) {
				return projectJdbcDao.updateStatusWithFac(projId, statId, "project_lead_faculty",leadId);
			}
		}
		return 0;
	}
	
	/*
	 * updateProjectStatusWithFac - assign lead faculty to the project
	 *
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
	
	public int checkProjAcceptedbyLead(int projId, int leadId) {
		return projectJdbcDao.checkAcceptedbyLead(projId, leadId);
	}
	
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
	
	public int archiveProject(int projId) {
		return projectJdbcDao.archiveProject(projId);
	}
	protected int fetchLeadId(int dispId) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		return userJdbcDao.getLeadId(dispId);
	}
	
	protected List<User> fetchAllLeads(int dispId) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
		return userJdbcDao.getAllLeadsInDisp(dispId);
	}
	
	protected int fetchStatusId(String stat) {
		//instantiates StatusJdbcDaoImpl user related database transaction
		StatusJdbcDaoImpl statJdbcDao = new StatusJdbcDaoImpl();
		return statJdbcDao.selectIdByName(stat);
	}
	
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
	 * fetch project field name based on role
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

