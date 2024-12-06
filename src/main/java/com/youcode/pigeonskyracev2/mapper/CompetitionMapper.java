package com.youcode.pigeonskyracev2.mapper;


import com.youcode.pigeonskyracev2.dto.competition.CompetitionRequestDTO;
import com.youcode.pigeonskyracev2.dto.competition.CompetitionResponseDTO;
import com.youcode.pigeonskyracev2.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")

public interface CompetitionMapper {
    Competition toEntity(CompetitionRequestDTO competitionRequestDTO);
    CompetitionResponseDTO toResponse(Competition competition);
}
