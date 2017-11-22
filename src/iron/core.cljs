;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2017, David Goldfarb

(ns iron.core
  (:require
   [clojure.spec.alpha :as s]
   [re-frame.loggers :refer [console]]
   [iron.re-utils :refer [<sub >evt]]
   [iron.utils :as utils]))

(s/def :iron/size #{:tiny :small :medium :large :huge})

(defn- negligible?
  [x]
  (if (seqable? x) (empty? x) (not x)))

(defn >event
  "Return a function suitable for an on-* handler, to deliver the value
  into into a re-frame event. See also >atom.
  The first argument is a re-frame event vector, into which the value
  will be conjed as the final element.
  It is followed by two optional arguments: a default value that will
  be used when the value is empty, and a 'coercer' function to convert
  the value into a suitable form for the event.
  Note that the default value is _not_ passed through the coercer."
  ;; [TODO] The line about the default value is obviously wrong. Check users and fix!
  ([event]
   (>event event ""))
  ([event default]
   (>event event default identity))
  ([event default coercer]
   #(>evt (let [value (or (.-value %2) (.-checked %2))]
            (conj event
                  (coercer (if (negligible? value)
                             default
                             value)))))))

(defn >events
  "Utility function to dispatch multiple events from an on-* hander.
  The syntax is a bit opaque, because we have to wrap both the event
  parameters and the parameters to >event (default and coercer).

  So, a usage looks like:

    (na/>events [[[:update-age :in-minutes] 42]
                 [[:set-color] :cyan nearest-color]])
  "
  [events]
  (fn [dom-event param-map]
    (run! (fn [event]
            ((apply >event event) dom-event param-map))
          events)))

(defn >atom
  "Return a function suitable for an on-* handler, to deliver the value
  into into an atom. This would typically be used to store a result into
  a local reagent atom. See also >event.
  The first argument is an atom, which the value will be reset! into.
  It is followed by two optional arguments: a default value that will
  be used when the value is empty, and a 'coercer' function to convert
  the value into a suitable form for the event.
  Note that the default value is _not_ passed through the coercer."
  ;; [TODO] Default wrong here too
  ([atom]
   (>atom atom ""))
  ([atom default]
   (>atom atom default identity))
  ([atom default coercer]
   #(->> (or (.-value %2) (.-checked %2))
         js->clj
         coercer
         (reset! atom))))


(defn <atom
  "Get a value from an atom. Suitable to use, e.g., as the :value or
  :default-value parameter to a component.
  Atom is the atom to be dereferenced. It will be translated by access-fn.
  If null, default will be supplied instead."
  ([atom]
   (<atom atom nil))
  ([atom default]
   (<atom atom default identity))
  ([atom default access-fn]
   (or (access-fn @atom) default)))
