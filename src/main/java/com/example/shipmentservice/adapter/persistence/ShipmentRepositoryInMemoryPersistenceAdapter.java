package com.example.shipmentservice.adapter.persistence;

import com.example.shipmentservice.application.domain.Shipment;
import com.example.shipmentservice.application.port.ShipmentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class ShipmentRepositoryInMemoryPersistenceAdapter implements ShipmentRepository {

  private final List<Shipment> shipments = new ArrayList<>();

  @Override
  public Shipment save(Shipment shipment) {
    // remove by id
    shipments.removeIf(p -> Objects.equals(p.shipmentId(), shipment.shipmentId()));
    // add
    shipments.add(shipment);
    return shipment;
  }
}
