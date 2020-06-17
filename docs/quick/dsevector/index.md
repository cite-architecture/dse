---
title:  "DseVector: quick start"
layout: page
---

**Version 7.1.2**


Import the library:


```scala
import edu.holycross.shot.dse._
```


You can create a `DseVector` from a collectoin in CiteLibrary, or diretly from CEX-format source data.


## Buiding from CEX data


```scala

val cex = """
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
urn:cite2:hmt:dse.2017a:#urn:cite2:dse:datamodel.v1:#DSE model#Documented Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.

#!citedata
urn#label#passage#imageroi#surface
urn:cite2:hmt:dse.2017a:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA:311r
// etc, etc, etc
"""

val dse1 = DseVector(cex)
```

In the JVM environment, you can use the `DseSource` object's `fromFile` method to instantiate a `DseVector` directly from a file in CEX format.  (This is a small sample with a total of 17 text passages on 2 pages.  We'll use this sample data set in the following examples.)


```scala
val dse = DseSource.fromFile("jvm/src/test/resources/311rv.cex")
```


## Working with a `DseVector`


To work with URNs, you'll need to import the `cite` library.

```scala
import edu.holycross.shot.cite._
```


### Find text contents and documentary images for a text-bearing surface

```scala
val page1 = Cite2Urn("urn:cite2:hmt:msA:311r")
// page1: Cite2Urn = Cite2Urn("urn:cite2:hmt:msA:311r")
val passages1 = dse.textsForTbs(page1)
// passages1: Vector[CtsUrn] = Vector(
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A2"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A4"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A5"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A6"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A7"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A8"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A9"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A10"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A11")
// )
assert(passages1.size == 10)

val image = dse.imageForTbs(page1)
// image: Option[Cite2Urn] = None
println(image)
// None
//assert(image == Cite2Urn("urn:cite2:hmt:vaimg:VA311RN_0481"))

val page2 = Cite2Urn("urn:cite2:hmt:msA:311v")
// page2: Cite2Urn = Cite2Urn("urn:cite2:hmt:msA:311v")
val passages2 = dse.textsForTbs(page2)
// passages2: Vector[CtsUrn] = Vector(
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B1"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B2"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B3"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B4"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B5"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B6"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.B7")
// )
assert(passages2.size == 7)

val image2 = dse.imageForTbs(page2)
// image2: Option[Cite2Urn] = None
```

### Find documentary image for a text passage

```scala
val passage = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A5")
// passage: CtsUrn = CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A5")
val image3 = dse.imageForText(passage)
// image3: Option[Cite2Urn] = Some(
//   Cite2Urn("urn:cite2:hmt:vaimg.v1:VA311RN_0481")
// )
```


### Find surfaces and texts illustrated by an image

```scala
val img = Cite2Urn("urn:cite2:hmt:vaimg:VA311RN_0481")
// img: Cite2Urn = Cite2Urn("urn:cite2:hmt:vaimg:VA311RN_0481")

val surfaces = dse.tbsForImage(img)
// surfaces: Set[Cite2Urn] = Set(Cite2Urn("urn:cite2:hmt:msA.v1:311r"))
assert(surfaces.size == 1)
val passages = dse.textsForImage(img)
// passages: Vector[CtsUrn] = Vector(
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A2"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A3"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A4"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A5"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A6"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A7"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A8"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A9"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A10"),
//   CtsUrn("urn:cts:greekLit:tlg5026.msA.hmt:24.A11")
// )
assert(passages.size == 10)
```
