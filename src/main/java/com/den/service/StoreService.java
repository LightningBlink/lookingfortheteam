package com.den.service;

import com.den.model.Order;

import java.util.Optional;

/**
 * Created by Denis on 19.06.2015.
 */
public interface StoreService {

    public void registerOrder(Order order);

    public Optional<Order> getReceivedOrder(String id);

    void removeOrder(String id);

    void checkByPort(String id, int port);
}
