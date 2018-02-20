package com.stefano.briky.service;

import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.TagRepository;
import com.stefano.briky.utils.SecurityUtils;
import com.stefano.briky.utils.slug.Slug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    Slug slugger;


    public Tags createIfNotExists(Tags tag, int userId) {

        String slug = slugger.from(tag);
        Tags dbTag = tagRepository.findBySlugAndUserId(slug, userId);

        if (dbTag != null) {
            return dbTag;
        } else {
            tag.setSlug(slug);
            tag.setUserId(userId);
            tagRepository.save(tag);

            return tag;
        }
    }

    public List<Tags> createIfNotExists(List<Tags> tags, int userId) {
        List<Tags> checkedTags = new ArrayList<>();

        for (Tags tag : tags) {
            checkedTags.add(createIfNotExists(tag, userId));
        }

        return checkedTags;
    }

    public Integer countTagUsage(int tagId) {
        return tagRepository.countById(tagId);
    }


    public List<Tags> getOrCreate(List<Tags> tags) {
        int userId = SecurityUtils.getUser().getId();

        return tags.stream()
                .map(tag -> {
                    String slug = slugger.from(tag);

                    Tags findTag = tagRepository.findBySlugAndUserId(slug, userId);

                    if (null == findTag) {
                        tag.setUserId(userId);
                        tag.setSlug(slug);
                        findTag = tagRepository.save(tag);
                    }

                    return findTag;
                })
                .collect(Collectors.toList());
    }
}
