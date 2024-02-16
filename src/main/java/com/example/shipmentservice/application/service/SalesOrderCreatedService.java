package com.example.shipmentservice.application.service;

import com.example.shipmentservice.application.domain.SalesOrderPosition;
import com.example.shipmentservice.application.port.SalesOrderCreatedHandler;
import com.example.shipmentservice.application.port.SalesOrderPositionRepository;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderCreatedService implements SalesOrderCreatedHandler {

  private final SalesOrderPositionRepository salesOrderPositionRepository;

  public SalesOrderCreatedService(SalesOrderPositionRepository salesOrderPositionRepository) {
    this.salesOrderPositionRepository = salesOrderPositionRepository;
  }

  @Override
  public void salesOrderCreated(SalesOrderData data) {
    for (SalesOrderPositionData positionData : data.positionItems()) {
      salesOrderPositionRepository.save(SalesOrderPosition.builder()
          .positionItemId(positionData.positionItemId())
          .salesOrderId(data.salesOrderId())
          .productId(positionData.productId())
          .build());
    }
  }
}
