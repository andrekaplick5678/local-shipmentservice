package com.example.shipmentservice.application.domain;

import lombok.Builder;

public record SalesOrderPosition(
    String salesOrderId,
    String positionItemId,
    String productId
) {

  @Builder
  public SalesOrderPosition {
  }

}
