package com.arteco.grooweb.tag;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class GrooIsAnonymTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		@SuppressWarnings("unchecked")
		Set<String> userRoles = (Set<String>) this.pageContext.getSession().getAttribute("grooRoles");
		if (userRoles == null || userRoles.size() == 0) {
			return EVAL_BODY_INCLUDE;
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
