package com.example.shipmentservice.adapter.persistence;

import com.example.shipmentservice.application.domain.SalesOrderPosition;
import com.example.shipmentservice.application.port.SalesOrderPositionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderPositionInMemoryPersistenceAdapter implements SalesOrderPositionRepository {

  private final List<SalesOrderPosition> salesOrderPositions = new ArrayList<>();

  @Override
  public void save(SalesOrderPosition salesOrderPosition) {
    // remove by id
    salesOrderPositions.removeIf(
        p -> Objects.equals(p.positionItemId(), salesOrderPosition.positionItemId()));
    // add
    salesOrderPositions.add(salesOrderPosition);
  }

  @Override
  public Optional<SalesOrderPosition> findByPositionItemId(String positionItemId) {
    return salesOrderPositions.stream()
        .filter(p -> Objects.equals(p.positionItemId(), positionItemId))
        .findAny();
  }
}
