package com.example.rohit.kheti;

/**
 * Created by Rohit on 3/20/2018.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class mandilistviewexpand {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> day1 = new ArrayList<String>();
        day1.add("10:00 am to 11:00 am");
        day1.add("1:00 pm to 2:00 pm");
        day1.add("4:00 pm to 5:00 pm");

        List<String> day2 = new ArrayList<String>();
        day2.add("11:00 am to 12:00 pm");
        day2.add("2:00 pm to 3:00 pm");
        day2.add("5:00 pm to 6:00 pm");

        List<String> day3 = new ArrayList<String>();
        day3.add("10:00 am to 11:00 am");
        day3.add("1:00 pm to 2:00 pm");
        day3.add("4:00 pm to 5:00 pm");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dayy1 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dayy2 = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dayy3 = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        expandableListDetail.put(dateFormat.format(dayy1), day1);
        expandableListDetail.put(dateFormat.format(dayy2), day2);
        expandableListDetail.put(dateFormat.format(dayy3), day3);
        return expandableListDetail;
    }
}
