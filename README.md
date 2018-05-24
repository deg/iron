# Iron

## Introduction

Iron is support for code for my ClojureScript projects; initially for
[Sodium](https://github.com/deg/sodium)

### Why "Iron"?

Sodium came first, and got its name from React, Soda-ash, and Semantic-UI. See its
[readme](https://github.com/deg/sodium/blob/master/README.md) for the punny details.

Iron is intended to hold the shared parts used by Sodium and other related libraries,
which will have similar elemental names. Ium seemed like a good possibility. But since
the focus is on front-end code, `fe-ium`.  And, of course, FE is iron. So, there you go.

### State of this project

Iron is still a early work in progress. I am using it to help me with other projects
and have only added functionality that I need and can test. Iron will only grow as
it helps me (or other contributers) accomplish what they need. If you need features now,
PRs are welcome.

## Setup

[![Clojars Project](https://img.shields.io/clojars/v/com.degel/iron.svg)](https://clojars.org/com.degel/iron)
[![Dependencies Status](https://versions.deps.co/deg/iron/status.svg)](https://versions.deps.co/deg/iron)

If you want to contribute to this project, you will want to test your changes. You can
extend the (very minimal) tests inside Sodium, but you will probably need to really test
by using Sodium in a ClojureScript project. The easiest way to do this, while you are
changing Iron, is by using Leiningen's
[checkouts directory](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md#checkout-dependencies)
feature by sym-linking directly to your copy of Sodium.

## Using Iron

Iron is a library, with a grab-bag of functions for re-frame projects. You can use it in
several ways:

### Helper functions

These are some some of the functions that I've needed often in re-frame projects. I
expect this will grow rapidly with time. PRs are welcome here, though I'm likely to be
opinionated about what I accept.

#### In `iron.utils`
- `negligible?` - Variant of empty? that behaves reasonably for non-seqs too
- `ci-compare`, `ci-sort`, `ci-sort-by`, and `ci-includes?` - Case-insensitive string functions
- `validate` - Wrapper for Clojure specs checking in pre-conditions.
- `vconcat` - Like concat, but returns a vector
- "Camelize" functions - Convert Clojure-style names to JavaScript style

#### In `iron.re-utils`
- `sub2` - Shorthand for a simple re-frame 'level-2' subscription (one that simply accesses the db)
- `>evt` - Wrapper around re-frame/dispatch, with added sugar for defaults and coersions
- `<sub` - Shorthand for re-frame subscribe and immediate deref
- `event->fn` - Allow re-frame events to be used in contexts that expect a function
- `sub->fn -`Allow re-frame subscriptions to be used in contexts that expect a function
- `check-and-throw` - Helper for spec-driven app-db validation.

#### In `iron.chars`

Definitions for a few common Unicode characters.

### Internals

### Testing

`lein doo phantom test auto` will run the (embarrasingly few) unit tests I've written so
far. More are needed. PRs especially welcome here.

## Useful references

- https://semantic-ui.com/
- https://react.semantic-ui.com/introduction
- https://github.com/Semantic-Org/Semantic-UI-React
- https://github.com/gadfly361/soda-ash


## Questions

I can usually be found on the [Clojurians Slack](https://clojurians.net) #reagent or
#re-frame slack channels. My handle is @deg. Email is also fine.

## License

Copyright © 2017-2018, David Goldfarb <deg@degel.com>

Licensed under the Eclipse Public License.
