module client {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;
    opens client.controllers to javafx.fxml;
    exports client.controllers;
    exports client;
    exports client.global;
}