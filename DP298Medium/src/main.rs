#![allow(non_snake_case)]
// https://www.reddit.com/r/dailyprogrammer/comments/5m034l/20170104_challenge_298_intermediate_too_many_or/
use std::fs::File;
use std::io::Read;

fn main() {
    let mut f = File::open("input.txt").unwrap();
    let mut s = String::new();
    f.read_to_string(&mut s).unwrap();
    let lines = s.lines();
    for line in lines {
        let mut opens = 0;
        let mut line = String::from(line);
        let mut badParen = None;
        for c in line.char_indices() {
            match c
            {
                (_, '(') => opens += 1,
                (x, ')') => {
                    if opens == 0 {
                        badParen = Some(x);
                        break;
                    }
                    else 
                    {
                        opens -= 1;
                    }
                }
                (_, _) => {}
            }
        }
        match badParen {
            Some(parenLocation) => 
            {
                line.insert(parenLocation, '*');
                line.insert(parenLocation, '*');
                // I need to move 3 forward to get the two asterisks that I just inserted and the paren
                line.insert(parenLocation + 3, '*');
                line.insert(parenLocation + 3, '*');
            }, 
            None => {}
        }
        println!("{}", line);
    }
}
