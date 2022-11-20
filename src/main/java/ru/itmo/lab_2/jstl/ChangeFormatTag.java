package ru.itmo.lab_2.jstl;

import netscape.javascript.JSException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;


public class ChangeFormatTag extends BodyTagSupport {
    @Override
    public int doAfterBody() throws JspException {
        try {
            BodyContent bodyContent = getBodyContent();
            JspWriter out = bodyContent.getEnclosingWriter();
            String text = bodyContent.getString();
            StringBuilder formatText = new StringBuilder();
            for (int i=0; i < text.length(); i++) {
                if(i % 2 == 0) {
                    formatText.append(toUpperCase(text.charAt(i)));
                }
                else {
                    formatText.append(toLowerCase(text.charAt(i)));
                }
            }
            out.println(formatText);
        } catch (IOException e) {
            throw new JspException("Message: " + e.getMessage());
        }
        return SKIP_BODY;
    }
}
