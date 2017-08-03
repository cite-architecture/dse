package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._


/**
*/
class DseSourceSpec extends FlatSpec {



  "A DseSource" should "make a digital scholarly edition from a file with CEX data" in  {
    val fName = "jvm/src/test/resources/311rv.cex"
    val dse = DseSource.fromFile(fName)
    dse match {
      case edition: Dse => assert(true)
      case _ => fail("Should have made a Dse")
    }
    val expectedSize = 17
    assert(dse.size == expectedSize)
  }


}
