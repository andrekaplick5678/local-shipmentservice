package com.example.shipmentservice.application.domain;

import java.util.List;
import lombok.Builder;

public record Shipment(
    String shipmentId,
    String shippingDate,
    List<SalesOrderPosition> salesOrderPositions
) {

  @Builder
  public Shipment {
  }

  private ShipmentBuilder copyBuilder() {
    return Shipment.builder()
        .shipmentId(shipmentId)
        .shippingDate(shippingDate)
        .salesOrderPositions(salesOrderPositions);
  }

  public Shipment withPositions(List<SalesOrderPosition> salesOrderPositions) {
    return copyBuilder()
        .salesOrderPositions(salesOrderPositions)
        .build();
  }
}
