package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.repository.UserPersonalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPersonalServiceImpl implements UserPersonalService {

    private final UserPersonalRepository userPersonalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserPersonalServiceImpl(UserPersonalRepository userPersonalRepository, ModelMapper modelMapper) {
        this.userPersonalRepository = userPersonalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserPersonalServiceModel> findAll() {
        return this.userPersonalRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserPersonalServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        this.userPersonalRepository.deleteById(id);
    }
}
