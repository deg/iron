# Sodium

## Introduction

Iron is support for code for my ClojureScript projects; initially for
[Sodium](https://github.com/deg/sodium)

### Why "Iron"?

Sodium came first, and got its name from react, Soda-ash, and Semantic-UI. See its
[readme](https://github.com/deg/sodium/blob/master/README.md) for the punny details.

Iron is intended to hold the shared parts used by Sodium and other related libraries,
which will have similar elemental names. Ium seemed like a good possibility. But since
the focus is on front-end code, `fe-ium`.  And, of course, FE is iron. So, there you go.

### State of this project

Iron is still a early work in progress. I am using it to help me with other projects
and have only addded functionality that I need and can test.  Iron will only grow as
it helps me (or other contributers) accomplish what they need. If you need features now,
PRs are welcome.

### Background

TBD

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

### Data-in and -out

Iron offers the following:

- `fe/>event`, `fe/>events` and `fe/>atom` - Create `:on-*` handlers to
  pass a value to a re-frame event or react atom.
- `fe/<atom` - Get a value from a react atom.

#### Typical usage

````
(defn login-panel []
  (let [email (reagent/atom "")
        password (reagent/atom "")]
    (fn []
      [na/form {}
       [na/form-input {:label "Email"
                       :type "email"
                       :on-change (fe/>atom email)}]
       [na/form-input {:label "Password"
                       :type "password"
                       :on-change (fe/>atom password)}]
       [na/form-button {:content "Login"
                        :on-click (fe/>event [:login @email @password])}]])))
````

These functions work equally with with Sodium components and any others.

### Helper functions

These are some some of the functions that I've needed often in re-frame projects. I
expect this will grow rapidly with time. PRs are welcome here, though I'm likely to be
opinionated about what I accept.

#### In `iron.utils`
- `ci-compare`, `ci-sort`, and `ci-includes?` - Case-insensitive string functions
- `validate` - Wrapper for Clojure specs checking in pre-conditions.
- "Camelize" functions - Convert Clojure-style names to JavaScript style

#### In `iron.re-utils`
- `<sub` and `>evt` - Re-frame wrappers, taken from <https://lambdaisland.com/blog/11-02-2017-re-frame-form-1-subscriptions>
- `sub2` - Shorthand for a simple re-frame 'level-2' subscription (one that simply accesses the db)

#### In `iron.chars`

Definitions for a few common Unicode characters.

### Internals

### Testing

`lein doo phantom test auto` will run the few unit tests I've written so far. More are
needed. PRs especially welcome here.

## Useful references

- https://semantic-ui.com/
- https://react.semantic-ui.com/introduction
- https://github.com/Semantic-Org/Semantic-UI-React
- https://github.com/gadfly361/soda-ashsoda


## Questions

I can usually be found on the [Clojurians Slack](https://clojurians.net) #reagent or
#re-frame slack channels. My handle is @deg. Email is also fine.

## License

Copyright © 2017, David Goldfarb <deg@degel.com>

Licensed under the Eclipse Public License.
