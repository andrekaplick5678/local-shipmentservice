package com.example.shipmentservice.adapter.messaging.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageDispatcher {

  private final ObjectMapper objectMapper;

  private final SalesOrderCreatedEventHandler salesOrderCreatedEventHandler;
  private final ShipmentCreatedEventHandler shipmentCreatedEventHandler;

  public MessageDispatcher(ObjectMapper objectMapper,
      SalesOrderCreatedEventHandler salesOrderCreatedEventHandler,
      ShipmentCreatedEventHandler shipmentCreatedEventHandler) {
    this.objectMapper = objectMapper;
    this.salesOrderCreatedEventHandler = salesOrderCreatedEventHandler;
    this.shipmentCreatedEventHandler = shipmentCreatedEventHandler;
  }

  public void onMessage(String event) throws Exception {
    MessageWithHeader messageWithHeader = objectMapper.readValue(event, MessageWithHeader.class);
    String type = "N/A";
    if (messageWithHeader.header() != null && messageWithHeader.header().type() != null) {
      type = messageWithHeader.header().type();
    }

    switch (type) {
      case "salesorder.created" -> salesOrderCreatedEventHandler.onSalesOrderCreatedEvent(event);
      case "shipment.created" -> shipmentCreatedEventHandler.onShipmentCreatedEvent(event);
      default -> throw new IllegalArgumentException("Cannot handle type '" + type + "'.");
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  private record MessageWithHeader(MessageHeader header) {

  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  private record MessageHeader(
      String id,
      String traceId,
      String type
  ) {

  }

}
