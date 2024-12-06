package com.youcode.pigeonskyracev2.service.Impl;
import com.youcode.pigeonskyracev2.dto.pigeon.PigeonRequestDTO;
import com.youcode.pigeonskyracev2.dto.pigeon.PigeonResponseDTO;
import com.youcode.pigeonskyracev2.entity.Competition;
import com.youcode.pigeonskyracev2.entity.Pigeon;
import com.youcode.pigeonskyracev2.entity.User;
import com.youcode.pigeonskyracev2.mapper.PigeonMapper;
import com.youcode.pigeonskyracev2.repository.CompetionRepository;
import com.youcode.pigeonskyracev2.repository.PigeonRepository;
import com.youcode.pigeonskyracev2.repository.UserRepository;
import com.youcode.pigeonskyracev2.service.PigeonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PigeonServiceImpl implements PigeonService {

    private final PigeonRepository pigeonRepository;
    private final UserRepository userRepository;
    private final CompetionRepository competitionRepository;
    private final PigeonMapper pigeonMapper;

    @Override
    public PigeonResponseDTO createPigeon(PigeonRequestDTO pigeonRequestDTO) {
        User user = userRepository.findById(pigeonRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Competition competition = null;
        if (pigeonRequestDTO.getCompetitionId() != null) {
            competition = competitionRepository.findById(pigeonRequestDTO.getCompetitionId())
                    .orElseThrow(() -> new RuntimeException("Competition not found"));
        }

        Pigeon pigeon = pigeonMapper.toEntity(pigeonRequestDTO);
        pigeon.setUser(user);

        if (competition != null) {
            pigeon.setCompetition(competition);
        }

        Pigeon savedPigeon = pigeonRepository.save(pigeon);

        return pigeonMapper.toResponse(savedPigeon);
    }
}
