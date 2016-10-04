import scala.reflect.{BeanProperty, BooleanBeanProperty}


class Paciente(@BeanProperty var nombre: String, @BeanProperty var enfermedad: String, @BeanProperty var prioridad: String)
    extends Comparable[Paciente] {

  override def equals(otro: Paciente): Boolean = this.getPrioridad == otro.getPrioridad

  def compareTo(otro: Paciente): Int = {
    this.getPrioridad.compareTo(otro.getPrioridad)
  }

  override def toString(): String = {
    this.getNombre + ", " + this.getEnfermedad + ", " + this.getPrioridad
  }
}