package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.orders;
import com.example.OrderCoffeeBE.Service.OrderService;
import com.example.OrderCoffeeBE.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;

    @Override
    public List<orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public orders createOrder(orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public orders updateOrder(orders updateOrder) {
        orders currentOrder = ordersRepository.findById(updateOrder.getId()).get();
        if(currentOrder != null) {
            currentOrder.setId(updateOrder.getId());
            currentOrder.setTable_id(updateOrder.getTable_id());
            currentOrder.setTotal_amount(updateOrder.getTotal_amount());
            currentOrder.setStatus(updateOrder.getStatus());
            orders save = ordersRepository.save(currentOrder);
        }
        return updateOrder;
    }

    @Override
    public void deleteOrder(int id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public orders findById(int id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));
    }
}
