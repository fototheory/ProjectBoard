package com.session;

import com.beans.User;
/**
 * <code>SessionScopeDataImpl</code> implements <code>SessionScopeData</code>
 * stores actual user from session defined in bean
 * @author Yumiko Iwai
 * @version 1.0
 */
public class SessionScopeDataImpl implements SessionScopeData {
	//Session variable
    private User user;
    /**
	 * constructor
	 * will fetch the session variable from bean 
	 * when <code>SessionScopeData</code> is autowired
	 */
    public SessionScopeDataImpl(User user) {
        this.user = user;
    }
	/**
	 * set user object to session
	 * @param user must contain an authenticated user after login check
	 */
    @Override
    public void setUserInfo(User usr) {
        this.user = usr;
    }
    /**
	 * return user object stored in session
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user exists in session
	 */
    @Override
    public User getUserInfo() {
        return this.user;
    }
}
