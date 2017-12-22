;#################################################################
;22 Count a Sequence
;(= (__ '(1 2 3 3 1)) 5)
;(= (__ "Hello World") 11)
;(= (__ [[1 2] [3 4] [5 6]]) 3)
;(= (__ '(13)) 1)
;(= (__ '(:a :b :c)) 3)

;вариант обычной функцией:
(defn count-elements [collection]
  (loop [summ 0 coll collection]
    (if (not= (first coll) nil)
      (recur (inc summ) (rest coll))
      summ)))

;anonymous function
#(loop [summ 0 coll %1]
    (if (not= (first coll) nil)
      (recur (inc summ) (rest coll))
      summ))

;Крутой вариант решения из ответов 4clojure
#(reduce (fn [x y] (inc x)) 0 %)

;#################################################################
;23 Reverse a Sequence
;(= (__ [1 2 3 4 5]) [5 4 3 2 1])
;(= (__ (sorted-set 5 7 2 7)) '(7 5 2))
;(= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])

;вариант обычной функцией:
(defn reverse-seq [initial-seq]
  (loop [myvec [] curr-pos (dec (count initial-seq))]
    (if (not= curr-pos -1)
      (recur (into myvec [(nth (vec initial-seq) curr-pos)]) (dec curr-pos))
       myvec)))

;anonymous function
#(loop [myvec [] curr-pos (dec (count %1))]
    (if (not= curr-pos -1)
      (recur (into myvec [(nth (vec %1) curr-pos)]) (dec curr-pos))
       myvec))

;Крутой вариант решения из ответов 4clojure
(#(into () %) [1 2 3])
;(3 2 1)

(reduce conj () [1 2 3])
;(3 2 1)

;тут используется особенность списков в Clojure - элементы добавляются слева
;поэтому когда мы по очереди добавляем числа 1, 2, 3
;сначала добавляется 1, затем слева 2, затем слева 3
;в итоге мы получаем перевернутую последовательность

;И тут есть еще один момент, что две последовательности элементов будут равны,
; если в них одни и те же элементы, в том же порядке, даже когда они разного типа
(= '(1 2 3) [1 2 3])
;=> true

