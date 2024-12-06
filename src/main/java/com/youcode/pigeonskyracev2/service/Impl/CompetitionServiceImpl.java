package com.youcode.pigeonskyracev2.service.Impl;
import com.youcode.pigeonskyracev2.dto.competition.CompetitionRequestDTO;
import com.youcode.pigeonskyracev2.dto.competition.CompetitionResponseDTO;
import com.youcode.pigeonskyracev2.entity.Competition;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.mapper.CompetitionMapper;
import com.youcode.pigeonskyracev2.repository.CompetionRepository;
import com.youcode.pigeonskyracev2.repository.UserRepository;
import com.youcode.pigeonskyracev2.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;
    private final UserRepository userRepository;
    @Override
    public CompetitionResponseDTO createCompetition(CompetitionRequestDTO competitionRequestDTO) {
        User user = userRepository.findById(competitionRequestDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Competition competition = competitionMapper.toEntity(competitionRequestDTO);

        competition.setUser(user);

        competition = competitionRepository.save(competition);

        return competitionMapper.toResponse(competition);
    }



}
