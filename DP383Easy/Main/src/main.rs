use crate::Main::{same_necklace, rotate_n};
use criterion::{black_box, criterion_group, criterion_main, Criterion};
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::collections::HashSet;

mod Main {

    pub fn rotate_n(s: &str, n: usize) -> String {
        let length = s.len();
        let new_start: &str = &s[length - n..length];
        let new_end: &str = &s[0..length - n];
        let mut out = String::from(new_start);
        out += new_end;
        out
    }

    pub fn same_necklace(a: &str, b: &str) -> bool {
        if a.len() == 0 && b.len() == 0 {
            return true;
        } else if a.len() != b.len() {
            return false;
        }

        let achars: Vec<_> = a.chars().collect();
        let bchars: Vec<_> = b.chars().collect();

        for how_many in 0..b.len() {
            if achars[a.len() - 1] == bchars[b.len() - how_many - 1] && a == rotate_n(b, how_many) {
                return true;
            }
        }
        false
    }

    pub fn repeats(a: &str) -> usize {
        if a.len() == 0 {
            1
        } else {
            (0..a.len())
                .map(|n| rotate_n(a, n))
                .filter(|s| s == a)
                .count()
        }
    }

    #[test]
    fn simple_rotate_test() {
        assert_eq!(rotate_n("apple", 1), "eappl");
        assert_eq!(rotate_n("apple", 2), "leapp");
        assert_eq!(rotate_n("apple", 0), "apple");
    }

    #[test]
    fn test_same_necklace() {
        assert!(same_necklace("nicole", "icolen"));
        assert!(same_necklace("nicole", "lenico"));
        assert!(!same_necklace("nicole", "coneli"));
        assert!(same_necklace("aabaaaaabaab", "aabaabaabaaa"));
        assert!(!same_necklace("abc", "cba"));
        assert!(!same_necklace("xxyyy", "xxxyy"));
        assert!(!same_necklace("xyxxz", "xxyxz"));
        assert!(same_necklace("x", "x"));
        assert!(!same_necklace("x", "xx"));
        assert!(!same_necklace("x", ""));
        assert!(same_necklace("", ""));
    }

    #[test]
    fn test_repeats() {
        assert_eq!(repeats("abc"), 1);
        assert_eq!(repeats("abcabcabc"), 3);
        assert_eq!(repeats("abcabcabcx"), 1);
        assert_eq!(repeats("aaaaaa"), 6);
        assert_eq!(repeats("a"), 1);
        assert_eq!(repeats(""), 1);
    }

}

fn main() {
    let group_size = 4;
    let file = File::open("enable1.txt").unwrap();
    let buf_reader = BufReader::new(file);
    let words: HashSet<String> = buf_reader.lines().map(|r| r.unwrap()).collect();
    let out = words.iter().filter(|w| (0..w.len()).filter(|i| words.contains(&rotate_n(w, *i))).count() >= group_size).nth(0).unwrap();
    let good_words: Vec<String> = (0..out.len()).map(|i| rotate_n(&out, i)).filter(|w| words.contains(w)).collect();
    println!("{:?}", good_words);
}

fn criterion_benchmark(c: &mut Criterion) {
    c.bench_function("same_necklace success test", |b| {
        b.iter(|| {
            same_necklace(
                black_box("abcdefghijklmnopqrstuvwxyz"),
                "pqrstuvwxyzabcdefghijklmno",
            )
        })
    });
    c.bench_function("same_necklace easy fail test", |b| {
        b.iter(|| same_necklace(black_box("abcdefghijklmnopqrstuvwxyz"), "rocks"))
    });
    c.bench_function("same_necklace hard fail test", |b| {
        b.iter(|| {
            same_necklace(
                black_box("abcdefghijklmnopqrstuvwxyz"),
                "pqrrstuwxyzabcdefghijklmno",
            )
        })
    });
}

criterion_group!(benches, criterion_benchmark);
// criterion_main!(benches);
