package com.stefano.briky.json.filter;

import com.stefano.briky.json.EpochGroup;

import java.util.List;

public class SeriesFilter extends DateRangeFilter {

    private EpochGroup group;
    private List<SerieFilter> series;

    public EpochGroup getGroup() {
        return group;
    }

    public void setGroup(EpochGroup group) {
        this.group = group;
    }

    public List<SerieFilter> getSeries() {
        return series;
    }

    public void setSeries(List<SerieFilter> series) {
        this.series = series;
    }
}
