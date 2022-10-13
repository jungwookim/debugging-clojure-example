(ns my-inspector)

(defn inspector
  [x]
  (prn x)
  x)

(defn calls
  []
  (inspector "called"))

(defn calls>
  []
  (-> {:value 1}
      (update :value inc)
      inspector
      (assoc :after true)))

(defn calls>>
  []
  (->> (range 10)
       (map inc)
       inspector
       (partition 2)))

(comment
  (calls)
  (calls>)
  (calls>>))
