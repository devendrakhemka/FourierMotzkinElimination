package Compilers.FourierMotzkinElimination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgrammingAssignment1 
{
public static void main(String[] args) throws IOException 
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FMEFinal objInequality = new FMEFinal();
	FMEequality objEquality = new FMEequality();
	System.out.println("Enter the number of unknowns.");
	int n=Integer.parseInt(br.readLine());
	double[] solEq = new double[n];
	double[] solIn = new double[n];
	System.out.println("Comparing the bounds of both the systems");
	System.out.println("*****************************************");
	System.out.println("SOLVING AX <= B");
	System.out.println("*****************************************");
	
	solIn=objInequality.Inequality(n);
	
	System.out.println("*****************************************");
	System.out.println("SOLVING AX = B");
	System.out.println("*****************************************");
	
	solEq=objEquality.Equality(n);
	
	int lengthA1 = solIn.length;
	int lengthA2 = solEq.length;
	if (lengthA1!=lengthA2)
	{
		System.out.println("The size of both the vectors aren't same.");
		return;
	}
	else
	{
		int count=0;
		for(int i=0;i<lengthA1;i++)
		{
			if(solEq[i]<=solIn[i])
			{
				count++;
			}
			
			
		}
		if(count==lengthA1)
		{
			System.out.println("Both X vectors are the same");
		}
		else
			System.out.println("Both X vectors are different");
	}
	System.out.println("Press a key to exit");
	String x = br.readLine();
}
}
