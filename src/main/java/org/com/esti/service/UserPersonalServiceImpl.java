package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.repository.UserPersonalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserPersonalServiceModel findUserByUserId(Long id) {
        return this.userPersonalRepository.findByUserId(id)
                .map(p -> this.modelMapper.map(p, UserPersonalServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("User personal information doesn't found."));

    }
}
