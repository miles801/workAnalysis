package com.michael.workanalysis.basic;

/**
 * 项目
 *
 * @author Michael
 */
public class Project {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目编号
     */
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(key);
    }

    @Override
    public String toString() {
        return getKey();
    }
}
