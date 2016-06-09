package sort

//import scala.collection.JavaConversions._
//import scala.collection.Seq

object ScalaSort {
  def sort(array: Array[Int]): Array[Int] = {
    sort(array, 0, array.length-1)
    return array
  }
  private def sort(array: Array[Int], i: Int, j: Int): Array[Int] = {
    if(array.length < 2) return array
    else if (array(i) > array(i + 1)) {
      var temp = array(i)
      array(i) = array(i + 1)
      array(i + 1) = temp
    }
    if (i == j-1)
      return sort(array, 0, j - 1)
    else if (j == 0)
      return array
    else
      return sort(array, i + 1, j)
  }
}