package com.java.Sankalp;

import java.util.Scanner;

interface IFloyd {
	
	public abstract void compute();
	public abstract void getMatrix(int Matrix[][]);

}


abstract class Floyd implements IFloyd {
	
	public static Scanner scanner = new Scanner(System.in);	
	public int distanceMatrix[][], n, resultMatrix[][];
	
	// Default Constructor
	Floyd(){
		System.out.print("Enter the number of vertices: ");
		n = scanner.nextInt();
		
		distanceMatrix = new int[n][n];
		resultMatrix = new int[n][n];

		System.out.println("Enter the distance matrix");
		for(int i = 0; i<n; i++ ) {
			for(int j = 0; j<n; j++) {
				distanceMatrix[i][j] = scanner.nextInt();
			}
		}	
	}
	
	// Implementing the method present in interface IFloyd
	@Override
	public void compute() {
		resultMatrix = distanceMatrix;
		for(int k = 0; k<n; k++) {
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					resultMatrix[i][j] = Math.min(resultMatrix[i][j], (resultMatrix[i][k]+resultMatrix[k][j]));
				}
			}
		}
	}
	
	// Abstract methods inherited from Interface "IFloyd" remains not defined in this class "Floyd"
	// Resulting in making this class as "Abstract" 
	public abstract void getMatrix(int Matrix[][]);

}


public class Main {
	
	public static void main(String[] args) { 
		
		// Reference of an Interface "IFloyd" points to an object of type "Floyd"
		// Base Class Reference (BCR)
		IFloyd IReference = new Floyd() {
			@Override
			public void getMatrix(int Matrix[][]) {
				for(int i = 0; i<n; i++ ) {
					for(int j = 0; j<n; j++) {
						System.out.print(Matrix[i][j] + " ");
					}
					System.out.println();
				}	
			}
		};
		
		// Since, IReference is a BCR 
		// To access child class Instance Level Variable (ILV) and Instance Level Method (ILV)
		// Type-Casting the BCR to child class Reference
		System.out.println("Entered Distance Matrix: ");
		((Floyd)IReference).getMatrix(((Floyd)IReference).distanceMatrix);
		
		((Floyd)IReference).compute();
		
		System.out.println("Shortest Path from every vertex to every other vertex: ");
		((Floyd)IReference).getMatrix(((Floyd)IReference).resultMatrix);
		
	}
}
