# `dse`

A cross-platform library for working with digital scholarly editions in the CITE architecture model.

## Status

Planning stages.

## Goals

Implement the CITE architecture's DSE model in a scala library cross-compiled for both JVM and ScalaJS environments by:

- designing the data structures of the DSE model using the `scm` library's `CiteLibrary` object
    - texts, objects and images are available from the library's repositories
    - DSE relations are expressed in the `CiteLibrary`'s collection of `relation` triplets
    - verbs in the `relation` triplets are URNs identifying objects in on or more citable collections
- supporting instantation of a DSE library from CEX source
- providing high-level functions implementing basic DSE relations, such as:
    - text-bearing surface(s) transmitting a given passage of text
    - passages of text appearing on a given text-bearing surface
    - documentary image(s) for a given passage of text or text-bearing surface    
