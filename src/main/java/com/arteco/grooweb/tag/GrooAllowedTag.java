package com.arteco.grooweb.tag;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class GrooAllowedTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String[] rolesAllowed;

	public void setRoles(String rolesAllowed) {
		this.rolesAllowed = StringUtils.split(rolesAllowed);
	}

	@Override
	public int doStartTag() throws JspException {
		@SuppressWarnings("unchecked")
		Set<String> userRoles = (Set<String>) this.pageContext.getSession().getAttribute("grooRoles");
		if (userRoles != null && rolesAllowed != null) {
			for (String userRole : userRoles) {
				for (String allowedRole : rolesAllowed) {
					if (StringUtils.equals(allowedRole, userRole)) {
						return EVAL_BODY_INCLUDE;
					}
				}
			}
		}
		return SKIP_BODY;
	}

	// @Override
	// public int doEndTag() throws JspException {
	// try {
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return EVAL_PAGE;
	// }

}
