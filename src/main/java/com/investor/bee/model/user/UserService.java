package com.investor.bee.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public DataUserDto save(CreateUserDto user) {
        User newUser = userRepository.save(new User(user));
        return new DataUserDto(newUser.getId(), newUser.getName(), newUser.getEmail());

    }

    public Page<DataUserDto> read (Pageable pageable) {
        return userRepository.findAll(pageable).map(
                x -> new DataUserDto(x.getId(), x.getName(), x.getEmail())
        );
    }

    @Transactional
    public UpdateUserDto update(UpdateUserDto user) {
        if(!userRepository.existsById(user.id())) {
            throw new RuntimeException("User with id " + user.id() + " does not exist");
        }

        User userToUpdate = userRepository.getReferenceById(user.id());

        userToUpdate.updateData(user);

        return new UpdateUserDto(userToUpdate);
    }
}