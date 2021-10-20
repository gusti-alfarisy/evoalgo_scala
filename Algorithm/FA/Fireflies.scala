package Algorithm.FA

import Default.Problem


/**
  * Created by Gusti Ahmad Fanshuri on 22/10/2016.
  */
class Fireflies(val swarmSize: Int = 30, val dimension: Int,
                val min:Int = 0,
                val max:Int = 100) {

  var fireflies = new Array[Firefly](swarmSize)

  def initialize(): Unit ={
    runInitFunc
  }

  def runInitFunc(implicit f: Unit): Unit = f


  implicit def randomInitialization : Unit ={
    val r = scala.util.Random
    for(i <- 0 to swarmSize-1){
      var randomVal = IndexedSeq(0.0)
      if(Problem.withConstraint){
        randomVal = for(j <- 1 to dimension) yield Problem.rangeValue(j-1)(0) + r.nextDouble() * Problem.rangeValue(j-1)(1)
      }else{
        randomVal = for(j <- 1 to dimension) yield min + r.nextDouble() * max
      }
      fireflies(i) = new Firefly(randomVal: _*)
      fireflies(i).evaluate()
      //      println(swarm(i))
    }
  }

  def anotherInitialization : Unit = {
    println("in another")
  }

  def at(i:Int):Firefly = fireflies(i)
}
