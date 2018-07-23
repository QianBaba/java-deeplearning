package deeplearning.math;

import java.util.Arrays;

/**
 * ���ѧϰ�о������ؼ���
 * @author cqq	1324095615@qq.com
 * @version 1.0
 * 
 */
public class Matrix {
	/**
	 * ��ȡnά��λ����
	 * @param n ָ����λ�����ά��
	 * @return ����nά��λ����
	 */
	public  static double[][] getIdentityMatrix( int n)
	{
		double[][] Identity_Matrix;
		if(n>0){
			Identity_Matrix=new double[n][n];
			for(int i=0;i<n;i++)Identity_Matrix[i][i]=1;
			return Identity_Matrix;
		}
		else {
			System.out.println("���벻�Ϸ�����ȷ���������nΪ����0��int������");
			return null;
		}
	}
	
	/**
	 * �Ե���������м򵥵���ѧ���㣺�������ˣ���������
	 * @param m �������or����
	 * @param w �������˵�ϵ��
	 * @param c �������ӵ�ϵ��
	 * @return ���������ľ���
	 */
	public static double[][] matrixCompute(double[][] m,double w,double c)
	{
		double[][] m_result=null;//���巵�ؾ���
		try {
			m_result = new double[m.length][m[0].length];
			for(int i=0;i<m.length;i++)
				for(int j=0;j<m[i].length;j++)
					m_result[i][j] = w*m[i][j]+c;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣����ȷ�������Ƿ���ȷ");
			e.printStackTrace();
		}
		return m_result;
	}
	
	/**
	 * �������
	 * @param a �������1
	 * @param b �������2
	 * @return ������Ӻ�ľ���
	 * @throws Exception
	 */
	public static double[][] matrixAdd(double[][] a,double[][] b)
	{
		double[][] m = new double[a.length][a[0].length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++) 
				m[i][j]=a[i][j]+b[i][j];
		return m;
	}
	
	/**
	 * ����ת��
	 * @param A �������
	 * @return ����ת�ú�ľ���
	 * @throws Exception
	 */
	public static double[][] matrixTranspose(double[][] A)
	{
		double[][] AT = new double[A[0].length][A.length];
		for (int i = 0; i < AT.length; i++)
			for (int j = 0; j < AT[i].length; j++)
				AT[i][j] = A[j][i];
		return AT;
	}
	
