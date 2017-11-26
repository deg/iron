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


## [0.1.0] - 2017-11-26
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

[Unreleased]: https://github.com/deg/iron/compare/HEAD...HEAD
[0.1.0]: https://github.com/deg/iron/compare/6e80201...HEAD
