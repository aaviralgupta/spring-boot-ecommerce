package com.web.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

  // lombok will generate constructor for only final fields
  private final String orderTrackingNumber;
}
