(ns lotto.views
  (:require [lotto.utils :refer [state-viewer]]
            [re-frame.core :as re-frame]
            [lotto.cards :as cards]))

(defn main-panel []
  [:div
   "Welcome to the world's first New Orleans Clojure Workshop!"])
