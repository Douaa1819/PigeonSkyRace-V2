package com.youcode.pigeonskyracev2.dto.competition;

import java.time.LocalDateTime;

public record CompetitionRequestDTO(
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        double latitudeGPS,
        double longitudeGPS,
        int pigeonCount
) {}
