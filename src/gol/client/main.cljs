(ns gol.client.main
  (:require [reagent.core :as r]
            [gol.client.act :refer [process-changes process-actions process-periods]]
            [gol.client.chan :refer [create-channels]]
            [gol.client.state :refer [create-state]]
            [gol.client.ui.app :refer [app-component]]
            [gol.client.ui.control :refer [control-component]]))

(def state (create-state {:width  30
                          :height 30
                          :count  450
                          :period 500
                          :status :progress
                          :type   :unlimited}))
(def channels (create-channels))

(r/render-component [app-component state channels] (js/document.getElementById "app"))
(r/render-component [control-component state channels] (js/document.getElementById "control"))

(process-changes state channels)
(process-actions state channels)
(process-periods state channels)