(ns lotto.cards)

(def mahjong
  {:east-wind           "🀀"
   :south-wind          "🀁"
   :west-wind           "🀂"
   :north-wind          "🀃"
   :red-dragon          "🀄"
   :green-dragon        "🀅"
   :white-dragon        "🀆"
   :one-of-characters   "🀇"
   :two-of-characters   "🀈"
   :three-of-characters "🀉"
   :four-of-characters  "🀊"
   :five-of-characters  "🀋"
   :six-of-characters   "🀌"
   :seven-of-characters "🀍"
   :eight-of-characters "🀎"
   :nine-of-characters  "🀏"
   :one-of-bamboos      "🀐"
   :two-of-bamboos      "🀑"
   :three-of-bamboos    "🀒"
   :four-of-bamboos     "🀓"
   :five-of-bamboos     "🀔"
   :six-of-bamboos      "🀕"
   :seven-of-bamboos    "🀖"
   :eight-of-bamboos    "🀗"
   :nine-of-bamboos     "🀘"
   :one-of-circles      "🀙"
   :two-of-circles      "🀚"
   :three-of-circles    "🀛"
   :four-of-circles     "🀜"
   :five-of-circles     "🀝"
   :six-of-circles      "🀞"
   :seven-of-circles    "🀟"
   :eight-of-circles    "🀠"
   :nine-of-circles     "🀡"
   :plum                "🀢"
   :orchid              "🀣"
   :bamboo              "🀤"
   :chrysanthemum       "🀥"
   :spring              "🀦"
   :summer              "🀧"
   :autumn              "🀨"
   :winter              "🀩"
   :joker               "🀪"})

(defn card [face orientation]
  [face orientation])

(defn orientation [card]
  (get card 1))

(defn face [card]
  (get card 0))

(defn front? [card]
  (= :front (orientation card)))

(defn back? [card]
  (= :back (orientation card)))

(defn flip-up [crd]
  (card (face crd) :front))

(defn flip-down [crd]
  (card (face crd) :back))

(defn flip-down-all [grid]
  (mapv (fn [row] (mapv flip-down row)) grid))

(defn remove-up-cards [grid]
  (mapv (fn [row]
          (mapv (fn [card]
                  (if (front? card)
                    nil
                    card)) row))
        grid))
