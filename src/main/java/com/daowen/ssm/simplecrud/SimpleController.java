package com.daowen.ssm.simplecrud;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleController {

    @Autowired
    protected HttpServletRequest request;

    protected String join(String separator, String[] items) {
        if (items == null || items.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(items[i]);
        }
        return sb.toString();
    }
}