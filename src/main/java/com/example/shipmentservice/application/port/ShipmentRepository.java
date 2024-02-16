package com.example.shipmentservice.application.port;

import com.example.shipmentservice.application.domain.Shipment;

public interface ShipmentRepository {

  Shipment save(Shipment shipment);
}
