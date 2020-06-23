# `dse`: release notes

**7.1.3**: Fixes misleading error message.

**7.1.2**: Fixes a bug in loading DSE triples from text file.

**7.1.1**: Fixes a bug in string formatting of `DsePassage`.

**7.1.0**: Adds support for SJS 1.0.0.

**7.0.0**: API-breaking correction to display in ICT2 when no indexed passages on a surface.

**6.1.0** Adds new `markdown` methods in `DsePassage` class.

**6.0.4** Will not throw an exception when asked to create a DSEVector from a library that has no DSE object, or one that has no DSE model declared, or one with no CiteCollectionRepository at all. Gives an empty Vector back instead, in each case.

**6.0.3** Improved time for validting DSEVectors.

**6.0.2** Really properly publish version 6.0.0  with logging set to `info` level.

**6.0.1** Republish version 6.0.0 with logging set to `info` level.

**6.0.0** API-breaking update.  Functions retrieving a URN for an image or text-bearing surface in DSE relations now return `Option[Cite2Urn]` rather than returning `Cite2Urn` and throwing Exceptions.  `DseVector` object adds a suite of functions for evaluating a Vector of `DsePassage`  objects that can be used to evaluate data prior to attempting to construct a `DseVector`.  Behavior of `DseVector` is unchanged in that it *will* throw an Exception if bad input data are provided to its constructor.

**5.3.0** Add logging support.

**5.2.2**: Improved DSE validation time by four orders of magnitude.

**5.2.1**: fix typo in `fromCiteLibrary` function.

**5.2.0**:  add function to `DseVector` object to extract a `DseVector` from a `CiteLibrary`.

**5.1.2**: updates to multiple library dependencies.

**5.1.1**: republish as 5.1.1 previous 5.1.0 that failed on binary publicatoin to bintray.


**5.1.0**:  Add `cex` function to `DsePassage`.

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
