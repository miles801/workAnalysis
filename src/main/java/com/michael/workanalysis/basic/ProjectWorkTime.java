package com.michael.workanalysis.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 */
public class ProjectWorkTime {
    /**
     * 项目编号
     */
    private String projectKey;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 员工名称
     */
    private String employeeName;
    /**
     * 总工时
     */
    private float totalWorkTime = 0f;

    /**
     * 工时统计明细
     */
    List<WorkTimeDetail> details;

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getTotalWorkTime() {
        return totalWorkTime;
    }


    public List<WorkTimeDetail> getDetails() {
        return details;
    }

    public void addDetail(WorkTimeDetail workTimeDetail) {
        if (details == null) {
            details = new ArrayList<WorkTimeDetail>();
        }
        this.totalWorkTime += workTimeDetail.getWorkTime();
        details.add(workTimeDetail);
    }

    @Override
    public String toString() {
        return "项目编号:" + this.getProjectKey() + ",项目名称:" + this.getProjectName() + ",员工【" + this.getEmployeeName() + "】总工时:" + this.getTotalWorkTime();
    }
}
