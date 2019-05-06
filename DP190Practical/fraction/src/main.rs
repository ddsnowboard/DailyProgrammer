use std::cmp::min;
use std::fmt;
use std::ops;

type NumType = u32;
type InputNumType = i32;
#[derive(Clone, Copy, PartialEq, Eq)]
struct Fraction {
    sign: bool,
    numerator: NumType,
    denominator: NumType,
}

fn xor(a: bool, b: bool) -> bool {
    return !(a && b) && (a || b);
}

fn sgn(a: InputNumType) -> InputNumType {
    if a < 0 {
        -1
    } else {
        1
    }
}

impl Fraction {
    fn _reduce(&mut self) {
        let mut found_one = false;
        {
            let top: &mut NumType = &mut self.numerator;
            let bottom: &mut NumType = &mut self.denominator;
            let smallest = min(*top, *bottom);
            for i in 2..=smallest {
                if *top % i == 0 && *bottom % i == 0 {
                    *top /= i;
                    *bottom /= i;
                    found_one = true;
                    break;
                }
            }
        }
        if found_one {
            self._reduce();
        }
    }
    fn new(top: i32, bottom: i32) -> Result<Self, String> {
        if bottom == 0 {
            return Err(String::from("You can't divide by zero!"));
        }
        let sign = !xor(top < 0, bottom < 0);
        let top = (top * sgn(top)) as NumType;
        let bottom = (bottom * sgn(bottom)) as NumType;
        let mut out = Fraction {
            sign: sign,
            numerator: top,
            denominator: bottom,
        };
        out._reduce();
        Ok(out)
    }
}

impl fmt::Debug for Fraction {
    fn fmt(&self, formatter: &mut fmt::Formatter) -> fmt::Result {
        let top = self.numerator;
        let bottom = self.denominator;
        let sign = if self.sign { "" } else { "-" };
        formatter.write_fmt(format_args!("{}{} / {}", sign, top, bottom))
    }
}

impl fmt::Display for Fraction {
    fn fmt(&self, formatter: &mut fmt::Formatter) -> fmt::Result {
        formatter.write_fmt(format_args!("{:?}", self))
    }
}

impl ops::Add<Fraction> for Fraction {
    type Output = Fraction;
    fn add(mut self, mut rhs: Fraction) -> Fraction {
        if self.denominator != rhs.denominator {
            let newDenominator = self.denominator * rhs.denominator;
            self.numerator *= rhs.denominator;
            rhs.denominator *= self.denominator;
            rhs.numerator *= self.denominator;
            self.denominator = newDenominator;
        }
        let (sign, newNumerator) = match (self.sign, rhs.sign) {
            (a, b) if a == b => (self.sign, self.numerator + rhs.numerator),
            (true, false) if self.numerator >= rhs.numerator => {
                (true, self.numerator - rhs.numerator)
            }
            (true, false) if self.numerator < rhs.numerator => {
                (false, rhs.numerator - self.denominator)
            }
            (false, true) if self.numerator >= rhs.numerator => {
                (false, self.numerator - rhs.numerator)
            }
            (false, true) if self.numerator < rhs.numerator => {
                (true, rhs.numerator - self.denominator)
            }
            _ => panic!("We shouldn't have gotten here"),
        };
        let mut out = Fraction {
            sign: sign,
            numerator: newNumerator,
            denominator: self.denominator,
        };
        out._reduce();
        out
    }
}

fn main() {
    println!("Hello world!");
}

#[test]
fn add_test() {
    let a = Fraction::new(5, 3).unwrap();
    let b = Fraction::new(8, 3).unwrap();
    let c = Fraction::new(7, 6).unwrap();
    let d = Fraction::new(-7, 12).unwrap();
    assert_eq!(
        a + b,
        Fraction {
            sign: true,
            numerator: 13,
            denominator: 3
        }
    );
    assert_eq!(
        b + c,
        Fraction {
            sign: true,
            numerator: 23,
            denominator: 6
        }
    );

    assert_eq!(
        c + d,
        Fraction {
            sign: true,
            numerator: 1,
            denominator: 2
        }
    );

    assert_eq!(
        d + c,
        Fraction {
            sign: false,
            numerator: 1,
            denominator: 2
        }
    );
}

#[test]
fn show_test() {
    let f = Fraction {
        sign: true,
        numerator: 5,
        denominator: 4,
    };
    assert_eq!(format!("{:?}", f), format!("{}", f));
    assert_eq!(format!("{:?}", f), "5 / 4");
    let f = Fraction {
        sign: false,
        numerator: 5,
        denominator: 4,
    };
    assert_eq!(format!("{:?}", f), "-5 / 4");
}

#[test]
fn new_test() {
    let n = Fraction::new;
    let f = n(5, 3);
    assert_eq!(
        f,
        Ok(Fraction {
            sign: true,
            numerator: 5,
            denominator: 3
        })
    );
    let f = n(55, 33);
    assert_eq!(
        f,
        Ok(Fraction {
            sign: true,
            numerator: 5,
            denominator: 3
        })
    );
    let f = n(-55, 33);
    assert_eq!(
        f,
        Ok(Fraction {
            sign: false,
            numerator: 5,
            denominator: 3
        })
    );
    if let Ok(_) = n(55, 0) {
        panic!();
    }

    let f = n(16384, 8192);
    assert_eq!(
        f,
        Ok(Fraction {
            sign: true,
            numerator: 2,
            denominator: 1
        })
    );
    let f = n(23, 6);
    assert_eq!(
        f,
        Ok(Fraction {
            sign: true,
            numerator: 23,
            denominator: 6
        })
    );
}
