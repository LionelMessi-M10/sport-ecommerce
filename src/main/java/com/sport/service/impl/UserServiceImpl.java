package com.sport.service.impl;

import com.sport.converter.UserConverter;
import com.sport.entity.UserEntity;
import com.sport.model.dto.UserDTO;
import com.sport.repository.UserRepository;
import com.sport.service.UserService;
import jakarta.transaction.Transactional;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Transactional
    @Override
    public void saveUserDTO(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertToUserEntity(userDTO);
        userEntity.setCreatedAt(new Date());
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO findByUsername(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return userConverter.convertToDTO(userEntity);
    }

    @Override
    public UserEntity getByUsername(String userName) {
        return this.userRepository.findByUserName(userName);
    }
}
