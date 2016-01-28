package DropChance.files

import scala.io.StdIn._
import DropChance._
import scala.collection.mutable.Buffer

class Instance(items:Array[Double]) extends Calculation{
  
  var respectively = Buffer[Double]()
  var soFar: Double = 0
  var runs: Int = 0
  var debug: Boolean = true
   
 def runLoop(desiredProbability: Double): Unit = {
   runs += 1
   if (runs == 1){ //exception on the first run. this fills the buffer
     respectively = items.toBuffer
     if (debug) println("Runs: 1")
   } else {     
     for (i <- 0 until items.size)     {
       respectively.update(i, respectively(i) + (100 - respectively(i)) * items(i)/100) 
     }      
     if (debug) println("Runs: " + runs + " Odds separately: "  + respectively.mkString(", "))
     if (debug) println("Items: "  + items.mkString(", "))
   }  
   soFarLoop
   if (soFar < desiredProbability) this.runLoop(desiredProbability)
 
 }
  
   def soFarLoop = {
     if (debug) println("soFarLoop start: " + soFar)
     var failChance: Double = 0
     for (i <- respectively){
       if (debug) println(failChance)
       failChance += (100 - failChance) * (100 - i)/100
     }
     soFar = 100 - failChance
     if (debug) println("failChance when loop finished: " + failChance)
     if (debug) println("soFarLoop end: " + soFar)
   }
   
   def runCalculation(probablity: Double): Int = {
    this.runLoop(probablity)
    runs
 }
}
