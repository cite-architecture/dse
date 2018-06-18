package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._

import scala.scalajs.js
import scala.scalajs.js.annotation._


/** A class for working with an ordered sequence of `DsePassage` obects.
*
* @param passages Vector of `DsePassage` objects.
*/
@JSExportAll case class DseVector (passages: Vector[DsePassage]) {

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


  /** Set of image citations with RoI illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imagesWRoiForText(psg: CtsUrn) : Set[Cite2Urn] = {
    val tbs = passages.filter(_.passage ~~ psg)
    tbs.map(_.imageroi).distinct.toSet
  }

  /** Set of texts appearing on a given text-bearing surface.
  * Note that this is an unordered set of citable nodes.  You need to consult a
  * TextRepository or a Corpus to determine the document order
  * of nodes in this set or construct a range from this set.
  *
  * @param surf Text-bearing surface.
  */
  def textsForTbs(surf: Cite2Urn): Set[CtsUrn] = {
    val tbs = passages.filter(_.surface ~~ surf)
    tbs.map(_.passage).toSet
  }

  /** Set of texts illustrated by a given image.
  * Note that this is an unordered set of citable nodes.  You need to consult a
  * TextRepository or a Corpus to determine the document order
  * of nodes in this set or construct a range from this set.
  *
  * @param img Illustrative image.
  */
  def textsForImage(img: Cite2Urn): Set[CtsUrn] = {
    val tbs = passages.filter(_.imageroi ~~ img)
    tbs.map(_.passage).toSet
  }

  /** Set of text-bearing surfaces illustrated by a given image.
  *
  * @param img Illustrative image.
  */
  def tbsForImage(img: Cite2Urn): Set[Cite2Urn] = {
    val tbs = passages.filter(_.imageroi ~~ img)
    tbs.map(_.surface).distinct.toSet
  }


  /** Compose a URL string to display DSE relations for
  * a specifice text-bearing surface in the image citation
  * tool developed for the CITE Architecture.
  * See https://github.com/cite-architecture/ict2.
  *
  * @param surfaceUrn The surface to illustrate.
  * @param baseUrl Home URL, as a String, for an installation
  * of the CITE Image Citation Tool (version 2).
  *
  */
  def ictForSurface(surfaceUrn: Cite2Urn, baseUrl: String = "http://www.homermultitext.org/ict2/") : String = {
    val rois = passages.filter(_.surface == surfaceUrn).map(_.imageroi)
    if (rois.size > 0) {
      baseUrl + "?urn=" + rois.mkString("&urn=")
    } else {
      baseUrl
    }
  }

  /** Compose a URL string to display DSE relations for
  * a specified image in the image citation
  * tool developed for the CITE Architecture.
  * See https://github.com/cite-architecture/ict2.
  *
  * @param img The images to illustrate.
  * @param baseUrl Home URL, as a String, for an installation
  * of the CITE Image Citation Tool (version 2).
  *
  */
  def ictForImage(img: Cite2Urn, baseUrl: String = "http://www.homermultitext.org/ict2/") : String = {
    val rois = passages.filter(_.imageroi ~~ img).map(_.imageroi)
    if (rois.size > 0 ) {
      baseUrl + "?urn=" + rois.mkString("&urn=")
    } else {
      baseUrl + "?urn=" + img
    }
  }

  /** Compose a URL string to display DSE relations for
  * an exactly matching specified passage of text in the image citation
  * tool developed for the CITE Architecture.
  * See https://github.com/cite-architecture/ict2.
  *
  * @param psg Text passage to illustrate.
  * @param baseUrl Home URL, as a String, for an installation
  * of the CITE Image Citation Tool (version 2).
  *
  */
  def ictForText(psg: CtsUrn, baseUrl: String = "http://www.homermultitext.org/ict2/") : String = {
    val rois = passages.filter(_.passage == psg).map(_.imageroi)
    if (rois.size > 0) {
      baseUrl + "?urn=" + rois.mkString("&urn=")
    } else {
      baseUrl
    }
  }


}

/** Factory for making catalogs from text sources.
*/
object DseVector {

  /**  Strip header line off of each String in a Vector.
  *
  * @param v Vector of Strings.
  */
  def stripHeader(v: Vector[String]) : Vector[String] = {
    val dataModels = v.map( s =>
      s.split("\n").drop(1).mkString("\n"))
    dataModels
  }

  /** Create a [[DseVector]] from a CEX source.
  *
  * @param cexSrc CEX data.
  */
  def apply(cexSrc : String, delimiter: String = "#", delimiter2: String = ","): DseVector = {
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
    DseVector(dsePassages)
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
