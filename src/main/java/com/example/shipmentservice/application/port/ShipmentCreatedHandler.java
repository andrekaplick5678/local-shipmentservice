package com.example.shipmentservice.application.port;

import java.util.List;
import lombok.Builder;

public interface ShipmentCreatedHandler {

  void shipmentCreated(ShipmentData data);

  record ShipmentData(
      String shipmentId,
      String shippingDate,
      List<PositionData> positions
  ) {

    @Builder
    public ShipmentData {
    }
  }

  record PositionData(
      String positionItemId
  ) {

    @Builder
    public PositionData {
    }
  }
}
