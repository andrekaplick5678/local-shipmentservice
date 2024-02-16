package com.example.shipmentservice.adapter.messaging.output;

import com.example.shipmentservice.application.domain.Shipment;
import com.example.shipmentservice.application.port.ShipmentAnnouncedPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShipmentAnnouncedLogPublisher implements ShipmentAnnouncedPublisher {

  private final ObjectMapper objectMapper;

  public ShipmentAnnouncedLogPublisher(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void publish(Shipment shipment) {
    try {
      log.info("New shipment: {}", objectMapper.writeValueAsString(shipment));
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("JSON", e);
    }
  }
}
