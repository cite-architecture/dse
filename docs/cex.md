---
layout: page
title: CEX serialization of the DSE data model
---


To serialize DSE data in CEX, define each DSE collection with the three properties `passage`, `imageroi` and `surface`, in addition to the required properties for idetnifying URN and human-readable label.  Then include in your CEX a `#datamodels` section declaring that your collection follows the DSE data model. The following example illustrates these two blocks of a CEX source:


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


Like any other CITE Collection, DSE collections are declared in a CEX `citecollections` block, and include their data in a `citedata` block.
