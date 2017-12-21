;1 Nothing but the Truth
;(= __ true)
true

;2 Simple Math
;(= (- 10 (* 2 3)) __)
4

;3 Intro to Strings
;(= __ (.toUpperCase "hello world"))
"HELLO WORLD"

;4 Intro to Lists
;(= (list __) '(:a :b :c))
:a :b :c

;5 Lists: conj
;(= __ (conj '(2 3 4) 1))
;(= __ (conj '(3 4) 2 1))
'(1 2 3 4)

;6 Intro to Vectors
;(= [__] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))
:a :b :c

;7 Vectors: conj
;(= __ (conj [1 2 3] 4))
;(= __ (conj [1 2] 3 4))
[1 2 3 4]

;8 Intro to Sets
;(= __ (set '(:a :a :b :c :c :c :c :d :d)))
;(= __ (clojure.set/union #{:a :b :c} #{:b :c :d}))
#{:a :b :c :d}

;9 Sets: conj
;(= #{1 2 3 4} (conj #{1 4 3} __))
2

;10 Intro to Maps
;(= __ ((hash-map :a 10, :b 20, :c 30) :b))
;(= __ (:b {:a 10, :b 20, :c 30}))
20

;11 Maps: conj
;(= {:a 1, :b 2, :c 3} (conj {:a 1} __ [:c 3]))
[:b 2]

;12 Intro to Sequences
;(= __ (first '(3 2 1)))
;(= __ (second [2 3 4]))
;(= __ (last (list 1 2 3)))
3

;13 Sequences: rest
;(= __ (rest [10 20 30 40]))
[20 30 40]

;14 Intro to Functions
;(= __ ((fn add-five [x] (+ x 5)) 3))
;(= __ ((fn [x] (+ x 5)) 3))
;(= __ (#(+ % 5) 3))
;(= __ ((partial + 5) 3))
8

;15 Double Down
;(= (__ 2) 4)
;(= (__ 3) 6)
;(= (__ 11) 22)
;(= (__ 7) 14)
(fn [x] (* x 2))

;16 Hello World
;(= (__ "Dave") "Hello, Dave!")
;(= (__ "Jenn") "Hello, Jenn!")
;(= (__ "Rhea") "Hello, Rhea!")
(fn [name] (str "Hello, " name "!"))

;17 Sequences: map
;(= __ (map #(+ % 5) '(1 2 3)))
'(6 7 8)

