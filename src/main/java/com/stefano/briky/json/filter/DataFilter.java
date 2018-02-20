package com.stefano.briky.json.filter;

import com.stefano.briky.json.EpochGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro inviato dal client per ricevere i dati
 */
public class DataFilter {

    // date
    private LocalDateTime from;
    private LocalDateTime to;
    // tags
    private List<Integer> tags = new ArrayList<>();
    // group
    private EpochGroup group;
    // pagination
    private int start = 0;
    private int size = 25;
    private List<String> sort = new ArrayList<>();

    public DataFilter() {
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public EpochGroup getGroup() {
        return group;
    }

    public void setGroup(EpochGroup group) {
        this.group = group;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }
}
