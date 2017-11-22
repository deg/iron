(defproject
  com.degel/iron "0.1.0-SNAPSHOT"
  :description "Front-end utilities and support for my ClojureScript and re-frame projects"
  :url "https://github.com/deg/sodium"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                 [org.clojure/clojurescript "1.9.946"]
                 [re-frame "0.10.2"]
                 [reagent "0.7.0"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.8"]]
  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src"]
     :compiler {:pretty-print true}}
    {:id "test"
     :source-paths  ["src" "test"]
     :compiler {:main          iron.runner
                :output-to     "resources/public/js/compiled/test.js"
                :output-dir    "resources/public/js/compiled/test/out"
                :optimizations :none}}]})
