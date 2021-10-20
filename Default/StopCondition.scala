package Default

/**
  * Created by Gusti Ahmad Fanshuri on 13/10/2016.
  */
object StopCondition {

  var t:Double =0;
  var maxIteration = 1000;
  var maxNanoTime:Double = 100000000.0;
  //Durasi dalam detik
  var duration:Int = 5;
  //Stop Condition
  var option = () => false
  option = infiniteStopCond
//  def stopCondition():Boolean = runStopCondition
//  def runStopCondition(implicit f:Boolean) = f


  /////////////////////////////////
  //Pemilihan mode kondisi berhenti
  //Stop berdasarkan jumlah iterasi
  def iterationStopCond():Boolean = if(t < maxIteration) {t+=1; true} else {t=0;false}
  //Selalu true, infinite iteration
  def infiniteStopCond():Boolean = true

  //Gunakan Thread saja!!!
//  def timeStopCondition:Boolean = {
//    if(t <= -1){t = 0; return false}
//    if(t <= 0) {t = System.nanoTime(); return true}
//    val duration = System.nanoTime()-t
//    if((duration) <= maxNanoTime) true
//    else {t= -1; false}
//  }
}
