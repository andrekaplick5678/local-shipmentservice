package com.example.shipmentservice.application.port;

import com.example.shipmentservice.application.domain.SalesOrderPosition;
import java.util.Optional;

public interface SalesOrderPositionRepository {

  void save(SalesOrderPosition salesOrderPosition);

  Optional<SalesOrderPosition> findByPositionItemId(String positionItemId);
}
