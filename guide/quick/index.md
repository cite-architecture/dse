---
title:  Quick start
layout: page
---


First import the library:


```scala
import edu.holycross.shot.dse._
```

The two main structures are the `DsePassage` representing the documentary record for a single citable passage of text, and the `DseVector`, a collection of `DsePassage` objects.  The records in a  `DseVector` are ordered for each physical surface, but may or may not be ordered across multiple physical surfaces.


-   [a `DseVector`](dsevector)
