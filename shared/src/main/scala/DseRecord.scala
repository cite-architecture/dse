package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._
import edu.holycross.shot.citeobj._

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSExportAll case class DseRecord (label: String, passage: CtsUrn, imageroi: Cite2Urn, surface: Cite2Urn, citeObject: CiteObject) {
}

object DseRecord  {

  /** Create a DseRecord 
  *
  * @param label String A label for the Record
  * @param passage CtsUrn A CtsUrn identifying a passage in the DSE triangle
  * @param imageroi Cite2Urn A Cite2Urn identifying a image+ROI illustrating the passage
  * @param surface Cite2Urn A Cite2Urn identifying a text-bearing surface on which the passage appears
  * @param citeObject CiteObject a CiteObject from a CITE Collection implementing the DSE DataModel
  */
  def apply(row: String, delimiter: String = "#"): DsePassage = {
    val columns = row.split(delimiter).toVector
    DsePassage(Cite2Urn(columns(0)), columns(1),
    CtsUrn(columns(2)),
    Cite2Urn(columns(3)),
    Cite2Urn(columns(4))
  )
  }
}
