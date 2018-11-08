package edu.holycross.shot.dse

import edu.holycross.shot.scm._
import scala.io.Source


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



}
