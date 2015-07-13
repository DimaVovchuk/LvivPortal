package com.lab.epam.localization;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class LocalizationTag extends TagSupport {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

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
        loger.info("doStartTag method");
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        loger.info("doEndTag method");
        return EVAL_PAGE;
    }
}