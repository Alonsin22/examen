package parcel_service.repository;

import parcel_service.model.Parcel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParcelRepository extends MongoRepository<Parcel, String> {
    boolean existsByNameIgnoreCase(String name);
}