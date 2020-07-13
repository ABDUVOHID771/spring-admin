package com.example.springloan.service;

import com.example.springloan.dao.domain.User;
import com.example.springloan.dao.repository.UserRepository;
import com.google.common.base.Strings;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserRepository, User> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public User update(User updating) throws NotFoundException {

        User user = getRepository().findById(updating.getId()).orElseThrow(() -> new NotFoundException("Not Found !"));

        if (!Strings.isNullOrEmpty(updating.getPhone())) {
            user.setPhone(updating.getPhone());
        }
        if (!Strings.isNullOrEmpty(updating.getFullname())) {
            user.setFullname(updating.getFullname());
        }
        if (!Strings.isNullOrEmpty(updating.getCodeClient())) {
            user.setCodeClient(updating.getCodeClient());
        }
        user.setStatus(updating.isStatus());

        User updated = getRepository().save(user);

        return updated;

    }



}