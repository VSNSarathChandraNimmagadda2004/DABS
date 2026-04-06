package com.example.DABS.dto.common;

public class AvailabilityDTO {
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public AvailabilityDTO() {}

    public AvailabilityDTO(String dayOfWeek, String startTime, String endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static AvailabilityDTOBuilder builder() {
        return new AvailabilityDTOBuilder();
    }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public static class AvailabilityDTOBuilder {
        private String dayOfWeek;
        private String startTime;
        private String endTime;

        public AvailabilityDTOBuilder dayOfWeek(String d) { this.dayOfWeek = d; return this; }
        public AvailabilityDTOBuilder startTime(String s) { this.startTime = s; return this; }
        public AvailabilityDTOBuilder endTime(String e) { this.endTime = e; return this; }
        public AvailabilityDTO build() { return new AvailabilityDTO(dayOfWeek, startTime, endTime); }
    }
}
