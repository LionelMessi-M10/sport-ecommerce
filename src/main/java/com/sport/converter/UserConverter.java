package com.sport.converter;

import com.sport.entity.UserEntity;
import com.sport.model.dto.UserDTO;
import com.sport.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserConverter(ModelMapper modelMapper, RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity convertToUserEntity(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDTO.setEnabled(1);

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        userEntity.setRoleEntities(List.of(roleRepository.findByCode(userDTO.getRole())));

        return userEntity;
    }

    public UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        userDTO.setRole(userEntity.getRoleEntities().get(0).getCode());

        return userDTO;
    }
}
