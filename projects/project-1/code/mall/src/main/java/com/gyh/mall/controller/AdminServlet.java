package com.gyh.mall.controller;

import com.gyh.mall.model.User;
import com.gyh.mall.model.bo.*;
import com.gyh.mall.utils.HttpUtils;
import com.google.gson.Gson;
import com.gyh.mall.model.Admin;
import com.gyh.mall.model.Result;
import com.gyh.mall.model.vo.AdminLoginVo;
import com.gyh.mall.service.AdminService;
import com.gyh.mall.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");

        // action
        if ("login".equals(action)) {   // 登录
            login(request, response);
        } else if ("addAdminss".equals(action)) {  // 添加管理员
            addAdminss(request, response);
        } else if ("updateAdminss".equals(action)) {   // 修改管理员信息
            updateAdminss(request, response);
        } else if ("getSearchAdmins".equals(action)) {  // 搜索管理员
            getSearchAdmins(request, response);
        } else if ("changePwd".equals(action)) {    // 修改密码
            changePwd(request, response);
        }
    }

    /**
     * 修改当前管理员密码
     * @param request
     * @param response
     * @throws IOException
     */
    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminChangePwdBo changePwdBo = gson.fromJson(requestBody, AdminChangePwdBo.class);

        int code = adminService.changePwd(changePwdBo);

        if (code == 0) {
            response.getWriter().println(Result.error("修改失败"));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(changePwdBo)));
        }
    }

    /**
     * 搜索管理员
     * @param request
     * @param response
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminSearchBo searchBo = gson.fromJson(requestBody, AdminSearchBo.class);

        List<Admin> admins = adminService.getSearchAdmins(searchBo);

        response.getWriter().println(gson.toJson(Result.ok(admins)));
    }

    /**
     * 修改管理员信息
     * 1. 获取管理员信息
     * 2. 修改管理员信息
     * @param request
     * @param response
     */
    private void updateAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminUpdateBo updateBo = gson.fromJson(requestBody, AdminUpdateBo.class);

        int code = adminService.updateAdminss(updateBo);
        if (code == 0) {
            response.getWriter().println(Result.error("修改失败"));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(updateBo)));
        }

    }

    /**
     * 添加管理员
     * @param request
     * @param response
     */
    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminAddBo addBo = gson.fromJson(requestBody, AdminAddBo.class);

        int code = adminService.addAdminss(addBo);
        if (code == 0) {
            response.getWriter().println(Result.error("添加失败"));
        } else {
            response.getWriter().println(gson.toJson(Result.ok(addBo)));
        }
    }

    /**
     * 管理员登录逻辑
     * 1. 浏览器向 8084 发送请求，请求体中携带了用户名密码参数（json 字符串形式）
     * 2. 查询数据库，检验当前用户名密码是否正确
     * 3. 根据密码返回不同的响应
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求体，返回 json 字符串
        String requestBody = HttpUtils.getRequestBody(request);

        // json 转 Java 对象
        AdminLoginBo loginBo = gson.fromJson(requestBody, AdminLoginBo.class);

        Admin login = adminService.login(loginBo);

        if (login != null) {
            AdminLoginVo loginVo = new AdminLoginVo();
            loginVo.setToken(login.getNickname());
            loginVo.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVo)));
        } else {
            response.getWriter().println(Result.error("用户名或者密码错误"));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");

        // action
        if ("allAdmins".equals(action)) {   // 获取所有管理员信息
            allAdmins(request, response);
        } else if ("deleteAdmins".equals(action)) {    // 删除管理员
            deleteAdmins(request, response);
        } else if ("getAdminsInfo".equals(action)) {   // 获取管理员信息
            getAdminsInfo(request, response);
        }
    }

    /**
     * 获取管理员信息
     * @param request
     * @param response
     */
    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Admin admin = adminService.getAdminsInfo(id);

        if (admin != null) {
            response.getWriter().println(gson.toJson(Result.ok(admin)));
        } else {
            response.getWriter().println(Result.error("获取失败"));
        }
    }

    /**
     * 删除管理员
     * @param request
     * @param response
     */
    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Admin admin = adminService.deleteAdmins(id);

        response.getWriter().println(gson.toJson(Result.ok(admin)));
    }

    /**
     * 显示所有的 admin 信息
     * 1. 查询数据库，返回数据
     * 2. 做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        response.getWriter().println(gson.toJson(Result.ok(adminList)));
    }


}
