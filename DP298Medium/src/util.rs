pub mod util {
    fn _surroundIndex(s: &mut String, currIdx: usize, toInsert: &str)
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
            for currIndex in (0..toInsert.len()).rev() {
                self.insert(idx, toInsert.chars().nth(currIndex).unwrap()); 
            }
        }
    }

#[test]
    fn test_insertString() 
    {
        let mut s = String::from("apples");
        s.insert_string(0, "bad");
        assert_eq!(s, "badapples");
        let mut s = String::from("apples");
        s.insert_string(3, "red");
        assert_eq!(s, "appredles");
    }

    fn highlight_char(s: &str, idx: usize)
    {
        println!("{}", s);
        for _ in 0..idx {
            print!(" ");
        }
        println!("^");
    }
}

