package com.sirma.longesteamperiod.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sirma.longesteamperiod.domain.Employee;
import com.sirma.longesteamperiod.domain.OverlappedEmployeePair;
import com.sirma.longesteamperiod.domain.Record;
import com.sirma.longesteamperiod.comparator.OverlappedPairComparator;

@Service
public class PeriodCalculatorService {
	// return later date
	public static LocalDate findMax(LocalDate d1, LocalDate d2) {
		if (d1.compareTo(d2) > 0) {
			return d1;
		} else {
			return d2;
		}
	}

	// return earlier date
	public static LocalDate findMin(LocalDate d1, LocalDate d2) {
		if (d1.compareTo(d2) > 0) {
			return d2;
		} else {
			return d1;
		}
	}

	public static boolean isOverLapped(LocalDate d1Start, LocalDate d1End, LocalDate d2Start, LocalDate d2End) {

		if (findMax(d1Start, d2Start).compareTo(findMin(d1End, d2End)) <= 0) {
			return true;

		} else {
			return false;
		}
	}

	public List<OverlappedEmployeePair> calculate(List<Record> recs) {
		// map with key=projectID and value=List<Employee>
		Map<Integer, List<Employee>> map = new HashMap<>();

		// list for overlapped intervals, employees and overlapped days
		List<OverlappedEmployeePair> overlappedEmployee = new ArrayList<OverlappedEmployeePair>();

		for (Record rec : recs) {
			// if map contains key = rec.projectID
			// 1.check for overlapping intervals with all employees from value list
			// 2.save result in overlappedEmployee (calculated overlapped days)
			// 3.insert employee map values list

			// if map does not contain key = rec.projectID insert into map and into value
			// list
			map.compute(rec.getProjectID(), (projId, emplList) -> {
				if (emplList == null) {
					emplList = new ArrayList<Employee>();
				} else {
					for (Employee e : emplList) {
						if (isOverLapped(rec.getStartDate(), rec.getEndDate(), e.getStartDate(), e.getEndDate())) {

							long daysBetween = ChronoUnit.DAYS.between(findMax(rec.getStartDate(), e.getStartDate()),
									findMin(rec.getEndDate(), e.getEndDate())) + 1;

							overlappedEmployee.add(new OverlappedEmployeePair(rec.getEmpID(), e.getEmpID(),
									rec.getProjectID(), daysBetween));
						}
					}
				}
				emplList.add(new Employee(rec.getEmpID(), rec.getStartDate(), rec.getEndDate()));
				return emplList;
			});

		} // for

		Collections.sort(overlappedEmployee, new OverlappedPairComparator());

		return overlappedEmployee.stream()
				.filter(x -> x.getOverlappedDays() == overlappedEmployee.get(0).getOverlappedDays())
				.collect(Collectors.toList());

	}

	public static List<String[]> parseFile(File file) throws IllegalArgumentException, IOException {
		List<String[]> parsedLines = new LinkedList<>();

		try (FileInputStream inputStream = new FileInputStream(file); Scanner sc = new Scanner(inputStream)) {
			while (sc.hasNextLine()) {
				String currentLine = sc.nextLine();
				String[] parsedData = Arrays.stream(currentLine.split(",")).map(String::trim).toArray(String[]::new);

				if (parsedData.length != 4) {
					throw new IllegalArgumentException(
							"Line (" + currentLine + ") has " + parsedData.length + " elements. Expected 4 elements.");
				}

				parsedLines.add(parsedData);
			}
		}

		return parsedLines;
	}

	public static List<Record> parseData(List<String[]> parsedLines) throws NumberFormatException {
		List<Record> dataEntries = new LinkedList<>();

		for (String[] line : parsedLines) {
			int employeeId;
			int projectId;
			LocalDate dateFrom;
			LocalDate dateTo;

			employeeId = Integer.parseInt(line[0]);
			projectId = Integer.parseInt(line[1]);
			dateFrom = LocalDate.parse(line[2]);

			if (line[3].trim().equalsIgnoreCase("NULL")) {
				dateTo = LocalDate.now();
			} else {
				dateTo = LocalDate.parse(line[3]);
			}
			dataEntries.add(new Record(employeeId, projectId, dateFrom, dateTo));
		}
		return dataEntries;

	}

}