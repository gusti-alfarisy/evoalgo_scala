package Algorithm.GA

import Default.{Problem, Solution}

/**
  * Created by Gusti Ahmad Fanshuri on 12/10/2016.
  */
class Chromosome(_genes: Double*)extends Solution(_genes:_*){
  var genes = values
  val (crossOverMinRandVal, crosOverMaxRandVal) = (-0.25, 1.25)
  val (mutationMinRandVal, mutationMaxRandVal) = (-1.0, 1.0)
  //Batasan nilai untuk tiap gen
  val random = scala.util.Random

//  override def toString: String = genes.toString()
  val genDomain = Problem.rangeValue
  def crossingOver(p2:Chromosome) = runCrossing(p2)
  def runCrossing(p2:Chromosome)(implicit f:Chromosome => (Chromosome,Chromosome)) = f(p2)
  ////////////////////////////////////////////////////////////////
  //Real Coded Cross Over by (Muhlenbein & Schlier-Voosen, 1993)
  ////////////////////////////////////////////////////////////////
  implicit def extendedIntermediateCrossOver (p2: Chromosome):(Chromosome, Chromosome) = {
    var C1 = new Chromosome()
    var C2 = new Chromosome()

    //producing C1 & C2
    //g1 = gen from parent 1
    //g2 = gen from parent 2
    var i =0
    for((g1,g2) <- genes zip p2.genes){
      val randomVal = crossOverMinRandVal + random.nextDouble()*crosOverMaxRandVal
      var valC1 = (g1 + randomVal * (g2-g1))
      var valC2 = (g2 + randomVal * (g1-g2))
      if(Problem.withConstraint){
        if(valC1 < Problem.rangeValue(i)(0)){
          valC1 = Problem.rangeValue(i)(0)
        }else if(valC1 > Problem.rangeValue(i)(1)){
          valC1 = Problem.rangeValue(i)(1)
        }

        if(valC2 < Problem.rangeValue(i)(0)){
          valC2 = Problem.rangeValue(i)(0)
        }else if(valC2 > Problem.rangeValue(i)(1)){
          valC2 = Problem.rangeValue(i)(1)
        }
      }
      C1.genes += valC1
      C2.genes += valC2
      i+=1
    }
    return (C1, C2)
  }
//  implicit def anotherCrossOver(p2:Chromosome):(Chromosome,Chromosome) = {
//    println("another cros over")
//  }
  def mutate = runMutation
  def runMutation(implicit f:Chromosome) = f
  ////////////////////////////////////////////////////////////////
  //Random Mutation
  ////////////////////////////////////////////////////////////////
  implicit def randomMutation : Chromosome ={
    val genIndex = random.nextInt(genes.length)
    var C1 = clone()
    val randomVal = mutationMinRandVal + random.nextDouble()*mutationMaxRandVal
    C1.genes(genIndex) = C1.genes(genIndex) + randomVal*(genDomain(genIndex)(1) - genDomain(genIndex)(0))
    if(Problem.withConstraint){
      if(C1.genes(genIndex) < Problem.rangeValue(genIndex)(0)){
        C1.genes(genIndex) = Problem.rangeValue(genIndex)(0)
      }else if(C1.genes(genIndex) > Problem.rangeValue(genIndex)(1)){
        C1.genes(genIndex) = Problem.rangeValue(genIndex)(1)
      }
    }
    return C1
  }

  override def clone():Chromosome = {
    var newObj = new Chromosome(genes:_*)
    newObj.genes = genes.clone()
    return newObj
  }




}

