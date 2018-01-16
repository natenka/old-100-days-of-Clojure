;#################################################################
;60

(defn my-red [f coll]
  (loop [result [(first coll)] coll (rest coll)]
    (if (empty? coll)
      result
      (recur 
        (conj result (f (last result) (first coll))) (rest coll)))))
