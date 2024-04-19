package com.sport.service;

import com.sport.entity.UserEntity;
import com.sport.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUserDTO(UserDTO userDTO);
    UserDTO findByUsername(String userName);
    UserEntity getByUsername(String userName);
}
