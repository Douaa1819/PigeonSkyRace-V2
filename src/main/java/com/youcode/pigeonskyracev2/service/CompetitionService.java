package com.youcode.pigeonskyracev2.service;


import com.youcode.pigeonskyracev2.dto.competition.CompetitionRequestDTO;
import com.youcode.pigeonskyracev2.dto.competition.CompetitionResponseDTO;

public interface CompetitionService {
    CompetitionResponseDTO createCompetition(CompetitionRequestDTO competitionRequestDTO);

}
