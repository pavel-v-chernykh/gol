(ns gol.ui.client
  (:require [reagent.core :as r :refer [atom]]
            [gol.core.act :refer [listen-channels run-periods]]
            [gol.core.comp :refer [create-state create-channels]]
            [gol.ui.components.viewport.main :refer [viewport-component]]
            [gol.ui.components.control.main :refer [control-component]]))

(defonce state (atom @(create-state {:width  20
                                     :height 20
                                     :count  200
                                     :period 400
                                     :status :progress
                                     :type   :unlimited})))

(defn ^:export main
  []
  (let [channels (create-channels)]

    (r/render [viewport-component state channels] (js/document.getElementById "viewport"))
    (r/render [control-component state channels] (js/document.getElementById "control"))

    (listen-channels state channels)
    (run-periods state channels)))

(defn ^:export on-js-reload
  []
  (swap! state update-in [:__figwheel_counter] inc))
