package com.huertos.parcel_composite_service.controller;

import com.huertos.parcel_composite_service.dto.ParcelDetailResponse;
import com.huertos.parcel_composite_service.exception.ResourceNotFoundException;
import com.huertos.parcel_composite_service.exception.ServiceUnavailableException;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parcels")
@Validated
public class ParcelCompositeController {

    private final RestClient client;

    public ParcelCompositeController(RestClient parcelClient) {
        this.client = parcelClient;
    }

    @GetMapping("/{id}")
    public ParcelDetailResponse detail(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{24}$",
                     message = "El id debe tener 24 caracteres hexadecimales")
            String id) {
        try {
            Object parcel = client.get()
                    .uri("/api/v1/parcels/{id}", id)
                    .retrieve()
                    .body(Object.class);

            // Estado actual (simulado; luego se conectará a los sensores)
            return new ParcelDetailResponse(parcel, "SIN_LECTURAS_RECIENTES", List.of());

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Parcela", id);
        } catch (ResourceAccessException e) {
            throw new ServiceUnavailableException(
                "El servicio de parcelas no está disponible en este momento");
        }
    }
}