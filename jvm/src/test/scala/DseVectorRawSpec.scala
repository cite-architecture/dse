package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._
import scala.io.Source

/** Specify what constitutes a sane collection of [[DsePassage]]s.
*/
class DseVectorRawSpec extends FlatSpec {


  val cexSrcFile1= "jvm/src/test/resources/hmt-2018e-errors.cex"
  val buff1 = Source.fromFile(cexSrcFile1)
  val cexSrc = buff1.getLines.mkString("\n")
  buff1.close
  val psgVector = DseVector.rawFromCex(cexSrc)

  "The DSEVector object" should "uncritically create a Vector of DsePassages from CEX source" in {
    psgVector match {
      case v: Vector[DsePassage] => assert(true)
      case _ => fail("Should have created a Vector of DsePassages")
    }
    println("SIZE: = " + psgVector.size)
    assert(psgVector.nonEmpty, "Should have created some DsePassages")
  }

  it should "find duplicate text passages" in {
    val dupes = DseVector.duplicatePassages(psgVector)
    println("DUPES: " + dupes)
    assert(dupes.nonEmpty, "Should have found some duplicates")
  }

  it should "find surfaces indexed to more than one image" in pending

  it should "find images indexed to more than one surface" in pending

}
