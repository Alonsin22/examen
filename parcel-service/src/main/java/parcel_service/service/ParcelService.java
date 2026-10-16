package parcel_service.service;

import parcel_service.dto.ParcelRequest;
import parcel_service.dto.ParcelResponse;
import parcel_service.exception.DuplicateResourceException;
import parcel_service.exception.InvalidInputException;
import parcel_service.exception.ResourceNotFoundException;
import parcel_service.model.Location;
import parcel_service.model.Parcel;
import parcel_service.model.SoilType;
import parcel_service.repository.ParcelRepository;
import parcel_service.util.InputSanitizer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {

    private final ParcelRepository repository;

    public ParcelService(ParcelRepository repository) {
        this.repository = repository;
    }

    public ParcelResponse create(ParcelRequest req) {
        // 1. Sanitizar
        String name  = InputSanitizer.clean(req.name());
        String owner = InputSanitizer.clean(req.ownerOrLead());

        // 2. Validar que no haya quedado vacío tras limpiar
        if (name.isBlank() || owner.isBlank()) {
            throw new InvalidInputException(
                "El nombre o el responsable contienen caracteres no permitidos");
        }

        // 3. Regla de negocio: nombre único
        if (repository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Ya existe una parcela con el nombre '" + name + "'");
        }

        SoilType soil = SoilType.valueOf(req.soilType().toUpperCase());
        Location loc = new Location(req.location().latitude(), req.location().longitude());

        Parcel saved = repository.save(new Parcel(name, loc, soil, owner));
        return ParcelResponse.from(saved);
    }

    public List<ParcelResponse> findAll() {
        return repository.findAll().stream().map(ParcelResponse::from).toList();
    }

    public ParcelResponse findById(String id) {
        return repository.findById(id)
                .map(ParcelResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Parcela", id));
    }
}