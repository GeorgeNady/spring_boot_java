package com.george.demo.springdemo.springdemo.user;

import com.george.demo.springdemo.springdemo.models.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response.ResList<User> getUsers() {
        List<User> users = userRepository.findAll();
        return new Response.ResList<>(true, users, "data fetched successfully!", users.size());
    }

    public Response.ResSingle<User> insertUsers(@NotNull User user) {
        Optional<User> studentByEmail = userRepository.findUserByEmail(user.getEmail());

        if (studentByEmail.isPresent()) {
            return new Response.ResSingle<>(true, null, "user saved successfully!");
        }

        User savedUser = userRepository.save(user);

        return new Response.ResSingle<>(true, savedUser, "user saved successfully!");
    }

    public Response.ResSingle<User> findUserById(Long id) {
        User user = getUserObjById(id);
        return new Response.ResSingle<>(true, user, "user fetched successfully!");
    }

    public Response.ResSingle<User> deleteUser(@NotNull Long id) {
        User user = getUserObjById(id);
        userRepository.deleteById(id);
        return new Response.ResSingle<>(true, user, "user deleted successfully!");
    }

    @Transactional
    public Response.ResSingle<User> updateUser(Long id, String name, String phone) {
        // get
        User user = getUserObjById(id);

        // prepare
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (phone != null && phone.length() == 11 && !Objects.equals(user.getPhone(), phone)) {
            user.setName(name);
        }

        // update
        // User newUser = userRepository.save(user);

        // response
        return new Response.ResSingle<>(true, user, "user updated successfully!");
    }

    private User getUserObjById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("user with id " + id + " dose not exists"));
    }

    private User getUserObjByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new IllegalStateException("user with email " + email + " dose not exists"));
    }

}
