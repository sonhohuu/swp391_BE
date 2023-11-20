package com.FPTU.security.mapper;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.dto.UserDTO;
import com.FPTU.model.User;
import com.FPTU.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User convertToUser(RegistrationRequest registrationRequest);

  User convertToUser(AuthenticatedUserDto authenticatedUserDto);

  AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

  UserDTO convertToUserDto(User user);


}