package com.example.gameguessnum;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/guess-number")
public class GuessNumberServlet extends HttpServlet
{
    private static final int MIN = 0;
    private static final int MAX = 100;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Integer low = (Integer) session.getAttribute("low");
        Integer high = (Integer) session.getAttribute("high");
        if (low == null || high == null)
        {
            low = MIN;
            high = MAX;
            session.setAttribute("low", low);
            session.setAttribute("high", high);
        }
        int guess = (low + high) / 2;
        request.setAttribute("guess", guess);
        request.getRequestDispatcher("guess-num.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        int low = (Integer) session.getAttribute("low");
        int high = (Integer) session.getAttribute("high");
        int guess = Integer.parseInt(request.getParameter("guess"));
        String action = request.getParameter("action");
        if ("Greater".equals(action))
        {
            low = guess + 1;
        }
        else if ("Less".equals(action))
        {
            high = guess - 1;
        }
        else if ("Equal".equals(action))
        {
            session.invalidate();
            response.sendRedirect("win.jsp");
            return;
        }
        session.setAttribute("low", low);
        session.setAttribute("high", high);
        response.sendRedirect("guess-number");
    }
}
