# `dse`

A cross-platform library for working with digital diplomatic scholarly editions.

## Current version: 2.0.0

See [release notes](releases.md).

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)

## Using, building, testing

`dse` is compiled for both the JVM and ScalaJS using scala version  2.11.

To build from source and test for a given version, use normal `sbt` commands (`compile`, `test` ...).


## The model

Diplomatic scholarly editions relate three entities:

1. a diplomatic transcription of texts
2. a sequence of text-bearing surfaces
3. visual documentary evidence

Passages of text are identified with CTS URNs;  text-bearing surfaces and visual evidence are identified with CITE2 URNs.  


## CEX serialization of the DSE data model

To serialize DSE data in CEX, first define one or more collections with the three required  properties, then include a `#datamodels` section declaring that your collection follows the DSE data model. The following example illustrates these two blocks of a CEX source:


    #!citeproperties
    Property#Label#Type#Authority list
    urn:cite2:hmt:dse.2017a.urn:#DSE record#Cite2Urn#
    urn:cite2:hmt:dse.2017a.label:#Label#String#
    urn:cite2:hmt:dse.2017a.passage:#Text passage#CtsUrn#
    urn:cite2:hmt:dse.2017a.imageroi:#Image region of interest#Cite2Urn#
    urn:cite2:hmt:dse.2017a.surface:#Artifact surface#Cite2Urn#

    #!datamodels
    Collection#Model#Label#Description
    urn:cite2:hmt:dse.2017a:#urn:cite2:dse:datamodel.v1:#DSE model#Diplomatic Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.
