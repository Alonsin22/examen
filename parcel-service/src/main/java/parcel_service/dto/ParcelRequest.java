package parcel_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record ParcelRequest(

    @NotBlank(message = "El nombre de la parcela es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name,

    @NotNull(message = "La ubicación es obligatoria")
    @Valid
    LocationRequest location,

    @NotBlank(message = "El tipo de suelo es obligatorio")
    @Pattern(regexp = "(?i)arcilloso|limoso|arenoso",
             message = "El tipo de suelo debe ser: arcilloso, limoso o arenoso")
    String soilType,

    @NotBlank(message = "El responsable es obligatorio")
    @Size(min = 3, max = 100, message = "El responsable debe tener entre 3 y 100 caracteres")
    String ownerOrLead
) {
    public record LocationRequest(
        @NotNull(message = "La latitud es obligatoria")
        @DecimalMin(value = "-90.0", message = "La latitud mínima es -90")
        @DecimalMax(value = "90.0", message = "La latitud máxima es 90")
        Double latitude,

        @NotNull(message = "La longitud es obligatoria")
        @DecimalMin(value = "-180.0", message = "La longitud mínima es -180")
        @DecimalMax(value = "180.0", message = "La longitud máxima es 180")
        Double longitude
    ) {}
}