	/**
	 * �����׼���
	 * @param a �������1
	 * @param b �������2
	 * @return ������˺�ľ���
	 * @throws Exception
	 */
	public static double[][] matrixProduct(double[][] a,double[][] b)
	{
		double[][] m = null;
		if(a[0].length!=b.length) m = null;
		else {
			m = new double[a.length][b[0].length];
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++) {
					for (int k = 0;  k< b.length;k++) {
						m[i][j] +=a[i][k]*b[k][j];
					} 
				}
			}
		}
		return m;
	}
	
	/**
	 * ����Hadamard����������Ԫ�ض�Ӧ���
	 * @param a �������1
	 * @param b �������2
	 * @return ����Hadamard�˻�����
	 * @throws Exception
	 */
	public static double[][] hadamardProduct(double[][] a,double[][] b)
	{
		double[][] m = null;
		m = new double[a.length][a[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = a[i][j]*b[i][j];
			}
		}
		return m;
	}
	
	/**
	 * ��������ʽ(m,n)�����µ�����ʽ
	 * ע�⣺m,n����1��ʼ,���ǰ��������±�
	 * @param A �������1
	 * @param m ������
	 * @param n ������
	 * @return ����(m,n)�����µ�����ʽ
	 */
	public static double[][] getCofactor(double[][] A, int m,int n)
	{
		int M= A.length;
		int N = A[0].length;
		double[][] result = new double[M-1][N-1];
		for (int i = 0; i < result.length; i++) {
			if (i<m-1) {
				for (int j = 0; j < result[i].length; j++) {
					if (j<n-1) {
						result[i][j] = A[i][j];
					}
					else result[i][j] = A[i][j+1];
				}
			}
			else{
				for (int j = 0; j < result[i].length; j++) {
					if (j<n-1) {
						result[i][j] = A[i+1][j];
					}
					else result[i][j] = A[i+1][j+1];
				}
			}
		}
		return result;
	}
	
	/**
	 * ��������ʽ��ֵ
	 * @param A ��������ʽA
	 * @return ��������ʽ��ֵ
	 */
	public static double det(double[][] A)
	{
		
		double result = 0;
		if(A.length==2) result = A[0][0]*A[1][1]-A[0][1]*A[1][0];
		else{
			double[] data = new double[A.length];
			for (int i = 0; i < data.length; i++) {
				if(i%2==0)
					data[i]=A[0][i]*det(getCofactor(A,1,i+1));
				else
					data[i]=-A[0][i]*det(getCofactor(A,1,i+1));
			}
			for (int i = 0; i < data.length; i++) {
				result+=data[i];
			}
		}
		return result;
	}
	
	/**
	 * ���������A-1 = A*\|A|
	 * ����������det(A)��Ϊ0����AΪ����
	 * @param A ���뷽��A
	 * @return ���������������󲻿��淵��null
	 */
	public static double[][] matrixInverse(double[][] A)
	{
		double[][] result = new double[A.length][A[0].length];
		double val = det(A);
		if(val==0){
			System.out.println("����ʽΪ0�����󲻿���");
			result = null;
		}
		else {
			for(int i=0; i<A.length; i++) {
	            for(int j=0; j<A[0].length; j++) {
	                if((i+j)%2 == 0) {
	                    result[i][j] = det(getCofactor(A, i+1, j+1)) /val;
	                }else {
	                    result[i][j] = -det(getCofactor(A, i+1, j+1)) /val;
	                }

	            }
	        }
			result = matrixTranspose(result);
		}
		return result;
	}
	
	/**
	 * ����ת
	 * @param A �������A
	 * @return ���ط�ת����
	 */
	public static double[][] matrixReverse(double[][] A)
	{
		double[][] result = new double[A.length][A[0].length];
		
		for(int i=0; i<A.length; i++) {
            for(int j=0; j<A[i].length; j++) {
                result[i][j] = A[i][A[i].length-1-j];
            }
        }

		return result;
	}
	
	/**
	 * ������ת
	 * @param A ��������A
	 * @return ���ط�ת����
	 */
	public static double[] vectorReverse(double[] A)
	{
		double[] result = new double[A.length];
		for(int i=0; i<A.length; i++) {
            result[i] = A[A.length-1-i];
        }
		return result;
	}
	
	/**
	 * ������
	 * @param A �������A
	 * @param N ��������N
	 * @return ����N�η���L(N)
	 */
	public static double matrixNorm(double[][] A,int N)
	{
		double result =0;
		
		for(int i=0; i<A.length; i++) {
            for(int j=0; j<A[i].length; j++) {
                result+=Math.pow(A[i][j], N);
            }
        }
		result = Math.pow(result, 1.0/N);
		return result;
	}
	
	/**
	 * ��������
	 * @param A ��������A
	 * @param N ��������N
	 * @return ����N�η���L(N)
	 */
	public static double vectorNorm(double[] A,int N)
	{
		double result =0;
		
		for(int i=0; i<A.length; i++) {
            result+=Math.pow(A[i], N);
        }
		result = Math.pow(result, 1.0/N);
		return result;
	}
	
	/**
	 * ��������
	 * @param A ��������A
	 * @param B ��������B
	 * @return �н�����
	 */
	public static double vectorCosine(double[] A,double[] B)
	{
		double result =0;
		
		for(int i=0; i<A.length; i++) {
            result += A[i]*B[i];
        }
		result = result/(vectorNorm(A,2)*vectorNorm(B,2));
		return result;
	}
	
	/**
	 * ���ɶԽǾ���
	 * @param A ��������A
	 * @return �ԽǾ���
	 */
	public static double[][] diagMatrix(double[] A)
	{
		double[][] result = new double[A.length][A.length];
		
		for(int i=0; i<A.length; i++) {
            result[i][i] = A[i];
        }
		return result;
	}
	
	/**
	 * ��������
	 * @param A
	 * @return �Խ�Ԫ�صĺ�
	 */
	public static double matrixTr(double[][] A)
	{
		double result =0;
		if(A.length==A[0].length){
			for(int i=0; i<A.length; i++) {
	            result += A[i][i];
	        }
		}
		else throw new NullPointerException();
		
		return result;
	}
	public static void main(String[] args) throws Exception
	{
		double[][] a={{1,2,-1},{3,1,0},{-1,-1,-2}};
		double[] b = {0,1};
		double[] c = {1,1};
		//double[][] b={{-1,4,0},{0,1,5}};
		System.out.println( matrixTr(a));//Arrays.toString()
		System.out.println(vectorCosine(b,c));

	}
			
}
