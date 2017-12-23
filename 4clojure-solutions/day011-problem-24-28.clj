;#################################################################
;24 Sum It All Up
;(= (__ [1 2 3]) 6)
;(= (__ (list 0 -2 5 5)) 8)
;(= (__ #{4 2 1}) 7)
;(= (__ '(0 0 -1)) -1)
;(= (__ '(1 10 3)) 14)

;answer
reduce +


;#################################################################
;25 Find the odd numbers
;(= (__ #{1 2 3 4 5}) '(1 3 5))
;(= (__ [4 2 1 6]) '(1))
;(= (__ [2 2 4 6]) '())
;(= (__ [1 1 1 3]) '(1 1 1 3))

;answer
filter odd?

;#################################################################
;26 Fibonacci Sequence
;(= (__ 3) '(1 1 2))
;(= (__ 6) '(1 1 2 3 5 8))
;(= (__ 8) '(1 1 2 3 5 8 13 21))

;named func
(defn fibonacci [num]
  (loop [fibo [1 1], penultnum 1, lastnum 1]
    (if (not= (count fibo) num)
      (recur (conj fibo (+ penultnum lastnum)) lastnum (+ penultnum lastnum))
      fibo)))

;anonymous function
(#(loop [fibo [1 1] pnum 1 lnum 1]
    (if (not= (count fibo) %1)
      (recur (conj fibo (+ pnum lnum)) lnum (+ pnum lnum))
      fibo)) 3)
;=> [1 1 2]


;#################################################################
;27 Palindrome Detector
;(false? (__ '(1 2 3 4 5)))
;(true? (__ "racecar"))
;(true? (__ [:foo :bar :foo]))
;(true? (__ '(1 1 3 3 1 1)))
;(false? (__ '(:a :b :c)))

;named func
(defn palindrome-detector [collection]
  (let [coll (vec collection) length (count coll) half (quot (count coll) 2)]
    (if (even? length)
      (= (subvec coll 0 half) (reverse (subvec coll half length)))
      (= (subvec coll 0 half) (reverse (subvec coll (+ half 1) length))))))

(palindrome-detector [1 1 3 3 2 1])
;false
(palindrome-detector [1 2 3 3 2 1])
;true
(palindrome-detector [1 2 1])
;true
(palindrome-detector "afa")
;true
(palindrome-detector '(:a :b :c))
;false
(palindrome-detector '(:a :b :a))
;true
(palindrome-detector [:foo :bar :foo])
;true

;anonymous function
#(let [coll (vec %1) length (count coll) half (quot (count coll) 2)]
  (if (even? length)
    (= (subvec coll 0 half) (reverse (subvec coll half length)))
    (= (subvec coll 0 half) (reverse (subvec coll (+ half 1) length)))))

;solution:
(fn [xs] (= (reverse xs) (reverse (reverse xs))))

;refactor my answer
(defn palindrome-detector2 [collection]
  (let [coll (vec collection)]
    (= coll (reverse coll))))

#(let [coll (vec %1)]
    (= coll (reverse coll)))



;#################################################################
;28 Flatten a Sequence
;(= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
;(= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
;(= (__ '((((:a))))) '(:a))


;named func
(defn flattener [coll]
  (loop [result [] items (vec coll)]
    ;(println items)
    (if (sequential? (first items))
      (recur result (into (vec (first items)) (rest (vec items))))
      (if (empty? items)
        result
        (recur (conj result (first items)) (rest (vec items)))))))


;anonymous function
#(loop [result [] items (vec %)]
   (if (sequential? (first items))
      (recur result (into (vec (first items)) (rest (vec items))))
      (if (empty? items)
        result
        (recur (conj result (first items)) (rest (vec items))))))


;################# solutions
;solution1
(fn iter [xs] 
    (reduce (fn [acc x]
        (if (coll? x)
            (concat acc (iter x))
            (concat acc (cons x ()))))
            ()
            xs))

;solution2
#(filter (complement sequential?)
          (rest (tree-seq sequential? seq %)))

;solution3
(fn myFlatten [x]
  (if (coll? x)
    (mapcat myFlatten x)
    [x]))


