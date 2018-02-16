package com.stefano.briky.json.filter;

import com.stefano.briky.json.EpochGroup;

/**
 * Filter from date to date, tags and group epoch
 */
public class EpochFilter extends TagDateFilter {

    private EpochGroup group;

    public EpochFilter() {
    }

    public EpochFilter(SeriesFilter filter, SerieFilter serie) {
        this.setFrom(filter.getFrom());
        this.setTo(filter.getTo());
        this.setGroup(filter.getGroup());
        this.setTags(serie.getTags());
    }

    public EpochGroup getGroup() {
        return group;
    }

    public void setGroup(EpochGroup group) {
        this.group = group;
    }

}
