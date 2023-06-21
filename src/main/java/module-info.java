module client {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;
    requires jdk.jsobject;
    requires javafx.web;
    requires java.desktop;

    opens mid.data to java.base;
    exports mid.data;
    exports mid.data.processing;
    opens client.controllers to javafx.fxml;
    exports client.controllers;
    exports client;
    exports client.global;
}