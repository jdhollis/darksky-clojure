(ns darksky-clojure.core
  (:use [environ.core])
  (:require [cheshire.core :as json]
            [clj-http.client :as client]))

(defn- data-for [data-path & args]
  (let [api-url "https://api.darkskyapp.com/v1"
        api-key (env :dark-sky-key)
        response (client/get (apply str (interpose \/ [api-url data-path api-key (first args)]))
                             {:throw-exceptions false})]
    (cond (= 200 (:status response))
          (json/parse-string (:body response) true))))

(defn forecast
  "Returns a forecast for the next hour at a given location"
  [lat lon]
  (data-for "forecast" (str lat "," lon)))

(defn brief-forecast
  "Returns a brief forecast for the next hour at a given location"
  [lat lon]
  (data-for "brief_forecast" (str lat "," lon)))

(defn precipitation
  "Returns forecasts for a collection of arbitrary points"
  [& triplets]
  (letfn [(commafy [triplets & strings]
            (let [triplet (first triplets)]
              (if (nil? triplet)
                strings
                (recur (rest triplets)
                       (conj (or strings []) (apply str (interpose \, triplet)))))))]
    (:precipitation (data-for "precipitation" (apply str (interpose \; (commafy triplets)))))))

(defn interesting
  "Returns a list of interesting storms happening right now"
  []
  (:storms (data-for "interesting")))
