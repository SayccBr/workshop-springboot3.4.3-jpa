package org.saycc.springboot.services;

import org.saycc.springboot.entities.User;
import org.saycc.springboot.repositories.UserRepository;
import org.saycc.springboot.services.exceptions.DatabaseException;
import org.saycc.springboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User user) {
        User entity = userRepository.getReferenceById(id);

        updateData(entity, user);

        return userRepository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
