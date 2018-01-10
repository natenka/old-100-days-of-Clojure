;#################################################################
;59 Juxtaposition
;(= [21 6 1] ((__ + max min) 2 3 5 1 6 4))
;(= ["HELLO" 5] ((__ #(.toUpperCase %) count) "hello"))
;(= [2 6 4] ((__ :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))

;my-answer
(fn [& funcs]
  (fn [& args]
    (for [f funcs] (apply f args))))

;solution1
(fn [& fs]
  (fn [& args]
    (reduce #(conj %1 (apply %2 args)) [] fs)))

;solution2
(fn [& fns]
  (fn [& args]
    (map (fn [f] (apply f args)) (vec fns))))
