/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;

/**
 *
 * @author test
 */
public class ExampleTag2 extends SimpleTagSupport {

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public String[][] getAryData() {
        return aryData;
    }

    public void setAryData(String[][] aryData) {
        this.aryData = aryData;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private Writer writer = null;
    private String[][] aryData = null;
    private String header = null;
    private final HandleDBheader handdb_header = new HandleDBheader();

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
            String code = null;
            out.write("<h3>" + header + "</h3>");
            out.write("<table border=\"1\">");
            for (int i = 0; i < aryData.length; i++) {
                code += ("<tr>");
                if (i == 0) {
                    for (int j = 0; j < aryData[0].length; j++) {
                        out.write("<th>" + aryData[i][j] + "</th>");
                    }
                } else {
                    for (int j = 0; j < aryData[0].length; j++) {
                        out.write("<td>" + aryData[i][j] + "</td>");
                    }
                }
                out.write("</tr>");
            }
            out.write("</table>");
            out.write("<br><hr>");
        } catch (Exception ex) {
        }

    }
    /*
    public void showByTable(String[] header, String[][] data) {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.println("<table border=\"1\">");
            out.println("<tr>");

            for (String colName : header) {
                out.println("<th>" + colName + "</th>");
            }

            out.println("</tr>");

            for (int j = 0; j < data[0].length; j++) {
                out.println("<tr>");
                for (String[] items : data) {
                    out.println("<td>" + items[j] + "</td>");
                }
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
     */

}
