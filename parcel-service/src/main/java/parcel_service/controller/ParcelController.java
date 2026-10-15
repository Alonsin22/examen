package parcel_service.controller;

import parcel_service.dto.ParcelRequest;
import parcel_service.dto.ParcelResponse;
import parcel_service.service.ParcelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parcels")
@Validated   // necesario para validar @PathVariable
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParcelResponse> create(@Valid @RequestBody ParcelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<ParcelResponse> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ParcelResponse detail(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "El id debe tener 24 caracteres hexadecimales")
            String id) {
        return service.findById(id);
    }
}