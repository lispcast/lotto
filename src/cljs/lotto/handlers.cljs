(ns lotto.handlers
    (:require [re-frame.core :as re-frame]
              [lotto.db :as db]
              [lotto.cards :as cards]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :shuffle-cards
 (fn [db [_ width height tiles]]
   (let [num-cards (* width height)
         half (/ num-cards 2)
         half-cards (take half (shuffle tiles))
         cards (concat half-cards half-cards)
         cards (map (fn [face] (cards/card face :back)) cards)
         shuffled (shuffle cards)
         rows (partition width shuffled)
         grid (mapv vec rows)]
     (assoc db :cards grid
            :current-player :A
            :scores {:A 0
                     :B 0}))))

(re-frame/reg-event-db
 :flip-up
 (fn [db [_ x y]]
   (let [card (get-in db [:cards y x])
         cards-up (count (filter cards/front? (apply concat (get db :cards))))]
     (if (or (cards/front? card)
             (not (contains? #{0 1} cards-up)))
       db
       (do
         (when (= 1 cards-up)
           (js/setTimeout (fn []
                            (re-frame/dispatch [:end-turn]))
                          2000))
         (update-in db [:cards y x] cards/flip-up))))))

(defn swap-players [player]
  (if (= :A player)
    :B
    :A))

(re-frame/reg-event-db
 :end-turn
 (fn [db _]
   (let [cards (get db :cards)
         up-cards (filter cards/front? (apply concat cards))
         match? (and (= 2 (count up-cards))
                     (apply = up-cards))
         current-player (get db :current-player)]
     (if match?
       (let [db (update-in db [:scores current-player] inc)
             db (update db :cards cards/remove-up-cards)]
         db)
       (let [db (update db :cards cards/flip-down-all)
             db (update db :current-player swap-players)]
         db)))))
