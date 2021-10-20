package Algorithm.Hybrid

import Default.StopCondition
import Algorithm.GA.{Chromosome, GeneticAlgorithm, Population}
import Algorithm.SA.SimulatedAnnealing

/**
  * Created by Gusti Ahmad Fanshuri on 13/10/2016.
  */
//Dalam loop interval tertentu kromosom Algorithm.GA masuk ke Algorithm.SA
class GASA3 {
  //Interval untuk running Algorithm.SA
  var interval = 100;
  //Durasi dalam detik
//  var duration = 10;
  //Jumlah iterasi Algorithm.SA
  var SATotIteration = 100;
  def run(): Population ={
    val GA = new GeneticAlgorithm(popSize = 100, chromLength = 2)
    val SA = new SimulatedAnnealing(temperature = SATotIteration * 10)
    GA.population.initialize()
    val thread2 = new Thread{
      override def run {
        while(true){
          StopCondition.maxIteration = interval
//          println("Jalankan Algorithm.GA...")
          GA.runWithoutInit()
//          println("Jalankan Algorithm.SA...")
          GA.population.chromosomes.foreach(x =>{
//            println("sebelum jalankan Algorithm.SA")
//            println(x)
            StopCondition.maxIteration = SATotIteration
            SA.runFromChromosome(x)
//            println("setelah jalankan Algorithm.SA")
//            println(x)
          })
        }
      }
    }
    thread2.start
    Thread.sleep(StopCondition.duration * 1000)
    thread2.stop

    return GA.population

  }

}
