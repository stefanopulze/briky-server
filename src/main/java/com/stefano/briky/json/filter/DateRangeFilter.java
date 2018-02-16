package com.stefano.briky.json.filter;

import java.time.LocalDateTime;

/**
 * Filter from date to date.
 */
public class DateRangeFilter {

    private LocalDateTime from;
    private LocalDateTime to;

    public DateRangeFilter() {
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

    @Override
    public String toString() {
        return "DateRangeFilter{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

}
