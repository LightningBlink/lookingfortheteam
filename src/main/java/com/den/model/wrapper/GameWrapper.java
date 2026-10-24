package com.den.model.wrapper;

/**
 * Created by Denis on 05.11.2015.
 */
public class GameWrapper {

    private Long id;

    private String name;

    private String filePath;

    public GameWrapper(Long id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
