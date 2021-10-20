package Driver.Hybrid

import Default.{FunctionList, Problem, StopCondition}
import Algorithm.Hybrid.GASA3

/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
object DriverGASA3 {
  def main(args: Array[String]): Unit ={

    //Durasi 5 detik
    StopCondition.duration = 5
    StopCondition.option = StopCondition.iterationStopCond
    Problem.function = FunctionList.f2Solution
    Problem.withConstraint = true
    val gasa3 = new GASA3()
    val population = gasa3.run()
    println
    val best = population.bestSolution()
    println(s"${best.genes} => ${best.fitnessValue}")
  }
}
