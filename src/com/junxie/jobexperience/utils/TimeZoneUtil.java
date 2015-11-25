package com.junxie.jobexperience.utils;

import java.util.Date;
import java.util.TimeZone;

public class TimeZoneUtil {
	/**
	 * �ж��û����豸ʱ���Ƿ�Ϊ���������й��� 2014��7��31��
	 * 
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * ���ݲ�ͬʱ����ת��ʱ�� 2014��7��31��
	 * 
	 * @param time
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone,
			TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;

	}
}
