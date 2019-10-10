use std::collections::HashMap;
use std::collections::HashSet;
use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;

lazy_static! {
    pub static ref MORSE_MAP: HashMap<char, &'static str> = "abcdefghijklmnopqrstuvwxyz"
        .chars()
        .zip(vec![
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..",
        ])
        .collect();
    pub static ref WORD_LIST: Vec<String> = BufReader::new(File::open("wordlist").unwrap())
        .lines()
        .map(|s| s.unwrap())
        .collect();
    pub static ref WORD_MORSE: HashMap<String, String> =
        WORD_LIST.iter().map(|s| (s.clone(), smorse(s))).collect();
    pub static ref MORSE_LETTER: HashMap<String, char> =
        MORSE_MAP.iter().map(|(k, v)| (v.to_string(), *k)).collect();
    pub static ref ALPHABET_SET: HashSet<char> = [
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    ]
    .into_iter()
    .map(|&x| x)
    .collect();
}

fn consume(s: &str, c: char) -> &str {
    let code = MORSE_MAP[&c];
    assert!(s.starts_with(code));
    &s[code.len()..]
}

fn possible_first_letters(s: &str, remaining: &HashSet<char>) -> Vec<char> {
    remaining
        .iter()
        .filter(|c| s.starts_with(MORSE_MAP[c]))
        .map(|&c| c)
        .collect()
}

fn find_order(s: &str, remaining: &mut HashSet<char>) -> Option<String> {
    if remaining.is_empty() {
        return Some("".to_string());
    }
    for c in possible_first_letters(s, &remaining) {
        remaining.remove(&c);
        let new_string = consume(s, c);
        if let Some(mut s) = find_order(new_string, remaining) {
            s.insert(0, c);
            return Some(s);
        }
        remaining.insert(c);
    }
    None
}

pub fn smalpha(s: &str) -> Option<String> {
    find_order(s, &mut ALPHABET_SET.clone())
}

pub fn increment_morse(mut s: String) -> Option<String> {
    for idx in (0..s.len()).rev() {
        match &s[idx..idx + 1] {
            "." => {
                s.replace_range(idx..idx + 1, &"-");
                return Some(s);
            }
            "-" => {
                s.replace_range(idx..idx + 1, &".");
            }
            _ => {
                panic!(
                    "There was something there shouldn't have been in the string; {}",
                    s
                );
            }
        }
    }
    None
}

pub fn get_morse(c: char) -> &'static str {
    if !c.is_ascii_alphabetic() {
        panic!("{} is not a letter of the alphabet", c);
    }

    let lowered: char = if c.is_uppercase() {
        c.to_lowercase().to_string().chars().next().unwrap()
    } else {
        c
    };

    assert!(MORSE_MAP.contains_key(&lowered));
    MORSE_MAP[&lowered]
}

pub fn smorse(s: &str) -> String {
    let mut out = String::new();
    const LONGEST_MORSE_STRING: usize = 4;
    out.reserve(s.len() * LONGEST_MORSE_STRING);
    for c in s.chars() {
        out.push_str(get_morse(c));
    }
    out
}

pub fn count_letters(s: &str, c: &char) -> usize {
    s.chars().filter(|ch| ch == c).count()
}

#[test]
fn test_get_morse() {
    assert_eq!(get_morse('s'), "...");
}

#[test]
fn test_simple_smorse() {
    assert_eq!(smorse("SOS"), "...---...");
    assert_eq!(smorse("sos"), "...---...");
    assert_eq!(smorse("sOs"), "...---...");
}

#[test]
#[ignore]
fn test_enable1() {
    const NUM_DOTS: usize = 2_499_157;
    const NUM_DASHES: usize = 1_565_081;
    let mut counted_dots = 0;
    let mut counted_dashes = 0;
    WORD_LIST.iter().map(|s| smorse(&s)).for_each(|s| {
        counted_dots += count_letters(&s, &'.');
        counted_dashes += count_letters(&s, &'-');
    });
    assert_eq!(counted_dots, NUM_DOTS);
    assert_eq!(counted_dashes, NUM_DASHES);
}

#[test]
fn test_increment_morse() {
    assert_eq!(
        Some(".....-".to_string()),
        increment_morse("......".to_string())
    );
    assert_eq!(
        Some("-....-".to_string()),
        increment_morse("-.....".to_string())
    );
    assert_eq!(
        Some("-.....".to_string()),
        increment_morse(".-----".to_string())
    );
    assert_eq!(None, increment_morse("------".to_string()));
}

#[test]
fn test_smalpha() {
    const STRINGS: [&'static str; 4] = ["this", "is", "some", "asdfgh"];
    for s in STRINGS.iter() {
        let morsed = smorse(s);
        let reversed = find_order(&morsed, s.chars().collect()).unwrap();
        assert_eq!(morsed, smorse(&reversed));
    }
}

#[test]
fn test_smalpha_optimized() {
    const STRINGS: [&'static str; 4] = ["this", "is", "some", "asdfgh"];
    for s in STRINGS.iter() {
        let morsed = smorse(s);
        let reversed = new_find_order(&morsed, &mut s.chars().collect()).unwrap();
        assert_eq!(morsed, smorse(&reversed));
    }
}
