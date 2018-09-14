package com.example.abdulrahman.newslist.Events;


import com.example.abdulrahman.newslist.data.entities.Entity;

/**
 * Created by abdulrahman on 25/07/17.
 */

public class DataEvent  implements   Event{

    private  Entity allData;

    public DataEvent( Entity allData) {
        this.allData = allData;
    }

    public Entity getAllData() {
        return allData;
    }

    public void setAllData(Entity allData) {
        this.allData = allData;
    }
}
