package Algorithm.PSO

import Default.Solution

/**
  * Created by gusti on 01/08/2016.
  */
class Particle(_positions: Double*) extends Solution(_positions:_*){
  var positions = values
//  var velocity = new Array[Double](positions.length)
  val _velValue = for(i <- positions.indices) yield 0.1
  var velocity = _velValue.toArray

  def setToZero() = {
    positions transform {x => 0}
    velocity transform {x => 0}
    fitnessValue = 0
  }

  override def clone(): Particle = {
    var clone = new Particle()
//    var clone = new Particle(values.clone():_*)
    clone.positions = positions.clone()
    clone.values = values.clone()
    clone.fitnessValue = fitnessValue
    clone.velocity = velocity.clone()
    return clone
  }

}
