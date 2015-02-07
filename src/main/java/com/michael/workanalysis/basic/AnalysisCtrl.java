package com.michael.workanalysis.basic;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Michael
 */
@WebServlet(name = "AnalysisServlet", urlPatterns = {"/analysis"}, loadOnStartup = 0)
public class AnalysisCtrl extends HttpServlet {
    private Logger logger = Logger.getLogger(AnalysisCtrl.class);

    @Override
    public void init() throws ServletException {
        logger.info("初始化分析数据....");
        AnalysisMain.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectNo = req.getParameter("projectNo");
        List<ProjectWorkTime> details = AnalysisMain.getInstance().analysis(projectNo.split(","));
        Gson gson = new Gson();
        String json = gson.toJson(details);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
