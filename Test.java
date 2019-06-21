import java.util.*;
import java.io.*;

public class Test {
	public static void main(String[] args) {

		TreeMap<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
		map.put(3,"3");
		map.put(1,"1");
		map.put(6, "6");

		System.out.println(map.firstKey());
	}		
}