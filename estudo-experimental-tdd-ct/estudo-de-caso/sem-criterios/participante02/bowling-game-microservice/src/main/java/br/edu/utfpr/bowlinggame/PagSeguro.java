package br.edu.utfpr.bowlinggame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PagSeguro {

	public static void main(String[] args) throws IOException {
		File file = new File("/home/altitdb/Desktop/merchant-client.csv");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		while ((st = br.readLine()) != null) {
			System.out.println(st);
		}
	}
}
