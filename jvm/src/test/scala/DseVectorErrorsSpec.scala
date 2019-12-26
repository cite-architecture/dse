package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.scm._
import scala.io.Source


class DseVectorErrorsSpec extends FlatSpec {

  val cexSrcFile1= "jvm/src/test/resources/hmt-2018e-errors.cex"
  val buff1 = Source.fromFile(cexSrcFile1)
  val cexSrc = buff1.getLines.mkString("\n")
  buff1.close
  val psgVector = DseVector.rawFromCex(cexSrc)





  "A Digital Scholarly Edition" should "log duplicate text entries in source DsePassages" in {
    try {
      val dseV = DseVector(psgVector)
      fail("Should have thrown an Exception")
    } catch {
      case iae: java.lang.IllegalArgumentException => assert(iae.toString.contains("Duplicate text passages in constructor to DseVector"))
      case t: Throwable => {
        fail("Should have thrown an IllegalArgumentException, but threw " + t)
      }
    }
  }

}
