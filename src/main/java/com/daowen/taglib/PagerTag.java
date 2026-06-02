package com.daowen.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class PagerTag extends BodyTagSupport {

    private String url;
    private int total = 0;
    private int pagesize = 10;
    private int currentpage = 1;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (total <= 0) {
                out.print("");
                return EVAL_PAGE;
            }
            
            int totalPages = (total + pagesize - 1) / pagesize;
            if (currentpage < 1) currentpage = 1;
            if (currentpage > totalPages) currentpage = totalPages;
            
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='pagination'>");
            
            if (currentpage > 1) {
                sb.append("<a href='").append(url).append("?page=").append(currentpage - 1).append("'>上一页</a>");
            }
            
            int startPage = Math.max(1, currentpage - 5);
            int endPage = Math.min(totalPages, currentpage + 5);
            
            for (int i = startPage; i <= endPage; i++) {
                if (i == currentpage) {
                    sb.append("<span class='current'>").append(i).append("</span>");
                } else {
                    sb.append("<a href='").append(url).append("?page=").append(i).append("'>").append(i).append("</a>");
                }
            }
            
            if (currentpage < totalPages) {
                sb.append("<a href='").append(url).append("?page=").append(currentpage + 1).append("'>下一页</a>");
            }
            
            sb.append("</div>");
            out.print(sb.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}