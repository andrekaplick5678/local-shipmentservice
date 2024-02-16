package com.example.shipmentservice.application.service;

import com.example.shipmentservice.application.domain.SalesOrderPosition;
import com.example.shipmentservice.application.domain.Shipment;
import com.example.shipmentservice.application.port.SalesOrderPositionRepository;
import com.example.shipmentservice.application.port.ShipmentAnnouncedPublisher;
import com.example.shipmentservice.application.port.ShipmentCreatedHandler;
import com.example.shipmentservice.application.port.ShipmentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ShipmentCreatedService implements ShipmentCreatedHandler {

  private final SalesOrderPositionRepository salesOrderPositionRepository;
  private final ShipmentRepository shipmentRepository;
  private final ShipmentAnnouncedPublisher shipmentAnnouncedPublisher;

  public ShipmentCreatedService(SalesOrderPositionRepository salesOrderPositionRepository,
      ShipmentRepository shipmentRepository,
      ShipmentAnnouncedPublisher shipmentAnnouncedPublisher) {
    this.salesOrderPositionRepository = salesOrderPositionRepository;
    this.shipmentRepository = shipmentRepository;
    this.shipmentAnnouncedPublisher = shipmentAnnouncedPublisher;
  }

  @Override
  public void shipmentCreated(ShipmentData data) {
    // find all salesOrderPositions
    List<SalesOrderPosition> positions = new ArrayList<>();
    for (PositionData position : data.positions()) {
      SalesOrderPosition salesOrderPosition = salesOrderPositionRepository.findByPositionItemId(
              position.positionItemId())
          .orElseThrow();
      positions.add(salesOrderPosition);
    }

    // create shipment
    Shipment shipment = shipmentRepository.save(Shipment.builder()
        .shipmentId(data.shipmentId())
        .shippingDate(data.shippingDate())
        .salesOrderPositions(positions)
        .build());

    // publish shipment
    shipmentAnnouncedPublisher.publish(shipment);
  }

}
