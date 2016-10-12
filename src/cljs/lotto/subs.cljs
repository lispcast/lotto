(ns lotto.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :app-state
 (fn [db]
   db))

(re-frame/reg-sub
 :grid-size
 (fn [db]
   {:height (count (get db :cards))
    :width (count (first (get db :cards)))}))

(re-frame/reg-sub
 :card-at
 (fn [db [_ x y]]
   (get-in db [:cards y x])))

(re-frame/reg-sub
 :current-player
 (fn [db]
   (get db :current-player)))

(re-frame/reg-sub
 :scores
 (fn [db]
   (get db :scores)))
