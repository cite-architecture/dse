package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.scm._


class DseLibrarySpec extends FlatSpec {

val cexSrc = """
#!citelibrary

name#Sample DSE data
urn#urn:cite2:cite:cextest.2017_1:dse
license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.

#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:hmt:dse.v1:#DSE relations of the Venetus A manuscriptscript#urn:cite2:hmt:dse.v1.label:#urn:cite2:hmt:dse.v1.seq:#CC-attribution-share-alike

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:hmt:dse.v1.urn:#DSE record#Cite2Urn#
urn:cite2:hmt:dse.v1.label:#Label#String#
urn:cite2:hmt:dse.v1.passage:#Text passage#CtsUrn#
urn:cite2:hmt:dse.v1.imageroi:#Image region of interest#Cite2Urn#
urn:cite2:hmt:dse.v1.surface:#artifact surface#Cite2Urn#
urn:cite2:hmt:dse.v1.seq:#sequence#number#

#!datamodels
Collection#Model#Label#Description
urn:cite2:hmt:dse.v1:#urn:cite2:cite:datamodels.v1:dsemodel#DSE model#Diplomatic Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.

#!citedata
urn#label#passage#imageroi#surface#seq
urn:cite2:hmt:dse.v1:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA.v1:311r#1
urn:cite2:hmt:dse.v1:311r.main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA.v1:311r#2
urn:cite2:hmt:dse.v1:311r.main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA.v1:311r#3
urn:cite2:hmt:dse.v1:311r.main4#Main scholion 4, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A5#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.643,0.2523,0.186,0.024#urn:cite2:hmt:msA.v1:311r#4
urn:cite2:hmt:dse.v1:311r.main5#Main scholion 5, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A6#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.648,0.2763,0.188,0.0578#urn:cite2:hmt:msA.v1:311r#5
urn:cite2:hmt:dse.v1:311r.main6#Main scholion 6, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A7#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.64,0.3326,0.196,0.0848#urn:cite2:hmt:msA.v1:311r#6
urn:cite2:hmt:dse.v1:311r.main7#Main scholion 7, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A8#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.4129,0.63,0.2995#urn:cite2:hmt:msA.v1:311r#7
urn:cite2:hmt:dse.v1:311r.main8#Main scholion 8, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A9#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.202,0.6922,0.62,0.0345#urn:cite2:hmt:msA.v1:311r#8
urn:cite2:hmt:dse.v1:311r.main9#Main scholion 9, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A10#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.206,0.7102,0.617,0.0616#urn:cite2:hmt:msA.v1:311r#9
urn:cite2:hmt:dse.v1:311r.main10#Main scholion 10, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A11#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.201,0.7545,0.639,0.0818#urn:cite2:hmt:msA.v1:311r#10
urn:cite2:hmt:dse.v1:311v.main1#Main scholion 1, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B1#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.231,0.1216,0.642,0.033#urn:cite2:hmt:msA.v1:311v#11
urn:cite2:hmt:dse.v1:311v.main2#Main scholion 2, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B2#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.544,0.1351,0.329,0.0158#urn:cite2:hmt:msA.v1:311v#12
urn:cite2:hmt:dse.v1:311v.main3#Main scholion 3, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B3#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.238,0.1471,0.552,0.0203#urn:cite2:hmt:msA.v1:311v#13
urn:cite2:hmt:dse.v1:311v.main4#Main scholion 4, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B4#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.22,0.4565,0.218,0.1659#urn:cite2:hmt:msA.v1:311v#14
urn:cite2:hmt:dse.v1:311v.main5#Main scholion 5, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B5#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.221,0.6171,0.526,0.1269#urn:cite2:hmt:msA.v1:311v#15
urn:cite2:hmt:dse.v1:311v.main6#Main scholion 6, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B6#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.225,0.7312,0.652,0.0398#urn:cite2:hmt:msA.v1:311v#16
urn:cite2:hmt:dse.v1:311v.main7#Main scholion 7, 311 verso#urn:cts:greekLit:tlg5026.msA.hmt:24.B7#urn:cite2:hmt:vaimg.2017a:VA311VN_0813@0.227,0.7628,0.655,0.0413#urn:cite2:hmt:msA.v1:311v#17

"""

val noDseCex = """
#!citelibrary

name#Sample DSE data
urn#urn:cite2:cite:cextest.2017_1:dse
license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.

#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:hmt:dse.v1:#DSE relations of the Venetus A manuscriptscript#urn:cite2:hmt:dse.v1.label:#urn:cite2:hmt:dse.v1.seq:#CC-attribution-share-alike

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:hmt:dse.v1.urn:#DSE record#Cite2Urn#
urn:cite2:hmt:dse.v1.label:#Label#String#
urn:cite2:hmt:dse.v1.passage:#Text passage#CtsUrn#
urn:cite2:hmt:dse.v1.imageroi:#Image region of interest#Cite2Urn#
urn:cite2:hmt:dse.v1.surface:#artifact surface#Cite2Urn#
urn:cite2:hmt:dse.v1.seq:#sequence#number#

#!datamodels
Collection#Model#Label#Description
urn:cite2:hmt:dsex.v1:#urn:cite2:cite:datamodels.v1:dsemodel#DSE model#Diplomatic Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.

#!citedata
urn#label#passage#imageroi#surface#seq
urn:cite2:hmt:dse.v1:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA.v1:311r#1
urn:cite2:hmt:dse.v1:311r.main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA.v1:311r#2
urn:cite2:hmt:dse.v1:311r.main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA.v1:311r#3

"""
val noDseModelCex = """
#!citelibrary

name#Sample DSE data
urn#urn:cite2:cite:cextest.2017_1:dse
license#Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.

#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:hmt:dse.v1:#DSE relations of the Venetus A manuscriptscript#urn:cite2:hmt:dse.v1.label:#urn:cite2:hmt:dse.v1.seq:#CC-attribution-share-alike

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:hmt:dse.v1.urn:#DSE record#Cite2Urn#
urn:cite2:hmt:dse.v1.label:#Label#String#
urn:cite2:hmt:dse.v1.passage:#Text passage#CtsUrn#
urn:cite2:hmt:dse.v1.imageroi:#Image region of interest#Cite2Urn#
urn:cite2:hmt:dse.v1.surface:#artifact surface#Cite2Urn#
urn:cite2:hmt:dse.v1.seq:#sequence#number#


#!citedata
urn#label#passage#imageroi#surface#seq
urn:cite2:hmt:dse.v1:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA.v1:311r#1
urn:cite2:hmt:dse.v1:311r.main2#Main scholion 2, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A3#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.218,0.1411,0.597,0.024#urn:cite2:hmt:msA.v1:311r#2
urn:cite2:hmt:dse.v1:311r.main3#Main scholion 3, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A4#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.1509,0.615,0.1051#urn:cite2:hmt:msA.v1:311r#3

"""

val noCollectionsAtAll = """
#!cexversion
3.0

#!citelibrary
name#Fragment from Herodotus' Histories, Book VIII on Papyrus Oxyrhynchus 2099, dated to early 2nd century AD.
urn#urn:cite2:cex:fufolio.2018a:POxy2099
license#CC Share Alike.  For details, see more info.


#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0016.tlg001.eng:#book/section#Herodotus#Histories#English, trans. Godley##true#eng

#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.eng:8.22#Themistocles however selected those ships of the Athenians which sailed best, and went round to the springs of drinking-water, cutting inscriptions on the stones there, which the Ionians read when they came to Artemision on the following day. These inscriptions ran thus: "Ionians, ye act not rightly in making expedition against the fathers of your race and endeavouring to enslave Hellas. Best of all were it that ye should come and be
"""

