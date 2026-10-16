package parcel_service.dto;

import parcel_service.model.Parcel;
import java.time.Instant;

public record ParcelResponse(String id, String name, double latitude, double longitude,
                             String soilType, String ownerOrLead, Instant createdAt) {

    public static ParcelResponse from(Parcel p) {
        return new ParcelResponse(p.getId(), p.getName(),
                p.getLocation().latitude(), p.getLocation().longitude(),
                p.getSoilType().name(), p.getOwnerOrLead(), p.getCreatedAt());
    }
}