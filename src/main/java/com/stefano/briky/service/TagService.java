package com.stefano.briky.service;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.TagRepository;
import com.stefano.briky.utils.TagSlugger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagSlugger tagSlugger;

    public Tags createIfNotExists(Tags tag, LoggedUser principal) {

        String slug = tagSlugger.calculateSlug(tag);
        Tags dbTag = tagRepository.findBySlugAndUser(slug, principal.getId());

        if(dbTag != null) {
            return dbTag;
        } else {
            tag.setSlug(slug);
            tag.setUserId(principal.getId());
            tagRepository.save(tag);

            return tag;
        }
    }

    public List<Tags> createIfNotExists(List<Tags> tags, LoggedUser principal) {
        List<Tags> checkedTags = new ArrayList<>();

        for (Tags tag : tags) {
           checkedTags.add(createIfNotExists(tag, principal));
        }

        return checkedTags;
    }


    public Integer countTagUsage(int tagId) {
        return tagRepository.countById(tagId);
    }
}
