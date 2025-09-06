open module myapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires net.harawata.appdirs;
    requires com.google.gson;
    requires org.apache.commons.lang3;
    requires org.apache.commons.io;
    requires org.tinylog.api;
    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;

    exports myapp;
    exports myapp.controller;
    exports myapp.services;

}
