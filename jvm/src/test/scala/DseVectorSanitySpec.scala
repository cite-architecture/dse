package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._
import scala.io.Source

/** Specify what constitutes a sane collection of [[DsePassage]]s.
*/
class DseVectorSanitySpec extends FlatSpec {


  val cexSrcFile1= "jvm/src/test/resources/cex1.cex"
  val buff1 = Source.fromFile(cexSrcFile1)
  val cexSrc = buff1.getLines.mkString("\n")
  buff1.close

  val cexSrcFile2= "jvm/src/test/resources/cex2.cex"
  val buff2 = Source.fromFile(cexSrcFile2)
  val cexSrc2 = buff2.getLines.mkString("\n")
  buff2.close




  "A DSE Vector" should "have no duplicate text passages" in {
    val dupeFile = "jvm/src/test/resources/duplicateText.cex"
    val buff = Source.fromFile(dupeFile)
    val dupeCex = buff.getLines.mkString("\n")
    buff.close
    try {
      val dse1 = DseVector(dupeCex)
      fail("Should not have created DSE from CEX with duplicate recrods")
    } catch {
      case coe: CiteObjectException => assert(coe.toString.contains("There are two objects with URN"))
      case t: Throwable => fail("Should have thrown CiteObjectException, but instead threw this: " + t)
    }

  }
  it should "allow only one reference image per surface" in {
    val dupeFile = "jvm/src/test/resources/duplicateImage.cex"
    val buff = Source.fromFile(dupeFile)
    val dupeCex = buff.getLines.mkString("\n")
    buff.close
    try {
      val dse = DseVector(dupeCex)

      def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
      //val imgs = dse.imageForTbs(surface)

      fail("Should not have created DSE from CEX with multiple images for one  surface")
    } catch {
      case exc: Exception => assert(exc.toString.contains("One or more surfaces indexed to multiple images"))
      case t: Throwable => fail("Should have thrown java.lang.Exception, but instead threw this: " + t)
    }
  }
  it should "allow multiple surfaces for a single reference image" in pending
  it should "validate consistency of indexing of texts and surfaces to reference image" in pending
}
