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


  /** Alias for easy access to all triples in the library.
  */
  def triples = citeLibrary.relationSet.get.relations

  /** True if submitted CiteLibrary fulfills requirements
  * of DSE model.
  */
  def isValid : Boolean = {
    false
  }


  /** Find set of text-bearing surfaces in the library.
  */
  def tbs: Set[Cite2Urn] = {
    triples.filter(_.relation == hasOnIt).map(tr => Cite2Urn(tr.urn1.toString))
  }

  /** Find set of texts in the library.
  */
  def texts: Set[CtsUrn] = {
    triples.filter(_.relation == appearsOn).map(tr => CtsUrn(tr.urn1.toString).dropPassage)
  }

  /** Find set of image collections in the library.
  */
  def imageCollections: Set[Cite2Urn] = {
    triples.filter(_.relation == illustrates).map(tr => Cite2Urn(tr.urn1.toString).dropSelector)
  }


  /** Find images illustrating a given text-bearing surface.
  *
  * @param surface Surface to find images for.
  */
  def imagesForTbs(surface: Cite2Urn): Set[Cite2Urn] = {
      val imgTriples = triples.filter(_.relation == illustrates).filter(t => isCite2Urn(t.urn2))

      imgTriples.filter(_.urn2 ~~ surface).map(tr => Cite2Urn(tr.urn1.toString))
  }


  /** Find texts appearing on a given text-bearing surface.
  *
  * @param surface Surface to find texts for.
  */
  def textsForTbs(surface: Cite2Urn): Set[CtsUrn] = {
      val surfaceTriples = triples.filter(_.relation == hasOnIt).filter(t => isCtsUrn(t.urn2))

      surfaceTriples.filter(_.urn1 ~~ surface).map(tr => CtsUrn(tr.urn2.toString))
  }



  /** Find texts illustrated by a given image.
  *
  * @param surface Surface to find texts for.
  */
  def textsForImage(img: Cite2Urn): Set[CtsUrn] = {
      val imgTriples = triples.filter(_.relation == illustrates).filter(t => isCtsUrn(t.urn2))

      imgTriples.filter(_.urn1 ~~ img).map(tr => CtsUrn(tr.urn2.toString))
  }


  /** Find text-bearing surfaces illustrated by a given image.
  *
  * @param surface Surface to find texts for.
  */
  def tbsForImage(img: Cite2Urn): Set[Cite2Urn] = {
      val imgTriples = triples.filter(_.relation == illustrates).filter(t => isCite2Urn(t.urn2))

      imgTriples.filter(_.urn1 ~~ img).map(tr => Cite2Urn(tr.urn2.toString))
  }

  /** Find images illustrating a given text-bearing surface.
  *
  * @param surface Surface to find images for.
  */
  def imagesForText(passage: CtsUrn): Set[Cite2Urn] = {
      val imgTriples = triples.filter(_.relation == illustrates).filter(t => isCtsUrn(t.urn2))

      imgTriples.filter(_.urn2 ~~ passage).map(tr => Cite2Urn(tr.urn1.toString))
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



}
