package com.tangshengbo.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class MyDateFormatter implements Formatter<Date> {

	public String print(Date arg0, Locale arg1) {

		return null;
	}

	public Date parse(String arg0, Locale arg1) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(arg0);
	}

}
