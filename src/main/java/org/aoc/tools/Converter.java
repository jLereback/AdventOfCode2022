package main.java.org.aoc.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Converter {
	public static List<List<String>> stringToListOfListOfStrings(String s) {
		List<List<String>> initial = new ArrayList<>();
		initial.add(new ArrayList<>());
		return s.lines().reduce(initial, (subtotal, element) -> {
			if (element.trim().isEmpty()) {
				subtotal.add(new ArrayList<>());
			} else {
				subtotal.get(subtotal.size() - 1).add(element);
			}
			return subtotal;
		}, (list1, list2) -> List.of());
	}

	public static Collection<List<String>> createRows(List<String> inputList, int columnsPerRow) {
		AtomicInteger counter = new AtomicInteger();
		return inputList.stream().collect(Collectors.groupingBy(gr -> counter.getAndIncrement() / columnsPerRow)).values();
	}

	public static List<String> stringToList(String input) {
		return input.lines().toList();
	}

	public static byte[][] stringValuesTo2DByteArray(String s){
		var rows = (int) s.lines().count();
		var cols = s.lines().findAny().get().length();
		byte[][] array = new byte[rows][cols];
		int count = 0;
		//Fill array
		for (var line : s.lines().toList()) {
			for (var height : line.chars().toArray()) {
				array[count / cols][count % cols] = (byte) Character.getNumericValue(height);
				count++;
			}
		}
		return array;
	}
}