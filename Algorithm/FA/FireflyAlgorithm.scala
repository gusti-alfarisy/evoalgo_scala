package Algorithm.FA

import Default.{Problem, StopCondition}
import Math.{exp, pow, sqrt}
/**
  * Created by Gusti Ahmad Fanshuri on 22/10/2016.
  */
class FireflyAlgorithm(val SwarmSize:Int = 100,
                       val Min:Int = 0,
                       val Max:Int = 100,
                       val Dimension:Int = 2,
                       val y:Double = 1.0,
                       val B0:Double = 1.0,
                       val a:Double = 0.2) {
//y = absrobtion coefficientr, B0 = attractiveness
  val random = scala.util.Random
  val swarm = new Fireflies(swarmSize = SwarmSize, min = Min, max = Max, dimension = Dimension)

  def initialize():Unit={
    swarm.initialize()
  }

  def run():Firefly={
    initialize()
    while(StopCondition.option()){
      for(f1 <- swarm.fireflies){
        for(f2 <- swarm.fireflies){
          if(f1.fitnessValue < f2.fitnessValue){
            val r = distance(f1,f2)
            var i = -1
            f1.values.transform(x =>{
              i+=1
              val y = f2.values(i)
              val r = random.nextDouble()
              var newX = x + B0*exp(-y*r*r)*(y-x)+a*(r-0.5)
              if(Problem.withConstraint){
                if(newX < Problem.rangeValue(i)(0)){
                  newX = Problem.rangeValue(i)(0)
                }else if(newX > Problem.rangeValue(i)(1)){
                  newX = Problem.rangeValue(i)(1)
                }
              }
              newX
            })

            f1.evaluate()
          }
        }
      }
    }

    return bestFirefly()
  }

  def distance(f1:Firefly, f2:Firefly):Double = {
    val arr = (f1.values, f2.values).zipped.map(_-_)
    val arr2 = for(v <- arr) yield pow(v,2)
    sqrt(arr2.sum)
  }

  def bestFirefly():Firefly = swarm.fireflies.maxBy(_.fitnessValue)

}
