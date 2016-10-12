(ns lotto.handlers
    (:require [re-frame.core :as re-frame]
              [lotto.db :as db]
              [lotto.cards :as cards]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
