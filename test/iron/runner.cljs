(ns iron.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [iron.utils-test]
              [iron.core-test]))

(doo-tests 'iron.utils-test
           'iron.core-test)
