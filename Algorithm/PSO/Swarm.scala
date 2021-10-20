package Algorithm.PSO


/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
class Swarm (val swarmSize: Int = 30, val dimension: Int,
             val min:Int = 0,
             val max:Int = 100){
  var swarm = new Array[Particle](swarmSize)

  def initialize(): Unit ={
    runInitFunc
  }

  def runInitFunc(implicit f: Unit): Unit = f


  implicit def randomInitialization : Unit ={
    val r = scala.util.Random
    for(i <- 0 to swarmSize-1){
      val randomVal = for(j <- 1 to dimension) yield min + r.nextDouble() * max
      swarm(i) = new Particle(randomVal: _*)
      //      println(swarm(i))
    }
  }

  def anotherInitialization : Unit = {
    println("in another")
  }

  def at(i:Int):Particle = swarm(i)
}
