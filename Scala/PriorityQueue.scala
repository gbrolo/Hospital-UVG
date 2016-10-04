
import scala.collection.JavaConversions._

trait PriorityQueue[E <: Comparable[E]] {

  def getFirst(): E

  def remove(): E

  def add(value: E): Unit

  def isEmpty(): Boolean

  def size(): Int

  def clear(): Unit
}
