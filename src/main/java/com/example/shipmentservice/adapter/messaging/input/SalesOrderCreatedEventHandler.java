package com.example.shipmentservice.adapter.messaging.input;

import com.example.shipmentservice.application.port.SalesOrderCreatedHandler;
import com.example.shipmentservice.application.port.SalesOrderCreatedHandler.SalesOrderData;
import com.example.shipmentservice.application.port.SalesOrderCreatedHandler.SalesOrderPositionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesOrderCreatedEventHandler {

  private final ObjectMapper objectMapper;
  private final SalesOrderCreatedHandler salesOrderCreatedHandler;

  public SalesOrderCreatedEventHandler(ObjectMapper objectMapper,
      SalesOrderCreatedHandler salesOrderCreatedHandler) {
    this.objectMapper = objectMapper;
    this.salesOrderCreatedHandler = salesOrderCreatedHandler;
  }

  public void onSalesOrderCreatedEvent(String event) throws Exception {
    SalesOrderDto dto = objectMapper.readValue(event, SalesOrderDto.class);

    salesOrderCreatedHandler.salesOrderCreated(SalesOrderData.builder()
        .salesOrderId(dto.salesOrderId())
        .positionItems(dto.positionItems().stream()
            .map(p -> SalesOrderPositionData.builder()
                .positionItemId(p.positionItemId())
                .productId(p.productId())
                .build())
            .toList())
        .build());
  }

  private record SalesOrderDto(
      String salesOrderId,
      List<SalesOrderPositionDto> positionItems
  ) {

  }

  private record SalesOrderPositionDto(
      String positionItemId,
      String productId
  ) {

  }
}
