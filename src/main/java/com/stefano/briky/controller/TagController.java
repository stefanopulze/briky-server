package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.model.Tags;
import com.stefano.briky.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public TagJson createTag(@AuthenticationPrincipal LoggedUser principal, @RequestBody TagJson json) {
        Tags tag = modelMapper.map(json, Tags.class);

        tag = tagService.createIfNotExists(tag, principal);

        return modelMapper.map(tag, TagJson.class);
    }
}
