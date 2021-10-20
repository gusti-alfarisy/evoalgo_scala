package Algorithm.GA

/**
  * Created by Gusti Ahmad Fanshuri on 12/10/2016.
  */
import Default.FunctionList.f2Genetic
import Default.FunctionList.f1Genetic
import Default.{Problem, StopCondition}
import Default.StopCondition.option

class GeneticAlgorithm(val popSize:Int = 20,
                       val chromLength:Int = 2,
                       val pc:Double = 0.8,
                       val pm:Double = 0.4){

  //Fungsi evaluasi
  implicit def funcEvaluation(c:Chromosome):Double = f2Genetic(c)

  var population = new Population(popSize, chromLength)

  //pc = crossover rate
  //pm = mutation rate

  val random = scala.util.Random
  val totChildCrossOver = (pc*population.length).toInt
  val totChildMutation = (pm*population.length).toInt

  def run():Population = {
    population initialize;
    runWithoutInit
    return population
  }
  //Jalankan Algorithm.GA tanpa inisialisasi awal
  def runWithoutInit():Population ={
    while(option()){
      reproduction
      evaluation
      selection
    }

    return population
  }


  def reproduction():Unit = {
    //Current total child after performing cross over
    var currentTotChildCO = 0
    while(currentTotChildCO < totChildCrossOver){
      val gIndex1 = random.nextInt(population.length)
      var gIndex2 = random.nextInt(population.length)
      while(gIndex1 == gIndex2){
        gIndex2 = random.nextInt(population.length)
      }

      var (c1,c2) = (population at gIndex1) crossingOver (population at gIndex2)

      population add c1
      population add c2
      currentTotChildCO+=2
    }

    var currentTotChildM = 0
    while(currentTotChildM < totChildMutation){
      val gIndex1 = random.nextInt(population.length)
      var c1 = (population at gIndex1) mutate;
      population add c1
      currentTotChildM+=1
    }
    currentTotChildCO = 0
    currentTotChildM = 0
  }

  def evaluation(implicit f: (Chromosome) => Double):Unit = {
    (population chromosomes) foreach{x =>
      x.evaluate()
//      println(x)
    }
  }
  //Selection
  def selection():Unit = runSelection
  def runSelection(implicit f: Unit):Unit = f
  implicit def elitismSelection:Unit = {
//    println("Sebelum sorting")
//    population.chromosomes.foreach(x => println(x.fitnessValue))
    population.chromosomes = population.chromosomes sortBy(-_.fitnessValue)
//    println("Setelah di sort")
//    population.chromosomes.foreach(x => println(x.fitnessValue))
    val splittedPop = population.chromosomes splitAt popSize
    population.chromosomes = splittedPop._1
//    println("Seletah dipotong")
//    population.chromosomes.foreach(x => println(x.fitnessValue))

  }



}
