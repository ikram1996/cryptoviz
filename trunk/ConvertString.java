


import java.awt.*;
import  java.io.*;

public class ConvertString{



	public static String stringToBinary(String line){

		String binaryString = new String();

		for(int i = 0; i < line.length(); i++){
			int x = (int)line.charAt(i);
			binaryString += Integer.toBinaryString(x);
		}

		return binaryString;
	}

	/*
	public static void main(String[] args){

		String test = "hello world";
		System.out.println(stringToBinary(test));
	}
	*/
}
