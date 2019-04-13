package org.com.esti.service;

import org.com.esti.models.service.UserPersonalServiceModel;

public interface UserPersonalService {
    UserPersonalServiceModel findUserByUserId(Long id);
}
