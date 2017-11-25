;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2017, David Goldfarb

(ns iron.utils
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as str]))


(defn negligible?
  "Variant of empty? that behaves reasonably for non-seqs too."
  [x]
  (if (seqable? x) (empty? x) (not x)))


(defn ci-compare
  "Case-insensitive string compare"
  [s1 s2]
  (compare (str/upper-case s1) (str/upper-case s2)))

(defn ci-sort
  "Case-insensitive string sort"
  [coll]
  (sort-by str/upper-case coll))

(defn ci-sort-by
    "Case-insensitive string sort-by"
  [keyfn coll]
  (sort-by (comp str/upper-case keyfn)
           coll))

(defn ci-includes?
  "Case-insensitive string inclusion test"
  [s substr]
  (str/includes? (str/upper-case s) (str/upper-case substr)))


(defn validate
  "Like s/valid?, but show the error like s/assert. Useful for pre-conditions."
  [spec x]
  (or (s/valid? spec x)
      (s/explain spec x)))

(defn vconcat
  "Like concat, but return a vector."
  [& vecs]
  (vec (apply concat vecs)))

(s/def ::event-vector (s/cat :event keyword? :params (s/* any?)))

(defn unpredicate
  "Remove trailing '?' from predicate, to make suitable for JavaScript"
  [s]
  (if (str/ends-with? s "?")
    (subs s 0 (-> s count dec))
    s))

(defn camelize-str
  "Convert a string from ClojureScript to JavaScript conventions.
  - Replace hyphens with camelCase
  - Remove trailing '?'"
  [s]
  (let [[first-word & more] (str/split (unpredicate s) "-")]
    (if more
      (str first-word (str/join (map str/capitalize more)))
      first-word)))

(defn camelize-key
  "Convert a keyword from ClojureScript to JavaScript conventions.
  - Replace hyphens with camelCase
  - Remove trailing '?'
  - Preserve namespace"
  [k]
  (keyword (namespace k)
           (camelize-str (name k))))

(defn camelize-map-keys
  "Convert a map from ClojureScript to JavaScript conventions. Change the map
  keys, but leave the values alone.  For convenience, you can pass in a seq
  of keywords that must be excluded (left unchanged)."
  [m & {:keys [exclude]}]
  (reduce-kv (fn [m k v]
               (assoc m
                      (if (some #{k} exclude) k (camelize-key k))
                      v))
             {} m))

;;; [TODO] Merge this with re-frame/console copy and chrome_utils.cljs
(defn err
  "Simple helper to show an error message in Clojure or ClojureScript"
  [& strings]
  (apply #?(:clj (partial println "Error: ") :cljs js/console.error) strings))

