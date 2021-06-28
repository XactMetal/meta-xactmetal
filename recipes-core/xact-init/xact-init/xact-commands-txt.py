#!/usr/bin/python3

import mmap
import os
import sys

sout = os.fdopen(sys.stdout.fileno(), 'wb')
f = open(sys.argv[2], "r+b")
mm = None
try:
    mm = mmap.mmap(f.fileno(), 0)
except:
    sout.write(b'e\n\n')
    f.close()
    sys.exit(0)


found = False

while mm.tell() < mm.size():
    pos = mm.tell()
    line = mm.readline()
    #print(line)
    if len(line) < 2:
        continue
    off = 0
    if line[0] == b'#'[0]:
        continue
    if line[0] == b'!'[0]:
        sout.write(b'!')
        if sys.argv[1] == "pop":
            tmppos = mm.tell()
            mm.seek(pos)
            mm.write(b'#')
            mm.seek(tmppos)
        off += 1
    if line[1] == b'?'[0]:
        sout.write(b'?')
        off += 1
    sout.write(b'\n')
    sout.write(line[off:])
    found = True
    break

if not found:
    sout.write(b'e\n\n')

mm.close()
os.fsync(f.fileno())
f.close()

