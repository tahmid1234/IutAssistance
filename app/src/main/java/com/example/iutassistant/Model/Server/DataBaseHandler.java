package com.example.iutassistant.Model.Server;

import com.example.iutassistant.Model.IModel;

public interface DataBaseHandler {


    public void receive();
    public void send(IModel model);
}
