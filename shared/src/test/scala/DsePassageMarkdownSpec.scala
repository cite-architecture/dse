package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._




class DsePassageMarkdownSpec extends FlatSpec {



  "The DsePassage object" should "compose a markdown string with linked embedded image" in {
    val record = "urn:cite2:hmt:dse.2017a:311r.main1#Main scholion 1, 311 recto#urn:cts:greekLit:tlg5026.msA.hmt:24.A2#urn:cite2:hmt:vaimg.2017a:VA311RN_0481@0.216,0.0811,0.61,0.0751#urn:cite2:hmt:msA:311r"
    val dsePassage = DsePassage(record)
    println(dsePassage.markdown())
  }


}
