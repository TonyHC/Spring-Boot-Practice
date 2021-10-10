package com.healthycoderapp;

public class ActivityCalculator {
	private static final int WORKOUT_DURATION_MIN = 45;
	
	public static String rateActivityLevel(int weeklyCardioMins, int weeklyWorkoutSessions) {
		int totalMinutes = weeklyCardioMins + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;
		double avgDailyActivityMins = totalMinutes / 7.0;
		
		if (weeklyCardioMins < 0 || weeklyWorkoutSessions < 0) {
			throw new RuntimeException("Inputs cannot be negative");
		}
		
		if (avgDailyActivityMins < 20) {
			return "bad";
		} else if (avgDailyActivityMins <= 40) {
			return "average";
		} else {
			return "good";
		}
	}
}