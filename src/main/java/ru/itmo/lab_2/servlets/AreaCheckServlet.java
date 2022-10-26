package ru.itmo.lab_2.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itmo.lab_2.entities.Table;
import ru.itmo.lab_2.entities.TableRow;
import ru.itmo.lab_2.handlers.HitChecker;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


@WebServlet(name = "AreaCheckServlet", value = "/area-check")
public class AreaCheckServlet extends HttpServlet {
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String ENCODING = "UTF-8";
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        double startTime = System.nanoTime();

        BigDecimal R = new BigDecimal(request.getParameter("R"));
        BigDecimal X = new BigDecimal(request.getParameter("X"));
        BigDecimal Y = new BigDecimal(request.getParameter("Y"));

        String resultHit = HitChecker.checkHit(R, X, Y) ? "Попадение)" : "Промах(";

        Cookie[] requestCookies = request.getCookies();
        String acceptCookie = "false";
        ZoneOffset clientZone = ZoneOffset.of("+0");

        for (Cookie cookie : requestCookies) {
            if (cookie.getName().equals("TIMEZONE"))
                clientZone = ZoneOffset.of( cookie.getValue().charAt(0) == '-'
                        ? cookie.getValue() : "+" + cookie.getValue() );
            if (cookie.getName().equals("ACCEPT_COOKIE"))
                acceptCookie = cookie.getValue();
        }

        OffsetDateTime clientTime = OffsetDateTime.now(clientZone);
        double executedTime = (System.nanoTime() - startTime)/1000000d;

        TableRow tableRow = new TableRow(R, X, Y, resultHit, clientTime,
                Math.ceil(executedTime * Math.pow(10, 2))/Math.pow(10, 2) );


        if (acceptCookie.equals("true")) {
            Table.addTableRow(tableRow);
            HttpSession session = request.getSession();
            session.setAttribute("result_table", Table.getTable());
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(CONTENT_TYPE_JSON);
        response.setCharacterEncoding(ENCODING);

        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(tableRow.toList()));
        out.close();
    }
}
