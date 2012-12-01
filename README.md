# darksky-clojure

A Clojure library for retrieving data from the [Dark Sky API](http://darkskyapp.com/api/). The Dark Sky API lets you query for short-term precipitation forecast data at geographical points inside the United States.

## Installation

`darksky-clojure` is available as a Maven artifact from [Clojars](http://clojars.org/darksky-clojure):

```clojure
[darksky-clojure 1.0.1]
```

## Configuration

The library fetches your Dark Sky API key from the environment using [Environ](https://github.com/weavejester/environ). You can set your key in one of two ways.

In `~/.lein/profiles.clj`:

```clojure
{:user {:env {:dark-sky-key "insert-your-api-key-here"}}}
```

Or, set it as an environment variable:

```bash
DARK_SKY_KEY=insert-your-api-key-here
```

## Usage

```clojure
(require '[darksky-clojure.core :as darksky])

;; Returns a forecast for the next hour at a given location
(darksky/forecast "42.7243", "-73.6927")
=> { :isPrecipitating true
     :minutesUntilChange 25
     :currentSummary "rain"
     :hourSummary "rain will stop in 25 min"
     :daySummary "likely rain until tonight"
     :currentTemp 65
     :timezone "America/New_York (EDT -0400)"
     :checkTimeout 750
     :radarStation "enx"
     :hourPrecipitation [
       { :probability 1.0
         :intensity 15.6
         :error 1.0
         :type "rain"
         :time 1325607311 }
       { :probability 0.84
         :intensity 12.0
         :error 2.34
         :type "rain"
         :time 1325607431 }
       { :probability 0.8
         :intensity 20.5
         :error 5.1
         :type "rain"
         :time 1325607551 }
         ... ]
     :dayPrecipitation [
       { :probability 1.0
         :type "rain"
         :temp 65
         :time 1325607311 }    
       { :probability 0.84
         :type "rain"
         :temp 65
         :time 1325610911 }
       { :probability 0.8
         :type "rain"
         :temp 65
         :time 1325614511 }
         ... ]}

;; Returns a brief forecast for the next hour at a given 
(darksky/brief-forecast "42.7243", "-73.6927")
=> { :currentTemp 34
     :currentSummary "clear"
     :hourSummary "clear"
     :daySummary "moderate chance of snow until this evening"
     :isPrecipitating false
     :currentIntensity 0
     :minutesUntilChange 0
     :checkTimeout 1320 }

;; Returns forecasts for a collection of arbitrary points
(darksky/precipitation ["42.7", "-73.6", 1325607100] ["42.0", "-73.0", 1325607791])
=> [{ :probability 1.0
      :intensity 15.6
      :error 1.0
      :type "rain"
      :time 1325607100 }
    { :probability 0.0
      :intensity 0.0
      :error 0.0
      :type "rain"
      :time 1325607791 }]

;; Returns a list of interesting storms happening right now
(darksky/interesting)
=> [{ :radarStation "jkl"
      :intensity 56.4706
      :city "Jackson KY"
      :latitude 37.64339067929419
      :longitude -83.46643966254257 }
    { :radarStation "iln"
      :intensity 56.1765
      :city "Jeffersonville OH"
      :latitude 39.65499292401906
      :longitude -83.55941231055581 }
    ... ]
```

## License

Copyright © 2012 J.D. Hollis

Distributed under the Eclipse Public License, the same as Clojure.