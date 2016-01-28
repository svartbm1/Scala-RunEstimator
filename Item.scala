package DropChance.files

import scala.io.StdIn._
import DropChance._

class Item(dropChance:Double) extends Calculation{
  
  var soFar: Double = 0
  var kills: Int = 0
   
 def killLoop(desiredProbability: Double): Unit = { 
   kills += 1
   soFar += (100 - soFar) * dropChance/100
   if (soFar < desiredProbability) this.killLoop(desiredProbability)
     
 }
  def runCalculation(probablity: Double): Int = {
    this.killLoop(probablity)
    kills
  }
 
}