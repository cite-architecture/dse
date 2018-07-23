package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._

import scala.scalajs.js
import scala.scalajs.js.annotation._


/** A class for working with an ordered sequence of [[DsePassage]] obects.
*
* @param passages Vector of [[DsePassage]] objects.
*/
@JSExportAll case class DseVector (passages: Vector[DsePassage]) {





  /** Number of citable text passages in this data set.
  */
  def size : Int = {
    passages.size
  }

  /** Concatenate a second [[DseVector]] to this one.
  *
  * @param dseVector Second [[DseVector]] to concatenate
  * to this one.
  */
  def ++ (dseVector: DseVector): DseVector = {
    DseVector(this.passages ++ dseVector.passages)
  }



  /** Set of text-bearing surfaces in this DSE.
  */
  def tbs : Set[Cite2Urn]= {
    passages.map(_.surface).toSet
  }

  /** Set of texts (editions or versions) in this DSE.
  */
  def texts: Set[CtsUrn] = {
    passages.map(_.passage.dropPassage).toSet
  }

  /** Set of image collections in this DSE.
  */
  def imageCollections: Set[Cite2Urn] = {
    passages.map(_.imageroi.dropSelector).toSet
  }

  /** Reference image illustrating a given surface.
  *  All DSE records for a surface should refer to a
  * single image.
  *
  * @param surface A text-bearing surface.
  */
  def imageForTbs(surface: Cite2Urn) : Cite2Urn = {
    val referenceImg = passages.filter(_.surface ~~ surface).map(_.imageroi.dropExtensions).distinct
    referenceImg.size match {
      case 0 => throw new Exception("DseVector: no image found for " + surface)
      case 1 => referenceImg(0)
      case _ => {
        throw new Exception("DseVector: multiple images found for " + surface + ":  " + referenceImg)
      }
    }
  }

  /** Reference image illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imageForText(psg: CtsUrn) : Cite2Urn = {
    val dse = passages.filter(_.passage ~~ psg)
    dse.size match {
      case 0 => throw new Exception("DseVector: no image found for " + psg)
      case 1 => dse(0).imageroi.dropExtensions
      case _ => throw new Exception("DseVector: multiple images found for " + psg)
    }
  }


  /** Image citation with RoI illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imageWRoiForText(psg: CtsUrn) : Cite2Urn = {
    val dse = passages.filter(_.passage ~~ psg)
    dse.size match {
      case 0 => throw new Exception("DseVector: no image found for " + psg)
      case 1 => dse(0).imageroi
      case _ => throw new Exception("DseVector: multiple images found for " + psg)
    }
  }

  /** Vector of texts appearing on a given text-bearing surface.
  * Ordering of the Vector follows the initial construction of
  * the [[DseVector]].
  *
  * @param surf Text-bearing surface.
  */
  def textsForTbs(surf: Cite2Urn): Vector[CtsUrn] = {
    val tbs = passages.filter(_.surface ~~ surf)
    tbs.map(_.passage)
  }

  /** Set of texts illustrated by a given image.
  * Note that this is an unordered set of citable nodes.  You need to consult a
  * TextRepository or a Corpus to determine the document order
  * of nodes in this set or construct a range from this set.
  *
  * @param img Illustrative image.
  */
  def textsForImage(img: Cite2Urn): Vector[CtsUrn] = {
    val tbs = passages.filter(_.imageroi ~~ img)
    tbs.map(_.passage)
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

/** Factory for making [[DseVector]]s from various sources.
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
  * The CEX source must define a CITE Library.
  *
  * @param cexSrc CEX data.
  */
  def apply(cexSrc : String, delimiter: String = "#", delimiter2: String = ","): DseVector = {
    val citeLib = CiteLibrary(cexSrc, delimiter, delimiter2)
    val dseCollections = citeLib.collectionsForModel(dseModel)
    val dsePsgVects = for (coll <- dseCollections) yield {
      val dseObjs = citeLib.collectionRepository.get ~~ coll
      dseObjs.map(DseVector.fromCitableObject(_))
    }
    DseVector(dsePsgVects.flatten)
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
