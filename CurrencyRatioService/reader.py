from typing import Tuple, Generator


def read(filename)-> Generator[Tuple[str, str, float], None, None]:
    with open(filename, 'r') as f:
        try:
            for line in f:
                c1, c2, val = line.split()
                line = (c1, c2, float(val))
                yield line
        except ValueError:
            print("Currency file: {} has wrong format".format(filename))
