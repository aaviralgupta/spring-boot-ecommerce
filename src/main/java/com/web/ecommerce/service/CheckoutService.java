package com.web.ecommerce.service;

import com.web.ecommerce.dto.Purchase;
import com.web.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
