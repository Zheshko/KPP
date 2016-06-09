package sort
import scala.collection.mutable.ArrayBuffer
object ScalaStatistic {
 def getStatistic(array: Array[Int]): Array[Float] = {
   val max=array.max
   var statisticArray=ArrayBuffer[Float]()
   for(i<- 1 to max)
   {
     statisticArray+=(getDigitArray(array,i).length.toFloat/array.size.toFloat)    
   }
   statisticArray.toArray
 }
 private def getDigitArray(array: Array[Int],i: Int):Array[Int]={
   for(x<-array if (x==i)) yield x
 }
}