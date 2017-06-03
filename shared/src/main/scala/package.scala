package edu.holycross.shot

import edu.holycross.shot.cite._

package object dse {

  // The four verbs of the DSE model.
  val illustrates = Cite2Urn("urn:cite2:cite:dseverbs.2017a:illustrates")
  val illustratedBy = Cite2Urn("urn:cite2:cite:dseverbs.2017a:illustratedBy")

  val hasOnIt = Cite2Urn("urn:cite2:cite:dseverbs.2017a:hasOnIt")
  val appearsOn = Cite2Urn("urn:cite2:cite:dseverbs.2017a:appearsOn")


  /** True if a given URN is a CTS URN.
  *
  * @param u URN to examine.
  */
  def isCtsUrn(u: Urn): Boolean = {
    val parts = u.toString.split(":")
    (parts(1) == "cts")
  }

  /** True if a given URN is a CITE2 URN.
  *
  * @param u URN to examine.
  */
  def isCite2Urn(u: Urn): Boolean = {
    val parts = u.toString.split(":")
    (parts(1) == "cite2")
  }
}
