package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.scm._
import scala.io.Source


class DseVectorSpec extends FlatSpec {


val cexSrcFile1= "shared/src/test/resources/cex1.cex"
val buff1 = Source.fromFile(cexSrcFile1)
val cexSrc = buff1.getLines.mkString("\n")
buff1.close

val cexSrcFile2= "shared/src/test/resources/cex2.cex"
val buff2 = Source.fromFile(cexSrcFile2)
val cexSrc2 = buff2.getLines.mkString("\n")
buff2.close




  "A Digital Scholarly Edition" should "identify the set of TBS in the library" in {
    val dse = DseVector(cexSrc)
    val expected = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"), Cite2Urn("urn:cite2:hmt:msA.v1:311v"))
    assert (dse.tbs == expected)
  }

  it should "identify the set of texts in the library" in {
    val dse = DseVector(cexSrc)
    val expected = Set(CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:"))
    assert(dse.texts == expected)
  }

  it should "identify the set of image collections in the library" in {
    val dse = DseVector(cexSrc)
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:"))
    assert(dse.imageCollections == expected)
  }

  it should "find a reference image for a given TBS" in {
    val dse = DseVector(cexSrc)
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val expected = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    assert(dse.imageForTbs(surface) == expected)
  }

  it should "find a reference image for a given text passage" in {
    val dse = DseVector(cexSrc)
    val expected = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val psg = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    assert(dse.imageForText(psg) == expected)
  }

  it should "find image including RoI for a given text passage" in {
    val dse = DseVector(cexSrc)
    val expected = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024")
    val psg = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    assert(dse.imageWRoiForText(psg) == expected)
  }


  it should "find text passages for a given TBS" in {
    val dse = DseVector(cexSrc)
    val expectedSize = 10
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    assert(dse.textsForTbs(surface).size == expectedSize)
  }

  it should "maintain order of text passages for a given TBS" in {
    val dse = DseVector(cexSrc)
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val texts = dse.textsForTbs(surface)
    val expectedOrder = Vector(
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A2"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A4"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A5"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A6"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A7"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A8"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A9"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A10"),
      CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A11")
    )
    assert(texts == expectedOrder)
  }


  it should "find text passages for a given image" in {
    val dse = DseVector(cexSrc)
    val expectedSize = 10
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    assert(dse.textsForImage(img).size == expectedSize)
  }

  it should "find a set of TBS illustrated by a given image" in {
    val dse = DseVector(cexSrc)
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val expected = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"))
    assert(dse.tbsForImage(img.dropExtensions) == expected)
  }


  it should "compose ICT URLs for a given surface" in {
    val pg = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val dse = DseVector(cexSrc)
    val actual = dse.ictForSurface(pg)

    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818"


    assert(actual == expected)
  }


  it should "compose ICT URLs for a given image" in {

    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818"
    assert(dse.ictForImage(img) == expected)
  }

  it should "compose ICT URLs for a given text passage" in {
    val scholion = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024"
    assert(dse.ictForText(scholion) == expected)
  }

  it should "composite an ICT URL for an image when no overlays" in {
    //val pg = "urn:cite2:hmt:msA.v1:insidefrontcover"
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VAMSInside_front_cover_versoN_0500")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=" + img
    assert (dse.ictForImage(img) == expected)
  }


  it should "create a new DseVector by concatenating a second DseVector" in {
    val dse1 = DseVector(cexSrc)
    val dse2 = DseVector(cexSrc2)
    val catted = dse1 ++ dse2
    assert(catted.size == (dse1.size + dse2.size))
  }
}
