package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._




class DsePassageSpec extends FlatSpec {



  "The DsePassage object" should "build a DsePassage from a single line of delimited text" in {
    val record = "urn:cite2:hmt:dse.2017a:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA:311r"
    val dsePassage = DsePassage(record)
    dsePassage match {
      case dsepsg: DsePassage => assert(true)
      case _ => fail("Should have created a DsePassage")
    }

  }

}
