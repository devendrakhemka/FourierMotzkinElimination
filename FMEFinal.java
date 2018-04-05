package Compilers.FourierMotzkinElimination;
import java.io.*;
public class FMEFinal 
{	
 public double solutionFME[];
public double[] Inequality(int n)throws IOException
{
	FMEFinal obj = new FMEFinal();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	supportFunctions objSupportFunction = new supportFunctions();
	System.out.println("Note: The number of equations should be equal to the number of unknowns!");
	
	System.out.println("Ax<=b");
	System.out.println("Enter the matrix A and B");
	
	double[][] A = new double[n][n];
	double[] B   = new double[n];
	double[][] copyA = new double[n][n];
	double[] copyB = new double[n];
	double[][] newA;
	double[] newB;
	obj.solutionFME= new double[n];
	//Entering the array A and array B
	for (int i=0;i<n;i++)
	{
		System.out.println("Enter the matrix row, each number followed by a <return>");
		for(int j=0;j<n;j++)
		{
			A[i][j] = Double.parseDouble(br.readLine());
			copyA[i][j]= A[i][j];
		}
		System.out.println();
		System.out.println("Enter the value for the corresponding b");
		B[i] = Double.parseDouble(br.readLine());
		copyB[i]=B[i];
	}
	
	objSupportFunction.Display(A, B);
	int rotation = 0;
	
	while(rotation < n)
	{
		int index=0;
		int index2=0;
		int reducedSize =n-1;
		int positiveCoeff =0;
		positiveCoeff = objSupportFunction.countCoeff(A);
		int negativeCoeff = n - positiveCoeff;
		if(positiveCoeff > negativeCoeff)
		{
			objSupportFunction.SortEquations(A, B, "descending");
		}
		else
		{
			objSupportFunction.SortEquations(A, B, "ascending");
		}
		
		System.out.println();
		objSupportFunction.Display(A, B);
		System.out.println();
		while(index2<n)
		{
			//objSupportFunction.transformMatrix(A);
			if(reducedSize==0)
			{
				double value =objSupportFunction.printSolution(A,B,rotation);
				index2++;
				obj.solutionFME[rotation]=value;
				continue;
			}
			
			A= objSupportFunction.hasSolution(A);
			if(A==null)
			{
				System.out.println("-Infinity<= X"+(rotation+1)+" <=Infinity");
				index2=n;
				continue;
			}
			objSupportFunction.ColumnWiseNormalize(A, B);
			objSupportFunction.ReduceMatrix(A, B);
			newA = new double[reducedSize][reducedSize];
			newB = new double[reducedSize];
			newA = objSupportFunction.DropColumn(A, reducedSize);
			newB = objSupportFunction.DropRowB(B);
			A= new double[reducedSize][reducedSize];
			B= new double[reducedSize];
			A = newA.clone();
			B = newB.clone();
			index2++;
			reducedSize--;
		}
			A = new double[copyA.length][copyA.length];
			B = new double[copyB.length];
			A = copyA.clone();
			B = copyB.clone();
			//A=objSupportFunction.RotateMatrixA(A);
			int var = rotation+1;
			while(var>0) 
			{
			A=objSupportFunction.RotateMatrixA(A);
			var--;
			}
			rotation++;
	}
	return obj.solutionFME;
	
	
		
}
}
