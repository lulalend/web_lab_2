package ru.itmo.lab_2.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itmo.lab_2.entities.Table;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "SendTableServlet",
        value = "/send-table")
public class SendTableServlet extends HttpServlet {
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String ENCODING = "UTF-8";
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mapper.findAndRegisterModules();
    }
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        HttpSession session = request.getSession();
        if ( !session.isNew() ) {
            response.setStatus( HttpServletResponse.SC_OK );
            response.setContentType( CONTENT_TYPE_JSON );
            response.setCharacterEncoding( ENCODING );
            PrintWriter out = response.getWriter();
            out.println( mapper.writeValueAsString(Table.getTable()) );
            out.close();
        }
    }
}
