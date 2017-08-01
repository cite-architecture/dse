package edu.holycross.shot.dse
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._




class TriangleSpec extends FlatSpec {


val triangle = """Text#Image#Folio
urn:cts:greekLit:tlg5026.msAext.hmt:24.A1#urn:cite:hmt:vaimg.VA310VN-0812@0.113,0.6141,0.042,0.0173#urn:cite:hmt:msA.310v
urn:cts:greekLit:tlg5026.msAext.hmt:24.B1#urn:cite:hmt:vaimg.VA311VN-0813@0.147,0.2312,0.141,0.0188#urn:cite:hmt:msA.311v
urn:cts:greekLit:tlg5026.msAext.hmt:24.B2#urn:cite:hmt:vaimg.VA311VN-0813@0.128,0.2913,0.069,0.0158#urn:cite:hmt:msA.311v
urn:cts:greekLit:tlg5026.msAext.hmt:24.B3#urn:cite:hmt:vaimg.VA311VN-0813@0.127,0.497,0.067,0.0188#urn:cite:hmt:msA.311v
urn:cts:greekLit:tlg5026.msAext.hmt:24.B4#urn:cite:hmt:vaimg.VA311VN-0813@0.134,0.5495,0.07,0.0233#urn:cite:hmt:msA.311v
urn:cts:greekLit:tlg5026.msAext.hmt:24.B5#urn:cite:hmt:vaimg.VA311VN-0813@0.139,0.5728,0.047,0.018#urn:cite:hmt:msA.311v
urn:cts:greekLit:tlg5026.msAext.hmt:24.1#urn:cite:hmt:vaimg.VA314RN-0484@0.89,0.5736,0.051,0.0218#urn:cite:hmt:msA.314r
urn:cts:greekLit:tlg5026.msAext.hmt:24.2#urn:cite:hmt:vaimg.VA314VN-0816@0.111,0.4557,0.075,0.0315#urn:cite:hmt:msA.314v
urn:cts:greekLit:tlg5026.msAext.hmt:24.3#urn:cite:hmt:vaimg.VA315RN-0485@0.837,0.5368,0.053,0.027#urn:cite:hmt:msA.315r
urn:cts:greekLit:tlg5026.msAext.hmt:24.4#urn:cite:hmt:vaimg.VA315VN-0817@0.123,0.3221,0.095,0.0601#urn:cite:hmt:msA.315v
urn:cts:greekLit:tlg5026.msAext.hmt:24.5#urn:cite:hmt:vaimg.VA315VN-0817@0.125,0.4392,0.076,0.03#urn:cite:hmt:msA.315v
urn:cts:greekLit:tlg5026.msAext.hmt:24.6#urn:cite:hmt:vaimg.VA317RN-0487@0.832,0.6426,0.066,0.0443#urn:cite:hmt:msA.317r
urn:cts:greekLit:tlg5026.msAext.hmt:24.7#urn:cite:hmt:vaimg.VA318RN-0488@0.801,0.4482,0.058,0.0368#urn:cite:hmt:msA.318r
urn:cts:greekLit:tlg5026.msAext.hmt:24.8#urn:cite:hmt:vaimg.VA318RN-0488@0.819,0.5526,0.077,0.0233#urn:cite:hmt:msA.318r
urn:cts:greekLit:tlg5026.msAext.hmt:24.9#urn:cite:hmt:vaimg.VA322RN-0492@0.826,0.6239,0.066,0.0368#urn:cite:hmt:msA.322r
urn:cts:greekLit:tlg5026.msAext.hmt:24.10#urn:cite:hmt:vaimg.VA324RN-0494@0.804,0.5848,0.072,0.0368#urn:cite:hmt:msA.324r
urn:cts:greekLit:tlg5026.msAext.hmt:24.11#urn:cite:hmt:vaimg.VA326RN-0496@0.784,0.3453,0.088,0.027#urn:cite:hmt:msA.326r
urn:cts:greekLit:tlg5026.msAext.hmt:24.12#urn:cite:hmt:vaimg.VA326RN-0496@0.757,0.5773,0.055,0.021#urn:cite:hmt:msA.326r
"""

  "The Dse object" should "have a function to build a DSE from triangle statements" in pending

}
