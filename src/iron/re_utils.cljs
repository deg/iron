;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2017, David Goldfarb

(ns iron.re-utils
  (:require
   [clojure.spec.alpha :as s]
   [expound.alpha :as expound]
   [clojure.string :as str]
   [re-frame.core :as re-frame]
   [iron.closure-utils :refer [debug?]]
   [iron.utils :as utils]))


(defn sub2
  "Shorthand for simple 'layer 2` usage of re-sub"
  [key db-path]
  (re-frame/reg-sub
   key
   (fn [db _] (get-in db db-path))))


;; Ideas based on https://lambdaisland.com/blog/11-02-2017-re-frame-form-1-subscriptions

(defn >evt
  "Shorthand for re-frame dispatch to event.
  The two-argument form appends a value into the event.
  The three-argument form offers more control over this value, letting
  you specify a default value for it and/or a coercer (casting) function"
  ([event]
   (re-frame/dispatch event))
  ([event value]
   (re-frame/dispatch (conj event value)))
  ([event value {:keys [default coercer] :or {coercer identity}}]
   (>evt event
         (coercer (if (utils/negligible? value)
                    default
                    value)))))

(defn <sub
  "Shorthand for re-frame subscribe and deref."
  ([subscription]
   (-> subscription re-frame/subscribe deref))
  ([subscription default]
   (or (<sub subscription) default)))



(s/def :re-frame/vec-or-fn (s/or :event-or-sub vector? :function fn?))

(defn- vec->fn [vec-or-fn key-fn]
  {:pre [(utils/validate (s/nilable :re-frame/vec-or-fn) vec-or-fn)]
   :post (fn? %)}
  (if (vector? vec-or-fn)
    #(key-fn (conj vec-or-fn %))
    vec-or-fn))


(defn event->fn
  "For contexts that want to pass an argument to a sink function: accept
  either a function or a re-frame event vector.
  If a vector is received, convert it to a function that dispatches to that
  event, with the parameter conj'd on to the end."
  [event-or-fn]
  (vec->fn event-or-fn >evt))


(defn sub->fn
  "Accept either a re-frame sub or a function, for contexts that demand
  a function."
  [sub-or-fn]
  (vec->fn sub-or-fn <sub))


;;; (See https://github.com/Day8/re-frame/blob/master/examples/todomvc/src/todomvc/events.cljs)
(defn check-and-throw
  "Throws an exception if `db` doesn't match the Spec `a-spec`."
  [a-spec db]
  (when (and debug?
             (not (s/valid? a-spec db)))
    (throw (ex-info (binding [s/*explain-out* expound/printer]
                      (s/explain a-spec db))
                    {}))))
