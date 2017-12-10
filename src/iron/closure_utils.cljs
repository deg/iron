;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2017, David Goldfarb

(ns iron.closure-utils
  (:require
   [clojure.spec.alpha :as s]))


(def debug?
  ^boolean goog.DEBUG)
