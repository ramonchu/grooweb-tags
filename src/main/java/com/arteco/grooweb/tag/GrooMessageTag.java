package com.arteco.grooweb.tag;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.arteco.grooweb.web.GrooLocaleResolver;
import com.arteco.grooweb.web.GrooMessenger;

public class GrooMessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String code;
	private Object[] values;
	private boolean capitalize = false;
	private boolean upperCase = false;

	public void setValues(Object[] bean) {
		this.values = bean;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCapitalize(Boolean bean) {
		this.capitalize = bean;
	}

	public void setUpperCase(Boolean bean) {
		this.upperCase = bean;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			GrooMessenger messenger = (GrooMessenger) this.pageContext.getRequest().getAttribute("grooMessenger");
			GrooLocaleResolver localeResolver = (GrooLocaleResolver) this.pageContext.getRequest().getAttribute("grooLocaleResolver");
			Locale locale = localeResolver.getLocale((HttpServletRequest) this.pageContext.getRequest());
			String text = messenger.interpolate(locale, code, values);

			if (StringUtils.trimToNull(text) == null) {
				text = "¿¿¿" + code + "???";
			}

			if (capitalize) {
				text = StringUtils.capitalize(text);
			}
			if (upperCase) {
				text = StringUtils.upperCase(text);
			}
			this.pageContext.getOut().append(StringUtils.trimToEmpty(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
