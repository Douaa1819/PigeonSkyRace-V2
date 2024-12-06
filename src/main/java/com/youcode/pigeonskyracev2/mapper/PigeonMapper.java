package com.youcode.pigeonskyracev2.mapper;

import com.youcode.pigeonskyracev2.dto.pigeon.PigeonRequestDTO;
import com.youcode.pigeonskyracev2.dto.pigeon.PigeonResponseDTO;
import com.youcode.pigeonskyracev2.entity.Pigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PigeonMapper {
    PigeonResponseDTO toResponse(Pigeon pigeon);
    Pigeon toEntity(PigeonRequestDTO pigeonRequestDTO);
}


