package com.huertos.parcel_composite_service.dto;

import java.util.List;

public record ParcelDetailResponse(Object parcel, String currentStatus, List<String> alerts) {}