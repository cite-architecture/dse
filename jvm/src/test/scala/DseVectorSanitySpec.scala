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




  "A DSE Vector" should "have no duplicate text passages" in  {
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
      fail("Should not have created DSE from CEX with multiple images for one  surface")

    } catch {
      case exc: Exception => assert(exc.toString.contains("One or more surfaces indexed to multiple images"))
      case t: Throwable => fail("Should have thrown java.lang.Exception, but instead threw this: " + t)
    }
  }




  it should "allow multiple surfaces for a single reference image" in  {
    val bifolioFile = "jvm/src/test/resources/e3triples.cex"
    val triples = Source.fromFile(bifolioFile)
    val bifolioTriples = triples.getLines.toVector.tail.mkString("\n")
    triples.close
    val testCollection = Cite2Urn("urn:cite2:units:testdse.v1:")
    val dse = DseVector.fromTextTriples(bifolioTriples, testCollection)


    val img = Cite2Urn("urn:cite2:hmt:e3bifolio.v1:E3_109v_110r")
    val expected = Set(Cite2Urn("urn:cite2:hmt:e3.v1:109v"), Cite2Urn("urn:cite2:hmt:e3.v1:110r"))
    val actual = dse.tbsForImage(img)
    assert( actual ==  expected)
  }

  it should "validate that a surface is consistently indexed to a single reference image" in {
    val mixedPages = "jvm/src/test/resources/inconsistent.cex"
    val buff = Source.fromFile(mixedPages)
    val mixedPageCex = buff.getLines.mkString("\n")
    buff.close
    try {
      val dse = DseVector(mixedPageCex)
      fail("Should not have created DSE from CEX with inconsistent surfaces for text on an image")

    } catch {
      case exc: Exception => assert(exc.toString.contains("One or more surfaces indexed to multiple images"))
      case t: Throwable => fail("Should have thrown java.lang.Exception, but instead threw this: " + t)
    }
  }



  it should  "validate that something else happens" in pending /*{

  }*/
}