  val lib = CiteLibrary(cexSrc)

  "The Dse object" should "extract a DseVector from a CiteLibrary" in {
    val dsev = DseVector.fromCiteLibrary(lib)
    val expectedSize = 17
    assert(dsev.size == expectedSize)
  }

  it should "return an empty vector if there are no objects in a collection implementing the DSE model" in {
  	val noDSElib = CiteLibrary(noDseCex)
  	val dsev = DseVector.fromCiteLibrary(noDSElib)
  	assert(dsev.size == 0)
  }
 
 it should "return an empty vector if there is no DSE model defined" in {
  	val noDSElib = CiteLibrary(noDseModelCex)
  	val dsev = DseVector.fromCiteLibrary(noDSElib)
  	assert(dsev.size == 0)
  } 

it should "return an empty vector if there are no collections at all defined" in {
  	val noDSElib = CiteLibrary(noCollectionsAtAll)
  	val dsev = DseVector.fromCiteLibrary(noDSElib)
  	assert(dsev.size == 0)
  } 

it should "happily create an empty DseVector" in {
  val dsev = DseVector(Vector[DsePassage]())
  assert(dsev.size == 0)
}

/*
it should "work with this file by returning an empty vector" in {
  val remoteLib = CiteLibrarySource.fromUrl("https://raw.githubusercontent.com/Eumaeus/fuCiteDX/master/CEX_Tutorial/4_Image_Collection.cex")
  //val dsev = DseVector.fromCiteLibrary(remoteLib)
  lazy val dseVec: DseVector = DseVector.fromCiteLibrary(remoteLib)

  assert( dseVec.size == 0)

}
*/

}
