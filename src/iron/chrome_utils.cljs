;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2017, David Goldfarb

(ns iron.chrome-utils)


;;; [TODO] Merge this into copy of https://github.com/Day8/re-frame/blob/master/src/re_frame/loggers.cljc

(defn console-dir
  "Inspect an object in the Chrome console, preceded by a text message.
   This is useful in general, but especially nice for JavaScript objects
   that print opaquely in the REPL."
  [msg obj]
  (when msg
    (js/console.log msg))
  (when obj
    (js/console.dir obj)))
