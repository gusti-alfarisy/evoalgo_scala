package Algorithm.GA

import Default.Problem

/**
  * Created by Gusti Ahmad Fanshuri on 12/10/2016.
  */
class Population (val popSize: Int = 30, val chromLength: Int){
  var chromosomes = new Array[Chromosome](popSize).toBuffer
  var min = 0
  var max = 100

  def initialize(): Unit ={
    runInitFunc
  }

  def runInitFunc(implicit f: Unit): Unit = f


  implicit def randomInitialization : Unit ={
    val r = scala.util.Random
    for(i <- 0 to popSize-1){
      var randomVal = IndexedSeq(0.0)
      if(Problem.withConstraint){
        randomVal = for(j <- 1 to chromLength) yield Problem.rangeValue(j-1)(0) + r.nextDouble() * Problem.rangeValue(j-1)(1)
      }else{
        randomVal = for(j <- 1 to chromLength) yield min + r.nextDouble() * max
      }
      chromosomes(i) = new Chromosome(randomVal: _*)
//      println(chromosomes(i))
    }
  }

  def anotherInitialization : Unit = {
    println("in another")
  }

  def length = chromosomes.length;

  def add(child: Chromosome) = chromosomes += child
  def at(i: Int):Chromosome = chromosomes(i)

  def bestSolution():Chromosome = {
    val best = chromosomes.maxBy(_.fitnessValue)
//    println(best)
//    println(s"fitness value ${best.fitnessValue}")
    return best
  }



}
