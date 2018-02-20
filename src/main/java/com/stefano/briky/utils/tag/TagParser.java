package com.stefano.briky.utils.tag;

import com.stefano.briky.model.Tags;

import java.util.List;

public interface TagParser {

    List<Tags> parse(String message);

}
