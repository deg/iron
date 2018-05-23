(ns iron.utils-test
  (:require [cljs.test :refer-macros [deftest testing is are]]
            [iron.utils :as utils]))

(deftest negligible?
  (is (= false (utils/negligible? [:a :b :c])))
  (is (= false (utils/negligible? "abc")))
  (is (= false (utils/negligible? '(a b c))))
  (is (= false (utils/negligible? 42)))
  (is (= false (utils/negligible? 17.123)))
  (is (= false (utils/negligible? true)))
  (is (= false (utils/negligible? false)))

  (is (= true (utils/negligible? ())))
  (is (= true (utils/negligible? [])))
  (is (= true (utils/negligible? {})))
  (is (= true (utils/negligible? ""))))

(deftest ci-compare
  (is (= 0 (utils/ci-compare "" "")))
  (is (= 0 (utils/ci-compare "abc" "abc")))
  (is (= 0 (utils/ci-compare "abc" "ABC")))
  (is (= 0 (utils/ci-compare "ABC" "abc")))
  (is (= 0 (utils/ci-compare "AbC" "aBc")))
  (is (= 1 (utils/ci-compare "abc" "ab")))
  (is (= 1 (utils/ci-compare "abc" "AB")))
  (is (= -1 (utils/ci-compare "ab" "abc")))
  (is (= -1 (utils/ci-compare "AB" "abc"))))

(deftest ci-sort
  (is (= (utils/ci-sort ["xyz" "AbC" "123" "XYZZ"])
         ["123" "AbC" "xyz" "XYZZ"])))

(deftest ci-sort-by
  (is (= (utils/ci-sort-by first [["DEF"] ["ghi"] ["abc"]])
         [["abc"] ["DEF"] ["ghi"]])))

(deftest ci-includes?
  (is (= true (utils/ci-includes? "abcdefg" "BCD"))))

(deftest camelize-str
  (are [pre-string post-string] (= (utils/camelize-str pre-string) post-string)
    "string"       "string"
    "the_string"   "the_string"
    "the-string"   "theString"
    "the-string-"  "theString"
    "-the-string"  "TheString"
    "even?"        "even"
    "long-string?" "longString"))

(deftest camelize-key
  (are [pre-key post-key] (= (utils/camelize-key pre-key) post-key)
    :a       :a
    :a-b     :aB
    :a-b?    :aB
    :foo/a   :foo/a
    :foo/a-b :foo/aB
    ::x?     ::x))

(deftest camelize-map-keys
  (is (= (utils/camelize-map-keys
          {:word :word
           :two-words :two-words
           :predicate? :predicate?
           :even-number? :even-number?
           :data-tooltip :data-tooltip}
          :exclude [:data-tooltip])
         {:word :word                  ;; Simple
          :twoWords :two-words         ;; Camel
          :predicate :predicate?       ;; Predicate
          :evenNumber :even-number?    ;; Predicate/Camel
          :data-tooltip :data-tooltip  ;; Preserved
          })))
