(ns lotto.views
  (:require [lotto.utils :refer [state-viewer]]
            [re-frame.core :as re-frame]
            [lotto.cards :as cards]))

(defn card-face [tile]
  [:div.front
   [:div.card {:style {:font-size "240px"}}
    (get cards/mahjong tile)]])

(defn card-back []
  [:div.back
   [:div.card]])

(defn oriented-card [card]
  [:div {:class (if (cards/front? card)
                  "flip-container"
                  "flip-container back-of-card")}
   (when card
     [:div.flipper
      [card-back]
      [card-face (cards/face card)]])])

(defn grid [f]
  (let [grid-size (re-frame/subscribe [:grid-size])
        height (:height @grid-size)
        width (:width @grid-size)]
    [:table
     [:tbody
      (doall
       (for [y (range height)]
         [:tr {:key (str y)}
          (doall
           (for [x (range width)]
             [:td {:key (str x)}
              [f x y]]))]))]]))

(defn flipper [x y]
  (let [card (re-frame/subscribe [:card-at x y])]
    [:div {:on-click (fn [] (re-frame/dispatch [:flip-up x y]))}
     [oriented-card @card]]))

(defn main-panel []
  (let [current-player (re-frame/subscribe [:current-player])
        scores         (re-frame/subscribe [:scores])]
    [:div
     [:div.info
      [:div
       [:h2 "Current player: "]
       (name @current-player)]
      [:div
       [:h2  "Scores: "]
       (for [score-record @scores]
         (let [player (first score-record)
               score (second score-record)]
           [:div {:key (name player)}
            (name player) " " (str score)]))]]
     [grid flipper]
     [:div [:button {:on-click (fn []
                                 (re-frame/dispatch
                                  [:shuffle-cards 2 2 (keys cards/mahjong)]))}
            "Shuffle."]]
     [state-viewer]]))
