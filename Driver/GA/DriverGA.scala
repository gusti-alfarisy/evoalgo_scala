package Driver.GA

import Default.{FunctionList, Problem, StopCondition}
import Algorithm.GA.GeneticAlgorithm

/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
object DriverGA {
  def main(args: Array[String]): Unit ={
    Problem.function = FunctionList.f2Solution
    StopCondition.option = StopCondition.infiniteStopCond
    Problem.withConstraint = true
    StopCondition.duration = 5

    val GA = new GeneticAlgorithm(popSize = 100, pc=0.8, pm=0.4)
    val thread2 = new Thread{
      override def run {
        GA.run()
      }
    }
    thread2.start()
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop
    val best = GA.population.bestSolution()
    println(best)
//    println(s"${best.values} => ${best.fitnessValue}")
  }
}
