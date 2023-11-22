package com.example.demo3;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    ServiceOne serviceOne;
    Service<Integer> serviceTwo;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnTwo;

    @FXML
    private Label lblOne;

    @FXML
    private Label lblTwo;

    @FXML
    private Button btnStop;

    @FXML
    void btnStopOne(ActionEvent event) {
        serviceOne.cancel();
        serviceTwo.cancel();
    }


    @FXML
    void onOneButtonClick(ActionEvent event) {
        btnOne.setDisable(true);
        serviceOne.start();
    }

    @FXML
    void onTwoButtonClick(ActionEvent event) {
        btnTwo.setDisable(true);
        serviceTwo.start();
    }

    public void initialize() {
        serviceOne = new ServiceOne(lblOne);
        serviceOne.setOnSucceeded(ev -> {
            btnOne.setDisable(false);
            serviceOne.reset();
        });
        serviceOne.setOnCancelled(ev -> {
            btnOne.setDisable(false);
            serviceOne.reset();
        });

        serviceOne.setOnFailed(ev -> {
            btnOne.setDisable(false);
            serviceOne.reset();
        });
        lblOne.textProperty().bind(serviceOne.messageProperty());


        serviceTwo = new Service<Integer>() {

            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        int finalI = 0;
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                                finalI = i;
                                int finalI1 = finalI;
                                updateMessage("" + finalI1 + "");
                            } catch (Exception ex) {
                            }
                        }

                        return finalI;
                    }
                };
            }
        };

        serviceTwo.setOnSucceeded(ev -> {
            btnTwo.setDisable(false);
            serviceTwo.reset();
        });
        serviceTwo.setOnCancelled(ev -> {
            btnTwo.setDisable(false);
            serviceTwo.reset();
        });

        serviceTwo.setOnFailed(ev -> {
            btnTwo.setDisable(false);
            serviceTwo.reset();
        });

        lblTwo.textProperty().bind(serviceTwo.messageProperty());

    }
}