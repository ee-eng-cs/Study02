package kp.c_d_i.producers;

import java.time.LocalDateTime;

/**
 * Foreseeable Date.
 *
 */
public enum ForeseeableDate {
	NEAR(LocalDateTime.of(2033, 3, 6, 9, 12, 24)), /*-*/
	FAR(LocalDateTime.of(2050, 5, 10, 15, 20, 25))/*-*/
	;
	/**
	 * Constructor.
	 * 
	 * @param localDateTime the local date and time
	 */
	ForeseeableDate(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	LocalDateTime localDateTime;
}