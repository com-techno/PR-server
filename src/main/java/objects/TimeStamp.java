package objects;


import java.util.Calendar;
import java.util.TimeZone;

public class TimeStamp {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int timeZone;

    public TimeStamp(String parseFrom) {
        String zone = parseFrom.substring(parseFrom.length() - 6);
        boolean sign = zone.charAt(0) == '+';
        parseFrom = parseFrom.substring(0, parseFrom.length() - 6);
        String[] zoneArr = zone.substring(1).split(":");
        this.timeZone = (Integer.parseInt(zoneArr[0]) * 60 + Integer
                .parseInt(zoneArr[1])) * (sign ? 1 : -1);
        parseFrom = parseFrom.replace(":", "-").replace("T", "-");
        String[] timeFields = parseFrom.split("-");
        this.year = Integer.parseInt(timeFields[0]);
        this.month = Integer.parseInt(timeFields[1]);
        this.day = Integer.parseInt(timeFields[2]);
        this.hour = Integer.parseInt(timeFields[3]);
        this.minute = Integer.parseInt(timeFields[4]);
        this.second = Integer.parseInt(timeFields[5]);
    }

    public TimeStamp(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        this.timeZone = 0;
    }

    private static String getConstLength(int length, String target) {
        StringBuilder output = new StringBuilder();
        if (target.length() >= length) return target;
        else {
            for (int i = 0; i < length - target.length(); i++)
                output.append("0");
            output.append(target);
            return output.toString();
        }
    }

    @Override
    public String toString() {
        return getConstLength(4, String.valueOf(year))
                + "-" + getConstLength(2, String.valueOf(month))
                + "-" + getConstLength(2, String.valueOf(day))
                + "T"
                + getConstLength(2, String.valueOf(hour))
                + ":" + getConstLength(2, String.valueOf(minute))
                + ":" + getConstLength(2, String.valueOf(second))
                + timezoneToString();
    }

    private String timezoneToString() {
        if (timeZone == 0) return "";
        return (timeZone > 0 ? "+" : "-")
                + getConstLength(2, String.valueOf(Math.abs(timeZone) / 60))
                + ":" + getConstLength(2, String
                .valueOf(timeZone % 60));
    }
}
