package com.george.demo.springdemo.springdemo.user;

import com.george.demo.springdemo.springdemo.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Response.ResList<User> getUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping(path = "{user_id}")
    public Response.ResSingle<User> findUserById(@PathVariable("user_id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public Response.ResSingle<User> insertUser(@RequestBody User user) {
        return userService.insertUsers(user);
    }

    @DeleteMapping(path = "{user_id}")
    public Response.ResSingle<User> deleteUser(@PathVariable("user_id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping(path = "{user_id}")
    public Response.ResSingle<User> updateUser(
            @PathVariable("user_id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone
    ) {
        return userService.updateUser(id,name,phone);
    }

}
