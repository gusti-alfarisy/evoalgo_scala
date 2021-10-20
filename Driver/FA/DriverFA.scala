package Driver.FA

import Algorithm.FA.{Firefly, FireflyAlgorithm}
import Default.{FunctionList, Problem, StopCondition}

/**
  * Created by Gusti Ahmad Fanshuri on 22/10/2016.
  */
object DriverFA {
  def main(args:Array[String]): Unit ={
    Problem.function = FunctionList.f2Solution
    StopCondition.option = StopCondition.infiniteStopCond
    Problem.withConstraint = true
    StopCondition.duration = 5
    //    StopCondition.maxIteration = 100
    val FA = new FireflyAlgorithm(SwarmSize = 100, Dimension = 2)
    val thread2 = new Thread{
      override def run {
        FA.run()
      }
    }
    thread2.start()
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop
    val best = FA.bestFirefly()
    println
    println(best)
  }
}
