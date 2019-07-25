package edu.holycross.shot.dse

import scala.scalajs.js
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._

object Main extends js.JSApp {
  def main(): Unit = {
    val cexSrc = """

#!citelibrary
name#Demo of DSE structure: Venetus A manuscript, folio 12 recto
urn#urn:cite2:dse:demo.2017a:va12r
license#public domain

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:greekLit:tlg0012.tlg001.msA:#book/line#Homeric poetry#Iliad#HMT project edition of the Venetus A##true

#!ctsdata
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος


#!citecatalog
# Text-bearing surfaces:
collection#urn:cite2:hmt:msA.v1:#Pages of the Venetus A manuscriptscript#urn:cite2:hmt:msA.v1.label:#urn:cite2:hmt:msA.v1.sequence:#CC-attribution-share-alike

property#urn:cite2:hmt:msA.v1.urn:#URN#Cite2Urn#
property#urn:cite2:hmt:msA.v1.label:#Label#String#
property#urn:cite2:hmt:msA.v1.siglum:#Manuscript siglum#String#
property#urn:cite2:hmt:msA.v1.sequence:#Page sequence#Number#
property#urn:cite2:hmt:msA.v1.rv:#Recto or Verso#String#recto,verso
property#urn:cite2:hmt:msA.v1.codex:#Codex URN#Cite2Urn#


# Documentary images:
collection#urn:cite2:hmt:vaimg.2017a:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.2017a.caption:##CC-attribution-share-alike

property#urn:cite2:hmt:vaimg.2017a.urn:#URN#Cite2Urn#
property#urn:cite2:hmt:vaimg.2017a.caption:#Caption#String#
property#urn:cite2:hmt:vaimg.2017a.rights:#Rights#String#

#!citedata
#
#Data block for the collection of text-bearing surfaces.
siglum#sequence#urn#rv#label#codex
msA#1#urn:cite2:hmt:msA.v1:12r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 12 recto#urn:cite2:hmt:codex:msA

#!citedata
# Data block for the collection of documentary images.
urn#caption#rights
urn:cite2:hmt:vaimg.2017a:VA012RN_0013.2017#Natural light photograph of Venetus A: Marcianus Graecus Z. 454 (= 822), folio 12, recto#This image was derived from an original ©2007, Biblioteca Nazionale Marciana, Venezie, Italia. The derivative image is ©2010, Center for Hellenic Studies. Original and derivative are licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. The CHS/Marciana Imaging Project was directed by David Jacobs of the British Library.

#!imagedata
urn:cite2:hmt:vaimg.2017a:#local string string#./#urn:cite2:hmt:vaimg.2017a.rights:

#!relations
# 1. Relation of text passages to text-bearing surface:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:appearsOn#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1


# 2. Relation of text passages to documentary image:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1


# 3. Relation of text-bearing surface to documentary image:
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.2017a:VA012RN_0013
urn:cite2:hmt:vaimg.2017a:VA012RN_0013#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cite2:hmt:msA.2017a:12r
"""

    val dse = DseVector(cexSrc)
    println("Made a DSE: " + dse)

  }
}
