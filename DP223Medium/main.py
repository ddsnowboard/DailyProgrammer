#!/usr/bin/env python
import re
def problem(word, slang):
    return "".join([i for i in word if i in slang]) == slang
WORDS = ("synchronized", "misfuntioned", "mispronounced", "shotgunned", "snond")
SLANG = "snond"
for i in WORDS:
    print("{} returns {}".format(i, problem(i, SLANG)))
