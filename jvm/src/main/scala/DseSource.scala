package edu.holycross.shot.dse

import edu.holycross.shot.scm._
import scala.io.Source


import scala.scalajs.js
import js.annotation.JSExport



/** Factory for making catalogs from text sources.
*/
object DseSource {


  /** Create [[Dse]] from a file with data in CEX format.
  *
  * @param fileName Name of file with data in CEX format.
  */
  def fromFile(fileName: String): DseVector = {
    val lns = Source.fromFile(fileName).getLines.toVector
    DseVector(lns.mkString("\n"))
  }



}
