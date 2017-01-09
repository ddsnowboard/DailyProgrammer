#![allow(non_snake_case)]
// https://www.reddit.com/r/dailyprogrammer/comments/5m034l/20170104_challenge_298_intermediate_too_many_or/
use std::fs::File;
use std::io::Read;

fn main() {
    let mut f = File::open("input.txt").unwrap();
    let mut s = String::new();
    f.read_to_string(&mut s).unwrap();



    for line in s.lines() {
        let mut line = String::from(line);
        let mut currIdx = 0;
        let badIndex: usize;
        while currIdx < line.len() {
            let currChar = line.chars().nth(currIdx).unwrap();
            if  currChar == ')'
            {
                badIndex = currIdx;
                surroundIndex(&mut line, badIndex, "**");
                break;
            }
            else if currChar == '('
            {
                match find_match(&mut line, currIdx)
                {
                    Ok(x) => currIdx = x,
                    Err(x) => {badIndex = x; surroundIndex(&mut line, badIndex, "**"); break;}
                }
            }
        }
        println!("{}", line);
    }
}

fn find_match(s: &String, startingIdx: usize) -> Result<usize, usize>
{
    let mut chars = s.chars();
    let mut idx = startingIdx;
    let retval: Result<_,_> = Err(0);
    while idx < s.len()
    {
        match chars.nth(idx).unwrap()
        {
            '(' => {
                match find_match(s, idx) {
                    Ok(x) => {idx = x},
                    Err(x) => return Err(x), 
                }
            }
            ')' => return Ok(idx), 
            _ => {}
        }
    }
    retval
}

fn surroundIndex(s: &mut String, currIdx: usize, toInsert: &str)
{
    s.insert_string(currIdx, toInsert);
    s.insert_string(currIdx + 1 + toInsert.len(), toInsert);
}

trait InsertsString {
    fn insert_string(&mut self, usize, &str);
}

impl InsertsString for String {
    fn insert_string(&mut self, idx: usize, toInsert: &str)
    {
        println!("What's going on? idx is {} and toInsert is {}", idx, toInsert);
        for currIndex in (idx..toInsert.len()).rev() {
            self.insert(idx, toInsert.chars().nth(currIndex).unwrap()); 
        }
    }
}
