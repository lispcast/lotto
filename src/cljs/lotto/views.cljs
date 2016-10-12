(ns lotto.views
  (:require [lotto.utils :refer [state-viewer]]
            [re-frame.core :as re-frame]
            [lotto.cards :as cards]))

(defn main-panel []
  [:div
   [:h1 "Welcome to the world's first New Orleans Clojure Workshop!"]
   [:img {:src "https://talkingtrumpet.files.wordpress.com/2015/11/louisarmstrong.jpg"}]])
