package Driver.PSO

import Default.{FunctionList, Problem, StopCondition}
import Algorithm.GA.GeneticAlgorithm
import Algorithm.PSO.ParticleSwarmOptimization

/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
object DriverPSO {

  def main(args: Array[String]): Unit ={
    Problem.function = FunctionList.f2Solution
    StopCondition.option = StopCondition.infiniteStopCond
    Problem.withConstraint = true
    StopCondition.duration = 5
//    StopCondition.maxIteration = 100
    val PSO = new ParticleSwarmOptimization(swarmSize = 100)
    val thread2 = new Thread{
      override def run {
        PSO.run()
      }
    }
    thread2.start()
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop
    val best = PSO.gBest
    println
    println(best)
  }
}
