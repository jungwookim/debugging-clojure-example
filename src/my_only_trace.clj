(ns my-only-trace
  (:require [net.lewisship.trace :as trace
             :refer [trace trace> trace>>]]))

(defn calls-trace
  []
  (trace :msg "called"))

(defn calls-trace>
  []
  (-> {:value 1}
      (update :value inc)
      (trace> :label :post-inc)
      (assoc :after true)))

(defn calls-trace>>
  []
  (->> (range 10)
       (map inc)
       (trace>> :label :post-inc)
       (partition 2)))


(comment
  (calls-trace)
;; no output
  (trace/setup-default)
;; Reload this NS to test the remainder:
  (calls-trace)
;;   {:in     my-only-trace/calls-trace
;;    :line   7
;;    :thread "nREPL-session-90a76836-b2ce-4d07-86db-23db11474218"
;;    :msg    "called"}
  (calls-trace>)
;;   {:in      my-only-trace/calls-trace>
;;    :line    13
;;    :thread  "nREPL-session-90a76836-b2ce-4d07-86db-23db11474218"
;;    :%value% {:value 2}
;;    :label   :post-inc}
  (calls-trace>>)
;;   {:in      my-only-trace/calls-trace>>
;;    :line    20
;;    :thread  "nREPL-session-90a76836-b2ce-4d07-86db-23db11474218"
;;    :%value% (1 2 3 4 5 6 7 8 9 10)
;;    :label   :post-inc}
  )