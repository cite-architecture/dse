package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._
import java.util.Calendar
import scala.scalajs.js
import scala.scalajs.js.annotation._



import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** A class for working with an ordered sequence of [[DsePassage]] obects.
*
* @param passages Vector of [[DsePassage]] objects.
*/
@JSExportAll case class DseVector (passages: Vector[DsePassage]) extends LogSupport {
  Logger.setDefaultLogLevel(LogLevel.DEBUG)


  require(passages.size == passages.map(_.passage).toSet.size, {
    val msg = "Duplicate text passages in constructor to DseVector:\n" + DseVector.duplicatePassages(passages).mkString("\n")
    warn(msg)
    msg
  })

  require(oneImagePerSurface, {
    val msg = "One or more surfaces indexed to multiple images:\n" + DseVector.doubleIndexedSurfaces(passages).mkString("\n")
    warn(msg)
    msg
  })

//  require(consistentImageSurface, "Inconsistent pairing of surface and reference image: \n" + inconsistentPairs.mkString("; "))



  /** True if ...
  */
  def consistentImageSurface : Boolean = {
    val passageImage: Vector[(CtsUrn, Cite2Urn)] = {
      passages.map( p => {
        (p.passage, p.imageroi)
      }).distinct
    }
    val passageSurface: Vector[(CtsUrn, Cite2Urn)] = {
      passages.map( p => {
        (p.passage, p.surface)
      }).distinct
    }
    val imageSurface: Vector[(Cite2Urn, Cite2Urn)] = {
      passages.map( p => {
        (p.imageroi.dropExtensions, p.surface)
      })
    }
    ((passageImage.size == passageSurface.size) &
      (passageSurface.size == imageSurface.size) & (passages.size > 0))

  }


/*
  def inconsistentPairs : Vector[String] = {
    val surfaces = passages.map(_.surface).distinct
    val consistencyMessages = for (surface <- surfaces) yield {

      val img = imageForTbs(surface)
      //debug("check " + surface + "<->" + img)
      val psgs = textsForTbs(surface)
      val pairMessages = for (psg <- psgs) yield {
        val psgImage = imageForText(psg)
        //debug("\t" + psg + "<->" + psgImage )
        if (psgImage == img) { "" } else {
          val msg = s"${surface} linked to ${img} but ${psg} to ${psgImage}"
          //debug("\n\nMISMATCH:  " + msg + "\n\n")
          msg
        }
      }
      pairMessages.filter(_.nonEmpty)
    }
    consistencyMessages.flatten
  }
  */

  /** True if only one reference image referenced to a surface.
  */
  def oneImagePerSurface: Boolean = {
    val surfaces = passages.map(_.surface).distinct
    if (surfaces.isEmpty) {
      val msg =  "No text-bearing surfaces identified."
      warn(msg)
      throw new Exception(msg)
    }
    val tf = for (surface <- surfaces) yield {
      try {
        this.imageForTbs(surface)
        true
      } catch {
        case t: Throwable => {
          debug(s"${t} :: surface ${surface}")
          false
        }
      }
    }
    (tf(0) && tf.distinct.size == 1)
  }

/*
  def doubleIndexedSurfaces : Vector[Cite2Urn] = {
    val surfaces = passages.map(_.surface).distinct
    val singleImage = for (surface <- surfaces) yield {
      try {
        val img = imageForTbs(surface)
        (surface, Some(img))
      } catch {
        case t: Throwable => (surface, None)
      }
    }
    singleImage.filter(_._2 == None).map(_._1)
  }
*/

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


  def imageForTbs(surface: Cite2Urn) : Option[Cite2Urn] = {
    DseVector.imageForTbs(passages, surface)
  }



  /** Reference image illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imageForText(psg: CtsUrn) : Cite2Urn = {
    //val dse = passages.filter(_.passage ~~ psg)
    val dse = passages.filter(_.passage == psg)
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


  /** URN for the text-bearing surface carrying a given text passage.
  *
  * @param psg A passage of text found on a single text-bearing surface.
  */
  def tbsForText(psg: CtsUrn): Option[Cite2Urn] = {
    val records = passages.filter(_.passage == psg)
    val tbs = records.map(_.surface).distinct
    tbs.size match {
      case 0 => {
        warn("No text-bearing surface found for " + psg )
        None
      }
      case 1 => Some(tbs(0))
      case _ => {
        val msg = "CtsUrn " + psg + " is not specific enough: " + tbs.size  + " text-bearing surfaces found (" + tbs.mkString(", ") + ")"
        warn(msg)
        None
      }
    }
  }

  /** Compose a URL string to display DSE relations for
  * a specifice text-bearing surface in the image citation
  * tool developed for the CITE Architecture.
  * See https://github.com/cite-architecture/ict2.
  *
  * @param surfaceUrn The surface to illustrate.
  * @param baseUrl Home URL, as a String, for an installation
  * of the CITE Image Citation Tool (version 2).
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
object DseVector extends LogSupport {
  Logger.setDefaultLogLevel(LogLevel.DEBUG)


  /** Find any instances of a text passages appearing more than
  * once in a Vector of [[DsePassage]]s.
  *
  * @param dsePassages Vector of [[DsePassage]]s to test.
  */
  def duplicatePassages(dsePassages: Vector[DsePassage]): Vector[CtsUrn] = {
    val urns = dsePassages.map(_.passage)
    val dupeCounts = urns.groupBy(u => u).filter( _._2.size > 1)
    dupeCounts.map(_._1).toVector
  }

  /**
  */
  def imageForTbs(passages: Vector[DsePassage], surface: Cite2Urn) : Option[Cite2Urn] = {
    val referenceImg = passages.filter(_.surface == surface).map(_.imageroi.dropExtensions).distinct
    referenceImg.size match {
      case 1 => Some(referenceImg(0))
      case 0 => {
        val msg = "DseVector: no image found for " + surface
        warn(msg)
        None
      }
      case _ => {
        val msg = "DseVector: multiple images found for " + surface + ":\n" + referenceImg.mkString("\n")
        None
      }
    }
  }

  /** Find any instances of a text-bearing surface indexed to more than
  * one reference image in a Vector of [[DsePassage]]s.
  *
  * @param dsePassages Vector of [[DsePassage]]s to test.
  */
  def doubleIndexedSurfaces(passages: Vector[DsePassage]) : Vector[Cite2Urn] = {
    // 1. Get unique set of surface IDs
    val surfaces = passages.map(_.surface).distinct
    // 2.
    val singleImageEntries = for (surface <- surfaces) yield {
      try {
        val img = imageForTbs(passages, surface)
        (surface, img)
      } catch {
        case t: Throwable => (surface, None)
      }
    }
    singleImageEntries.filter(_._2 == None).map(_._1)
  }




  /** Build a [[DseVector]] from a CiteLibrary.
  *
  * @param lib A CiteLibrary including one or more collections
  * implementing the DSE model.
  */
  def fromCiteLibrary(lib: CiteLibrary): DseVector = {
    val dseModel = Cite2Urn("urn:cite2:cite:datamodels.v1:dsemodel")
    val dseCollections = lib.collectionsForModel(dseModel)
    val citeObjects = lib.collectionRepository.get.citableObjects

    val dseSingles = for (dseCollection <- dseCollections) yield {
      citeObjects.filter(dseCollection ~~ _.urn)
    }
    DseVector(dseSingles.flatten.map(DseVector.fromCitableObject(_)))
  }


  /**  Strip header line off of each String in a Vector.
  *
  * @param v Vector of Strings.
  */
  def stripHeader(v: Vector[String]) : Vector[String] = {
    val dataModels = v.map( s =>
      s.split("\n").drop(1).mkString("\n"))
    dataModels
  }


  /** Create a [[DseVector]] from CEX source with default
  * values for delimiter strings.
  * The CEX source must define a CITE Library.
  *
  * @param cexSrc CEX data.
  */
  def apply(cexString: String) : DseVector = {
    cex(cexString, "#", ",")
  }

  /** Uncritically create a Vector of [[DsePassage]]s from a
  * CEX string defining a CITE library with DSE content.
  *
  * @param cexSrc CEX data.
  * @param delimiter Primary delimiter for CEX.
  * @param delimiter2 Secondary delimiter for CEX.
  */
  def rawFromCex(cexSrc : String, delimiter: String = "#", delimiter2: String = ","): Vector[DsePassage] = {
    debug("Uncritically building Vector of DsePassages from cex string.")
    val citeLib = CiteLibrary.cex(cexSrc, delimiter, delimiter2)
    val citeObjRepo = citeLib.collectionRepository.get
    // 1. Find relevant collections in your library.
    val dseCollections = citeLib.collectionsForModel(dseModel)
    debug(s"Collections implementing ${dseModel} : " + dseCollections.size + " (" +  dseCollections.mkString(", ")  + ")")

    // 2. construct DseObjects for all collections
    // implementing the model:
    val dseObjs = for (coll <- dseCollections ) yield {
      val ids = citeObjRepo.collectionsMap(coll)
      // and make full ojbects from them:
      val objs = for (id <- ids) yield {
        citeObjRepo.citableObject(id)
      }
      objs
    }
    debug("Created " + dseObjs.flatten.size +  " citable objects.")
    val dses = dseObjs.flatten.map(DseVector.fromCitableObject(_))
    debug("Converted to " + dses.size + " DseObjs.")
    dses
  }

  /** Create a [[DseVector]] from a CEX source.
  * The CEX source must define a CITE Library.
  *
  * @param cexSrc CEX data.
  */
  def cex(cexSrc : String, delimiter: String = "#", delimiter2: String = ","): DseVector = {
    val psgVect = rawFromCex(cexSrc, delimiter, delimiter2)
    DseVector(psgVect)
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

  /** Uncritically create a Vector of [[DsePassage]]s from a
  * CEX string with DSE triples.
  *
  * @param cexStr CEX data.
  * @param dseCollection Cite2Urn for the CITE collection newly
  * created entries.
  */
  def rawFromTextTriples(cexStr: String, dseCollection : Cite2Urn) : Vector[DsePassage] = {
    val goodLines = cexStr.split("\n").toVector.tail.filter(_.nonEmpty)
    val indexed = goodLines.zipWithIndex
    val v = for ( (l,i) <- indexed) yield {
      //urn: Cite2Urn, label: String, passage: CtsUrn, imageroi: Cite2Urn, surface: Cite2Urn

      val dsePsg = dseCollection.addSelector("record_" + i)
      val cols =l.split("#")
      //passage#imageroi#surface
      val label = "Passage " + i
      val psg = CtsUrn(cols(0))
      val img = Cite2Urn(cols(1))
      val surface = Cite2Urn(cols(2))
      DsePassage(dsePsg, label, psg, img, surface)
    }
    v
  }

  /** Create a [[DseVector]] from CEX data for triples.  New
  * DSE records are created in a specified collection.
  *
  * @param cexStr CEX data for DSE triples.
  * @param dseCollection Cite2Urn for the collection for
  * newly created [[DsePassage]]s.
  */
  def fromTextTriples(cexStr: String, dseCollection : Cite2Urn) : DseVector = {
    val v = rawFromTextTriples(cexStr, dseCollection)
    DseVector(v)
  }


}
