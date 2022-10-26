package ru.itmo.lab_2.servlets;

import ru.itmo.lab_2.entities.Table;
import ru.itmo.lab_2.handlers.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "controllerServlet",
        value = "/server")
public class ControllerServlet extends HttpServlet {
    private static final String CONTENT_TYPE_TEXT = "text/plain";
    private static final String ENCODING = "UTF-8";

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        ServletContext context = getServletContext();

        if ( request.getParameter( "table") != null &&
                request.getParameter("table").equals("true") ) {
                context.getRequestDispatcher("/send-table").forward(request, response);
        }

        if( request.getParameter( "cookie") != null &&
                request.getParameter( "cookie" ).equals("true") ) {
            context.getRequestDispatcher( "/set-cookie" ).forward(request, response);
        }

        String R = request.getParameter( "R" );
        String X = request.getParameter( "X" );
        String Y = request.getParameter( "Y" );

        try {
            Validator.validate( R, X, Y );
            context.getRequestDispatcher( "/area-check" ).forward( request, response );
        } catch ( IllegalArgumentException e ) {
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType( CONTENT_TYPE_TEXT );
            response.setCharacterEncoding( ENCODING );
            PrintWriter out = response.getWriter();
            out.println( e.getMessage() );
            out.close();
        }
    }

    @Override
    protected void doDelete( HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();
        Table.clearTable();
        session.invalidate();

        response.setStatus( HttpServletResponse.SC_OK );
        response.setCharacterEncoding( ENCODING );
    }
}