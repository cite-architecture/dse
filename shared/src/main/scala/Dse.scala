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
    val citerepo = CiteLibrary(cexSrc, delimiter, delimiter2)

    val cex = CexParser(cexSrc)
    val dataModels = stripHeader(cex.blockVector("datamodels"))
    val collections = dataModels.map { s =>
      val cols = s.split("#")
      Cite2Urn(cols(0))
    }
    // SELECT ALL DATA FOR EACH COLLECTION:
    println("COLLS: " + collections)


    val passages = Vector.empty[DsePassage]
    Dse(passages)
  }



}
