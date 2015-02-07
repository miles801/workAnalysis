package com.michael.workanalysis.basic;

import com.michael.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 */
public class Pool {
    private static Pool ourInstance = new Pool();

    public static Pool getInstance() {
        return ourInstance;
    }

    private Pool() {
    }

    List<ProjectWorkTime> projectWorkTimes = new ArrayList<ProjectWorkTime>();

    public void addProjectWorkTime(ProjectWorkTime projectWorkTime) {
        projectWorkTimes.add(projectWorkTime);
    }

    public synchronized ProjectWorkTime getProjectWorkTime(String projectKey, String employeeName) {
        if (StringUtils.isEmpty(projectKey)) {
            throw new RuntimeException("没有获得项目编号!");
        }
        if (StringUtils.isEmpty(employeeName)) {
            throw new RuntimeException("没有获得员工名称!");
        }
        ProjectWorkTime projectWorkTime = null;
        for (ProjectWorkTime foo : projectWorkTimes) {
            if (foo.getProjectKey().equals(projectKey) && foo.getEmployeeName().equals(employeeName)) {
                projectWorkTime = foo;
                break;
            }
        }
        return projectWorkTime;
    }

    public List<ProjectWorkTime> getFilterProjectWorkTime(Filter filter) {
        List<ProjectWorkTime> list = new ArrayList<ProjectWorkTime>();
        for (ProjectWorkTime foo : projectWorkTimes) {
            if (filter.doFilter(foo)) {
                list.add(foo);
            }
        }
        return list;
    }


}
