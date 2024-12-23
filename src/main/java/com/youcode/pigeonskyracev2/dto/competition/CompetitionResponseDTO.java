package com.youcode.pigeonskyracev2.dto.competition;


import com.youcode.pigeonskyracev2.dto.pigeon.PigeonResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record CompetitionResponseDTO(
        Long id,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        double latitudeGPS,
        double longitudeGPS,
        int pigeonCount
) {}
