import java.lang.Math;
import java.text.NumberFormat;
import java.util.*;
import java.io.*;

class Solution
{
	int funcComp;
	double started;
	double finished;
	double functionAccuracy;
	double argumentAccuracy;
	double step;
	
	public int computationFunc(int i)
	{
	  funcComp +=i;
	  return funcComp;
	}


	public double func( double x)//Метод возвращает значение заданной функции, вычисленной от текущего параметра 
	{
		return Math.sqrt(4*x+7)-3*Math.cos(x); //Вариант 6
	}

	public void separation (double start, double s, double finish)
	{
	  int u = 0;
	  int i=0;
	  double x1 = start;
	  double x2;
	  double y1;
	  double y2;
	  x2 = x1 + s;
	  y1 = func(x1);

	  while (x2<finish)
	  {
		y2 = func(x2);
		i++;
		if (y1*y2<=0){
		  
		  u++;
		  System.out.print("x1: "+x1+"\t" + "x2: " + x2+ "\n" + "y1: " +y1+"\t"+"y2: "+y2+"\n");
		  semidiv(x1, x2, functionAccuracy, argumentAccuracy, u);
		}
		x1 = x2;
		x2 = x1+s;
		y1 = y2;
	  }
		computationFunc(i);
		System.out.print("Количество итераций отделения корней: "+i+ "\n"  );
	}

	void semidiv (double a, double b, double epsy, double epsx, int n)
	{
	  double c;
	  int t=0;

	  while(Math.abs(b-a) > epsx && Math.abs(func(a)) > epsy)
	  {
		t++;
		c = (a+b)/2;
		if (func(a)*func(c) < 0)
		{
		  b = c;
		}
		else {
		  a = c;
		}
	  }
	  c = (a+b)/2;

	  System.out.print("Значение функции при найденном решении:"+ func(c)+"\n");
	  System.out.print("Количество итераций уточнения корня: "+t+"\n");
	  System.out.print("Корень x_"+n+": "+c+"\n"); 
	  computationFunc(t);
	  System.out.print("==============================================\n");
	}
}


public class semdiv
{
	public static void main(String[] args)
	{
		Solution solution = new Solution();
		Scanner input = new Scanner(System.in);
		System.out.print("Введите начальную точку интервала: ");
		solution.started = input.nextDouble();
		while (solution.started<-7/4)
		{
			System.out.print("Введенное значение не входит в область определения, значение должно быть более чем -7/4:\n ");
		solution.started = input.nextDouble();
		}

		System.out.print("Введите значение конца интервала:\n " );
		solution.finished = input.nextDouble();

		System.out.print("Введите значение шага сетки для отделения корней \nРекомендуемое значение от 0.1 до 1:  ");
		solution.step = input.nextFloat();
		while (solution.step<=0.1 && solution.step>=1)
		{
			System.out.print("Введенное значение не входит в рекомендуюмую область, значение должно быть в пределах 0.1 - 1 : \n ");
			solution.step = input.nextDouble();
		}

		System.out.print("Введите точность решения:\n ");
		solution.functionAccuracy = input.nextDouble();

		System.out.print("Введите точность по аргументу решения:\n " );
		solution.argumentAccuracy = input.nextDouble();
		solution.separation(solution.started, solution.step, solution.finished);
		System.out.print("Времени затрачено: %f секунд\n");
	}
}

