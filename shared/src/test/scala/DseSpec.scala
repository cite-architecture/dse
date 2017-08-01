package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.scm._



class DseSpec extends FlatSpec {


val cexSrc = """

#!citelibrary
name#Demo of DSE structure: Venetus A manuscript, folio 12 recto
urn#urn:cite2:dse:demo.v1:va12r
license#public domain

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#language
urn:cts:greekLit:tlg0012.tlg001.msA:#book/line#Homeric poetry#Iliad#HMT project edition of the Venetus A##true#grc

#!ctsdata
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος


#!citecollections
// Text-bearing surfaces:
urn:cite2:hmt:msA.v1:#Pages of the Venetus A manuscriptscript#urn:cite2:hmt:msA.v1.label:#urn:cite2:hmt:msA.v1.sequence:#CC-attribution-share-alike
#!citeproperties
urn:cite2:hmt:msA.v1.urn:#URN#Cite2Urn#
urn:cite2:hmt:msA.v1.label:#Label#String#
urn:cite2:hmt:msA.v1.siglum:#Manuscript siglum#String#
urn:cite2:hmt:msA.v1.sequence:#Page sequence#Number#
urn:cite2:hmt:msA.v1.rv:#Recto or Verso#String#recto,verso
urn:cite2:hmt:msA.v1.codex:#Codex URN#Cite2Urn#


#!citecollections
// Documentary images:
urn:cite2:hmt:vaimg.2017a:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.2017a.caption:##CC-attribution-share-alike
#!citeproperties
urn:cite2:hmt:vaimg.2017a.urn:#URN#Cite2Urn#
urn:cite2:hmt:vaimg.2017a.caption:#Caption#String#
urn:cite2:hmt:vaimg.2017a.rights:#Rights#String#

#!citedata
//
//Data block for the collection of text-bearing surfaces.
siglum#sequence#urn#rv#label#codex
msA#1#urn:cite2:hmt:msA.v1:12r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 12 recto#urn:cite2:hmt:codex:msA

#!citedata
# Data block for the collection of documentary images.
urn#caption#rights
urn:cite2:hmt:vaimg.2017a:VA012RN_0013.2017#Natural light photograph of Venetus A: Marcianus Graecus Z. 454 (= 822), folio 12, recto#This image was derived from an original ©2007, Biblioteca Nazionale Marciana, Venezie, Italia. The derivative image is ©2010, Center for Hellenic Studies. Original and derivative are licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. The CHS/Marciana Imaging Project was directed by David Jacobs of the British Library.

#!imagedata
urn:cite2:hmt:vaimg.2017a:#local string string#./#urn:cite2:hmt:vaimg.2017a.rights:

#!relations
// 1. Relation of text passages to text-bearing surface:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:appearsOn#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1


// 2. Relation of text passages to documentary image:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1


// 3. Relation of text-bearing surface to documentary image:
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.2017a:VA012RN_0013
urn:cite2:hmt:vaimg.2017a:VA012RN_0013#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cite2:hmt:msA.2017a:12r
"""


/*
  val dse = Dse(cexSrc,"#",",")


  val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val surface = Cite2Urn("urn:cite2:hmt:msA.2017a:12r")
  val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")
*/



  "A Digital Scholarly Edition" should "be instantiated from a CiteLibrary" in pending
  /* {
    dse match {
      case dse: Dse => assert(true)
      case _ => fail("Should have created a CiteLibrary")
    }
  }

  it should "identify the set of TBS in the library" in {
    val expected = Set(surface)
    assert (dse.tbs == expected)
  }
  it should "identify the set of texts in the library" in {
    val expected = Set(psg.dropPassage)
    assert(dse.texts == expected)
  }
  it should "identify the set of image collections in the library" in {
    val expected = Set(img.dropSelector)
    assert(dse.imageCollections == expected)
  }
  it should "find images for a given TBS" in {
    val expected = Set(Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA012RN_0013"))
    assert(dse.imagesForTbs(surface) == expected)
  }
  it should "find images for a given text passage" in {
    val expected = Set(img)
    assert(dse.imagesForText(psg) == expected)
  }
  it should "find text passages for a given TBS" in {
    val expected = Set(psg)
    assert(dse.textsForTbs(surface) == expected)
  }
  it should "find text passages for a given image" in {
    val expected = Set(psg)
    assert(dse.textsForImage(img.dropExtensions) == expected)
  }
  it should "find TBS illustrated by a given image" in {
    val expected = Set(surface)
    assert(dse.tbsForImage(img.dropExtensions) == expected)
  }



  it should "be possible to instantiate from a CEX source" in {
    dse match {
      case dse: Dse => assert(true)
      case _ => fail("Should have created a Dse object")
    }
  }
  */


}
