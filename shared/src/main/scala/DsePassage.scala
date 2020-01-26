package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._


import edu.holycross.shot.citebinaryimage._

import scala.scalajs.js
import scala.scalajs.js.annotation._


/** Relations of documented scholarly edition for
* a single citable passage of text.
*
* @param urn URN identifying this DSE record.
* @param label Human-readable label for this DSE record.
* @param passage The text passage.
* @param imageroi Region of interest on an image illustrating
* this passage of text.
* @param surface Physical surface where this passage is preserved.
*/
@JSExportAll case class DsePassage (urn: Cite2Urn, label: String, passage: CtsUrn, imageroi: Cite2Urn, surface: Cite2Urn) {
  def cex(delimiter: String = "#") = {
    s"${urn}${delimiter}${label}${delimiter}${passage}${delimiter}${imageroi}${delimiter}${surface}"
  }


  /** Formats markdown string for proofreading.  Assumes layout of
  * file system on IIIF service mirrors URN values.
  */
  def markdown(
    baseUrl : String  = "http://www.homermultitext.org/iipsrv?",
    basePath: String = "/project/homer/pyramidal/deepzoom/"
  ): String = {
    val extension =  Vector(imageroi.namespace, imageroi.collection, imageroi.version).mkString("/") + "/"
    val fullPath = basePath + extension
    val bis  = IIIFApi(baseUrl, fullPath)
    "- Compare text " + passage + " to image " + bis.linkedMarkdownImage(imageroi)
  }
}


/** Factory object for creating [[DsePassage]]s from various
* kinds of sources.
*/
object DsePassage  {

  /** Create a DsePassage from a single line of delimited text.
  *
  * @param row String with five columns of data required for DSE data model.
  * @param delimiter Delimiting string used to separate columns.
  */
  def apply(row: String, delimiter: String = "#"): DsePassage = {
    val columns = row.split(delimiter).toVector
    DsePassage(Cite2Urn(columns(0)), columns(1),
    CtsUrn(columns(2)),
    Cite2Urn(columns(3)),
    Cite2Urn(columns(4))
  )}

  /** Create a [[DsePassage]] from a `CiteObject`.
  *
  * @param co CiteObject from a collection implementing the
  * DSE model.
  */
  def fromCitableObject(co: CiteObject): DsePassage = {
      DsePassage(co.urn,
    co.label,
    CtsUrn(co.propertyValue(co.urn.addProperty("passage")).toString),
    Cite2Urn(co.propertyValue(co.urn.addProperty("imageroi")).toString),
    Cite2Urn(co.propertyValue(co.urn.addProperty("surface")).toString)
  )
  }
}
