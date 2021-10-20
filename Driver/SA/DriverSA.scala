package Driver.SA

import Default.{FunctionList, Problem, StopCondition}
import Algorithm.SA.SimulatedAnnealing

/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
object DriverSA {
  def main(args: Array[String]): Unit ={
    Problem.function = FunctionList.f2Solution
    StopCondition.option = StopCondition.infiniteStopCond
    Problem.withConstraint = true
    StopCondition.duration = 5
    val SA = new SimulatedAnnealing(temperature = StopCondition.duration * 10000)
    val thread2 = new Thread{
      override def run {
        SA.run()
      }
    }
    thread2.start()
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop
    val best = SA.currSolution
    println(s"${best.values} => ${best.fitnessValue}")
  }
}
