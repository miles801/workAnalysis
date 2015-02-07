package com.michael.workanalysis.basic;

/**
 * @author Michael
 */
public interface Filter {
    /**
     * @param projectWorkTime 工时对象
     * @return true:符合条件，false：不符合条件
     */
    boolean doFilter(ProjectWorkTime projectWorkTime);
}
