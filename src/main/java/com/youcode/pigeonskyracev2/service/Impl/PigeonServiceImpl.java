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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PigeonServiceImpl implements PigeonService {

    private final PigeonRepository pigeonRepository;
    private final PigeonMapper pigeonMapper;

    @Override
    public PigeonResponseDTO createPigeon(PigeonRequestDTO pigeonRequestDTO) {
        if (pigeonRequestDTO.gender() == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }

        Pigeon pigeon = pigeonMapper.toEntity(pigeonRequestDTO);

        Pigeon savedPigeon = pigeonRepository.save(pigeon);
        return pigeonMapper.toResponse(savedPigeon);
    }

}