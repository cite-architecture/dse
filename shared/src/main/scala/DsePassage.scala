package edu.holycross.shot.dse

import edu.holycross.shot.cite._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSExportAll case class DsePassage (urn: Cite2Urn, label: String, passage: CtsUrn, imageroi: Cite2Urn, surface: Cite2Urn) {
}


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
  )
  }
}
