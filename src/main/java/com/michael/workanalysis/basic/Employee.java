package com.michael.workanalysis.basic;

/**
 * 员工
 *
 * @author Michael
 */
public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
