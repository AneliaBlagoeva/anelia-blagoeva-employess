package com.sirma.longesteamperiod.comparator;

import java.util.Comparator;

import com.sirma.longesteamperiod.domain.OverlappedEmployeePair;

public class OverlappedPairComparator implements Comparator<OverlappedEmployeePair> {

	@Override
	public int compare(OverlappedEmployeePair e1, OverlappedEmployeePair e2) {
		if (e1.getOverlappedDays() < e2.getOverlappedDays()) {
			return 1;
		} else {
			return -1;
		}
	}
}