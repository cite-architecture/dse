package edu.holycross.shot.dse

import edu.holycross.shot.scm._
import scala.io.Source
import edu.holycross.shot.cite._

import scala.scalajs.js
import js.annotation.JSExport



/** Factory for making [[DseVector]]s from text sources.
*/
object DseSource {


  /** Create a [[DseVector]] from a file with data in CEX format.
  *
  * @param fileName Name of file with data in CEX format.
  */
  def fromFile(fileName: String, encoding: String = "UTF-8"): DseVector = {
    val lns = Source.fromFile(fileName, encoding).getLines.toVector
    DseVector(lns.mkString("\n"))
  }


  def fromTriplesFile(f: String, dseCollection : Cite2Urn) : DseVector = {
    val buff = Source.fromFile(f)
    val cex = buff.getLines.mkString("\n")
    buff.close
    DseVector.fromTextTriples(cex, dseCollection)
  }

}
