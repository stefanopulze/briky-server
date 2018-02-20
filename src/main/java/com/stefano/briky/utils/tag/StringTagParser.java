package com.stefano.briky.utils.tag;

import com.stefano.briky.model.Tags;
import com.stefano.briky.utils.slug.Slug;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringTagParser implements TagParser {

    private static final String PATTERN = "#([\\w-]+)";

    private Pattern regex;

    @Override
    public List<Tags> parse(String message) {
        List<Tags> tags = new ArrayList<>();

        if(StringUtils.isNotEmpty(message)) {

            Matcher matcher = buildMatcher(message);

            while (matcher.find()) {
                tags.add(new Tags(matcher.group(1)));
            }

        }

        return tags;
    }

    private Matcher buildMatcher(String message) {
        if(null == regex) {
            regex = Pattern.compile(PATTERN);
        }

        return regex.matcher(message);
    }

}
