package com.syalloc.main.servlet;

import com.syalloc.main.configuration.ConfigurationCenter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SALocalHelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        StringBuilder stringBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(ConfigurationCenter.getInstance().getWebConfiguration().getServer().getIndex()), StandardCharsets.UTF_8);
        stream.forEach(s -> stringBuilder.append(s).append("\n"));
        System.out.println("响应内容："+stringBuilder.toString());
        resp.getWriter().print(stringBuilder.toString());
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }
}
