package com.den.model.wrapper;

/**
 * Created by Denis on 11.11.2015.
 */
public class GameTwich {

    private String name;

    private String filePath;

    public GameTwich(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
