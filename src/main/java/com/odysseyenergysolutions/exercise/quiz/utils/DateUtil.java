package com.odysseyenergysolutions.exercise.quiz.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateUtil {

	public static String formatDate(LocalDateTime dateTime) {
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		DateFormat format = new SimpleDateFormat("MMM dd yyyy HH:mm aa");
		return format.format(date);
	}


}
