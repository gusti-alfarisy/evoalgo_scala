package Default

/**
  * Created by Gusti Ahmad Fanshuri on 12/10/2016.
  */

import Algorithm.GA.Chromosome

import scala.math.{Pi, pow, sin};
object FunctionList {
  def f1(x:Double):Double = pow(-x,2) + 14*x -13
  def f2(x1:Double, x2:Double):Double = 19 + x1*sin(x1*Pi) + (10-x2)*sin(x2*Pi)

  def f1Genetic(c: Chromosome) = f1(c.genes(0))
  def f2Genetic(c: Chromosome) = f2(c.genes(0), c.genes(1))

  def f1Solution(s:Solution) = f1(s.values(0))
  def f2Solution(s:Solution) = f2(s.values(0), s.values(1))
}
