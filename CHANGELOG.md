# Change Log

## [Unreleased]
### Changed
_(nothing)_
### Added
_(nothing)_
### Removed
_(nothing)_
### Fixed
_(nothing)_


## [0.4.0] - 2018-5-23
### Changed
- Updated dependencies: expound to 0.6.0; reagent to 0.8.1
- utils/negligible? now treats `false` as not negligible.

## [0.3.0] - 2018-4-15
### Changed
- Updated dependencies: CLJS to 1.10.238; re-frame to 0.10.5
### Added
- check-and-throw - for spec-driven app-db validation



## [0.2.0] - 2018-1-16
No major changes. Releasing just because it's been a snapshot for too long.
### Changed
- Updated Clojure to released 1.9.0
### Added
- iron.closure-utils/debug? - Are we in dev or production release?


## [0.1.1] - 2017-11-26
This first version of Iron was extracted from [Sodium](https://github.com/deg/sodium),
including only the parts that are independent of
[soda-ash](https://github.com/gadfly361/soda-ash) or
[react-semantic-ui](https://github.com/Semantic-Org/Semantic-UI-React).

During this extraction, I also took the liberty of cleaning up bits that I no longer
like. This change-note lists changes relative to Sodium v0.8.0.

### Changed
- Many functions from sodium have moved into similarly-named namespaces in Iron. See the
  [readme](README.md) for details
### Removed
- Most of `>event` has moved into `>evt`. Some parts were specific to react-semantic-ui,
  and are in sodium.core/value->event-fn
- `>events` is gone. It will probably come back someday, but not until someone needs and tests
- `>atom` and `<atom` are gone. The few parts that were useful are in sodium.core/value->event-fn

[Unreleased]: https://github.com/deg/iron/compare/f5058c3...HEAD
[0.4.0]:      https://github.com/deg/iron/compare/2a39914...f5058c3
[0.3.0]:      https://github.com/deg/iron/compare/0352644...2a39914
[0.2.0]:      https://github.com/deg/iron/compare/cdcb5e0...0352644
[0.1.1]:      https://github.com/deg/iron/compare/6e80201...cdcb5e0
