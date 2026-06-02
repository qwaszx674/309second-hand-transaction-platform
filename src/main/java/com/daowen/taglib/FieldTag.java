package com.daowen.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class FieldTag extends BodyTagSupport {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}