package Algorithm.SA

import Default.FunctionList._
import Default.{Problem, Solution}
import Default.StopCondition._
import Algorithm.GA.Chromosome

import scala.math.exp;

/**
  * Created by Gusti Ahmad Fanshuri on 13/10/2016.
  */
class SimulatedAnnealing(var temperature:Double = 1000,
                         var coolingRate:Double = 0.003,
                         val dimension:Int = 2,
                         var min:Double = 0,
                         var max:Double = 100,
                         var minRate:Double = -1,
                         var maxRate:Double = 1) {

  var currSolution = new Solution(0)
  val random = scala.util.Random
  //Batasan nilai untuk tiap gen
  val solutionDomain = Array(Array(-5,10), Array(0,15))
  //Fungsi evaluasi
  implicit def funcEvaluation(s:Solution):Double = f2Solution(s)

  def run():Solution = {
    initialize
    runWithoutInit
  }
  def runWithoutInit(): Solution ={
//    println("Memulai Algorithm.SA.........")
//    println(currSolution)
    while(option()){
      val neighbour = pickRandomNeighbour()

      evaluation(neighbour)
      if(neighbour.fitnessValue > currSolution.fitnessValue){
        currSolution = neighbour
      }else{
        val accept:Boolean = exp((neighbour.fitnessValue-currSolution.fitnessValue)/temperature) > random.nextDouble()
//        println(s"Ada lebih rendah, accept ? = $accept")
        if(accept) currSolution = neighbour
      }


      temperature *= 1-coolingRate
//      println(currSolution)
//      println(currSolution.fitnessValue)
    }
//    println("Selesai Algorithm.SA.........")
    return currSolution
  }

  def runFromChromosome(c:Chromosome):Chromosome = {
    currSolution.values = c.genes.clone()
    val newSolution = runWithoutInit()
    c.genes = newSolution.values.clone()
    return c
  }

  def initialize(): Unit ={
    runInitFunc
  }

  def runInitFunc(implicit f: Unit): Unit = f

  implicit def randomInitialization : Unit ={
    val randomVal = for(j <- 1 to dimension) yield min + random.nextDouble() * max
    currSolution = new Solution(randomVal:_*)
  }

  //Random Mutation
  def pickRandomNeighbour():Solution = {
    runPickRandomNeighbour
  }

  def runPickRandomNeighbour(implicit f: Solution):Solution = f

  //Pemilihan random neighbour dengan teknik random mutation dari genetik
  //Tetapi semua index dilakukan mutasi berbeda dengan genetik hanya random gen tertentu saja.
  implicit def randomMutation : Solution = {
    val neighbour = currSolution.clone()

    for(i <- neighbour.values.indices){
      val randomVal = minRate + random.nextDouble()*maxRate
      neighbour.values(i) = neighbour.values(i) + randomVal*solutionDomain(i)(1) - solutionDomain(i)(0)
      if(Problem.withConstraint){
        if(neighbour.values(i) < Problem.rangeValue(i)(0)){
          neighbour.values(i) = Problem.rangeValue(i)(0)
        }else if(neighbour.values(i) > Problem.rangeValue(i)(1)){
          neighbour.values(i) = Problem.rangeValue(i)(1)
        }
      }
    }


    return neighbour
  }
  //Evaluation
  def evaluation(solution:Solution)(implicit f: (Solution) => Double):Unit = {
    solution.fitnessValue = f(solution)
  }


}
