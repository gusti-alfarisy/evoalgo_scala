package Driver.Hybrid

import Algorithm.Hybrid.GASA2
import Default.{FunctionList, Problem, StopCondition}

/**
  * Created by Tirana Noor Fatyanosa on 23/10/2016.
  */
object DriverGASA2 {
  def main(args: Array[String]): Unit ={

    //Durasi 5 detik
    StopCondition.duration = 5
    StopCondition.option = StopCondition.iterationStopCond
    Problem.function = FunctionList.f2Solution
    Problem.withConstraint = true
    val gasa2 = new GASA2()
    val population = gasa2.run()
    println
    val best = population.bestSolution()
    println(s"${best.genes} => ${best.fitnessValue}")
  }
}
