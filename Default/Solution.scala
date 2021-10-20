package Default

/**
  * Created by Gusti Ahmad Fanshuri on 21/10/2016.
  */
class Solution(_val: Double*) extends {
  var values = _val.toBuffer
  var fitnessValue:Double = 0

  override def clone():Solution = {
    var newObj = new Solution()
    newObj.values = values.clone()
    return newObj
  }
  override def toString: String = values.toString() + " => " + fitnessValue

  def evaluate():Unit = {
    fitnessValue = Problem.function(this)
  }
}
