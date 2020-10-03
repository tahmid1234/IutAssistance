package com.example.iutassistant.Server;

import com.example.iutassistant.Model.IModel;

public interface DataBaseHandler {


    public void receive();
    public void send(IModel model);
}
