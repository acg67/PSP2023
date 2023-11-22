package com.example.demo3;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class ServiceOne extends Service<Integer> {

    Integer progress = 0;
    Label label;


    public ServiceOne(Label label) {
        this.label = label;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {

                for (int i = 0; i < 5; i++) {
                    try {
                        progress = i;
                        Thread.sleep(1000);
                        updateMessage("" + progress + "");

                    } catch (Exception ex) {
                    }
                }
                return progress;
            }
        };
    }
}
