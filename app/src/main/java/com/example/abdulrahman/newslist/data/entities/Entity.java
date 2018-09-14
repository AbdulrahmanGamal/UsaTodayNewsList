package com.example.abdulrahman.newslist.data.entities;

import java.io.Serializable;

/**
 * Created by mohamedyoussef on 24/07/17.
 */

abstract public class Entity implements Serializable {
    private boolean error=false;
    private String status;
    private int pageCount;
    public boolean getError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


}
