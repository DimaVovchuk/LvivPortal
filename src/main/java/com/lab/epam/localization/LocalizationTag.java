package com.lab.epam.localization;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class LocalizationTag extends TagSupport {
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        try {
            JspWriter out = pageContext.getOut();
            out.write(new String((bundle.getString(key)).getBytes("ISO-8859-1"), "cp1251"));
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}