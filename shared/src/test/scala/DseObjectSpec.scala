package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.citerelation._



class DseObjectSpec extends FlatSpec {


  val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val surface = Cite2Urn("urn:cite2:hmt:msA.2017a:12r")
  val img = Cite2Urn("urn:cite2:hmt:vaimg.v1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901")

  "The DSE object" should "determine if the subject of a triple is a TBS" in {
    val tbs = CiteTriple(surface,hasOnIt,psg)
    assert(Dse.isTbs(tbs))

    val notTbs = CiteTriple(img,illustrates,psg)
    assert(Dse.isTbs(notTbs) ==  false)
  }


  it should "determine if the subject of a triple is a text" in pending

  it should "determine if the subject of a triple is an image" in pending


}
