(ns gol.core.chan
  (:require [cljs.core.async :refer [chan]]))

(defn create-channels
  []
  {:actions (chan)
   :periods (chan)
   :changes (chan)})
