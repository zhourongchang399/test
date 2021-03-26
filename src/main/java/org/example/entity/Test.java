package org.example.entity;

public class Test {

    private Integer id;

    private String testStr;

    public Test() {
    }

    public Test(Integer id, String testStr) {
        this.id = id;
        this.testStr = testStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}
