(ns my-tap)

(def debug-atom (atom []))
(add-tap (fn [value] (swap! debug-atom #(conj % value))))

(comment
  (tap> 1)
  @debug-atom

  (tap> {:age  31
         :name "jungwoo"})
  @debug-atom)
