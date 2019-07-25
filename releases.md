# `dse`: release notes


**5.0.0**: API-breaking change.  `DseVector` object now constructs a `DseVector` from a single CEX string using default values for delimiters.  To specify delimiters explicitly, it includes a new `cex` function.

**4.6.0**: Includes CWB's substantial performance improvements in creating large `DseVector`s.

**4.5.0**:  Modest performance improvement in creating large `DseVector`s.

**4.4.0**:  Validates consistency of `DsePassage`s in a `DseVector`.


**4.3.0**: Adds functions for constructing `DseVector` from CEX triples of passage, image and surface.

**4.2.3**:  Library updates.

**4.2.2**:  Library updates.

**4.2.1**:  Bug fix in underlying library.


**4.2.0**:  Updates to all CITE library dependencies.


**4.1.1**:   Support dynamic setting of character encoding when reading data with `scala.io.Source`.

**4.1.0**:  Adds `++` function to `DseVector`.

**4.0.0**:  API-breaking changes make `DseVector` work with ordered sequences of text nodes for each physical surface.


**3.2.0**:  Add `imagesWRoiForText` function.


**3.1.1**:   Updates to libraries.


**3.0.0**: **Breaking update.** `Dse` renamed to `DseVector` (no additional or differing functionality). `DseConfiguration` added, for working with DSE models implemented in a CiteCollection.

**2.2.1**: ICT functions return more reasonable URL if there are no matches for requested image, surface or text passage.

**2.2.0**:  Add functions to generate URLs pointing to an installation of CITE Architecture's Image Citation Tool for any of the three corners of the DSE relation.

**2.1.0**:  Update to `ohco2` library.  Add full cross build functionality.

**2.0.1**: Update to `citeobj` library fixes a bug in computing path for some image extensions.

**2.0.0**: API-breaking updates to `scm` and `ohco2` libraries, and implementation of DSE model as a CITE extension.  Most of the API remains unchanged despite the fundamentally different underlying implementation of the abstract model.

**1.0**: Initial release.
