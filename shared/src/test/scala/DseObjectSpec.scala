package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.scm._


class DseObjectSpec extends FlatSpec {


val cexSrc = """
#!citelibrary

name#Data for testing DSE relations
urn#urn:cite2:cite:testdata.2017_1:dse
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
urn:cite2:hmt:dse.2017a:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main4#Main scholion 4, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A5#urn:cite2:hmt:vaimg:VA311RN_0481@0.643,0.2523,0.186,0.024#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main5#Main scholion 5, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A6#urn:cite2:hmt:vaimg:VA311RN_0481@0.648,0.2763,0.188,0.0578#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main6#Main scholion 6, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A7#urn:cite2:hmt:vaimg:VA311RN_0481@0.64,0.3326,0.196,0.0848#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main7#Main scholion 7, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A8#urn:cite2:hmt:vaimg:VA311RN_0481@0.201,0.4129,0.63,0.2995#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main8#Main scholion 8, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A9#urn:cite2:hmt:vaimg:VA311RN_0481@0.202,0.6922,0.62,0.0345#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main9#Main scholion 9, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A10#urn:cite2:hmt:vaimg:VA311RN_0481@0.206,0.7102,0.617,0.0616#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311r.main10#Main scholion 10, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A11#urn:cite2:hmt:vaimg:VA311RN_0481@0.201,0.7545,0.639,0.0818#urn:cite2:hmt:msA:311r
urn:cite2:hmt:dse.2017a:311v.main1#Main scholion 1, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B1#urn:cite2:hmt:vaimg:VA311VN_0813@0.231,0.1216,0.642,0.033#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main2#Main scholion 2, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B2#urn:cite2:hmt:vaimg:VA311VN_0813@0.544,0.1351,0.329,0.0158#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main3#Main scholion 3, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B3#urn:cite2:hmt:vaimg:VA311VN_0813@0.238,0.1471,0.552,0.0203#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main4#Main scholion 4, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B4#urn:cite2:hmt:vaimg:VA311VN_0813@0.22,0.4565,0.218,0.1659#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main5#Main scholion 5, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B5#urn:cite2:hmt:vaimg:VA311VN_0813@0.221,0.6171,0.526,0.1269#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main6#Main scholion 6, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B6#urn:cite2:hmt:vaimg:VA311VN_0813@0.225,0.7312,0.652,0.0398#urn:cite2:hmt:msA:311v
urn:cite2:hmt:dse.2017a:311v.main7#Main scholion 7, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B7#urn:cite2:hmt:vaimg:VA311VN_0813@0.227,0.7628,0.655,0.0413#urn:cite2:hmt:msA:311v

"""


  val dse = Dse(cexSrc)


  val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val surface = Cite2Urn("urn:cite2:hmt:msA.2017a:12r")
  val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

  "The Dse object" should "drop headers from data model source" in {
    val cex = CexParser(cexSrc)
    val stripped = Dse.stripHeader(cex.blockVector("datamodels"))
    assert(stripped.size == 1)
  }

  it should "build a DsePassage from a CiteObject" in {

    val libr = CiteLibrary(cexSrc, "#", ",")




    val citableObjects = CiteLibrary(cexSrc, "#", ",").collectionRepository.get.citableObjects
    val testUrn =  Cite2Urn("urn:cite2:hmt:dse.2017a:311r.main1")

    val matchingCites = citableObjects.filter(_.urn == testUrn)
    val firstObject = matchingCites(0)
    val dsePsg = Dse.fromCitableObject(firstObject)
    assert(dsePsg.urn == Cite2Urn("urn:cite2:hmt:dse.2017a:311r.main1"))
    assert(dsePsg.label == "Main scholion 1, 311 recto")
    assert(dsePsg.passage == CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A2"))
    assert(dsePsg.imageroi == Cite2Urn("urn:cite2:hmt:vaimg:VA311RN_0481@0.216,0.0811,0.61,0.0751"))
    assert(dsePsg.surface == Cite2Urn("urn:cite2:hmt:msA:311r"))

  }

  it should "create a Dse from CEX source" in {
   val dse = Dse(cexSrc)
   dse match {
     case dse: Dse => assert(true)
     case _ => fail("Should have created a CiteLibrary")
   }
   val expectedSize =  17
   assert(dse.size == expectedSize)
 }





}
