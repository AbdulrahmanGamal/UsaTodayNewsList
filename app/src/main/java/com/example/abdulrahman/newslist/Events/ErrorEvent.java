package com.example.abdulrahman.newslist.Events;

/**
 * Created by abdulrahman on 25/07/17.
 */

public class ErrorEvent implements  Event {

    private String massage;




    public ErrorEvent(String massage){

        this.massage = massage;

    }

    public String getErrorMessage() {
        return massage;
    }

    public void setErrorMessage(String errorMessage) {
        this.massage = errorMessage;
    }
}
