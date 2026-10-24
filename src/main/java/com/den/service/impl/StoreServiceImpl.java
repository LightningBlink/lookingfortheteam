package com.den.service.impl;

import com.den.model.Order;
import com.den.service.StoreService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Denis on 19.06.2015.
 */
@Service
public class StoreServiceImpl implements StoreService {
    private final List<Order> receivedOrders = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void registerOrder(Order order) {
        synchronized (this.receivedOrders) {
            Optional<Order> optOrd = this.getReceivedOrder(order.getId());
            if (!optOrd.isPresent()) {
                order.setDate();
                this.receivedOrders.add(order);
            } else{
                Order hasOrder = this.receivedOrders.get(this.receivedOrders.indexOf(optOrd.get()));
                hasOrder.setDate();
                hasOrder.getPorts().clear();
            }

        }
    }

    @Override
    public Optional<Order> getReceivedOrder(String id) {
        synchronized(receivedOrders) {
            return receivedOrders.stream().filter(o -> o.getId().equals(id)).findFirst();
        }
    }

    @Override
    public void removeOrder(String id) {
        Optional<Order> order = getReceivedOrder(id);
        receivedOrders.remove(order.get());
    }

    @Override
    public void checkByPort(String id, int port) {
        Optional<Order> order = getReceivedOrder(id);
        order.get().getPorts().add(port);
    }

    @Scheduled(fixedDelay=1000*60*5)
    public void clearOrders(){
        final Date current = new Date();
        synchronized(receivedOrders) {

            for(int i = 0; i < receivedOrders.size(); i++){

                if(receivedOrders.get(i).getDate().compareTo(DateUtils.addMinutes(current, -5)) == -1) {
                    receivedOrders.remove(i--);
                }
            }
        }
    }
}
