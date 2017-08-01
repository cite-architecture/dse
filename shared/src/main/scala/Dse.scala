package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._

import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll case class Dse (passages: Vector[DsePassage]) {

  /** Number of citable text passages in this data set.
  */
  def size : Int = {
    passages.size
  }
}

/** Factory for making catalogs from text sources.
*/
object Dse {

  /** Create a [[Dse]] from a CEX source.
  *
  * @param cexSrc CEX data.
  */
  def apply(cexSrc : String, delimiter: String = "#", secondaryDelim: String = ","): Dse = {
    val citeLib = CiteLibrary(cexSrc,delimiter,secondaryDelim)

    val passages = Vector.empty[DsePassage]
    Dse(passages)
  }



}
