#lang racket
(define (upcase? l)
  (char=? l (char-upcase l)))
(define (downcase? l)
  (not (upcase? l)))
(define (index l v)
  (define (indexHelper l v idx)
    (if (and (not (empty? l)) (char=? v (car l)))
      idx
      (indexHelper (cdr l) v (+ idx 1))))
  (indexHelper l v 0))

(define (encipher l)
  (define alphabet "abcdefghijklmnopqrstuvwxyz")
  (define letters (string->list alphabet))
  (when (upcase? l)
    (set! letters (map char-upcase letters))
    )
  (if (not (char-alphabetic? l))
    l
    (list-ref letters (- (- (length letters) 1) (index letters l)))))
(define INPUT "gsrh rh zm vcznkov lu GSV zgyzhs xrksvi")
(display (list->string (map encipher (string->list INPUT))))
(display "\n")
