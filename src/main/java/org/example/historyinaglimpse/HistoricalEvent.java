package org.example.historyinaglimpse;
record HistoricalEvent(String title, int year, int lane, String description, String source,
                       String displayDate, String illustrationSheet, int illustrationCell) {
    HistoricalEvent(String title, int year, int lane, String description, String source) {
        this(title, year, lane, description, source, null, "historical-vignettes.png", -1);
    }
    String date() {
        return displayDate != null ? displayDate : Math.abs(year) + (year < 0 ? " BCE" : " CE");
    }
}
