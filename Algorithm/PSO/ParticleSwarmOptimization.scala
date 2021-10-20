package Algorithm.PSO

import Default.{Problem, StopCondition}


/**
  * Created by gusti on 01/08/2016.
  */
class ParticleSwarmOptimization(val dimensionLen: Int = 2,
                                val swarmSize: Int = 30,
                                val c1: Double = 1.4962,
                                val c2: Double = 1.4962,
                                val w: Double = 0.7968) {

  var swarm = new Swarm(swarmSize = swarmSize,dimension = dimensionLen)
  var pBest = new Array[Particle](swarmSize)
  var gBest = new Particle()

  private val random = new scala.util.Random
  initSwarm();

  def initSwarm(): Unit = {

    swarm.initialize()
//    swarm.swarm.foreach(_.evaluate())
    for(i <- pBest.indices){
      pBest(i) = (swarm at i).clone()
    }

    gBest = pBest.maxBy(_.fitnessValue).clone()
  }

  def run(): Particle = {
    while(StopCondition.option()){
      for(i <- swarm.swarm.indices){
        for(j <- swarm.swarm(i).positions.indices){
          val v = swarm.swarm(i).velocity(j)
          val p = swarm.swarm(i).positions(j)
//          println("posisi awal = " + swarm.swarm(i).positions(j))
          swarm.swarm(i).velocity(j) = w*v+c1*random.nextDouble()*(pBest(i).positions(j) - p)+c2*random.nextDouble()*(gBest.positions(j)-p)
//          println("velocity = " + swarm.swarm(i).velocity(j))
          swarm.swarm(i).positions(j)= swarm.swarm(i).positions(j) + swarm.swarm(i).velocity(j)
//          println("posisi akhir = " + swarm.swarm(i).positions(j))
//          println(s"nilai dari swarm = ${swarm.swarm(i)}")

          if(Problem.withConstraint){
            if(swarm.swarm(i).positions(j) < Problem.rangeValue(j)(0)){
              swarm.swarm(i).positions(j) = Problem.rangeValue(j)(0)
            }else if(swarm.swarm(i).positions(j) > Problem.rangeValue(j)(1)){
              swarm.swarm(i).positions(j) = Problem.rangeValue(j)(1)
            }
          }

        }
//        println("setelah pergerakan")
        swarm.swarm(i).evaluate()
//        println(s"${swarm.swarm(i).fitnessValue} > ${pBest(i).fitnessValue}")
//        println(swarm.swarm(i))
//        println(pBest(i))
        if(swarm.swarm(i).fitnessValue > pBest(i).fitnessValue){
          pBest(i) = swarm.swarm(i).clone()
//          println("gbest----------")
//          println(s"${swarm.swarm(i).fitnessValue} > ${gBest.fitnessValue}")
//          println("---------------")
          if(swarm.swarm(i).fitnessValue > gBest.fitnessValue){
            gBest = swarm.swarm(i).clone()
//            println(gBest)
          }
        }
      }
    }
    return gBest
  }
}
