package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;

import java.util.List;

public interface UserPersonalService {
    UserPersonalServiceModel findUserByUserId(Long id);

    List<UserPersonalServiceModel> findAll();
}
