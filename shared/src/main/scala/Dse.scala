package edu.holycross.shot.dse

import edu.holycross.shot.scm._
import scala.io.Source


import scala.scalajs.js
import js.annotation.JSExport

/** Catalog for an entire text repository.
*
* @param texts Set of catalog entries.
*/
@JSExport case class Dse (citeLibrary: CiteLibrary) {

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
    Dse(citeLib)
  }

}
