package com.laioffer.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.db.MySQLConnection;
import com.laioffer.jupiter.db.MySQLException;
import com.laioffer.jupiter.entity.FavoriteRequestBody;
import com.laioffer.jupiter.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FavoriteServlet", urlPatterns = {"/favorite"})
public class FavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String userId = request.getParameter("user_id");
        // Protect Favorite Related Functions with Session Validation
        // get current session
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        // 从 session 中读取 user_id
        String userId = (String) session.getAttribute("user_id");
//        ObjectMapper mapper = new ObjectMapper();
//        FavoriteRequestBody body = mapper.readValue(request.getReader(), FavoriteRequestBody.class);
        // step 1: 读取 request body: request.getReader()
        // step 2: 用 ObjectMapper 将 JSON对象 convert 为 Java Object（class类型：FavoriteRequestBody.class）：mapper.readValue()
        // request.getReader() --> Reader 是读取 stream的interface
        FavoriteRequestBody body = ServletUtil.readRequestBody(FavoriteRequestBody.class, request);
        if (body == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        MySQLConnection connection = null;
        try {
            connection = new MySQLConnection();
            connection.setFavoriteItem(userId, body.getFavoriteItem());
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String userId = request.getParameter("user_id");
        // Protect Favorite Related Functions with Session Validation
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String userId = (String) session.getAttribute("user_id");
        Map<String, List<Item>> itemMap;
        MySQLConnection connection = null;
        try {
            connection = new MySQLConnection();
            itemMap = connection.getFavoriteItems(userId);
            // response.setContentType("application/json;charset=UTF-8");
            // response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));
            // Method 2: utilize ServletUtil writeItemMap function
            ServletUtil.writeItemMap(response, itemMap);
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String userId = request.getParameter("user_id");
        // Protect Favorite Related Functions with Session Validation
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

//        ObjectMapper mapper = new ObjectMapper();
//        FavoriteRequestBody body = mapper.readValue(request.getReader(), FavoriteRequestBody.class);
        FavoriteRequestBody body = ServletUtil.readRequestBody(FavoriteRequestBody.class, request);
        if (body == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String userId = (String) session.getAttribute("user_id");
        MySQLConnection connection = null;
        try {
            connection = new MySQLConnection();
            connection.unsetFavoriteItem(userId, body.getFavoriteItem().getId());
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
