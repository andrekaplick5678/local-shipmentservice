package com.example.shipmentservice.application.port;

import java.util.List;
import lombok.Builder;

public interface SalesOrderCreatedHandler {

  void salesOrderCreated(SalesOrderData data);

  record SalesOrderData(
      String salesOrderId,
      List<SalesOrderPositionData> positionItems
  ) {

    @Builder
    public SalesOrderData {
    }
  }

  record SalesOrderPositionData(
      String positionItemId,
      String productId
  ) {

    @Builder
    public SalesOrderPositionData {
    }
  }
}
