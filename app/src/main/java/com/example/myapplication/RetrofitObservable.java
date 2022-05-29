package com.example.myapplication;

import java.util.Observable;

public class RetrofitObservable extends Observable {

    private static RetrofitObservable instance = null;

    public static RetrofitObservable getInstance() {

        if(instance == null) {

            instance = new RetrofitObservable();

        }

        return instance;
    }

    public void notifyObserverWithResponse(Object response) {

        setChanged();

        notifyObservers(response);

    }

}