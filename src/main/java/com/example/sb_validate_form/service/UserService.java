package com.example.sb_validate_form.service;

import com.example.sb_validate_form.model.User;
import com.example.sb_validate_form.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }
}
