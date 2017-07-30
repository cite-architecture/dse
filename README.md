# `dse`

A cross-platform library for working with digital diplomatic scholarly editions.

## Current version: 2.0.0

See [release notes](releases.md).

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)

## Using, building, testing

`dse` is compiled for both the JVM and ScalaJS using scala version  2.11.

To build from source and test for a given version, use normal `sbt` commands (`compile`, `test` ...).

## CEX serialization of the DSE data model

Diplomatic scholarly editions relate three entities:

1. a diplomatic transcription of texts
2. a sequence of text-bearing surfaces
3. visual documentary evidence

Passages of text are identified with CTS URNs;  text-bearing surfaces and visual evidence are identified with CITE2 URNs.  To serialize DSE data in CEX, include the CITE Collection `urn:cite2:dse:verbs.v1:` (in CEX format [here](dse.cex)) in your CEX source, and create statements in the CEX `relations` block linking each entity (text, surface, image) with the other two related entities.\\
