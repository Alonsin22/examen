package parcel_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "parcels")
public class Parcel {
    @Id
    private String id;
    private String name;
    private Location location;
    private SoilType soilType;
    private String ownerOrLead;
    private Instant createdAt;

    public Parcel() {}

    public Parcel(String name, Location location, SoilType soilType, String ownerOrLead) {
        this.name = name;
        this.location = location;
        this.soilType = soilType;
        this.ownerOrLead = ownerOrLead;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Location getLocation() { return location; }
    public SoilType getSoilType() { return soilType; }
    public String getOwnerOrLead() { return ownerOrLead; }
    public Instant getCreatedAt() { return createdAt; }
}