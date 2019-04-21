package org.com.esti.service;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.service.UserPasswordServiceModel;
import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.models.service.UserServiceModel;
import org.com.esti.repository.UserPersonalRepository;
import org.com.esti.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserPersonalRepository userPersonalRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserPersonalRepository userPersonalRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userPersonalRepository = userPersonalRepository;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());

            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        UserServiceModel userServiceModel1 = this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

        UserPersonal userPersonal = new UserPersonal();
        userPersonal.setUser(user);

        UserPersonalServiceModel personal = this.modelMapper.map(this.userPersonalRepository.saveAndFlush(userPersonal), UserPersonalServiceModel.class);

        return userServiceModel1;
    }

//    @Override
//    public UserServiceModel findUserById(Long id) {
//        return null;
//    }

//    @Override
//    public UserServiceModel findUserByUserName(String name) {
//        return this.userRepository.findByUsername(name)
//                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserPasswordServiceModel editUserPassword(Long id, UserPasswordServiceModel userPasswordServiceModel) {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!this.bCryptPasswordEncoder.matches(userPasswordServiceModel.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password!");
        }

        user.setPassword(!"".equals(userPasswordServiceModel.getNewPassword()) ?
                this.bCryptPasswordEncoder.encode(userPasswordServiceModel.getNewPassword()) :
                user.getPassword());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserPasswordServiceModel.class);
    }

    @Override
    public UserPersonalServiceModel editUserPersonal(Long id, UserPersonalServiceModel userPersonalServiceModel) {

        UserPersonal userPersonal = this.userPersonalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userPersonal.setFirstName(userPersonalServiceModel.getFirstName());
        userPersonal.setMiddleName(userPersonalServiceModel.getMiddleName());
        userPersonal.setLastName(userPersonalServiceModel.getLastName());
        userPersonal.setBirthday(userPersonalServiceModel.getBirthday());
        userPersonal.setEmail(userPersonalServiceModel.getEmail());
        userPersonal.setGender(userPersonalServiceModel.getGender());
        userPersonal.setCardNumber(userPersonalServiceModel.getCardNumber());
        userPersonal.setPrimaryAddress(userPersonalServiceModel.getPrimaryAddress());
        userPersonal.setSecondAddress(userPersonalServiceModel.getSecondAddress());


        return this.modelMapper.map(this.userPersonalRepository.saveAndFlush(userPersonal), UserPersonalServiceModel.class);
    }


}
