package com.session;

import com.beans.User;
/**
 * <code>SessionScopeData</code> class utilizes a session variable from applicationContext.xml
 * @author Yumiko Iwai
 * @version 1.0
 */
public interface SessionScopeData {
	public void setUserInfo(User user);
    public User getUserInfo();
}
