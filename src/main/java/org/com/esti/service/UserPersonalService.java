package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;

import java.util.List;

public interface UserPersonalService {

    List<UserPersonalServiceModel> findAll();

    void deleteUserById(Long id);
}
