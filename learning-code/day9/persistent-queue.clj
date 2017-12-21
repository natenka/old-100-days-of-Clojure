(defmethod print-method clojure.lang.PersistentQueue
  [q, w]
  
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))

(def schedule
  (conj clojure.lang.PersistentQueue/EMPTY
    :wake-up :shower :brush-teeth))

