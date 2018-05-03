package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.scm._



class DseVectorSpec extends FlatSpec {


val cexSrc = """
#!citelibrary

name#Sample DSE data
urn#urn:cite2:cite:cextest.2017_1:dse
license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.

#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:hmt:dse.2017a:#DSE relations of the Venetus A manuscriptscript#urn:cite2:hmt:dse.2017a.label:##CC-attribution-share-alike


#!citeproperties
Property#Label#Type#Authority list
urn:cite2:hmt:dse.2017a.urn:#DSE record#Cite2Urn#
urn:cite2:hmt:dse.2017a.label:#Label#String#
urn:cite2:hmt:dse.2017a.passage:#Text passage#CtsUrn#
urn:cite2:hmt:dse.2017a.imageroi:#Image region of interest#Cite2Urn#
urn:cite2:hmt:dse.2017a.surface:#ertifact surfact#Cite2Urn#

#!datamodels
Collection#Model#Label#Description
urn:cite2:hmt:dse.2017a:#urn:cite2:dse:datamodel.v1:#DSE model#Diplomatic Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.

#!citedata
urn#label#passage#imageroi#surface
urn:cite2:hmt:dse.2017a:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main4#Main scholion 4, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A5#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main5#Main scholion 5, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A6#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main6#Main scholion 6, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A7#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main7#Main scholion 7, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A8#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main8#Main scholion 8, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A9#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main9#Main scholion 9, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A10#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r.main10#Main scholion 10, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A11#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311v.main1#Main scholion 1, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B1#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.231,0.1216,0.642,0.033#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main2#Main scholion 2, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B2#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.544,0.1351,0.329,0.0158#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main3#Main scholion 3, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B3#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.238,0.1471,0.552,0.0203#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main4#Main scholion 4, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B4#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.22,0.4565,0.218,0.1659#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main5#Main scholion 5, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B5#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.221,0.6171,0.526,0.1269#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main6#Main scholion 6, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B6#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.225,0.7312,0.652,0.0398#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v.main7#Main scholion 7, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B7#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.227,0.7628,0.655,0.0413#urn:cite2:hmt:msA.v1:311v

"""



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

  it should "find images for a given TBS" in {
    val dse = DseVector(cexSrc)
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481"))
    assert(dse.imagesForTbs(surface) == expected)
  }

  it should "find images for a given text passage" in {
    val dse = DseVector(cexSrc)
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481"))
    val psg = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    assert(dse.imagesForText(psg) == expected)
  }


  it should "find text passages for a given TBS" in {
    val dse = DseVector(cexSrc)
    val expectedSize = 10
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    assert(dse.textsForTbs(surface).size == expectedSize)
  }


  it should "find text passages for a given image" in {
    val dse = DseVector(cexSrc)
    val expectedSize = 10
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    assert(dse.textsForImage(img).size == expectedSize)
  }

  it should "find TBS illustrated by a given image" in {
    val dse = DseVector(cexSrc)
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val expected = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"))
    assert(dse.tbsForImage(img.dropExtensions) == expected)
  }


  it should "compose ICT URLs for a given surface" in {
    val pg = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818"

    assert(dse.ictForSurface(pg) == expected)
  }


  it should "compose ICT URLs for a given image" in {
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578&urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818"
    assert(dse.ictForImage(img) == expected)
  }
  it should "compose ICT URLs for a given text passage" in {
    val scholion = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024"
    assert(dse.ictForText(scholion) == expected)
  }

  it should "composte an ICT URL for an image when no overlays" in {
    //val pg = "urn:cite2:hmt:msA.v1:insidefrontcover"
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VAMSInside_front_cover_versoN_0500")
    val dse = DseVector(cexSrc)
    val expected = "http://www.homermultitext.org/ict2/?urn=" + img
    assert (dse.ictForImage(img) == expected)
  }

}
