package com.web.ecommerce.dto;

import com.web.ecommerce.entity.Address;
import com.web.ecommerce.entity.Customer;
import com.web.ecommerce.entity.Order;
import com.web.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
