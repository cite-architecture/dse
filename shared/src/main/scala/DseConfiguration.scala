package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._


import scala.scalajs.js
import scala.scalajs.js.annotation._



/** Association of a CITE Collection with a citable
* data model.
*
* @param collection A CITE Collection following a specified data model.
* @param model Identifier for the data model.
* @param label Human-readable label.
* @param description Fuller description of the model, potentially with
* pointers to external documentation.
*/
@JSExportAll  case class  DseConfiguration (
  repo: CiteCollectionRepository,
  label: String,
  passagePropertyUrn: Cite2Urn,
  imagePropertyUrn: Cite2Urn,
  surfacePropertyUrn: Cite2Urn,
) {


  assert(validateAll)

  def validateAll:Boolean = {
    try {
      // all property urns
      if (passagePropertyUrn.propertyOption == None){
        throw new Exception(s"(${passagePropertyUrn} is not a property-level urn.")
      }
      if (imagePropertyUrn.propertyOption == None){
        throw new Exception(s"(${imagePropertyUrn} is not a property-level urn.")
      }
      if (surfacePropertyUrn.propertyOption == None){
        throw new Exception(s"(${surfacePropertyUrn} is not a property-level urn.")
      }
      // same collection
      if (passagePropertyUrn.dropProperty != imagePropertyUrn.dropProperty){
        throw new Exception(s"Properties (${passagePropertyUrn} and ${imagePropertyUrn}) must be from the same collection.")
      }
      if (surfacePropertyUrn.dropProperty != imagePropertyUrn.dropProperty){
        throw new Exception(s"Properties (${surfacePropertyUrn} and ${imagePropertyUrn}) must be from the same collection.")
      }
      // Count and confirm property type the same time
      val numPassages:Int = {
        val f = repo.data.data.filter(_.urn.dropSelector == passagePropertyUrn)
        f(0).propertyValue.getClass.getName.toString match {
            case "edu.holycross.shot.cite.CtsUrn" => 
            case _ => throw new Exception(s"${passagePropertyUrn} is not of type CtsUrn.")
        }
        f.size
      }
      val numImages:Int = {
        repo.data.data.filter(_.urn.dropSelector == imagePropertyUrn).size
      }
      val numSurfaces:Int = {
        repo.data.data.filter(_.urn.dropSelector == surfacePropertyUrn).size
      }
      if ( (numPassages != numImages) || (numImages != numSurfaces) ) {
        throw new Exception(s"DseExtension Exception: passages, surfaces, and images not of equal numbers.")
      }
      true
    } catch {
      case e:Exception => false
    }
  } 





  /** Number of citable text passages in this data set.
  */
  def  size : Int = {
    try {
      // We validated on construction, so we only need to do one
      repo.data.data.filter(_.urn.dropSelector == passagePropertyUrn).size
    } catch {
      case e:Exception => throw new Exception(s"DseExtension Exception: ${e}")
    }
  }


  /** Set of text-bearing surfaces in this DSE.
  */
  def tbs : Set[Cite2Urn]= {
    repo.data.data.filter(_.urn.dropSelector == surfacePropertyUrn).map(_.propertyValue.asInstanceOf[Cite2Urn]).distinct.toSet
  }

  /** Set of texts (editions or versions) in this DSE.
  */
  def texts: Set[CtsUrn] = {
    repo.data.data.filter(_.urn.dropSelector == passagePropertyUrn).map(_.propertyValue.asInstanceOf[CtsUrn].dropPassage).distinct.toSet
  }

  /** Set of image collections in this DSE.
  */
  def imageCollections: Set[Cite2Urn] = {
    repo.data.data.filter(_.urn.dropSelector == imagePropertyUrn).map(_.propertyValue.asInstanceOf[Cite2Urn].dropSelector).distinct.toSet
  }

  /** Set of images illustrating a given surface.
  *
  * @param surface A text-bearing surface.
  */
  def imagesForTbs(surface: Cite2Urn) : Set[Cite2Urn] = {
    val tbs = repo.data.data.filter(_.urn.dropSelector == surfacePropertyUrn).filter(_.propertyValue.asInstanceOf[Cite2Urn] == surface)
    val propName = imagePropertyUrn.property
    //println(s"propName: ${propName}")
    val imgProps = tbs.map(t => {
        //println(s"t: ${t}")
        propertyUrnFromPropertyName(t.urn.asInstanceOf[Cite2Urn], propName).dropExtensions
    }).distinct.toSet
    val imgs = imgProps.map( i => {
      repo.data.propertyValue(i).asInstanceOf[Cite2Urn].dropExtensions
    })
    imgs
  }
  
  /** Set of images illustrating a given passage of text.
  *
  * @param psg A citable node of text.
  */
  def imagesForText(psg: CtsUrn) : Set[Cite2Urn] = {
    //val tbs = passages.filter(_.passage ~~ psg)
    //tbs.map(_.imageroi.dropExtensions).distinct.toSet
    val matchingTexts = repo.data.data.filter(_.urn.dropSelector == passagePropertyUrn).filter(_.propertyValue.asInstanceOf[CtsUrn] >= psg)
    val propName = imagePropertyUrn.property
    //println(s"propName: ${propName}")
    val imgProps = matchingTexts.map(t => {
     //   println(s"t: ${t}")
        propertyUrnFromPropertyName(t.urn.asInstanceOf[Cite2Urn], propName).dropExtensions
    }).distinct.toSet
    val imgs = imgProps.map( i => {
      repo.data.propertyValue(i).asInstanceOf[Cite2Urn].dropExtensions
    })
    imgs


  }

  /** Set of texts appearing on a given text-bearing surface.
  * Note that this is an unordered set of citable nodes.  You need to consult a
  * TextRepository or a Corpus to determine the document order
  * of nodes in this set or construct a range from this set.
  *
  * @param surf Text-bearing surface.
  */
  def textsForTbs(surf: Cite2Urn): Set[CtsUrn] = {
    val tbs = repo.data.data.filter(_.urn.dropSelector == surfacePropertyUrn).filter(_.propertyValue.asInstanceOf[Cite2Urn] == surf)
    val propName = passagePropertyUrn.property
    val passageProps = tbs.map(t => {
        propertyUrnFromPropertyName(t.urn.asInstanceOf[Cite2Urn], propName).dropExtensions
    }).distinct.toSet
    val texts = passageProps.map( i => {
      repo.data.propertyValue(i).asInstanceOf[CtsUrn]
    })
    texts
  }

/** Set of texts illustrated by a given image.
  * Note that this is an unordered set of citable nodes.  You need to consult a
  * TextRepository or a Corpus to determine the document order
  * of nodes in this set or construct a range from this set.
  *
  * @param img Illustrative image.
  */
  def textsForImage(img: Cite2Urn): Set[CtsUrn] = {
    val imgs = repo.data.data.filter(_.urn.dropSelector == imagePropertyUrn).filter(_.propertyValue.asInstanceOf[Cite2Urn].dropExtensions == img)
    val propName = passagePropertyUrn.property
    val passageProps = imgs.map(t => {
        propertyUrnFromPropertyName(t.urn.asInstanceOf[Cite2Urn], propName).dropExtensions
    }).distinct.toSet
    val texts = passageProps.map( i => {
      repo.data.propertyValue(i).asInstanceOf[CtsUrn]
    })
    texts
  }

  /** Set of text-bearing surfaces illustrated by a given image.
  *
  * @param img Illustrative image.
  */
  def tbsForImage(img: Cite2Urn): Set[Cite2Urn] = {
    val imgs = repo.data.data.filter(_.urn.dropSelector == imagePropertyUrn).filter(_.propertyValue.asInstanceOf[Cite2Urn].dropExtensions == img)
    val propName = surfacePropertyUrn.property
    val passageProps = imgs.map(t => {
        propertyUrnFromPropertyName(t.urn.asInstanceOf[Cite2Urn], propName).dropExtensions
    }).distinct.toSet
    val tbs = passageProps.map( i => {
      repo.data.propertyValue(i).asInstanceOf[Cite2Urn]
    })
    tbs
  }

  /** Returns Vector[DseRecord] showing passages, surfaces, and image-Rois for 
  * a given image. May return an empty vector.
  * @param img Illustrative image.
  */
  def recordsForImage(img: Cite2Urn):Vector[DseRecord] = {
    Vector[DseRecord]()
  }

  /** Returns Vector[DseRecord] showing passages, surfaces and image-Rois for 
  * a given surface. May return an empty vector.
  * @param img Illustrative image.
  */
  def recordsForSurface(surface: Cite2Urn):Vector[DseRecord] = {
    Vector[DseRecord]()
  }

  /** Returns Vector[DseRecord] showing passages, surfaces, and image-Rois for 
  * a given vector of CtsUrns. May return an empty vector
  * @param textVec Vector[CtsUrn]
  */
  def recordsForTextVector(textVec: Vector[CtsUrn]):Vector[DseRecord] = {
    Vector[DseRecord]()
  }

  /** Set of Option[Set[(CtsUrn,Cite2Urn)]] showing DSE Collection-objects
  * for a vector of CtsUrns
  * @param textVec Vector[CtsUrn]
  */





  // Probably should be in CiteObj library?
  // Given a collection URN and a property name, construct a property URN
  def propertyUrnFromPropertyName(urn:Cite2Urn, propName:String):Cite2Urn = {
    //println("\n\n-------")
    //println(s"urn: ${urn}")
    val returnUrn:Cite2Urn = {
        val collUrn:Cite2Urn = {
            urn.propertyOption match {
            case Some(po) => {
              urn.dropProperty.dropSelector
            }
            case None => {
              urn.dropSelector
            }
          }
        }
        //println(s"collUrn: ${collUrn}")
        val collUrnString:String = collUrn.toString.dropRight(1) // remove colon
        urn.objectComponentOption match {
        case Some(oc) => {
          Cite2Urn(s"${collUrnString}.${propName}:${oc}")
        }
        case None => {
          Cite2Urn(s"${collUrnString}.${propName}:")
        }
      }
    }
    //println(s"returnUrn: ${returnUrn}")
    returnUrn
  } 


}
