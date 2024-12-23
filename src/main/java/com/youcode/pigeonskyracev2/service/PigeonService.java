package com.youcode.pigeonskyracev2.service;


import com.youcode.pigeonskyracev2.dto.pigeon.PigeonRequestDTO;
import com.youcode.pigeonskyracev2.dto.pigeon.PigeonResponseDTO;

public interface PigeonService {
    PigeonResponseDTO createPigeon(PigeonRequestDTO pigeonRequestDTO);
}
