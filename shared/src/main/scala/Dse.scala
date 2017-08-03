package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._

import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll case class Dse (passages: Vector[DsePassage]) {

  /** Number of citable text passages in this data set.
  */
  def size : Int = {
    passages.size
  }

  /** Set of text-bearing surfaces in this DSE.
  */
  def tbs : Set[Cite2Urn]= {
    passages.map(_.surface).distinct.toSet
  }

  /** Set of texts (editions or versions) in this DSE.
  */
  def texts: Set[CtsUrn] = {
    passages.map(_.passage.dropPassage).distinct.toSet
  }

  /** Set of image collections in this DSE.
  */
  def imageCollections: Set[Cite2Urn] = {
    passages.map(_.imageroi.dropSelector).distinct.toSet
  }

  /** Set of images illustrating a given surface.
  *
  * @param surface A text-bearing surface.
  */
  def imagesForTbs(surface: Cite2Urn) : Set[Cite2Urn] = {
    val tbs = passages.filter(_.surface ~~ surface)
    tbs.map(_.imageroi.dropExtensions).distinct.toSet
  }

  /** Set of images illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imagesForText(psg: CtsUrn) : Set[Cite2Urn] = {
    val tbs = passages.filter(_.passage ~~ psg)
    tbs.map(_.imageroi.dropExtensions).distinct.toSet
  }

}

/** Factory for making catalogs from text sources.
*/
object Dse {

  /**  Strip header line off of each String in a Vector.
  *
  * @param v Vector of Strings.
  */
  def stripHeader(v: Vector[String]) : Vector[String] = {
    val dataModels = v.map( s =>
      s.split("\n").drop(1).mkString("\n"))
    dataModels
  }

  /** Create a [[Dse]] from a CEX source.
  *
  * @param cexSrc CEX data.
  */
  def apply(cexSrc : String, delimiter: String = "#", delimiter2: String = ","): Dse = {
    //  all citable objects in the repo
    val objs = CiteLibrary(cexSrc, delimiter, delimiter2).collectionRepository.get.citableObjects

    val cex = CexParser(cexSrc)
    val dataModels = stripHeader(cex.blockVector("datamodels"))
    val collections = dataModels.map { s =>
      val cols = s.split("#")
      Cite2Urn(cols(0))
    }
    val applicable = for (c <- collections) yield {
      objs.filter(_.urn ~~ c)
    }
    val dsePassages = applicable.flatten.map(fromCitableObject(_))
    Dse(dsePassages)
  }

  /** Construct a [[DsePassage]] from a CiteObject belonging to a
  * collection implementing the Dse model.
  *
  * @param obj Citable object in a colletion implementing the Dse model.
  */
  def fromCitableObject(obj: CiteObject): DsePassage = {
    val passageUrn = obj.urn.addProperty("passage")
    val passage = obj.propertyValue(passageUrn).asInstanceOf[CtsUrn]
    val imageUrn = obj.urn.addProperty("imageroi")
    val image = obj.propertyValue(imageUrn).asInstanceOf[Cite2Urn]
    val surfaceUrn = obj.urn.addProperty("surface")
    val surface = obj.propertyValue(surfaceUrn).asInstanceOf[Cite2Urn]
    DsePassage(obj.urn, obj.label, passage, image,surface)
  }

}
