---
layout: page
title: CEX serialization of the DSE data model
---


Like any other CITE Collection, DSE collections are declared in a CEX `citecollections` block, and include their data in a `citedata` block.

All CITE collections require properties for identifying URN and human-readable label.  To implement the DSE model, define each DSE collection with three further properties named `passage`, `imageroi` and `surface`.

In your CEX, include a `#datamodels` section declaring that your collection follows the DSE data model. The following example illustrates these two blocks of a CEX source:


    #!citeproperties
    Property#Label#Type#Authority list
    urn:cite2:hmt:dse.2017a.urn:#DSE record#Cite2Urn#
    urn:cite2:hmt:dse.2017a.label:#Label#String#
    urn:cite2:hmt:dse.2017a.passage:#Text passage#CtsUrn#
    urn:cite2:hmt:dse.2017a.imageroi:#Image region of interest#Cite2Urn#
    urn:cite2:hmt:dse.2017a.surface:#Artifact surface#Cite2Urn#

    #!datamodels
    Collection#Model#Label#Description
    urn:cite2:hmt:dse.2017a:#urn:cite2:dse:datamodel.v1:#DSE model#Documented Scholarly Edition (DSE) model.  See documentation at <http://cite-architecture.org/dse/>.
