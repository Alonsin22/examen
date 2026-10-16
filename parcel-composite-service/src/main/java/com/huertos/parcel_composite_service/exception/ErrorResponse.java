package com.huertos.parcel_composite_service.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(Instant timestamp, int status, String error,
                            String message, String path, List<String> details) {}