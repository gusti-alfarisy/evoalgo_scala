package Algorithm.Hybrid

import Default.StopCondition
import Algorithm.GA.{Chromosome, GeneticAlgorithm, Population}
import Algorithm.SA.SimulatedAnnealing
/**
  * Created by Gusti Ahmad Fanshuri on 13/10/2016.
  */
//Algorithm.SA menjadi inisialisasi bagi Algorithm.GA
class GASA2 {
  //Jumlah iterasi Algorithm.SA
  var SATotIteration = 100;
  def run(): Population ={
    val GA = new GeneticAlgorithm(popSize = 100, chromLength = 2)
    val SA = new SimulatedAnnealing()
    GA.population.initialize()
    val thread2 = new Thread{
      override def run {
        while(true){
          GA.population.chromosomes.foreach(x =>{
            StopCondition.maxIteration = SATotIteration
            StopCondition.option = StopCondition.iterationStopCond
            SA.runFromChromosome(x)
          })
          GA.runWithoutInit()
        }
      }
    }
    thread2.start
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop

    return GA.population

  }
}
