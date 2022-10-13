(ns my-trace-portal
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
  (trace/setup-default)
;; Reload this NS to test the remainder:
  (do (ns dev)
      (def portal ((requiring-resolve 'portal.api/open)
                   {:launcher                     :vs-code
                    :portal.launcher/window-title (System/getProperty "user.dir")}))
      (add-tap (requiring-resolve 'portal.api/submit)))
  (calls-trace)
  (calls-trace>)
  (calls-trace>>))