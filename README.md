# Shipment Service

## test locally

```bash
## start application
./gradlew bootRun
```

```bash
## create destination folder
mkdir events

## publish events
cp eventCopySource/salesOrderCreatedEvent_01.json events/input.msg.txt

## publish events
cp eventCopySource/shipmentCreatedEvent_01.json events/input.msg.txt
```

