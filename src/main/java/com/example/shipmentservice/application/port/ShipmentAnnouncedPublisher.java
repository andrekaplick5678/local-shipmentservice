package com.example.shipmentservice.application.port;

import com.example.shipmentservice.application.domain.Shipment;

public interface ShipmentAnnouncedPublisher {

  void publish(Shipment shipment);
}
