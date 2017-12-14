(ns try-night.core
  (:gen-class)
  (:require clojure.set))

(defn f-values [f xs ys]
  (for [x (range xs) y (range ys)]
       [x y (rem (f x y) 256)]))

(def frame (java.awt.Frame.))
(.setVisible frame true)
(def gfx (.getGraphics frame))

(defn clear [g] (.clearRect g 0 0 200 200))

(defn draw-values [f xs ys]
  (.setVisible frame true)
  (.setSize frame (java.awt.Dimension. xs ys))
  (doseq [[x y v] (f-values f xs ys)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))

(defn plus-mult [x y]
  (* 3 (+ x y)))

(defn rand-bit-func [x y]
  ((rand-nth [bit-and bit-or bit-xor]) x y))

(defn rand-func [x y]
  ((rand-nth [+ *]) x y))
