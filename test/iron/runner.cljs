(ns iron.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [iron.utils-test]))

(doo-tests 'iron.utils-test)
