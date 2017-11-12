package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.Tag;
import com.stefano.briky.model.Tags;
import com.stefano.briky.model.Users;
import com.stefano.briky.repository.TagRepository;
import com.stefano.briky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public Tags createTag(@AuthenticationPrincipal LoggedUser principal, @RequestBody Tag tag) {

        Tags t = tagRepository.findByName(tag.getName(), principal.getId());

        if (null == t) {
            Users user = userRepository.getOne(principal.getId());

            t = new Tags();
            t.setName(tag.getName());
            user.getTags().add(t);

            tagRepository.save(t);
            userRepository.save(user);
        }

        return t;
    }
}
