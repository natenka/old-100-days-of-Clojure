;#################################################################
;57 Simple Recursion
;(= __ ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

'(5 4 3 2 1)

;#################################################################
;58 Function Composition
;(= [3 2 1] ((__ rest reverse) [1 2 3 4]))
;(= 5 ((__ (partial + 3) second) [1 2 3 4]))
;(= true ((__ zero? #(mod % 8) +) 3 5 7 9))
;(= "HELLO" ((__ #(.toUpperCase %) #(apply str %) take) 5 "hello world"))

;my answer

(fn [& funcs]
  (fn [& args]
    (loop [current (last funcs) funcs (butlast funcs) args args]
      (if (= funcs nil)
        (apply current args)
        (recur (last funcs) (butlast funcs) (vector (apply current args)))))))


;solution
(fn [& funcs]
  (fn [& args]
    (first (reduce #(vector (apply %2 %1)) args (reverse funcs)))))

