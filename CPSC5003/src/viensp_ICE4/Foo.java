
package viensp_ICE4;

import java.util.Scanner;

public class Foo {
	
	public static void main(String[] arg) {
		Scanner kbd = new Scanner(System.in);
		long val;
		System.out.println("Please enter a value: ");
		val = kbd.nextInt();
		
		for(int n = 25; n <= 3200; n*=2){
			System.out.println(getFooTimeSeconds(n, val));
		}
		
		kbd.close();
		
	}
	
	private static double getFooTimeSeconds(long n, long val) {
		final long startTime = System.nanoTime();
		foo(n, val);
		final long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}
	
	private static void foo(long n, long val) {
		long b = 0;
		long c = 0;
		for(long j = 4; j < n; j++) {
			for(long i = 1; i < j; i++) {
				b = b * val;
				for (long k = 1; k <= n; k++) {
					c = b + c;
				}
			}
		}
	}
}
