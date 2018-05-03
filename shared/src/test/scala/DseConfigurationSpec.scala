package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.scm._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._



class DseConfigurationSpec extends FlatSpec {

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
urn:cite2:hmt:dse.2017a:311r_main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main4#Main scholion 4, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A5#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main5#Main scholion 5, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A6#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main6#Main scholion 6, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A7#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main7#Main scholion 7, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A8#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main8#Main scholion 8, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A9#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main9#Main scholion 9, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A10#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311r_main10#Main scholion 10, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A11#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818#urn:cite2:hmt:msA.v1:311r
urn:cite2:hmt:dse.2017a:311v_main1#Main scholion 1, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B1#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.231,0.1216,0.642,0.033#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main2#Main scholion 2, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B2#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.544,0.1351,0.329,0.0158#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main3#Main scholion 3, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B3#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.238,0.1471,0.552,0.0203#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main4#Main scholion 4, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B4#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.22,0.4565,0.218,0.1659#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main5#Main scholion 5, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B5#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.221,0.6171,0.526,0.1269#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main6#Main scholion 6, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B6#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.225,0.7312,0.652,0.0398#urn:cite2:hmt:msA.v1:311v
urn:cite2:hmt:dse.2017a:311v_main7#Main scholion 7, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B7#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.227,0.7628,0.655,0.0413#urn:cite2:hmt:msA.v1:311v

"""

  val repo = CiteCollectionRepository(cexSrc,"#",",")
  //val bigCex = "shared/src/test/resources/va-dse.cex"
  //val bigRepo = CiteRepositorySource.fromFile(testCex)

  val collUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:dse.2017a:") 
  val lbl:String = "DSE model of Venetus A manuscript"
  val psgPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:dse.2017a.passage:")
  val imgPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:dse.2017a.imageroi:")
  val surPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:dse.2017a.surface:")

  "The DseConfiguration" should "build" in {
      val dseExt:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
      assert(dseExt.label == lbl)
  }

  it should "report the number of objects" in {
      val dseExt:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
      assert(dseExt.size == 17)
  }

  it should "report the number of TBS cited in a Collection" in {
   val dseExt:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expected = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"), Cite2Urn("urn:cite2:hmt:msA.v1:311v"))
    assert (dseExt.tbs == expected)
  }

  it should "report the number of texts cited in a Collection" in {
   val dseExt:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expected = Set(CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:"))
    assert(dseExt.texts == expected)
  }

  it should "report the number of image collections cited in a Collection" in {
   val dseExt:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:"))
    assert(dseExt.imageCollections == expected)
  }

  it should "find images for a given TBS" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481"))
    assert(dse.imagesForTbs(surface) == expected)
  }

  it should "find images for a given text passage" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481"))
    val psg = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3")
    assert(dse.imagesForText(psg) == expected)
  }

  it should "find text passages for a given TBS" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expectedSize = 10
    def surface = Cite2Urn("urn:cite2:hmt:msA.v1:311r")
    assert(dse.textsForTbs(surface).size == expectedSize)
  }

  it should "find text passages for a given image" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val expectedSize = 10
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    assert(dse.textsForImage(img).size == expectedSize)
  }

  it should "find TBS illustrated by a given image" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
    val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA311RN_0481")
    val expected = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"))
    assert(dse.tbsForImage(img.dropExtensions) == expected)
  }


}
