package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.UserJson;
import com.stefano.briky.json.UserPasswordJson;
import com.stefano.briky.model.Tags;
import com.stefano.briky.model.Users;
import com.stefano.briky.repository.TagRepository;
import com.stefano.briky.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserJson info(@AuthenticationPrincipal LoggedUser currentUser) {
        Users user = userRepository.getOne(currentUser.getId());
        return modelMapper.map(user, UserJson.class);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public UserJson updateUser(@AuthenticationPrincipal LoggedUser currentUser, @RequestBody UserJson json) {
        Users user = userRepository.getOne(currentUser.getId());

        user.setName(json.getName());
        user.setSurname(json.getSurname());
        user.setEmail(json.getEmail());

        userRepository.save(user);

        return modelMapper.map(user, UserJson.class);
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.PUT)
    public UserJson updateUserPassword(@AuthenticationPrincipal LoggedUser currentUser, @RequestBody UserPasswordJson json) {
        Users user = userRepository.getOne(currentUser.getId());

        String currentPwd = passwordEncoder.encode(json.getCurrentPassword());

        if (!user.getPassword().equals(currentPwd)) {
            // TODO eccezione
        }

        if (!json.getNewPassword().equals(json.getConfirmPassword())) {
            // TODO eccezione
        }

        user.setPassword(json.getNewPassword());
        user.setUpdatedAt(new Date());

        userRepository.save(user);

        return modelMapper.map(user, UserJson.class);
    }


    /**
     * @deprecated Usare TagController/tags
     * @param user
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/user/tags")
    public List<Tags> mostUsedTag(@AuthenticationPrincipal LoggedUser user) {
        return tagRepository.findByUserIdOrderByName(user.getId());
    }
}
