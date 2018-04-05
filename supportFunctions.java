package Compilers.FourierMotzkinElimination;
import java.util.Arrays;
import java.util.Comparator;

public class supportFunctions
{
	int negativeNo=-1;
	
	
	public void Display(double[][] a, double[] b) 
	{

	/*for(int i =0;i<a.length;i++)
	{
		for(int j=0;j<a.length;j++)
		{
			if(j!=0)
				System.out.print("+ ");
			System.out.print(a[i][j]+"x"+(j+1));
		}
		System.out.print(" <= ");
		System.out.println(b[i]);
		
	}*/
		
	}
	
	public void SortEquations(double[][]a, double[]b, String order)
	{
		double merge[][] = new double[a.length][a[0].length+1];
		merge = MergeAB(a,b);
		if(order.equals("ascending"))
		{
			java.util.Arrays.sort(merge, new java.util.Comparator<double[]>() {
			public int compare(double[] a, double[] b) {
			return Double.compare(a[0], b[0]);
			}});
		}
		else
		{
			java.util.Arrays.sort(merge, new java.util.Comparator<double[]>() {
				public int compare(double[] a, double[] b) {
				return Double.compare(b[0], a[0]);
				}});
		}
		
		SeparateArray(merge,a,b);
		
	}
	
	private void SeparateArray(double[][] merge, double[][] a, double[] b) 
	{
		for(int i=0;i<merge.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				a[i][j]= merge[i][j];
			}
			b[i]=merge[i][merge[0].length-1];
			
		}
		
	}

	private double[][] MergeAB(double[][] a, double[] b) 
	{
		double merge[][] = new double[a.length][a[0].length+1];
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				merge[i][j] = a[i][j];
			}
			merge[i][merge[0].length-1]=b[i];
		}
		return merge;
	}

	public int countCoeff(double[][] a)
	{
		int pos = 0;
		for(int i =0;i<a.length;i++)
		{
			if(a[i][0]>=0)
				pos++;
			else
				continue;
		}
		return pos;
	}
	
	public double[] DropRowB(double[] b) 
	{
		
		double newB[] = new double[b.length-1];
		int c=0;
		for(int i=0;i<b.length-1;i++)
		{
				
				newB[c]=b[i];
				c++;
			
		}
		return newB;
	}

	public void ReduceMatrix(double[][] a, double[] b)
	{
		for (int i=0;i<a.length-1;i++)
		{
				for(int j=i+1;j<a.length;j++)
				{
					int flag=0;
					for(int k=0;k<a.length;k++)
					{
						
					if(a[i][0]+a[j][0]==0 || flag>0)
					{
						flag++;
						a[i][k]=a[i][k]+a[j][k];
						if(flag==1)
						{
							b[i]+=b[j];
						}
					}
			}
				}
			
		}
	}

	public double[][] DropColumn(double[][] a, int m) 
	{

		double redA[][] = new double[m][m];
		int p = 0,q=0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i][0]!=0)
				continue;
			q=0;
			for(int j=1;j<a.length;j++)
			{
				redA[p][q]=a[i][j];
				q++;
				
			}
			p++;
		}
		
		
		return redA;
	}

	public double[][] hasSolution(double[][] a) 
	{
		int rot=0;
		while(rot<a.length)
	{
		
		int count =0;
		for (int i=0;i<a.length;i++)
		{
			if(a[i][0]<0)
			{
				count++;
			}
			
		}
		if(count==0 || count==a[0].length)
		{
			a=RotateMatrixA(a);
			rot++;
		}
		else
			return a;
	}
		a=null;
		
		return a;
	}

	public void ColumnWiseNormalize(double[][] a, double[] b)
	{
		double key;
		for(int i=0;i<a.length;i++)
		{
			if(a[i][0]!=0)
			key =Math.abs(a[i][0]);
			else
				continue;
			//At the end of the loop A[i][index] will be 1 and row 'i' will be value/key
			for(int j=0;j<a.length;j++)
			{
				
				a[i][j] = a[i][j]/key;
			}
				b[i]=b[i]/key;
		
			
		Display(a,b);
		System.out.println();
		}
	}

	public double[][] RotateMatrixA(double[][] a) 
	{
			
		double rotA[][] = new double[a.length][a.length];
		
		for(int i=0;i<a.length;i++)
		{
			for(int j=1;j<a.length;j++)
			{
				rotA[i][j-1]=a[i][j];
			}
		}
		
		for(int i=0;i<a.length;i++)
		{
			rotA[i][a.length-1]=a[i][0];
		
		}return rotA;
	}

	public void swapArrayA(double[][] a, double[][] newA) 
	{
		for(int i =0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				a[i][j]=newA[i][j];
			}
		}
		
	}

	public void swapArrayB(double[] b, double[] newB) 
	{
		for(int i=0;i<b.length;i++)
		{
			b[i]=newB[i];
		}
		
	}

	public double printSolution(double[][] a, double[] b, int rotation)
	{
		FMEFinal obj = new FMEFinal();
		double key = a[0][0];
		double value = b[0]/key;
		String sign="";
		if (key<0 && b[0]>0)
			 sign="-";
		System.out.println("***********************************************");
		System.out.println("-Infinity <= "+sign+"X"+(rotation+1)+" <= "+value);
		System.out.println("***********************************************");
		return value;
	}


	public void transformMatrix(double[][] a)
	{
		findNegative(a);
		if(negativeNo!=0)
		{
			transpose(a);
		}
		java.util.Arrays.sort(a, new java.util.Comparator<double[]>() {
	           
	                       
	          // Compare values according to columns
	          public int compare(final double[] entry1, 
	                             final double[] entry2) {
	 
	            // To sort in descending order revert 
	            // the '>' Operator
	            if (entry1[negativeNo] > entry2[negativeNo])
	                return 1;
	            else
	                return -1;
	          }
	        });  // End of function call sort().
		
		if(negativeNo!=0)
		{
			transpose(a);
		}
		
	}

	private void transpose(double[][] a)
	{
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				a[i][j]=a[j][i];
			}
		}
		
	}

	private void findNegative(double[][] a)
	{
		for(int i=0;i<a.length-1;i++)
		{
			for(int j=0;j<a.length-1;j++)
			{
				if(a[i][j]<0)
					negativeNo=j;
				return;
			}
		}
		
	}

	public void ReduceEqualityMatrix(double[][] a, double[] b) 
	{
		
		for(int i=1;i<a.length;i++)
			{
			int flag=0;
			int n=1;
			for(int j=0;j<a.length;j++)
			{
				if((a[i][0]!=0 && a[i-1][0]!=0)||flag>0)
				{
					if((a[i][0]<0 && a[i-1][0]>0)||flag>0 ||(a[i][0]>0 && a[i-1][0]<0))
					{
						flag++;
						a[i-1][j] += (n*a[i][j]);
					}
					else
						if((a[i][0]<0 && a[i-1][0]<0) || flag>0 || (a[i][0]>0 && a[i-1][0]>0))
						{
							n=-1;
							a[i-1][j] = a[i-1][j] + (-1)*a[i][j];
							flag++;
						}
					if (flag==1)
					{
						b[i-1]=b[i-1]+((n)*b[i]);
					}
				}
			}
			}
			
		
	}

	public double printEqualitySolution(double[][] a, double[] b, int rotation) 
	{
	
		double key = a[0][0];
		double value = b[0]/key;
		System.out.println("***********************************************");
		System.out.println("X"+(rotation+1)+" = "+value);
		System.out.println("***********************************************");
		return value;
	}
}
