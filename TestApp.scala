package DropChance.files

import scala.io.StdIn._
import DropChance._
import scala.collection.mutable.Buffer

object TestApp extends App {
  
 var calculation: Calculation = new Item(100)
   
  println("This app will count the amount of kills or instance runs you have to do to reach a given probability to get the gear you want.\nPlease enter the probability you want to calculate with as a % (for example '50' (more likely than not) or '95'(95% likely)).")
  val probability = readDouble()
  println("Would you like to do an item(1) or instance(2) calculation?")
  val calculationType = readInt()
  
  if (calculationType == 2) {
   var numbers = Buffer[Double]() 
   println("Enter the droprates of each item in the same instance on separate rows. Enter 100 after the numbers to proceed.")
   while (!numbers.contains(100)){
     numbers += readDouble()
   }
    calculation = new Instance(numbers.toArray)
    
    
  } else {
    println("Please enter the chance for the item to drop in a single kill in % (for example '18.5').")
    var chance = readDouble()
    calculation = new Item(chance)
  }
 
  println("Result: " + calculation.runCalculation(probability))
  
 
}