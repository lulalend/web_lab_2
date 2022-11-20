package ru.itmo.lab_2.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "SetCookieServlet",
        value = "/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) {
        // exactly after controllerServlet because of CheckURLFilter
        Cookie cookie = new Cookie( "ACCEPT_COOKIE", "true" );
        response.addCookie( cookie );
        response.setStatus( HttpServletResponse.SC_OK );
    }
}
