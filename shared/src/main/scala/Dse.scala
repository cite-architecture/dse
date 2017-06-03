package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._

import scala.scalajs.js
import js.annotation.JSExport

/** Catalog for an entire text repository.
*
* @param texts Set of catalog entries.
*/
@JSExport case class Dse (citeLibrary: CiteLibrary) {


  /** True if submitted CiteLibrary fulfills requirements
  * of DSE model.
  */
  def isValid : Boolean = {
    false
  }


  /** Find set of text-bearing surfaces in the library.
  */
  def tbs: Set[Cite2Urn] = {
    citeLibrary.relationSet.get.relations.filter(_.relation == hasOnIt).map(tr => Cite2Urn(tr.urn1.toString))
  }

  /** Find set of texts in the library.
  */
  def texts: Set[CtsUrn] = {
    citeLibrary.relationSet.get.relations.filter(_.relation == appearsOn).map(tr => CtsUrn(tr.urn1.toString).dropPassage)
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
    Dse(citeLib)
  }

  /** True if subject of a triple is a text-bearing surface.
  *
  * @param Triple to examine.
  */
  def isTbs(triple: CiteTriple): Boolean = {
    (triple.relation == hasOnIt)
  }

}
