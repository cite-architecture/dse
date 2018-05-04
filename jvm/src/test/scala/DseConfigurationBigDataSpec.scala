package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.scm._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._



class DseConfigurationBigDataSpec extends FlatSpec {


  val bigCex = "shared/src/test/resources/hmt-2018b.cex"
  val repo = CiteRepositorySource.fromFile(bigCex)

  val collUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:va_dse.v1:") 
  val lbl:String = "DSE model of Venetus A manuscript"
  val psgPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:va_dse.v1.passage:")
  val imgPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:va_dse.v1.imageroi:")
  val surPropUrn:Cite2Urn = Cite2Urn("urn:cite2:hmt:va_dse.v1.surface:")

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


  it should "find a Vector of DseRecords for an image" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
   val img = Cite2Urn("urn:cite2:hmt:vaimg.2017a:VA055VN_0557")
   val recVec:Vector[DseRecord] = dse.recordsForImage(img)
   assert(recVec.size == 34)
  }

it should "find a Vector of DseRecords for a surface" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
   val surf = Cite2Urn("urn:cite2:hmt:msA.v1:55v")
   val recVec:Vector[DseRecord] = dse.recordsForSurface(surf)
   assert(recVec.size == 34)
  }

it should "find a Vector of DseRecords for a Vector of Passages" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
   val textVec = Vector(
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.215"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.216"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.217"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.218"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.219"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:4.220")
   )
   val recVec:Vector[DseRecord] = dse.recordsForTextVector(textVec)
   assert(recVec.size == 6)
  }


it should "return a Vector of DseRecords for a Vector of Passages when some, but not all, passages are present" in {
   val dse:DseConfiguration = DseConfiguration(
        repo,
        lbl,
        psgPropUrn,
        imgPropUrn,
        surPropUrn
      )
   val textVec = Vector(
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.610"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.611"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.612"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:2.1")
   )
   val recVec:Vector[DseRecord] = dse.recordsForTextVector(textVec)
   assert(recVec.size == 3)
  }


}
