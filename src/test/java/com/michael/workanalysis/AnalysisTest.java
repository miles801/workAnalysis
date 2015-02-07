package com.michael.workanalysis;

import com.michael.poi.sheet.SheetFacade;
import com.michael.utils.StringUtils;
import com.michael.workanalysis.basic.Filter;
import com.michael.workanalysis.basic.Pool;
import com.michael.workanalysis.basic.ProjectWorkTime;
import com.michael.workanalysis.basic.WorkTimeDetail;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author Michael
 */
public class AnalysisTest {
    private Logger logger = Logger.getLogger(AnalysisTest.class);

    @Test
    public void testAnalysis() throws Exception {
        // 加载文件
        Collection<File> files = FileUtils.listFiles(new File("D:\\upload\\silu"), new String[]{"xls", "xlsx"}, true);
        Pool pool = Pool.getInstance();
        // 遍历读取文件内容
        for (File file : files) {
            System.out.println("加载文件:" + file.getAbsolutePath());
            SheetFacade facade = new SheetFacade(file.getName(), new FileInputStream(file));
            if (facade.getSheetCounts() < 1) {
                System.err.println("文件[" + file.getAbsolutePath() + "]中没有sheet!");
            }
            Sheet sheet = facade.getSheets().get(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                // 读取项目编号&项目名称
                String projectKey = null;
                try {
                    projectKey = row.getCell(row.getFirstCellNum()).toString();
                } catch (Exception e) {
                    System.out.println("异常文件：" + file.getName() + ",项目编号:" + projectKey + ",异常行数：" + (i + 1));
                }
                if (StringUtils.isEmpty(projectKey)) {
                    continue;
                }
                String projectName = row.getCell(1).toString();
                // 读取工时
                for (int j = 2; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || StringUtils.isEmpty(cell.toString())) {
                        continue;
                    }

                    float workTime = 0.0f;
                    try {
                        workTime = Float.parseFloat(cell.toString());
                    } catch (Exception e) {
                        System.out.println("异常文件：" + file.getName() + ",项目编号:" + projectKey + ",异常行数：" + (i + 1) + ",异常列数：" + (j + 1) + ",值：" + cell.toString() + ",单元格类型：" + cell.getCellType());
                    }
                    String employeeName = firstRow.getCell(j).toString();
                    ProjectWorkTime projectWorkTime = pool.getProjectWorkTime(projectKey, employeeName);
                    // 如果没有找到，则说明是新增加的，需要添加到池子中
                    if (projectWorkTime == null) {
                        projectWorkTime = new ProjectWorkTime();
                        projectWorkTime.setProjectKey(projectKey);
                        projectWorkTime.setProjectName(projectName);
                        projectWorkTime.setEmployeeName(employeeName);
                        pool.addProjectWorkTime(projectWorkTime);
                    }

                    // 添加明细
                    WorkTimeDetail detail = new WorkTimeDetail();
                    detail.setFileName(file.getName());
                    detail.setWorkTime(workTime);
                    detail.setRows(i + 1);
                    detail.setCols(j + 1);
                    projectWorkTime.addDetail(detail);

                }
            }
        }
        System.out.println("文件加载完毕..........");
        final String key[] = new String[]{"AN1410"};
//       AD1303，D1216
//        AD1316
//                AD1428
//        AN1418，N1419
//                AN1430
//        AN1439
//                B1403
//        AS1257
        System.out.println("获得项目编号为[" + Arrays.toString(key) + "]的工时信息!");
        List<ProjectWorkTime> projectWorkTimes = sum(key);
        for (ProjectWorkTime foo : projectWorkTimes) {
            System.out.println(""
//                    + "项目编号:" + foo.getProjectKey()
//                    + ",项目名称:" + foo.getProjectName()
                    + ",员工【" + foo.getEmployeeName()
                    + "】总工时:" + foo.getTotalWorkTime());
//            printDetail(foo);
        }
    }

    private List<ProjectWorkTime> sum(final String[] projectKeys) {
        List<ProjectWorkTime> list = Pool.getInstance().getFilterProjectWorkTime(new Filter() {
            @Override
            public boolean doFilter(ProjectWorkTime projectWorkTime) {
                for (String key : projectKeys) {
                    if (key.equals(projectWorkTime.getProjectKey())) {
                        return true;
                    }
                }
                return false;
            }
        });
        Collections.sort(list, new Comparator<ProjectWorkTime>() {
            @Override
            public int compare(ProjectWorkTime o1, ProjectWorkTime o2) {
                return o1.getEmployeeName().compareTo(o2.getEmployeeName());
            }
        });
        return list;
    }

    private void printDetail(ProjectWorkTime workTime) {
        List<WorkTimeDetail> details = workTime.getDetails();
        if (details != null && !details.isEmpty()) {
            for (WorkTimeDetail detail : details) {
                System.out.println("\t--来源文件:" + detail.getFileName() + ",工时：" + detail.getWorkTime() + ",行号：" + detail.getRows() + ",列号：" + detail.getCols());
            }
        }
    }
}
