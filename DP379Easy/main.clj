(defn deltifyBrackets [brackets]
  (let [endpts (map first brackets)
        rates (map second (subvec brackets 1))
        ranges (partition 2 1 endpts)]
    (map vector ranges rates)
  ))

(def brackets (deltifyBrackets ['[10000 0.0]
                '[30000 0.1]
                '[100000 0.25]
                '[##Inf 0.4]]))

(defn taxBracket [income rateTuple]
  (let [[[bottom top] rate] rateTuple]
    (cond
      (<= income bottom) 0
      (and (> income bottom) (<= income top)) (* rate (- income bottom))
      (> income top) (* rate (- top bottom)))))

(defn tax [income] 
  (int (reduce + 
          (map 
            #(taxBracket income %)
            brackets))))

(println (tax 0))
(println (tax 10000))
(println (tax 10009))
(println (tax 10010))
(println (tax 12000))
(println (tax 56789))
(println (tax 1234567))
