(defproject darksky-clojure "1.0.0"
  :description "Clojure library for retrieving data from the Dark Sky API"
  :url "https://github.com/jdhollis/darksky-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [environ "0.3.0"]
                 [clj-http "0.5.8"]
                 [cheshire "5.0.0"]])
