package com.example.shipmentservice.adapter.messaging.input;

import com.example.shipmentservice.application.port.ShipmentCreatedHandler;
import com.example.shipmentservice.application.port.ShipmentCreatedHandler.PositionData;
import com.example.shipmentservice.application.port.ShipmentCreatedHandler.ShipmentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShipmentCreatedEventHandler {

  private final ObjectMapper objectMapper;
  private final ShipmentCreatedHandler shipmentCreatedHandler;

  public ShipmentCreatedEventHandler(ObjectMapper objectMapper,
      ShipmentCreatedHandler shipmentCreatedHandler) {
    this.objectMapper = objectMapper;
    this.shipmentCreatedHandler = shipmentCreatedHandler;
  }

  public void onShipmentCreatedEvent(String event) throws Exception {
    ShipmentDto dto = objectMapper.readValue(event, ShipmentDto.class);

    shipmentCreatedHandler.shipmentCreated(ShipmentData.builder()
        .shipmentId(dto.shipmentId())
        .shippingDate(dto.shippingDate())
        .positions(dto.positions().stream()
            .map(p -> PositionData.builder()
                .positionItemId(p.positionItemId())
                .build())
            .toList())
        .build());
  }

  private record ShipmentDto(
      String shipmentId,
      String shippingDate,
      List<PositionDto> positions
  ) {

  }

  private record PositionDto(
      String positionItemId
  ) {

  }
}
