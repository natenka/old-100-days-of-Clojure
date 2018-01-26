;#################################################################
;65 Black Box Testing

; my answer
(defn black-box [coll]
  (if (associative? coll)
    (if (= (vec coll) coll)
      :vector
      :map)
    (if (= (first (vec (conj (conj coll 4) 5))) 5)
      :list
      :set)))

; solution
(comp {\# :set \{ :map \[ :vector \c :list} first str)


; solution
#(let [r (str %)]
   (if (.contains r "#")
     :set
       (if (.contains r "{")
         :map
         (if (.contains r "[")
           :vector
           :list))))

;#################################################################
;66 Greatest Common Divisor

; my answer
(fn gcd [num1 num2]
  (if (= num2 0)
    num1
    (recur (Math/abs (- num1 num2)) (min num1 num2))))

; solution
(fn [x y] (if (zero? y) x (recur y (rem x y))))

;#################################################################
;67 Prime Numbers

; my answer
(defn prime-nums [n]
  (let [prime?
        (fn [x]
          (if (not-any? #(= 0 (mod x %)) (range 2 x))
           true
           false))]
    (take n (filter prime? (drop 2 (range))))))


; solution
(fn [n]
  (let [prime (fn [x] (not (some #(zero? (mod x %)) (range 2 x))))]
  (take n (filter prime (iterate inc 2)))))

; solution
#(take % ((fn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter (fn [xx] (not= 0 (mod xx (first s))))
                                 (rest s)))))) (iterate inc 2)))
; solution
#(loop[result [], ind 0, remaining (drop 2 (range))];(drop 2 (range)) represents 2, 3, .... to infinite
   (let[primeNum (first remaining)];take out the new prime number
     (if (= ind %) result
       (recur (conj result primeNum) (inc ind) (remove (fn[x] (= 0 (rem x primeNum))) remaining))
